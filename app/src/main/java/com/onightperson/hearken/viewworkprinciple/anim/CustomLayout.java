package com.onightperson.hearken.viewworkprinciple.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by liubaozhu on 17/2/21.
 */

public class CustomLayout extends LinearLayout {
    private CustomView mCustomView;

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        mCustomView = new CustomView(context, attrs);
        addView(mCustomView, new LayoutParams(LayoutParams.MATCH_PARENT,
                200));

        addView(new BelowView(context), new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1.0f));
    }

    public void changeParams() {
        LayoutParams params = (LinearLayout.LayoutParams) mCustomView.getLayoutParams();
        params.height = 20;
        params.topMargin = 400;
        mCustomView.setLayoutParams(params);
    }

    class BelowView extends View {

        public BelowView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(0x66619bf7);
        }
    }

}
