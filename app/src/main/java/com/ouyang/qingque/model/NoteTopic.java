package com.ouyang.qingque.model;

import java.io.Serializable;

/**
 * Created by fengyun on 2018/3/27.
 */

public class NoteTopic implements Serializable {
    private int topicId;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    private int isShow;
    private String topicName;
}
