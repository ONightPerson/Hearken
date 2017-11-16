package com.onightperson.hearken.notify.notificationparse.model;

import com.onightperson.hearken.notify.notificationparse.annotation.Transient;

import java.io.Serializable;

/**
 * 央视新闻
 */
public class Item implements Serializable {
    private boolean adLoadSuccess;
    private boolean adLoading;
    private boolean adShowing;
    private String allow_comment;
    private String allow_praise;
    private String allow_share;
    private String brief;
    private String category;
    private String commentNum;
    private String commentid;
    private String detailUrl;
    private String guid;
    private String headerBarTitle;
    private String hlsUrl;
    private int id;
    private String imageNum;
    private boolean isChecked;
    @Transient
    public boolean isPlaying;
    private boolean isRead;
    private boolean isShowHead;
    @Transient
    public boolean isShowListennews;
    private int isUpDown;
    private Object item;
    private String itemBrief;
    private String itemID;
    private Serializable itemImage;
    private int itemOrder;
    private String itemTitle;
    private String itemType;
    private String modelId;
    private String num;
    private String operate_time;
    private String photoCount;
    private String pubDate;
    private String pubTime;
    @Transient
    private static final long serialVersionUID = 1825401396716964256L;
    private String sharedImageUrl;
    private String source;
    private String tagColor;
    private String tagDesc;
    private String videoLength;
    private String videoPlayID;

    public Item() {
        super();
        this.isUpDown = 0;
        this.sharedImageUrl = "";
        this.isPlaying = false;
        this.isShowListennews = false;
    }

    public boolean equals(Object arg6) {
        boolean v1 = true;
        if (this != (((Item) arg6))) {
            if (arg6 == null) {
                v1 = false;
            } else {
                Object v0 = arg6;
                if (this.itemID == null) {
                    if (((Item) v0).itemID != null) {
                        v1 = false;
                    }
                } else if (!this.itemID.equals(((Item) v0).itemID)) {
                    v1 = false;
                }
            }
        }

        return v1;
    }

    public String getAllow_comment() {
        return this.allow_comment;
    }

    public String getAllow_praise() {
        return this.allow_praise;
    }

    public String getAllow_share() {
        return this.allow_share;
    }

