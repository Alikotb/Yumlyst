package com.example.yumlyst.model;

import java.io.Serializable;
import java.util.List;

public class FavoriteDTO implements Serializable {
    private List<String> meals;
    private String userId;

    public FavoriteDTO() {
    }

    public FavoriteDTO(List<String> meals, String userId) {
        this.meals = meals;
        this.userId = userId;
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
}
