package com.example.yumlyst.ui.favoritescrean.presenter;

import com.example.yumlyst.model.MealDTO;

public interface IFavoritePresenter {
    void getMeals(String userID, String type);
    void deleteMeal(String id,MealDTO meal);

}
