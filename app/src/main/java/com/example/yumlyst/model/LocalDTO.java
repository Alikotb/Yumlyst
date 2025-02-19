package com.example.yumlyst.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.yumlyst.helper.MealTypeConverter;

import java.io.Serializable;

@Entity(tableName = "LocalDTO", primaryKeys = {"userID", "meal", "day", "type"})
public class LocalDTO implements Serializable {
    @NonNull
    private String day;
    @NonNull
    private String userID;
    @NonNull
    private MealDTO meal;
    @NonNull
    private String type;

    public LocalDTO() {
    }

    public LocalDTO(String day, String UserID, MealDTO Meal, String tupe) {
        this.day = day;
        this.userID = UserID;
        this.meal = Meal;
        this.type = tupe;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public MealDTO getMeal() {
        return meal;
    }

    public void setMeal(MealDTO meal) {
        this.meal = meal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
