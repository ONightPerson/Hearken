package com.onightperson.hearken.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.news.constant.NewsConstant;
import com.onightperson.hearken.news.ui.adapter.NewsTitleAdapter;
import com.onightperson.hearken.news.utils.NewsUtils;

/**
 * Created by liubaozhu on 17/1/10.
 */

public class NewsActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "NewsActivity";

    //ui
    private ListView mTitleListView;
    private NewsTitleAdapter mAdapter;
    //data
    private String[] mTitles;
    private String[] mContents;
    private boolean mIsTwoPane = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initViews();
        initData();
    }

    private void initViews() {
        mTitleListView = (ListView) findViewById(R.id.new_title_list_view);
        mTitleListView.setOnItemClickListener(this);

        View view = findViewById(R.id.content_container);
        Log.d(TAG, "initViews--view: " + view);
        mIsTwoPane = view != null;
    }

    private void initData() {
        mTitles = NewsUtils.getTitles();
        mContents = NewsUtils.getContents();
        Log.d(TAG, "initData: " + mTitles);
        mAdapter = new NewsTitleAdapter(this, R.layout.news_title_item_layout, mTitles);
        mTitleListView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mIsTwoPane) {
            NewsContentFragment contentFragment = (NewsContentFragment)
                    getSupportFragmentManager().findFragmentById(R.id.news_content);

            contentFragment.setContent(mTitles[position], mContents[position]);
            /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.show(contentFragment);
            transaction.commit();*/
        } else {
            Intent intent = new Intent(this, NewsContentActivity.class);
            intent.putExtra(NewsConstant.KEY_NEWS_TITLE, mTitles[position]);
            intent.putExtra(NewsConstant.KEY_NEWS_CONTENT, mContents[position]);
            startActivity(intent);
        }

    }
}
