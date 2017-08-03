package com.onightperson.hearken.notify;

import android.content.Context;
import android.content.Intent;

/**
 * Created by liubaozhu on 17/6/14.
 */

public class NotificationUtils {

    public static void guidePermReadActivity(Context cxt) {
        Intent intent = new Intent(NotificationConstants.ACTION_SYSTEM_READ_NOTIFICATION_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        cxt.startActivity(intent);
    }
}
