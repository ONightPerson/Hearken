package com.onightperson.hearken.manager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/3/17.
 */

public class ManagerTestActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ManagerTestActivity";

    private Button mCheckActivityInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managertest_layout);

        initViews();
    }

    private void initViews() {
        mCheckActivityInfoBtn = (Button) findViewById(R.id.check_activityinfo);
        mCheckActivityInfoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCheckActivityInfoBtn) {
            ManagerTest.printPackageName(this);
        }
    }
}
