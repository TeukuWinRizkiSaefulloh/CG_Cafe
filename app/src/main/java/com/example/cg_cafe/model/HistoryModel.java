package com.example.cg_cafe.model;

import java.util.List;

public class HistoryModel {
    private int success;
    private String message;
    public List<DataHistory> data;

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

    public List<DataHistory> getData() {
        return data;
    }

    public void setData(List<DataHistory> data) {
        this.data = data;
    }
}


