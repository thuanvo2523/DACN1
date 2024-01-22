package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appdihocchung.Model.Lich;

public class ThanhToanActivity extends AppCompatActivity {
    String taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");
    }

    public void quaTrangChu(View view) {
        Intent intent = new Intent(ThanhToanActivity.this,TrangChuActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangHoatDong(View view) {
        Intent intent = new Intent(ThanhToanActivity.this, LichSuDatXeActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangThanhToan(View view) {

    }

    public void quaTrangTaiKhoan(View view) {
        Intent intent = new Intent(ThanhToanActivity.this,TaiKhoanActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }
}