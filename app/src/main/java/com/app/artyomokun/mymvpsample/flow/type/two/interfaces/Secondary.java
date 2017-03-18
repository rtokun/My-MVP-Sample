package com.app.artyomokun.mymvpsample.flow.type.two.interfaces;

import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.common.view.PresenterBase;

import java.util.List;

import rx.Observable;

/**
 * Created by artyomokun on 10/03/2017.
 */

public interface Secondary {

    interface View {

        void showNotes(List<Note> notes);

        void showError(String errorMessage);
    }

    interface Presenter extends PresenterBase {

        void deleteNote(Note note);

        void addNote(String noteText);

        void loadData();
    }

    interface Interactor {

        Observable<List<Note>> addNote(Note note);

        Observable<List<Note>> getAllNotes();

        Observable<List<Note>> deleteNote(Note note);
    }

}
