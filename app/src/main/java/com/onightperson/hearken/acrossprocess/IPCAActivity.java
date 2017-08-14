package com.onightperson.hearken.acrossprocess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/7/25.
 */

public class IPCAActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "IPCAActivity";

    private Button mStartBBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_a_layout);

        initViews();
        UserManager.sUserId++;
        Log.i(TAG, "onCreate: userId: " + UserManager.sUserId);
    }

    private void initViews() {
        mStartBBtn = (Button) findViewById(R.id.start_b);
        mStartBBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mStartBBtn) {
            Intent intent = new Intent(this, IPCBActivity.class);
            startActivity(intent);
        }
    }
}
