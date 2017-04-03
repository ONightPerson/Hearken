package com.onightperson.hearken.anim.viewanim;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.onightperson.hearken.R;
import com.onightperson.hearken.news.ui.SupportFragmentActivity;

/**
 * Created by liubaozhu on 17/3/19.
 */

public class LearnViewAnimActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LearnViewAnimActivity";

    private Button mDisplayViewAnimBtn;
    private Button mStartTranslationAnimBtn;
    private Button mStartScaleAnimBtn;
    private Button mStartRotateAnimBtn;
    private Button mStartAlphaAnimBtn;
    private Button mBackUseViewAnimBtn;
    private Button mBackUsePropertyAnimBtn;
    private Button mStartAnimSetBtn;
    private boolean mIsUsePropertyAnim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_view_anim);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        startAnim();
    }

    private void initViews() {
        mDisplayViewAnimBtn = (Button) findViewById(R.id.display_view_anim);
        mStartTranslationAnimBtn = (Button) findViewById(R.id.start_translaction_anim);
        mStartTranslationAnimBtn.setOnClickListener(this);
        mStartScaleAnimBtn = (Button) findViewById(R.id.start_scale_anim);
        mStartScaleAnimBtn.setOnClickListener(this);
        mStartRotateAnimBtn = (Button) findViewById(R.id.start_rotate_anim);
        mStartRotateAnimBtn.setOnClickListener(this);
        mStartAlphaAnimBtn = (Button) findViewById(R.id.start_alpha_anim);
        mStartAlphaAnimBtn.setOnClickListener(this);
        mStartAnimSetBtn = (Button) findViewById(R.id.start_anim_set);
        mStartAnimSetBtn.setOnClickListener(this);
        mBackUseViewAnimBtn = (Button) findViewById(R.id.back_use_view_anim);
        mBackUseViewAnimBtn.setOnClickListener(this);
        mBackUsePropertyAnimBtn = (Button) findViewById(R.id.back_use_property_anim);
        mBackUsePropertyAnimBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mDisplayViewAnimBtn) {
            Toast.makeText(this, "展示动画Button已点击", Toast.LENGTH_SHORT).show();
        } else if (v == mStartTranslationAnimBtn) {
          /*  TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0, 100);
            transAnim.setDuration(500);
            transAnim.setFillAfter(true);*/

            Animation transAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim_btn);
            transAnim.setInterpolator(new CycleInterpolator(2.5f));
            transAnim.setDuration(1000);
            mDisplayViewAnimBtn.startAnimation(transAnim);
        } else if (v == mStartScaleAnimBtn) {
//            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.8f, 1, 1, 0, 0);
//            scaleAnimation.setDuration(500);
//            scaleAnimation.setFillAfter(true);
            Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_anim_btn);
            scaleAnimation.setInterpolator(new BounceInterpolator());
            scaleAnimation.setDuration(1000);
            mDisplayViewAnimBtn.startAnimation(scaleAnimation);
        } else if (v == mStartRotateAnimBtn) {
//            RotateAnimation rotateAnimation = new RotateAnimation(0, 30,
//                    mDisplayViewAnimBtn.getWidth() / 2 , mDisplayViewAnimBtn.getHeight() / 2);
//            rotateAnimation.setDuration(1000);
//            rotateAnimation.setFillAfter(true);
            Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_btn);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setFillAfter(true);
            mDisplayViewAnimBtn.startAnimation(rotateAnimation);
        } else if (v == mStartAlphaAnimBtn) {
//            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);
//            alphaAnimation.setDuration(500);
//            alphaAnimation.setFillAfter(true);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim_btn);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(1000);
            mDisplayViewAnimBtn.startAnimation(animation);
        } else if (v == mStartAnimSetBtn) {
           /* AnimationSet animSet = new AnimationSet(false);
            animSet.setInterpolator(new DecelerateInterpolator());
            animSet.setDuration(1000);
            animSet.setFillAfter(true);

            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.8f, 1, 1, 0, 0);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);

            animSet.addAnimation(scaleAnimation);
            animSet.addAnimation(alphaAnimation);*/

            Animation animSet = AnimationUtils.loadAnimation(this, R.anim.set_anim_btn);
            mDisplayViewAnimBtn.startAnimation(animSet);
        } else if (v == mBackUseViewAnimBtn) {
            Intent intent = new Intent(this, SupportFragmentActivity.class);
            startActivity(intent);
            mDisplayViewAnimBtn.setVisibility(View.GONE);
            mIsUsePropertyAnim = false;
            Log.i(TAG, "onClick: mIsUsePropertyAnim: " + mIsUsePropertyAnim);
        } else if (v == mBackUsePropertyAnimBtn) {
            Intent intent = new Intent(this, SupportFragmentActivity.class);
            startActivity(intent);
            mDisplayViewAnimBtn.setVisibility(View.GONE);
            mIsUsePropertyAnim = true;
            Log.i(TAG, "onClick: mIsUsePropertyAnim: " + mIsUsePropertyAnim);
        }
    }

    private void startAnim() {
        mDisplayViewAnimBtn.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                mDisplayViewAnimBtn.getLayoutParams();
        Log.i(TAG, "startAnim: height: " + params.height);
        params.height += 120;
        mDisplayViewAnimBtn.setLayoutParams(params);
        Log.i(TAG, "startAnim: mIsUsePropertyAnim: " + mIsUsePropertyAnim);
        if (mIsUsePropertyAnim) {
            startPropertyAlphaAnim();
        } else {
            startViewAlphaAnim();
        }
    }

    private void startPropertyAlphaAnim() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mDisplayViewAnimBtn, "alpha", 0f, 1f);
        anim.setDuration(500);
        anim.start();
    }

    private void startViewAlphaAnim() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.set_anim_btn);
        anim.setInterpolator(new LinearInterpolator());
        mDisplayViewAnimBtn.startAnimation(anim);
    }
}
