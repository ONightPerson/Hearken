package com.onightperson.hearken.uri;

import android.util.Log;
import android.webkit.MimeTypeMap;

/**
 * Created by liubaozhu on 17/4/5.
 */

public class UriTest {
    private static final String TAG = "UriTest";

    public static void printType() {
        String photoUrl = MimeTypeMap.getFileExtensionFromUrl("myphote.png");
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(photoUrl);
        Log.i(TAG, "printType: myphote.png --> type: " + type);

        String photoUrl1 = MimeTypeMap.getFileExtensionFromUrl("myphote.jpg");
        String type1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(photoUrl1);
        Log.i(TAG, "printType: myphote.jpg --> type: " + type1);

        String photoUrl2 = MimeTypeMap.getFileExtensionFromUrl("myphote.jpeg");
        String type2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(photoUrl2);
        Log.i(TAG, "printType: myphote.jpeg --> type: " + type2);

        String voiceUrl = MimeTypeMap.getFileExtensionFromUrl("myvoice.mp3");
        String voiceType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(voiceUrl);
        Log.i(TAG, "printType: myvoice.mp3 --> type: " + voiceType);

        String voiceUrl1 = MimeTypeMap.getFileExtensionFromUrl("myvoice.amr");
        String voiceType2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(voiceUrl1);
        Log.i(TAG, "printType: myvoice.amr --> type: " + voiceType2);

        String videoUrl = MimeTypeMap.getFileExtensionFromUrl("video.mp4");
        String videoType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(videoUrl);
        Log.i(TAG, "printType: video.mp4 --> type: " + videoType);

        String videoUrl1 = MimeTypeMap.getFileExtensionFromUrl("video.mkv");
        String videoType1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(videoUrl1);
        Log.i(TAG, "printType: video.mkv --> type: " + videoType1);

        String videoUrl2 = MimeTypeMap.getFileExtensionFromUrl("video.avi");
        String videoType2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(videoUrl2);
        Log.i(TAG, "printType: video.avi --> type: " + videoType2);
    }
}
