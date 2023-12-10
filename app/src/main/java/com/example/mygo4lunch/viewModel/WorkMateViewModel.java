package com.example.mygo4lunch.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class WorkMateViewModel extends BaseViewModel {

    private MutableLiveData<List<WorkMate>> workMates = new MutableLiveData<>();

    public LiveData<List<WorkMate>> getWorkMates(){
        return workMates;
    }

    public WorkMateViewModel(WorkMatesRepository workmatesRepository, RestaurantRepository restaurantRepository) {
        this.workmatesRepository = workmatesRepository;
        this.restaurantRepository = restaurantRepository;
        workMate = workmatesRepository.getActualUser();
    }

    public void fetchListUsersFromFirebase(){
        workmatesRepository.getAllWorkMate().addOnSuccessListener(queryDocumentSnapshots -> {
            List<WorkMate> fetchedUsers = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                WorkMate userFetched = documentSnapshot.toObject(WorkMate.class);
                fetchedUsers.add(userFetched);
            }
            workMates.setValue(fetchedUsers);
        });
    }
}
