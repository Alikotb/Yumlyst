package com.example.yumlyst.ui.homescrean.presenter;


import android.content.Context;

import com.example.yumlyst.MealRepo;
import com.example.yumlyst.R;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.example.yumlyst.network.APICall.RetofitGetter;
import com.example.yumlyst.ui.homescrean.view.IHomeView;

public class HomePresenter implements IHomePresenter, RemoteDataSource.NetworkCallback {
    private IHomeView homeView;
    private MealRepo mealRepo;

    public HomePresenter(IHomeView homeView, MealRepo mealRepo) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
    }

    public void getCategories() {
        mealRepo.getCategories(this);
//        mealRepo.getCategories(new MealRepo.NetworkCallback<CategoriesResponse>() {
    }

    @Override
    public void getAreas() {
        mealRepo.getAreas(this);
    }

    @Override
    public void getIngredients() {
        mealRepo.getIngredients(this);
    }

    @Override
    public void getRandomMeal() {
        mealRepo.getRandomMeal(this);
    }

    @Override
    public void getMealsByArea(String area) {
        mealRepo.getMealsByArea(area, this);
    }


    @Override
    public void onSuccess(Object response) {
        if (response instanceof CategoriesResponse) {
            CategoriesResponse categoriesResponse = (CategoriesResponse) response;
            homeView.showCategories(categoriesResponse.getCategories());
        } else if (response instanceof AreasResponse) {
            AreasResponse areasResponse = (AreasResponse) response;
            homeView.showAreas(areasResponse.getMeals());
        } else if (response instanceof IngredientsResponse) {
            IngredientsResponse ingredientsResponse = (IngredientsResponse) response;
            homeView.showIngredients(ingredientsResponse.getMeals());
        } else if (response instanceof MealDTO) {
            MealDTO mealDTO = (MealDTO) response;
            homeView.showRandomMeal(mealDTO);
        }

    }

    @Override
    public void onFailure(String message) {
//showRandomMeal
        homeView.showError(message);
    }


}

