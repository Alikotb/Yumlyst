package com.example.yumlyst.ui.homescrean.presenter;

public interface IHomePresenter {
    void getCategories();

    void getAreas();

    void getIngredients();

    void getRandomMeal();

    void getPhotoUrl();

    void searchCategory(String query);

    void searchArea(String query);

    void searchIngredient(String query);


}
