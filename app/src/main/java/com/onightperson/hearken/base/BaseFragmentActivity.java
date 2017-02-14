package com.onightperson.hearken.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.WeakHashMap;

/**
 * Created by liubaozhu on 17/1/28.
 */

public class BaseFragmentActivity extends FragmentActivity {
    private static final String TAG = "BaseFragmentActivity";

    private static WeakHashMap<String, WeakReference<BaseFragmentActivity>> mInstanceList
            = new WeakHashMap<>();
    private String mActivityName = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseFragmentActivity instance = getInstance();
        if (instance != null) {
            this.finish();
        }

        //创建一个实例的弱引用
        synchronized (mInstanceList) {
            mInstanceList.put(mActivityName, new WeakReference<>(this));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BaseFragmentActivity instance = getInstance();
        if (instance == this) {
            synchronized (mInstanceList) {
                mInstanceList.remove(mActivityName);
            }
        }
    }

    private BaseFragmentActivity getInstance() {
        //get the name of current activity
        if (mActivityName == null) {
            mActivityName = this.getClass().getName();
        }

        WeakReference<BaseFragmentActivity> weakInstance = mInstanceList.get(mActivityName);
        if (weakInstance != null) {
            return weakInstance.get();
        }

        return null;
    }

    public static void finishAll() {
        Log.d(TAG, "finishAll--mInstanceList's size: " + mInstanceList.size());
        Collection<WeakReference<BaseFragmentActivity>> weakInstanceList = mInstanceList.values();
        for (WeakReference<BaseFragmentActivity> weakInstance : weakInstanceList) {
            if (weakInstance != null) {
                BaseFragmentActivity instance = weakInstance.get();
                instance.finish();
            }
        }
    }
}
