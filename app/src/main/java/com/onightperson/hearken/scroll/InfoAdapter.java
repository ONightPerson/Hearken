package com.onightperson.hearken.scroll;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.onightperson.hearken.R;

import java.util.List;

/**
 * Created by liubaozhu on 17/8/21.
 */

public class InfoAdapter extends BaseAdapter {
    private static final String TAG = "InfoAdapter";

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mData;

    public InfoAdapter(Context context, List<String> data) {
        Log.i(TAG, "InfoAdapter: current thread id: " + Thread.currentThread().getId());
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mData = data;
    }


    @Override
    public int getCount() {
        int size = mData.size();
        Log.i(TAG, "getCount: size: " + size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: position: " + position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.from(mContext).inflate(R.layout.student_info, parent, false);
            holder.mLeftSlideView = (LeftSlideScrollView)
                    convertView.findViewById(R.id.left_slide_view);
            holder.mLeftSlideView.setRemoveListener(holder);
            holder.mNameView = (TextView) convertView.findViewById(R.id.names);
            holder.mLayoutContentView = convertView.findViewById(R.id.layout_content);
            int screenWidth = getScreenWidth();
            holder.mLayoutContentView.getLayoutParams().width = screenWidth;
            holder.mNameView.getLayoutParams().width = screenWidth;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mLeftSlideView.setTag(position);
        holder.mLeftSlideView.setTipView(holder.mNameView);
        holder.mLayoutContentView.setTranslationX(0);
        holder.mNameView.setAlpha(1f);
        holder.mLeftSlideView.scrollTo(0, 0);
        String name = (String) getItem(position);
        holder.mNameView.setText(name);

        return convertView;
    }

    class ViewHolder implements LeftSlideScrollView.RemoveListener {
        public LeftSlideScrollView mLeftSlideView;
        public TextView mNameView;
        public View mLayoutContentView;

        @Override
        public void onRemove() {
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator transAnim = ObjectAnimator.ofFloat(mLayoutContentView, "translationX",
                    0, -mLayoutContentView.getWidth());
            ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mNameView, "alpha", 1, 0);
            set.playTogether(transAnim, alphaAnim);
            set.setDuration(400);
            set.setInterpolator(new LinearInterpolator());
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    int position = (int) mLeftSlideView.getTag();
                    Log.i(TAG, "onAnimationEnd: position: " + position
                            + ", current thread id: " + Thread.currentThread().getId());
                    mData.remove(position);
                    notifyDataSetChanged();
                }
            });
            set.start();

        }
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
