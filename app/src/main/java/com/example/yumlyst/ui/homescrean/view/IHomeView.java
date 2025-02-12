package com.example.yumlyst.ui.homescrean.view;

import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.IngredientDTO;
import com.example.yumlyst.model.MealDTO;

import java.util.List;

public interface IHomeView {
    void showCategories(List<CategoryDTO> categories);

    void showAreas(List<AreaDTO> areas);

    void showIngredients(List<IngredientDTO> ingredients);

    void showRandomMeal(List<MealDTO> randomMeal);
    void showPhotoUrl(String str);


    void navigateToMealDetails(MealDTO meal);
}
