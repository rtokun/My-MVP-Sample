package com.app.artyomokun.mymvpsample.di.dagger.modules;

import android.view.LayoutInflater;

import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerActivity;
import com.app.artyomokun.mymvpsample.flow.type.one.MainActivity;
import com.app.artyomokun.mymvpsample.flow.type.one.interactor.MainInteractor;
import com.app.artyomokun.mymvpsample.flow.type.one.interfaces.Main;
import com.app.artyomokun.mymvpsample.flow.type.one.presenter.MainPresenter;
import com.app.artyomokun.mymvpsample.flow.type.one.view.MainView;
import com.app.artyomokun.mymvpsample.flow.type.one.view.adapters.NotesAdapter;
import com.app.artyomokun.mymvpsample.flow.type.one.view.dialog.NoteDialogFragment;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by artyomokun on 04/03/2017.
 */
@Module
public class MainModule {

    private MainActivity mMainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    @Provides
    @PerActivity
    Main.View provideView(NotesAdapter notesAdapter) {
        Main.View view = new MainView(mMainActivity, notesAdapter);
        return view;
    }

    @Provides
    @PerActivity
    Main.Interactor provideInteractor(DataRepository dataRepository) {
        Main.Interactor interactor = new MainInteractor(dataRepository);
        return interactor;
    }

    @Provides
    @PerActivity
    Main.Presenter providePresenter(Main.Interactor interactor,
                                    Main.View view,
                                    RxUtils rxUtils) {
        Main.Presenter presenter = new MainPresenter(interactor, view, rxUtils);
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
        return mMainActivity.getLayoutInflater();
    }

    @Provides
    RxUtils provideRxUtils(){
        return new RxUtils();
    }
}
