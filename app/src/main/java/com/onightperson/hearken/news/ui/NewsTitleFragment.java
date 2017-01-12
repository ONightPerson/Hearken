package com.onightperson.hearken.news.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class NewsTitleFragment extends Fragment {
    private static final String TAG = "NewsTitleFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: container: " + container);
        return inflater.inflate(R.layout.fragment_news_title, container, false);
    }
}
