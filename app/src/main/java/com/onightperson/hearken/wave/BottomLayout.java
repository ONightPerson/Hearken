package com.onightperson.hearken.wave;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onightperson.hearken.viewtest.utils.DataUtils;

import com.onightperson.hearken.R;


public class BottomLayout extends RelativeLayout {
    private static final String TAG = "BottomLayout";

    private static final int SNAP_VELOCITY_LIMIT = 600;

    private static int REFRESH_INTERVAL = 30;
    private RelativeLayout mTextLayout;
    private TextView mProtectedDaysView;
    private Wave mWaveView;
    private LayoutParams mWaveParams;

    private int textSize = 20;
    private int mInitialWaveHeight = 0;
    //移动的位移
    private float mDisplacement = 0.0f;
    private Thread mDrawThread;
    private boolean mKeepAlive;
    private ValueAnimator mDropDownAnim;
    private ValueAnimator mDragUpAnim;
    private float mCurY;
    private VelocityTracker mVelocityTracker;
    private int mInitialNetWaveHeight;


    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "BottomLayout");
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        mTextLayout = (RelativeLayout) findViewById(R.id.text_area);
        Log.d(TAG, "onVisibilityChanged--mTextLayout: " + mTextLayout);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        Log.d(TAG, "onViewAdded--child: " + child);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate");
        initData(getContext());
    }

    private void initData(Context context) {
        mTextLayout = (RelativeLayout) findViewById(R.id.text_area);

        //保护天数字体设置
        int protectedDays = DataUtils.getProtectedDays();
        String protectedInfo = context.getString(R.string.protected_time, protectedDays);
        mProtectedDaysView = (TextView) findViewById(R.id.protected_days);

        SpannableStringBuilder spanBuilder = new SpannableStringBuilder(protectedInfo);
        spanBuilder.setSpan(new AbsoluteSizeSpan(textSize, true), 4, protectedInfo.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mProtectedDaysView.setText(spanBuilder);
        mWaveView = (Wave) findViewById(R.id.wave);
        mWaveParams = (LayoutParams) mWaveView.getLayoutParams();
        Log.d(TAG, "initData: wave height: " + mWaveParams.height + ", wave height by getheight: " + mWaveView.getHeight());

        mTextLayout = (RelativeLayout) findViewById(R.id.text_area);
        mInitialWaveHeight = (int) DataUtils.dp2px(context, 12);
        mInitialNetWaveHeight = (int) DataUtils.dp2px(context, 9);

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
        stopDropDownAnim();


        int delatY = (int) (mCurY - y);
        Log.d(TAG, "onDragUp: delatY: " + delatY);
        if (delatY < 0) {
            return;
        }
        mCurY = y;
        mDisplacement += delatY;
        Log.d(TAG, "onDragUp: mDisplacement: " + mDisplacement + ", 最大滑动距离： " + 2 * mInitialWaveHeight);
        if (mDisplacement >= 2 * mInitialWaveHeight) {
            return;
        }
        mWaveParams.height += delatY;
        Log.d(TAG, "onDragUp: cal path before");
        mWaveView.calPath(mWaveParams.height, delatY);
        Log.d(TAG, "onDragUp: cal path after");
        requestLayout();
        Log.d(TAG, "onDragUp: requestLayout");
    }

    public void onDropDown() {

        Log.d(TAG, "onDropDown: velocity: " + getScrollVelocity());
        if (getScrollVelocity() < -SNAP_VELOCITY_LIMIT) {
            startDragUpAnim();
        } else {
            startDropDownAnim();

        }
        recycleVelocityTracker();
    }

    private void startDragUpAnim() {
        mDragUpAnim = ValueAnimator.ofFloat(mWaveView.getMeasuredHeight(), 3 * mInitialWaveHeight);
        mDragUpAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float var = (Float) animation.getAnimatedValue();
                float displacement = var - mInitialWaveHeight;
                float offset = displacement - mDisplacement;
                mDisplacement = displacement;
                mWaveParams.height = (int) var;
                mWaveView.calPath(mWaveParams.height, offset);
                mWaveView.setLayoutParams(mWaveParams);
            }
        });
        mDragUpAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
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
        if (mDragUpAnim != null && mDragUpAnim.isRunning()) {
            mDragUpAnim.cancel();
        }
    }


    public void startDropDownAnim() {

        mDropDownAnim = ValueAnimator.ofFloat(mWaveView.getMeasuredHeight(), mInitialWaveHeight);
        mDropDownAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float var = (Float) animation.getAnimatedValue();
                float displacement = var - mInitialWaveHeight;
                float offset = displacement - mDisplacement;
//                Log.d(TAG, "onAnimationUpdate: offset: " + offset);
                mDisplacement = displacement;
//                Log.d(TAG, "onAnimationUpdate: mDisplacement: " + mDisplacement);
                mWaveParams.height = (int) var;
                mWaveView.calPath(mWaveParams.height, offset);
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
        Log.d(TAG, "addVelocityTracker--mVelocityTracker: " + mVelocityTracker);
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(e);
        Log.d(TAG, "addVelocityTracker--mVelocityTracker after: " + mVelocityTracker);

    }

    public void recycleVelocityTracker() {
        Log.d(TAG, "recycleVelocityTracker");
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public int getScrollVelocity() {
        Log.d(TAG, "getScrollVelocity: mTracker: " + mVelocityTracker);
        if (mVelocityTracker != null) {
            mVelocityTracker.computeCurrentVelocity(1000);
            Log.d(TAG, "getScrollVelocity: getYVelocity: " + mVelocityTracker.getYVelocity());
            return (int) mVelocityTracker.getYVelocity();
        }
        return 0;
    }
}