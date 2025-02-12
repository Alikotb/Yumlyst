package com.example.yumlyst.ui.homescrean.presenter;

public interface IHomePresenter {
    void getCategories();

    void getAreas();

    void getIngredients();

    void getRandomMeal();

    void getMealsByArea(String area);
}
