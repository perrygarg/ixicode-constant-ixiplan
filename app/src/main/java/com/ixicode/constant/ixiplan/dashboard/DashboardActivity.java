package com.ixicode.constant.ixiplan.dashboard;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.dashboard.fragment.InputFormFragment;
import com.ixicode.constant.ixiplan.dashboard.fragment.TrendingTripsFragment;

public class DashboardActivity extends BaseActivity {
    FrameLayout inputFormContainer, trendingListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setupToolbar(getString(R.string.dashboard_title), true);

        init();
    }

    private void init() {
        inputFormContainer = (FrameLayout) findViewById(R.id.input_form_fragment_holder);
        trendingListContainer = (FrameLayout) findViewById(R.id.trending_trips_fragment_holder);

        InputFormFragment inputFormFragment = new InputFormFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.input_form_fragment_holder, inputFormFragment).commit();

        TrendingTripsFragment trendingTripsFragment = new TrendingTripsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.trending_trips_fragment_holder, trendingTripsFragment).commit();

    }
}
