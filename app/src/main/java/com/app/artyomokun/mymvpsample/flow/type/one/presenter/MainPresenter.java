package com.app.artyomokun.mymvpsample.flow.type.one.presenter;

import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.flow.type.one.interfaces.Main;
import com.app.artyomokun.mymvpsample.flow.type.one.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.type.one.view.dialog.NoteDialogFragment;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;
import com.bytesizebit.androidutils.DateUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by Artyom Okun on 03/03/2017.
 */

public class MainPresenter implements Main.Presenter,
        NoteDialogFragment.InteractionInterface, NotesAdapter.ItemsInteractionCallbacks {

    private final Main.Interactor mInteractor;

    private final Main.View mView;

    private final RxUtils mRxUtils;

    @Inject
    public MainPresenter(Main.Interactor interactor,
                         Main.View view,
                         RxUtils rxUtils) {
        mInteractor = interactor;
        mView = view;
        mRxUtils = rxUtils;
    }

    @Override
    public void loadData() {
        mInteractor.getAllNotes()
                .subscribeOn(mRxUtils.computationThreadScheduler())
                .observeOn(mRxUtils.mainThreadScheduler())
                .subscribe(provideLoadAllNotesSubscriber());
    }

    Subscriber<List<Note>> provideLoadAllNotesSubscriber() {
        return new Subscriber<List<Note>>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("Failed to retrieve notes, the reason: " + e.getMessage());
            }

            @Override
            public void onNext(List<Note> notes) {
                Timber.d("Retrieved list of notes, processing notes to view");
                mView.showNotes(notes);
            }
        };
    }


    @Override
    public void onResume() {
        mView.registerAddingNoteListener(this);
        mView.registerDeleteNoteListener(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onNewNoteWritten(String noteText) {
        Note note = createNote(noteText);
        mInteractor.addNote(note)
                .observeOn(mRxUtils.mainThreadScheduler())
                .subscribe(mView::showNotes,
                        throwable -> {
                            mView.showError("Failed to add new note");
                            Timber.e(throwable);
                        },
                        () -> {
                        });
    }

    private Note createNote(String noteText) {
        Note note = new Note();
        note.setText(noteText);
        note.setDate(DateUtils.getCurrentTimeDate().toString());
        note.setId(System.currentTimeMillis());
        return note;
    }

    @Override
    public void onItemDeleted(Note noteToDelete) {
        mInteractor.deleteNote(noteToDelete)
                .observeOn(mRxUtils.mainThreadScheduler())
                .subscribe(mView::showNotes,
                        Throwable::printStackTrace,
                        () -> {
                        });
    }
}
