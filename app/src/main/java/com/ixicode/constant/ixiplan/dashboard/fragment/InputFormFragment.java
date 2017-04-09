package com.ixicode.constant.ixiplan.dashboard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.fragment.BaseFragment;
import com.ixicode.constant.ixiplan.common.location.GeoLocation;
import com.ixicode.constant.ixiplan.common.location.GoogleLocationHandler;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.common.util.UIUtil;
import com.ixicode.constant.ixiplan.dashboard.DashboardActivity;
import com.ixicode.constant.ixiplan.dashboard.InputFormPresenter;
import com.ixicode.constant.ixiplan.dashboard.adapter.AutocompletePlaceAdapter;
import com.ixicode.constant.ixiplan.dashboard.contract.InputFormContract;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceRequestModel;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;
import com.ixicode.constant.ixiplan.dashboard.util.DashboardConstant;
import com.ixicode.constant.ixiplan.locationsearch.LocationSearchActivity;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionConstants;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionManager;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionRequestModel;
import com.ixicode.constant.ixiplan.placedetail.PlaceDetail;
import com.ixicode.constant.ixiplan.triplisting.activities.ModeSelectorActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class InputFormFragment extends BaseFragment implements InputFormContract.View{

    //    private AutocompletePlaceAdapter fromAdapter = null;
//    private AutocompletePlaceAdapter toAdapter = null;
    private InputFormContract.Presenter presenter = null;
    private AutocompletePlaceRequestModel autocompletePlaceRequestModel = null;
    private EditText fromInput = null;
    private EditText toInput = null;
    private ImageView imageViewCurrentLoc = null;
    private Button submitBtn = null;
    public String fromId, toId, fromCityId, toCityId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_form_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new InputFormPresenter(getContext().getApplicationContext(), this);
        autocompletePlaceRequestModel = new AutocompletePlaceRequestModel();

        fromInput = (EditText) view.findViewById(R.id.from_input);
        toInput = (EditText) view.findViewById(R.id.to_input);
        imageViewCurrentLoc = (ImageView) view.findViewById(R.id.imageViewCurrentLoc);
        submitBtn = (Button) view.findViewById(R.id.submit_btn);

        HandlePlaceClickListener handlePlaceClickListener = new HandlePlaceClickListener();
        fromInput.setOnClickListener(handlePlaceClickListener);
        toInput.setOnClickListener(handlePlaceClickListener);
        submitBtn.setOnClickListener(handlePlaceClickListener);
    }

    public void goToNextDynamicScreen(String id) {
        startActivity(new Intent(getActivity(), PlaceDetail.class).putExtra("CITY_ID", id));
    }

    private class HandlePlaceClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {

            int id = view.getId();
            InputFormListener inputFormListener = (InputFormListener) getActivity();

            switch(id)
            {
                case R.id.from_input:

                    inputFormListener.navigateToLocationScreen(DashboardConstant.REQUEST_CODE_FROM_LOCATION);

                    break;

                case R.id.to_input:

                    inputFormListener.navigateToLocationScreen(DashboardConstant.REQUEST_CODE_TO_LOCATION);

                    break;

                case R.id.imageViewCurrentLoc:

                    break;
                case R.id.submit_btn:
                    if(validateInputs()) {
                        navigateToNextScreen();
                    } else {
                        UIUtil.makeAlert(getActivity(), getString(R.string.pls_fill_fields), getString(R.string.dashboard), getString(R.string.ok));
                    }
                    break;
            }

        }
    }

    private boolean validateInputs() {
        return !AppUtil.isStringEmpty(fromInput.getText().toString()) && !AppUtil.isStringEmpty(toInput.getText().toString());
    }

    public void navigateToNextScreen() {
        ArrayList<String> cityXids = new ArrayList<>();
        cityXids.add(fromId);
        cityXids.add(toId);

        ArrayList<String> cityIds = new ArrayList<>();
        cityIds.add(fromCityId);
        cityIds.add(toCityId);

        Intent intent = new Intent(getActivity(), ModeSelectorActivity.class);
        intent.putStringArrayListExtra(AppConstant.CITIES_XIDS, cityXids);
        intent.putStringArrayListExtra(AppConstant.CITIES_IDS, cityIds);
        startActivity(intent);
    }

    @Override
    public void onSubmitSuccess() {

    }

    @Override
    public void onSubmitFailure(ErrorDisplay errorDisplay) {

    }

    public interface InputFormListener
    {
        void navigateToLocationScreen(int code);
        void handleToLocation();
        void handleFromLocation();
        void fetchCurrentLocation();
    }

    public void handleToLocationResult(AutocompletePlaceResponseModel response)
    {

        toInput.setText(AppUtil.setStringNotNull(response.cityName));
        toId = response.xId;
        toCityId = response.cityId;
    }

    public void handleFromLocationResult(AutocompletePlaceResponseModel response)
    {
        fromInput.setText(AppUtil.setStringNotNull(response.cityName));
        fromId = response.xId;
        fromCityId = response.cityId;
    }


    public void setCurrentLocation(String location)
    {
        fromInput.setText(location);
    }



}
