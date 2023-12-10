package com.example.mygo4lunch.injections;

import com.example.mygo4lunch.factory.Go4LunchFactory;
import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.SaveDataRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;

public class Injection {

    public static RestaurantRepository provideRestaurantRepository() {
        return RestaurantRepository.getInstance();
    }

    public static WorkMatesRepository provideWorkMateRepository() {
        return WorkMatesRepository.getINSTANCE();
    }

    public static SaveDataRepository provideSaveDataRepository() {
        return SaveDataRepository.getInstance();
    }



    public static Go4LunchFactory provideViewModelFactory() {
        RestaurantRepository restaurantRepository = provideRestaurantRepository();
        WorkMatesRepository workMatesRepository = provideWorkMateRepository();
        SaveDataRepository saveDataRepository = provideSaveDataRepository();

        return new Go4LunchFactory(restaurantRepository, workMatesRepository, saveDataRepository);
    }



}
