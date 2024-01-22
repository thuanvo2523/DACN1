package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;
import com.example.lib.APIResponse.GetTaiKhoanAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaiKhoanActivity extends AppCompatActivity {
    TextView taiKhoanTextView;
    TextView ten;
    TextView soDienThoai;
    TextView diaChi;
    String taiKhoan;
    APIInterface apiInterface;
    ImageView IVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");

        IVPreviewImage = findViewById(R.id.Avatar);
        taiKhoanTextView = findViewById(R.id.TaiKhoan);
        ten = findViewById(R.id.Ten);
        soDienThoai = findViewById(R.id.Sdt);
        diaChi = findViewById(R.id.DiaChi);


        refreshThongTin();
    }

    public void refreshThongTin(){
        Call<GetTaiKhoanAPIResponse> call = apiInterface.getTaiKhoan(taiKhoan);
        call.enqueue(new Callback<GetTaiKhoanAPIResponse>() {
            @Override
            public void onResponse(Call<GetTaiKhoanAPIResponse> call, Response<GetTaiKhoanAPIResponse> response) {
                GetTaiKhoanAPIResponse  responses = (GetTaiKhoanAPIResponse) response.body();
                if(responses.getOneData().getAvatar() != null){
                    byte[] anhReceive = Base64.decode(responses.getOneData().getAvatar(),Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(anhReceive,0,anhReceive.length);
                    IVPreviewImage.setImageBitmap(bitmap);
                }
                taiKhoanTextView.setText(taiKhoan);
                ten.setText("Tên: " + responses.getOneData().getTen());
                soDienThoai.setText("SDT: " + responses.getOneData().getSoDienThoai());
                diaChi.setText("Địa chỉ: " + responses.getOneData().getDiaChi());
            }
            @Override
            public void onFailure(Call<GetTaiKhoanAPIResponse> call, Throwable t) {

            }
        });
    }

    public void quaTrangChu(View view) {
        Intent intent = new Intent(TaiKhoanActivity.this,TrangChuActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangHoatDong(View view) {
        Intent intent = new Intent(TaiKhoanActivity.this,LichSuDatXeActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangThanhToan(View view) {
        Intent intent = new Intent(TaiKhoanActivity.this,ThanhToanActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void quaTrangTaiKhoan(View view) {

    }

    public void doiAvatar(View view) {
        Intent intent = new Intent(TaiKhoanActivity.this,SetAvatarActivity.class);
        intent.putExtra("taiKhoan",taiKhoan);
        startActivity(intent);
    }

    public void dangKyTaiXe(View view) {
        Call<APIResponse> call = apiInterface.ktTonTai(taiKhoan);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse product = response.body();
                if( product.getStatus() == false && product.getMessage().equals("")){
                    Intent intent = new Intent(TaiKhoanActivity.this,TaiXe_DangKyActivity.class);
                    intent.putExtra("taiKhoan",taiKhoan);
                    startActivity(intent);
                }else if( product.getStatus() == true && product.getMessage().equals("chuaCoHinh")){
                    Intent intent = new Intent(TaiKhoanActivity.this,TaiXe_DangKy_2Activity.class);
                    intent.putExtra("taiKhoan",taiKhoan);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(TaiKhoanActivity.this,TaiXe_TrangChuActivity.class);
                    intent.putExtra("taiKhoan",taiKhoan);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(TaiKhoanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}