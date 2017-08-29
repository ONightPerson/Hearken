package com.onightperson.hearken.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * Created by liubaozhu on 17/8/21.
 */

public class LeftSlideScrollView extends HorizontalScrollView {
    private static final String TAG = "LeftSlideScrollView";

    private static final float RATIO_MIN_DISTANCE_AUTO_SLIDE_OUT = 0.25f;

    private int mScreenWidth;
    private float mAutoSlideOutDistance;
    private RemoveListener mRemoveListener;
    private View mTipView;
    private int mScrollWidth;

    public LeftSlideScrollView(Context context) {
        this(context, null);
    }

    public LeftSlideScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftSlideScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context cxt) {
        setOverScrollMode(OVER_SCROLL_NEVER);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) cxt.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        Log.i(TAG, "initData: mScreenWidth: " + mScreenWidth);
        mAutoSlideOutDistance = RATIO_MIN_DISTANCE_AUTO_SLIDE_OUT * mScreenWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout: l: " + l + ", r: " + r);
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            int selfWidth = getWidth();
            Log.i(TAG, "onLayout: selfWidth: " + selfWidth);
            if (mTipView != null) {
                Log.i(TAG, "onLayout: tipview width: " + mTipView.getWidth());
            }
            mScrollWidth = mTipView != null ? mTipView.getWidth() : selfWidth;
        }
        Log.i(TAG, "onLayout: mScrollWidth: " + mScrollWidth);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                changeScrollX();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void changeScrollX() {
        int scrollX = getScrollX();
        if (scrollX > mAutoSlideOutDistance) {
            if (mRemoveListener != null) {
                mRemoveListener.onRemove();
            }
//            smoothScrollTo(mScreenWidth, 0);
        } else {
            // 滑回原位
            smoothScrollTo(0, 0);
        }
    }

    public interface RemoveListener {
        void onRemove();
    }

    public void setRemoveListener(RemoveListener listener) {
        mRemoveListener = listener;
    }

    public void setTipView(View tipView) {
        mTipView = tipView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.i(TAG, "onScrollChanged: l: " + l + ", t: " + t + ", oldl: " + oldl + ", oldt: " + oldt);
        if (l >= mScrollWidth) {
            l = mScrollWidth;
        }
        super.onScrollChanged(l, t, oldl, oldt);
        if (mTipView != null) {
            mTipView.setTranslationX(l - mScrollWidth);
        }
    }
}
