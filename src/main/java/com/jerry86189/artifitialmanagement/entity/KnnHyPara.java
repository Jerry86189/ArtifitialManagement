package com.jerry86189.artifitialmanagement.entity;

import com.jerry86189.artifitialmanagement.enumpack.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: KnnHyPara
 * Description: TODO
 * date: 2023/06/13 10:09
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnnHyPara {
    private int nNeighbor;//1-...，奇数
    private Weight weight;
    private Algorithm algorithm;
    private int leafSize;//5-100，默认30
    private P p;
    private Metric metric;
    private NJob nJob;
}
