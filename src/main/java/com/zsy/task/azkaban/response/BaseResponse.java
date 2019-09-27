package com.zsy.task.azkaban.response;

import java.util.Objects;

public class BaseResponse {

    public static final String SUCCESS = "00";

    public static final String ERROR = "01";

    /**
     * 响应状态
     */
    private String status;

    /**
     * 错误类型(映射Azkaban)
     */
    private String error;

    /**
     * 详细信息
     */
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 更正信息
     */
    public void correction() {
        if (!ERROR.equals(this.status) && Objects.isNull(this.error)) {
            this.status = SUCCESS;
        } else {
            this.status = ERROR;
            if (Objects.isNull(this.error)) {
                this.error = this.message;
            } else if (Objects.isNull(this.message)) {
                this.message = this.error;
            }
        }
    }
}
