package com.example.rickandmorty.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.PersonRecyclerViewBinding;
import com.example.rickandmorty.retrofit.person.Result;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListAdapterViewHolder> {

    List<Result> list;
    Context context;

    public PersonListAdapter(List<Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonRecyclerViewBinding binding = PersonRecyclerViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonListAdapterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PersonListAdapterViewHolder holder, int position) {
           Result result = list.get(position);
           holder.binding.namePerson.setText(result.getName());
           Glide.with(context).load(result.getImage()).into(holder.binding.imagePerson);

           holder.binding.cardViewPerson.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Bundle bundle = new Bundle();
                   bundle.putString("PersonID", result.getId().toString());
                   Navigation.findNavController(v).navigate(R.id.action_personListFragment_to_infoPersonFragment, bundle);
               }
           });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class PersonListAdapterViewHolder extends RecyclerView.ViewHolder {
        PersonRecyclerViewBinding binding;
        public PersonListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
