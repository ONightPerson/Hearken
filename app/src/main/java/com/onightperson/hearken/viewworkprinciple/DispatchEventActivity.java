package com.onightperson.hearken.viewworkprinciple;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.onightperson.hearken.R;
import com.onightperson.hearken.viewworkprinciple.customview.CustomButton;

/**
 * Created by liubaozhu on 17/3/15.
 */

public class DispatchEventActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "DispatchEventActivity";

    private CustomButton mCustomBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchevent);

        initViews();
    }

    private void initViews() {
        mCustomBtn = (CustomButton) findViewById(R.id.custom_button);
        mCustomBtn.setOnClickListener(this);
     }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent ev: " + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        if (v == mCustomBtn) {
            Toast.makeText(this, "自定义Button已点击", Toast.LENGTH_SHORT).show();
        }
    }
}
