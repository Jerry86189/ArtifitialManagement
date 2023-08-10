package com.jerry86189.artifitialmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: DescribeResponse
 * Description: TODO
 * date: 2023/06/25 14:26
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescribeResponse {
    private String top20Rows;
    private String columnNames;
    private String info;
    private String description;
}
