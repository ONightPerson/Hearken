package com.onightperson.hearken.launchmode.singletask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/6/14.
 */

public class SingleTaskBActivity extends BaseActivity implements View.OnClickListener {

    private Button mLaunchSingleTaskA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletask_b_layout);

        initViews();
    }

    private void initViews() {
        mLaunchSingleTaskA = (Button) findViewById(R.id.launch_a_activity);
        mLaunchSingleTaskA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mLaunchSingleTaskA) {
            intent = new Intent(this, SingleTaskAActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
