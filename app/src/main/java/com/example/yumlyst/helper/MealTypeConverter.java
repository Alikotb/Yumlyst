package com.example.yumlyst.helper;

import androidx.room.TypeConverter;

import com.example.yumlyst.model.MealDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MealTypeConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromMealDTO(MealDTO meal) {
        return meal == null ? null : gson.toJson(meal);
    }

    @TypeConverter
    public static MealDTO toMealDTO(String mealString) {
        if (mealString == null) return null;
        Type type = new TypeToken<MealDTO>() {}.getType();
        return gson.fromJson(mealString, type);
    }

}
