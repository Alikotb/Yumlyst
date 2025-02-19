package com.example.yumlyst.presenters.search;

public interface ISearchPresenter {
    void getMealByCategory(String category);
    void getMealByArea(String area);
    void getMealByIngredient(String ingredient);
}
