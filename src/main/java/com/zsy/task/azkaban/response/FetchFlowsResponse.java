package com.zsy.task.azkaban.response;

import java.util.List;

public class FetchFlowsResponse extends BaseResponse {

    private String project;

    private String projectId;

    private List<Flow> flows;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

}
