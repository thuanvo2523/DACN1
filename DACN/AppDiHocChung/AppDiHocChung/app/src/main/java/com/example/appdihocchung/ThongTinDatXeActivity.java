package com.example.appdihocchung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinDatXeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_dat_xe);
    }

//    TextView txtBienSoXe;
//    TextView txtTenTaiXe;
//    TextView soTien;
//    TextView diaChiNhan;
//    TextView diaChiGiao;
//    Toolbar toolbar;
//    APIInterface apiInterface;
//    String idLoaiXe;
//    String taiKhoan;
//    String idTaiXe;
//    String idHang;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_thong_tin_giao_hang);
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar); //NO PROBLEM !!!!
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        RatingBar ratingBar = findViewById(R.id.ratingBar);
//
//        txtBienSoXe = findViewById(R.id.txtBienSoXe);
//        txtTenTaiXe = findViewById(R.id.txtTenTaiXe);
//        soTien = findViewById(R.id.soTien);
//        diaChiNhan = findViewById(R.id.diaChiNhan);
//        diaChiGiao = findViewById(R.id.diaChiGiao);
//
//        Intent intent = getIntent();
//        idLoaiXe = intent.getStringExtra("idLoaiXe");
//        taiKhoan = intent.getStringExtra("taiKhoan");
//
//        diaChiNhan.setText(intent.getStringExtra("noiLayHang"));
//        diaChiGiao.setText(intent.getStringExtra("noiGiaoHang"));
//        idHang = intent.getStringExtra("idHang");
//        setThongTinTaiXe();
//    }
//
//    //cái này chút tạo cái api tài xế mới xài đc
//    String maPhuongTien;
//    public void setThongTinTaiXe(){
//        Call<GetPhuongTienAPIResponse> call = apiInterface.getPhuongTienList();
//        call.enqueue(new Callback<GetPhuongTienAPIResponse>() {
//            @Override
//            public void onResponse(Call<GetPhuongTienAPIResponse> call, Response<GetPhuongTienAPIResponse> response) {
//                GetPhuongTienAPIResponse  responses = (GetPhuongTienAPIResponse) response.body();
//                for(int i = 0; i< responses.getData().size();i++){
//                    if(responses.getData().get(i).getIdLoaiXe().toString().equals(idLoaiXe)){
//                        txtBienSoXe.setText(responses.getData().get(i).getBienSo().toString());
//                        maPhuongTien = responses.getData().get(i).getId().toString();
//                        break;
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<GetPhuongTienAPIResponse> call, Throwable t) {
//            }
//        });
//
//        Call<GetTaiXeAPIResponse> call2 = apiInterface.getTaiXeList();
//        call2.enqueue(new Callback<GetTaiXeAPIResponse>() {
//            @Override
//            public void onResponse(Call<GetTaiXeAPIResponse> call2, Response<GetTaiXeAPIResponse> response) {
//                GetTaiXeAPIResponse  responses = (GetTaiXeAPIResponse) response.body();
//                for(int i = 0; i< responses.getData().size();i++){
//                    if(responses.getData().get(i).getIdXe().toString().equals(maPhuongTien)){
//                        txtTenTaiXe.setText(responses.getData().get(i).getTen().toString());
//                        idTaiXe = responses.getData().get(i).getId().toString();
//                        break;
//                    }
////                    else{
////                        Toast.makeText(ThongTinGiaoHangActivity.this, responses.getData().get(i).getIdXe().toString() + "/" + maPhuongTien + "/", Toast.LENGTH_SHORT).show();
////                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<GetTaiXeAPIResponse> call, Throwable t) {
//            }
//        });
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void datLai(View view) {
//        Intent intent = new Intent(ThongTinGiaoHangActivity.this,DatHangActivity.class);
//        intent.putExtra("taiKhoan",taiKhoan);
//        startActivity(intent);
//    }
//
//    public void quaTrangChiTiet(View view) {
//    }
//
//    public void baoCao(View view) {
//    }
//
//    //làm cái đơn đặt hàng nữa
//    public void datHang(View view) {
//        Call<APIResponse> call = apiInterface.insertThongTinDon(diaChiNhan.getText().toString(),diaChiGiao.getText().toString(),1,1,idHang,idTaiXe);
//        call.enqueue(new Callback<APIResponse>() {
//            @Override
//            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
//                APIResponse product = response.body();
//                if( product.getStatus() == true && product.getMessage() == ""){
//                    Toast.makeText(ThongTinGiaoHangActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(ThongTinGiaoHangActivity.this,DatHangActivity.class);
//                    intent.putExtra("taiKhoan",taiKhoan);
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(ThongTinGiaoHangActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<APIResponse> call, Throwable t) {
//                Toast.makeText(ThongTinGiaoHangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}