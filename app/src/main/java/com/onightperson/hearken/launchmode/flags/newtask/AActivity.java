package com.onightperson.hearken.launchmode.flags.newtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.launchmode.LaunchModeMainActivity;

/**
 * Created by liubaozhu on 17/6/15.
 */

public class AActivity extends BaseActivity implements View.OnClickListener {

    private Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_layout);
        initViews();

    }

    private void initViews() {
        mStartBtn = (Button) findViewById(R.id.start_next);
        mStartBtn.setText("重新启动 LaunchModeMainActivity");
        mStartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mStartBtn) {
            intent = new Intent(this, LaunchModeMainActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
