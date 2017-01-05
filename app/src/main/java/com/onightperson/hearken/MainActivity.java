package com.onightperson.hearken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mLaunchSplitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mLaunchSplitBtn = (Button) findViewById(R.id.start_split_activity);
        mLaunchSplitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (mLaunchSplitBtn == v) {
            Intent intent = new Intent(this, SplitActivity.class);
            startActivity(intent);
        }
    }
}
