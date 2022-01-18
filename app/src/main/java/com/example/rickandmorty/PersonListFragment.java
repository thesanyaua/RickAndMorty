package com.example.rickandmorty;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rickandmorty.adapter.PersonListAdapter;
import com.example.rickandmorty.databinding.FragmentPersonListBinding;
import com.example.rickandmorty.model.ApiViewModel;
import com.example.rickandmorty.retrofit.person.Result;

import java.util.Comparator;
import java.util.List;


public class PersonListFragment extends Fragment {
    FragmentPersonListBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_list, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiViewModel apiViewModel = new ViewModelProvider(this).get(ApiViewModel.class);

        apiViewModel.getListPerson().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<Result> results) {
                PersonListAdapter personListAdapter = new PersonListAdapter(results, getContext());
                binding.recyclerViewPersonList.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerViewPersonList.setAdapter(personListAdapter);
            }
        });

    }
}