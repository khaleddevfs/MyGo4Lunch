package com.example.mygo4lunch.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mygo4lunch.models.Restaurant;
import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BaseViewModel extends ViewModel {

    public final MutableLiveData<List<String>> workMatesIdMutableLiveData = new MutableLiveData<>();

    protected WorkMate workMate;

    protected WorkMatesRepository workmatesRepository;

    protected RestaurantRepository restaurantRepository;


   public LiveData<List<Restaurant>> getRestaurantList(double latitude, double longitude) {
        return restaurantRepository.getGoogleRestaurantList(latitude, longitude);
    }


    public LiveData<List<Restaurant>> getRestaurants (){
        return  restaurantRepository.restaurantListMutableLiveData;
    }

    public void fetchWorkMatesGoing(){
        List<String> workMatesToAdd = new ArrayList<>();
        workmatesRepository.getAllWorkMate()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                        if (workMate != null && workMate.getWorkMateRestaurantChoice() != null && workMate.getWorkMateRestaurantChoice(). getRestaurantId() !=null ) {
                            String restaurantUid = workMate.getWorkMateRestaurantChoice().getRestaurantId();
                            workMatesToAdd.add(restaurantUid);
                        }
                    }
                    workMatesIdMutableLiveData.setValue(workMatesToAdd);
                });
    }
}
