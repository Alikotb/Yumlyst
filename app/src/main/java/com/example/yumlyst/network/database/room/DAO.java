package com.example.yumlyst.network.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.model.MealDTO;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(LocalDTO localDTO);

    @Query("SELECT * FROM LocalDTO WHERE userID = :userID AND type = :type")
    Single <List<LocalDTO>> getAllFavorit(String userID, String type);

    @Query("SELECT * FROM LocalDTO WHERE userID = :userID AND day = :day AND type = :type")
    Single <List<LocalDTO>> getAllPlanByDay(String userID, String day, String type);


    @Query("DELETE FROM LocalDTO WHERE userID = :userID AND meal = :meal AND day = :day")
    Completable deleteFromPlan(String userID, MealDTO meal, String day);

    @Query("DELETE FROM LocalDTO WHERE userID = :userID AND type = :type")
    Completable deleteAllPlan(String userID, String type);

    @Query("DELETE FROM LocalDTO WHERE userID = :userID AND meal = :meal AND type = :type")
    Completable deleteFromFavorit(String userID, MealDTO meal, String type);

    @Query("DELETE FROM LocalDTO WHERE userID = :userID AND type = :type")
    Completable deleteAllFavorit(String userID, String type);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<LocalDTO> localDTO);

}
