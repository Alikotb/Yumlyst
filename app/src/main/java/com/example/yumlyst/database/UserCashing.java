package com.example.yumlyst.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yumlyst.helper.Constant;

public class UserCashing {


    private static UserCashing instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private  String userId;

    private UserCashing(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized UserCashing getInstance(Context context) {
        if (instance == null) {
            instance = new UserCashing(context);
        }
        return instance;
    }

    public void cacheUser(String username, String email, String photoUrl, String userId) {
        editor.putString(Constant.KEY_USERNAME, username);
        editor.putString(Constant.KEY_EMAIL, email);
        editor.putString(Constant.KEY_PHOTO_URL, photoUrl);
        editor.putString(Constant.KEY_USER_ID, userId);
        this.userId = userId;
        editor.apply();
    }


    public boolean isUserLoggedIn() {
        return sharedPreferences.contains(Constant.KEY_USERNAME);
    }

    public String getUserId() {
        return sharedPreferences.getString(Constant.KEY_USER_ID, null);  // ✅ Retrieve userId from SharedPreferences
    }


    public void clearCache() {
        editor.clear();
        editor.apply();
    }
}
