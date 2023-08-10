package com.jerry86189.artifitialmanagement.service;

import com.jerry86189.artifitialmanagement.dto.OperateResponse;
import com.jerry86189.artifitialmanagement.entity.*;

import java.util.List;

/**
 * ClassName: OperateMsgService
 * Description: TODO
 * date: 2023/06/15 08:55
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public interface OperateMsgService {
    /**
     * 添加操作消息.
     *
     * @param operateMsg 操作消息对象
     * @param outlierMsg 异常消息对象
     * @param model 模型对象
     * @param missingMsg 缺失消息对象
     */
    Long addOperateMsg(OperateMsg operateMsg, OutlierMsg outlierMsg, Model model, MissingMsg missingMsg);

    /**
     * 删除指定ID的操作消息.
     *
     * @param operateMsgId 操作消息ID
     */
    void deleteOperateMsg(int operateMsgId);

    /**
     * 批量删除操作消息.
     *
     * @param operateMsgIds 操作消息ID列表
     */
    void deleteOperateMsgs(List<Integer> operateMsgIds);

    /**
     * 分页获取所有操作消息.
     *
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 返回操作消息列表
     */
    List<OperateResponse> getAllOperateMsgs(int pageNum, int pageSize);

    /**
     * 根据用户ID分页获取操作消息.
     *
     * @param userId 用户ID
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 返回操作消息列表
     */
    List<OperateResponse> getOperateMsgsByUserId(int userId, int pageNum, int pageSize);

    /**
     * 根据操作消息ID获取操作消息.
     *
     * @param operateMsgId 操作消息ID
     * @return 返回操作消息对象
     */
    OperateResponse getOperateMsgById(int operateMsgId);

    /**
     * 获取所有操作消息的数量。
     *
     * @return 返回数据库中所有操作消息的数量。
     */
    int getCountOfAllOperateMsg();

    /**
     * 根据用户ID获取操作消息的数量。
     *
     * @param userId 用户ID
     * @return 返回数据库中与给定用户ID关联的操作消息的数量。
     */
    int getCountOfOperateMsgByUserId(int userId);

}
