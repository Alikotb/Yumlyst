package com.example.yumlyst.ui.mealdetails.presenter;

import com.example.yumlyst.database.MealRepo;
import com.example.yumlyst.model.MealDTO;

import com.example.yumlyst.ui.mealdetails.view.IDetailsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter {
    private IDetailsView detailsView;
    private MealRepo mealRepo;
    private MealDTO meal;
    private String id;
    public DetailsPresenter(IDetailsView detailsView, MealRepo mealRepo) {
        this.detailsView = detailsView;
        this.mealRepo = mealRepo;
    }
    public void getMealDetails(String id) {
        mealRepo.getMealById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> detailsView.showMealDetails(item.getMeals().get(0))
                );

    }


}
