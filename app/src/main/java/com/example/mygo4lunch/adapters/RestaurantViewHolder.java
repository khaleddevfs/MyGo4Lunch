package com.example.mygo4lunch.adapters;


import static com.example.mygo4lunch.ui.RestaurantDetailsActivity.RESTAURANT_PLACE_ID;

import android.content.Context;
import android.content.Intent;

import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygo4lunch.R;
import com.example.mygo4lunch.databinding.RestaurantItemLayoutBinding;
import com.example.mygo4lunch.models.Restaurant;
import com.example.mygo4lunch.ui.RestaurantDetailsActivity;

import java.util.List;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    public final RestaurantItemLayoutBinding binding;

    private Context context;
    public RestaurantViewHolder(RestaurantItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void updateRestaurantInfo(Restaurant restaurant, List<String> workMateIds){
        binding.restaurantItemListName.setText(restaurant.getName());
        context = binding.getRoot().getContext();

        if (restaurant.getDistance() > 0) {
            binding.restaurantItemListName.setText(restaurant.getDistance() + " m");
        }

        if (restaurant.getAddress() != null) {
            binding.restaurantItemListAddress.setText(restaurant.getAddress());
        }
        int counter = checkWorkMatesEatingHere(restaurant.getRestaurantID(), workMateIds);
        binding.restaurantItemListParticipantsNumber.setText("(" + counter + ")");
        binding.restaurantItemListInfo.setText((restaurant.isOpenNow()) ? R.string.restaurant_on : R.string.restaurant_closed_today);
        TextViewCompat.setTextAppearance(binding.restaurantItemListInfo, R.style.TimeRestaurantOpen);
        if (restaurant.getPhotoReference() != null) {
            Glide.with(context)
                    .load(restaurant.getPhotoReference())
                    .centerCrop()
                    .into(binding.restaurantImagePhotoItem);
        }
        binding.restaurantItemListRate.setRating(restaurant.getRating());
        binding.restaurantItemListRate.getNumStars();

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestaurantDetailsActivity.class);
            intent.putExtra(RESTAURANT_PLACE_ID, restaurant.getRestaurantID());
            context.startActivity(intent);
        });
    }


    private int checkWorkMatesEatingHere(String restaurantID, List<String> workMateIds) {
        int counter = 0;
        if (workMateIds != null) {
            for (String id : workMateIds) {
                if (id.equals(restaurantID)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
