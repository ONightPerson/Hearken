package com.onightperson.hearken.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class ScreenUtils {

    public static int getWidth() {
        WindowManager manager = (WindowManager) ApplicationUtils.getAppContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.widthPixels;
    }

    public static int getHeight() {
        WindowManager manager = (WindowManager) ApplicationUtils.getAppContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics.heightPixels;
    }
}
