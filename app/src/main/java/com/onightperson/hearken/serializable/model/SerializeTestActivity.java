package com.onightperson.hearken.serializable.model;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.onightperson.hearken.base.BaseActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by liubaozhu on 17/9/2.
 */

public class SerializeTestActivity extends BaseActivity {
    private static final String TAG = "SerializeTestActivity";

    private static final String FILE_NAME = "/myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    private void initData() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            // 序列化
            oos = new ObjectOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory() + FILE_NAME));
            Monitor monitor = new Monitor(201701, "Lucy", 3);
//            monitor.mId = 201701;
//            monitor.mName = "Lucy";
//            monitor.mPosition = 3;
            monitor.mClass = 2;
            Log.i(TAG, "initData: monitor: " + monitor);
            oos.writeObject(monitor);
            // 反序列化
            ois = new ObjectInputStream(new FileInputStream(Environment.getExternalStorageDirectory() + FILE_NAME));
            Monitor monitor1 = (Monitor) ois.readObject();

            Log.i(TAG, "initData: monitor1: " + monitor1);
            Log.i(TAG, "initData: (monitor == monitor1): " + (monitor == monitor1)
                    + ", monitor.equals(monitor): " + monitor.equals(monitor1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
