package com.example.anull.excitingnews;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.avos.avoscloud.AVOSCloud;
import com.example.anull.excitingnews.config.Config;
import com.example.anull.excitingnews.util.SpUtil;

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
        AVOSCloud.initialize(this, Config.CLOUD_ID, Config.CLOUD_KEY);
        SpUtil.init(this);
    }

    public static Context getAppContext() {
        return appContext;
    }
}