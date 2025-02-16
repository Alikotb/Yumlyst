package com.example.yumlyst.ui.planscrean.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.ui.favoritescrean.view.IFavoriteView;
import com.example.yumlyst.ui.planscrean.view.IPlanView;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter implements IPlanPresenter {
    private IPlanView view;
    private LocalRepo localRepo;
    public PlanPresenter(LocalRepo localRepo, IPlanView view) {
        this.localRepo = localRepo;
        this.view = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteFromPlan(String userID, MealDTO meal, String day) {
        localRepo.deleteFromPlan(userID, meal, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.deleteMeal(meal),
                        throwable -> view.deleteMeal(null)
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllPlanByDay(String userID, String day, String type) {
        localRepo.getAllPlanByDay(userID, day, type)
                .map(localDTOList ->
                        localDTOList.stream()
                                .map(LocalDTO::getMeal)
                                .collect(Collectors.toList())
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            view.showMeals(meals);
                            Log.d("sss", "hamda : " +meals.size());
                        },
                        throwable -> view.showMeals(null)
                );


    }
}
