package com.onightperson.hearken.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by liubaozhu on 10/19/17.
 */

public class MyHandler extends Handler {

    private WeakReference<MessageHandler> mHandlerRef;

    public MyHandler(MessageHandler handler) {
        mHandlerRef = new WeakReference<>(handler);
    }

    public interface MessageHandler {
        void handleMessage();
    }

    @Override
    public void handleMessage(Message msg) {
        MessageHandler handler = mHandlerRef.get();
        if (handler != null) {
            handler.handleMessage();
        }
    }
}
