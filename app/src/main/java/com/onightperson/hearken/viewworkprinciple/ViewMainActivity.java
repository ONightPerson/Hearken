package com.onightperson.hearken.viewworkprinciple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.viewworkprinciple.layoutinflater.LayoutInflaterTestActivity;

/**
 * Created by liubaozhu on 17/8/31.
 */

public class ViewMainActivity extends BaseActivity implements View.OnClickListener {

    private Button mTestCustomViewBtn;
    private Button mTestLayoutInflaterBtn;

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
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mTestCustomViewBtn) {
            intent = new Intent(this, DispatchEventActivity.class);
        } else if (v == mTestLayoutInflaterBtn) {
            intent = new Intent(this, LayoutInflaterTestActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
