package com.onightperson.hearken.wave;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onightperson.hearken.viewtest.utils.DataUtils;

import com.onightperson.hearken.R;


public class BottomLayout extends RelativeLayout {
    private static final String TAG = "BottomLayout";

    private static final int SNAP_VELOCITY_LIMIT = 500;

    private TextView mProtectedDaysView;
    private Wave mWaveView;
    private LayoutParams mWaveParams;

    private int mInitialWaveHeight = 0;
    //移动的位移
    private float mDisplacement = 0.0f;
    private ValueAnimator mDropDownAnim;
    private ValueAnimator mDragUpAnim;
    private float mCurY;
    private VelocityTracker mVelocityTracker;


    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initData(getContext());
    }

    private void initData(Context context) {
        int protectedDays = DataUtils.getProtectedDays();
        mProtectedDaysView = (TextView) findViewById(R.id.protected_days);
        mProtectedDaysView.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "fonts/typeface.ttf"));
        mProtectedDaysView.setText(String.valueOf(protectedDays));
        mWaveView = (Wave) findViewById(R.id.wave);
        mWaveParams = (LayoutParams) mWaveView.getLayoutParams();

        mInitialWaveHeight = (int) DataUtils.dp2px(context, 12);

    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }

    public void initDragOp(float y) {
        mCurY = y;
    }

    public void addVelocityTracker(MotionEvent e) {
        trackVelocity(e);
    }

    public void onDragUp(float y) {
        if (getScrollVelocity() < -SNAP_VELOCITY_LIMIT) {
            stopDropDownAnim();
            startDragUpAnim();
        } else {
            Log.d(TAG, "onDragUp: 开始上拉, 上拉的总高度为： " + 2 * mInitialWaveHeight);
            long currentTime = System.currentTimeMillis();
            stopDragUpAnim();
            stopDropDownAnim();
            Log.d(TAG, "onDragUp: 停止动画耗时: " + (System.currentTimeMillis() - currentTime) + " ms");
            Log.d(TAG, "onDragUp: ");

            int delatY = (int) (mCurY - y);
            Log.d(TAG, "onDragUp: 距上次位置偏移: " + delatY);
            if (delatY < 0) {
                return;
            }
            mCurY = y;
            Log.d(TAG, "onDragUp: 上次位移: " + mDisplacement);
            mDisplacement += delatY;
            Log.d(TAG, "onDragUp: 这次位移: " + mDisplacement);
            if (mDisplacement >= 2 * mInitialWaveHeight) {
                return;
            }
            mWaveParams.height += delatY;
            mWaveView.calPath(mWaveParams.height);
            requestLayout();

        }
    }

    public void onDropDown() {
        boolean isVelocityOverLimit = getScrollVelocity() < -SNAP_VELOCITY_LIMIT;
        Log.d(TAG, "onDropDown: 速率大: " + isVelocityOverLimit);

        if (isVelocityOverLimit) {
            startDragUpAnim();
        } else {
            startDropDownAnim();

        }
        recycleVelocityTracker();
    }

    private void startDragUpAnim() {
        //drag anim is running
        if (mDragUpAnim != null && mDragUpAnim.isRunning()) {
            return;
        }

        Log.d(TAG, "startDragUpAnim");

        mDragUpAnim = ValueAnimator.ofFloat(mWaveView.getMeasuredHeight(), 3 * mInitialWaveHeight);
        mDragUpAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float var = (Float) animation.getAnimatedValue();
                float displacement = var - mInitialWaveHeight;
                mDisplacement = displacement;
                mWaveParams.height = (int) var;
                mWaveView.calPath(mWaveParams.height);
                mWaveView.setLayoutParams(mWaveParams);
            }
        });
        mDragUpAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd");
                startDropDownAnim();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mDragUpAnim.setDuration(500);
        mDragUpAnim.start();
    }

    private void stopDragUpAnim() {
        Log.d(TAG, "stopDragUpAnim");
        if (mDragUpAnim != null && mDragUpAnim.isRunning()) {
            mDragUpAnim.cancel();
        }
    }


    public void startDropDownAnim() {
        if (mDropDownAnim != null && mDropDownAnim.isRunning()) {
            return;
        }

        Log.d(TAG, "startDropDownAnim");
        mDropDownAnim = ValueAnimator.ofFloat(mWaveView.getMeasuredHeight(), mInitialWaveHeight);
        mDropDownAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float var = (Float) animation.getAnimatedValue();
                float displacement = var - mInitialWaveHeight;
                mDisplacement = displacement;
                mWaveParams.height = (int) var;
                mWaveView.calPath(mWaveParams.height);
                mWaveView.setLayoutParams(mWaveParams);
            }
        });
        mDropDownAnim.setDuration(1500);

        mDropDownAnim.start();
    }

    public void stopDropDownAnim() {
        if (mDropDownAnim != null && mDropDownAnim.isRunning()) {
            mDropDownAnim.cancel();
        }
    }

    private void trackVelocity(MotionEvent e) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(e);

    }

    public void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public int getScrollVelocity() {
        if (mVelocityTracker != null) {
            mVelocityTracker.computeCurrentVelocity(1000);
            return (int) mVelocityTracker.getYVelocity();
        }
        return 0;
    }
}