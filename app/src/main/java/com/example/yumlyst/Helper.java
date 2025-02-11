package com.example.yumlyst;

import android.content.Context;

public class Helper {
    private Helper(){};
    public static int getFlagResourceByName(Context context, String countryName) {
        String formattedName = countryName.toLowerCase().replace(" ", "_");
        int resourceId = context.getResources().getIdentifier(formattedName, "drawable", context.getPackageName());
        return resourceId != 0 ? resourceId : R.drawable.ic_launcher_background;
    }}
