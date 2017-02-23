package com.onightperson.hearken.viewtest.utils;

import com.onightperson.hearken.viewtest.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubaozhu on 17/2/23.
 */

public class DataUtils {

    public static List<ContactInfo> getContactInfoList() {
        List<ContactInfo> contactInfoList = new ArrayList<>();
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.name = "Suson";
        contactInfo.surName = "Smith";
        contactInfo.title = "Suson' info";
        contactInfo.email = "susonjfefe@gmail.com";
        contactInfo.addr = "New York";
        contactInfoList.add(contactInfo);

        contactInfo = new ContactInfo();
        contactInfo.name = "Jack";
        contactInfo.surName = "James";
        contactInfo.title = "Jack' info";
        contactInfo.email = "jacknngf@gmail.com";
        contactInfo.addr = "Washingtn";
        contactInfoList.add(contactInfo);

        contactInfo = new ContactInfo();
        contactInfo.name = "Lebron";
        contactInfo.surName = "James";
        contactInfo.title = "Lebron' info";
        contactInfo.email = "lebronlbj@gmail.com";
        contactInfo.addr = "Cleveland";
        contactInfoList.add(contactInfo);

        return contactInfoList;
    }
}
