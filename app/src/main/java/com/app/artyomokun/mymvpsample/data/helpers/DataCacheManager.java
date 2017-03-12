package com.app.artyomokun.mymvpsample.data.helpers;

import com.app.artyomokun.mymvpsample.common.dto.Note;

import java.util.List;

/**
 * Created by artyomokun on 11/03/2017.
 */

public class DataCacheManager {

    private List<Note> mNotes;

    public List<Note> getNotes() {
        return mNotes;
    }

    public void setNotes(List<Note> notes) {
        this.mNotes = notes;
    }
}
