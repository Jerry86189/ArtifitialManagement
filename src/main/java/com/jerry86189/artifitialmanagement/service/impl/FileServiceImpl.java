package com.jerry86189.artifitialmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry86189.artifitialmanagement.config.FileStorageConfig;
import com.jerry86189.artifitialmanagement.entity.FileInfo;
import com.jerry86189.artifitialmanagement.entity.User;
import com.jerry86189.artifitialmanagement.exceptions.DeleteFailed;
import com.jerry86189.artifitialmanagement.exceptions.InsertFailed;
import com.jerry86189.artifitialmanagement.exceptions.NotFound;
import com.jerry86189.artifitialmanagement.mapper.FileMapper;
import com.jerry86189.artifitialmanagement.mapper.UserMapper;
import com.jerry86189.artifitialmanagement.service.FileService;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: FileServiceImpl
 * Description: TODO
 * date: 2023/06/10 21:34
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileStorageConfig fileStorageConfig;

    /**
     * 上传文件
     *
     * @param file       文件对象
     * @param uploaderId 上传者的用户ID
     */
    @Override
    @Transactional
    public void uploadFile(MultipartFile file, Long uploaderId) {
        String fileName = file.getOriginalFilename();
        if (!isValidFileName(fileName)) {
            throw new InvalidFileNameException(fileName, "File format is not supported. Only .csv files are allowed.");
        }

        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().eq("file_name", fileName);
        if (fileMapper.selectOne(queryWrapper) != null) {
            throw new InsertFailed("File already exists.");
        }

        String filePath = fileStorageConfig.getUploadDir() + "/" + fileName;
        File destFile = new File(filePath);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new InsertFailed("File upload failed: " + e.getMessage(), e);
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setUploaderId(uploaderId);
        fileInfo.setFileName(fileName);
        fileInfo.setUploadTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        fileInfo.setFileSize(file.getSize());
        fileInfo.setFilePath(filePath);

        int insert = fileMapper.insert(fileInfo);
        if (insert != 1) {
            throw new InsertFailed("File upload failed.");
        }
    }

    /**
     * 下载文件
     *
     * @param fileId 需要下载的文件ID
     * @return 包含文件内容的资源对象
     */
    @Override
    public Resource downloadFile(Long fileId) {
        FileInfo fileInfo = fileMapper.selectById(fileId);

        if (fileInfo == null) {
            throw new InsertFailed("File not found.");
        }

        try {
            Path filePath = Paths.get(fileInfo.getFilePath());
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new NotFound("Could not read file: " + fileInfo.getFileName());
            }

        } catch (MalformedURLException e) {
            throw new NotFound("Error occurred while downloading file: " + fileInfo.getFileName(), e);
        }
    }

    /**
     * 删除文件
     *
     * @param fileId 需要删除的文件ID
     * @param userId 进行操作的用户ID
     */
    @Override
    @Transactional
    public void deleteFile(Long fileId, Long userId) {
        System.out.println("file id: " + fileId +
                ", user id: " + userId);
        FileInfo fileInfo = fileMapper.selectById(fileId);

        if (fileInfo == null) {
            throw new NotFound("File with file ID: " + fileId + "not found.");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new NotFound("User with user ID: " + userId + "not found.");
        }
        String roleValue = user.getRole().getValue();
        if (fileInfo.getUploaderId() != userId && !roleValue.equals("ADMIN")) {
            throw new DeleteFailed("You do not have permission to delete this file.");
        }

        Path filePath = Paths.get(fileInfo.getFilePath());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new DeleteFailed("Failed to delete file in file storage end: " + fileInfo.getFileName());
        }

        int delete = fileMapper.deleteById(fileId);
        if (delete != 1) {
            throw new DeleteFailed("Failed to delete file in database log: " + fileInfo.getFileName());
        }
    }

    /**
     * 批量删除文件
     *
     * @param ids    需要删除的文件ID列表
     * @param userId 进行操作的用户ID
     */
    @Override
    public void deleteFiles(List<Long> ids, Long userId) {
        for (Long id : ids) {
            deleteFile(id, userId);
        }
    }

    /**
     * 获取所有文件信息
     *
     * @param pageNum  页码
     * @param pageSize 每页显示的数量
     * @return 文件信息列表
     */
    @Override
    public List<FileInfo> getAllFiles(int pageNum, int pageSize) {
        Page<FileInfo> page = new Page<>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().orderByDesc("upload_timestamp");
        IPage<FileInfo> fileInfoPage = fileMapper.selectPage(page, queryWrapper);

        if (fileInfoPage == null || fileInfoPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (fileInfoPage.getRecords().isEmpty()) {
            throw new NotFound("No files found");
        }

        return fileInfoPage.getRecords();
    }

    /**
     * 获取指定文件信息
     *
     * @param fileId 文件ID
     * @return 文件信息对象
     */
    @Override
    public FileInfo getFileInfo(Long fileId) {
        FileInfo fileInfo = fileMapper.selectById(fileId);

        if (fileInfo == null) {
            throw new NotFound("File with file ID: " + fileId + "not found.");
        }

        return fileInfo;
    }

    /**
     * 根据文件名获取文件信息
     *
     * @param filename 文件名
     * @param pageNum  页码
     * @param pageSize 每页显示的数量
     * @return 文件信息列表
     */
    @Override
    public List<FileInfo> getFilesByFilename(String filename, int pageNum, int pageSize) {
        Page<FileInfo> page = new Page<>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().like("file_name", filename).orderByDesc("upload_timestamp");
        IPage<FileInfo> fileInfoPage = fileMapper.selectPage(page, queryWrapper);

        if (fileInfoPage == null || fileInfoPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (fileInfoPage.getRecords().isEmpty()) {
            throw new NotFound("No files found like name: " + filename);
        }

        return fileInfoPage.getRecords();
    }

    /**
     * 根据上传者获取文件信息
     *
     * @param uploaderId 上传者ID
     * @param pageNum    页码
     * @param pageSize   每页显示的数量
     * @return 文件信息列表
     */
    @Override
    public List<FileInfo> getFilesByUploaderId(Long uploaderId, int pageNum, int pageSize) {
        Page<FileInfo> page = new Page<>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().like("uploader_id", uploaderId).orderByDesc("upload_timestamp");
        IPage<FileInfo> fileInfoPage = fileMapper.selectPage(page, queryWrapper);

        if (fileInfoPage == null || fileInfoPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (fileInfoPage.getRecords().isEmpty()) {
            throw new NotFound("No files found like ID: " + uploaderId);
        }

        return fileInfoPage.getRecords();
    }

    /**
     * 获取所有文件数量
     *
     * @return 文件数量
     */
    @Override
    public int getCountOfAllFiles() {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().orderByDesc("upload_timestamp");
        return fileMapper.selectCount(queryWrapper);
    }

    /**
     * 根据文件名获取文件数量
     *
     * @param filename 文件名
     * @return 文件数量
     */
    @Override
    public int getCountOfFilesByFilename(String filename) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().like("file_name", filename).orderByDesc("upload_timestamp");
        return fileMapper.selectCount(queryWrapper);
    }

    /**
     * 根据上传者获取文件数量
     *
     * @param uploaderId 上传者ID
     * @return 文件数量
     */
    @Override
    public int getCountOfFilesByUploaderId(Long uploaderId) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>().like("uploader_id", uploaderId).orderByDesc("upload_timestamp");
        return fileMapper.selectCount(queryWrapper);
    }

    private boolean isValidFileName(@NotNull String fileName) {
        //这里，我们假设一个合法的文件名可以包含字母、数字、下划线或者连字符，并且必须是.csv文件
        return fileName.matches("[\\w-]+\\.csv");
    }
}
