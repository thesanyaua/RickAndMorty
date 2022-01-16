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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonInfoViewModel extends AndroidViewModel {
    MutableLiveData<String> imagePerson = new MutableLiveData<>();
    MutableLiveData<String> namePerson = new MutableLiveData<>();
    MutableLiveData<String> genderPerson = new MutableLiveData<>();
    MutableLiveData<String> livePerson = new MutableLiveData<>();
    MutableLiveData<String> location = new MutableLiveData<>();

    public PersonInfoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getImagePerson(String id) {
        API api = ApiClient.getRetrofit().create(API.class);
        api.getAllInfo(id).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                imagePerson.postValue(response.body().getImage());
                namePerson.postValue(response.body().getName());
                genderPerson.postValue(response.body().getGender());
                livePerson.postValue(response.body().getStatus());
                location.postValue(response.body().getLocation().getName());

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        return imagePerson;
    }

    public LiveData<String> getName() {
        return namePerson;
    }

    public LiveData<String> getGender() {
        return genderPerson;
    }

    public LiveData<String> getLivePerson() {
        return livePerson;
    }

    public LiveData<String> getLocation() {
        return location;
    }




}
