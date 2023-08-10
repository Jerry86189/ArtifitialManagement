//package com.jerry86189.artifitialmanagement.controller;
//
//import com.jerry86189.artifitialmanagement.entity.*;
//import com.jerry86189.artifitialmanagement.enumpack.*;
//import com.jerry86189.artifitialmanagement.service.AsyncService;
//import com.jerry86189.artifitialmanagement.service.OperateMsgService;
//import com.jerry86189.artifitialmanagement.service.impl.OperateMsgServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
///**
// * ClassName: TestController
// * Description: TODO
// * date: 2023/06/17 22:20
// *
// * @author Jerry
// * @version 1.0
// * @since JDK 1.8
// */
//@Controller
//public class TestController {
//
//    @Autowired
//    private OperateMsgService operateMsgService;
//
//    @Autowired
//    private AsyncService asyncService;
//
//    @GetMapping("/test")
//    public String test() {
//
//        OperateMsg operateMsg = new OperateMsg();
//        operateMsg.setFileId(1L);
//        operateMsg.setUserId(1L);
//        operateMsg.setMissingId(1L);
//        operateMsg.setOutlierId(1L);
//        operateMsg.setModelId(1L);
//        operateMsg.setAccuracy(0.89);
//        operateMsg.setPrecision(0.91);
//        operateMsg.setRecall(0.92);
//        operateMsg.setF1(0.93);
//
//        // 创建一个 OutlierMsg 对象
//        OutlierMsg outlierMsg = new OutlierMsg();
//        outlierMsg.setOutlierId(1L);
//        outlierMsg.setColsJson("[\"col1\", \"col2\"]");
//        outlierMsg.setDeleteMethod(OutlierDeleteMethod.MEAN_STD);
//        outlierMsg.setThreshold(1);
//        outlierMsg.setType(true);
//        outlierMsg.setReplaceMethod(OutlierReplaceMethod.MEAN);
//        outlierMsg.setDetectMethod(OutlierDetectMethod.Z_SCORE);
//
//        // 创建一个 MissingMsg 对象
//        MissingMsg missingMsg = new MissingMsg();
//        missingMsg.setMissingId(1L);
//        missingMsg.setColumnsJson("[\"col1\", \"col2\"]");
//        missingMsg.setType(true);
//        missingMsg.setMissingMethod(MissingMethod.MEAN);
//        missingMsg.getColumns();
//
//        // 创建一个 Model 对象
//        Model model = new Model();
//        model.setModelId(1L);
//        model.setXNamesJson("[\"x1\", \"x2\"]");
//        model.setYNamesJson("[\"y1\"]");
//
//        DeepCnnHyPara deepCnnHyPara = new DeepCnnHyPara();
//        deepCnnHyPara.setLossMethod(LossMethod.BINARY_CROSS_ENTROPY);
//        deepCnnHyPara.setLstmUnit(32);
//        deepCnnHyPara.setDropoutRate(0.2f);
//        deepCnnHyPara.setBatchSize(32);
//        deepCnnHyPara.setEpoch(5);
//        model.setDeepCnnHyPara(deepCnnHyPara);
//
//        KnnHyPara knnHyPara = new KnnHyPara();
//        knnHyPara.setNNeighbor(1);
//        knnHyPara.setWeight(Weight.UNIFORM);
//        knnHyPara.setAlgorithm(Algorithm.BALL_TREE);
//        knnHyPara.setLeafSize(30);
//        knnHyPara.setP(P.ONE);
//        knnHyPara.setMetric(Metric.MINKOWSKI);
//        knnHyPara.setNJob(NJob.ONE);
//        model.setKnnHyPara(knnHyPara);
//
//        NNHyPara nnHyPara = new NNHyPara();
//        nnHyPara.setHiddenSize(32);
//        nnHyPara.setNumLayers(1);
//        nnHyPara.setNumEpochs(10);
//        nnHyPara.setLearningRate(0.00001);
//        nnHyPara.setBatchSize(16);
//        model.setNnHyPara(nnHyPara);
//
//        // 这里的service应该是你的实际service对象，你可能需要注入或者直接创建它的实例
//        OperateMsgService service = new OperateMsgServiceImpl(/*...your params here...*/);
//        Long trainingId = operateMsgService.addOperateMsg(operateMsg, outlierMsg, model, missingMsg);
//
//        // 之后，你可以使用你的AsyncService来发送测试请求
////        AsyncService asyncService = new AsyncServiceImpl(client, objectMapper);
//        String taskId = asyncService.sendDataToPython(trainingId);
//        System.out.println("taskId: " + taskId);
//
//        return "test";
//    }
//}
