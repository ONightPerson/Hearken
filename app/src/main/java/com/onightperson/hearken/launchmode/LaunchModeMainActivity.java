package com.onightperson.hearken.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.Constants;
import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.launchmode.flags.FinishOnTaskLaunchTestActivity;
import com.onightperson.hearken.launchmode.otherproperty.AActivity;
import com.onightperson.hearken.launchmode.singletask.SingleTaskAActivity;

/**
 * Created by liubaozhu on 17/6/13.
 */

public class LaunchModeMainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LaunchModeMainActivity";

    private Button mSingleTopAActivityBtn;
    private Button mSingleTaskAActivityBtn;
    private Button mSingleInstanceAActivityBtn;
    private Button mFinishOnTaskLaunchBtn;
    private Button mClearTopTestBtn;
    private Button mNewTaskTestBtn;
    private Button mNewTaskAndClearTopBtn;
    private Button mSingleTopTestBtn;
    private Button mTestATRBtn;
    private Button mClearTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top_test_layout);
        initViews();
    }

    private void initViews() {
        mSingleTopAActivityBtn = (Button) findViewById(R.id.launch_singletop_a_activity);
        mSingleTopAActivityBtn.setOnClickListener(this);
        mSingleTaskAActivityBtn = (Button) findViewById(R.id.launch_singletask_a_activity);
        mSingleTaskAActivityBtn.setOnClickListener(this);
        mSingleInstanceAActivityBtn = (Button) findViewById(R.id.launch_singleinstance_a_activity);
        mSingleInstanceAActivityBtn.setOnClickListener(this);
        mFinishOnTaskLaunchBtn = (Button) findViewById(R.id.finish_on_task_launch_btn);
        mFinishOnTaskLaunchBtn.setOnClickListener(this);
        mClearTopTestBtn = (Button) findViewById(R.id.flag_actvity_clear_top);
        mClearTopTestBtn.setOnClickListener(this);
        mNewTaskTestBtn = (Button) findViewById(R.id.flag_actvity_new_task);
        mNewTaskTestBtn.setOnClickListener(this);
        mNewTaskAndClearTopBtn = (Button) findViewById(R.id.combine_new_task_and_clear_top);
        mNewTaskAndClearTopBtn.setOnClickListener(this);
        mClearTaskBtn = (Button) findViewById(R.id.flag_clear_task);
        mClearTaskBtn.setOnClickListener(this);
        mSingleTopTestBtn = (Button) findViewById(R.id.flag_actvity_single_top);
        mSingleTopTestBtn.setOnClickListener(this);
        mTestATRBtn = (Button) findViewById(R.id.test_allow_task_reparenting);
        mTestATRBtn.setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String launchFrom = intent.getStringExtra(Constants.KEY_LAUNCH_FROM);
            Log.i(TAG, "onNewIntent -> launch from : " + launchFrom);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mSingleTopAActivityBtn) {
            intent = new Intent(this, LaunchModeMainActivity.class);
            intent.putExtra(Constants.KEY_LAUNCH_FROM, getClass().getSimpleName());
            startActivity(intent);
        } else if (v == mSingleTaskAActivityBtn) {
            intent = new Intent(this, SingleTaskAActivity.class);
        } else if (v == mSingleInstanceAActivityBtn) {
            intent = new Intent(this, com.onightperson.hearken.launchmode.singleinstance.AActivity.class);
        } else if (v == mFinishOnTaskLaunchBtn) {
            intent = new Intent(this, FinishOnTaskLaunchTestActivity.class);
        } else if (v == mClearTopTestBtn) {
            intent = new Intent(this, com.onightperson.hearken.launchmode.flags.cleartop.AActivity.class);
        } else if (v == mNewTaskTestBtn) {
            intent = new Intent(this, com.onightperson.hearken.launchmode.flags.newtask.AActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else if (v == mSingleTopTestBtn) {
            intent = new Intent(this, com.onightperson.hearken.launchmode.flags.singletop.AActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        } else if (v == mTestATRBtn) {
            intent = new Intent("com.onightperson.otherapp.ENTER_B");
            intent.setClassName("com.onightperson.otherapp", "com.onightperson.otherapp.BActivity");
        } else if (v == mNewTaskAndClearTopBtn) {
            intent = new Intent(this, com.onightperson.hearken.launchmode.flags.newtaskandcleartop.AActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else if (v == mClearTaskBtn) {
            intent = new Intent(this, com.onightperson.hearken.launchmode.flags.cleartask.AActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
