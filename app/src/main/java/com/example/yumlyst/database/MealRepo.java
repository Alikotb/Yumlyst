package com.example.yumlyst.database;

import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;
import com.example.yumlyst.network.APICall.RemoteDataSource;

import io.reactivex.rxjava3.core.Single;

public class MealRepo {
    //ProductLocalDataSource localDataSource;
    private static MealRepo instance = null;
    RemoteDataSource remoteDataSource;

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
        //Log.d("TAG", "getRandomMeal: "+ remoteDataSource);
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
