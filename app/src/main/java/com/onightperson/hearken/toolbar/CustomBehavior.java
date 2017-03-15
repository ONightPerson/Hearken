package com.onightperson.hearken.toolbar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.onightperson.hearken.wave.Wave;

/**
 * Created by liubaozhu on 17/3/10.
 */

public class CustomBehavior extends CoordinatorLayout.Behavior<Wave> {
    private static final String TAG = "CustomBehavior";

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Wave child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Wave child, View dependency) {
        int[] dependencyLocation = new int[2];
        int[] waveLocation = new int[2];
        int[] dependencyLocationOnScreen = new int[2];
        int[] waveLocationOnScreen = new int[2];


        dependency.getLocationInWindow(dependencyLocation);
        child.getLocationInWindow(waveLocation);
        dependency.getLocationOnScreen(dependencyLocationOnScreen);
        child.getLocationOnScreen(waveLocationOnScreen);

        Log.i(TAG, "onDependentViewChanged: dependencyLocation=(" + dependencyLocation[0] + ", " + dependencyLocation[1] + ")"
                + ", waveLocation=(" + waveLocation[0] + ", " + waveLocation[1] + ")"
                + ", dependency location on Screen=(" + dependencyLocationOnScreen[0] + ", " + dependencyLocationOnScreen[1] + ")"
                + ", wave location on screen=(" + waveLocationOnScreen[0] + ", " + waveLocationOnScreen[1] + ")");

        int diff = waveLocation[1] - dependencyLocation[1];
        Log.i(TAG, "onDependentViewChanged: diff: " + diff);

        float scale =  diff / (float) waveLocation[1];

        if (diff > 0) {
            child.setScaleX(1 + scale);
            child.setScaleY(1 + scale);
        }
       return false;
    }
}
