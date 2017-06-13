package com.onightperson.hearken.notify;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/6/13.
 */

public class NotificationActivity extends Activity implements View.OnClickListener {

    private Button mSendNormalNotiBtn;
    private Button mSendCustomNotiBtn;
    private Button mSendNormalNoti2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_layout);

        initViews();
    }

    private void initViews() {
        mSendNormalNotiBtn = (Button) findViewById(R.id.send_normal_notification);
        mSendNormalNotiBtn.setOnClickListener(this);
        mSendCustomNotiBtn = (Button) findViewById(R.id.send_custom_notification);
        mSendCustomNotiBtn.setOnClickListener(this);
        mSendNormalNoti2Btn = (Button) findViewById(R.id.send_normal_notification_2);
        mSendNormalNoti2Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSendNormalNotiBtn) {
            NotificationMgr.getInstance(this).sendAlarmNotification();
        } else if (v == mSendCustomNotiBtn) {
            NotificationMgr.getInstance(this).sendCustomNotification();
        } else if (v == mSendNormalNoti2Btn) {
            NotificationMgr.getInstance(this).sendNormalNotification();
        }
    }
}
