package com.onightperson.hearken.scroll;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/8/21.
 */

public class ScrollViewTestActivity extends BaseActivity {
    private static final String TAG = "ScrollViewTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_test_layout);
        
        initData();

    }
    
    private void initData() {

        DisplayMetrics m = new DisplayMetrics();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(m);
        int screenWidth = m.heightPixels;
        Log.i(TAG, "onMeasure: screenHeight: " + screenWidth);
    }
}
