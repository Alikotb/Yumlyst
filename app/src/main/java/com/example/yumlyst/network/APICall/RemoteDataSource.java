package com.example.yumlyst.network.APICall;

import android.util.Log;

import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private final RetofitGetter service;
    private static RemoteDataSource instance = null;

    private RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetofitGetter.class);
    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }

    private <T> void makeRequest(Call<T> call, NetworkCallback<T> callback) {
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
    }

    public void getCategories(NetworkCallback<CategoriesResponse> callback) {
        makeRequest(service.getCategories(), callback);
    }

    public void getAreas(NetworkCallback<AreasResponse> callback) {
        makeRequest(service.getAreas(), callback);
    }

    public void getIngredients(NetworkCallback<IngredientsResponse> callback) {
        makeRequest(service.getIngredients(), callback);
    }

    public void getRandomMeal(NetworkCallback<MealResponse> callback) {
        makeRequest(service.getRandomMeal(), callback);
        Log.d("TAG", "getRandomMeal remot : "+ service);
    }


    public void getMealsByArea(String area, NetworkCallback<MealResponse> callback) {
        makeRequest(service.getMealsByArea(area), callback);
    }

    public void getMealsByCategory(String category, NetworkCallback<MealResponse> callback) {
        makeRequest(service.getMealsByCategory(category), callback);
    }

    public void getMealsByIngredient(String ingredient, NetworkCallback<MealResponse> callback) {
        makeRequest(service.getMealsByIngredient(ingredient), callback);
    }
    public void getMealById(String id, NetworkCallback<MealResponse> callback) {
        makeRequest(service.getMealById(id), callback);
    }

    public interface NetworkCallback<T> {
        void onSuccess(T response);
        void onFailure(String message);
    }
}
