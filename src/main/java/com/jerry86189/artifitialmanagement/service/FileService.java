package com.jerry86189.artifitialmanagement.service;

import com.jerry86189.artifitialmanagement.entity.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: FileService
 * Description: TODO
 * date: 2023/06/10 21:15
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param file 文件对象
     * @param uploaderId 上传者的用户ID
     */
    void uploadFile(MultipartFile file, Long uploaderId);

    /**
     * 下载文件
     *
     * @param fileId 需要下载的文件ID
     * @return 包含文件内容的资源对象
     */
    Resource downloadFile(Long fileId);

    /**
     * 删除文件
     *
     * @param fileId 需要删除的文件ID
     * @param userId 进行操作的用户ID
     */
    void deleteFile(Long fileId, Long userId);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的文件ID列表
     * @param userId 进行操作的用户ID
     */
    void deleteFiles(List<Long> ids, Long userId);

    /**
     * 获取所有文件信息
     *
     * @param pageNum 页码
     * @param pageSize 每页显示的数量
     * @return 文件信息列表
     */
    List<FileInfo> getAllFiles(int pageNum, int pageSize);

    /**
     * 获取指定文件信息
     *
     * @param fileId 文件ID
     * @return 文件信息对象
     */
    FileInfo getFileInfo(Long fileId);

    /**
     * 根据文件名获取文件信息
     *
     * @param filename 文件名
     * @param pageNum 页码
     * @param pageSize 每页显示的数量
     * @return 文件信息列表
     */
    List<FileInfo> getFilesByFilename(String filename, int pageNum, int pageSize);

    /**
     * 根据上传者获取文件信息
     *
     * @param uploaderId 上传者ID
     * @param pageNum 页码
     * @param pageSize 每页显示的数量
     * @return 文件信息列表
     */
    List<FileInfo> getFilesByUploaderId(Long uploaderId, int pageNum, int pageSize);

    /**
     * 获取所有文件数量
     *
     * @return 文件数量
     */
    int getCountOfAllFiles();

    /**
     * 根据文件名获取文件数量
     *
     * @param filename 文件名
     * @return 文件数量
     */
    int getCountOfFilesByFilename(String filename);

    /**
     * 根据上传者获取文件数量
     *
     * @param uploaderId 上传者ID
     * @return 文件数量
     */
    int getCountOfFilesByUploaderId(Long uploaderId);
}
