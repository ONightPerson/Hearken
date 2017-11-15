package com.onightperson.hearken.viewworkprinciple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/8/31.
 */

public class ViewMainActivity extends BaseActivity implements View.OnClickListener {

    private Button mTestCustomViewBtn;
    private Button mTestLayoutInflaterBtn;
    private Button mCustomButton;
    private boolean mIsShowButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main_activity_layout);

        initViews();
    }

    private void initViews() {
        mTestCustomViewBtn = (Button) findViewById(R.id.custom_view_test_btn);
        mTestCustomViewBtn.setOnClickListener(this);
        mTestLayoutInflaterBtn = (Button) findViewById(R.id.layout_inflater_test_btn);
        mTestLayoutInflaterBtn.setOnClickListener(this);
        mCustomButton = (Button) findViewById(R.id.custom_button);
    }

    @Override
    public void onClick(View v) {
        if (v == mTestCustomViewBtn) {
            mTestLayoutInflaterBtn.setVisibility(mIsShowButton ? View.VISIBLE : View.GONE);
//            mCustomButton.setVisibility(mIsShowButton ? View.GONE : View.VISIBLE);
            mIsShowButton = !mIsShowButton;
        }
    }
}
