package com.jerry86189.artifitialmanagement.dto;

import com.jerry86189.artifitialmanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: LoginResponse
 * Description: TODO
 * date: 2023/06/09 23:29
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private User user;
    private String token;
}
