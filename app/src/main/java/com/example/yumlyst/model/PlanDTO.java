package com.example.yumlyst.model;

import java.util.List;

public class PlanDTO {
    private String day;
    private List<String> meals;
    private String userId;
    private String planId;

    public PlanDTO( ) {
    }

    public PlanDTO(String day, List<String> meals, String userId, String planId) {
        this.day = day;
        this.meals = meals;
        this.userId = userId;
        this.planId = planId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<String> getMeals() {
        return meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}
