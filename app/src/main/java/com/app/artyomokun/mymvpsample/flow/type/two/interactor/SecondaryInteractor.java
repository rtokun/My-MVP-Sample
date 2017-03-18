package com.app.artyomokun.mymvpsample.flow.type.two.interactor;


import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.flow.type.two.interfaces.Secondary;

import java.util.List;

import rx.Observable;


public class SecondaryInteractor implements Secondary.Interactor {

    private final DataRepository mDataRepository;

    public SecondaryInteractor(DataRepository dataRepository) {
        mDataRepository = dataRepository;
    }

    @Override
    public Observable<List<Note>> addNote(Note note) {
        return mDataRepository.saveNote(note);
    }

    @Override
    public Observable<List<Note>> getAllNotes() {
        return mDataRepository.getAllNotes();
    }

    @Override
    public Observable<List<Note>> deleteNote(Note note) {
        return mDataRepository.deleteNote(note.getId()).flatMap(deletedNote -> getAllNotes());
    }

}
