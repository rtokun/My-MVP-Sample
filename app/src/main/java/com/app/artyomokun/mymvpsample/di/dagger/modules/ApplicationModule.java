package com.app.artyomokun.mymvpsample.di.dagger.modules;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by artyomokun on 04/03/2017.
 */
@Module
public class ApplicationModule {

    MVPApplication mvpApplication;

    public ApplicationModule(MVPApplication application) {
        this.mvpApplication = application;
    }

    @Provides
    @PerApplication
    MVPApplication application() {
        return mvpApplication;
    }

}
