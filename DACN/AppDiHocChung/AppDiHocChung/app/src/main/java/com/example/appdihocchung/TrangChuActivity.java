package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdihocchung.Model.Lich;
import com.example.appdihocchung.Model.ListLich;

import java.util.ArrayList;

public class TrangChuActivity extends AppCompatActivity {
    String ngayThu[] = {"Thứ 2","Thứ 2","Thứ 4","Thứ 5","Thứ 6","Thứ 7","CN"};
    String diemHen[] = {"Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7","CN"};
    String thoiGianHen[] = {"Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7","CN"};
    String thongTinLienLac[] = {"Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7","CN"};
    ArrayList<Lich> myList;
    ListLich myAdapter;
    ListView lv;
    String taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");

        lv = findViewById(R.id.list_item);
        myList = new ArrayList<Lich>();
        for (int i = 0 ; i < ngayThu.length;i++){
            myList.add(new Lich(ngayThu[i],diemHen[i],thoiGianHen[i],thongTinLienLac[i] ));
        }
        myAdapter = new ListLich(TrangChuActivity.this,R.layout.layout_item,myList);
        lv.setAdapter(myAdapter);
    }


    public void trangDatXe(View view) {
        Intent intent = new Intent(TrangChuActivity.this,DatXeActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangChu(View view) {

    }

    public void quaTrangHoatDong(View view) {
        Intent intent = new Intent(TrangChuActivity.this,LichSuDatXeActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangThanhToan(View view) {
        Intent intent = new Intent(TrangChuActivity.this,ThanhToanActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangTaiKhoan(View view) {
        Intent intent = new Intent(TrangChuActivity.this,TaiKhoanActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }
}