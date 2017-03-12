package com.app.artyomokun.mymvpsample.di.dagger.modules;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.data.DataRepository;
import com.app.artyomokun.mymvpsample.data.DataRepositoryImpl;
import com.app.artyomokun.mymvpsample.data.helpers.DataCacheManager;
import com.app.artyomokun.mymvpsample.data.helpers.shared.preferences.SharedPreferencesManager;
import com.app.artyomokun.mymvpsample.di.dagger.scopes.PerApplication;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Created by artyomokun on 10/03/2017.
 */
@Module
public class DataModule {

    @Provides
    @PerApplication
    DataRepository provideDBProvider(MVPApplication application,
                                     SharedPreferencesManager sharedPreferencesManager,
                                     DataCacheManager dataCacheManager,
                                     RxUtils rxUtils) {
        return new DataRepositoryImpl(application,
                sharedPreferencesManager,
                dataCacheManager,
                rxUtils);
    }

    @Provides
    @PerApplication
    SharedPreferencesManager provideSharedPreferencesManager(MVPApplication application) {
        return new SharedPreferencesManager(application);
    }

    @Provides
    DataCacheManager provideDataCacheManager(){
        return new DataCacheManager();
    }

    @Provides
    RxUtils provideRxUtils(){
        return new RxUtils();
    }

}
