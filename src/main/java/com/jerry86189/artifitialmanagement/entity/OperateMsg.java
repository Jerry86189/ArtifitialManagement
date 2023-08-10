package com.jerry86189.artifitialmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: MissingMsg
 * Description: TODO
 * date: 2023/06/11 18:08
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@TableName("operate_msg")
public class OperateMsg implements Serializable {
    @TableId(value = "operate_id", type = IdType.AUTO)
    private Long operateId;

    @TableField("file_id")
    private Long fileId;

    @TableField("user_id")
    private Long userId;

    @TableField("missing_id")
    private Long missingId;

    @TableField("outlier_id")
    private Long outlierId;

    @TableField("model_id")
    private Long modelId;

    @TableField("accuracy")
    private Double accuracy;

    @TableField("pre")
    private Double precision;

    @TableField("recall")
    private Double recall;

    @TableField("f1")
    private Double f1;
}
