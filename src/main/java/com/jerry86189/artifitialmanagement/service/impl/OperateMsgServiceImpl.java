package com.jerry86189.artifitialmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jerry86189.artifitialmanagement.dto.OperateResponse;
import com.jerry86189.artifitialmanagement.entity.*;
import com.jerry86189.artifitialmanagement.exceptions.DeleteFailed;
import com.jerry86189.artifitialmanagement.exceptions.InsertFailed;
import com.jerry86189.artifitialmanagement.exceptions.NotFound;
import com.jerry86189.artifitialmanagement.mapper.MissingMsgMapper;
import com.jerry86189.artifitialmanagement.mapper.ModelMapper;
import com.jerry86189.artifitialmanagement.mapper.OperateMsgMapper;
import com.jerry86189.artifitialmanagement.mapper.OutlierMsgMapper;
import com.jerry86189.artifitialmanagement.service.OperateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OperateMsgServiceImpl
 * Description: TODO
 * date: 2023/06/15 10:54
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class OperateMsgServiceImpl implements OperateMsgService {

    @Autowired
    private OperateMsgMapper operateMsgMapper;

    @Autowired
    private OutlierMsgMapper outlierMsgMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MissingMsgMapper missingMsgMapper;

    /**
     * 添加操作消息.
     *
     * @param operateMsg 操作消息对象
     * @param outlierMsg 异常消息对象
     * @param model 模型对象
     * @param missingMsg 缺失消息对象
     */
    @Override
    @Transactional
    public Long addOperateMsg(OperateMsg operateMsg, OutlierMsg outlierMsg, Model model, MissingMsg missingMsg) {
        System.out.println(operateMsg.toString());
        System.out.println(outlierMsg.toString());
        System.out.println(model.toString());
        System.out.println(missingMsg.toString());

        System.out.println(operateMsgMapper);
        System.out.println(outlierMsgMapper);
        System.out.println(modelMapper);
        System.out.println(missingMsgMapper);

        missingMsg.setColumns(missingMsg.getColumns());
        int insertMissingMsg = missingMsgMapper.insert(missingMsg);
        if (insertMissingMsg != 1) {
            throw new InsertFailed("Insert missingMsg failed");
        }

        outlierMsg.setCols(outlierMsg.getCols());
        int insertOutlierMsg = outlierMsgMapper.insert(outlierMsg);
        if (insertOutlierMsg!= 1) {
            throw new InsertFailed("Insert outlierMsg failed");
        }

        model.setXNames(model.getXNames());
        model.setYNames(model.getYNames());
        model.setDeepCnnHyPara(model.getDeepCnnHyPara());
        model.setKnnHyPara(model.getKnnHyPara());
        model.setNnHyPara(model.getNnHyPara());
        int insertModel = modelMapper.insert(model);
        if (insertModel!= 1) {
            throw new InsertFailed("Insert model failed");
        }

        operateMsg.setMissingId(missingMsg.getMissingId());
        operateMsg.setOutlierId(outlierMsg.getOutlierId());
        operateMsg.setModelId(model.getModelId());
        int insertOperateMsg = operateMsgMapper.insert(operateMsg);
        if (insertOperateMsg!= 1) {
            throw new InsertFailed("Insert operateMsg failed");
        }

        return operateMsg.getOperateId();
    }

    /**
     * 删除指定ID的操作消息.
     *
     * @param operateMsgId 操作消息ID
     */
    @Override
    @Transactional
    public void deleteOperateMsg(int operateMsgId) {
        int delete = operateMsgMapper.deleteById(operateMsgId);
        if (delete!= 1) {
            throw new DeleteFailed("Delete operateMsg failed, ID: " + operateMsgId);
        }
    }

    /**
     * 批量删除操作消息.
     *
     * @param operateMsgIds 操作消息ID列表
     */
    @Override
    public void deleteOperateMsgs(List<Integer> operateMsgIds) {
        for (Integer operateMsgId : operateMsgIds) {
            operateMsgMapper.deleteById(operateMsgId);
        }
    }

    /**
     * 分页获取所有操作消息.
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 返回操作消息列表
     */
    @Override
    public List<OperateResponse> getAllOperateMsgs(int pageNum, int pageSize) {
        Page<OperateMsg> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OperateMsg> queryWrapper = new QueryWrapper<OperateMsg>().orderByDesc("operate_id");
        IPage<OperateMsg> operateMsgPage = operateMsgMapper.selectPage(page, queryWrapper);

        if (operateMsgPage == null || operateMsgPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (operateMsgPage.getRecords().isEmpty()) {
            throw new NotFound("No operateMsg found");
        }

        ArrayList<OperateResponse> operateResponses = new ArrayList<>();
        for (OperateMsg operateMsg : operateMsgPage.getRecords()) {
            OutlierMsg outlierMsg = outlierMsgMapper.selectById(operateMsg.getOutlierId());
            Model model = modelMapper.selectById(operateMsg.getModelId());
            MissingMsg missingMsg = missingMsgMapper.selectById(operateMsg.getMissingId());

            if (outlierMsg == null || model == null || missingMsg == null) {
                throw new NotFound("OutlierMsg or Model or MissingMsg not found, ID: " + operateMsg.getOperateId());
            }

            model.getXNames();
            model.getYNames();
            model.getDeepCnnHyPara();
            model.getKnnHyPara();
            model.getNnHyPara();

            missingMsg.getColumns();

            OperateResponse operateResponse = new OperateResponse();
            operateResponse.setOperateMsg(operateMsg);
            operateResponse.setOutlierMsg(outlierMsg);
            operateResponse.setModel(model);
            operateResponse.setMissingMsg(missingMsg);
        }
        return operateResponses;
    }

    /**
     * 根据用户ID分页获取操作消息.
     *
     * @param userId   用户ID
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @return 返回操作消息列表
     */
    @Override
    public List<OperateResponse> getOperateMsgsByUserId(int userId, int pageNum, int pageSize) {
        Page<OperateMsg> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OperateMsg> queryWrapper = new QueryWrapper<OperateMsg>().eq("user_id", userId).orderByDesc("operate_id");
        IPage<OperateMsg> operateMsgPage = operateMsgMapper.selectPage(page, queryWrapper);

        if (operateMsgPage == null || operateMsgPage.getRecords() == null) {
            throw new NotFound("Query page: " + pageNum + " failed");
        }
        if (operateMsgPage.getRecords().isEmpty()) {
            throw new NotFound("No operateMsg found");
        }

        ArrayList<OperateResponse> operateResponses = new ArrayList<>();
        for (OperateMsg operateMsg : operateMsgPage.getRecords()) {
            OutlierMsg outlierMsg = outlierMsgMapper.selectById(operateMsg.getOutlierId());
            Model model = modelMapper.selectById(operateMsg.getModelId());
            MissingMsg missingMsg = missingMsgMapper.selectById(operateMsg.getMissingId());

            if (outlierMsg == null || model == null || missingMsg == null) {
                throw new NotFound("OutlierMsg or Model or MissingMsg not found, ID: " + operateMsg.getOperateId());
            }

            model.getXNames();
            model.getYNames();
            model.getDeepCnnHyPara();
            model.getKnnHyPara();
            model.getNnHyPara();

            missingMsg.getColumns();

            OperateResponse operateResponse = new OperateResponse();
            operateResponse.setOperateMsg(operateMsg);
            operateResponse.setOutlierMsg(outlierMsg);
            operateResponse.setModel(model);
        }
        return operateResponses;
    }

    /**
     * 根据操作消息ID获取操作消息.
     *
     * @param operateMsgId 操作消息ID
     * @return 返回操作消息对象
     */
    @Override
    public OperateResponse getOperateMsgById(int operateMsgId) {
        OperateMsg operateMsg = operateMsgMapper.selectById(operateMsgId);
        OutlierMsg outlierMsg = outlierMsgMapper.selectById(operateMsg.getOutlierId());
        Model model = modelMapper.selectById(operateMsg.getModelId());
        MissingMsg missingMsg = missingMsgMapper.selectById(operateMsg.getMissingId());

        if (outlierMsg == null || model == null || missingMsg == null) {
            throw new NotFound("OutlierMsg or Model or MissingMsg not found, ID: " + operateMsgId);
        }

        model.getXNames();
        model.getYNames();
        model.getDeepCnnHyPara();
        model.getKnnHyPara();
        model.getNnHyPara();

        missingMsg.getColumns();

        OperateResponse operateResponse = new OperateResponse();
        operateResponse.setOperateMsg(operateMsg);
        operateResponse.setOutlierMsg(outlierMsg);
        return operateResponse;
    }

    /**
     * 获取所有操作消息的数量。
     *
     * @return 返回数据库中所有操作消息的数量。
     */
    @Override
    public int getCountOfAllOperateMsg() {
        QueryWrapper<OperateMsg> queryWrapper = new QueryWrapper<OperateMsg>().orderByDesc("operate_id");
        return operateMsgMapper.selectCount(queryWrapper);
    }

    /**
     * 根据用户ID获取操作消息的数量。
     *
     * @param userId 用户ID
     * @return 返回数据库中与给定用户ID关联的操作消息的数量。
     */
    @Override
    public int getCountOfOperateMsgByUserId(int userId) {
        QueryWrapper<OperateMsg> queryWrapper = new QueryWrapper<OperateMsg>().eq("user_id", userId).orderByDesc("operate_id");
        return operateMsgMapper.selectCount(queryWrapper);
    }
}
