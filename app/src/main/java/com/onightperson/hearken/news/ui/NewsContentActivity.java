package com.onightperson.hearken.news.ui;

import android.os.Bundle;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseFragmentActivity;
import com.onightperson.hearken.news.constant.NewsConstant;

/**
 * Created by liubaozhu on 17/1/10.
 */

public class NewsContentActivity extends BaseFragmentActivity {
    private static final String TAG = "NewsContentActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String title = getIntent().getStringExtra(NewsConstant.KEY_NEWS_TITLE);
        String content = getIntent().getStringExtra(NewsConstant.KEY_NEWS_CONTENT);
        updateFragment(title, content);
    }

    private void updateFragment(String title, String content) {
        NewsContentFragment contentFragment = (NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content);
        contentFragment.setContent(title, content);
    }
}
