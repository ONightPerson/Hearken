package com.onightperson.hearken.table;

import com.onightperson.hearken.database.ClonyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by liubaozhu on 17/1/6.
 */

/**
 * 声明表类，将表与数据库对应
 */
@Table(database = ClonyDatabase.class)
public class Queen extends BaseModel {
    //定义主键，至少一个
    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    String name;
    QueenClony clony;
}
