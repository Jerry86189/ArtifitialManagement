package com.jerry86189.artifitialmanagement.dto;

import com.jerry86189.artifitialmanagement.entity.MissingMsg;
import com.jerry86189.artifitialmanagement.entity.Model;
import com.jerry86189.artifitialmanagement.entity.OperateMsg;
import com.jerry86189.artifitialmanagement.entity.OutlierMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: OperateResponse
 * Description: TODO
 * date: 2023/06/15 19:35
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateResponse {
    private OperateMsg operateMsg;
    private OutlierMsg outlierMsg;
    private Model model;
    private MissingMsg missingMsg;
}
