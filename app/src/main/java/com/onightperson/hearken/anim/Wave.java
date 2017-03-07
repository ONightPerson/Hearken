package com.onightperson.hearken.anim;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.util.Log;
import android.view.View;


public class Wave extends View {
    private static final String TAG = "Wave";
    private static final boolean DEBUG = true;

    private static final int DEFAULT_WAVE_LENGTH = 720; // 默认波长

    private static final int MIN_X_SPACE = 10;  // 最小步进
    private static final int MAX_X_SPACE = 50; // 最大步进
    private static final int MIN_REFRESH_INTERVAL = 20; // 最小刷新间隔
    private static final int REFRESH_INTERVAL = 35; // 刷新间隔(millisecond)(值越小，刷新的间隔越小)

    private int mWaveLength = DEFAULT_WAVE_LENGTH; // 波长
    private int mStrokeWidth = 4; // 波描边宽度, 即波线条宽度
    private int mForegroundWaveSpace = MIN_X_SPACE; // 前景波波速.
    private int mBackgroundWaveSpace = MIN_X_SPACE; // 背景波波速.
    private float mForegroundOffsetP = 0.0f;  // 前景波偏移相对波长比例
    private float mBackgroundOffsetP = 0.0f;  // 背景波偏移相对波长比例
    private int mForegroundOffset = 0;  // 前景波偏移量
    private int mBackgroundOffset = 0;  // 背景波偏移量
    private int mForegroundWaveStrokeColor = 0x666cc9ff; // 前景波描边颜色
    private int mForegroundWaveColor = 0xcc619bf7;      // 前景波颜色
    private int mBackgroundWaveStrokeColor = 0xcc86a2ff; // 背景波描边颜色
    private int mBackgroundWaveColor = 0x664789f4;      // 背景波颜色

    private Path mWavePath = new Path(); // 默认波形路径
    private Paint mForegroundLineWavePaint = new Paint(); // 前景描边画笔
    private Paint mForegroundWavePaint = new Paint(); // 前景画笔

    private Paint mBackgroundLineWavePaint = new Paint(); // 背景描边画笔
    private Paint mBackgroundWavePaint = new Paint(); // 背景画笔
    private DrawFilter mDrawFilter;

    Bitmap bitmapBuffer;
    Canvas temCanvas;
    private long mTime;

