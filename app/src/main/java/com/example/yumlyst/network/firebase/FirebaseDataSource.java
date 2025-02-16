package com.example.yumlyst.network.firebase;

import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class FirebaseDataSource {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private  static FirebaseDataSource instance;
    private FirebaseDataSource() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
    }
    public static FirebaseDataSource getInstance() {
        if (instance == null) {
            instance = new FirebaseDataSource();
        }
        return instance;
    }
    public Completable insert(LocalDTO localDTO ){
        myRef.child(localDTO.getUserID()).child(localDTO.getDay()).child(localDTO.getType()).push().setValue(localDTO);
        return null;
    }
    public Completable deleteFromPlan(String userID, MealDTO meal, String day, String type) {
        return null;
    }
    public Single<List<MealDTO>> getAllPlanByDay(String userID, String day, String type) {

        return null;
    }

}
