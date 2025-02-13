package com.example.yumlyst.network.APICall;

import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static RemoteDataSource instance = null;
    private final RetofitGetter service;

    private RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(RetofitGetter.class);
    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }

    /*private <T> void makeRequest(Call<T> call, NetworkCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("API request failed or response body is null");
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }*/

    public Single<CategoriesResponse> getCategories() {
        return service.getCategories();
    }

    public Single<AreasResponse> getAreas() {
        return service.getAreas();
    }

    public Single<IngredientsResponse> getIngredients() {
        return service.getIngredients();
    }

    public Single<MealResponse> getRandomMeal() {
        return service.getRandomMeal();
        //Log.d("TAG", "getRandomMeal remot : "+ service);
    }


    public Single<MealResponse> getMealsByArea(String area) {
        return service.getMealsByArea(area);
    }

    public Single<MealResponse> getMealsByCategory(String category) {
        return service.getMealsByCategory(category);
    }

    public Single<MealResponse> getMealsByIngredient(String ingredient) {
        return service.getMealsByIngredient(ingredient);
    }

    public Single<MealResponse> getMealById(String id) {
        return service.getMealById(id);
    }


}
