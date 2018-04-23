package com.ouyang.qingque.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fengyun on 2018/3/27.
 */

public class DataList implements Serializable {

    public int getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(int notesCount) {
        this.notesCount = notesCount;
    }


    private int notesCount;

    public List<FocusBean> getNotes() {
        return notes;
    }

    public void setNotes(List<FocusBean> notes) {
        this.notes = notes;
    }

    private List<FocusBean> notes;

}
