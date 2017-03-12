package com.app.artyomokun.mymvpsample.di.dagger.modules;

import android.view.LayoutInflater;

import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerActivity;
import com.app.artyomokun.mymvpsample.flow.secondary.SecondaryActivity;
import com.app.artyomokun.mymvpsample.flow.secondary.interactor.SecondaryInteractor;
import com.app.artyomokun.mymvpsample.flow.secondary.interfaces.Secondary;
import com.app.artyomokun.mymvpsample.flow.secondary.presenter.SecondaryPresenter;
import com.app.artyomokun.mymvpsample.flow.secondary.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.secondary.view.dialog.NoteDialogFragment;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by artyomokun on 04/03/2017.
 */
@Module
public class SecondaryModule {

    private SecondaryActivity mSecondaryActivity;

    public SecondaryModule(SecondaryActivity secondaryActivity) {
        this.mSecondaryActivity = secondaryActivity;
    }

    @Provides
    @PerActivity
    Secondary.Interactor provideInteractor(DataRepository dataRepository) {
        Secondary.Interactor interactor = new SecondaryInteractor(dataRepository);
        return interactor;
    }

    @Provides
    @PerActivity
    Secondary.Presenter providePresenter(Secondary.Interactor interactor,
                                         RxUtils rxUtils) {
        Secondary.Presenter presenter = new SecondaryPresenter(interactor, mSecondaryActivity, rxUtils);
        return presenter;
    }

    @Provides
    NoteDialogFragment provideNoteDialogFragment() {
        return new NoteDialogFragment();
    }

    @Provides
    @PerActivity
    NotesAdapter provideNotesAdapter(LayoutInflater inflater) {
        return new NotesAdapter(inflater);
    }

    @Provides
    @PerActivity
    LayoutInflater provideLayoutInflater() {
        return mSecondaryActivity.getLayoutInflater();
    }

    @Provides
    RxUtils provideRxUtils() {
        return new RxUtils();
    }
}
