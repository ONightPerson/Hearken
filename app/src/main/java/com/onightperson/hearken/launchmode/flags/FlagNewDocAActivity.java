package com.onightperson.hearken.launchmode.flags;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.HearkenActivity;

/**
 * Created by liubaozhu on 17/6/14.
 */

public class FlagNewDocAActivity extends HearkenActivity implements View.OnClickListener {

    private Button mFlagNewDocBBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_new_doc_a);

        initViews();
    }

    private void initViews() {
        mFlagNewDocBBtn = (Button) findViewById(R.id.flag_new_doc_b_btn);
        mFlagNewDocBBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mFlagNewDocBBtn) {
            intent = new Intent(this, FlagNewDocBActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
