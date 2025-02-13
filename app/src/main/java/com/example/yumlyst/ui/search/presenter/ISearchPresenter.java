package com.example.yumlyst.ui.search.presenter;

public interface ISearchPresenter {
    void getMealByCategory(String category);
    void getMealByArea(String area);
    void getMealByIngredient(String ingredient);
    void getMealByName(String name);
}
