package com.example.lib.API;


import com.example.lib.APIResponse.APIResponse;
import com.example.lib.APIResponse.GetTaiKhoanAPIResponse;
import com.example.lib.APIResponse.ToaDoTaiXe;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {
    //dang ky tai khoan
    @POST("/api/TaiKhoan/dangKyTaiKhoan")
    Call<APIResponse> dangKyTaiKhoan(@Query("taiKhoan") String taiKhoan, @Query("matKhau") String matKhau,
                                     @Query("ten") String ten, @Query("soDienThoai") String soDienThoai);
//    Call<APIResponse> insertTaiKhoan(@Query("taiKhoan") String taiKhoan, @Query("matKhau") String matKhau, @Query("ten") String ten,
//                                     @Query("avatar") byte[] avatar, @Query("moMo") String moMo, @Query("soDienThoai") String soDienThoai,
//                                     @Query("diaChi") String diaChi, @Query("otp") String otp);

    @POST("/api/TaiKhoan/dangNhapTaiKhoan")
    Call<APIResponse> dangNhapTaiKhoan(@Query("taiKhoan") String taiKhoan, @Query("matKhau") String matKhau);

    @POST("/api/TaiKhoan/forgotpassword")
    Call<APIResponse> forgotPassword(@Query("taiKhoan") String taiKhoan);

    @POST("/api/TaiKhoan/verifycode")
    Call<APIResponse> verifycode(@Query("taiKhoan") String taiKhoan, @Query("OTP") String OTP, @Query("matKhauMoi") String matKhauMoi);

    @GET("/api/TaiKhoan/Get-TaiKhoan")
    Call<GetTaiKhoanAPIResponse> getTaiKhoan(@Query("taiKhoan") String taiKhoan);

    @POST("/api/TaiXe/ktTonTai")
    Call<APIResponse> ktTonTai(@Query("taiKhoan") String taiKhoan);

    @POST("/api/TaiXe/insertTaiXe")
    Call<APIResponse> insertTaiXe(@Query("taiKhoan") String taiKhoan, @Query("moTa") String moTa,
                                     @Query("thongTinXe") String thongTinXe, @Query("bienSo") String bienSo);
    @Multipart
    @POST("api/TaiKhoan/updateHinhAnh")
    Call<APIResponse> updateHinhAnh(@Part MultipartBody.Part imageFile,
                                    @Part("taiKhoan") RequestBody taiKhoan);

    @Multipart
    @POST("api/TaiXe/addBangLai")
    Call<APIResponse> addBangLai(@Part MultipartBody.Part imageFile,
                                    @Part("taiKhoan") RequestBody taiKhoan);

    @Multipart
    @POST("api/TaiXe/addCMND")
    Call<APIResponse> addCMND(@Part MultipartBody.Part imageFile,
                                    @Part("taiKhoan") RequestBody taiKhoan);

    @Multipart
    @POST("api/TaiXe/addHinhXe")
    Call<APIResponse> addHinhXe(@Part MultipartBody.Part imageFile,
                                    @Part("taiKhoan") RequestBody taiKhoan);

    @Multipart
    @POST("api/TaiXe/addGiayToXe")
    Call<APIResponse> addGiayToXe(@Part MultipartBody.Part imageFile,
                                    @Part("taiKhoan") RequestBody taiKhoan);


    @GET("api/TaiXe/Get-TaiXe-List-HoatDong")
    Call<ToaDoTaiXe> getToaDoTaiXe();
}
