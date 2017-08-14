package com.onightperson.hearken.launchmode.flags;

import android.os.Bundle;
import android.util.Log;

import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/6/14.
 */

public class FinishOnTaskLaunchTestActivity extends BaseActivity {
    private static final String TAG = "FinishOnTaskLaunchTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
