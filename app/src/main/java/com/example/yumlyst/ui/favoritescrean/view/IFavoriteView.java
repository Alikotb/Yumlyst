package com.example.yumlyst.ui.favoritescrean.view;

import com.example.yumlyst.model.MealDTO;

import java.util.List;

public interface IFavoriteView {
    void showMeals(List<MealDTO> meals);
    void deleteMeal(MealDTO meal);
}
