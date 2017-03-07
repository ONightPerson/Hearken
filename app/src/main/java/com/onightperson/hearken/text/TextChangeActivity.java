package com.onightperson.hearken.text;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.viewtest.utils.DataUtils;


/**
 * Created by liubaozhu on 17/3/1.
 */

public class TextChangeActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;
    private Button mInInBtn;
    private Button mInExBtn;
    private Button mExInBtn;
    private Button mExExBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_change_layout);

        initViews();
        initData();
    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.text_view);
        mInInBtn = (Button) findViewById(R.id.in_in);
        mInExBtn = (Button) findViewById(R.id.in_ex);
        mExInBtn = (Button) findViewById(R.id.ex_in);
        mExExBtn = (Button) findViewById(R.id.ex_ex);

        mInInBtn.setOnClickListener(this);
        mInExBtn.setOnClickListener(this);
        mExInBtn.setOnClickListener(this);
        mExExBtn.setOnClickListener(this);
    }

    private void initData() {
        mTextView.setText(getString(R.string.protected_time));
    }

    private void changeTextSize(int flags, int days) {
        CharSequence loveDays = getString(R.string.text_change_test, days);
        SpannableStringBuilder builder = new SpannableStringBuilder(loveDays);

        builder.setSpan(new AbsoluteSizeSpan(20, true), 3, loveDays.length() - 1, flags);
        mTextView.setText(builder);
    }

    @Override
    public void onClick(View v) {
        if (v == mInInBtn) {
            changeTextSize(Spanned.SPAN_INCLUSIVE_INCLUSIVE, 10);
        } else if (v == mInExBtn) {
            changeTextSize(Spanned.SPAN_INCLUSIVE_INCLUSIVE, 1);

        } else if (v == mExInBtn) {
            changeTextSize(Spanned.SPAN_INCLUSIVE_INCLUSIVE, 1000);

        } else if (v == mExExBtn) {
            changeTextSize(Spanned.SPAN_INCLUSIVE_INCLUSIVE, 100);

        }
    }
}
