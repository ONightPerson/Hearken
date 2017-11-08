package com.onightperson.hearken.file;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取挂载点
 * Created by liubaozhu on 10/27/17.
 */

public class StorageUtils {
    private static final String TAG = "StorageUtils";

    public static String[] getVolumePaths(Context cxt) {
        StorageManager manager;
        Method getVolumePathsMethod;

        manager = (StorageManager) cxt.getSystemService(Context.STORAGE_SERVICE);
        List<String> pathList = new ArrayList<>();
        try {
            getVolumePathsMethod = manager.getClass().getMethod("getVolumePaths");
            Object result = getVolumePathsMethod.invoke(manager);
            if (result != null || result instanceof String[]) {
                String[] paths = (String[]) result;
                StatFs statFs;
                for (String path : paths) {
                    Log.i(TAG, "getVolumePaths: path: " + path);
                    if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                        statFs = new StatFs(path);
                        int blockCount = statFs.getBlockCount();
                        int blockSize = statFs.getBlockSize();
                        Log.i(TAG, "getVolumePaths: blockCount: " + blockCount
                                + ", blockSize: " + blockSize + ", totalSize: " + blockCount * blockSize);
                        if (blockCount * blockSize != 0) {
                            pathList.add(path);
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pathList.toArray(new String[pathList.size()]);
    }

    public static void readSystem() {
        String rootPath = Environment.getRootDirectory().getPath();
        String dataPath = Environment.getDataDirectory().getPath();
        String cachePath = Environment.getDownloadCacheDirectory().getPath();
        String state = Environment.getExternalStorageState();
        Log.i(TAG, "readSystem: rootPath: " + rootPath + ", dataPath: " + dataPath
                + ", cachePath: " + cachePath + ", state: " + state);
        StatFs statFs = new StatFs(rootPath);
        int blockCount = statFs.getBlockCount();
        int blockSize = statFs.getBlockSize();
        long availCount = statFs.getAvailableBlocks();

        Log.i(TAG, "readSystem: blockCount: " + blockCount + ", blockSize: " + blockSize
                + ", availCount: " + availCount);
    }
}
