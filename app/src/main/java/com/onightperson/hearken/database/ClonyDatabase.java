package com.onightperson.hearken.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by liubaozhu on 17/1/6.
 */

@Database(name = ClonyDatabase.NAME, version = ClonyDatabase.VERSION)
public class ClonyDatabase {
    public static final String NAME = "clonies";
    public static final int VERSION = 1;
}
