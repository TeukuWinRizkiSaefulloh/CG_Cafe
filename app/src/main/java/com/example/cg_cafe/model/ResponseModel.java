package com.example.cg_cafe.model;

import java.util.List;

public class ResponseModel {
    private int success;
    private String message;
    private List<DataKasir> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataKasir> getData() {
        return data;
    }

    public void setData(List<DataKasir> data) {
        this.data = data;
    }
}
