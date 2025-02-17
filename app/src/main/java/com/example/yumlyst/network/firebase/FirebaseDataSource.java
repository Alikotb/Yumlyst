package com.example.yumlyst.network.firebase;

import android.content.Context;
import android.widget.Toast;

import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class FirebaseDataSource {
    private  FirebaseDatabase database;
    private  DatabaseReference myRef;
    private  static FirebaseDataSource instance;
    private FirebaseDataSource() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Constant.FIREBASE_REF);
    }
    public static FirebaseDataSource getInstance() {
        if (instance == null) {
            instance = new FirebaseDataSource();
        }
        return instance;
    }

    public  void insert(LocalDTO localDTO ){
        myRef.child(localDTO.getUserID())
                .child(localDTO.getType())
                .child(localDTO.getDay())
                .child(localDTO.getMeal().getIdMeal())
                .setValue(localDTO.getMeal());

    }
    public void deleteFromFireBase( String userID, MealDTO meal, String day, String type) {
        myRef.child(userID)
                .child(type)
                .child(day)
                .child(meal.getIdMeal())
                .removeValue();
    }

    public DatabaseReference getAllPlansForUser(String userID, String type) {
        return myRef.child(userID)
                .child(type);
    }
    public DatabaseReference getFavorite(String userID, String type) {

        return myRef.child(userID)
                .child(type);
    }

}
