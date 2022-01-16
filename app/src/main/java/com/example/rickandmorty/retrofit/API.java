package com.example.rickandmorty.retrofit;

import com.example.rickandmorty.retrofit.person.Example;
import com.example.rickandmorty.retrofit.person.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {


    @GET("character")
    Call<Example> getPersonInfo();

    @GET("character/{id}")
    Call<Result> getAllInfo(@Path("id") String id);
}
