package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: ActMethod
 * Description: TODO
 * date: 2023/06/13 11:08
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum ActMethod implements IEnum<String> {
    RELU("relu"),
    SIGMOID("sigmoid"),
    TANH("tanh");

    @EnumValue
    private final String value;

    ActMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
