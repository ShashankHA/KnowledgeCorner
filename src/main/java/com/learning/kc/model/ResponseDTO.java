package com.learning.kc.model;

public class ResponseDTO {

    private String code;
    private Object response;

    public ResponseDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
