package com.onightperson.hearken.notify.notificationparse.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 网易新闻
 */
public class a implements Parcelable {

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;

    protected a(Parcel arg2) {
        this.a = arg2.readString();
        this.b = arg2.readString();
        this.c = arg2.readString();
        this.d = arg2.readString();
        this.e = arg2.readString();
        this.f = arg2.readString();
        this.g = arg2.readString();
        this.h = arg2.readString();
        this.i = arg2.readString();
        this.j = arg2.readString();
        this.k = arg2.readString();
        this.l = arg2.readString();
        this.m = arg2.readString();
        this.n = arg2.readInt();
    }

    public a() {
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = 0;
    }

    public static final Creator<a> CREATOR =
            new Creator<a>() {
                @Override
                public a createFromParcel(Parcel source) {
                    return new a(source);
                }

                @Override
                public a[] newArray(int size) {
                    return new a[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeString(this.b);
        dest.writeString(this.c);
        dest.writeString(this.d);
        dest.writeString(this.e);
        dest.writeString(this.f);
        dest.writeString(this.g);
        dest.writeString(this.h);
        dest.writeString(this.i);
        dest.writeString(this.j);
        dest.writeString(this.k);
        dest.writeString(this.l);
        dest.writeString(this.m);
        dest.writeInt(this.n);
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getE() {
        return e;
    }

    public String getF() {
        return f;
    }

    public String getG() {
        return g;
    }

    public String getH() {
        return h;
    }

    public String getI() {
        return i;
    }

    public String getJ() {
        return j;
    }

    public String getK() {
        return k;
    }

    public String getL() {
        return l;
    }

    public String getM() {
        return m;
    }

    public int getN() {
        return n;
    }


    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }

    public void setE(String e) {
        this.e = e;
    }

    public void setF(String f) {
        this.f = f;
    }

    public void setG(String g) {
        this.g = g;
    }

    public void setH(String h) {
        this.h = h;
    }

    public void setI(String i) {
        this.i = i;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public void setK(String k) {
        this.k = k;
    }

    public void setL(String l) {
        this.l = l;
    }

    public void setM(String m) {
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }
}

