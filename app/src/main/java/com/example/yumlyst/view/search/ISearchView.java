package com.example.yumlyst.view.search;

import com.example.yumlyst.model.MealDTO;

import java.util.List;

public interface ISearchView {

    void showMealsByCategory(List<MealDTO> meals);
    void showMealsByArea(List<MealDTO> meals);
    void showMealsByIngredient(List<MealDTO> meals);
    void navigateToMealDetails(String id);
    void navigateHome();

}
