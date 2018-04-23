package com.ouyang.qingque.model;

import java.io.Serializable;

/**
 * Created by fengyun on 2018/3/27.
 */

public class NoteAttaches implements Serializable {
    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsHaveVoice() {
        return isHaveVoice;
    }

    public void setIsHaveVoice(int isHaveVoice) {
        this.isHaveVoice = isHaveVoice;
    }

    public int getIsHaveCover() {
        return isHaveCover;
    }

    public void setIsHaveCover(int isHaveCover) {
        this.isHaveCover = isHaveCover;
    }

    public String[] getAttachVoices() {
        return attachVoices;
    }

    public void setAttachVoices(String[] attachVoices) {
        this.attachVoices = attachVoices;
    }

    public AttachCover getAttachCover() {
        return attachCover;
    }

    public void setAttachCover(AttachCover attachCover) {
        this.attachCover = attachCover;
    }

    private int fileType;
    private int sort;
    private String url;
    private int duration;
    private int height;
    private int width;
    private String name;
    private int isHaveVoice;
    private int isHaveCover;
    private String[] attachVoices;
    private AttachCover attachCover;
}
