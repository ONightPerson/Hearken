package com.onightperson.hearken.acrossprocess;

import android.os.Bundle;
import android.util.Log;

import com.onightperson.hearken.base.HearkenActivity;

/**
 * Created by liubaozhu on 17/7/25.
 */

public class IPCBActivity extends HearkenActivity {
    private static final String TAG = "IPCBActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: userId: " + UserManager.sUserId);
    }
}
