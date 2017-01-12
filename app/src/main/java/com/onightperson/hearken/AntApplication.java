package com.onightperson.hearken;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by liubaozhu on 17/1/6.
 */

public class AntApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
