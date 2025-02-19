package com.example.yumlyst.view.plan;

import com.example.yumlyst.model.MealDTO;

import java.util.List;

public interface IPlanView {
    void showMeals(List<MealDTO> meals);
    void deleteMeal(MealDTO meal);
}
