package com.app.artyomokun.mymvpsample.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.common.dto.Note;
import com.app.artyomokun.mymvpsample.data.helpers.DataCacheManager;
import com.app.artyomokun.mymvpsample.data.helpers.shared.preferences.SharedPreferencesManager;
import com.app.artyomokun.mymvpsample.utils.rx.RxUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by artyomokun on 10/03/2017.
 */

public class DataRepositoryImpl implements DataRepository {

    private final MVPApplication mApplication;

    private final SharedPreferencesManager mSharedPreferencesManager;

    private final DataCacheManager mDataCacheManager;

    private final RxUtils mRxUtils;

    @Inject
    public DataRepositoryImpl(MVPApplication application,
                              SharedPreferencesManager sharedPreferencesManager,
                              DataCacheManager dataCacheManager,
                              RxUtils rxUtils) {

        this.mApplication = application;
        mSharedPreferencesManager = sharedPreferencesManager;
        mDataCacheManager = dataCacheManager;
        mRxUtils = rxUtils;
    }

    @Override
    public Observable<Note> deleteNote(long noteId) {
        return Observable.just(noteId)
                .map(id -> {
                    String notesList = mSharedPreferencesManager.getString(SharedPreferencesManager.NOTES);
                    if (SharedPreferencesManager.STRING_NOT_FOUND.equals(notesList)) {
                        return null;
                    } else {
                        List<Note> notes = deserializeFromString(notesList);
                        Note deletedNote = null;
                        for (int i = 0; i < notes.size(); i++) {
                            if (notes.get(i).getId() == id) {
                                deletedNote = notes.get(i);
                                notes.remove(i);
                                String jsonString = serializeToString(notes);
                                mSharedPreferencesManager.putString(SharedPreferencesManager.NOTES, jsonString);
                                mDataCacheManager.setNotes(notes);
                                break;
                            }
                        }
                        return deletedNote;
                    }
                })
                .subscribeOn(mRxUtils.computationThreadScheduler());
    }

    @Override
    public Observable<List<Note>> saveNote(Note note) {
        return Observable.just(note)
                .map(noteToSave -> {
                    String notesList = mSharedPreferencesManager.getString(SharedPreferencesManager.NOTES);
                    List<Note> notes;
                    if (SharedPreferencesManager.STRING_NOT_FOUND.equals(notesList)) {
                        notes = new ArrayList<>();
                    } else {
                        notes = deserializeFromString(notesList);
                    }
                    notes.add(noteToSave);
                    String jsonString = serializeToString(notes);
                    mSharedPreferencesManager.putString(SharedPreferencesManager.NOTES, jsonString);
                    mDataCacheManager.setNotes(notes);
                    return notes;
                })
                .subscribeOn(mRxUtils.computationThreadScheduler());
    }

    private String serializeToString(List<Note> notes) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Note>>() {

        }.getType();
        return gson.toJson(notes, listType);
    }

    private List<Note> deserializeFromString(String notesString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Note>>() {

        }.getType();
        return gson.fromJson(notesString, listType);
    }

    @Override
    public Observable<List<Note>> getAllNotes() {
        return Observable.create(subscriber -> {
            try {
                if (mDataCacheManager.getNotes() != null) {
                    subscriber.onNext(mDataCacheManager.getNotes());
                    subscriber.onCompleted();
                } else {
                    String notesList = mSharedPreferencesManager.getString(SharedPreferencesManager.NOTES);
                    if (SharedPreferencesManager.STRING_NOT_FOUND.equals(notesList)) {
                        subscriber.onError(new IOException("Data wasn't found in repository"));
                    } else {
                        List<Note> notes = deserializeFromString(notesList);
                        mDataCacheManager.setNotes(notes);
                        subscriber.onNext(notes);
                        subscriber.onCompleted();
                    }
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }
}
