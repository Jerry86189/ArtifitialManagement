package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: P
 * Description: TODO
 * date: 2023/06/13 15:36
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum P implements IEnum<Integer> {
    ONE(1),
    TWO(2);

    @EnumValue
    private final int value;

    P(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
