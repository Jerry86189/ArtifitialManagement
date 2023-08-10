package com.jerry86189.artifitialmanagement.dto;

import com.jerry86189.artifitialmanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: GetUsersResponse
 * Description: TODO
 * date: 2023/06/09 23:31
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {
    private List<User> users;
    private int currentPage;
    private int pageSize;
    private int totalCount;
    private int totalPages;
}
