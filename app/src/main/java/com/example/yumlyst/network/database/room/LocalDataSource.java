package com.example.yumlyst.network.database.room;

import android.content.Context;
import android.util.Log;

import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class LocalDataSource {
    private static LocalDataSource instance;
    private DAO dao;

    private LocalDataSource(Context context) {
        this.dao = LocalDataBase.getInstance(context).getdao();
        //this.context = context;
    }

    public static LocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDataSource(context);
        }
        return instance;
    }

    public Completable insert(LocalDTO localDTO) {
        return dao.insert(localDTO);

    }

    public Completable insertAll(List<LocalDTO> localDTO) {
        return dao.insertAll(localDTO);

    }

    public Completable deleteFromPlan(String userID, MealDTO meal, String day) {
        return dao.deleteFromPlan(userID, meal, day);

    }


    public Completable deleteFromFavorit(String userID, MealDTO meal, String type) {
        Log.d("aaa", "deleteFromFavoritDs: "+userID);
        return dao.deleteFromFavorit(userID, meal, type);
    }


    public Single<List<LocalDTO>> getAllFavorit(String userID, String type) {
        return dao.getAllFavorit(userID, type);
    }

    public Single<List<LocalDTO>> getAllPlanByDay(String userID, String day, String type) {
        return dao.getAllPlanByDay(userID, day, type);
    }

}
