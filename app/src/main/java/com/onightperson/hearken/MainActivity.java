package com.onightperson.hearken;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.anim.CustomViewActivity;
import com.onightperson.hearken.anim.WaveView;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.news.ui.NewsActivity;
import com.onightperson.hearken.storage.StorageUtils;
import com.onightperson.hearken.viewtest.RecyclerTestActivity;
import com.onightperson.hearken.wave.WaveActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

//    private Button mLaunchNewsBtn;
    private WaveView mWaveView;
    private Button mChangePercentBtn;
    private Button mChangeWaveLengthBtn;
    private Button mChangeWavePaceBtn;
    private Button mChangeZhengfuBtn;

    private float mPercent = 0.2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wave_anim);
        initViews();
    }

    private void initViews() {
//        mLaunchNewsBtn = (Button) findViewById(R.id.start_news_activity);
//        mLaunchNewsBtn.setOnClickListener(this);

        mWaveView = (WaveView) findViewById(R.id.wave_view);
        mChangePercentBtn = (Button) findViewById(R.id.change_percent_btn);
        mChangePercentBtn.setOnClickListener(this);
        mChangeWaveLengthBtn = (Button) findViewById(R.id.change_wave_length);
        mChangeWaveLengthBtn.setOnClickListener(this);
        mChangeWavePaceBtn = (Button) findViewById(R.id.change_wave_pace);
        mChangeWavePaceBtn.setOnClickListener(this);
        mChangeZhengfuBtn = (Button) findViewById(R.id.change_wave_zhengfu);
        mChangeZhengfuBtn.setOnClickListener(this);

        mWaveView.setPercent(mPercent);

    }

    @Override
    public void onClick(View v) {
//        if (mLaunchNewsBtn == v) {
//            Intent intent = new Intent(this, NewsActivity.class);
//            startActivity(intent);
//            StorageUtils.showSeriousDirectories();
//        }

        if (v == mChangePercentBtn) {
            if (mPercent >= 1.0f) {
                mPercent = 0.2f;
            } else {
                mPercent += 0.1f;
            }
            mWaveView.setPercent(mPercent);
        } else if (v == mChangeWaveLengthBtn) {
            mWaveView.changeWaveLength();
        } else if (v == mChangeWavePaceBtn) {
            mWaveView.changeWavePace();
        } else if (v == mChangeZhengfuBtn) {
//            mWaveView.changeWavezhengfu();
            Intent intent = new Intent(this, WaveActivity.class);
            startActivity(intent);
        }

        /*Intent intent = new Intent(this, RecyclerTestActivity.class);
        startActivity(intent);*/
    }
}