    private boolean mConfigChanged = false;
    private Long lastRefresh = 0L;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Wave(Context context) {
        super(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (isHardwareAccelerated()) {
                setLayerType(LAYER_TYPE_SOFTWARE, null);  //禁用硬件加速
            }
        }
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    /**
     * 获得当前组件高度, 为修复setLayoutParams不触发SizeChanged.
     * @return
     */
    private int getCurrentHeight() {
        if (null == getLayoutParams() || getLayoutParams().height <= 0) {
            return getHeight();
        }
        return getLayoutParams().height;
    }

    private int getCurrentWidth() {
        if (null == getLayoutParams() || getLayoutParams().width <= 0) {
            return getWidth();
        }
        return getLayoutParams().width;
    }

    /**
     * 初始化波形参数.
     */
    private void initWave() {
        int height = getCurrentHeight();
        int width = getCurrentWidth();
        Log.d(TAG, "initWave--height: " + height + ", width: " + width);
        if (width <= 0 || height <= 0) {
            return;
        }
        int amplitude = height / 2 - mStrokeWidth; // 振幅
        mWavePath = calculateSinPath(width, height, amplitude, mWaveLength); // 初始化路径
        // 前景波描边画笔
        mForegroundLineWavePaint.setStrokeWidth(1.0f);
        mForegroundLineWavePaint.setColor(mForegroundWaveStrokeColor);
        mForegroundLineWavePaint.setAntiAlias(true);
        // 前景波填充画笔
        mForegroundWavePaint.setStrokeWidth(1.0f);
//        mForegroundWavePaint.setColor(mForegroundWaveColor);
        mForegroundWavePaint.setColor(Color.BLUE);
        mForegroundWavePaint.setAlpha(30);

        mForegroundWavePaint.setAntiAlias(true);
        // 背景波描边画笔
        mBackgroundLineWavePaint.setStrokeWidth(1.0f);
        mBackgroundLineWavePaint.setColor(mBackgroundWaveStrokeColor);
        mBackgroundLineWavePaint.setAntiAlias(true);
        // 背景波填充画笔
        mBackgroundWavePaint.setStrokeWidth(1.0f);
//        mBackgroundWavePaint.setColor(mBackgroundWaveColor);
        mBackgroundWavePaint.setColor(Color.BLUE);
        mBackgroundWavePaint.setAlpha(15);
        mBackgroundWavePaint.setAntiAlias(true);

        if (null != bitmapBuffer) {
            bitmapBuffer.recycle();
            bitmapBuffer = null;
        }
        bitmapBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        temCanvas = new Canvas(bitmapBuffer);
        mConfigChanged = false;
    }

    private void drawWave(Canvas canvas, Paint fillWave, Paint strokeLine, int offset) {
        if (offset > mWaveLength) {
            offset = offset % mWaveLength;
        }

       /* canvas.save();
        mWavePath.offset(-offset, 0);
        canvas.clipPath(mWavePath);
        mWavePath.offset(0, mStrokeWidth);
        canvas.clipPath(mWavePath, Region.Op.XOR);
        mWavePath.offset(0, -mStrokeWidth);
        canvas.drawPath(mWavePath, strokeLine);
        canvas.restore();*/

        canvas.save();
        mWavePath.offset(-offset, 0);
        mWavePath.offset(0, mStrokeWidth);
        canvas.drawPath(mWavePath, fillWave);
        mWavePath.offset(offset, -mStrokeWidth);
        canvas.restore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long start = System.currentTimeMillis();
        if (start - lastRefresh >= MIN_REFRESH_INTERVAL) {
            synchronized (Wave.this) {
                super.onDraw(canvas);
                if (mConfigChanged) {
                    initWave();
                }
                Log.d(TAG, "onDraw--时间差: " + (start - mTime));
                mTime = start;
                if (null != bitmapBuffer) {
                    bitmapBuffer.eraseColor(0x00000000);
                    temCanvas.setDrawFilter(mDrawFilter);
                    drawWave(temCanvas, mBackgroundWavePaint, mBackgroundLineWavePaint, mBackgroundOffset);
                    drawWave(temCanvas, mForegroundWavePaint, mForegroundLineWavePaint, mForegroundOffset);

                    canvas.setDrawFilter(mDrawFilter);
                    canvas.drawBitmap(bitmapBuffer, 0, 0, null);

                    if (View.VISIBLE == getVisibility()) {
                        mForegroundOffset = getWaveOffset(mForegroundOffset, mForegroundWaveSpace, mWaveLength);
                        mBackgroundOffset = getWaveOffset(mBackgroundOffset, mBackgroundWaveSpace, mWaveLength);
                        postInvalidateDelayed(REFRESH_INTERVAL);
                    }
                }
            }
        } else {
            postInvalidateDelayed(REFRESH_INTERVAL + lastRefresh - start);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout--initWave");
        initWave();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (bitmapBuffer != null) {
            bitmapBuffer.recycle();
            bitmapBuffer = null;
        }
    }

    /**
     * 计算偏移量.
     * @param offset  原偏移量
     * @param space  偏移距离
     * @param length 波长
     * @return
     */
    private int getWaveOffset(int offset, int space, int length) {
        Log.d(TAG, "getWaveOffset--offset: " + offset + ", space: " + space + ", length: " + length);
        offset += space;
        while (offset < 0) {
            offset = length + offset;
        }
        return offset % length;
    }

    /**
     * 获得一个屏幕宽+一个波长的波形, 以保证任何波形都能映射到屏幕中.
     *
     * @param width             显示宽度
     * @param height            图形高度, 大于两倍振幅
     * @param amplitude     振幅, 即波高
     * @param length            波长, 一个波形的长度
     */
    private Path calculateSinPath(int width, int height, int amplitude, int length) {
        Path path = new Path();
        path.reset();
        if (height < amplitude * 2) {
            amplitude = height / 2;
        }

        float[] sinY = calculateSinY(amplitude, length);
        if (sinY.length > 0) {
            int leftX = 0;
            int rightX = width + length;
            path.moveTo(leftX, height);
            float y;
            for (int x = 0; x <= width + length; x++) {
                y = (height / 2) - sinY[x % length];
                path.lineTo(x, y);
            }
            path.lineTo(rightX, height); // 从右上角到右下角边
            path.close();
        }

        return path;
    }

    /**
     * 计算一个完整波的Y坐标值.
     *
     * @param amplitude     振幅, 即波高(单位: 像素)
     * @param length            波长, 一个波形的长度(单位: 像素)
     */
    private float[] calculateSinY(int amplitude, int length) {
        float[] sinY = new float[length];
        double omega = Math.PI * 2 / length; // 欧米伽, 角速度 ω
        for (int x = 0; x < length; x++) {
            sinY[x] = (int) (amplitude * Math.sin(omega * x));
        }
        return sinY;
    }

    public int getForegroundWaveSpace() {
        return mForegroundWaveSpace;
    }

    /**
     * 设置前景波X轴步进速度.
     * @param xSpace 单位px
     */
    public void setForegroundWaveSpace(int xSpace) {
        if (xSpace >= 0) {
            xSpace = xSpace < MIN_X_SPACE ? MIN_X_SPACE : xSpace;
            xSpace = xSpace > MAX_X_SPACE ? MAX_X_SPACE : xSpace;
        } else {
            xSpace = xSpace > -MIN_X_SPACE ? -MIN_X_SPACE : xSpace;
            xSpace = xSpace < -MAX_X_SPACE ? -MAX_X_SPACE : xSpace;
        }
        if (xSpace != mForegroundWaveSpace) {
            mForegroundWaveSpace = xSpace;
        }
    }

    public int getBackgroundWaveSpace() {
        return mBackgroundWaveSpace;
    }

    /**
     * 设置前景波X轴步进速度.
     * @param xSpace 单位px
     */
    public void setBackgroundWaveSpace(int xSpace) {
        if (xSpace >= 0) {
            xSpace = xSpace < MIN_X_SPACE ? MIN_X_SPACE : xSpace;
            xSpace = xSpace > MAX_X_SPACE ? MAX_X_SPACE : xSpace;
        } else {
            xSpace = xSpace > -MIN_X_SPACE ? -MIN_X_SPACE : xSpace;
            xSpace = xSpace < -MAX_X_SPACE ? -MAX_X_SPACE : xSpace;
        }
        if (xSpace != mBackgroundWaveSpace) {
            mBackgroundWaveSpace = xSpace;
        }
    }

    public int getWaveLength() {
        return mWaveLength;
    }

    /**
     * 设置波长.
     * @param waveLength 单位px
     */
    public void setWaveLength(int waveLength) {
        if (waveLength > 0 && mWaveLength != waveLength) {
            mWaveLength = waveLength;
            mForegroundOffset = (int) (mWaveLength * mForegroundOffsetP);
            mBackgroundOffset = (int) (mWaveLength * mBackgroundOffsetP);
            mConfigChanged = true;
        }
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    /**
     * 设置波形描边宽度.
     * @param strokeWidth 单位px
     */
    public void setStrokeWidth(int strokeWidth) {
        if (strokeWidth >= 0 && mWaveLength != strokeWidth) {
            mWaveLength = strokeWidth;
            mConfigChanged = true;
        }
    }

    public float getForegroundWaveOffset() {
        return mForegroundOffsetP;
    }

    /**
     * 设置前景波偏移量.
     * @param foregroundWaveOffset 一个波长的比例, 一个波长为1
     */
    public void setForegroundWaveOffset(float foregroundWaveOffset) {
        mForegroundOffsetP = foregroundWaveOffset;
        mForegroundOffset = (int) (mWaveLength * foregroundWaveOffset);
    }

    public float getBackgroundWaveOffset() {
        return mBackgroundOffsetP;
    }

    /**
     * 设置背景波偏移量.
     * @param backgroundWaveOffset 一个波长的比例, 一个波长为1
     */
    public void setBackgroundWaveOffset(float backgroundWaveOffset) {
        mBackgroundOffsetP = backgroundWaveOffset;
        mBackgroundOffset = (int) (mWaveLength * backgroundWaveOffset);
    }

    public int getForegroundWaveColor() {
        return mForegroundWaveColor;
    }

    /**
     * 设置前景波颜色.
     * @param foregroundWaveColor   颜色值
     */
    public void setForegroundWaveColor(int foregroundWaveColor) {
        if (mForegroundWaveColor != foregroundWaveColor) {
            mForegroundWaveColor = foregroundWaveColor;
            mConfigChanged = true;
        }
    }

    public int getForegroundWaveStrokeColor() {
        return mForegroundWaveStrokeColor;
    }

    /**
     * 设置前景波描边颜色.
     * @param foregroundWaveStrokeColor  颜色值
     */
    public void setForegroundWaveStrokeColor(int foregroundWaveStrokeColor) {
        if (mForegroundWaveStrokeColor != foregroundWaveStrokeColor) {
            mForegroundWaveStrokeColor = foregroundWaveStrokeColor;
            mConfigChanged = true;
        }
    }

    public int getBackgroundWaveColor() {
        return mBackgroundWaveColor;
    }

    /**
     * 设置背景波颜色.
     * @param backgroundWaveColor   颜色值
     */
    public void setBackgroundWaveColor(int backgroundWaveColor) {
        if (mBackgroundWaveColor != backgroundWaveColor) {
            mBackgroundWaveColor = backgroundWaveColor;
            mConfigChanged = true;
        }
    }

    public int getBackgroundWaveStrokeColor() {
        return mBackgroundWaveStrokeColor;
    }

    /**
     * 设置背景波描边颜色.
     * @param backgroundWaveStrokeColor 颜色值
     */
    public void setBackgroundWaveStrokeColor(int backgroundWaveStrokeColor) {
        if (mBackgroundWaveStrokeColor != backgroundWaveStrokeColor) {
            mBackgroundWaveStrokeColor = backgroundWaveStrokeColor;
            mConfigChanged = true;
        }
    }
}