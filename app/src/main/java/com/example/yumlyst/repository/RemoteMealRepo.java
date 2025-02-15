package com.example.yumlyst.repository;

import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;
import com.example.yumlyst.network.APICall.RemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class RemoteMealRepo {
    //ProductLocalDataSource localDataSource;
    private static RemoteMealRepo instance = null;
    RemoteDataSource remoteDataSource;

    private RemoteMealRepo(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        //this.localDataSource = localDataSource;
    }

    public static RemoteMealRepo getInstance(RemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new RemoteMealRepo(remoteDataSource);
        }
        return instance;
    }

    public Single<CategoriesResponse> getCategories() {
        return remoteDataSource.getCategories();
    }

    public Single<AreasResponse> getAreas() {
        return remoteDataSource.getAreas();
    }

    public Single<IngredientsResponse> getIngredients() {
        return remoteDataSource.getIngredients();
    }

    public Single<MealResponse> getRandomMeal() {
        return remoteDataSource.getRandomMeal();
    }

    public Single<MealResponse> getMealsByArea(String area) {
        return remoteDataSource.getMealsByArea(area);
    }

    public Single<MealResponse> getMealsByCategory(String category) {
        return remoteDataSource.getMealsByCategory(category);
    }

    public Single<MealResponse> getMealsByIngredient(String ingredient) {
        return remoteDataSource.getMealsByIngredient(ingredient);
    }

    public Single<MealResponse> getMealById(String id) {
        return remoteDataSource.getMealById(id);
    }


}
