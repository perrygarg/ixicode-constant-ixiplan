package com.ixicode.constant.ixiplan.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.dashboard.DashboardActivity;
import com.ixicode.constant.ixiplan.splash.contract.SplashContract;
import com.ixicode.constant.ixiplan.splash.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        presenter = new SplashPresenter(this);

        presenter.doBackgroundTask();
    }

    @Override
    public void OnSuccessBackgroundTask() {
        //navigate to Dashboard Activity

        navigateToNextScreen();
    }

    private void navigateToNextScreen() {
        startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
        finish();
    }
}
