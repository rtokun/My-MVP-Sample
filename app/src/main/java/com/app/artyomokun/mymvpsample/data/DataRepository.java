package com.app.artyomokun.mymvpsample.data;

import com.app.artyomokun.mymvpsample.common.dto.Note;

import java.util.List;

import rx.Observable;

/**
 * Created by artyomokun on 10/03/2017.
 */

public interface DataRepository {

    Observable<Note> deleteNote(long noteId);

    Observable<List<Note>> saveNote(Note note);

    Observable<List<Note>> getAllNotes();
}
