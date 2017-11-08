package com.onightperson.hearken.file;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.base.BaseActivity;

import java.io.File;

/**
 * Created by liubaozhu on 10/27/17.
 */

public class FileTestActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "FileTestActivity";

    private Button mGetVolumePathsBtn;
    private Button mStartVideoFileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);

        initViews();
    }

    private void initViews() {
        mGetVolumePathsBtn = (Button) findViewById(R.id.get_volume_paths);
        mGetVolumePathsBtn.setOnClickListener(this);
        mStartVideoFileBtn = (Button) findViewById(R.id.start_video_file);
        mStartVideoFileBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mGetVolumePathsBtn) {
            StorageUtils.getVolumePaths(this);
            StorageUtils.readSystem();
        } else if (v == mStartVideoFileBtn) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String path = "/storage/emulated/0/AndroidOptimizer/recycle/recycle_trash/00000003MOV_0001";
            File file = new File(path);
            boolean exists = file.exists();
            Log.i(TAG, "onClick: exists: " + exists);
            if (exists) {
                Uri uri = Uri.fromFile(file);
                Log.i(TAG, "onClick: uri: " + uri);
                if (uri != null) {
                    String extensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.getPath() + ".mp4");
                    Log.i(TAG, "onClick: extensionFromUrl: " + extensionFromUrl);
                    String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extensionFromUrl);
                    Log.i(TAG, "onClick: type: " + type);
                    intent.setDataAndType(uri, type);
                    startActivity(intent);
                }
            }

        }
    }
}
