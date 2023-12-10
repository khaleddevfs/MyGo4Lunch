package com.example.mygo4lunch.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mygo4lunch.models.Restaurant;
import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsViewModel extends BaseViewModel {

    private final String TAG = RestaurantDetailsViewModel.class.getSimpleName();

    private final RestaurantRepository restaurantRepository;

    public Restaurant restaurant;


    public final MutableLiveData<List<WorkMate>> mWorkMateList = new MutableLiveData<>();

    public MutableLiveData<Boolean> isRestaurantLiked = new MutableLiveData<>();

    public MutableLiveData<Boolean> isRestaurantPicked = new MutableLiveData<>();

    public RestaurantDetailsViewModel(RestaurantRepository restaurantRepository, WorkMatesRepository workmatesRepository) {
        this.restaurantRepository = restaurantRepository;
        this.workmatesRepository = workmatesRepository;

        workMate = workmatesRepository.getActualUser();
    }



    public void fetchInfoRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
        fetchWorkMatesGoing();
        isRestaurantLiked.setValue(checkIfRestaurantIsLiked());
        if (workMate.getWorkMateRestaurantChoice() != null) {
            String uidSelection = workMate.getWorkMateRestaurantChoice().getRestaurantId();
            String restaurantId = restaurant.getRestaurantID();
            isRestaurantPicked.setValue(uidSelection.equals(restaurantId));
        } else {
            isRestaurantPicked.setValue(false);
        }

    }
    public void updateRestaurantLiked(Restaurant restaurant) {
        if (isRestaurantLiked.getValue()) {
            isRestaurantLiked.setValue(false);
            workmatesRepository.removeLikedRestaurant(restaurant.getRestaurantID())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant disliked"));
        } else {
            isRestaurantLiked.setValue(true);
            workmatesRepository.addLikedRestaurant(restaurant.getRestaurantID())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant liked"));
        }
    }

    public void updateRestaurantPiked(Restaurant restaurant) {

        if (isRestaurantPicked.getValue()) {
            isRestaurantPicked.setValue(false);
            workmatesRepository.updateRestaurantPicked(null, null, null, workMate.getUid())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant unselected"));
        } else {
            isRestaurantPicked.setValue(true);
            workmatesRepository.updateRestaurantPicked(restaurant.getRestaurantID(), restaurant.getName(),
                            restaurant.getAddress(), workMate.getUid())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant selected"));
        }
        fetchUsersGoing();
    }


    public void fetchWorkMateLike(Restaurant restaurant){

        if (workMate != null && workMate.getLikedRestaurants() != null) {
            List<String> likedRestaurant = workMate.getLikedRestaurants();
            String restaurantUid = restaurant.getRestaurantID();
            if (likedRestaurant != null && restaurantUid != null && likedRestaurant.contains(restaurantUid)) {
                isRestaurantLiked.setValue(true);
            }
        }
    }

    private void fetchUsersGoing() {

        List<WorkMate> workMateToAdd = new ArrayList<>();
        workmatesRepository.getAllWorkMate()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                        if (workMate != null && workMate.getWorkMateRestaurantChoice() != null && workMate.getWorkMateRestaurantChoice().getRestaurantId() != null) {
                            String restaurantUid = workMate.getWorkMateRestaurantChoice().getRestaurantId();
                            if (restaurantUid.equals(restaurant.getRestaurantID())) {
                                workMateToAdd.add(workMate);
                            }
                        }
                    }
                    restaurant.setWorkMatesGoingEating(workMateToAdd);
                    mWorkMateList.setValue(restaurant.getWorkMatesEatingHere());
                });
    }


    private Boolean checkIfRestaurantIsLiked() {
        List<String> restaurantLiked = workMate.getLikedRestaurants();
        if (restaurantLiked != null && restaurantLiked.size() > 0) {
            for (String uid : restaurantLiked) {
                if (uid.equals(restaurant.getRestaurantID())) return true;
            }
        }
        return false;
    }
}
