package com.example.yumlyst.model;

import android.content.Context;

import com.example.yumlyst.R;

import java.util.Random;

public class Helper {
    private Helper(){};
    public static int getFlagResourceByName(Context context, String countryName) {
        String formattedName = countryName.toLowerCase().replace(" ", "_");
        int resourceId = context.getResources().getIdentifier(formattedName, "drawable", context.getPackageName());
        return resourceId != 0 ? resourceId : R.drawable.ic_launcher_background;
    }

    public static int getRandomDrawable() {
        int[] drawableResources = {
                R.drawable.color1,
                R.drawable.color2,
                R.drawable.color3,
                R.drawable.color4,
                R.drawable.color5,
                R.drawable.color6,
                R.drawable.color7,
                R.drawable.color8,
                R.drawable.color9,
                R.drawable.color10,
        };
        Random random = new Random();
        int randomIndex = random.nextInt(drawableResources.length);
        return drawableResources[randomIndex];
    }

}
