package com.example.appdihocchung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaiXe_DangKyActivity extends AppCompatActivity {
    String taiKhoan;
    TextInputLayout moTa;
    TextInputLayout thongTinXe;
    TextInputLayout bienSo;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_xe_dang_ky);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");

        moTa = findViewById(R.id.moTa);
        thongTinXe = findViewById(R.id.thongTinXe);
        bienSo = findViewById(R.id.bienSo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void dangKyTaiXe(View view) {
        String moTaTam = moTa.getEditText().getText().toString();
        String thongTinXeTam = thongTinXe.getEditText().getText().toString();
        String bienSoTam = bienSo.getEditText().getText().toString();
        if(moTaTam.equals("") || thongTinXeTam.equals("") || bienSoTam.equals("")){
            Toast.makeText(TaiXe_DangKyActivity.this, "Vui lòng nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
        }else{
            Call<APIResponse> call = apiInterface.insertTaiXe(taiKhoan,moTaTam,thongTinXeTam,bienSoTam);
            call.enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    APIResponse product = response.body();
                    if( product.getStatus() == true && product.getMessage().equals("")){
                        Intent intent = new Intent(TaiXe_DangKyActivity.this,TaiXe_DangKy_2Activity.class);
                        intent.putExtra("taiKhoan",taiKhoan);
                        startActivity(intent);
                    } else if (product.getStatus() == false && product.getMessage() == "daTonTai") {
                        Intent intent = new Intent(TaiXe_DangKyActivity.this,TaiXe_DangKy_2Activity.class);
                        intent.putExtra("taiKhoan",taiKhoan);
                        startActivity(intent);
                    } else {
                        Toast.makeText(TaiXe_DangKyActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    Toast.makeText(TaiXe_DangKyActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}