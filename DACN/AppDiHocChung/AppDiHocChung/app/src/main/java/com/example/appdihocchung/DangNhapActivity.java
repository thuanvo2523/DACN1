package com.example.appdihocchung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    private static final String USERNAME_PATTERN = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    TextInputLayout taiKhoan;
    TextInputLayout matKhau;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taiKhoan = findViewById(R.id.TaiKhoan);
        matKhau = findViewById(R.id.MatKhau);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean verifyUsername(String username) {
        if (username == null) return false;
        return username.matches(USERNAME_PATTERN);
    }

    public static boolean verifyPassword(String password) {
        if (password == null) return false;
        return password.matches(PASSWORD_PATTERN);
    }

    public void dangNhapTaiKhoan(String taiKhoanTam, String matKhauTam) {
        Call<APIResponse> call = apiInterface.dangNhapTaiKhoan(taiKhoanTam,matKhauTam);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse product = response.body();
                if( product.getStatus() == true && product.getMessage().equals("")){
                    if(response.isSuccessful()) {
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                        intent.putExtra("taiKhoan", taiKhoanTam);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(DangNhapActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(DangNhapActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void dangNhap(View view) {
        String taiKhoanTam = taiKhoan.getEditText().getText().toString();
        String matKhauTam = matKhau.getEditText().getText().toString();
        if(taiKhoanTam.equals("") || matKhauTam.equals("")){
            Toast.makeText(DangNhapActivity.this, "Vui lòng nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
        } else if (verifyUsername(taiKhoanTam) == false) {
            Toast.makeText(DangNhapActivity.this, "Tài khoản không đúng yêu cầu", Toast.LENGTH_SHORT).show();
        } else if (verifyPassword(matKhauTam) == false) {
            Toast.makeText(DangNhapActivity.this, "Mật khẩu không đúng yêu cầu", Toast.LENGTH_SHORT).show();
        } else{
            dangNhapTaiKhoan(taiKhoanTam,matKhauTam);
        }
    }

    public void quenMatKhau(View view) {
        Intent intent = new Intent(DangNhapActivity.this,QuenMatKhauActivity.class);
        startActivity(intent);
    }

    public void dangKy(View view) {
        Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
        startActivity(intent);
    }
}