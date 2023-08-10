package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: Role
 * Description: TODO
 * date: 2023/06/09 23:18
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum Role implements IEnum<String> {
    ADMIN("ADMIN"),
    NORM("NORM");

    @EnumValue
    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
