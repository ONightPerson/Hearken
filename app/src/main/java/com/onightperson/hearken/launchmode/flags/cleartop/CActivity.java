package com.onightperson.hearken.launchmode.flags.cleartop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

/**
 * Created by liubaozhu on 17/6/15.
 */

public class CActivity extends BaseActivity implements View.OnClickListener {

    private Button mStartABtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_layout);
        initViews();
    }

    private void initViews() {
        mStartABtn = (Button) findViewById(R.id.start_next);
        mStartABtn.setText("启动 A Activity");
        mStartABtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mStartABtn) {
            intent = new Intent(this, AActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
