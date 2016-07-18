package com.example.anull.excitingnews;

import android.app.Application;
import android.content.Context;

/**
 * Created by null on 2016/7/18.
 */
public class App extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
