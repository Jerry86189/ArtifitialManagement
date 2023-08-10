package com.jerry86189.artifitialmanagement.enumpack;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ClassName: Metric
 * Description: TODO
 * date: 2023/06/13 15:34
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public enum Metric implements IEnum<String> {
    MINKOWSKI("minkowski"),
    EUCLIDEAN("euclidean"),
    MANHATTAN("manhattan"),
    CHEBYSHEV("chebyshev"),
    HAVERSINE("haversine"),
    HAMMING("hamming"),
    JACCARD("jaccard"),
    COSINE("cosine");

    @EnumValue
    private final String value;

    Metric(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
