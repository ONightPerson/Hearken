package com.onightperson.hearken.util;

import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.base.BaseFragmentActivity;

/**
 * Created by liubaozhu on 17/1/28.
 */

public class ActivityUtils {

    public static void finishAll() {
        BaseActivity.finishAll();
        BaseFragmentActivity.finishAll();
    }
}
