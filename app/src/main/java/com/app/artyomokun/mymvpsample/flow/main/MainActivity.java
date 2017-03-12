package com.app.artyomokun.mymvpsample.flow.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.artyomokun.mymvpsample.MVPApplication;
import com.app.artyomokun.mymvpsample.R;
import com.app.artyomokun.mymvpsample.common.view.ActivityMVPBase;
import com.app.artyomokun.mymvpsample.di.dagger.components.DaggerMainActivityComponent;
import com.app.artyomokun.mymvpsample.di.dagger.modules.MainModule;
import com.app.artyomokun.mymvpsample.flow.main.interfaces.Main;

public class MainActivity extends ActivityMVPBase {

    private Main.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
        loadData();
    }

    private void loadData() {
        mPresenter.loadData();
    }

    private void initDependencies() {
        mPresenter = DaggerMainActivityComponent.builder()
                .applicationComponent(MVPApplication.getApplicationComponent())
                .mainModule(new MainModule(this))
                .build()
                .getPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }
}
