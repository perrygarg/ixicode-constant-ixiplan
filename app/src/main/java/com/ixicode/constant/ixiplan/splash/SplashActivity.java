package com.ixicode.constant.ixiplan.splash;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.dashboard.DashboardActivity;
import com.ixicode.constant.ixiplan.splash.contract.SplashContract;
import com.ixicode.constant.ixiplan.splash.presenter.SplashPresenter;
import com.ixicode.constant.ixiplan.triplisting.activities.BookingConfirmationActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    SplashPresenter presenter;
    private Thread splashThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        presenter = new SplashPresenter(this);

        StartAnimations();

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

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l=(RelativeLayout) findViewById(R.id.activity_splash);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 1000) {
                        sleep(50);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                }

            }
        };
        splashThread.start();

    }

}
