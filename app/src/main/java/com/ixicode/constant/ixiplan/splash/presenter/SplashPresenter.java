package com.ixicode.constant.ixiplan.splash.presenter;

import android.os.Handler;

import com.ixicode.constant.ixiplan.splash.contract.SplashContract;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;

    private int SPLASH_DELAY = 2000;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
    }


    @Override
    public void doBackgroundTask() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.OnSuccessBackgroundTask();
            }
        }, SPLASH_DELAY);
    }

}
