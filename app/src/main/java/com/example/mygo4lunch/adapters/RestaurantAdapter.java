package com.example.mygo4lunch.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygo4lunch.databinding.RestaurantItemLayoutBinding;
import com.example.mygo4lunch.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant> restaurantList;

    private List<String> workMatesIds;


    public RestaurantAdapter() {
    }


    public void setRestaurantList(List<Restaurant> restaurantList, List<String> workMatesId) {
        this.restaurantList = restaurantList;
        this.workMatesIds = workMatesId;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RestaurantItemLayoutBinding binding = RestaurantItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new RestaurantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.updateRestaurantInfo(restaurantList.get(position), workMatesIds);

    }

    @Override
    public int getItemCount() {
        if (restaurantList != null)
            return restaurantList.size();
        return 0;
    }
}
