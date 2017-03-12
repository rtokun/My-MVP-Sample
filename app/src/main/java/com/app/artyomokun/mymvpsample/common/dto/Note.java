package com.app.artyomokun.mymvpsample.common.dto;

public class Note {

    private long id = -1;

    private String mText;

    private String mDate;

    public Note() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return mDate;
    }

    public String getText() {
        return mText;
    }
}