    public String getBrief() {
        return this.brief;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCommentNum() {
        return this.commentNum;
    }

    public String getCommentid() {
        return this.commentid;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getHeaderBarTitle() {
        return this.headerBarTitle;
    }

    public String getHlsUrl() {
        return this.hlsUrl;
    }

    public int getId() {
        return this.id;
    }

    public String getImageNum() {
        return this.imageNum;
    }

    public int getIsUpDown() {
        return this.isUpDown;
    }

    public Object getItem() {
        return this.item;
    }

    public String getItemBrief() {
        return this.itemBrief;
    }

    public String getItemID() {
        return this.itemID;
    }

    public Serializable getItemImage() {
        return this.itemImage;
    }

    public int getItemOrder() {
        return this.itemOrder;
    }

    public String getItemTitle() {
        return this.itemTitle;
    }

    public String getItemType() {
        return this.itemType;
    }

    public String getModelId() {
        return this.modelId;
    }

    public String getNum() {
        return this.num;
    }

    public String getOperate_time() {
        return this.operate_time;
    }

    public String getPhotoCount() {
        return this.photoCount;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public String getPubTime() {
        return this.pubTime;
    }

    public String getSharedImageUrl() {
        return this.sharedImageUrl;
    }

    public static Item getSimpleItem(String arg1, String arg2, String arg3) {
        Item v0 = new Item();
        v0.setItemTitle(arg1);
        v0.setDetailUrl(arg2);
        v0.setItemType(arg3);
        return v0;
    }

    public String getSource() {
        return this.source;
    }

    public String getTagColor() {
        return this.tagColor;
    }

    public String getTagDesc() {
        return this.tagDesc;
    }

    public String getVideoLength() {
        return this.videoLength;
    }

    public String getVideoPlayID() {
        return this.videoPlayID;
    }

    public int hashCode() {
        int v3 = 0;
        int v2 = this.detailUrl == null ? 0 : this.detailUrl.hashCode();
        v2 = (v2 + 31) * 31;
        if (this.itemID != null) {
            v3 = this.itemID.hashCode();
        }

        return v2 + v3;
    }

    public boolean isAdLoadSuccess() {
        return this.adLoadSuccess;
    }

    public boolean isAdLoading() {
        return this.adLoading;
    }

    public boolean isAdShowing() {
        return this.adShowing;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public boolean isShowHead() {
        return this.isShowHead;
    }

    public void setAdLoadSuccess(boolean arg1) {
        this.adLoadSuccess = arg1;
    }

    public void setAdLoading(boolean arg1) {
        this.adLoading = arg1;
    }

    public void setAdShowing(boolean arg1) {
        this.adShowing = arg1;
    }

    public void setAllow_comment(String arg1) {
        this.allow_comment = arg1;
    }

    public void setAllow_praise(String arg1) {
        this.allow_praise = arg1;
    }

    public void setAllow_share(String arg1) {
        this.allow_share = arg1;
    }

    public void setBrief(String arg1) {
        this.brief = arg1;
    }

    public void setCategory(String arg1) {
        this.category = arg1;
    }

    public void setChecked(boolean arg1) {
        this.isChecked = arg1;
    }

    public void setCommentNum(String arg1) {
        this.commentNum = arg1;
    }

    public void setCommentid(String arg1) {
        this.commentid = arg1;
    }

    public void setDetailUrl(String arg1) {
        this.detailUrl = arg1;
    }

    public void setGuid(String arg1) {
        this.guid = arg1;
    }

    public void setHeaderBarTitle(String arg1) {
        this.headerBarTitle = arg1;
    }

    public void setHlsUrl(String arg1) {
        this.hlsUrl = arg1;
    }

    public void setId(int arg1) {
        this.id = arg1;
    }

    public void setImageNum(String arg1) {
        this.imageNum = arg1;
    }

    public void setIsUpDown(int arg1) {
        this.isUpDown = arg1;
    }

    public void setItem(Object arg1) {
        this.item = arg1;
    }

    public void setItemBrief(String arg1) {
        this.itemBrief = arg1;
    }

    public void setItemID(String arg1) {
        this.itemID = arg1;
    }

    public void setItemImage(Serializable arg1) {
        this.itemImage = arg1;
    }

    public void setItemOrder(int arg1) {
        this.itemOrder = arg1;
    }

    public void setItemTitle(String arg1) {
        this.itemTitle = arg1;
    }

    public void setItemType(String arg1) {
        this.itemType = arg1;
    }

    public void setModelId(String arg1) {
        this.modelId = arg1;
    }

    public void setNum(String arg1) {
        this.num = arg1;
    }

    public void setOperate_time(String arg1) {
        this.operate_time = arg1;
    }

    public void setPhotoCount(String arg1) {
        this.photoCount = arg1;
    }

    public void setPubDate(String arg1) {
        this.pubDate = arg1;
    }

    public void setPubTime(String arg1) {
        this.pubTime = arg1;
    }

    public void setRead(boolean arg1) {
        this.isRead = arg1;
    }

    public void setSharedImageUrl(String arg1) {
        this.sharedImageUrl = arg1;
    }

    public void setShowHead(boolean arg1) {
        this.isShowHead = arg1;
    }

    public void setSource(String arg1) {
        this.source = arg1;
    }

    public void setTagColor(String arg1) {
        this.tagColor = arg1;
    }

    public void setTagDesc(String arg1) {
        this.tagDesc = arg1;
    }

    public void setVideoLength(String arg1) {
        this.videoLength = arg1;
    }

    public void setVideoPlayID(String arg1) {
        this.videoPlayID = arg1;
    }
}

