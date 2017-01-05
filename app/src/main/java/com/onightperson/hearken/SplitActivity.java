package com.onightperson.hearken;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.onightperson.hearken.fragment.RightFragment;
import com.onightperson.hearken.fragment.RightFragment2;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class SplitActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "SplitActivity";

    private TextView mClickTextView;
    private boolean mIsFirstRight = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        initViews();
    }

    private void initViews() {
        mClickTextView = (TextView) findViewById(R.id.click_text);
        mClickTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mClickTextView) {
            //1. 获取FragmentManager实例
            FragmentManager fragmentManager = getFragmentManager();
            //2. 通过FragmentManager获取FragmentTransaction
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            Fragment fragment;
            if (mIsFirstRight) {
                //3. 动态创建Fragment
                fragment = new RightFragment2();

                mIsFirstRight = false;
            } else {
                //3. 动态创建Fragment
                fragment = new RightFragment();

                mIsFirstRight = true;
            }
            //4. 通过FragmentTransaction更新fragment
            transaction.replace(R.id.right_container, fragment);
            transaction.addToBackStack(null);
            //5. 提交更新
            transaction.commit();
        }
    }
}
