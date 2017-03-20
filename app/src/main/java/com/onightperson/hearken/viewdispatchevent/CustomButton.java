package com.onightperson.hearken.viewdispatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by liubaozhu on 17/3/15.
 */

public class CustomButton extends Button {
    private static final String TAG = "CustomButton";

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "CustomButton");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: event: " + event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent event: " + event);
        return super.onTouchEvent(event);
    }
}
