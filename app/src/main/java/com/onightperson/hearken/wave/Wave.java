package com.onightperson.hearken.wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.onightperson.hearken.viewtest.utils.DataUtils;


/**
 * Created by liubaozhu on 17/2/23.
 */

public class Wave extends View {
    private static final String TAG = "WaveView";
    private static final int ALPHA_FOREGROUND_WAVE_COLOR = 30; //12% blue
    private static final int ALPHA_BACKGROUND_WAVE_COLOR = 15; //6% blue

    private static final int WAVE_DEFAULT_LENGTH = 240;

    public static final float WAVE_DEFAULT_HEIGHT = 9f;
    private static final int WAVE_DEFAULT_PACE = 5;
    private static final int WAVE_REFRESH_INTERVAL = 35; //刷新频率 35ms
    private static final int OFFSET_BACKGROUND_TO_FOREGROUND = 60;

    private int mWaveLength; //波长
    private int mWavePace; //波速
    private int mInitalPosOffset; //两波起始位置偏移量
    private int mWaveHeight;//波高 初始长度为波长的1/24

    private int mForegroundOffset;
    private int mBackgroundOffset;

    private Paint mForegroundWavePaint;
    private Paint mBackgroundWavePaint;
    private Path mWavePath;
    private int mScreenWidth;
    private long mLastRefreshTime;
    private Thread mDrawThread;
    private boolean mKeepAlive;
    private Bitmap mBitmapCache;
    private Canvas mTempCanvas;
    private DrawFilter mDrawFilter;
    private float mBalancePos;
    private float mRealWaveHeight;

    public Wave(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (isHardwareAccelerated()) {
                setLayerType(LAYER_TYPE_SOFTWARE, null);  //禁用硬件加速
            }
        }
        mWaveLength = (int) DataUtils.dp2px(context, WAVE_DEFAULT_LENGTH);
        mWavePace = (int) DataUtils.dp2px(context, WAVE_DEFAULT_PACE);
        mWaveHeight = (int) DataUtils.dp2px(context, 12);

        mInitalPosOffset = (int) DataUtils.dp2px(context, OFFSET_BACKGROUND_TO_FOREGROUND);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;

        mForegroundOffset = 0;
        mBackgroundOffset = mInitalPosOffset;

        mForegroundWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mForegroundWavePaint.setStrokeWidth(1.0f);
        mForegroundWavePaint.setColor(Color.BLUE);
        mForegroundWavePaint.setAlpha(ALPHA_FOREGROUND_WAVE_COLOR);
        mBackgroundWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundWavePaint.setStrokeWidth(1.0f);
        mBackgroundWavePaint.setColor(Color.BLUE);
        mBackgroundWavePaint.setAlpha(ALPHA_BACKGROUND_WAVE_COLOR);
        mBitmapCache = Bitmap.createBitmap(mScreenWidth, mWaveHeight, Bitmap.Config.ARGB_8888);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mTempCanvas = new Canvas(mBitmapCache);
        mTempCanvas.setDrawFilter(mDrawFilter);

