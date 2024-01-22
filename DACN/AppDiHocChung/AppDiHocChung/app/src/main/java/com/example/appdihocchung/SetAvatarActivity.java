package com.example.appdihocchung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appdihocchung.Model.RealPathUtil;
import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetAvatarActivity extends AppCompatActivity {
    Button BSelectImage;
    Button btnLuuAvatar;
    ImageView IVPreviewImage;
    String taiKhoan;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_avatar);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");

        // register the UI widgets with their appropriate IDs
        BSelectImage = findViewById(R.id.BSelectImage);
        btnLuuAvatar = findViewById(R.id.luuVaoSql);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        btnLuuAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uri != null){
                    luuVaoSql();
                }
                else{
                    Toast.makeText(SetAvatarActivity.this, "Chưa chọn hình", Toast.LENGTH_SHORT).show();
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

    void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }
    private byte[] convertImageToByteArray(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Uri uri = null;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            // Lấy Uri của hình ảnh
            uri = data.getData();

            if (null != uri) {
                // update the preview image in the layout
                byte[] imageBytes = convertImageToByteArray(uri);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                IVPreviewImage.setImageBitmap(bitmap);
            }
        }
    }

    public void luuVaoSql(){
        String strRealPath = RealPathUtil.getRealPath(this,uri);
        File file = new File(strRealPath);
        RequestBody requestBodytaiKhoan = RequestBody.create(MediaType.parse("multipart/form-data"),taiKhoan);
        RequestBody requestBodyimageFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part multipartbodyimageFile = MultipartBody.Part.createFormData("imageFile",file.getName(),requestBodyimageFile);

        Call<APIResponse> call = apiInterface.updateHinhAnh(multipartbodyimageFile,requestBodytaiKhoan);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                int statuscode = response.code();
                if(statuscode == 200){
                    Toast.makeText(SetAvatarActivity.this, "Đã lưu avatar", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SetAvatarActivity.this,TaiKhoanActivity.class);
                    intent.putExtra("taiKhoan",taiKhoan);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SetAvatarActivity.this, "Lỗi " + statuscode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
        // Gửi hình ảnh đến API
    }
}