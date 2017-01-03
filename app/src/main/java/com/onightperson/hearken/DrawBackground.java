package com.onightperson.hearken;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.onightperson.hearken.util.BitmapUtil;
import com.onightperson.hearken.util.ScreenUtil;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class DrawBackground implements IGameDraw {

    private Context mContext;
    private Bitmap mBackgroundTop;
    private Bitmap mBackgroundBottom;
    private Paint mPaint;
    private float mTopY;
    private float mBottomY;

    /**
     * 移动速度
     */
    private float mSpeedY;

    //// TODO: 17/1/4 remove getWidth和getHeight的参数
    public DrawBackground(Context context, String background) {
        mContext = context;
        init(background);
    }

    @Override
    public void init(Object... objects) {
        mBackgroundBottom = BitmapUtil.decodeSampleBitmap(mContext, (String) objects[0],
                ScreenUtil.getWidth(mContext), ScreenUtil.getHeight(mContext));
        mBackgroundTop = BitmapUtil.getVerticalFlipBitmap(mBackgroundBottom);
        //初始化时当前图片位置y坐标
        mBottomY = 0;
        //初始化时屏幕外上方图片位置y坐标
        mTopY = -ScreenUtil.getHeight(mContext);
        mSpeedY = 10;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mContext.getResources().getColor(R.color.white));
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBackgroundBottom, 0, mBottomY, mPaint);
        canvas.drawBitmap(mBackgroundTop, 0, mTopY, mPaint);
    }

    @Override
    public void updateGameUI() {
        mBottomY += mSpeedY;
        mTopY += mSpeedY;
        //判断 移出屏幕就重新设置到屏幕上方
        if (mBottomY > ScreenUtil.getHeight(mContext)) {
            mBottomY = -ScreenUtil.getHeight(mContext);
        }

        if (mTopY > ScreenUtil.getHeight(mContext)) {
            mTopY = -ScreenUtil.getHeight(mContext);
        }
    }
}
