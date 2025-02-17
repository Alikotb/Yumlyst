package com.example.yumlyst.ui.homescrean.presenter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.IngredientDTO;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.repository.FireBaseRepo;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.repository.RemoteMealRepo;
import com.example.yumlyst.ui.homescrean.view.IHomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements IHomePresenter {
    private FirebaseAuth auth;
    private IHomeView homeView;
    private RemoteMealRepo mealRepo;
    private List<CategoryDTO> categories = new ArrayList<>();
    private List<AreaDTO> areas = new ArrayList<>();
    private List<IngredientDTO> ingredients = new ArrayList<>();
    private LocalRepo localRepo;
    private List<LocalDTO> localDTOS = new ArrayList<>();


    public HomePresenter(IHomeView homeView, RemoteMealRepo mealRepo, Context context) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
        auth = FirebaseAuth.getInstance();
        localRepo = LocalRepo.getInstance(LocalDataSource.getInstance(context));
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
                        },
                        error->{}
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
                        },
                        error->{}

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
                        },
                        error->{}

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
    public void insertAllPlansFromFirebase(String userID, String type) {
        FireBaseRepo.getInstance().getAllPlansForUser(userID, type)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                        for (DataSnapshot daySnapshot : snapshot.getChildren()) {
                            String dateDay = daySnapshot.getKey();
                            for (DataSnapshot mealSnapshot : daySnapshot.getChildren()) {
                                MealDTO meal = mealSnapshot.getValue(MealDTO.class);
                                localDTOS.add(new LocalDTO(dateDay, userID, meal, type));
                            }
                        }
                        localRepo.insertAll(localDTOS).subscribeOn(Schedulers.io()).subscribe();
                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                        Log.e("TAG", "onCancelled: Firebase request canceled - " + error.getMessage());
                    }
                });


    }

    @Override
    public void insertAllFavoriteFromFirebase(String userID,String type) {
        FireBaseRepo.getInstance().getFavorite(userID, type)
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                        for (DataSnapshot daySnapshot : snapshot.getChildren()) {
                            for (DataSnapshot mealSnapshot : daySnapshot.getChildren()) {
                                MealDTO meal = mealSnapshot.getValue(MealDTO.class);
                                localDTOS.add(new LocalDTO(Constant.FIREBASE_FAVORITE, userID, meal,Constant.FAVORITE ));
                            }
                        }
                       // insertall(localDTOS);
                        localRepo.insertAll(localDTOS).subscribeOn(Schedulers.io()).subscribe(
                                ()->{},
                                error->{}

                        );
                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                        Log.e("TAG", "onCancelled: Firebase request canceled - " + error.getMessage());
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

