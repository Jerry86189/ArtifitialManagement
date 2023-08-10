package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: Weight
 * Description: TODO
 * date: 2023/06/13 15:23
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum Weight implements IEnum<String> {
    UNIFORM("uniform"),
    DISTANCE("distance");

    @EnumValue
    private final String value;

    Weight(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
