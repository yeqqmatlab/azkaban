package com.zsy.task.azkaban.api;

import com.zsy.task.azkaban.response.ExecuteFlowResponse;
import com.zsy.task.azkaban.response.FetchExecFlowResponse;
import java.io.IOException;

/**
 * Created by yqq on 2019/9/26.
 */
public interface AzkabanApi {

    /**
     * 执行flow
     *
     * @param projectName 项目名称
     * @param flowName    flow名称
     * @return ExecuteFlowResponse
     */
    ExecuteFlowResponse executeFlow(String projectName, String flowName) throws IOException;

    /**
     * 执行有参数 flow
     *
     * @param projectName
     * @param flowName
     * @param argName
     * @param argValue
     * @return
     */
    ExecuteFlowResponse executeFlow(String projectName, String flowName, String argName,String argValue) throws IOException;

    /**
     * 查询执行Flow信息
     *
     * @param execId 执行ID
     * @return FetchExecFlowResponse
     */
    FetchExecFlowResponse fetchExecFlow(String execId) throws IOException;


}
