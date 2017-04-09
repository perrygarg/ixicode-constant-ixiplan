package com.ixicode.constant.ixiplan.triplisting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.util.UIUtil;
import com.ixicode.constant.ixiplan.common.util.customprogress.CProgressHUD;
import com.ixicode.constant.ixiplan.triplisting.contract.ModeSelectorContract;
import com.ixicode.constant.ixiplan.triplisting.model.response.DataModelResponse;
import com.ixicode.constant.ixiplan.triplisting.presenter.ModeSelectorPresenter;

import java.util.ArrayList;

public class ModeSelectorActivity extends BaseActivity implements ModeSelectorContract.View, View.OnClickListener {
    private FloatingActionButton plane = null;
    private FloatingActionButton train = null;
    private FloatingActionButton car = null;
    private FloatingActionButton bus = null;
    private FloatingActionButton fastest = null;
    private FloatingActionButton cheapest = null;

    private ModeSelectorPresenter presenter = null;

    private CProgressHUD progressDialog = null;

    ArrayList<String> xids = null;
    private ArrayList<String> ids = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selector);
        
        init();
    }

    private void init() {
        findViewsById();

        presenter = new ModeSelectorPresenter(this, getApplicationContext());

        xids = getIntent().getStringArrayListExtra(AppConstant.CITIES_XIDS);

        ids = getIntent().getStringArrayListExtra(AppConstant.CITIES_IDS);

//        String[] citiesIds = new String[] {"1065223", "1075798"};

        if(xids != null && xids.size() == 2) {
            doFetchModes(xids);
        }
        
    }

    private void doFetchModes(ArrayList<String> citiesIds) {
        presenter.fetchModes(citiesIds.get(0), citiesIds.get(1));    }

    private void findViewsById() {
        plane = (FloatingActionButton) findViewById(R.id.plane);
        train = (FloatingActionButton) findViewById(R.id.train);
        bus = (FloatingActionButton) findViewById(R.id.bus);
        car = (FloatingActionButton) findViewById(R.id.car);
        fastest = (FloatingActionButton) findViewById(R.id.fastest);
        cheapest = (FloatingActionButton) findViewById(R.id.cheapest);

        plane.setOnClickListener(this);
        train.setOnClickListener(this);
        bus.setOnClickListener(this);
        car.setOnClickListener(this);
        fastest.setOnClickListener(this);
        cheapest.setOnClickListener(this);
    }

    private void hideFab(FloatingActionButton fab) {
        if (fab != null) {
            fab.setVisibility(View.GONE);
        }
    }

    private void showFab(FloatingActionButton fab) {
        if (fab != null) {
            fab.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgress() {
        if (this.progressDialog == null || !this.progressDialog.isShowing()) {
            this.progressDialog = UIUtil.showCustomProgress(this);
        }
    }

    @Override
    public void hideProgress() {
        UIUtil.dismissCustomProgress(this.progressDialog);
    }

    @Override
    public void onSuccessFetchingModes(ArrayList<String> avlblModes) {
        if(avlblModes.contains("flight")) {
            plane.setTag("flight");
            showFab(plane);
        }
        if(avlblModes.contains("train")) {
            train.setTag("train");
            showFab(train);
        }
        if(avlblModes.contains("bus")) {
            bus.setTag("bus");
            showFab(bus);
        }
        if(avlblModes.contains("car")) {
            car.setTag("car");
            showFab(car);
        }
        fastest.setTag("fastest");
        cheapest.setTag("cheapest");
    }

    @Override
    public void routeDetailsForRequestedMode(DataModelResponse.RoutesModelResponse route) {
        Intent intent = new Intent(ModeSelectorActivity.this, ModesListingActivity.class);
        intent.putExtra(AppConstant.ROUTE_MODEL_SERIALIZED,route);
        intent.putStringArrayListExtra(AppConstant.CITIES_XIDS, xids);
        intent.putStringArrayListExtra(AppConstant.CITIES_IDS, ids);
        startActivity(intent);
    }

    @Override
    public void routeDetailsForRequestedMode(DataModelResponse.FastestRouteResponse routeResponse) {
        Intent intent = new Intent(ModeSelectorActivity.this, ModesListingActivity.class);
        intent.putExtra(AppConstant.FASTEST_MODEL_SERIALIZED,routeResponse);
        intent.putStringArrayListExtra(AppConstant.IDS_INFO, xids);
        intent.putStringArrayListExtra(AppConstant.CITIES_IDS, ids);
        startActivity(intent);
    }

    @Override
    public void routeDetailsForRequestedMode(DataModelResponse.CheapestRouteResponse routeResponse) {
        Intent intent = new Intent(ModeSelectorActivity.this, ModesListingActivity.class);
        intent.putExtra(AppConstant.CHEAPEST_MODEL_SERIALIZED,routeResponse);
        intent.putStringArrayListExtra(AppConstant.IDS_INFO, xids);
        intent.putStringArrayListExtra(AppConstant.CITIES_IDS, ids);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        presenter.onClickOnMode((String) view.getTag());
    }

}
