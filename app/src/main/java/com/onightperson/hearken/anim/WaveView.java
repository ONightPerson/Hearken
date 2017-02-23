package com.onightperson.hearken.anim;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;


public class WaveView extends LinearLayout {
    private static final boolean DEBUG = true;
    private static final String TAG = "WaveView";

    private static final float MIN_LEVEL_PERCENT = 0.2f; // 水面最低高度, (百分比)
    private static final float MAX_LEVEL_PERCENT = 1.0f; // 水面最高高度, (百分比)

    private static final int DEFAULT_STROKE_WIDTH = 2; // 默认描边宽度
   /* private static final int DEFAULT_FOREGROUND_STROKE_COLOR = 0xcc6cc9ff; // 前景波线条颜色
    private static final int DEFAULT_BACKGROUND_STROKE_COLOR = 0xcc86a2ff; // 后景波线条颜色*/
   private static final int DEFAULT_FOREGROUND_STROKE_COLOR = 0x66619bf7; // 前景波线条颜色
    private static final int DEFAULT_BACKGROUND_STROKE_COLOR = 0xcc4789f4; // 后景波线条颜色
    private static final int DEFAULT_FOREGROUND_COLOR = 0x66619bf7; // 前景颜色
    private static final int DEFAULT_BACKGROUND_COLOR = 0xcc4789f4; // 后景颜色

    private static final int DEFAULT_FOREGROUND_SPACE = 15;     // 前景波波速, px
    private static final int DEFAULT_BACKGROUND_SPACE = 15;     // 背景波波速, px
    private static final float DEFAULT_FOREGROUND_WAVE_OFFSET = 0.0f; // 前景波偏移量(比例, 一个波长为1)
    private static final float DEFAULT_BACKGROUND_WAVE_OFFSET = 0.75f; // 后景波偏移量(比例, 一个波长为1)

    private float mProgress;
    private Wave mWave;
    private View mSolid;
    private int mLastWaveHeight = 0;
    private int mLastWaveLength = 0;
    private int mLastWaveToTop = 0;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        mWave = new Wave(getContext());
        initWave();
        addView(mWave, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        mSolid = new Solid(getContext());
        addView(mSolid, new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT, 1.0f));

