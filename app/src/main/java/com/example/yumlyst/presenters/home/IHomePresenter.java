package com.example.yumlyst.presenters.home;

public interface IHomePresenter {
    void getCategories();

    void getAreas();

    void getIngredients();

    void getRandomMeal();

    void getPhotoUrl();

    void searchCategory(String query);

    void searchArea(String query);

    void searchIngredient(String query);

    void insertAllPlansFromFirebase(String userID, String type);

    void insertAllFavoriteFromFirebase(String userID, String type);


}
