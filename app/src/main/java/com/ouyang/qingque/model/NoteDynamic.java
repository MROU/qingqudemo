package com.ouyang.qingque.model;

import java.io.Serializable;

/**
 * Created by fengyun on 2018/3/27.
 */

public class NoteDynamic implements Serializable {
    private int attachCount;
    private int picCount;
    private int videoCount;
    private int clickCount;
    private int replyCount;
    private int praiseCount;
    private int treadCount;
    private int sharedCount;
    private int tipoffCount;
    private int collectCount;
    public int getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public int getPicCount() {
        return picCount;
    }

    public void setPicCount(int picCount) {
        this.picCount = picCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getTreadCount() {
        return treadCount;
    }

    public void setTreadCount(int treadCount) {
        this.treadCount = treadCount;
    }

    public int getSharedCount() {
        return sharedCount;
    }

    public void setSharedCount(int sharedCount) {
        this.sharedCount = sharedCount;
    }

    public int getTipoffCount() {
        return tipoffCount;
    }

    public void setTipoffCount(int tipoffCount) {
        this.tipoffCount = tipoffCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }


}
