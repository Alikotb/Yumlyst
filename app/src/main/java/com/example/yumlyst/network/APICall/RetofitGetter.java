package com.example.yumlyst.network.APICall;

import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetofitGetter {

    @GET("categories.php")
    Single<CategoriesResponse> getCategories();

    @GET("list.php?a=list")
    Single<AreasResponse> getAreas();

    @GET("list.php?i=list")
    Single<IngredientsResponse> getIngredients();

    @GET("random.php")
    Single<MealResponse> getRandomMeal();


    @GET("lookup.php")
    Single<MealResponse> getMealById(@Query("i") String id);


    @GET("filter.php")
    Single<MealResponse> getMealsByArea(@Query("a") String area);

    @GET("filter.php")
    Single<MealResponse> getMealsByCategory(@Query("c") String category);

    //filter.php?a=Canadian
    @GET("filter.php")
    Single<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

}
