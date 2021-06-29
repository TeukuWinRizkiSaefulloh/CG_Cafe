package com.example.cg_cafe.model;

import java.util.List;

public class PemesananModel {
    private int success;
    private String message;
    private List<DataPemesanan> data;

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

    public List<DataPemesanan> getData() {
        return data;
    }

    public void setData(List<DataPemesanan> data) {
        this.data = data;
    }
}
