package com.example.yumlyst.ui.planscrean.view;

import com.example.yumlyst.model.MealDTO;

import java.util.List;

public interface IPlanView {
    void showMeals(List<MealDTO> meals);
    void deleteMeal(MealDTO meal);
}
