package com.example.anull.excitingnews;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.anull.excitingnews.config.Config;

/**
 * Created by null on 2016/7/18.
 */
public class App extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Config.ID = telephonyManager.getDeviceId();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
