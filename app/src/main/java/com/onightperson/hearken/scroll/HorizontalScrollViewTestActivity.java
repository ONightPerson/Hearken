package com.onightperson.hearken.scroll;

import android.os.Bundle;
import android.widget.ListView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/8/21.
 */

public class HorizontalScrollViewTestActivity extends BaseActivity {

    private ListView mListView;
    private InfoAdapter mInfoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll_layout);

        initViews();
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.class_info);
        mInfoAdapter = new InfoAdapter(this, ScrollUtils.getData());
        mListView.setAdapter(mInfoAdapter);
    }
}
