package com.jerry86189.artifitialmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.jerry86189.artifitialmanagement.enumpack.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: User
 * Description: TODO
 * date: 2023/06/09 23:16
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @EnumValue
    @TableField("role")
    private Role role;
}

