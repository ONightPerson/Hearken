package com.onightperson.hearken.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


/**
 * Created by liubaozhu on 17/2/23.
 */

public class Wave extends View {
    private static final String TAG = "Wave";
    private static final int ALPHA_FOREGROUND_WAVE_COLOR = 70;//12% blue
    private static final int ALPHA_BACKGROUND_WAVE_COLOR = 15;//6% blue
    private static final float RATIO_WAVE_LENGTH_TO_SCREEN_WIDTH = 2.5f / 2;
    private static final int INTERVAL_WAVE_REFRESH = 30;//刷新频率 30ms
    private static final int TIME_WAVE_DISAPPEAR_ON_SCREEN = 4000;//一个波点从屏幕一端到另一端的时间
    private int mScreenWidth;
    private int mWaveLength;//波长
    private int mAmplitude;//振幅 初始长度为波长的1/24
    private int mWavePace;
    private int mForegroundOffset;
    private int mBackgroundOffset;


    private Paint mForegroundWavePaint;
    private Paint mBackgroundWavePaint;
    private Path mWavePath;

    public Wave(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        mWaveLength = (int) (mScreenWidth * RATIO_WAVE_LENGTH_TO_SCREEN_WIDTH);//初始波长
        mAmplitude = mWaveLength / 24;
        mWavePace = INTERVAL_WAVE_REFRESH * mScreenWidth / TIME_WAVE_DISAPPEAR_ON_SCREEN;
        Log.d(TAG, "init--mWavePace: " + mWavePace);
        mForegroundOffset = 0;
        mBackgroundOffset = mWaveLength / 4;

        mForegroundWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mForegroundWavePaint.setColor(Color.BLUE);
        mForegroundWavePaint.setAlpha(ALPHA_FOREGROUND_WAVE_COLOR);
        mBackgroundWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundWavePaint.setColor(Color.BLUE);
        mBackgroundWavePaint.setAlpha(ALPHA_BACKGROUND_WAVE_COLOR);

        mWavePath = calWavePath(mAmplitude, mScreenWidth + mWaveLength);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawWave(canvas, mForegroundWavePaint, mForegroundOffset);
        drawWave(canvas, mBackgroundWavePaint, mBackgroundOffset);

        postInvalidateDelayed(INTERVAL_WAVE_REFRESH);

        mForegroundOffset = getWaveOffset(mForegroundOffset);
        mBackgroundOffset = getWaveOffset(mBackgroundOffset);

    }

    private void drawWave(Canvas canvas, Paint paint, int offset) {
        canvas.save();
        mWavePath.offset(-offset, 0);
        mWavePath.offset(0, 4);
        canvas.drawPath(mWavePath, paint);
        mWavePath.offset(offset, -4);
        canvas.restore();
    }

    private int getWaveOffset(int offset) {
        return (offset + mWavePace) % mWaveLength;
    }

    /**
     * @param length 波长
     * @return
     */
    private Path calWavePath(int amplitude, int length) {
        Path path = new Path();
        //构建波路径
        path.moveTo(0, 2 * amplitude);//路径起始点
        Point[] points = getPathPoints(length);
        Log.d(TAG, "calWavePath--points: " + points);
        for (int i = 0; i < points.length; i++) {
            path.lineTo(points[i].x, points[i].y);
        }
        path.lineTo(length, 2 * amplitude);//路径结束点
        path.close();

        return path;
    }

    /**
     * 获取波路径点集合
     *
     * @param length 波长
     * @return
     */
    private Point[] getPathPoints(int length) {
        //获取一个波长+屏幕宽的点集
        Point[] points = new Point[length];

        double omega = Math.PI * 2 / mWaveLength;//角频率

        for (int i = 0; i < length; i++) {
            points[i] = new Point();
            points[i].x = i;
            points[i].y = (int) (mAmplitude + mAmplitude * Math.sin(omega * i));
        }
        return points;
    }
}
