package com.onightperson.hearken.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.ipc.binder.ComputeServer;
import com.onightperson.hearken.ipc.binder.ComputerImpl;
import com.onightperson.hearken.ipc.binder.IComputer;
import com.onightperson.hearken.ipc.messenger.MessengerService;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class IPCTestActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "IPCTestActivity";

    private Button mComputeBinderBtn;
    private Button mMessengerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_test_layout);

        initViews();
    }

    private void initViews() {
        mComputeBinderBtn = (Button) findViewById(R.id.start_compute);
        mComputeBinderBtn.setOnClickListener(this);
        mMessengerBtn = (Button) findViewById(R.id.messenger);
        mMessengerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mComputeBinderBtn) {
            startCompute();
        } else if (v == mMessengerBtn) {
           sayHelloByMessenger();
        }
    }

    private void sayHelloByMessenger() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mMessengerConn, Service.BIND_AUTO_CREATE);
    }

    private void startCompute() {
        // 如何获取远端 Binder
        Intent intent = new Intent(this, ComputeServer.class);
        bindService(intent, mBinderConnection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection mBinderConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: service: " + service);
            if (service != null) {
                IComputer computer = ComputerImpl.asInterface(service);
                int result = computer.add(1, 2);
                Log.i(TAG, "onServiceConnected: result: " + result);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection mMessengerConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message msg = Message.obtain();
            msg.what = MessengerService.MSG_SAY_HELLO;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
