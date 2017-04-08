package com.ixicode.constant.ixiplan.triplisting.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.common.util.UIUtil;
import com.ixicode.constant.ixiplan.common.util.customprogress.CProgressHUD;
import com.ixicode.constant.ixiplan.triplisting.contract.ModeSelectorContract;
import com.ixicode.constant.ixiplan.triplisting.presenter.ModeSelectorPresenter;

import java.util.ArrayList;

public class ModeSelectorActivity extends BaseActivity implements ModeSelectorContract.View{
    private FloatingActionButton plane = null;
    private FloatingActionButton train = null;
    private FloatingActionButton car = null;
    private FloatingActionButton bus = null;
    private FloatingActionButton fastest = null;
    private FloatingActionButton cheapest = null;

    private ModeSelectorPresenter presenter = null;

    private CProgressHUD progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selector);
        
        init();
    }

    private void init() {
        findViewsById();

        presenter = new ModeSelectorPresenter(this);

        String[] citiesIds = getIntent().getStringArrayExtra(AppConstant.CITIES_IDS);
        if(!AppUtil.isArrayEmpty(citiesIds)) {

        }
        
    }

    private void findViewsById() {
        plane = (FloatingActionButton) findViewById(R.id.plane);
        train = (FloatingActionButton) findViewById(R.id.train);
        bus = (FloatingActionButton) findViewById(R.id.bus);
        car = (FloatingActionButton) findViewById(R.id.car);
        fastest = (FloatingActionButton) findViewById(R.id.fastest);
        cheapest = (FloatingActionButton) findViewById(R.id.cheapest);
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

    }
}
