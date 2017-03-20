package com.onightperson.hearken.anim.viewanim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.onightperson.hearken.R;

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
    private Button mStartAnimSetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_view_anim);

        initViews();
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
    }

    @Override
    public void onClick(View v) {
        if (v == mDisplayViewAnimBtn) {
            Toast.makeText(this, "展示动画Button已点击", Toast.LENGTH_SHORT).show();
        } else if (v == mStartTranslationAnimBtn) {
            TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0, 100);
            transAnim.setDuration(500);
            transAnim.setFillAfter(true);
            mDisplayViewAnimBtn.startAnimation(transAnim);
        } else if (v == mStartScaleAnimBtn) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.8f, 1, 1, 0, 0);
            scaleAnimation.setDuration(500);
            scaleAnimation.setFillAfter(true);
            mDisplayViewAnimBtn.startAnimation(scaleAnimation);
        } else if (v == mStartRotateAnimBtn) {
            RotateAnimation rotateAnimation = new RotateAnimation(0, 30,
                    mDisplayViewAnimBtn.getWidth() / 2 , mDisplayViewAnimBtn.getHeight() / 2);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setFillAfter(true);
            mDisplayViewAnimBtn.startAnimation(rotateAnimation);
        } else if (v == mStartAlphaAnimBtn) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            mDisplayViewAnimBtn.startAnimation(alphaAnimation);
        } else if (v == mStartAnimSetBtn) {
            AnimationSet animSet = new AnimationSet(false);
            animSet.setInterpolator(new DecelerateInterpolator());
            animSet.setDuration(1000);
            animSet.setFillAfter(true);



            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.8f, 1, 1, 0, 0);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);

            animSet.addAnimation(scaleAnimation);
            animSet.addAnimation(alphaAnimation);

            mDisplayViewAnimBtn.startAnimation(animSet);
        }
    }
}
