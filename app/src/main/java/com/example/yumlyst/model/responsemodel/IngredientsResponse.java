package com.example.yumlyst.model.responsemodel;

import com.example.yumlyst.model.IngredientDTO;

import java.util.List;

public class IngredientsResponse {
    private List<IngredientDTO> meals;

    public List<IngredientDTO> getMeals() {
        return meals;
    }
}
