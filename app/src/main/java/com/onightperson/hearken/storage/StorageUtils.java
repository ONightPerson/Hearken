package com.onightperson.hearken.storage;

import android.os.Environment;
import android.util.Log;

/**
 * Created by liubaozhu on 17/2/20.
 */

public class StorageUtils {
    private static final String TAG = "StorageUtils";


    public static void showSeriousDirectories() {
        String dataDir = Environment.getDataDirectory().toString();
        String downloadCacheDir = Environment.getDownloadCacheDirectory().toString();
        String exStorageDir = Environment.getExternalStorageDirectory().toString();
        String exStorageDirByPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString();
        String exStorageState = Environment.getExternalStorageState();
        String rootDir = Environment.getRootDirectory().toString();
        boolean isExStorageEmulated = Environment.isExternalStorageEmulated();
        boolean isExStorageRemovable = Environment.isExternalStorageRemovable();

        String exStorage = System.getenv("EXTERNAL_STORAGE");
        String secStorage = System.getenv("SECONDARY_STORAGE");

        Log.i(TAG, "showSeriousDirectories--dataDir: " + dataDir + ", downloadCacheDir: "
                + downloadCacheDir + ", exStorageDir: " + exStorageDir + ", exStorageDirByPath: "
                + exStorageDirByPath + ", exStorageState: " + exStorageState + ", rootDir: " + rootDir
                + ", isExStorageEmulated: " + isExStorageEmulated + ", isExStorageRemovable: " + isExStorageRemovable
                + ", exStorage: " + exStorage + ", secStorage: " + secStorage);
    }
}
