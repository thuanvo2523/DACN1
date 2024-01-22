package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void trangDangNhap(View view) {
        Intent intent = new Intent(MainActivity.this,DangNhapActivity.class);
        startActivity(intent);
    }

    public void trangDangKy(View view) {
        Intent intent = new Intent(MainActivity.this,DangKyActivity.class);
        startActivity(intent);
    }
}