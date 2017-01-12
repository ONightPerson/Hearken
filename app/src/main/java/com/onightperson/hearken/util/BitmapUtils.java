package com.onightperson.hearken.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.IOException;

/**
 * Created by liubaozhu on 17/1/4.
 */

public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    public static Bitmap decodeSampleBitmap(Context context, String resName,
                                            int reqWidth, int reqHeight) {
        Bitmap bitmap = null;

        BitmapFactory.Options options = getOptions(context, resName);
        options.inSampleSize = calInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        try {
            bitmap = BitmapFactory.decodeStream(context.getAssets().open(resName), null, options);
            float scaleWidth = reqWidth / (float) bitmap.getWidth();
            float scaleHeight = reqHeight / (float) bitmap.getHeight();
            Log.i(TAG, "decodeSampleBitmap: scaleWidth: " + scaleWidth + ", scaleHeight: "
                    + scaleHeight);
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                    matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getVerticalFlipBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1, -1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static BitmapFactory.Options getOptions(Context context, String resName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(context.getAssets().open(resName), null, options);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return options;
        }
    }

    private static int calInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            int halfWidth = options.outWidth / 2;
            int halfHeight = options.outHeight / 2;
            while (halfWidth / inSampleSize >= reqHeight
                    && halfHeight / inSampleSize >=reqHeight) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
