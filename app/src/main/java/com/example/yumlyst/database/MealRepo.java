package com.example.yumlyst.database;

import android.util.Log;

import com.example.yumlyst.network.APICall.RemoteDataSource;

public class MealRepo {
    RemoteDataSource remoteDataSource;
    //ProductLocalDataSource localDataSource;
    private static MealRepo instance = null;

    private MealRepo(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        //this.localDataSource = localDataSource;
    }

    public static MealRepo getInstance(RemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new MealRepo(remoteDataSource);
        }
        return instance;
    }
    public void getCategories(RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getCategories(callback);
    }
    public void getAreas(RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getAreas(callback);
    }
    public void getIngredients(RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getIngredients(callback);
    }
    public void getRandomMeal(RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getRandomMeal(callback);
        Log.d("TAG", "getRandomMeal: "+ remoteDataSource);
    }
    public void getMealsByArea(String area, RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getMealsByArea(area, callback);
    }
    public void getMealsByCategory(String category, RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getMealsByCategory(category, callback);
    }
    public void getMealsByIngredient(String ingredient, RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getMealsByIngredient(ingredient, callback);
    }
    public void getMealById(String id, RemoteDataSource.NetworkCallback callback) {
        remoteDataSource.getMealById(id, callback);
    }






}
