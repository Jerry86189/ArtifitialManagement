package com.jerry86189.artifitialmanagement.dto;

import com.jerry86189.artifitialmanagement.entity.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: GetFilesResponse
 * Description: TODO
 * date: 2023/06/19 00:54
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFilesResponse {
    private List<FileInfo> fileInfos;
    private int currentPage;
    private int pageSize;
    private int totalCount;
    private int totalPages;
}
