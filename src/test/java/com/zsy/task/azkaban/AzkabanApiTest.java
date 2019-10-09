package com.zsy.task.azkaban;

import com.zsy.task.azkaban.api.AzkabanApi;
import com.zsy.task.azkaban.api.Impl.AzkabanApiImpl;
import com.zsy.task.azkaban.response.ExecuteFlowResponse;
import com.zsy.task.azkaban.response.FetchExecFlowResponse;
import org.junit.Test;
import java.io.IOException;
import java.net.URLEncoder;

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
        FetchExecFlowResponse fetchExecFlowResponse = azkabanApi.fetchExecFlow("1482");
        System.out.println("fetchExecFlowResponse-->"+fetchExecFlowResponse.getStatus());
        System.out.println(fetchExecFlowResponse.getStartTime());
        System.out.println(fetchExecFlowResponse.getEndTime());
        System.out.println(fetchExecFlowResponse.getFlowId());
        System.out.println(fetchExecFlowResponse.getFlow());
        System.out.println(fetchExecFlowResponse.getNestedId());
    }

    @Test
    public void executeFlow2() throws IOException {

        String argsJson = "{\"job_id\":108,\"start\":1469316037,\"end\":1569388000,\"school_ids\":[135,1182,1556,3813,3853,3854,3852,599,35,213,214,219,222,225,226,227,234,236,238,241,242,244,249,253,257,263,279],\"grade\":1,\"text_book_id\":null,\"module_id\":null,\"student_level\":[1,2],\"wrong_count\":30,\"wrong_student_count\":30,\"student_rate\":0.1}";
        String encodeArgsJson = URLEncoder.encode(argsJson, "UTF-8");
        AzkabanApi azkabanApi = new AzkabanApiImpl();
        ExecuteFlowResponse executeFlowResponse = azkabanApi.executeFlow("zsy-bd-testing", "spark_methods", "args", encodeArgsJson);
        System.out.println(executeFlowResponse.getStatus());
        System.out.println(executeFlowResponse.getError());
        System.out.println(executeFlowResponse.getExecid());
        System.out.println(executeFlowResponse.getFlow());
        System.out.println(executeFlowResponse.getMessage());

    }

}
