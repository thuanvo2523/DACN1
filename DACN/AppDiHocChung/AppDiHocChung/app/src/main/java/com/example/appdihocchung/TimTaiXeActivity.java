package com.example.appdihocchung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdihocchung.Model.taiXeAndAddress;
import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.APIResponse;
import com.example.lib.APIResponse.ToaDoTaiXe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimTaiXeActivity extends AppCompatActivity {
    String taiKhoan;
    String diaChiDi;
    String diaChiDen;



    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_tai_xe);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");
//        diaChiDi = intent.getStringExtra("diaChiDi");
        diaChiDen = intent.getStringExtra("diaChiDen");
        diaChiDi = "65/3, Điện Biên Phủ, Phường 25, Quận Bình Thạnh, Phường 25, Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam";


        Call<ToaDoTaiXe> call = apiInterface.getToaDoTaiXe();
        call.enqueue(new Callback<ToaDoTaiXe>() {
            @Override
            public void onResponse(Call<ToaDoTaiXe> call, Response<ToaDoTaiXe> response) {
                Geocoder geocoder = new Geocoder(TimTaiXeActivity.this);
                List<Address> originAddresses = null;
                try {
                    originAddresses = geocoder.getFromLocationName(diaChiDi, 1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                List<taiXeAndAddress> listTaiXe = new ArrayList<taiXeAndAddress>();
                ToaDoTaiXe product = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(TimTaiXeActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                    if(product != null){
                        Toast.makeText(TimTaiXeActivity.this, "Product OK", Toast.LENGTH_SHORT).show();
                        float min = 1000;
                        for (int i = 0 ; i < product.getData().size(); i++){
                            List<Address> destinationAddresses = null;
                            try {
                                destinationAddresses = geocoder.getFromLocationName(product.getData().get(i).getDiaChi(), 1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            Location originLocation = new Location("origin");
                            originLocation.setLatitude(originAddresses.get(0).getLatitude());
                            originLocation.setLongitude(originAddresses.get(0).getLongitude());

                            Location destinationLocation = new Location("destination");
                            destinationLocation.setLatitude(destinationAddresses.get(0).getLatitude());
                            destinationLocation.setLongitude(destinationAddresses.get(0).getLongitude());

                            float distance = originLocation.distanceTo(destinationLocation);

                            // Hiển thị khoảng cách lên màn hình
                            TextView distanceTextView = findViewById(R.id.test);
                            distanceTextView.setText(String.format("%.2f mét", distance));
                            Toast.makeText(TimTaiXeActivity.this, String.format("%.2f mét", distance), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(TimTaiXeActivity.this, "Product no Ok", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(TimTaiXeActivity.this, "Không được", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ToaDoTaiXe> call, Throwable t) {
                Toast.makeText(TimTaiXeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}

