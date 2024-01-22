package com.example.lib.APIResponse;

import com.example.lib.ApiGoogle.Model.Southwest;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ToaDoTaiXe implements Serializable {
    private boolean status;
    private String message;
    @SerializedName("data")
    private List<TaiKhoan> data;

    public List<TaiKhoan> getData() {
        return data;
    }

    public void setData(List<TaiKhoan> data) {
        this.data = data;
    }
    // Getter Methods

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Setter Methods

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
