package com.jerry86189.artifitialmanagement.controller;

import com.jerry86189.artifitialmanagement.dto.GetFilesResponse;
import com.jerry86189.artifitialmanagement.entity.FileInfo;
import com.jerry86189.artifitialmanagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: FileController
 * Description: TODO
 * date: 2023/06/11 00:35
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/norm/upload")
    public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file, @RequestParam("uploaderId") Long uploaderId) {
        fileService.uploadFile(file, uploaderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/norm/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") Long fileId) {
        Resource resource = fileService.downloadFile(fileId);
        String contentType = null;
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/both/delete")
    public ResponseEntity<Void> delete(@RequestParam("fileIds") List<Long> fileIds, @RequestParam("userId") Long userId) {
        fileService.deleteFiles(fileIds, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/both/get_all_files")
    public ResponseEntity<GetFilesResponse> getAllFiles(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<FileInfo> files = fileService.getAllFiles(pageNum, pageSize);
        int totalCount = fileService.getCountOfAllFiles();

        GetFilesResponse getFilesResponse = new GetFilesResponse();
        getFilesResponse.setFileInfos(files);
        getFilesResponse.setCurrentPage(pageNum);
        getFilesResponse.setPageSize(pageSize);

        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize == 0) {
            totalPages++;
        }
        getFilesResponse.setTotalCount(totalPages);
        return ResponseEntity.ok(getFilesResponse);
    }

    @GetMapping("/both/get_file_by_id/{fileId}")
    public ResponseEntity<FileInfo> getFileById(@PathVariable("fileId") Long fileId) {
        fileService.getFileInfo(fileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/both/get_file_by_name/{fileName}")
    public ResponseEntity<GetFilesResponse> getFileByName(@PathVariable("fileName") String fileName, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<FileInfo> files = fileService.getFilesByFilename(fileName, pageNum, pageSize);
        int totalCount = fileService.getCountOfFilesByFilename(fileName);

        GetFilesResponse getFilesResponse = new GetFilesResponse();
        getFilesResponse.setFileInfos(files);
        getFilesResponse.setCurrentPage(pageNum);
        getFilesResponse.setPageSize(pageSize);
        getFilesResponse.setTotalPages(totalCount);

        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize == 0) {
            totalPages++;
        }
        getFilesResponse.setTotalCount(totalPages);
        return ResponseEntity.ok(getFilesResponse);
    }

    @GetMapping("/norm/get_file_by_uploader_id/{uploaderId}")
    public ResponseEntity<GetFilesResponse> getFileByUploaderId(@PathVariable("uploaderId") Long uploaderId, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        List<FileInfo> files = fileService.getFilesByUploaderId(uploaderId, pageNum, pageSize);
        int totalCount = fileService.getCountOfFilesByUploaderId(uploaderId);

        GetFilesResponse getFilesResponse = new GetFilesResponse();
        getFilesResponse.setFileInfos(files);
        getFilesResponse.setCurrentPage(pageNum);
        getFilesResponse.setPageSize(pageSize);
        getFilesResponse.setTotalPages(totalCount);

        int totalPages = totalCount / pageSize;
        if (totalCount % pageSize == 0) {
            totalPages++;
        }
        getFilesResponse.setTotalCount(totalPages);
        return ResponseEntity.ok(getFilesResponse);
    }
}
