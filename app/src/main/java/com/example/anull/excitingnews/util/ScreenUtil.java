package com.example.anull.excitingnews.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by DESKTOP-1JBF8Q8$ on 2016/7/19.
 */
public class ScreenUtil {
    public static int[] getScreenHW(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int high = dm.heightPixels;
        int[] hw = {width, high};
        return hw;
    }
}
