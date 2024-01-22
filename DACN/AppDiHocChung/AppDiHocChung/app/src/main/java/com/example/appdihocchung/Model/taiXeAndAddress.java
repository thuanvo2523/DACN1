package com.example.appdihocchung.Model;

public class taiXeAndAddress {
    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getAddress() {
        return khoanCach;
    }

    public void setAddress(String address) {
        this.khoanCach = address;
    }

    public taiXeAndAddress(String taikhoan, String address) {
        this.taikhoan = taikhoan;
        this.khoanCach = address;
    }

    String taikhoan;

    String khoanCach;
}
