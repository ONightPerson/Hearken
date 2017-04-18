package com.onightperson.hearken.camera;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import java.io.IOException;
import java.util.List;

/**
 * Created by liubaozhu on 17/4/18.
 */

public class Preview extends FrameLayout implements SurfaceHolder.Callback {
    private static final String TAG = "Preview";

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private List<Camera.Size> mSupportedPreviewSizes;

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceView = new SurfaceView(context);
        addView(mSurfaceView);
        //设置回调，用于接收surface创建或者销毁的通知
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
    }

    public void setCamera(Camera camera) {
        if (mCamera == camera) {
            return;
        }

        stopPreviewAndFreeCamera();
        mCamera = camera;
        if (mCamera != null) {
            List<Camera.Size> sizes = mCamera.getParameters().getSupportedPreviewSizes();
            mSupportedPreviewSizes  = sizes;
            requestLayout();

            try {
                mCamera.setPreviewDisplay(mHolder);//在startPreview之前调用
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.startPreview();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
        }
    }

    public void stopPreviewAndFreeCamera() {

        if (mCamera != null) {
            // Call stopPreview() to stop updating the preview surface.
            mCamera.stopPreview();

            // Important: Call release() to release the camera for use by other
            // applications. Applications should release the camera immediately
            // during onPause() and re-open() it during onResume()).
            mCamera.release();

            mCamera = null;
            Log.i(TAG, "stopPreviewAndFreeCamera: mCamera: " + mCamera);
        }
    }
}
