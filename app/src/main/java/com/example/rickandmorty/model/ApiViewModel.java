package com.example.rickandmorty.model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rickandmorty.retrofit.API;
import com.example.rickandmorty.retrofit.ApiClient;
import com.example.rickandmorty.retrofit.person.Example;
import com.example.rickandmorty.retrofit.person.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiViewModel extends AndroidViewModel {

    MutableLiveData<List<Result>> person = new MutableLiveData<>();

    public ApiViewModel(@NonNull Application application) {
        super(application);
    }

      public LiveData<List<Result>> getListPerson() {
        API api = ApiClient.getRetrofit().create(API.class);
        Call<Example> call = api.getPersonInfo();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                person.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return person;
    }



}