        mBalancePos = DataUtils.dp2px(context, WAVE_DEFAULT_HEIGHT) / 2;
        mRealWaveHeight = DataUtils.dp2px(context, 9);
        calPath(mWaveHeight, 0);
    }

    public void calPath(int totalHeight, float offset) {
        mWaveHeight = totalHeight;
        if (mWavePath == null) {
            mWavePath = calWavePath(totalHeight, offset);
        }
        float realWaveHeight = totalHeight / 3 + 5 * DataUtils.dp2px(getContext(), WAVE_DEFAULT_HEIGHT) / 9;
        float scale = realWaveHeight / mRealWaveHeight;
        mRealWaveHeight = realWaveHeight;
        Matrix matrix = new Matrix();
        matrix.setScale(1, scale);
        mWavePath.transform(matrix);
        mWavePath.addRect(0, realWaveHeight, mScreenWidth + mWaveLength, totalHeight, Path.Direction.CCW);

//        if (isInvalidate) {
//            if (null != mBitmapCache) {
//                mBitmapCache.recycle();
//                mBitmapCache = null;
//            }
//            mBitmapCache = Bitmap.createBitmap(mScreenWidth, mWaveHeight, Bitmap.Config.ARGB_8888);
//            mTempCanvas.setBitmap(mBitmapCache);
//        }
//        mBitmapCache.eraseColor(0x00000000);
//        drawWave(mTempCanvas, mBackgroundWavePaint, mBackgroundOffset);
//        drawWave(mTempCanvas, mForegroundWavePaint, mForegroundOffset);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
        startWaveAnim();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
        stopWaveAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        long curTime = System.currentTimeMillis();
        long diffTime = curTime - mLastRefreshTime;
        Log.d(TAG, "onDraw: diffTime: " + diffTime);
        synchronized (Wave.this) {
//            super.onDraw(canvas);
//            if (null != mBitmapCache) {
//                canvas.drawBitmap(mBitmapCache, 0, 0, null);
//            }
            canvas.drawARGB(0, 0,0,0);
            drawWave(canvas, mBackgroundWavePaint, mBackgroundOffset);
            drawWave(canvas, mForegroundWavePaint, mForegroundOffset);

            mLastRefreshTime = curTime;
        }

    }

    public void startWaveAnim() {
        Log.d(TAG, "startDropDownAnim initial--isAlive: "
                + (mDrawThread != null && mDrawThread.isAlive()));
        synchronized (Wave.this) {
            if (mDrawThread != null && mDrawThread.isAlive()) {
                return;
            }

            mKeepAlive = true;
            mDrawThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mKeepAlive) {
                        mForegroundOffset = getWaveOffset(mForegroundOffset);
                        mBackgroundOffset = getWaveOffset(mBackgroundOffset);
                        postInvalidate();
                        SystemClock.sleep(WAVE_REFRESH_INTERVAL);
                    }
                }
            });
            mDrawThread.start();
            Log.d(TAG, "startDropDownAnim finished--isAlive: "
                    + (mDrawThread != null && mDrawThread.isAlive()));

        }
    }

    public void stopWaveAnim() {
        if (mDrawThread != null && mDrawThread.isAlive()) {
            mKeepAlive = false;
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void drawWave(Canvas canvas, Paint paint, int offset) {
        canvas.save();
        mWavePath.offset(-offset, 0);
        canvas.drawPath(mWavePath, paint);
        mWavePath.offset(offset, 0);
        canvas.restore();
    }

    private int getWaveOffset(int offset) {
        return (offset + mWavePace) % mWaveLength;
    }

    /**
     * @param offset 位移
     * @return
     */
    private Path calWavePath(int totalHeight, float offset) {
        float waveHeight = totalHeight / 3 + 5 * DataUtils.dp2px(getContext(), WAVE_DEFAULT_HEIGHT) / 9;
        float amplitude = waveHeight / 2.0f;
        Path path = new Path();
        //构建波路径
        path.moveTo(0, waveHeight);//路径起始点

        //一个波长+屏幕宽的点集
        int totalLength = mScreenWidth + mWaveLength;
        float[] yPos = getSinPathYPos(mBalancePos, amplitude, totalLength);
        for (int i = 0; i < yPos.length; i++) {
            path.lineTo(i, yPos[i]);
        }
        path.lineTo(totalLength, waveHeight);//路径结束点
        path.close();
        return path;
    }

    /**
     * 获取波路径点集合
     *
     * @param length 波长
     * @return
     */
    private float[] getSinPathYPos(float balance, float amplitude, int length) {
        float[] yPos = new float[length];

        double omega = Math.PI * 2 / mWaveLength;//角频率

        for (int i = 0; i < length; i++) {
            yPos[i] = (float) (balance + amplitude * Math.sin(omega * i));
            Log.d(TAG, "getSinPathYPos: y坐标： " + yPos[i]);
        }
        return yPos;
    }

    /**
     * 水面下，静止区
     */
    public static class StillView extends View {
        public StillView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Log.d(TAG, "StillView--onDraw");
            super.onDraw(canvas);
            canvas.drawARGB(ALPHA_BACKGROUND_WAVE_COLOR, 0, 0, 255);
            canvas.drawARGB(ALPHA_FOREGROUND_WAVE_COLOR, 0, 0, 255);
        }
    }
}
