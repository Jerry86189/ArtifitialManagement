package com.jerry86189.artifitialmanagement.entity;

import com.jerry86189.artifitialmanagement.enumpack.LossMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: DeepCnnHyPara
 * Description: TODO
 * date: 2023/06/13 10:08
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeepCnnHyPara {
    private LossMethod lossMethod;
    private int lstmUnit;//32-256
    private float dropoutRate;//0.2-0.5
    private int batchSize;//32-256
    private int epoch;//5-50
    List<DeepCnnLayer> deepCnnLayers;
}
