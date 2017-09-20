package com.onightperson.hearken.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liubaozhu on 17/4/18.
 */

public class CameraActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "CameraActivity";

    private static final int MSG_CAMERA_INIT_FINISH = 0;

    private static final int INVALID_CAMERA_ID = -1;

    private int mCameraId;
    private Handler mHandler;
    private Camera mCamera;
    private PreView mPreview;
    private Button mTakePictureBtn;
    private Button mTurnOnFlashLightBtn;
    private Button mTurnOffFlashLightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity_layout);

        initViews();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_CAMERA_INIT_FINISH:
                        boolean success = (Boolean) msg.obj;
                        if (success) {
                            mPreview.setCamera(mCamera);
                        }
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //放在onResume中便于重用
        startCameraInitTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在pause时期停止更新surface
        Log.i(TAG, "onPause: ");
        mPreview.stopPreviewAndFreeCamera();
    }

    private void initViews() {
        mPreview = (PreView) findViewById(R.id.camera_preview);
        mTakePictureBtn = (Button) findViewById(R.id.take_picture);
        mTakePictureBtn.setOnClickListener(this);
        mTurnOnFlashLightBtn = (Button) findViewById(R.id.turn_on_flash_light);
        mTurnOnFlashLightBtn.setOnClickListener(this);
        mTurnOffFlashLightBtn = (Button) findViewById(R.id.turn_off_flash_light);
        mTurnOffFlashLightBtn.setOnClickListener(this);
    }

    private void startCameraInitTask() {
        //如果当前有Camera引用，先释放
        releaseCameraAndPreview();
        if (mCamera == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建Camera实例，用于访问第一个后置摄像头相机
                    mCameraId = findBackFacingCamera();
                    Log.i(TAG, "run: mCameraId: " + mCameraId);
                    mCamera = Camera.open(mCameraId);
                    setCameraDisplayOrientation(mCameraId, mCamera);

                    boolean success = mCamera != null;
                    Message.obtain(mHandler, MSG_CAMERA_INIT_FINISH, success).sendToTarget();
                }
            }).start();
        } else {
            Message.obtain(mHandler, MSG_CAMERA_INIT_FINISH, true).sendToTarget();
        }
    }


    private void releaseCameraAndPreview() {
        mPreview.setCamera(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    public void setCameraDisplayOrientation(int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = getWindowManager().getDefaultDisplay()
                .getRotation();
        Log.i(TAG, "setCameraDisplayOrientation: rotation: " + rotation);
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    /** * 获取后置摄像头id * @return */
    public static int findBackFacingCamera() {
        int cameraId = INVALID_CAMERA_ID;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        Log.i(TAG, "findBackFacingCamera: ");
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    public void onClick(View v) {
        if (v == mTakePictureBtn) {
            if (mCamera != null) {
                mCamera.takePicture(new Camera.ShutterCallback() {
                    @Override
                    public void onShutter() {
                        Log.i(TAG, "onShutter: ");
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Log.i(TAG, "onPictureTaken1: data: " + data);
                        if (data != null) {

                            Log.i(TAG, "onPictureTaken1: data.size: " + data.length);
                        }

                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        Log.i(TAG, "onPictureTaken2: data: " + data);
                        if (data != null) {

                            Log.i(TAG, "onPictureTaken2: data.size: " + data.length);
                        }
                        //存储数据到DCIM中
                        CharSequence fileName = DateFormat.format("yyyy-MM-dd-HH-mm-ss.jpg", System.currentTimeMillis());
                        File fileToWrite = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), fileName.toString());
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(fileToWrite);
                            fos.write(data);
                            fos.flush();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            }
        } else if (v == mTurnOnFlashLightBtn) {
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(params);
        } else if (v == mTurnOffFlashLightBtn) {
//            Camera.Parameters params = mCamera.getParameters();
//            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//            mCamera.setParameters(params);
            Log.i(TAG, "onClick: mCamera: " + mCamera);
            if (mCamera != null) {
                Log.i(TAG, "onClick: flash mode: " + mCamera.getParameters().getFlashMode());
            }
        }
    }
}
