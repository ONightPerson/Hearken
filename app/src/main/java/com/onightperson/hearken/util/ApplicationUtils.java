package com.onightperson.hearken.util;

import android.content.Context;

/**
 * Created by liubaozhu on 17/1/13.
 */

public class ApplicationUtils {

    private static Context mAppContext;

    public static void initAppContext(Context context) {
        mAppContext = context;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
