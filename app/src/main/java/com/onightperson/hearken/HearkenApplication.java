package com.onightperson.hearken;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Process;
import android.util.Log;

import com.onightperson.hearken.receiver.NetworkChangeReceiver;
import com.onightperson.hearken.util.ApplicationUtils;

/**
 * Created by liubaozhu on 17/1/6.
 */

public class HearkenApplication extends Application {
    private static final String TAG = "HearkenApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ApplicationUtils.initAppContext(this);

        Log.d(TAG, "attachBaseContext: pid: " + Process.myPid());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册网络变化接收器
        registerNetworkChangeReceiver();

    }

    private void registerNetworkChangeReceiver() {
        NetworkChangeReceiver receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }
}
