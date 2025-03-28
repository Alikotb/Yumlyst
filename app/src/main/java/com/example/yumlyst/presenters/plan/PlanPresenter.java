package com.example.yumlyst.presenters.plan;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.yumlyst.network.database.room.LocalDataSource;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.repository.FireBaseRepo;
import com.example.yumlyst.network.repository.LocalRepo;
import com.example.yumlyst.view.plan.IPlanView;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter implements IPlanPresenter {
    private IPlanView view;
    private LocalRepo localRepo;
    private FireBaseRepo fireBaseRepo;
    public PlanPresenter(IPlanView view, Context context) {
        this.localRepo = LocalRepo.getInstance(LocalDataSource.getInstance(context));
        this.view = view;
        fireBaseRepo=FireBaseRepo.getInstance();
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteFromPlan(String userID, MealDTO meal, String day) {
        localRepo.deleteFromPlan(userID, meal, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.deleteMeal(meal),
                        throwable -> {}
                );
        fireBaseRepo.deleteFromFireBase(userID,meal,day, Constant.PLAN);
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
                        },
                        throwable -> {}
                );


    }
}
