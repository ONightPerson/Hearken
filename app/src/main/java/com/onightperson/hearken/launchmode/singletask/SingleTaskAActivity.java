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

public class SingleTaskAActivity extends BaseActivity implements View.OnClickListener {

    private Button mLaunchSingleTaskB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletask_a_layout);

        initViews();
    }

    private void initViews() {
        mLaunchSingleTaskB = (Button) findViewById(R.id.launch_singletask_b);
        mLaunchSingleTaskB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mLaunchSingleTaskB) {
            intent = new Intent(this, SingleTaskBActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
