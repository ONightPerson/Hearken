package com.onightperson.hearken.serializable.model;


/**
 * Created by liubaozhu on 17/9/2.
 *
 * */
public class Manager {


    protected Manager(int id, String name, int position) {
        mId = id;
        mName = name;
        mPosition = position;
    }

    public int mId;
    public String mName;
    public int mPosition;
}
