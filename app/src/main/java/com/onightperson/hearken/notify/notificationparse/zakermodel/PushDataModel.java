package com.onightperson.hearken.notify.notificationparse.zakermodel;

import android.os.Parcel;
import android.util.Log;

import com.onightperson.hearken.notify.notificationparse.parse.ReportCollectUtils;

/**
 * Created by yuejianchao on 2017/7/26.
 */

public class PushDataModel extends BasicProObject {

    private BasicProObject adLiveInfoModel;
    private String articleStaticUrl;
    private String badge;
    private String blockPk;
    private BasicProObject groupPostModel;
    private String isLocalTab;
    private String isSilent;
    private String isTest;
    private long noticeTime;
    private BasicProObject openBlockModel;
    private BasicProObject openTopicModel;
    private BasicProObject openWebModel;
    private String page;
    private String pushArriveStatUrl;
    private String pushId;
    private String pushImageFlag;
    private String pushImageUrl;
    private String pushPk;
    private String pushReceiveStatUrl;
    private String pushStatUrl;
    private String pushSummaries;
    private long pushTime;
    private String pushTitle;
    private static final long serialVersionUID = 6135699905045900156L;
    private String type;


    public static final Creator<PushDataModel> CREATOR = new Creator<PushDataModel>() {
        @Override
        public PushDataModel createFromParcel(Parcel source) {
            return new PushDataModel(source);
        }

        @Override
        public PushDataModel[] newArray(int size) {
            return new PushDataModel[size];
        }
    };

    public PushDataModel() {
        super();
        this.groupPostModel = null;
    }

    protected PushDataModel(Parcel arg3) {
        super(arg3);
        this.groupPostModel = null;
        this.pushSummaries = arg3.readString();
        this.type = arg3.readString();
        Log.d("ccmm", "type=" + type);
        this.blockPk = arg3.readString();
        this.page = arg3.readString();
        this.pushPk = arg3.readString();
        this.pushTitle = arg3.readString();
        this.badge = arg3.readString();
        this.articleStaticUrl = arg3.readString();
        if (this.type.equals("a")) {
            ReportCollectUtils.queue.add(articleStaticUrl);
        }
        Log.d("ccmm", "InvokeUtils articleStaticUrl=" + articleStaticUrl);
        this.pushTime = arg3.readLong();
        this.openWebModel = arg3.readParcelable(BasicProObject.class.getClassLoader());
        this.openTopicModel = arg3.readParcelable(BasicProObject.class.getClassLoader());
        this.openBlockModel = arg3.readParcelable(BasicProObject.class.getClassLoader());
        this.pushStatUrl = arg3.readString();
        this.pushImageFlag = arg3.readString();
        this.pushImageUrl = arg3.readString();
        this.pushArriveStatUrl = arg3.readString();
        this.pushReceiveStatUrl = arg3.readString();
        this.isTest = arg3.readString();
        this.isSilent = arg3.readString();
        this.isLocalTab = arg3.readString();
        this.pushId = arg3.readString();
        this.adLiveInfoModel = arg3.readParcelable(BasicProObject.class.getClassLoader());
        this.groupPostModel = arg3.readParcelable(BasicProObject.class.getClassLoader());
        this.noticeTime = arg3.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg3, int arg4) {
        super.writeToParcel(arg3, arg4);
        arg3.writeString(this.pushSummaries);
        arg3.writeString(this.type);
        arg3.writeString(this.blockPk);
        arg3.writeString(this.page);
        arg3.writeString(this.pushPk);
        arg3.writeString(this.pushTitle);
        arg3.writeString(this.badge);
        arg3.writeString(this.articleStaticUrl);
        arg3.writeLong(this.pushTime);
        arg3.writeParcelable(this.openWebModel, arg4);
        arg3.writeParcelable(this.openTopicModel, arg4);
        arg3.writeParcelable(this.openBlockModel, arg4);
        arg3.writeString(this.pushStatUrl);
        arg3.writeString(this.pushImageFlag);
        arg3.writeString(this.pushImageUrl);
        arg3.writeString(this.pushArriveStatUrl);
        arg3.writeString(this.pushReceiveStatUrl);
        arg3.writeString(this.isTest);
        arg3.writeString(this.isSilent);
        arg3.writeString(this.isLocalTab);
        arg3.writeString(this.pushId);
        arg3.writeParcelable(this.adLiveInfoModel, arg4);
        arg3.writeParcelable(this.groupPostModel, arg4);
        arg3.writeLong(this.noticeTime);
    }


    @Override
    public String toString() {
        return this.articleStaticUrl + "\n" + this.badge + "\n" + this.blockPk + "\n"
                + this.isLocalTab + "\n" + this.isSilent + "\n" + this.isTest + "\n"
                + this.noticeTime + "\n" + this.page + "\n" + this.pushArriveStatUrl + "\n"
                + this.pushId + "\n" + this.pushImageFlag + "\n" + this.pushImageUrl + "\n"
                + this.pushPk + "\n" + this.pushReceiveStatUrl + "\n" + this.pushStatUrl + "\n"
                + this.pushSummaries + "\n" + this.pushTime + "\n" + this.pushTitle + "\n" + this.type + "\n";
    }

    public String getArticleStaticUrl() {
        return this.articleStaticUrl;
    }

    public String getType() {
        return this.type;
    }
}
