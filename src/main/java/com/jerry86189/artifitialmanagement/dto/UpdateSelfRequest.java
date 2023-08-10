package com.jerry86189.artifitialmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UpdateSelfRequest
 * Description: TODO
 * date: 2023/06/09 23:30
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSelfRequest {
    private Long id;
    private String verifyPassword;
    private String newUsername;
    private String newPassword;
}
