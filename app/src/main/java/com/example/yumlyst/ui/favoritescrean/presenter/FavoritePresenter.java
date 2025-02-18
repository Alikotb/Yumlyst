package com.example.yumlyst.ui.favoritescrean.presenter;

import android.annotation.SuppressLint;

import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.repository.FireBaseRepo;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.ui.favoritescrean.view.IFavoriteView;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoritePresenter implements IFavoritePresenter{
    private LocalRepo localRepo;
    private IFavoriteView view;
    private FireBaseRepo fireBaseRepo;

    public FavoritePresenter(LocalRepo localRepo, IFavoriteView view) {
        this.localRepo = localRepo;
        this.view = view;
        fireBaseRepo=FireBaseRepo.getInstance();
    }



    @SuppressLint("CheckResult")
    @Override
    public void getMeals(String userID, String type) {
        localRepo.getAllFavorit(userID, type)
                .map(localDTOList ->
                        localDTOList.stream()
                                .map(LocalDTO::getMeal)
                                .collect(Collectors.toList())
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.showMeals(meals),
                        throwable -> {}
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteMeal(String id,MealDTO meal) {
        localRepo.deleteFromFavorit(id,meal,"f")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.deleteMeal(meal),
                        throwable -> {}
                );
        fireBaseRepo.deleteFromFireBase(id,meal,Constant.FIREBASE_FAVORITE, Constant.FAVORITE);
    }
}
