package com.onightperson.hearken.launchmode.flags.cleartop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.HearkenActivity;

/**
 * Created by liubaozhu on 17/6/15.
 */

public class BActivity extends HearkenActivity implements View.OnClickListener {

    private Button mStartCBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_layout);
        initViews();

    }

    private void initViews() {
        mStartCBtn = (Button) findViewById(R.id.start_next);
        mStartCBtn.setText("启动 C Activity");
        mStartCBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mStartCBtn) {
            intent = new Intent(this, CActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
