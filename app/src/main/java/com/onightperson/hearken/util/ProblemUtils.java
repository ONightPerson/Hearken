package com.onightperson.hearken.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类为了验证一些疑问
 * Created by liubaozhu on 17/8/7.
 */

public class ProblemUtils {
    private static final String TAG = "ProblemUtils";

    public static void testArrayListCopy() {
        List<String> names = new ArrayList<>();
        names.add("Arya");
        names.add("John");
        names.add("Belly");

        List<String> otherNames = new ArrayList<>(names);
        if (otherNames != null && !otherNames.isEmpty()) {
            otherNames.set(0, "Kitty");
        }

        int size = names.size();
        int otherSize = otherNames.size();
        for (int i = 0; i < size; i++) {
            Log.i(TAG, "testArrayListCopy: " + i + ", name: " + names.get(i));
        }

        for (int i = 0; i < otherSize; i++) {
            Log.i(TAG, "testArrayListCopy: " + i + ", otherName: " + otherNames.get(i));
        }

        otherNames.remove(0);
        int newSize = names.size();
        int otherNewSize = otherNames.size();
        for (int i = 0; i < newSize; i++) {
            Log.i(TAG, "testArrayListCopy: " + i + ", name: " + names.get(i));
        }

        for (int i = 0; i < otherNewSize; i++) {
            Log.i(TAG, "testArrayListCopy: " + i + ", otherName: " + otherNames.get(i));
        }

    }
}
