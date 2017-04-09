package com.ixicode.constant.ixiplan.triplisting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.listners.OnItemClickListener;
import com.ixicode.constant.ixiplan.triplisting.adapter.CheapestRouteAdapter;
import com.ixicode.constant.ixiplan.triplisting.adapter.FastestRouteAdapter;
import com.ixicode.constant.ixiplan.triplisting.adapter.RouteModelAdapter;
import com.ixicode.constant.ixiplan.triplisting.contract.ModesListingContract;
import com.ixicode.constant.ixiplan.triplisting.model.modeslisting.CheapestRouteModel;
import com.ixicode.constant.ixiplan.triplisting.model.modeslisting.FastestRouteModel;
import com.ixicode.constant.ixiplan.triplisting.model.modeslisting.GeneralRouteModel;
import com.ixicode.constant.ixiplan.triplisting.model.response.DataModelResponse;

import java.util.ArrayList;

public class ModesListingActivity extends BaseActivity implements ModesListingContract.View {
    RecyclerView view = null;
    ArrayList<GeneralRouteModel> generalRouteList = null;
    ArrayList<FastestRouteModel> fastestRouteList = null;
    ArrayList<CheapestRouteModel> cheapestRouteList = null;
    RouteModelAdapter routeModelAdapter = null;
    FastestRouteAdapter fastestRouteAdapter = null;
    CheapestRouteAdapter cheapestRouteAdapter = null;
    ArrayList<String> xids = null;
    ArrayList<String> ids = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes_listing);

        setupToolbar(getString(R.string.options_for_chosen_mode), true);

        init();
    }

    private void init() {

        view = (RecyclerView) findViewById(R.id.r_view);

        generalRouteList = new ArrayList<>();
        fastestRouteList = new ArrayList<>();
        cheapestRouteList = new ArrayList<>();

        getDataFromIntent();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        view.hasFixedSize();
        view.setLayoutManager(linearLayoutManager);
    }

    private void getDataFromIntent() {
        xids = getIntent().getStringArrayListExtra(AppConstant.CITIES_XIDS);
        ids = getIntent().getStringArrayListExtra(AppConstant.CITIES_IDS);

        DataModelResponse.RoutesModelResponse modelResponse = (DataModelResponse.RoutesModelResponse) getIntent().getSerializableExtra(AppConstant.ROUTE_MODEL_SERIALIZED);
        DataModelResponse.FastestRouteResponse fastestRoute = (DataModelResponse.FastestRouteResponse) getIntent().getSerializableExtra(AppConstant.FASTEST_MODEL_SERIALIZED);
        DataModelResponse.CheapestRouteResponse cheapestRoute = (DataModelResponse.CheapestRouteResponse) getIntent().getSerializableExtra(AppConstant.CHEAPEST_MODEL_SERIALIZED);
        if(modelResponse != null) {
            fillValuesAndSetAdapter(modelResponse);
        } else if(fastestRoute != null) {
            fillValuesAndSetAdapter(fastestRoute);
        } else if(cheapestRoute != null) {
            fillValuesAndSetAdapter(cheapestRoute);
        }
    }

    private void fillValuesAndSetAdapter(DataModelResponse.CheapestRouteResponse cheapestRoute) {

        int size = cheapestRoute.firstStep.carriers.length;

        for(int i=0; i<size; i++)
        {
            CheapestRouteModel cheapestRouteModel = new CheapestRouteModel();
            cheapestRouteModel.name = cheapestRoute.firstStep.carriers[i].carrierName;
            cheapestRouteModel.fare = cheapestRoute.price;
            cheapestRouteModel.duration = cheapestRoute.firstStep.carriers[i].time;
            switch (cheapestRoute.firstStep.mode) {
                case "flight":
                    cheapestRouteModel.type = AppConstant.FLIGHT;
                    break;
                case "train":
                    cheapestRouteModel.type = AppConstant.TRAIN;
                    break;
                case "car":
                    cheapestRouteModel.type = AppConstant.CAR;
                    break;
                case "bus":
                    cheapestRouteModel.type = AppConstant.BUS;
                    break;
            }
            cheapestRouteList.add(cheapestRouteModel);
        }
        cheapestRouteAdapter = new CheapestRouteAdapter(null, false, this.getApplicationContext(),
                cheapestRouteList, new HandleItemClickListener());
        view.setAdapter(cheapestRouteAdapter);

    }

    private void fillValuesAndSetAdapter(DataModelResponse.FastestRouteResponse fastestRoute) {
        int size = fastestRoute.firstStep.carriers.length;

        for(int i=0; i<size; i++)
        {
            FastestRouteModel fastestRouteModel = new FastestRouteModel();
            fastestRouteModel.name = fastestRoute.firstStep.carriers[i].carrierName;
            fastestRouteModel.fare = fastestRoute.price;
            fastestRouteModel.duration = fastestRoute.firstStep.carriers[i].time;
            switch (fastestRoute.firstStep.mode) {
                case "flight":
                    fastestRouteModel.type = AppConstant.FLIGHT;
                    break;
                case "train":
                    fastestRouteModel.type = AppConstant.TRAIN;
                    break;
                case "car":
                    fastestRouteModel.type = AppConstant.CAR;
                    break;
                case "bus":
                    fastestRouteModel.type = AppConstant.BUS;
                    break;
            }
            fastestRouteList.add(fastestRouteModel);
        }
        fastestRouteAdapter = new FastestRouteAdapter(null, false, this.getApplicationContext(),
                fastestRouteList, new HandleItemClickListener());
        view.setAdapter(fastestRouteAdapter);

    }

    private void fillValuesAndSetAdapter(DataModelResponse.RoutesModelResponse modelResponse) {
        GeneralRouteModel generalRouteModel = new GeneralRouteModel();
        switch (modelResponse.allStepModes) {
            case "flight":
                generalRouteModel.type = AppConstant.FLIGHT;
                generalRouteModel.fare = modelResponse.price;
                break;
            case "train":
                generalRouteModel.type = AppConstant.TRAIN;
                generalRouteModel.fare = modelResponse.price;
                break;
            case "car":
                generalRouteModel.type = AppConstant.CAR;
                generalRouteModel.fare = modelResponse.price;
                break;
            case "bus":
                generalRouteModel.type = AppConstant.BUS;
                generalRouteModel.fare = modelResponse.price;
                break;
        }

        int size = modelResponse.firstStep.carriers.length;

        for(int i=0; i<size; i++) {
            GeneralRouteModel generalRouteModel1 = new GeneralRouteModel();
            generalRouteModel1.type = generalRouteModel.type;
            generalRouteModel1.fare = generalRouteModel.fare;
            generalRouteModel1.name = modelResponse.firstStep.carriers[i].carrierName;
            generalRouteModel1.duration = modelResponse.firstStep.carriers[i].time;
            generalRouteList.add(generalRouteModel1);
        }
        routeModelAdapter = new RouteModelAdapter(null, false, this.getApplicationContext(),
                generalRouteList, new HandleItemClickListener());
        view.setAdapter(routeModelAdapter);

    }

    private class HandleItemClickListener implements OnItemClickListener
    {
        @Override
        public void onItemClick(int position) {

            gotoNextScreen();

        }
    }

    private void gotoNextScreen() {
        startActivity(new Intent(ModesListingActivity.this, BookingConfirmationActivity.class).putStringArrayListExtra(AppConstant.CITIES_XIDS, xids).putStringArrayListExtra(AppConstant.CITIES_IDS, ids));
    }

}
