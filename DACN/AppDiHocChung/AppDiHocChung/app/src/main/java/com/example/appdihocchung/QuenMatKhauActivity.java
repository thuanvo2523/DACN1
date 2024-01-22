package com.example.appdihocchung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;
import com.example.lib.APIResponse.GetTaiKhoanAPIResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMatKhauActivity extends AppCompatActivity {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    TextInputLayout taiKhoan;
    TextInputLayout otp;
    TextInputLayout matKhauMoi;
    TextInputLayout xacNhanMK;
    Button btn;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taiKhoan = findViewById(R.id.TaiKhoan);
        otp = findViewById(R.id.OTP);
        matKhauMoi = findViewById(R.id.MatKhauMoi);
        xacNhanMK = findViewById(R.id.XacNhanLaiMK);
        btn = findViewById(R.id.btnGuiMaOTP);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean verifyPassword(String password) {
        if (password == null) return false;
        return password.matches(PASSWORD_PATTERN);
    }

    boolean ktTonTai = false;
    public boolean ktTaiKhoanCoTonTai(String tk){
        Call<APIResponse> call = apiInterface.forgotPassword(tk);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse product = response.body();
                if( product.getStatus() == true && product.getMessage().equals("")){
                    ktTonTai = true;
                }
                else if(product.getStatus() == true && product.getMessage().equals("saiTaiKhoan")){
                    Toast.makeText(QuenMatKhauActivity.this, "Sai tài khoản", Toast.LENGTH_SHORT).show();
                    ktTonTai = false;
                }
                else{
                    Toast.makeText(QuenMatKhauActivity.this, "Không thể gửi mã OTP", Toast.LENGTH_SHORT).show();
                    ktTonTai = false;
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(QuenMatKhauActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return ktTonTai;
    }

    public void doiMatKhau(String tk, String otp, String mk){
        Call<APIResponse> call = apiInterface.verifycode(tk,otp,mk);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse product = response.body();
                if( product.getStatus() == true && product.getMessage().equals("")){
                    Toast.makeText(QuenMatKhauActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuenMatKhauActivity.this,DangNhapActivity.class);
                    startActivity(intent);
                }
//                else{
//                    Toast.makeText(QuenMatKhauActivity.this, "OTP không hợp lệ", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(QuenMatKhauActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void guiMaOTP(View view) {
        String taiKhoanTam = taiKhoan.getEditText().getText().toString();
        String otpTam = otp.getEditText().getText().toString();
        String matKhauMoiTam = matKhauMoi.getEditText().getText().toString();
        String xacNhanMKTam = xacNhanMK.getEditText().getText().toString();
        if (taiKhoanTam.equals("")){
            Toast.makeText(QuenMatKhauActivity.this, "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean tonTai = ktTaiKhoanCoTonTai(taiKhoanTam);
        if( true){
            if(btn.getText().toString().equals("Gửi mã OTP")){
                btn.setText("Đặt mật khẩu mới");
                taiKhoan.setVisibility(View.INVISIBLE);
                otp.setVisibility(View.VISIBLE);
                matKhauMoi.setVisibility(View.VISIBLE);
                xacNhanMK.setVisibility(View.VISIBLE);
                Toast.makeText(QuenMatKhauActivity.this, "Đã gửi mã OTP", Toast.LENGTH_SHORT).show();
            }
            else{
                if(verifyPassword(matKhauMoiTam) == false){
                    Toast.makeText(QuenMatKhauActivity.this, "Mật khẩu mới sai yêu cầu", Toast.LENGTH_SHORT).show();
                }
                else if(matKhauMoiTam.equals(xacNhanMKTam) == false){
                    Toast.makeText(QuenMatKhauActivity.this, "Mật khẩu xác nhận lại khác với mật khẩu mới", Toast.LENGTH_SHORT).show();
                }
                else{
                    doiMatKhau(taiKhoanTam,otpTam,matKhauMoiTam);
                }
            }
        }
    }
}