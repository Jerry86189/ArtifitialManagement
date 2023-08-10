package com.jerry86189.artifitialmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry86189.artifitialmanagement.service.AsyncService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: AsyncServiceImpl
 * Description: TODO
 * date: 2023/06/10 21:47
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @Autowired
    public AsyncServiceImpl(OkHttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    /**
     * 发送训练任务
     *
     * @param trainingId 通过其他Service存储后返回的训练任务ID
     * @return 返回一个全局唯一的任务ID，用于后续跟踪任务状态
     */
    @Override
    @Transactional
    public String sendDataToPython(Long trainingId) {
        String taskId = UUID.randomUUID().toString();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("trainingId", String.valueOf(trainingId));
        parameters.put("taskId", taskId);

        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(parameters));
            Request request = new Request.Builder()
                    .url("http://localhost:5000/train")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return taskId;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取训练任务结果
     *
     * @param taskId 唯一的任务ID，由sendTrainingTask方法返回
     * @return 返回任务的处理结果，如果任务还在处理中，则返回表示"处理中"的消息
     */
    @Override
    @Scheduled(fixedRate = 5000)
    public String getResult(String taskId) {
        Request request = new Request.Builder()
                .url("http://localhost:5000/result/" + taskId)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取训练任务状态
     *
     * @param fileId 唯一的文件ID，由用户输入
     * @return 返回文件的所有主要关键信息top_20_rows、column_names、info、describe
     */
    @Override
    public String getFileDescribe(Long fileId) {
        Request request = new Request.Builder()
                .url("http://localhost:5000/describe/" + fileId)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}