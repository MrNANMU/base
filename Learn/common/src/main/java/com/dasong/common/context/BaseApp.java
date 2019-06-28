package com.dasong.common.context;

import android.app.Application;

public class BaseApp extends Application {

    public static BaseApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static BaseApp getContext(){
        return context;
    }
}
