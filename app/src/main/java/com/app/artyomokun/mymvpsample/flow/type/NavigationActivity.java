package com.app.artyomokun.mymvpsample.flow.type;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.artyomokun.mymvpsample.R;
import com.app.artyomokun.mymvpsample.flow.type.one.MainActivity;
import com.app.artyomokun.mymvpsample.flow.type.two.SecondaryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_mvp_type_one)
    void onBtnOneClick(){
        navigateToActivity(MainActivity.class);
    }

    @OnClick(R.id.btn_mvp_type_two)
    void onBtnTwoClick(){
        navigateToActivity(SecondaryActivity.class);
    }

    private void navigateToActivity(Class<? extends Activity> activityTo){
        Intent intent = new Intent(this, activityTo);
        startActivity(intent);
    }
}
