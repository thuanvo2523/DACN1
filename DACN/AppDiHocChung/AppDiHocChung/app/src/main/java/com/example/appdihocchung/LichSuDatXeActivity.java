package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LichSuDatXeActivity extends AppCompatActivity {
    String taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_dat_xe);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");
    }

    public void quaTrangChu(View view) {
        Intent intent = new Intent(LichSuDatXeActivity.this,TrangChuActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangHoatDong(View view) {

    }

    public void quaTrangThanhToan(View view) {
        Intent intent = new Intent(LichSuDatXeActivity.this,ThanhToanActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangTaiKhoan(View view) {
        Intent intent = new Intent(LichSuDatXeActivity.this,TaiKhoanActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }
}