package com.example.yumlyst.ui.homescrean.presenter;


import android.annotation.SuppressLint;

import com.example.yumlyst.database.MealRepo;
import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.IngredientDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.ui.homescrean.view.IHomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements IHomePresenter {
    FirebaseAuth auth;
    private IHomeView homeView;
    private MealRepo mealRepo;
    private List<CategoryDTO> categories = new ArrayList<>();
    private List<AreaDTO> areas = new ArrayList<>();
    private List<IngredientDTO> ingredients = new ArrayList<>();


    public HomePresenter(IHomeView homeView, MealRepo mealRepo) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
        auth = FirebaseAuth.getInstance();
    }

    @SuppressLint("CheckResult")
    public void getCategories() {
        mealRepo.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            homeView.showCategories(item.getCategories());
                            categories.addAll(item.getCategories());
                        }
                );

    }

    @SuppressLint("CheckResult")
    @Override
    public void getAreas() {
        mealRepo.getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            homeView.showAreas(item.getMeals());
                            areas.addAll(item.getMeals());
                        }
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void getIngredients() {
        mealRepo.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item -> {
                            homeView.showIngredients(item.getMeals());
                            ingredients.addAll(item.getMeals());
                        }
                );

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
    }
/*searchArea(String query);
    void searchIngredient(String query);*/

    @Override
    public void searchIngredient(String query) {
        Observable<IngredientDTO> ingrediantObservable = Observable.fromIterable(ingredients);
        ingrediantObservable
                .filter(item -> item.getStrIngredient().toLowerCase().contains(query.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<IngredientDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<IngredientDTO> ingredientDTOS) {
                        homeView.filterIngresiants(ingredientDTOS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    @Override
    public void searchArea(String query) {

        Observable<AreaDTO> areaObservable = Observable.fromIterable(areas);
        areaObservable
                .filter(item -> item.getStrArea().toLowerCase().contains(query.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<AreaDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<AreaDTO> areaDTOS) {
                        homeView.filterAreas(areaDTOS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    @Override
    public void searchCategory(String query) {
        Observable<CategoryDTO> myObservable = Observable.fromIterable(categories);
        myObservable
                .filter(item -> item.getStrCategory().toLowerCase().contains(query.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CategoryDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<CategoryDTO> categoryDTOS) {
                        homeView.filterCategories(categoryDTOS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    public void getPhotoUrl() {
        FirebaseUser user = auth.getCurrentUser();
        homeView.showPhotoUrl((user != null && user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null);
    }


}

