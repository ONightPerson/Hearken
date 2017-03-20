package com.onightperson.hearken.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import java.util.List;

/**
 * Created by liubaozhu on 17/3/17.
 */

public class ManagerTest {
    private static final String TAG = "ManagerTest";

    public static void printPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();

        //查询能够启动首页的包名
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> infoList = packageManager.queryIntentActivities(intent, 0);

        for (ResolveInfo info: infoList) {
            Log.i(TAG, "printPackageName: packageName: " + info.activityInfo.packageName
                    + ", name: " + info.activityInfo.name);
        }

    }

    public static void checkLockState(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isInteractive;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            isInteractive = powerManager.isInteractive();
        } else {
            isInteractive = powerManager.isScreenOn();
        }


    }
}
