package com.example.yumlyst.ui.mealdetails.presenter;

import android.annotation.SuppressLint;

import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.repository.FireBaseRepo;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.repository.RemoteMealRepo;
import com.example.yumlyst.model.MealDTO;

import com.example.yumlyst.ui.mealdetails.view.IDetailsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenter implements IDetailsPresenter{
    private IDetailsView detailsView;
    private RemoteMealRepo mealRepo;
    private LocalRepo localRepo;
    FireBaseRepo fireBaseRepo;

    public DetailsPresenter(IDetailsView detailsView, RemoteMealRepo mealRepo,LocalRepo localRepo) {
        this.detailsView = detailsView;
        this.mealRepo = mealRepo;
        this.localRepo=localRepo;
        fireBaseRepo=FireBaseRepo.getInstance();

    }
    @SuppressLint("CheckResult")
    @Override
    public void getMealDetails(String id) {
        mealRepo.getMealById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> detailsView.showMealDetails(item.getMeals().get(0))
                );

    }
    @Override
    public void insert(LocalDTO localDTO){
        localRepo.insert(localDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        fireBaseRepo.insert(localDTO);
    }



}
