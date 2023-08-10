package com.jerry86189.artifitialmanagement.service;

/**
 * ClassName: AsyncService
 * Description: TODO
 * date: 2023/06/17 10:01
 *
 * @author Jerry
 * @version 1.0
 * @since JDK 1.8
 */
public interface AsyncService {

    /**
     * 发送训练任务
     *
     * @param trainingId 通过其他Service存储后返回的训练任务ID
     * @return 返回一个全局唯一的任务ID，用于后续跟踪任务状态
     */
    String sendDataToPython(Long trainingId);

    /**
     * 获取训练任务结果
     *
     * @param taskId 唯一的任务ID，由sendDataToPython方法返回
     * @return 返回任务的处理结果，如果任务还在处理中，则返回表示"处理中"的消息
     */
    String getResult(String taskId);

    /**
     * 获取训练任务状态
     *
     * @param fileId 唯一的文件ID，由用户输入
     * @return 返回文件的所有主要关键信息top_20_rows、column_names、info、describe
     */
    String getFileDescribe(Long fileId);
}
