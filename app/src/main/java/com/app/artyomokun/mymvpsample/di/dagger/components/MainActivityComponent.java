package com.app.artyomokun.mymvpsample.di.dagger.components;

import com.app.artyomokun.mymvpsample.di.dagger.modules.MainModule;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerActivity;
import com.app.artyomokun.mymvpsample.flow.main.interfaces.Main;

import dagger.Component;

/**
 * Created by artyomokun on 04/03/2017.
 */
@PerActivity
@Component(modules = MainModule.class,
           dependencies = {ApplicationComponent.class})
public interface MainActivityComponent {

    Main.View getView();

    Main.Presenter getPresenter();

}
