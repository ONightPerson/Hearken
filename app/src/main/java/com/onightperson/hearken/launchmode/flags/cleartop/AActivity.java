package com.onightperson.hearken.launchmode.flags.cleartop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/6/15.
 */

public class AActivity extends BaseActivity implements View.OnClickListener {

    private Button mStartBBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_layout);
        initViews();

    }

    private void initViews() {
        mStartBBtn = (Button) findViewById(R.id.start_next);
        mStartBBtn.setText("启动 B Activity");
        mStartBBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mStartBBtn) {
            intent = new Intent(this, BActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
