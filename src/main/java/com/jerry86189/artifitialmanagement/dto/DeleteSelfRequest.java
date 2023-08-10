package com.jerry86189.artifitialmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: DeleteSelfRequest
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
public class DeleteSelfRequest {
    private Long id;
    private String verifyPassword;
}
