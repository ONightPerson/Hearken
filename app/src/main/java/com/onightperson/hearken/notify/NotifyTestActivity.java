package com.onightperson.hearken.notify;

import android.os.Bundle;
import android.util.Log;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/6/30.
 */

public class NotifyTestActivity extends BaseActivity {
    private static final String TAG = "NotifyTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_notify_test_layout);
    }
}
