package com.ixicode.constant.ixiplan.triplisting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.placedetail.PlaceDetail;

import java.util.ArrayList;

public class BookingConfirmationActivity extends BaseActivity {
    Thread splashThread = null;
    ArrayList<String> xids = null;
    ArrayList<String> ids = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        xids = getIntent().getStringArrayListExtra(AppConstant.CITIES_XIDS);
        ids = getIntent().getStringArrayListExtra(AppConstant.CITIES_IDS);

        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l=(RelativeLayout) findViewById(R.id.activity_booking_confirmation);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        RelativeLayout iv = (RelativeLayout) findViewById(R.id.middle_layout);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(BookingConfirmationActivity.this,
                            PlaceDetail.class);
                    intent.putExtra(AppConstant.CITIES_XIDS, xids);
                    intent.putExtra(AppConstant.CITIES_IDS, ids);
                    startActivity(intent);
                    BookingConfirmationActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    BookingConfirmationActivity.this.finish();
                }

            }
        };
        splashThread.start();

    }

}
