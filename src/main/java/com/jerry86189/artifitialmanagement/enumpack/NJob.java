package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: NJob
 * Description: TODO
 * date: 2023/06/13 16:30
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum NJob implements IEnum<Integer> {
    ONE(1),
    NEGATIVE_ONE(-1);

    @EnumValue
    private final int value;

    NJob(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
