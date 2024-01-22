package com.example.lib.APIResponse;

import java.io.Serializable;
import java.util.ArrayList;

public class GetTaiKhoanAPIResponse implements Serializable {
    public boolean status;
    public String message;
    public TaiKhoan oneData;
    //public ArrayList<GetTaiXeAPIResponseItem> data;

    public TaiKhoan getOneData() {
        return oneData;
    }

    public void setOneData(TaiKhoan oneData) {
        this.oneData = oneData;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
