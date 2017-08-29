package com.onightperson.hearken.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

/**
 * Created by liubaozhu on 17/8/28.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    private static final String TAG = "MyHorizontalScrollView";

    public MyHorizontalScrollView(Context context) {
        super(context, null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: width mode: " + MeasureSpec.getMode(widthMeasureSpec)
                + ", height mode: " + MeasureSpec.getMode(heightMeasureSpec)
                + ", EXACTLY: " + MeasureSpec.EXACTLY
                + ", width size: " + MeasureSpec.getSize(widthMeasureSpec)
                + ", height size: " + MeasureSpec.getSize(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int childCount = getChildCount();
//        Log.i(TAG, "onLayout: changed: "  + changed + ", l: " + l + ", t: " + t
//                + ", r: " + r + ", b: " + b);
//        int childWidth = 0;
//        int childMargins = 0;
//        if (childCount > 0) {
//            childWidth = getChildAt(0).getMeasuredWidth();
//            LayoutParams childParams = (LayoutParams) getChildAt(0).getLayoutParams();
//            childMargins = childParams.leftMargin + childParams.rightMargin;
//        }
//        Log.i(TAG, "onLayout: childWidth: " + childWidth + ", childMargins: " + childMargins);
//        final int available = r - l - childMargins;
//        Log.i(TAG, "onLayout: available: " + available + ", forceLeftGravity: " +  (childWidth > available));
        super.onLayout(changed, l, t, r, b);
    }
}
