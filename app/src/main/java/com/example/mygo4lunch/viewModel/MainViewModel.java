package com.example.mygo4lunch.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.repositories.WorkMatesRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainViewModel extends ViewModel {

    private final WorkMatesRepository workmatesRepository;

    public MutableLiveData<String> selectedRestaurantId = new MutableLiveData<>();

    public MainViewModel(WorkMatesRepository workmatesRepository) {
        this.workmatesRepository = workmatesRepository;
    }

    public void getSelectedRestaurant() {

        String uid = (getCurrentUser() != null) ? getCurrentUser().getUid() : "default";
        workmatesRepository.getWorkMateFromFirebase(uid)
                .addOnSuccessListener(documentSnapshot -> {
                    WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                    if (workMate != null && workMate.getWorkMateRestaurantChoice() != null) {
                        selectedRestaurantId.setValue(workMate.getWorkMateRestaurantChoice().getRestaurantId());
                    }
                });

    }

    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }


}
