package com.onightperson.hearken.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by liubaozhu on 17/1/28.
 */

public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return false;
        }

        NetworkInfo networkInfo = null;

        try {
            networkInfo = connManager.getActiveNetworkInfo();
        } catch (Exception e) {
            Log.e(TAG, "failed to get active networkinfo", e);
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
