package com.ixicode.constant.ixiplan.dashboard;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.fragment.ConfirmationDialogFragment;
import com.ixicode.constant.ixiplan.common.location.GeoCodingHelper;
import com.ixicode.constant.ixiplan.common.location.GeoLocation;
import com.ixicode.constant.ixiplan.common.location.GoogleLocationHandler;
import com.ixicode.constant.ixiplan.common.location.GoogleLocationListener;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.common.util.UIUtil;
import com.ixicode.constant.ixiplan.common.util.customprogress.CProgressHUD;
import com.ixicode.constant.ixiplan.dashboard.fragment.InputFormFragment;
import com.ixicode.constant.ixiplan.dashboard.fragment.TrendingTripsFragment;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;
import com.ixicode.constant.ixiplan.dashboard.util.DashboardConstant;
import com.ixicode.constant.ixiplan.locationsearch.LocationSearchActivity;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionConstants;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionManager;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionRationaleDialogListener;
import com.ixicode.constant.ixiplan.permissionhandling.PermissionRequestModel;
import com.ixicode.constant.ixiplan.smsreader.HandleSmsRead;
import com.ixicode.constant.ixiplan.smsreader.Sms;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends BaseActivity
        implements DashboardContract.View, InputFormFragment.InputFormListener {
    FrameLayout inputFormContainer, trendingListContainer;
    private CProgressHUD progressDialog = null;

    private final String TAG_INPUT_FRAGMENT = "TAG_INPUT_FRAGMENT";
    private final String TAG_TRENDING_FRAGMENT = "TAG_TRENDING_FRAGMENT";
    private final String ITEM = "item";

    private GeoLocation tempSelectedLocation; //Location Visibile on Action Bar
    private GoogleLocationHandler locationHandler;
    private Handler handler = new Handler();
    private LocationTimeOutRunnable locationTimeOutRunnable;
    private RefreshLocationRunnable runnable;
    private CProgressHUD dialog = null;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.input_form_fragment_holder, inputFormFragment, TAG_INPUT_FRAGMENT).commit();

        TrendingTripsFragment trendingTripsFragment = new TrendingTripsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.trending_trips_fragment_holder, trendingTripsFragment, TAG_TRENDING_FRAGMENT).commit();

        new HandleReadSms().execute();
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
    public void onSuccessFetchCurrentLocation(String location) {

    }

    @Override
    public void onFailureFetchCurrentLocation(ErrorDisplay errorDisplay) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {

            switch (requestCode) {
                case DashboardConstant.REQUEST_CODE_FROM_LOCATION:

                    InputFormFragment inputFormFragment = (InputFormFragment) getSupportFragmentManager().findFragmentByTag(TAG_INPUT_FRAGMENT);
                    AutocompletePlaceResponseModel autocompletePlaceResponseModel = data.getParcelableExtra(DashboardConstant.CITY_LOCATION);
                    inputFormFragment.handleFromLocationResult(autocompletePlaceResponseModel);
                    break;

                case DashboardConstant.REQUEST_CODE_TO_LOCATION:

                    inputFormFragment = (InputFormFragment) getSupportFragmentManager().findFragmentByTag(TAG_INPUT_FRAGMENT);
                    autocompletePlaceResponseModel = data.getParcelableExtra(DashboardConstant.CITY_LOCATION);
                    inputFormFragment.handleToLocationResult(autocompletePlaceResponseModel);
                    break;
            }
        }


    }

    @Override
    public void handleFromLocation() {
        Intent intent = new Intent(this, LocationSearchActivity.class);
        startActivityForResult(intent, DashboardConstant.REQUEST_CODE_FROM_LOCATION);
    }

    @Override
    public void handleToLocation() {
        Intent intent = new Intent(this, LocationSearchActivity.class);
        startActivityForResult(intent, DashboardConstant.REQUEST_CODE_TO_LOCATION);
    }

    @Override
    public void navigateToLocationScreen(int requestCode) {

        Intent intent = new Intent(this, LocationSearchActivity.class);
        startActivityForResult(intent, requestCode);

    }

    /**
     * Check for Location Permission
     */
    private boolean checkForLocationPermission()
    {
        boolean isPermissionGranted = false;

        PermissionRequestModel model = new PermissionRequestModel(android.Manifest.permission.ACCESS_FINE_LOCATION, PermissionConstants.REQUEST_LOCATION_PERMISSION, getString(R.string.msg_permission_location_rationale), new PermissionRationaleDialogHandler(), true);

        isPermissionGranted = PermissionManager.handleRequestForPermission(model, this);
        return isPermissionGranted;
    }

    /**
     *
     */
    private class PermissionRationaleDialogHandler implements PermissionRationaleDialogListener
    {
        @Override
        public void onCancelPermissionRationale(int requestCode)
        {
            UIUtil.showToast(getString(R.string.msg_permission_location_denied), getApplicationContext());

        }
    }

    public void fetchCurrentLocation()
    {
        //Log.d(Constants.LOG_TAG, "Home - fetchCurrentLocation()");

        if(UIUtil.isGooglePlayServiceAvailable(this, true, getString(R.string.dialog_title_update_play_services), getString(R.string.dialog_message_update_play_services_for_location), getString(R.string.label_button_update), getString(R.string.label_button_later), true))
        {
            //Register for Locations
            if(checkForLocationPermission())
            {
                registerForLocationUpdates();

                //If Location Service On, Then Start Timer else wait for User Response
                if(UIUtil.isLocationServiceOn(getApplicationContext()))
                {
                    //Start Timer
                    setLocationTimeout(); //Timeout is Only for First time, so that banners hit can be taken with previous saved location if no location availble.
                }
            }
        }
    }

    private void setLocationTimeout()
    {
        //Log.d(Constants.LOG_TAG, "Home - setLocationTimeout()");

        locationTimeOutRunnable = new LocationTimeOutRunnable();
        handler.postDelayed(locationTimeOutRunnable, AppConstant.TIME_CURRENT_LOCATION_TIMEOUT);
    }

    /**
     *
     */
    class LocationTimeOutRunnable implements Runnable
    {
        @Override
        public void run()
        {
            //Log.d(Constants.LOG_TAG, "Home - LocationTimeOutRunnable.run()");

            disconnectLocationHandler();


            locationTimeOutRunnable = null;
        }
    }

    private void disconnectLocationHandler()
    {
        //Log.d(Constants.LOG_TAG, "Home - disconnectLocationHandler()");

        if(locationHandler != null)
        {
            locationHandler.disconnect();
        }

        locationHandler = null;
    }

    /**
     * Start Registering for Location Updates
     */
    private void registerForLocationUpdates()
    {
        //Log.d(Constants.LOG_TAG, "Home - registerForLocationUpdates()");

        //Disconnect if any available
        disconnectLocationHandler();

        locationHandler = new GoogleLocationHandler(new GoogleApiClientHandler(), this);
    }

    private class GoogleApiClientHandler implements GoogleLocationListener
    {
        @Override
        public void onGetLastKnownLocation(GeoLocation geoLocation)
        {
        }

        @Override
        public void onLocationUpdated(GeoLocation geoLocation)
        {
            //Log.d(Constants.LOG_TAG, "Home - onLocationUpdated()");

            //Disconnect Google API
            disconnectLocationHandler();

            //Remove Location Timeout Runnable
            removeLocationTimeoutRunnable();

            if(geoLocation != null)
            {
                //Get Location Name
                getLocationName(geoLocation);
            }
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult)
        {
            //Log.d(Constants.LOG_TAG, "Home - onLocationUpdated()");

            disconnectLocationHandler();
        }
    }

    private void removeLocationTimeoutRunnable()
    {
        //Log.d(Constants.LOG_TAG, "Home - removeLocationTimeoutRunnable()");

        if(locationTimeOutRunnable != null)
        {
            handler.removeCallbacks(locationTimeOutRunnable);
            locationTimeOutRunnable = null;
        }
    }


    private void getLocationName(GeoLocation geoLocation)
    {
        //Log.d(Constants.LOG_TAG, "Home - getLocationName()");

        GeoCodingHelper helper = new GeoCodingHelper(new GeoCodingHandler(geoLocation), this);
        helper.getLocationName(geoLocation);
    }

    /**
     * Reverse Geocoding Handler
     */
    class GeoCodingHandler implements GeoCodingHelper.GeocoderListener
    {
        GeoLocation geoLocation;

        public GeoCodingHandler(GeoLocation geoLocation)
        {
            this.geoLocation = geoLocation;
        }

        @Override
        public void onAddressFound(String addressName)
        {
            //Log.d(Constants.LOG_TAG, "Home - onAddressFound()");

            if(AppUtil.isStringEmpty(addressName))
            {
                addressName = getString(R.string.text_unknown_address);
            }

            //Set Location Name
            setLocationName(addressName);

            //Update Temp Selected Location
            tempSelectedLocation = geoLocation;

        }
    }

    private void setLocationName(String locationName)
    {

    }

    /**
     *
     */
    class RefreshLocationRunnable implements Runnable
    {
        @Override
        public void run()
        {
            //Fetch Current Location
            fetchCurrentLocationWithoutAnyDialog();

            //Set Next Update
            setLocationUpdateTimer();
        }
    }

    /**
     * Set Next Location Update Timer
     */
    private void setLocationUpdateTimer()
    {
        runnable = new RefreshLocationRunnable();
        handler.postDelayed(runnable, AppConstant.TIME_LOCATION_UPDATE_INTERVAL);
    }

