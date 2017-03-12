package com.app.artyomokun.mymvpsample.flow.secondary.interfaces;

import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.common.view.PresenterBase;
import com.app.artyomokun.mymvpsample.flow.secondary.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.secondary.view.dialog.NoteDialogFragment;

import java.util.List;

import rx.Observable;

/**
 * Created by artyomokun on 10/03/2017.
 */

public interface Secondary {

    interface View {

        void onNoteAdded(int position);

        void showNotes(List<Note> notes);

        void registerAddingNoteListener(NoteDialogFragment.InteractionInterface interactionInterface);

        void registerDeleteNoteListener(NotesAdapter.ItemsInteractionCallbacks interactionCallbacks);

        void showError(String errorMessage);
    }

    interface Presenter extends PresenterBase {

        void onNoteAdded(int position);

        void addNote(String noteText);

        void loadData();
    }

    interface Interactor {

        Observable<List<Note>> addNote(Note note);

        Observable<List<Note>> getAllNotes();

        Observable<List<Note>> deleteNote(Note note);
    }

}
