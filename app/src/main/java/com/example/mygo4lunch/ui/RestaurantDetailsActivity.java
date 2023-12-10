package com.example.mygo4lunch.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mygo4lunch.databinding.ActivityRestaurantDetailsBinding;
import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.injections.Injection;
import com.example.mygo4lunch.models.Restaurant;
import com.example.mygo4lunch.viewModel.RestaurantDetailsViewModel;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private final String TAG = RestaurantDetailsActivity.class.getSimpleName(); // why?????

    public static final String RESTAURANT_PLACE_ID = "placeId";

    private Restaurant restaurant;

    private ActivityRestaurantDetailsBinding binding;

    private RestaurantDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();
        initViewModel();
        getRestaurantPlaceId();

    }

    private void initView() {
        binding = ActivityRestaurantDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void initListener() {
        binding.restaurantDetailsCallButton.setOnClickListener(V -> openDialer(restaurant.getPhoneNumber()));
        binding.restaurantDetailsLikeButton.setOnClickListener(v -> viewModel.updateRestaurantLiked(restaurant));
        binding.restaurantDetailsWebsiteButton.setOnClickListener(v -> openWebSite(restaurant.getWebSite()));
        binding.restaurantDetailsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void initViewModel() {
        Go4LunchFactory viewModelFactory = Injection.provideViewModelFactory();
        viewModel = new ViewModelProvider(this, viewModelFactory).get(RestaurantDetailsViewModel.class);
    }

    private void getRestaurantPlaceId() {
        if (getIntent().hasExtra(RESTAURANT_PLACE_ID)){
            String placeId = getIntent().getStringExtra(RESTAURANT_PLACE_ID);

            initWorkMatesList();
        }
    }





    private void displayInfoRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        binding.restaurantDetailsName.setText(restaurant.getName());
        binding.restaurantDetailsAddress.setText(restaurant.getAddress());

        if (restaurant.getPhotoReference() != null) {
            Glide.with(RestaurantDetailsActivity.this)
                    .load(restaurant.getPhotoReference())
                    .apply(RequestOptions.centerCropTransform())
                    .into(this.binding.restaurantDetailsPicture);
        }

        viewModel.fetchInfoRestaurant(restaurant);
        viewModel.fetchWorkMateLike(restaurant);
    }

    private void initWorkMatesList() {
    }


    private void openDialer(String phoneNumber) {
    }

    private void openWebSite(String webSite) {
    }



}