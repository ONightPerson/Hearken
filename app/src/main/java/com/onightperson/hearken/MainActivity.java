package com.onightperson.hearken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.news.ui.NewsActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mLaunchNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mLaunchNewsBtn = (Button) findViewById(R.id.start_news_activity);
        mLaunchNewsBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (mLaunchNewsBtn == v) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }
    }
}
