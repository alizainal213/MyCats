package com.example.mycats.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataCat {

    //ini adalah URL Parameter
    @GET("v1/breeds")
    Call<List<ApiServiceCat>> getAllInfo();
}
