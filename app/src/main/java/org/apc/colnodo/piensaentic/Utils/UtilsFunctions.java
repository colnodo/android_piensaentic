package org.apc.colnodo.piensaentic.Utils;

import android.content.Context;
import android.content.SharedPreferences;

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
        return preferences.getString(key, "");
    }

    public static boolean getSharedBoolean(Context ctx, String key){
        SharedPreferences preferences = ctx.getSharedPreferences(LocalConstants.SHARED_PREFERENCE_CONTIGOAPP, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }


}
