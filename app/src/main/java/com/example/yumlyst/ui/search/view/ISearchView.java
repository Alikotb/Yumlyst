package com.example.yumlyst.ui.search.view;

import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.IngredientDTO;
import com.example.yumlyst.model.MealDTO;

import java.util.List;

public interface ISearchView {

    void showMealsByCategory(List<MealDTO> meals);
    void showMealsByArea(List<MealDTO> meals);
    void showMealsByIngredient(List<MealDTO> meals);
    void showMealsByName(List<MealDTO> meals);
    void navigateToMealDetails(MealDTO meal);

}
