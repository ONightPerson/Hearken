package com.onightperson.hearken.notify.notificationparse.zakermodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by yuejianchao on 2017/7/26.
 */

public abstract class BasicProObject implements Parcelable, Serializable {

    public final String objectApiVersion;
    private long objectLastTime;
    private static final long serialVersionUID = 397955714216778727L;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.objectApiVersion);
        dest.writeLong(this.objectLastTime);
    }

    public BasicProObject() {
        this.objectApiVersion = "";
    }

    protected BasicProObject(Parcel parcel) {
        this.objectApiVersion = parcel.readString();
        this.objectLastTime = parcel.readLong();
    }

}
