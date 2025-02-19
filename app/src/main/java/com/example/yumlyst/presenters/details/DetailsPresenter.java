package com.example.yumlyst.presenters.details;

import android.annotation.SuppressLint;

import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.network.repository.FireBaseRepo;
import com.example.yumlyst.network.repository.LocalRepo;
import com.example.yumlyst.network.repository.RemoteMealRepo;

import com.example.yumlyst.view.details.IDetailsView;

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
                        item -> detailsView.showMealDetails(item.getMeals().get(0)),
                        asd->{}
                );

    }
    @SuppressLint("CheckResult")
    @Override
    public void insert(LocalDTO localDTO){
        localRepo.insert(localDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()->{},
                        error->{}
                );


        fireBaseRepo.insert(localDTO);
    }



}
