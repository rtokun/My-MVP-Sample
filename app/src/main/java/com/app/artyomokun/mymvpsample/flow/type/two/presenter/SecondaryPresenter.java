package com.app.artyomokun.mymvpsample.flow.type.two.presenter;

import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.flow.type.two.interfaces.Secondary;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;
import com.bytesizebit.androidutils.DateUtils;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Artyom Okun on 03/03/2017.
 */

public class SecondaryPresenter implements
        Secondary.Presenter {

    private final Secondary.Interactor mInteractor;

    private final Secondary.View mView;

    private final RxUtils mRxUtils;

    @Inject
    public SecondaryPresenter(Secondary.Interactor interactor,
                              Secondary.View view,
                              RxUtils rxUtils) {
        mInteractor = interactor;
        mView = view;
        mRxUtils = rxUtils;
    }

    @Override
    public void deleteNote(Note note) {
        mInteractor.deleteNote(note)
                .observeOn(mRxUtils.mainThreadScheduler())
                .subscribe(mView::showNotes,
                        Timber::e,
                        () -> {
                        });
    }

    @Override
    public void addNote(String noteText) {
        Note note = createNote(noteText);
        mInteractor.addNote(note)
                .observeOn(mRxUtils.mainThreadScheduler())
                .subscribe(mView::showNotes,
                        Timber::e,
                        () -> {
                        });
    }

    @Override
    public void loadData() {
        mInteractor.getAllNotes()
                .subscribeOn(mRxUtils.computationThreadScheduler())
                .observeOn(mRxUtils.mainThreadScheduler())
                .subscribe(notes -> {
                            Timber.d("Retrieved list of notes, processing notes to view");
                            mView.showNotes(notes);
                        }, e -> Timber.e("Failed to retrieve notes, the reason: " + e.getMessage()),
                        () -> {
                        });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private Note createNote(String noteText) {
        Note note = new Note();
        note.setText(noteText);
        note.setDate(DateUtils.getCurrentTimeDate().toString());
        note.setId(System.currentTimeMillis());
        return note;
    }
}
