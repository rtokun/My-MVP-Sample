package com.app.artyomokun.mymvpsample.di.dagger.components;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.di.dagger.modules.ApplicationModule;
import com.app.artyomokun.mymvpsample.di.dagger.modules.DataModule;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerApplication;

import dagger.Component;

/**
 * Created by artyomokun on 04/03/2017.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    MVPApplication application();

    DataRepository dataRepository();

}
