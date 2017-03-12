package com.app.artyomokun.mymvpsample;

import android.app.Application;

import com.app.artyomokun.mymvpsample.di.dagger.components.ApplicationComponent;
import com.app.artyomokun.mymvpsample.di.dagger.components.DaggerApplicationComponent;
import com.app.artyomokun.mymvpsample.di.dagger.modules.ApplicationModule;

import timber.log.Timber;

/**
 * Created by artyomokun on 04/03/2017.
 */

public class MVPApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        Timber.plant(new Timber.DebugTree());
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
