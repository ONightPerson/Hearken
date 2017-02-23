package com.onightperson.hearken.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liubaozhu on 17/2/22.
 */

public class CustomView extends View {

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipRect(0, 0, 100, 100);
        canvas.drawColor(Color.BLUE);
    }
}
