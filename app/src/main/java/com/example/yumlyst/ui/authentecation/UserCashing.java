package com.example.yumlyst.ui.authentecation;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCashing {

    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHOTO_URL = "photoUrl";

    private static UserCashing instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

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

    public void cacheUser(String username, String email) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "Guest");
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "No email provided");
    }

    public String getPhotoUrl() {
        return sharedPreferences.getString(KEY_PHOTO_URL, null);
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.contains(KEY_USERNAME);
    }

    public void clearCache() {
        editor.clear();
        editor.apply();
    }
}
