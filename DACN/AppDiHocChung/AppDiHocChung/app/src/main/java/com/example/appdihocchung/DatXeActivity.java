package com.example.appdihocchung;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.ViewGroupUtils;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.health.connect.datatypes.ExerciseRoute;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.appdihocchung.Model.taiXeAndAddress;
import com.example.lib.API.APIClient;
import com.example.lib.API.APIInterface;
import com.example.lib.APIResponse.ToaDoTaiXe;
import com.example.lib.ApiGoogle.ApiGoogleClient;
import com.example.lib.ApiGoogle.ApiGoogleInterface;

import com.example.lib.ApiGoogle.Model.DirectionResponses;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.androidgamesdk.gametextinput.Listener;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatXeActivity extends AppCompatActivity implements OnMapReadyCallback{
    String taiKhoan;
    TextView DiaChiDi;
    TextView DiaChiDen;

    Button DatXe;
    String Place_Id_DiaChiDi;
    String Place_Id_DiaChiDen;
    String diachitaikhoanTaiXe;
    LatLng LatLng_DiaChiDi;
    LatLng LatLng_DiaChiDen;
    private GoogleMap mMap;
    APIInterface apiInterface;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;
    ApiGoogleInterface apiGoogleInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_xe);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        DatXe = (Button) findViewById(R.id.btn_DatXe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiGoogleInterface = ApiGoogleClient.getClient().create(ApiGoogleInterface.class);
        Intent intent = getIntent();
        taiKhoan = intent.getStringExtra("taiKhoan");

        DiaChiDi = findViewById(R.id.diaChiDi);
        DiaChiDen = findViewById(R.id.diaChiDen);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(!Places.isInitialized()){
            Places.initialize(getApplication(),"AIzaSyB3nJndthR3cX5wTqWgH046sVmT_v075U4");
        }
        PlacesClient placesClient = Places.createClient(this);

        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG, Place.Field.ADDRESS);

        //-------------------------------------------------------------------------------------------------------------------------------------------------

        DiaChiDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountries(Arrays.asList("VN"))
                        .build(DatXeActivity.this);
                startAutocomplete.launch(intent);
            }
        });
        // Start the autocomplete intent.

        //-------------------------------------------------------------------------------------------------------------------------------------------------

        DiaChiDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .setCountries(Arrays.asList("VN"))
                        .build(DatXeActivity.this);
                startAutocomplete_2.launch(intent);
            }
        });

        DatXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timTaiXe();
            }
        });
        // Start the autocomplete intent.


    }

    private final ActivityResultLauncher<Intent> startAutocomplete = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Place place = Autocomplete.getPlaceFromIntent(intent);
                        DiaChiDi.setText(place.getAddress());
                        Place_Id_DiaChiDi = place.getId();
                        LatLng_DiaChiDi = place.getLatLng();
                        LatLng latLng = place.getLatLng();
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(place.getAddress()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
                        datXe();
                    }
                } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    // The user canceled the operation.
                    Log.i(TAG, "User canceled autocomplete");
                }
            });

    private final ActivityResultLauncher<Intent> startAutocomplete_2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null) {
                        Place place = Autocomplete.getPlaceFromIntent(intent);
                        DiaChiDen.setText(place.getAddress());
                        Place_Id_DiaChiDen = place.getId();
                        LatLng_DiaChiDen = place.getLatLng();
                        LatLng latLng = place.getLatLng();
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(place.getAddress()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
                        datXe();
                    }
                } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    // The user canceled the operation.
                    Log.i(TAG, "User canceled autocomplete");
                }
            });

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Nếu không có quyền, yêu cầu quyền
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Hiển thị nút "My Location" để dễ dàng điều hướng đến vị trí hiện tại
        mMap.setMyLocationEnabled(true);
        // Lấy vị trí hiện tại và đặt marker
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng currentLatLng = new LatLng(latitude, longitude);

                // Đặt marker
                if (currentLocationMarker != null) {
                    currentLocationMarker.remove();
                }
                currentLocationMarker = mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Your Location"));

                // Di chuyển camera đến vị trí hiện tại
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
            }
        });
    }

    private void drawPolylineTaiXe(@NonNull Response<DirectionResponses> response) {
        if (response.body() != null) {
            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
            PolylineOptions polyline = new PolylineOptions()
                    .addAll(PolyUtil.decode(shape))
                    .width(10f)
                    .color(Color.RED);
            mMap.addPolyline(polyline);

            LatLng midpoint = new LatLng(
                    (LatLng_DiaChiDi.latitude + LatLng_DiaChiDen.latitude) / 2,
                    (LatLng_DiaChiDi.longitude + LatLng_DiaChiDen.longitude) / 2
            );
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midpoint, 11));
        }
    }
    private void drawPolyline(@NonNull Response<DirectionResponses> response) {
        if (response.body() != null) {
            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
            PolylineOptions polyline = new PolylineOptions()
                    .addAll(PolyUtil.decode(shape))
                    .width(10f)
                    .color(Color.BLUE);
            mMap.addPolyline(polyline);

            LatLng midpoint = new LatLng(
                    (LatLng_DiaChiDi.latitude + LatLng_DiaChiDen.latitude) / 2,
                    (LatLng_DiaChiDi.longitude + LatLng_DiaChiDen.longitude) / 2
            );
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midpoint, 11));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void timTaiXe() {
        Call<ToaDoTaiXe> call = apiInterface.getToaDoTaiXe();
        call.enqueue(new Callback<ToaDoTaiXe>() {
            @Override
            public void onResponse(Call<ToaDoTaiXe> call, Response<ToaDoTaiXe> response) {
                if(response.code() == 200){
                    Toast.makeText(DatXeActivity.this, " được", Toast.LENGTH_SHORT).show();

                    Geocoder geocoder = new Geocoder(DatXeActivity.this);

                    List<taiXeAndAddress> listTaiXe = new ArrayList<taiXeAndAddress>();
                    ToaDoTaiXe product = response.body();
                    if(response.isSuccessful()){
                        Toast.makeText(DatXeActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                        if(product.getStatus() == true){
                            Toast.makeText(DatXeActivity.this, "Product OK", Toast.LENGTH_SHORT).show();
                            float min = 1000;
                            List<Address> originAddresses = null;
                            for (int i = 0 ; i < product.getData().size(); i++){
                                List<Address> destinationAddresses = null;
                                try {

                                    originAddresses = geocoder.getFromLocationName(DiaChiDi.getText().toString(), 1);
                                    destinationAddresses = geocoder.getFromLocationName(product.getData().get(i).getDiaChi(), 1);
                                    Location originLocation = new Location("origin");
                                    originLocation.setLatitude(originAddresses.get(0).getLatitude());
                                    originLocation.setLongitude(originAddresses.get(0).getLongitude());

                                    Location destinationLocation = new Location("destination");
                                    destinationLocation.setLatitude(destinationAddresses.get(0).getLatitude());
                                    destinationLocation.setLongitude(destinationAddresses.get(0).getLongitude());

                                    float distance = originLocation.distanceTo(destinationLocation);
                                    if(distance < min){
                                        min = distance;

                                        diachitaikhoanTaiXe = product.getData().get(i).getDiaChi();

                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            timDuongDiTuTaiXeDenKhach();

                        }
                        else{
                            Toast.makeText(DatXeActivity.this, "Product no Ok", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(DatXeActivity.this, "Không được", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ToaDoTaiXe> call, Throwable t) {
                Toast.makeText(DatXeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void timDuongDiTuTaiXeDenKhach(){
        Toast.makeText(DatXeActivity.this, "Gọi API", Toast.LENGTH_SHORT).show();

        Call<DirectionResponses> directionResponsesCall = apiGoogleInterface.getDirections((String) diachitaikhoanTaiXe,
                (String) DiaChiDi.getText(),
                "AIzaSyB3nJndthR3cX5wTqWgH046sVmT_v075U4");
        directionResponsesCall.enqueue(new Callback<DirectionResponses>() {
            @Override
            public void onResponse(Call<DirectionResponses> call, Response<DirectionResponses> response) {
                if(response.code() == 200) {
                    Toast.makeText(DatXeActivity.this, "Thành Công" + response.code(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {

                        Geocoder geocoder = new Geocoder(DatXeActivity.this);

                        // Lấy tọa độ của địa chỉ
                        try {
                            List<Address> addresses = geocoder.getFromLocationName(diachitaikhoanTaiXe, 1);

                            LatLng latLngDiaChiTaiXe = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                            // Lấy tọa độ
                            mMap.addMarker(new MarkerOptions()
                                    .position(latLngDiaChiTaiXe)
                                    .title(diachitaikhoanTaiXe));

                            // Hiển thị tọa độ
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        drawPolylineTaiXe(response);
                    } else {
                        Toast.makeText(DatXeActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }

            }


            @Override
            public void onFailure(Call<DirectionResponses> call, Throwable t) {

            }
        });
    }


    public void datXe() {
        if(DiaChiDi.getText() != "" && DiaChiDen.getText() != ""){
            Call<DirectionResponses> call = apiGoogleInterface.getDirections((String) DiaChiDi.getText(),
                    (String) DiaChiDen.getText(),
                    "AIzaSyB3nJndthR3cX5wTqWgH046sVmT_v075U4");
            call.enqueue(new Callback<DirectionResponses>() {
                @Override
                public void onResponse(Call<DirectionResponses> call, Response<DirectionResponses> response) {
                    if(response.code() == 200) {
                        Toast.makeText(DatXeActivity.this, "Thành Công" + response.code(), Toast.LENGTH_SHORT).show();
                        if (response.isSuccessful()) {
                            Toast.makeText(DatXeActivity.this, "TKhông Null" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                            drawPolyline(response);
                        } else {
                            Toast.makeText(DatXeActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }


                @Override
                public void onFailure(Call<DirectionResponses> call, Throwable t) {

                }
            });
        }
        else{
            if(DiaChiDi.getText() == "" && DiaChiDen.getText() != ""){
                Toast.makeText(DatXeActivity.this, "Nhập địa chỉ đón", Toast.LENGTH_SHORT).show();
            }
            else if (DiaChiDi.getText() != "" && DiaChiDen.getText() == ""){
                Toast.makeText(DatXeActivity.this, "Nhập địa chỉ đi đến", Toast.LENGTH_SHORT).show();
            }
            else if(DiaChiDi.getText() == "" && DiaChiDen.getText() == ""){
                Toast.makeText(DatXeActivity.this, "Nhập địa chỉ đón và điểm đến ", Toast.LENGTH_SHORT).show();

            }
        }
    }

//    String taiKhoan;
//    private GoogleMap mMap;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dat_xe);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Intent intent = getIntent();
//        taiKhoan = intent.getStringExtra("taiKhoan");
//
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        if(!Places.isInitialized()){
//            Places.initialize(getApplication(),"AIzaSyB9QzLCnCqxRUHKZhIY4n4ZHoUaVZiKOJg");
//        }
//        PlacesClient placesClient = Places.createClient(this);
//
//        //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        // Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));
//
//        // Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                // TODO: Get info about the selected place.
//                LatLng latLng = place.getLatLng();
//                mMap.addMarker(new MarkerOptions()
//                        .position(latLng)
//                        .title(place.getAddress()));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
//
//                // Log the place's name, ID, and coordinates
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() +
//                        ", Lat: " + latLng.latitude + ", Lng: " + latLng.longitude);
//
//                kt1 = true;
//
//            }
//
//
//            @Override
//            public void onError(@NonNull Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
//
//        //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//        AutocompleteSupportFragment autocompleteFragment_2 = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment_2);
//
//        // Specify the types of place data to return.
//        autocompleteFragment_2.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));
//
//        // Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment_2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                // TODO: Get info about the selected place.
//                LatLng latLng = place.getLatLng();
//                mMap.addMarker(new MarkerOptions()
//                        .position(latLng)
//                        .title(place.getAddress()));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
//
//                // Log the place's name, ID, and coordinates
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() +
//                        ", Lat: " + latLng.latitude + ", Lng: " + latLng.longitude);
//
//                kt2 = true;
//
//            }
//
//
//            @Override
//            public void onError(@NonNull Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
//    }
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng NgoQuyen = new LatLng(10.712966019883677, 106.73690584417822);
//        mMap.addMarker(new MarkerOptions()
//                .position(NgoQuyen)
//                .title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(NgoQuyen));
//        mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
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
//    boolean kt1 = false;
//    boolean kt2 = false;
//    public void datXe(View view) {
//        if(kt1 == false){
//            Toast.makeText(DatXeActivity.this, "Vui lòng nhập địa chỉ đi", Toast.LENGTH_SHORT).show();
//        }
//        else if(kt2 == false){
//            Toast.makeText(DatXeActivity.this, "Vui lòng nhập địa chỉ đến", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Intent intent = new Intent(DatXeActivity.this,TimTaiXeActivity.class);
//            intent.putExtra("taiKhoan",taiKhoan);
//            intent.putExtra("diaChiDi","cai nay teo bo vo toa do hay gi do");
//            intent.putExtra("diaChiDen","cai nay teo bo vo toa do hay gi do");
//            startActivity(intent);
//        }
//    }

//    String taiKhoan;
//    TextView DiaChiDi;
//    TextView DiaChiDen;
//
//    Button DatXe;
//    String Place_Id_DiaChiDi;
//    String Place_Id_DiaChiDen;
//
//    LatLng LatLng_DiaChiDi;
//    LatLng LatLng_DiaChiDen;
//    private GoogleMap mMap;
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private Marker currentLocationMarker;
//    ApiGoogleInterface apiGoogleInterface;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dat_xe);
//        DatXe = (Button) findViewById(R.id.btn_DatXe);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        apiGoogleInterface = ApiGoogleClient.getClient().create(ApiGoogleInterface.class);
//        Intent intent = getIntent();
//        taiKhoan = intent.getStringExtra("taiKhoan");
//
//        DiaChiDi = findViewById(R.id.diaChiDi);
//        DiaChiDen = findViewById(R.id.diaChiDen);
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        if(!Places.isInitialized()){
//            Places.initialize(getApplication(),"AIzaSyB3nJndthR3cX5wTqWgH046sVmT_v075U4");
//        }
//        PlacesClient placesClient = Places.createClient(this);
//
//        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG, Place.Field.ADDRESS);
//
//        //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//        DiaChiDi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                        .setCountries(Arrays.asList("VN"))
//                        .build(DatXeActivity.this);
//                startAutocomplete.launch(intent);
//            }
//        });
//        // Start the autocomplete intent.
//
//        //-------------------------------------------------------------------------------------------------------------------------------------------------
//
//        DiaChiDen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                        .setCountries(Arrays.asList("VN"))
//                        .build(DatXeActivity.this);
//                startAutocomplete_2.launch(intent);
//            }
//        });
//
//        DatXe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DatXeActivity.this,TimTaiXeActivity.class);
//                intent.putExtra("diaChiDi",(String) DiaChiDi.getText());
//                intent.putExtra("diaChiDen",(String) DiaChiDen.getText());
//                startActivity(intent);
//            }
//        });
//        // Start the autocomplete intent.
//
//
//    }
//
//    private final ActivityResultLauncher<Intent> startAutocomplete = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    Intent intent = result.getData();
//                    if (intent != null) {
//                        Place place = Autocomplete.getPlaceFromIntent(intent);
//                        DiaChiDi.setText(place.getAddress());
//                        Place_Id_DiaChiDi = place.getId();
//                        LatLng_DiaChiDi = place.getLatLng();
//                        LatLng latLng = place.getLatLng();
//                        mMap.addMarker(new MarkerOptions()
//                                .position(latLng)
//                                .title(place.getAddress()));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                        mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
//                        datXe();
//                    }
//                } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
//                    // The user canceled the operation.
//                    Log.i(TAG, "User canceled autocomplete");
//                }
//            });
//
//    private final ActivityResultLauncher<Intent> startAutocomplete_2 = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == Activity.RESULT_OK) {
//                    Intent intent = result.getData();
//                    if (intent != null) {
//                        Place place = Autocomplete.getPlaceFromIntent(intent);
//                        DiaChiDen.setText(place.getAddress());
//                        Place_Id_DiaChiDen = place.getId();
//                        LatLng_DiaChiDen = place.getLatLng();
//                        LatLng latLng = place.getLatLng();
//                        mMap.addMarker(new MarkerOptions()
//                                .position(latLng)
//                                .title(place.getAddress()));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                        mMap.moveCamera(CameraUpdateFactory.zoomTo(20));
//                        datXe();
//                    }
//                } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
//                    // The user canceled the operation.
//                    Log.i(TAG, "User canceled autocomplete");
//                }
//            });
//
//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mMap = googleMap;
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // Nếu không có quyền, yêu cầu quyền
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        }
//
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//
//        // Hiển thị nút "My Location" để dễ dàng điều hướng đến vị trí hiện tại
//        mMap.setMyLocationEnabled(true);
//        // Lấy vị trí hiện tại và đặt marker
//        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
//            if (location != null) {
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//                LatLng currentLatLng = new LatLng(latitude, longitude);
//
//                // Đặt marker
//                if (currentLocationMarker != null) {
//                    currentLocationMarker.remove();
//                }
//                currentLocationMarker = mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Your Location"));
//
//                // Di chuyển camera đến vị trí hiện tại
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
//            }
//        });
//    }
//
//    private void drawPolyline(@NonNull Response<DirectionResponses> response) {
//        if (response.body() != null) {
//            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
//            PolylineOptions polyline = new PolylineOptions()
//                    .addAll(PolyUtil.decode(shape))
//                    .width(10f)
//                    .color(Color.BLUE);
//            mMap.addPolyline(polyline);
//
//            LatLng midpoint = new LatLng(
//                    (LatLng_DiaChiDi.latitude + LatLng_DiaChiDen.latitude) / 2,
//                    (LatLng_DiaChiDi.longitude + LatLng_DiaChiDen.longitude) / 2
//            );
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midpoint, 11));
//        }
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
//    public void datXe() {
//        if(DiaChiDi.getText() != "" && DiaChiDen.getText() != ""){
//            Call<DirectionResponses> call = apiGoogleInterface.getDirections((String) DiaChiDi.getText(),
//                    (String) DiaChiDen.getText(),
//                    "AIzaSyB3nJndthR3cX5wTqWgH046sVmT_v075U4");
//            call.enqueue(new Callback<DirectionResponses>() {
//                @Override
//                public void onResponse(Call<DirectionResponses> call, Response<DirectionResponses> response) {
//                    if(response.code() == 200) {
//                        Toast.makeText(DatXeActivity.this, "Thành Công" + response.code(), Toast.LENGTH_SHORT).show();
//                        if (response.isSuccessful()) {
//                            Toast.makeText(DatXeActivity.this, "TKhông Null" + response.body().getStatus(), Toast.LENGTH_SHORT).show();
//                            drawPolyline(response);
//                        } else {
//                            Toast.makeText(DatXeActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                }
//
//
//                @Override
//                public void onFailure(Call<DirectionResponses> call, Throwable t) {
//
//                }
//            });
//        }
//        else{
//            if(DiaChiDi.getText() == "" && DiaChiDen.getText() != ""){
//                Toast.makeText(DatXeActivity.this, "Nhập địa chỉ đón", Toast.LENGTH_SHORT).show();
//            }
//            else if (DiaChiDi.getText() != "" && DiaChiDen.getText() == ""){
//                Toast.makeText(DatXeActivity.this, "Nhập địa chỉ đi đến", Toast.LENGTH_SHORT).show();
//            }
//            else if(DiaChiDi.getText() == "" && DiaChiDen.getText() == ""){
//                Toast.makeText(DatXeActivity.this, "Nhập địa chỉ đón và điểm đến ", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    }

}