//    private void setLocationTimeout()
//    {
//
//
//        locationTimeOutRunnable = new LocationTimeOutRunnable();
//        handler.postDelayed(locationTimeOutRunnable, AppConstant.TIME_CURRENT_LOCATION_TIMEOUT);
//    }

    /**
     * Fetch Location is Location Services ON and Permission is Granted else do not request or show show any dialog
     */
    private void fetchCurrentLocationWithoutAnyDialog()
    {
        //Log.d(Constants.LOG_TAG, "Home - fetchCurrentLocationWithoutAnyDialog()");

        if(UIUtil.isGooglePlayServiceAvailable(this, false, getString(R.string.dialog_title_update_play_services), getString(R.string.dialog_message_update_play_services_for_location), getString(R.string.label_button_update), getString(R.string.label_button_later), true))
        {
            //Register for Locations
            if(UIUtil.isLocationServiceOn(getApplicationContext()) && PermissionManager.isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION, this))
            {
                registerForLocationUpdates();
            }
        }
    }

    private class HandleReadSms extends AsyncTask<Void, Void, ArrayList<Sms>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = UIUtil.showCustomProgress(DashboardActivity.this);

        }

        @Override
        protected ArrayList<Sms> doInBackground(Void... params) {

            HandleSmsRead handleSmsRead = new HandleSmsRead();
            return handleSmsRead.getSms(DashboardActivity.this.getApplicationContext());

        }

        @Override
        protected void onPostExecute(ArrayList<Sms> arrayList) {
            super.onPostExecute(arrayList);

            UIUtil.dismissCustomProgress(dialog);

            if(!AppUtil.isCollectionEmpty(arrayList))
            {
                Sms sms = arrayList.get(0);
                String title = "Ticket already booked?";
                String msg = "I have detected that you have a ticket booked via IRCTC From : " + sms.from.name + "   To : " + sms.to.name + ". Do you want me to take you to" + sms.to.name +" ?";
                String positiveButtonText = "Yes";
                String negativeButtonText = "No";
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put(ITEM, arrayList.get(0));

                ConfirmationDialogFragment fragment = ConfirmationDialogFragment.getInstance(new HandleConfirmListener(), hashMap, title, msg, positiveButtonText, negativeButtonText, true);
                fragment.show(DashboardActivity.this.getSupportFragmentManager(), "");
            }
        }
    }

    private class HandleConfirmListener implements ConfirmationDialogFragment.ConfirmationDialogListener
    {

        @Override
        public void onDialogButtonClick(HashMap<String, Object> data, boolean isActionConfirmed) {

            if(isActionConfirmed)
            {
                Sms sms = (Sms) data.get(ITEM);

                InputFormFragment inputFormFragment = (InputFormFragment) getSupportFragmentManager().findFragmentByTag(TAG_INPUT_FRAGMENT);
                inputFormFragment.fromId = sms.from.code;
                inputFormFragment.toId = sms.to.code;
                inputFormFragment.goToNextDynamicScreen(sms.to.id);

            }
            else
            {

            }

        }
    }



}
