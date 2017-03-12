package com.app.artyomokun.mymvpsample.utils.rx;


import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by artyomokun on 10/03/2017.
 */

public class RxUtils {

    public Scheduler newThreadScheduler(){
        return Schedulers.newThread();
    }

    public Scheduler computationThreadScheduler(){
        return Schedulers.computation();
    }

    public Scheduler ioThreadScheduler(){
        return Schedulers.io();
    }

    public Scheduler mainThreadScheduler(){
        return AndroidSchedulers.mainThread();
    }

}
