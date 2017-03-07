package com.onightperson.hearken.viewtest.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import com.onightperson.hearken.viewtest.model.AnimContent;
import com.onightperson.hearken.viewtest.model.ContactContent;
import com.onightperson.hearken.viewtest.model.ContentBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubaozhu on 17/2/23.
 */

public class DataUtils {

    public static List<ContentBase> getContentList() {
        List<ContentBase> contentList = new ArrayList<>();

        ContactContent contactInfo = new ContactContent();
        contactInfo.name = "Suson";
        contactInfo.surName = "Smith";
        contactInfo.title = "Suson' info";
        contactInfo.email = "susonjfefe@gmail.com";
        contactInfo.addr = "New York";
        contactInfo.contentType = 0;
        contentList.add(contactInfo);

        contactInfo = new ContactContent();
        contactInfo.name = "Jack";
        contactInfo.surName = "James";
        contactInfo.title = "Jack' info";
        contactInfo.email = "jacknngf@gmail.com";
        contactInfo.addr = "Washingtn";
        contactInfo.contentType = 0;
        contentList.add(contactInfo);

        contactInfo = new ContactContent();
        contactInfo.name = "Lebron";
        contactInfo.surName = "James";
        contactInfo.title = "Lebron' info";
        contactInfo.email = "lebronlbj@gmail.com";
        contactInfo.addr = "Cleveland";
        contactInfo.contentType = 0;
        contentList.add(contactInfo);

        AnimContent animContent = new AnimContent();
        animContent.contentType = 1;
        contentList.add(animContent);

        return contentList;
    }

    public static float dp2px(Context cxt, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, cxt.getResources().getDisplayMetrics());
    }

    public static int getProtectedDays() {
        return 99;
    }
}
