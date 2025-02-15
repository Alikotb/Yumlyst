package com.example.yumlyst.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Calender {
    private Calender() {}
    public static String getDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String getMaxDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        return sdf.format(cal.getTime());
    }
}
