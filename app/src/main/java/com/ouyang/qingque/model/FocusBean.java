package com.ouyang.qingque.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fengyun on 2018/3/27.
 */

public class FocusBean  implements Serializable {

    private long id;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtCreateStamp() {
        return gmtCreateStamp;
    }

    public void setGmtCreateStamp(long gmtCreateStamp) {
        this.gmtCreateStamp = gmtCreateStamp;
    }

    public int getIsHavePraise() {
        return isHavePraise;
    }

    public void setIsHavePraise(int isHavePraise) {
        this.isHavePraise = isHavePraise;
    }

    public int getIsHaveTread() {
        return isHaveTread;
    }

    public void setIsHaveTread(int isHaveTread) {
        this.isHaveTread = isHaveTread;
    }

    public int getIsHaveCollect() {
        return isHaveCollect;
    }

    public void setIsHaveCollect(int isHaveCollect) {
        this.isHaveCollect = isHaveCollect;
    }

    public String getGmtCreateStr() {
        return gmtCreateStr;
    }

    public void setGmtCreateStr(String gmtCreateStr) {
        this.gmtCreateStr = gmtCreateStr;
    }

    public NoteDynamic getNoteDynamic() {
        return noteDynamic;
    }

    public void setNoteDynamic(NoteDynamic noteDynamic) {
        this.noteDynamic = noteDynamic;
    }

    public List<NoteAttaches> getNoteAttaches() {
        return noteAttaches;
    }

    public void setNoteAttaches(List<NoteAttaches> noteAttaches) {
        this.noteAttaches = noteAttaches;
    }

    public CreatorDetail getCreatorDetail() {
        return creatorDetail;
    }

    public void setCreatorDetail(CreatorDetail creatorDetail) {
        this.creatorDetail = creatorDetail;
    }

    public NoteAtString getNoteAtString() {
        return noteAtString;
    }

    public void setNoteAtString(NoteAtString noteAtString) {
        this.noteAtString = noteAtString;
    }

    public int getConcernStatus() {
        return concernStatus;
    }

    public void setConcernStatus(int concernStatus) {
        this.concernStatus = concernStatus;
    }



    public NoteClass getNoteClass() {
        return noteClass;
    }

    public void setNoteClass(NoteClass noteClass) {
        this.noteClass = noteClass;
    }

    public NoteTopic getNoteTopic() {
        return noteTopic;
    }

    public void setNoteTopic(NoteTopic noteTopic) {
        this.noteTopic = noteTopic;
    }

    public int getCanCommentClock() {
        return canCommentClock;
    }

    public void setCanCommentClock(int canCommentClock) {
        this.canCommentClock = canCommentClock;
    }

    public int getShowCss() {
        return showCss;
    }

    public void setShowCss(int showCss) {
        this.showCss = showCss;
    }

    public int getTakePartInCount() {
        return takePartInCount;
    }

    public void setTakePartInCount(int takePartInCount) {
        this.takePartInCount = takePartInCount;
    }

    public int getShowTakePartIn() {
        return showTakePartIn;
    }

    public void setShowTakePartIn(int showTakePartIn) {
        this.showTakePartIn = showTakePartIn;
    }

    private int level;
    private String content;
    private int sectionId;
    private long creator;
    private String gmtCreate;
    private long gmtCreateStamp;
    private int isHavePraise;
    private int isHaveTread;
    private int isHaveCollect;
    private String gmtCreateStr;
    private NoteDynamic noteDynamic;
    private List<NoteAttaches> noteAttaches;
    private  CreatorDetail creatorDetail;
    private NoteAtString noteAtString;
    private int concernStatus;

    public Object[] getNoteOpuses() {
        return noteOpuses;
    }

    public void setNoteOpuses(Object[] noteOpuses) {
        this.noteOpuses = noteOpuses;
    }

    private Object[] noteOpuses;
    private NoteClass noteClass;
    private NoteTopic noteTopic;
    private int canCommentClock;
    private int showCss;
    private int takePartInCount;
    private int showTakePartIn;
}