        setPercent(MIN_LEVEL_PERCENT); // 默认最低
    }

    private void initWave() {
        setStrokeWidth(WaveView.convertDpToPixel(getContext(), DEFAULT_STROKE_WIDTH));

        setForegroundWaveOffset(DEFAULT_FOREGROUND_WAVE_OFFSET);
        setForegroundWaveSpace(DEFAULT_FOREGROUND_SPACE);
        setForegroundWaveColor(DEFAULT_FOREGROUND_COLOR);
        setForegroundWaveStrokeColor(DEFAULT_FOREGROUND_STROKE_COLOR);

        setBackgroundWaveOffset(DEFAULT_BACKGROUND_WAVE_OFFSET);
        setBackgroundWaveSpace(DEFAULT_BACKGROUND_SPACE);
        setBackgroundWaveColor(DEFAULT_BACKGROUND_COLOR);
        setBackgroundWaveStrokeColor(DEFAULT_BACKGROUND_STROKE_COLOR);
    }

    public void setPercent(float levelPer) {
        levelPer = levelPer > MAX_LEVEL_PERCENT ? MAX_LEVEL_PERCENT : levelPer;
        mProgress = levelPer < MIN_LEVEL_PERCENT ? MIN_LEVEL_PERCENT :  levelPer;
        computeWaveToTop();
    }

    private void computeWaveToTop() {
        int waveHeight = getHeight() / 10; // 波高为组件高度的 1/(10 * 2);
        int waveLength = getWidth(); // 波长为组件宽度;
        int waveToTop = (int) ((getHeight() - waveHeight) * (1.0f - mProgress));
        if (DEBUG) {
            Log.d(TAG, "waveHeight: " + waveHeight + ", waveLength: " + waveLength
                    + ", waveToTop: " + waveToTop);
        }
        if ((waveHeight > 0 && waveLength > 0) && (waveHeight != mLastWaveHeight
                || waveLength != mLastWaveLength || waveToTop != mLastWaveToTop)) {
            if (DEBUG) {
                Log.d(TAG, "computeWaveToTop.");
            }
            mLastWaveHeight = waveHeight;
            mLastWaveLength = waveLength;
            mLastWaveToTop = waveToTop;

            LayoutParams params = (LayoutParams) mWave.getLayoutParams();
            if (params != null) {
                params.height = mLastWaveHeight;
                params.topMargin = mLastWaveToTop;
            }
            setWaveLength(mLastWaveLength);
            mWave.setLayoutParams(params);
            measure(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (DEBUG) {
            Log.d(TAG, "onSizeChanged: " + w + ", " + h + ", " + oldw + ", " + oldh);
        }
        computeWaveToTop();
    }

    public int getForegroundWaveSpace() {
        return mWave.getForegroundWaveSpace();
    }

    /**
     * 设置前景波X轴步进速度.
     * @param xSpace 单位px
     */
    public void setForegroundWaveSpace(int xSpace) {
        mWave.setForegroundWaveSpace(xSpace);
    }

    public int getBackgroundWaveSpace() {
        return mWave.getBackgroundWaveSpace();
    }

    /**
     * 设置前景波X轴步进速度.
     * @param xSpace 单位px
     */
    public void setBackgroundWaveSpace(int xSpace) {
        mWave.setBackgroundWaveSpace(xSpace);
    }

    public int getWaveLength() {
        return mWave.getWaveLength();
    }

    /**
     * 设置波长.
     * @param waveLength 单位px
     */
    public void setWaveLength(int waveLength) {
        mWave.setWaveLength(waveLength);
    }

    public int getStrokeWidth() {
        return mWave.getStrokeWidth();
    }

    /**
     * 设置波形描边宽度.
     * @param strokeWidth 单位px
     */
    public void setStrokeWidth(int strokeWidth) {
        mWave.setStrokeWidth(strokeWidth);
    }

    public float getForegroundWaveOffset() {
        return mWave.getForegroundWaveOffset();
    }

    /**
     * 设置前景波偏移量.
     * @param foregroundWaveOffset 一个波长的比例, 一个波长为1
     */
    public void setForegroundWaveOffset(float foregroundWaveOffset) {
        mWave.setForegroundWaveOffset(foregroundWaveOffset);
    }

    public float getBackgroundWaveOffset() {
        return mWave.getBackgroundWaveOffset();
    }

    /**
     * 设置背景波偏移量.
     * @param backgroundWaveOffset 一个波长的比例, 一个波长为1
     */
    public void setBackgroundWaveOffset(float backgroundWaveOffset) {
        mWave.setBackgroundWaveOffset(backgroundWaveOffset);
    }

    public int getForegroundWaveColor() {
        return mWave.getForegroundWaveColor();
    }

    /**
     * 设置前景波颜色.
     * @param foregroundWaveColor
     */
    public void setForegroundWaveColor(int foregroundWaveColor) {
        mWave.setForegroundWaveColor(foregroundWaveColor);
    }

    public int getForegroundWaveStrokeColor() {
        return mWave.getForegroundWaveStrokeColor();
    }

    /**
     * 设置前景波描边颜色.
     * @param foregroundWaveStrokeColor
     */
    public void setForegroundWaveStrokeColor(int foregroundWaveStrokeColor) {
        mWave.setForegroundWaveStrokeColor(foregroundWaveStrokeColor);
    }

    public int getBackgroundWaveColor() {
        return mWave.getBackgroundWaveColor();
    }

    /**
     * 设置背景波颜色.
     * @param backgroundWaveColor
     */
    public void setBackgroundWaveColor(int backgroundWaveColor) {
        mWave.setBackgroundWaveColor(backgroundWaveColor);
    }

    public int getBackgroundWaveStrokeColor() {
        return mWave.getBackgroundWaveStrokeColor();
    }

    /**
     * 设置背景波描边颜色.
     * @param backgroundWaveStrokeColor
     */
    public void setBackgroundWaveStrokeColor(int backgroundWaveStrokeColor) {
        mWave.setBackgroundWaveStrokeColor(backgroundWaveStrokeColor);
    }

    /**
     * 计算dip为px.
     * @param context
     * @param dp
     * @return
     */
    private static int convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    /**
     * 水面下部, 以提高效率.
     */
    class Solid extends View {
        public Solid(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(DEFAULT_BACKGROUND_COLOR);
            canvas.drawColor(DEFAULT_FOREGROUND_COLOR);
        }
    }

    public void changeWaveLength() {
        int width = getWidth();
        if (mLastWaveLength >= width) {
            mLastWaveLength = width / 5;
        }

        mLastWaveLength += width / 5;
        setWaveLength(mLastWaveLength);
    }

    public void changeWavePace() {
        int backgroundPace = getBackgroundWaveSpace();
        int foregroundPace = getForegroundWaveSpace();

        if (backgroundPace > 60) {
            backgroundPace = 15;
            foregroundPace = 15;
        }

        backgroundPace += 5;
        foregroundPace += 5;
        setBackgroundWaveSpace(backgroundPace);
        setForegroundWaveSpace(foregroundPace);
    }

    public void changeWavezhengfu() {
        LayoutParams params = (LayoutParams) mWave.getLayoutParams();
        int height = params.height;
        if (height >= getHeight() * 4 / 5) {
            height = params.height / 10;
        }
        height += params.height / 10;
        params.height = height;
        mWave.setLayoutParams(params);
    }
}