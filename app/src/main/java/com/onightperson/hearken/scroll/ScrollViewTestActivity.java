package com.onightperson.hearken.scroll;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/8/21.
 */

public class ScrollViewTestActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ScrollViewTestActivity";

    private Button mInfoBtn;
    private HorizontalScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_content_large_layout);

        initViews();

    }

    private void initViews() {
        mInfoBtn = (Button) findViewById(R.id.btn);
        mInfoBtn.setOnClickListener(this);
        mScrollView = (HorizontalScrollView) findViewById(R.id.my_horizontal_scrollview);
    }

    @Override
    public void onClick(View v) {
        if (v == mInfoBtn) {
            Log.i(TAG, "onClick: mRight: " + mScrollView.getChildAt(0).getRight());
        }
    }
}
