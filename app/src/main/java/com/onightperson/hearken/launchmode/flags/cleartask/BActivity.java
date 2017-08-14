package com.onightperson.hearken.launchmode.flags.cleartask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/6/14.
 */

public class BActivity extends BaseActivity implements View.OnClickListener {

    private Button mLaunchA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletask_b_layout);

        initViews();
    }

    private void initViews() {
        mLaunchA = (Button) findViewById(R.id.launch_a_activity);
        mLaunchA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mLaunchA) {
            intent = new Intent(this, AActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
