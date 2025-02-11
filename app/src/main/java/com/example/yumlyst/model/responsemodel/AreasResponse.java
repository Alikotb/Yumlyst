package com.example.yumlyst.model.responsemodel;

import com.example.yumlyst.model.AreaDTO;

import java.util.List;

public class AreasResponse {
    private List<AreaDTO> meals;

    public List<AreaDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<AreaDTO> meals) {
        this.meals = meals;
    }
}