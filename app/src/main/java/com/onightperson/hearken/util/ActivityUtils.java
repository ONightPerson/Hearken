package com.onightperson.hearken.util;

import com.onightperson.hearken.base.HearkenActivity;
import com.onightperson.hearken.base.BaseFragmentActivity;

/**
 * Created by liubaozhu on 17/1/28.
 */

public class ActivityUtils {

    public static void finishAll() {
        HearkenActivity.finishAll();
        BaseFragmentActivity.finishAll();
    }
}
