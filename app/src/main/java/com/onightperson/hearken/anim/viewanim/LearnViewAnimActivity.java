package com.onightperson.hearken.anim.viewanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/3/19.
 */

public class LearnViewAnimActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LearnViewAnimActivity";

    private Button mDisplayAnimBtn;
    private Button mStartAnimBtn;
    private Button mStopAnimBtn;
    
    private ObjectAnimator mAnimA;
    private ObjectAnimator mAnimB;
    private ObjectAnimator mAnimC;
    private long mStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_view_anim);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews() {
        mDisplayAnimBtn = (Button) findViewById(R.id.display_anim);
        mStartAnimBtn = (Button) findViewById(R.id.start_property_anim);
        mStartAnimBtn.setOnClickListener(this);
        mStopAnimBtn = (Button) findViewById(R.id.stop_property_anim);
        mStopAnimBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mDisplayAnimBtn) {
            Toast.makeText(this, "展示动画Button已点击", Toast.LENGTH_SHORT).show();
        } else if (v == mStartAnimBtn) {
            startAnim();
        } else if (v == mStopAnimBtn) {
            stopAnim();
        }
    }

    private void startAnim() {
        startAnimationA();
    }

    private void stopAnim() {
//        mDisplayAnimBtn.clearAnimation();
        Log.i(TAG, "stopAnim: mDisplayAnimBtn.getAnimation(): " + mDisplayAnimBtn.getAnimation());
//        mAnimA.cancel();

        mAnimA.removeAllListeners();
        mAnimA.cancel();
        mAnimB.removeAllListeners();
        mAnimB.cancel();
        mAnimC.removeAllListeners();
        mAnimC.cancel();
    }
    
    private void startAnimationA() {
        // 向右平移 100 px
        mAnimA = ObjectAnimator.ofFloat(mDisplayAnimBtn, "translationX", 0, 100);
        mAnimA.setDuration(2000);
        mAnimA.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // A动画结束，启动B动画
                Log.i(TAG, "onAnimationEnd: A动画结束, 用时： "
                        + (System.currentTimeMillis() - mStartTime) / 1000 + " s");
                startAnimationB();
            }
        });
        mAnimA.start();
        mStartTime = System.currentTimeMillis();
    }
    
    private void startAnimationB() {
        // 向下移动 100 px
        mAnimB = ObjectAnimator.ofFloat(mDisplayAnimBtn, "translationY", 0, 100);
        mAnimB.setDuration(2000);
        mAnimB.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // B动画结束，启动C动画
                Log.i(TAG, "onAnimationEnd: B动画结束, 用时： "
                        + (System.currentTimeMillis() - mStartTime) / 1000 + " s");

                //
                startAnimationC();
            }
        });
        mAnimB.start();
        // 更新当前时间
        mStartTime = System.currentTimeMillis();
    }
    
    private void startAnimationC() {
        // 向下移动 100 px
        mAnimC = ObjectAnimator.ofFloat(mDisplayAnimBtn, "alpha", 1, 0, 1);
        mAnimC.setDuration(2000);
        mAnimC.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd: C动画结束, 用时： "
                        + (System.currentTimeMillis() - mStartTime) / 1000 + " s");
            }
        });
        mAnimC.start();
        // 更新当前时间
        mStartTime = System.currentTimeMillis();
    }
}
