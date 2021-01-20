package com.example.myquiz;

import com.example.myquiz.model.RetrofitScorecard;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {
    String baseUrl = "http://10.0.0.100/";

    @FormUrlEncoded
    @POST("/Quiz/insert.php")
    public void insertUser(
            @Field("classification") String cat,
            @Field("grade") int score,
            Callback<Response> callback);

    @GET("/Quiz/showRetrofitData.php")
    Call<List<RetrofitScorecard>> getScoreCardData();

}
