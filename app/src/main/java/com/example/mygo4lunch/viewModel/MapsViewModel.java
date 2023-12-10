package com.example.mygo4lunch.viewModel;

import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;

public class MapsViewModel extends BaseViewModel {

    public MapsViewModel(RestaurantRepository restaurantRepository, WorkMatesRepository workmatesRepository) {
        this.restaurantRepository = restaurantRepository;
        this.workmatesRepository = workmatesRepository;

}
}