package com.onightperson.hearken.viewworkprinciple.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/2/21.
 */

public class CustomViewActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        initViews();
    }

    private void initViews() {

    }

    @Override
    public void onClick(View v) {

    }
}
