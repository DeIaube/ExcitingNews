package com.example.anull.excitingnews.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by null on 2016/7/20.
 */
public class SpUtil {
    private static SharedPreferences sp;
    public static void init(Context appContext){
        sp = appContext.getSharedPreferences(appContext.getPackageName(), appContext.MODE_PRIVATE);
    }

    public static void putBoolen(String tag, boolean data){
        if(sp != null){
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean(tag, data);
            edit.apply();
        }
    }

    public static boolean getBoolen(String tag, boolean data){
        if(sp == null){
            return false;
        }
        return sp.getBoolean(tag, data);
    }
}
