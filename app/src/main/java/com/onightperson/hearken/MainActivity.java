package com.onightperson.hearken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.anim.viewanim.LearnViewAnimActivity;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.camera.CameraActivity;
import com.onightperson.hearken.drawable.DrawableTestActivity;
import com.onightperson.hearken.listviewex.ListViewExActivity;
import com.onightperson.hearken.manager.ManagerTestActivity;
import com.onightperson.hearken.news.ui.SupportFragmentActivity;
import com.onightperson.hearken.notify.NotificationActivity;
import com.onightperson.hearken.progress.ProgressActivity;
import com.onightperson.hearken.text.TextChangeActivity;
import com.onightperson.hearken.recycle.RecyclerTestActivity;
import com.onightperson.hearken.toolbar.CoordinatorActivity;
import com.onightperson.hearken.uri.UriTest;
import com.onightperson.hearken.viewdispatchevent.DispatchEventActivity;
import com.onightperson.hearken.wave.WaveActivity;

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
            intent = new Intent(this, DispatchEventActivity.class);
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
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
