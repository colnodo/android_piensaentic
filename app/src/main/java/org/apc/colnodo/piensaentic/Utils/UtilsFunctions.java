package org.apc.colnodo.piensaentic.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 11/7/16.
 */

public abstract class UtilsFunctions {

    public static void saveSharedString(Context ctx, String key, String value){
        SharedPreferences preferences = ctx.getSharedPreferences(LocalConstants.SHARED_PREFERENCE_CONTIGOAPP, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).apply();
    }

    public static void saveSharedBoolean(Context ctx, String key, boolean value){
        SharedPreferences preferences = ctx.getSharedPreferences(LocalConstants.SHARED_PREFERENCE_CONTIGOAPP, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).apply();
    }

    public static String getSharedString(Context ctx, String key){
        SharedPreferences preferences = ctx.getSharedPreferences(LocalConstants.SHARED_PREFERENCE_CONTIGOAPP, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }

    public static boolean getSharedBoolean(Context ctx, String key){
        SharedPreferences preferences = ctx.getSharedPreferences(LocalConstants.SHARED_PREFERENCE_CONTIGOAPP, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static void resetSharedPreferences(Context ctx){
        SharedPreferences preferences = ctx.getSharedPreferences(LocalConstants.SHARED_PREFERENCE_CONTIGOAPP, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    public static boolean checkRegEx(String string, String regEx){
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher dateMatcher = pattern.matcher(string);
        return dateMatcher.find();
    }


}
