package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.GetTaiKhoanAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaiXe_TrangChuActivity extends AppCompatActivity {
    Button activity_button;
    TextView trangThai;
    //Toolbar toolbar = findViewById(R.id.toolbar);


    String taiKhoan;
    APIInterface apiInterface;
    ImageView IVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_xe_trang_chu);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");

        IVPreviewImage = findViewById(R.id.avatarImageView);

        //setSupportActionBar(toolbar);

        activity_button = findViewById(R.id.activity_button);
        trangThai = findViewById(R.id.TrangThai);
        activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trangThai.getText().equals("Không hoạt động")){
                    trangThai.setText("Đang hoạt động");
                }
                else{
                    trangThai.setText("Không hoạt động");
                }
            }
        });

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
            }
            @Override
            public void onFailure(Call<GetTaiKhoanAPIResponse> call, Throwable t) {

            }
        });
    }

}