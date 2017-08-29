package com.onightperson.hearken.scroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubaozhu on 17/8/29.
 */

public class ScrollUtils {

    public static List<String> getData() {
        String[] names = new String[] {
                "David",
                "John",
                "Lily",
                "James",
                "David",
                "John"
        };
        List<String> data = new ArrayList<>();
        for (String name : names) {
            data.add(name);
        }
        return data;
    }
}
