package com.app.artyomokun.mymvpsample.data.helpers.shared.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.artyomokun.mymvpsample.MVPApplication;

import javax.inject.Inject;

/**
 * Created by artyomokun on 10/03/2017.
 */

public class SharedPreferencesManager implements Constants {

    MVPApplication mApplication;

    SharedPreferences mDefaultPreferences;

    @Inject
    public SharedPreferencesManager(MVPApplication application) {
        this.mApplication = application;
        mDefaultPreferences = PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

    public String getString(String key) {
        return mDefaultPreferences.getString(key, STRING_NOT_FOUND);
    }

    public void putString(String key, String stringToSave) {
        mDefaultPreferences.edit().putString(key, stringToSave).apply();
    }
}
