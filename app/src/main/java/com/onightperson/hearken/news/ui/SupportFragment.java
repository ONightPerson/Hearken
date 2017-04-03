package com.onightperson.hearken.news.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.onightperson.hearken.R;
import com.onightperson.hearken.anim.viewanim.LearnViewAnimActivity;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class SupportFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SupportFragment";
    private Button mLaunchAnyActivityBtn;
    private Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);
        initViews(view);
        Log.i(TAG, "onCreateView");
        return view;
    }

    private void initViews(View view) {
        mLaunchAnyActivityBtn = (Button) view.findViewById(R.id.launch_any_activity);
        mLaunchAnyActivityBtn.setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: context");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i(TAG, "onViewStateRestored: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == mLaunchAnyActivityBtn) {
            intent = new Intent(mActivity, EmptyFragmentActivity.class);
            intent.setSourceBounds(getSourceBounds(v));
        }

        if (intent != null) {
            mActivity.startActivity(intent);
        }

    }

    private Rect getSourceBounds(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);

        location[1] = location[1] - getStatusBarHeight();

        return new Rect(location[0], location[1],
                location[0] + v.getWidth(), location[1] + v.getHeight());
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = mActivity.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = mActivity.getResources().getDimensionPixelOffset(resourceId);
        }
        return result;
    }
}
