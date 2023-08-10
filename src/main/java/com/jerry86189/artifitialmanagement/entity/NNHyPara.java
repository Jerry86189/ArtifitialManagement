package com.jerry86189.artifitialmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: NNHyPara
 * Description: TODO
 * date: 2023/06/13 10:11
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NNHyPara {
    private int hiddenSize;//32-512
    private int numLayers;//1-4
    private int numEpochs;//10-100
    private double learningRate;//0.00001-0.01
    private int batchSize;//16-128
}
