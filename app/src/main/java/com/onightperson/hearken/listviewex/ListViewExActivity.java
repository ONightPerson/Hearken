package com.onightperson.hearken.listviewex;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.listviewex.adapter.MyExpandableListAdapter;

/**
 * Created by liubaozhu on 17/3/13.
 */

public class ListViewExActivity extends Activity {

    private ExpandableListView mListView;
    private MyExpandableListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewex_layout);

        initViews();
        initData();
    }

    private void initViews() {
        mListView = (ExpandableListView) findViewById(R.id.expandable_listview);
    }

    private void initData() {
        mAdapter = new MyExpandableListAdapter(this,
                R.layout.expandablelistviewex_group_layout,
                R.layout.expandablelistviewex_child_layout);

        mListView.setAdapter(mAdapter);
    }
}
