package com.app.artyomokun.mymvpsample.flow.main.interactor;


import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.flow.main.interfaces.Main;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;

import java.util.List;

import rx.Observable;


public class MainInteractor implements Main.Interactor {

    private final DataRepository mDataRepository;

    public MainInteractor(DataRepository dataRepository) {
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
