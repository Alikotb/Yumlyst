package com.example.yumlyst.model.responsemodel;

import com.example.yumlyst.model.MealDTO;

import java.util.List;

public class MealResponse {
    private List<MealDTO> meals;

    public List<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDTO> meals) {
        this.meals = meals;
    }
}
