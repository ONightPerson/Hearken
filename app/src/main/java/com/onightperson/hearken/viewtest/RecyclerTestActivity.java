package com.onightperson.hearken.viewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.anim.CustomLayout;
import com.onightperson.hearken.viewtest.adapter.ContactAdapter;
import com.onightperson.hearken.viewtest.utils.DataUtils;

/**
 * Created by liubaozhu on 17/2/21.
 */

public class RecyclerTestActivity extends Activity implements View.OnClickListener {
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        initViews();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new ContactAdapter(DataUtils.getContactInfoList()));
    }

    @Override
    public void onClick(View v) {
    }
}
