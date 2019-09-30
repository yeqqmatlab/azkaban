package com.zsy.task.azkaban.api.Impl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zsy.task.azkaban.api.AzkabanApi;
import com.zsy.task.azkaban.config.AzkabanApiConfig;
import com.zsy.task.azkaban.response.ExecuteFlowResponse;
import com.zsy.task.azkaban.response.FetchExecFlowResponse;
import com.zsy.task.azkaban.response.LoginResponse;
import com.zsy.task.azkaban.response.ResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by yqq on 2019/9/26.
 */
public class AzkabanApiImpl implements AzkabanApi {

    private static String username;

    private static String password;

    private static String uri;

    private static String sessionId;

    public AzkabanApiImpl(){
        //AzkabanApiConfig azkabanApiConfig = new AzkabanApiConfig();
        Config load = ConfigFactory.load();
        this.uri = load.getString("azkaban.url");
        this.username = load.getString("azkaban.username");
        this.password = load.getString("azkaban.password");
    }

    private static Logger log = LoggerFactory.getLogger(AzkabanApiImpl.class);

    private static final String EXECUTE_FLOW = "{0}/executor?ajax=executeFlow&session.id={1}&project={2}&flow={3}";

    private static final String EXECUTE_FLOW_ARGS = "{0}/executor?ajax=executeFlow&session.id={1}&project={2}&flow={3}&flowOverride[{4}]={5}";

    private static final String FETCH_EXEC_FLOW = "{0}/executor?ajax=fetchexecflow&session.id={1}&execid={2}";

    @Override
    public ExecuteFlowResponse executeFlow(String projectName, String flowName) throws IOException {
        String sessionId = getSessionId();
        Request req = Request.Post(MessageFormat.format(EXECUTE_FLOW, uri, sessionId, projectName, flowName));
        return ResponseHandler.handle(req, ExecuteFlowResponse.class);
    }

    @Override
    public ExecuteFlowResponse executeFlow(String projectName, String flowName, String argName, String argValue) throws IOException {
        String sessionId = getSessionId();
        Request req = Request.Post(MessageFormat.format(EXECUTE_FLOW_ARGS, uri, sessionId, projectName, flowName,argName,argValue));
        return ResponseHandler.handle(req, ExecuteFlowResponse.class);
    }

    @Override
    public FetchExecFlowResponse fetchExecFlow(String execId) throws IOException{
        String sessionId = getSessionId();
        Request req = Request.Get(MessageFormat.format(FETCH_EXEC_FLOW, uri, sessionId, execId));
        return ResponseHandler.handle(req, FetchExecFlowResponse.class);
    }

    private static String getSessionId() {
        String sessionId = null;
        try {
            Response res = Request.Post(uri)
                    .bodyForm(Form.form()
                            .add("action", "login")
                            .add("username", username)
                            .add("password", password).build())
                    .execute();
            HttpEntity entity = res.returnResponse().getEntity();
            String content = EntityUtils.toString(entity).replace("session.id", "sessionId");
            LoginResponse response = ResponseHandler.handle(content, LoginResponse.class);
            if (StringUtils.isNotEmpty(response.getSessionId())) {
                sessionId = response.getSessionId();
            }else {
                throw new Exception("sessionId获取失败，未登录成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录失败");
        }
        return sessionId;
    }
}
