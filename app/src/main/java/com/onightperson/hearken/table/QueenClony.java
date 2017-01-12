package com.onightperson.hearken.table;

import com.onightperson.hearken.database.ClonyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by liubaozhu on 17/1/6.
 */

@Table(database = ClonyDatabase.class)
public class QueenClony extends BaseModel {
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    String name;
}
