package com.fuicui.gitdroid.gitdroid.gank.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 123 on 2016/9/1.
 */
public class GankItem {

    /**
     * _id : 56cc6d23421aa95caa707a69
     * createdAt : 2015-08-06T07:15:52.65Z
     * desc : 类似Link Bubble的悬浮式操作设计
     * publishedAt : 2015-08-07T03:57:48.45Z
     * type : Android
     * url : https://github.com/recruit-lifestyle/FloatingView
     * used : true
     * who : mthli
     */

    @SerializedName("_id")
    private String objectId;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
