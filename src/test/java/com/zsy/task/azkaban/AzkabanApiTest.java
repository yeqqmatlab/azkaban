package com.zsy.task.azkaban;

import com.zsy.task.azkaban.api.AzkabanApi;
import com.zsy.task.azkaban.api.Impl.AzkabanApiImpl;
import com.zsy.task.azkaban.response.ExecuteFlowResponse;
import com.zsy.task.azkaban.response.FetchExecFlowResponse;
import org.junit.Test;
import java.io.IOException;

/**
 * Created by yqq on 2019/9/26.
 */

public class AzkabanApiTest {

    @Test
    public void executeFlow() throws IOException {

        AzkabanApi azkabanApi = new AzkabanApiImpl();
        ExecuteFlowResponse executeFlowResponse = azkabanApi.executeFlow("test_args", "basic", "args", "helloooooo");
        System.out.println(executeFlowResponse.getStatus());
        System.out.println(executeFlowResponse.getError());
        System.out.println(executeFlowResponse.getExecid());
        System.out.println(executeFlowResponse.getFlow());
        System.out.println(executeFlowResponse.getMessage());

    }

    @Test
    public void fetchExecFlow() throws IOException {

        AzkabanApi azkabanApi = new AzkabanApiImpl();

        FetchExecFlowResponse fetchExecFlowResponse = azkabanApi.fetchExecFlow("1477");

        System.out.println("fetchExecFlowResponse-->"+fetchExecFlowResponse.getStatus());

        System.out.println(fetchExecFlowResponse.getStartTime());

        System.out.println(fetchExecFlowResponse.getEndTime());

        System.out.println(fetchExecFlowResponse.getFlowId());

        System.out.println(fetchExecFlowResponse.getFlow());

    }
}
