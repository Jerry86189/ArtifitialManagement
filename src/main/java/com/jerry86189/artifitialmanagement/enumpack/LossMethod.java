package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: LossMethod
 * Description: TODO
 * date: 2023/06/13 10:42
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum LossMethod implements IEnum<String> {
    BINARY_CROSS_ENTROPY("binary_crossentropy"),
    CATEGORICAL_CROSS_ENTROPY("categorical_crossentropy"),
    SPARSE_CATEGORICAL_CROSS_ENTROPY("sparse_categorical_crossentropy"),
    MEAN_SQUARED_ERROR("mean_squared_error"),
    MEAN_ABSOLUTE_ERROR("mean_absolute_error");

    @EnumValue
    private final String value;

    LossMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
