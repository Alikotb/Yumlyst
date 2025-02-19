package com.example.yumlyst.presenters.search;

import android.util.Log;

import com.example.yumlyst.network.repository.RemoteMealRepo;
import com.example.yumlyst.model.responsemodel.MealResponse;
import com.example.yumlyst.view.search.ISearchView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter implements ISearchPresenter {
    private ISearchView searchView;
    private RemoteMealRepo mealRepo;

    public SearchPresenter(ISearchView searchView, RemoteMealRepo mealRepo) {
        this.searchView = searchView;
        this.mealRepo = mealRepo;
    }

    @Override
    public void getMealByCategory(String category) {
        mealRepo.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onSuccess(@NonNull MealResponse mealResponse) {
                            searchView.showMealsByCategory(mealResponse.getMeals());
                            Log.d("TAG", "onSuccess: " + mealResponse.getMeals().size());
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void getMealByArea(String area) {
        mealRepo.getMealsByArea(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onSuccess(@NonNull MealResponse mealResponse) {
                        searchView.showMealsByArea(mealResponse.getMeals());
                        Log.d("TAG", "onSuccess: " + mealResponse.getMeals().size());
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void getMealByIngredient(String ingredient) {
        mealRepo.getMealsByIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onSuccess(@NonNull MealResponse mealResponse) {
                        if (mealResponse.getMeals() != null) {
                            searchView.showMealsByIngredient(mealResponse.getMeals());
                        } else {
                            searchView.showMealsByIngredient(new ArrayList<>()); // تجنب تمرير null
                        }

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    }



