package com.example.cg_cafe.api;

import com.example.cg_cafe.model.HistoryModel;
import com.example.cg_cafe.model.Login;
import com.example.cg_cafe.model.PemesananModel;
import com.example.cg_cafe.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<Login> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("orderpesanan")
    Call<ResponseModel> ardOrderData(
            @Field("id") String id,
            @Field("idMenu") String idMenu,
            @Field("idPengguna") String idPengguna,
            @Field("jumlahPesan") String jumlahPesan,
            @Field("harga") String harga,
            @Field("totalharga") String totalharga,
            @Field("catatan") String catatan,
            @Field("mejano") String mejano,
            @Field("status") String status
    );

    @GET("DataPesananController")
    Call<ResponseModel> ardPesananData();

    @GET("DataKasirController")
    Call<ResponseModel> ardKasirData();

    @GET("HistoryPesananController")
    Call<HistoryModel> ardHistoryData();

    @GET("MenuHidanganController")
    Call<PemesananModel> ardPemesananData();

    @FormUrlEncoded
    @POST("updatedatapesanan")
    Call<ResponseModel> ardUpdateData(
            @Field("id") String id,
            @Field("status") String status);
}
