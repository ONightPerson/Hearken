package com.onightperson.hearken.datetime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.util.ProblemUtils;

/**
 * Created by liubaozhu on 17/7/9.
 */

public class DateTimeActivity extends BaseActivity implements View.OnClickListener {

    private static int add = 0;
    private Button mTestWeekBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        initViews();
    }

    private void initViews() {
        mTestWeekBtn = (Button) findViewById(R.id.test_week);
        mTestWeekBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTestWeekBtn) {
            DateTimeUtils.testWeek(add++);
            ProblemUtils.testArrayListCopy();
        }
    }
}
