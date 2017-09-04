package com.onightperson.hearken.viewworkprinciple.layoutinflater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/8/31.
 */

public class LayoutInflaterTestActivity extends BaseActivity {

    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_inflater_layout);

        initViews();
    }

    private void initViews() {
        mRootLayout = (LinearLayout) findViewById(R.id.container_layout);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.inflate_button_layout, mRootLayout);
    }
}
