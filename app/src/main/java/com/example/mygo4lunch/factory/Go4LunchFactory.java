package com.example.mygo4lunch.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.SaveDataRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;
import com.example.mygo4lunch.viewModel.LoginViewModel;
import com.example.mygo4lunch.viewModel.MainViewModel;
import com.example.mygo4lunch.viewModel.MapsViewModel;
import com.example.mygo4lunch.viewModel.RestaurantDetailsViewModel;
import com.example.mygo4lunch.viewModel.RestaurantViewModel;
import com.example.mygo4lunch.viewModel.SettingsViewModel;
import com.example.mygo4lunch.viewModel.WorkMateViewModel;

public class Go4LunchFactory implements ViewModelProvider.Factory {


    private final RestaurantRepository restaurantRepository;

    private final WorkMatesRepository workmatesRepository;

    private final SaveDataRepository saveDataRepository;


    public Go4LunchFactory(RestaurantRepository restaurantRepository, WorkMatesRepository workmatesRepository, SaveDataRepository saveDataRepository) {
        this.restaurantRepository = restaurantRepository;
        this.workmatesRepository = workmatesRepository;
        this.saveDataRepository = saveDataRepository;

    }


    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel (workmatesRepository);
        }

        if (modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel (workmatesRepository);
        }
        if (modelClass.isAssignableFrom(MapsViewModel.class)) {
            return (T) new MapsViewModel (restaurantRepository, workmatesRepository);
        }

        if (modelClass.isAssignableFrom(SettingsViewModel.class)) {
            return (T) new SettingsViewModel(saveDataRepository, workmatesRepository);
        }

        if (modelClass.isAssignableFrom(RestaurantViewModel.class)) {
            return (T) new RestaurantViewModel(restaurantRepository, workmatesRepository);
        }

        if (modelClass.isAssignableFrom(RestaurantDetailsViewModel.class)) {
            return (T) new RestaurantDetailsViewModel(restaurantRepository, workmatesRepository);
        }

        if (modelClass.isAssignableFrom(WorkMateViewModel.class)) {
            return (T) new WorkMateViewModel(workmatesRepository, restaurantRepository);
        }


        throw new IllegalArgumentException("Problem with ViewModelFactory");

}


}


