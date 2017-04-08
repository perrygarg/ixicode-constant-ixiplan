package com.ixicode.constant.ixiplan.splash.contract;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public interface SplashContract {

    interface View {

        void OnSuccessBackgroundTask();

    }

    interface Presenter {
        void doBackgroundTask();
    }

}
