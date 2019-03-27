package com.example.pottho.countrycodepicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    public static void savePref(String name, String value) {
        SharedPreferences pref = MyApp.getInstance().getContext().getSharedPreferences("CountryCode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    // get data from shared preference
    public static String getPref(String name, String defaultValue) {
        SharedPreferences pref = MyApp.getInstance().getContext().getSharedPreferences("CountryCode", Context.MODE_PRIVATE);
        return pref.getString(name, defaultValue);
    }
}
