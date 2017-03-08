package com.onightperson.hearken.receiver;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.onightperson.hearken.MainActivity;
import com.onightperson.hearken.base.BaseActivity;
import com.onightperson.hearken.base.BaseFragmentActivity;
import com.onightperson.hearken.util.ActivityUtils;
import com.onightperson.hearken.util.NetworkUtils;

/**
 * Created by liubaozhu on 17/1/12.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

//        if (!NetworkUtils.isNetworkAvailable(context)) {
//           showForceOfflineDialog(context);
//        }


    }

    private void showForceOfflineDialog(final Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        dialogBuilder.setTitle("提醒");
        dialogBuilder.setMessage("你已被强制下线，请检查网络");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityUtils.finishAll();

                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(startIntent);
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        //只有设置type=TYPE_SYSTEM_ALERT，方可从receiver中弹出
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }
}
