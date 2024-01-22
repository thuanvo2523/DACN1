package com.example.appdihocchung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class DangKyActivity extends AppCompatActivity {
    private static final String USERNAME_PATTERN = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
    private static final String FULLNAME_PATTERN =
            "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" +
                    "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" +
                    "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$";
    private static final String PHONENUMBER_PATTERN =
            "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    TextInputLayout taiKhoan;
    TextInputLayout ten;
    TextInputLayout sdt;
    TextInputLayout matKhau;
    CheckBox checkBox;
    boolean dongYDieuKhoan = false;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taiKhoan = findViewById(R.id.TaiKhoan);
        ten = findViewById(R.id.Ten);
        sdt = findViewById(R.id.SDT);
        matKhau = findViewById(R.id.MatKhau);
        checkBox = findViewById(R.id.checkbox);
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    dongYDieuKhoan = true;
                }
                else{
                    dongYDieuKhoan = false;
                }
            }
        });
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

    public static boolean verifyFullname(String fullname) {
        if (fullname == null) return false;
        return fullname.matches(FULLNAME_PATTERN);
    }

    public static boolean verifyPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        return phoneNumber.matches(PHONENUMBER_PATTERN);
    }

    public static boolean verifyPassword(String password) {
        if (password == null) return false;
        return password.matches(PASSWORD_PATTERN);
    }

    public void dangKyTaiKhoan(String taiKhoanTam, String tenTam, String sdtTam, String matKhauTam) {
        Call<APIResponse> call = apiInterface.dangKyTaiKhoan(taiKhoanTam,matKhauTam,tenTam,sdtTam);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse product = response.body();
                if( product.getStatus() == true && product.getMessage().equals("")){
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKyActivity.this,TrangChuActivity.class);
                    intent.putExtra("taiKhoan",taiKhoanTam);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(DangKyActivity.this, "Lỗi, không thể đăng ký", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(DangKyActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void manHinhChinh(View view) {
        String taiKhoanTam = taiKhoan.getEditText().getText().toString();
        String tenTam = ten.getEditText().getText().toString();
        String sdtTam = sdt.getEditText().getText().toString();
        String matKhauTam = matKhau.getEditText().getText().toString();
        if(taiKhoanTam.equals("") || tenTam.equals("") || sdtTam.equals("") || matKhauTam.equals("")){
            Toast.makeText(DangKyActivity.this, "Vui lòng nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
        } else if (verifyUsername(taiKhoanTam) == false) {
            Toast.makeText(DangKyActivity.this, "Tài khoản không đúng yêu cầu", Toast.LENGTH_SHORT).show();
        }
        else if (verifyFullname(tenTam) == false) {
            Toast.makeText(DangKyActivity.this, "Họ tên ghi sai cú pháp", Toast.LENGTH_SHORT).show();
        }
        else if (verifyPhoneNumber(sdtTam) == false) {
            Toast.makeText(DangKyActivity.this, "Số điện thoại sai cú pháp", Toast.LENGTH_SHORT).show();
        }else if (verifyPassword(matKhauTam) == false) {
            Toast.makeText(DangKyActivity.this, "Mật khẩu không đúng yêu cầu", Toast.LENGTH_SHORT).show();
        }else if (dongYDieuKhoan == false) {
            Toast.makeText(DangKyActivity.this, "Bạn chưa đồng ý với điều khoản", Toast.LENGTH_SHORT).show();
        } else{
            dangKyTaiKhoan(taiKhoanTam,tenTam,sdtTam,matKhauTam);
        }
    }

    public void dangNhap(View view) {
        Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
        startActivity(intent);
    }
}