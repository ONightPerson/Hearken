package com.onightperson.hearken;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    private String mBackground;
    private DrawBackground mDrawBackground;
    private boolean mIsRun;

    public GameView(Context context, String backgroud) {
        super(context);
        mContext = context;
        mBackground = backgroud;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    private void initGame() {
        mDrawBackground = new DrawBackground(mContext, mBackground);
    }

    private void onGameDraw() {
        if (mCanvas == null) {
            return;
        }

        mCanvas.drawColor(mContext.getResources().getColor(R.color.white));
        mDrawBackground.onDraw(mCanvas);
        mDrawBackground.updateGameUI();
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
