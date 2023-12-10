package com.example.mygo4lunch.repositories;

import com.example.mygo4lunch.models.WorkMate;
import com.example.mygo4lunch.models.WorkMateRestaurantChoice;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class WorkMatesRepository {

    private static final String COLLECTION_NAME ="workMate";

    private final CollectionReference workMateCollection;

    private WorkMate workMate;

    private static volatile WorkMatesRepository INSTANCE;

    public static WorkMatesRepository getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new WorkMatesRepository();
        }
        return INSTANCE;
    }



    public WorkMatesRepository(){
        this.workMateCollection = getUsersCollection();
    }

    public WorkMate getActualUser(){
        return workMate;
    }

    private CollectionReference getUsersCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public Task<Void> createWorkmate(WorkMate workMate) {
        this.workMate = workMate;
        return workMateCollection.document(workMate.getUid()).set(workMate);
    }


    public Task<DocumentSnapshot> getWorkMateFromFirebase(String uid) {
        return workMateCollection.document(uid).get();
    }

    public Task<QuerySnapshot> getAllWorkMate() {
        return workMateCollection.get();
    }

    public Task<Void> updateRestaurantPicked(String id, String name, String address, String userUid) {
        WorkMateRestaurantChoice choice = new WorkMateRestaurantChoice(id, name, address, Timestamp.now());
        workMate.setWorkMateRestaurantChoice(choice);
        WorkMateRestaurantChoice choiceToCreate = (id != null) ? choice : null;
        return workMateCollection.document(userUid).update("workMateRestaurantChoice", choiceToCreate);
    }


    public Task<Void> addLikedRestaurant(String likedRestaurant) {
        workMate.addLikedRestaurant(likedRestaurant);
        return updateLikedRestaurants(workMate.getUid());
    }

    public Task<Void> removeLikedRestaurant(String likedRestaurant){
        workMate.removeLikedRestaurant((likedRestaurant));
        return updateLikedRestaurants(workMate.getUid());
    }

    private Task<Void> updateLikedRestaurants(String uid) {
        List<String> likedRestaurantList = workMate.getLikedRestaurants();
        return workMateCollection.document(uid).update("likedRestaurants", likedRestaurantList);
    }

    public void updateCurrentUser(WorkMate workMate){
        this.workMate = workMate;
    }


}
