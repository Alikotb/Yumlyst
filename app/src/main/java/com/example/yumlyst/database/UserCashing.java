package com.example.yumlyst.database;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCashing {

    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHOTO_URL = "photoUrl";
    private static final String KEY_USER_ID = "userId";

    private static UserCashing instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private  String userId;

    private UserCashing(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized UserCashing getInstance(Context context) {
        if (instance == null) {
            instance = new UserCashing(context);
        }
        return instance;
    }

    public void cacheUser(String username, String email, String photoUrl, String userId) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHOTO_URL, photoUrl);
        editor.putString(KEY_USER_ID, userId);
        this.userId = userId;
        editor.apply();
    }


    public boolean isUserLoggedIn() {
        return sharedPreferences.contains(KEY_USERNAME);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);  // ✅ Retrieve userId from SharedPreferences
    }


    public void clearCache() {
        editor.clear();
        editor.apply();
    }
}
