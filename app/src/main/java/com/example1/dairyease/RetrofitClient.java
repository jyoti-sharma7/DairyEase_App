package com.example1.dairyease;

import com.example1.dairyease.RetrofitInterface.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    String BASE_URL = "http://dairyease.oxfordcollege.edu.np/api/";

    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;


    static Gson gson = new GsonBuilder().setLenient().create();

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient==null){
            retrofitClient = new RetrofitClient();
        }
         return retrofitClient;
    }


    //api lai get garna method banauni
    public ApiInterface getApi(){

        return retrofit.create(ApiInterface.class);
    }


}
