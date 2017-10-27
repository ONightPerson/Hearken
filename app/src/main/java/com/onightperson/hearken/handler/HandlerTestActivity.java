package com.onightperson.hearken.handler;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

import java.io.File;

/**
 * Created by liubaozhu on 10/18/17.
 */

public class HandlerTestActivity extends BaseActivity implements View.OnClickListener,
        MyHandler.MessageHandler {
    private static final String TAG = "HandlerTestActivity";


    private Button mAddView;
    private Button mSendMsgOneBtn;
    private Button mSendMsgTwoBtn;
    private Button mGetAttachBtn;
    private LinearLayout mViewContainer;

    private static final int MSG_ONE = 0;
    private static final int MSG_TWO = 1;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);

        initViews();
//        startHandlerThread();
    }

    private void initViews() {
        mSendMsgOneBtn = (Button) findViewById(R.id.send_msg_1);
        mSendMsgOneBtn.setOnClickListener(this);
        mSendMsgTwoBtn = (Button) findViewById(R.id.send_msg_2);
        mSendMsgTwoBtn.setOnClickListener(this);
        mGetAttachBtn = (Button) findViewById(R.id.get_attach_binder);
        mGetAttachBtn.setOnClickListener(this);
        mViewContainer = (LinearLayout) findViewById(R.id.view_container);
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
//        mHandlerThread = new HandlerThread("DealMsg");
//        mHandlerThread.start();
//        mHandler = new Handler(mHandlerThread.getLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case MSG_ONE:
//                        Log.i(TAG, "handleMessage: receive one");
//                        long count = 0;
//                        for (int i = 0; i < 100; i++) {
//                            count += i;
//                            Log.i(TAG, "handleMessage: count: " + count);
//                        }
//                        break;
//                    case MSG_TWO:
//                        Log.i(TAG, "handleMessage: receive two");
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
        mAddView = new Button(getApplicationContext()) {
            @Override
            protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
                super.onLayout(changed, left, top, right, bottom);
                Log.i(TAG, "onLayout: isCalled");
            }
        };
//        mHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "MessageQueue view info --> height: " + toAddView.getHeight()
//                        + ", width: " + toAddView.getWidth());
//            }
//        });

//        toAddView.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "RunQueue view info --> height: " + toAddView.getHeight()
//                        + ", width: " + toAddView.getWidth());
//            }
//        });
        mViewContainer.addView(mAddView);
        Log.i(TAG, "initViews: toAddView attach info: " + mAddView.getWindowToken());

        String basePath = Environment.getExternalStorageDirectory().getPath();
        Log.i(TAG, "initViews: basePath: " + basePath);
        File file = new File(basePath + "/hello");
        file.mkdirs();
        Log.i(TAG, "initViews: after create file, it's path: " + file.getAbsolutePath());
    }

    private void startHandlerThread() {
        mHandlerThread = new HandlerThread("DealMsg");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_ONE:
                        Log.i(TAG, "handleMessage: receive one");
                        long count = 0;
                        for (int i = 0; i < 100; i++) {
                            count += i;
                            Log.i(TAG, "handleMessage: count: " + count);
                        }
                        break;
                    case MSG_TWO:
                        Log.i(TAG, "handleMessage: receive two");
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == mSendMsgOneBtn) {
            mHandler.sendEmptyMessage(MSG_ONE);
        } else if (v == mSendMsgTwoBtn) {
            mHandler.sendEmptyMessage(MSG_TWO);
        } else if (v == mGetAttachBtn) {
            Log.i(TAG, "onClick: get attach info for button: " + mAddView.getWindowToken());
        }
    }

    @Override
    public void handleMessage() {

    }
}
