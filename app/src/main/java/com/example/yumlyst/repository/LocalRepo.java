package com.example.yumlyst.repository;

import android.content.Context;

import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.APICall.RemoteDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class LocalRepo {
    private static LocalRepo instance = null;
    private LocalDataSource localDataSource;
    private LocalRepo(LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static LocalRepo getInstance(LocalDataSource localDataSource) {
        if (instance == null) {
            instance = new LocalRepo(localDataSource);
        }
        return instance;
    }
    public Completable insert(LocalDTO localDTO) {
        return localDataSource.insert(localDTO);
    }
    public Completable deleteFromPlan(String userID, MealDTO meal, String day) {
        return localDataSource.deleteFromPlan(userID, meal, day);
    }
    public Completable deleteFromFavorit(String userID, MealDTO meal, String type) {
        return localDataSource.deleteFromFavorit(userID, meal, type);
    }
    public Single<List<LocalDTO>> getAllFavorit(String userID, String type) {
        return localDataSource.getAllFavorit(userID, type);
    }
    public Single<List<LocalDTO>> getAllPlanByDay(String userID, String day, String type) {
        return localDataSource.getAllPlanByDay(userID, day, type);
    }
    public Completable insertAll(List<LocalDTO> localDTO) {
        return localDataSource.insertAll(localDTO);
    }

}
