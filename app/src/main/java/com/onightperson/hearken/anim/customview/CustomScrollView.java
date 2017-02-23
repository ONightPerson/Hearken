package com.onightperson.hearken.anim.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ScrollView;

/**
 * Created by liubaozhu on 17/2/22.
 */

public class CustomScrollView extends ScrollView {
    private static final String TAG = "CustomScrollView";
    private static final int MAX_OVER_SCROLL_Y = 200;

    private int mNewOverScrollY;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Log.d(TAG, "init--metrics: (" + metrics.xdpi + ", " + metrics.ydpi + ")");
        mNewOverScrollY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, MAX_OVER_SCROLL_Y,
                context.getResources().getDisplayMetrics());

        ////不管装载的控件填充的数据是否满屏，都允许橡皮筋一样的弹性回弹
        setOverScrollMode(OVER_SCROLL_ALWAYS);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mNewOverScrollY, isTouchEvent);
    }
}
