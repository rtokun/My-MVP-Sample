package com.app.artyomokun.mymvpsample.di.dagger.components;

import com.app.artyomokun.mymvpsample.di.dagger.modules.SecondaryModule;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerActivity;
import com.app.artyomokun.mymvpsample.flow.type.two.interfaces.Secondary;
import com.app.artyomokun.mymvpsample.flow.type.two.view.adapters.NotesAdapter;

import dagger.Component;

/**
 * Created by artyomokun on 12/03/2017.
 */
@PerActivity
@Component(modules = SecondaryModule.class,
           dependencies = {ApplicationComponent.class})
public interface SecondaryActivityComponent {

    Secondary.Presenter getPresenter();

    NotesAdapter getAdapter();
}
