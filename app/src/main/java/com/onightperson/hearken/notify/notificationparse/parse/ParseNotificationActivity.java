package com.onightperson.hearken.notify.notificationparse.parse;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.onightperson.hearken.R;
import com.onightperson.hearken.notify.NotificationMgr;

public class ParseNotificationActivity extends Activity implements NotificationManager.CallBack, ListenerService.BindListener {

    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int PERMISSION_REQ_ID = 0x229;

    private TextView mContent;
    private TextView mListenerStatus;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQ_ID) {
            for (int grantResult : grantResults) {
                if (PackageManager.PERMISSION_GRANTED == grantResult) {
                    continue;
                }

                Toast.makeText(this.getApplicationContext(), R.string.granted_fail_warning, Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            Toast.makeText(this.getApplicationContext(), R.string.granted_success, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_notification);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PackageManager.PERMISSION_GRANTED
                != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQ_ID);
        }
        mContent = (TextView) findViewById(R.id.content);
        mListenerStatus = (TextView) findViewById(R.id.listener_status);
        mListenerStatus.setText(isReadNotificationPermissionOpen()
                ? R.string.listener_enabled : R.string.listener_disabled);
        NotificationManager manager = NotificationManager.getInstance();
        mContent.setText(manager.loadContent());
        manager.setCallBack(this);
        ListenerService.sListener = this;
    }

    @Override
    protected void onDestroy() {
        NotificationManager.getInstance().setCallBack(null);
        ListenerService.sListener = null;
        super.onDestroy();
    }

    private boolean isReadNotificationPermissionOpen() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (String name : names) {
                final ComponentName cn = ComponentName.unflattenFromString(name);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void save(View view) {
        NotificationManager.getInstance().save(new Handler(), this.getApplication());
    }

    public void enterSetting(View view) {
        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void pull(View view) {
//        Intent it = new Intent(this, ParseNotificationActivity.class);
//        it.putExtra("test", "data");
//        it.putExtra("ff", 123);
//        PendingIntent pi = PendingIntent
//                .getActivity(this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification.Builder builder = new Notification.Builder(this);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentTitle("Show");
//        builder.setContentText("Big");
//        builder.setTicker("KL");
//        builder.setContentIntent(pi);
//
//        final android.app.NotificationManager manager = (android.app.NotificationManager)
//                getSystemService(NOTIFICATION_SERVICE);
//
//        manager.notify(ListenerService.AVOID_ID, builder.build());
//        mContent.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                manager.cancel(ListenerService.AVOID_ID);
//            }
//        }, 200);

        NotificationMgr.getInstance(this).sendCustomNotification();
    }

    @Override
    public void onNotificationReceived(final String notification) {
        mContent.post(new Runnable() {
            @Override
            public void run() {
                mContent.append(notification);
            }
        });
    }

    @Override
    public void listener(boolean bind) {
        mListenerStatus.setText(bind ? R.string.listener_enabled : R.string.listener_disabled);
    }
}
