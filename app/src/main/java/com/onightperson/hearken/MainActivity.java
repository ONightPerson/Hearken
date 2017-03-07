package com.onightperson.hearken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.text.TextChangeActivity;
import com.onightperson.hearken.viewtest.RecyclerTestActivity;
import com.onightperson.hearken.wave.WaveActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mLaunchWaveBtn;
    private Button mLaunchRecyclerViewBtn;
    private Button mLaunchTextChangeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wave_anim);
        initViews();
    }

    private void initViews() {
        mLaunchWaveBtn = (Button) findViewById(R.id.launch_wave_activity);
        mLaunchWaveBtn.setOnClickListener(this);
        mLaunchRecyclerViewBtn = (Button) findViewById(R.id.launch_recyclerview_activity);
        mLaunchRecyclerViewBtn.setOnClickListener(this);
        mLaunchTextChangeBtn = (Button) findViewById(R.id.launch_text_change_activity);
        mLaunchTextChangeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mLaunchWaveBtn) {
            Intent intent = new Intent(this, WaveActivity.class);
            startActivity(intent);
        } else if (v == mLaunchRecyclerViewBtn) {
            Intent intent = new Intent(this, RecyclerTestActivity.class);
            startActivity(intent);
        } else if (v == mLaunchTextChangeBtn) {
            Intent intent = new Intent(this, TextChangeActivity.class);
            startActivity(intent);
        }

        /*Intent intent = new Intent(this, RecyclerTestActivity.class);
        startActivity(intent);*/
    }
}
