package com.onightperson.hearken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.file.FileTestActivity;
import com.onightperson.hearken.viewworkprinciple.anim.viewanim.LearnViewAnimActivity;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.camera.CameraActivity;
import com.onightperson.hearken.datetime.DateTimeActivity;
import com.onightperson.hearken.viewworkprinciple.anim.drawable.DrawableTestActivity;
import com.onightperson.hearken.handler.HandlerTestActivity;
import com.onightperson.hearken.ipc.IPCTestActivity;
import com.onightperson.hearken.launchmode.LaunchModeMainActivity;
import com.onightperson.hearken.listviewex.ListViewExActivity;
import com.onightperson.hearken.manager.ManagerTestActivity;
import com.onightperson.hearken.viewworkprinciple.news.ui.SupportFragmentActivity;
import com.onightperson.hearken.notify.NotificationActivity;
import com.onightperson.hearken.viewworkprinciple.progress.ProgressActivity;
import com.onightperson.hearken.scroll.ScrollTestActivity;
import com.onightperson.hearken.serializable.model.SerializeTestActivity;
import com.onightperson.hearken.viewworkprinciple.text.TextChangeActivity;
import com.onightperson.hearken.viewworkprinciple.recycle.RecyclerTestActivity;
import com.onightperson.hearken.viewworkprinciple.toolbar.CoordinatorActivity;
import com.onightperson.hearken.viewworkprinciple.ViewMainActivity;
import com.onightperson.hearken.viewworkprinciple.wave.WaveActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mLaunchWaveBtn;
    private Button mLaunchRecyclerViewBtn;
    private Button mLaunchTextChangeBtn;
    private Button mLaunchCoordinatorLayoutBtn;
    private Button mLaunchListViewBtn;
    private Button mLaunchDispatchEventBtn;
    private Button mLaunchManagerTestBtn;
    private Button mLaunchAnimBtn;
    private Button mLaunchFragmentActivityBtn;
    private Button mLaunchCameraBtn;
    private Button mLaunchDrawableTestBtn;
    private Button mProgressTestBtn;
    private Button mNotifyBtn;
    private Button mLaunchModeActivityBtn;
    private Button mDateTimeBtn;
    private Button mIPCBtn;
    private Button mSlideBtn;
    private Button mSerializeTestBtn;
    private Button mHandlerTestBtn;
    private Button mStorageTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wave_anim);
        initViews();
    }

    private void initViews() {
        mLaunchWaveBtn = (Button) findViewById(R.id.launch_wave_activity);
        mLaunchWaveBtn.setOnClickListener(this);
        mLaunchRecyclerViewBtn = (Button) findViewById(R.id.launch_recyclerview_activity);
        mLaunchRecyclerViewBtn.setOnClickListener(this);
        mLaunchTextChangeBtn = (Button) findViewById(R.id.launch_text_change_activity);
        mLaunchTextChangeBtn.setOnClickListener(this);
        mLaunchCoordinatorLayoutBtn = (Button) findViewById(R.id.launch_coordinatorlayout_activity);
        mLaunchCoordinatorLayoutBtn.setOnClickListener(this);
        mLaunchListViewBtn = (Button) findViewById(R.id.launch_expandable_listview_activity);
        mLaunchListViewBtn.setOnClickListener(this);
        mLaunchDispatchEventBtn = (Button) findViewById(R.id.launch_dispatchevent_activity);
        mLaunchDispatchEventBtn.setOnClickListener(this);
        mLaunchManagerTestBtn = (Button) findViewById(R.id.launch_managertest_activity);
        mLaunchManagerTestBtn.setOnClickListener(this);
        mLaunchAnimBtn = (Button) findViewById(R.id.launch_anim_activity);
        mLaunchAnimBtn.setOnClickListener(this);
        mLaunchFragmentActivityBtn = (Button) findViewById(R.id.launch_fragment_activity);
        mLaunchFragmentActivityBtn.setOnClickListener(this);
        mLaunchCameraBtn = (Button) findViewById(R.id.launch_camera);
        mLaunchCameraBtn.setOnClickListener(this);
        mLaunchDrawableTestBtn = (Button) findViewById(R.id.launch_drawable_test_activity);
        mLaunchDrawableTestBtn.setOnClickListener(this);
        mProgressTestBtn = (Button) findViewById(R.id.launch_progress_test_activity);
        mProgressTestBtn.setOnClickListener(this);
        mNotifyBtn = (Button) findViewById(R.id.launch_notification_activity);
        mNotifyBtn.setOnClickListener(this);
        mLaunchModeActivityBtn = (Button) findViewById(R.id.launch_launchmode_activity);
        mLaunchModeActivityBtn.setOnClickListener(this);
        mDateTimeBtn = (Button) findViewById(R.id.date_time);
        mDateTimeBtn.setOnClickListener(this);
        mIPCBtn = (Button) findViewById(R.id.ipc_learn);
        mIPCBtn.setOnClickListener(this);
        mSlideBtn = (Button) findViewById(R.id.slide);
        mSlideBtn.setOnClickListener(this);
        mSerializeTestBtn = (Button) findViewById(R.id.serialize);
        mSerializeTestBtn.setOnClickListener(this);
        mHandlerTestBtn = (Button) findViewById(R.id.test_handler);
        mHandlerTestBtn.setOnClickListener(this);
        mStorageTestBtn = (Button) findViewById(R.id.test_storage);
        mStorageTestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mLaunchWaveBtn) {
            intent = new Intent(this, WaveActivity.class);
        } else if (v == mLaunchRecyclerViewBtn) {
            intent = new Intent(this, RecyclerTestActivity.class);
        } else if (v == mLaunchTextChangeBtn) {
            intent = new Intent(this, TextChangeActivity.class);
        } else if (v == mLaunchCoordinatorLayoutBtn) {
            intent = new Intent(this, CoordinatorActivity.class);
        } else if (v == mLaunchListViewBtn) {
            intent = new Intent(this, ListViewExActivity.class);
        } else if (v == mLaunchDispatchEventBtn) {
            intent = new Intent(this, ViewMainActivity.class);
        } else if (v == mLaunchManagerTestBtn) {
            intent = new Intent(this, ManagerTestActivity.class);
        } else if (v == mLaunchAnimBtn) {
            intent = new Intent(this, LearnViewAnimActivity.class);
        } else if (v == mLaunchFragmentActivityBtn) {
            intent = new Intent(this, SupportFragmentActivity.class);
//            UriTest.printType();
        } else if (v == mLaunchCameraBtn) {
            intent = new Intent(this, CameraActivity.class);
        } else if (v == mLaunchDrawableTestBtn) {
            intent = new Intent(this, DrawableTestActivity.class);
        } else if (v == mProgressTestBtn) {
            intent = new Intent(this, ProgressActivity.class);
        } else if (v == mNotifyBtn) {
            intent = new Intent(this, NotificationActivity.class);
        } else if (v == mLaunchModeActivityBtn) {
            intent = new Intent(this, LaunchModeMainActivity.class);
        } else if (v == mDateTimeBtn) {
            intent = new Intent(this, DateTimeActivity.class);
        } else if (v == mIPCBtn) {
            intent = new Intent(this, IPCTestActivity.class);
        } else if (v == mSlideBtn) {
            intent = new Intent(this, ScrollTestActivity.class);
        } else if (v == mSerializeTestBtn) {
            intent = new Intent(this, SerializeTestActivity.class);
        } else if (v == mHandlerTestBtn) {
            intent = new Intent(this, HandlerTestActivity.class);
        } else if (v == mStorageTestBtn) {
            intent = new Intent(this, FileTestActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
