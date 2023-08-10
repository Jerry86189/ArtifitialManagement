package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: Algorithm
 * Description: TODO
 * date: 2023/06/13 15:29
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum Algorithm implements IEnum<String> {
    BALL_TREE("ball_tree"),
    KD_TREE("kd_tree"),
    BRUTE("brute"),
    AUTO("auto");

    @EnumValue
    private final String value;

    Algorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
