package com.snapdeal.lunchbox.bean;

import java.io.Serializable;

import com.snapdeal.lunchbox.helper.Constants;



public class ResponseBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            statusCode;

    private String            message;

    private T                 data;

    public ResponseBean(T data) {
        if (data != null) {
            this.statusCode = Constants.SUCCESS;
            this.message = Constants.OK;
            this.data = data;
        }
    }

    public ResponseBean(String code, String message, T data) {
        this.statusCode = code;
        this.message = message;
        this.data = data;
    }
    
    public ResponseBean(String code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    public ResponseBean() {
        this.statusCode = Constants.SUCCESS;
        this.message = Constants.OK;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
