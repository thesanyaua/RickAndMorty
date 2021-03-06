package com.example.rickandmorty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.FragmentInfoPersonBinding;
import com.example.rickandmorty.databinding.FragmentPersonListBinding;
import com.example.rickandmorty.model.PersonInfoViewModel;
import com.example.rickandmorty.retrofit.person.Result;


public class InfoPersonFragment extends Fragment {
    FragmentInfoPersonBinding binding;
    public String personID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            personID = getArguments().getString("PersonID", "0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_person,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        PersonInfoViewModel personInfoViewModel = new ViewModelProvider(this).get(PersonInfoViewModel.class);

        personInfoViewModel.getPerson(personID).observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                Glide.with(InfoPersonFragment.this).load(result.getImage()).into(binding.imagePersonInfo);
                binding.namePerson.setText(result.getName());
                binding.statusLive.setText("Live status: "+result.getStatus());
                binding.gender.setText("Gender: "+result.getGender());
                binding.location.setText("From: "+result.getOrigin().getName());
            }
        });




    }
}