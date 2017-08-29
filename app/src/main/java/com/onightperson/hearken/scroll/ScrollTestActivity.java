package com.onightperson.hearken.scroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/8/21.
 */

public class ScrollTestActivity extends BaseActivity implements View.OnClickListener {

    private Button mScrollViewTestBtn;
    private Button mHorizontalScrollViewTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test_layout);

        initViews();
    }

    private void initViews() {
        mScrollViewTestBtn = (Button) findViewById(R.id.test_scroll_view_related);
        mScrollViewTestBtn.setOnClickListener(this);
        mHorizontalScrollViewTestBtn = (Button) findViewById(R.id.test_horizontal_scroll_view_related);
        mHorizontalScrollViewTestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mScrollViewTestBtn) {
            intent = new Intent(this, ScrollViewTestActivity.class);
        } else if (v == mHorizontalScrollViewTestBtn) {
            intent = new Intent(this, HorizontalScrollViewTestActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
