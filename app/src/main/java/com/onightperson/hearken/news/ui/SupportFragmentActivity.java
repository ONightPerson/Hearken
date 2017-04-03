package com.onightperson.hearken.news.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseFragmentActivity;
import com.onightperson.hearken.news.constant.NewsConstant;

/**
 * Created by liubaozhu on 17/1/10.
 */

public class SupportFragmentActivity extends BaseFragmentActivity {
    private static final String TAG = "SupportFragmentActivity";

    private SupportFragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        initData();

    }

    private void initData() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mFragment = new SupportFragment();
        ft.add(R.id.fragment_container, mFragment);
        ft.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }
}
