package com.jerry86189.artifitialmanagement.entity;

import com.jerry86189.artifitialmanagement.enumpack.ActMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: DeepCnnLayer
 * Description: TODO
 * date: 2023/06/13 14:41
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeepCnnLayer {
    private int convFilter;//32-512
    private int kernelSize;//2-5
    private int poolSize;//2-3
    private ActMethod activation;
}
