package com.example.yumlyst.repository;

import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.firebase.FirebaseDataSource;
import com.google.firebase.database.DatabaseReference;

public class FireBaseRepo {
    private FirebaseDataSource firebaseDatastore;
    private static FireBaseRepo instance;
    private FireBaseRepo() {
        firebaseDatastore = FirebaseDataSource.getInstance();
    }
    public static FireBaseRepo getInstance() {
        if (instance == null) {
            instance = new FireBaseRepo();
        }
        return instance;
    }



    public  void insert(LocalDTO localDTO ){
        firebaseDatastore.insert(localDTO);
    }
    public void deleteFromFireBase(String userID, MealDTO meal, String day, String type) {
            firebaseDatastore.deleteFromFireBase(userID, meal, day, type);
    }
    public DatabaseReference getAllPlansForUser(String userID, String type) {
        return firebaseDatastore.getAllPlansForUser(userID, type);
    }
    public DatabaseReference getFavorite(String userID, String type) {
        return firebaseDatastore.getFavorite(userID,type);
    }

}
