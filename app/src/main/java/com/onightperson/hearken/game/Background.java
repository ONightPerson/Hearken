package com.onightperson.hearken.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.onightperson.hearken.util.BitmapUtils;
import com.onightperson.hearken.util.ScreenUtils;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class Background implements IDraw {
    private static final String TAG = "Background";

    private Context mContext;
    private Bitmap mBackgroundTop;
    private Bitmap mBackgroundBottom;
    private float mTopY;
    private float mBottomY;
    private int mScreenHeight;

    /**
     * 移动速度
     */
    private float mSpeedY;

    public Background(Context context, String background) {
        mContext = context;
        init(background);
    }

    @Override
    public void init(Object... objects) {
        mScreenHeight = ScreenUtils.getHeight();
        mBackgroundBottom = BitmapUtils.decodeSampleBitmap(mContext, (String) objects[0],
                ScreenUtils.getWidth(), mScreenHeight);
        mBackgroundTop = BitmapUtils.getVerticalFlipBitmap(mBackgroundBottom);
        //初始化时当前图片位置y坐标
        mBottomY = 0;
        //初始化时屏幕外上方图片位置y坐标
        mTopY = -mScreenHeight;
        Log.d(TAG, "mTopY: " + mTopY);
        mSpeedY = 10;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackgroundBottom, 0, mBottomY, null);
        canvas.drawBitmap(mBackgroundTop, 0, mTopY, null);
    }

    @Override
    public void updateUI() {
        mBottomY += mSpeedY;
        mTopY += mSpeedY;
        //判断 移出屏幕就重新设置到屏幕上方
        Log.d(TAG, "updateUI: (mBottomY, mTopY) = " + "(" + mBottomY + ", " + mTopY + ")");
        if (mBottomY >= mScreenHeight) {
            mBottomY = -mScreenHeight;
        }

        if (mTopY >= mScreenHeight) {
            mTopY = -mScreenHeight;
        }
    }
}
