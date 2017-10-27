package com.onightperson.hearken.viewworkprinciple.progress;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/5/2.
 */

public class ProgressActivity extends Activity {
    private TextView mTextColorTextView;
    private int mCurCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.progress_activity_layout);
        initData();
        initViews();
    }

    private void initData() {
        setCurBabyCount(5);
    }

    public void setCurBabyCount(int count) {
        changeCount(count);
        mCurCount = count;
    }

    public void changeCount(int count) {
        count = 6;
    }

    private void initViews() {
        mTextColorTextView = (TextView) findViewById(R.id.test_color_text);
        mTextColorTextView.setText(Html.fromHtml(String.format(getString(R.string.test_color_text), mCurCount)));
    }
}
