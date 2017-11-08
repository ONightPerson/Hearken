package com.onightperson.hearken.viewworkprinciple.progress;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onightperson.hearken.R;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liubaozhu on 17/5/2.
 */

public class ProgressActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "ProgressActivity";

    private TextView mTextColorTextView;
    private int mCurCount;
    private Button mChangePosBtn;
    private ImageView mOneView;
    private ImageView mOtherView;
    private View mOneTipsView;
    private View mOtherTipsView;

    private static final String INTERNAL_PATH = "/storage/emulated/0";
    private static final String EXTERNAL_PATH = "/storage/sdcard1";

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
        mChangePosBtn = (Button) findViewById(R.id.change_pos_btn);
        mChangePosBtn.setOnClickListener(this);
        mOneView = (ImageView) findViewById(R.id.one_view);
        mOtherView = (ImageView) findViewById(R.id.other_view);
        mOneTipsView = findViewById(R.id.one_tips_view);
        mOtherTipsView = findViewById(R.id.other_tips_view);

        updateRecycleBinView(true);

        execShell();
//        execRenameTo();
    }

    private void execRenameTo() {
        String srcPath = INTERNAL_PATH + "/DCIM/Camera/1.mp4";
        String desPath = EXTERNAL_PATH + "/DCIM/Camera/2.mp4";
        File srcFile = new File(srcPath);
        File desFile = new File(desPath);
        boolean renameTo = srcFile.renameTo(desFile);
        Log.i(TAG, "execRenameTo: renameTo: " + renameTo);
    }

    private void updateRecycleBinView(boolean showOneView) {
        if (showOneView) {
            mOneView.setVisibility(View.VISIBLE);
            mOneTipsView.setVisibility(View.VISIBLE);

            mOtherView.setVisibility(View.VISIBLE);
            mOtherTipsView.setVisibility(View.VISIBLE);
        } else {

            mOneView.setVisibility(View.GONE);
            mOneTipsView.setVisibility(View.GONE);
            RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams)
                    mOtherView.getLayoutParams();
            rl.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16,
                    getResources().getDisplayMetrics());
            mOtherView.setVisibility(View.VISIBLE);
            mOtherView.setLayoutParams(rl);
            mOtherTipsView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        if (v == mChangePosBtn) {
            updateRecycleBinView(false);
        }
    }

    public void execShell() {
        Process process = null;
        try {
            // 权限限制
            process = Runtime.getRuntime().exec(new String[] {"sh", "-c", "rm -rf "
                    + EXTERNAL_PATH + "/DCIM/Camera/3.mp4"});
            Log.i(TAG, "execShell: process: " + process);
            // 获取输出流
            PrintWriter e = new PrintWriter(process.getOutputStream());
            // 将命令写入
            e.println();
            // 提交命令
            e.flush();
            e.close();
            int result = process.waitFor();
            Log.i(TAG, "result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }
}
