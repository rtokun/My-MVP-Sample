package com.app.artyomokun.mymvpsample.di.dagger.modules;

import android.view.LayoutInflater;

import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerActivity;
import com.app.artyomokun.mymvpsample.flow.type.two.SecondaryActivity;
import com.app.artyomokun.mymvpsample.flow.type.two.interactor.SecondaryInteractor;
import com.app.artyomokun.mymvpsample.flow.type.two.interfaces.Secondary;
import com.app.artyomokun.mymvpsample.flow.type.two.presenter.SecondaryPresenter;
import com.app.artyomokun.mymvpsample.flow.type.two.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.type.two.view.dialog.NoteDialogFragment;
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
        return new SecondaryInteractor(dataRepository);
    }

    @Provides
    @PerActivity
    Secondary.Presenter providePresenter(Secondary.Interactor interactor,
                                         RxUtils rxUtils) {
        return new SecondaryPresenter(interactor, mSecondaryActivity, rxUtils);
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
