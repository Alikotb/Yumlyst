package com.example.yumlyst.presenters.plan;

import com.example.yumlyst.model.MealDTO;

public interface IPlanPresenter {
    void deleteFromPlan(String userID, MealDTO meal, String day);

    void getAllPlanByDay(String userID, String day, String type);
}
