package com.example.yumlyst.network.APICall;

import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.model.responsemodel.AreasResponse;
import com.example.yumlyst.model.responsemodel.IngredientsResponse;
import com.example.yumlyst.model.responsemodel.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetofitGetter {

    @GET("categories.php")
    Call<CategoriesResponse> getCategories();

    @GET("list.php?a=list")
    Call<AreasResponse> getAreas();

    @GET("list.php?i=list")
    Call<IngredientsResponse> getIngredients();

    @GET("random.php")
    Call<MealResponse> getRandomMeal();


    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String id);


    @GET("filter.php")
    Call<MealResponse> getMealsByArea(@Query("a") String area);

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

    //filter.php?a=Canadian
    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

    //https://www.themealdb.com/images/ingredients/Lime-Small.png

}
