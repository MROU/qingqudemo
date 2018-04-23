package com.ouyang.qingque.model;

import java.io.Serializable;

/**
 * Created by fengyun on 2018/3/27.
 */

public class NoteClass implements Serializable {
    private int isShow;

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private int classId;
    private String className;
}
