package com.example.yumlyst.network.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.yumlyst.helper.MealTypeConverter;
import com.example.yumlyst.model.LocalDTO;

@Database(entities = {LocalDTO.class}, version = 2)
@TypeConverters({MealTypeConverter.class})  // Register the converter
public abstract class LocalDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "LocalDataBase";
    private static LocalDataBase instance;

    public static synchronized LocalDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), LocalDataBase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract DAO getdao();

}
