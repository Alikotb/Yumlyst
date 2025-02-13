package com.example.yumlyst.ui.homescrean.presenter;


import android.util.Log;

import com.example.yumlyst.database.MealRepo;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.ui.homescrean.view.IHomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements IHomePresenter {
    FirebaseAuth auth;
    private IHomeView homeView;
    private MealRepo mealRepo;

    public HomePresenter(IHomeView homeView, MealRepo mealRepo) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
        auth = FirebaseAuth.getInstance();
    }

    public void getCategories() {
        mealRepo.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> homeView.showCategories(item.getCategories())
                );

//        mealRepo.getCategories(new MealRepo.NetworkCallback<CategoriesResponse>() {
    }

    @Override
    public void getAreas() {
        mealRepo.getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> homeView.showAreas(item.getMeals())
                );
    }

    @Override
    public void getIngredients() {
        mealRepo.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> homeView.showIngredients(item.getMeals())
                );
        ;
        ;
    }

    @Override
    public void getRandomMeal() {
        mealRepo.getRandomMeal()
                .repeat(7)
                .flatMapIterable(x -> x.getMeals())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(new SingleObserver<List<MealDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<MealDTO> mealDTOS) {
                        homeView.showRandomMeal(mealDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

        Log.d("TAG", "getRandomMeal: ");
    }

    public void getPhotoUrl() {
        FirebaseUser user = auth.getCurrentUser();
        homeView.showPhotoUrl((user != null && user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null);
    }


}

