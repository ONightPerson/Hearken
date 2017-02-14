package com.onightperson.hearken.game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Context mContext;
    private SurfaceHolder mSurfaceHolder;
    /**
     * 画布
     */
    private Canvas mCanvas;

    /**
     * 画笔
     */
    private String mBackgroundName;
    private Background mBackground;
    private boolean mIsRun;

    public GameView(Context context) {
        super(context);
        initialize(context, "selectback01.jpg");
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, "selectback01.jpg");
    }

    public void initialize(Context context, String backgroud) {
        mContext = context;
        mBackgroundName = backgroud;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    private void initGame() {
        mBackground = new Background(mContext, mBackgroundName);
    }

    private void onGameDraw() {
        if (mCanvas == null) {
            return;
        }

        mCanvas.drawColor(mContext.getResources().getColor(R.color.white));
        mBackground.onDraw(mCanvas);
        mBackground.updateUI();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsRun = true;
        initGame();
        Thread drawThread = new Thread(this);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsRun = false;
    }

    @Override
    public void run() {
        while (mIsRun) {
            synchronized (mSurfaceHolder) {
                mCanvas = mSurfaceHolder.lockCanvas();
                onGameDraw();
                //执行完成后，提交画布
                if (mCanvas != null) {
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }
}
