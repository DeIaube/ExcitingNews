package com.example.anull.excitingnews.util;

import android.util.Log;

import com.example.anull.excitingnews.config.Config;


/**
 * Created by null on 2016/7/17.
 */
public class LogUtil {
    public static void  i(String tag, String content){
        if(Config.DEBUG){
            Log.i(tag, content);
        }
    }

    public static void  e(String tag, String content){
        if(Config.DEBUG){
            Log.e(tag, content);
        }
    }
}
