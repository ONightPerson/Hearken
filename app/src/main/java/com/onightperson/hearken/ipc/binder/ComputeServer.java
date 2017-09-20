package com.onightperson.hearken.ipc.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class ComputeServer extends Service {
    private static final String TAG = "ComputeServer";

    private RemoteCallbackList<IComputeCallback> mListeners;
    private AtomicBoolean mIsServiceDestoryed;


    @Override
    public void onCreate() {
        super.onCreate();
        mIsServiceDestoryed = new AtomicBoolean(false);
        mListeners = new RemoteCallbackList<>();
        startAddTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestoryed.set(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ComputerImpl();
    }

    public class ComputerImpl extends ComputerStub {
        private static final String TAG = "ComputerImpl";

        @Override
        public int add(int a, int b) {
            return a + b;
        }

        @Override
        public void addInternal(Map<String, Integer> values) {
            if (values == null) {
                return;
            }

            int result = 0;
            for (Map.Entry<String, Integer> entry : values.entrySet()) {
                result += entry.getValue();
            }
            Log.i(TAG, "addInternal: result: result: " + result);
        }

        @Override
        public void registerListener(IComputeCallback listener) {
            mListeners.register(listener);
        }

        @Override
        public void unregisterListener(IComputeCallback listener) {
            boolean unregister = mListeners.unregister(listener);
            Log.i(TAG, "unregisterListener: unregister: " + unregister);
        }
    }

    private void startAddTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mIsServiceDestoryed.get()) {
                    try {
                        int n = mListeners.beginBroadcast();
                        while (n > 0) {
                            n --;
                            IComputeCallback callback = mListeners.getBroadcastItem(n);
                            callback.onAddCalled();
                        }
                        mListeners.finishBroadcast();
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
