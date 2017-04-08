package com.ixicode.constant.ixiplan.common.location;

import android.app.Dialog;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;


public class GoogleLocationHandler implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    /**
     * Constants
     */
    private final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private final long TIME_FIRST_LOCATION_UPDATE_INTERVAL = 5000;
    private final long TIME_FIRST_LOCATION_FASTEST_INTERVAL = 3000;
    public static int REQUEST_CHECK_LOCATION_SETTINGS = 101;

    /**
     * Google API Client Instance
     */
    private GoogleApiClient googleApiClient;

    /**
     * Listener Callback
     */
    private GoogleLocationListener googleLocationListener;
    FragmentActivity context;

    private LocationListenerHandler locationHandler;
    private LocationRequest locationRequest;
    private LocationSettingListener locationSettingsListener;

    public GoogleLocationHandler(GoogleLocationListener locationListener, Context context)
    {
        this.googleLocationListener = locationListener;
        this.context = (FragmentActivity) context;

        //Build and Connect Google Api Client
        this.googleApiClient = buildGoogleApiClientForLocation();
    }

    /**
     * Build Google Api Client for Location
     */
    public GoogleApiClient buildGoogleApiClientForLocation()
    {
        //Log.i("Google Location Handler", "Google Location Handler : buildGoogleApiClientForLocation()");

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API);
        //.enableAutoManage(context, this); Used to auto handle the Connection Failed Errors

        //Build
        googleApiClient = builder.build();

        //Connect
        googleApiClient.connect();

        //Log.i("Google Location Handler"", "Google Location Handler : googleApiClient.connect() called");

        return googleApiClient;
    }

    /** Interface Methods **/
    @Override
    public void onConnected(Bundle bundle)
    {
        //Log.i("Google Location Handler", "Google Location Handler : Google API Client Connected.");

        //Create Location Request
        createFirstLocationRequest();

        //Start Register for Location Updates
        startRegisterForLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        //Connection Suspended
        //Log.i("Google Location Handler", "Google Play Serviecs Connection Suspended. Reconnecting...");

        if(googleApiClient != null)
        {
            //Reconnect
            googleApiClient.reconnect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        // Show An Error Message
        //Log.i("Google Location Handler", "Problem Connecting to Google Play Serviecs.");

        //Show Google Playe Service Default Error Dialog
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Dialog errorDialog = googleApiAvailability.getErrorDialog(context, connectionResult.getErrorCode(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
        errorDialog.show();
    }

    /**********/

    /**
     * Create Location Request
     * @return
     */
    public void createFirstLocationRequest()
    {
        //Instantiate Location Request
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(TIME_FIRST_LOCATION_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(TIME_FIRST_LOCATION_FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        this.locationRequest = locationRequest;
    }

    /**
     * Create Location Request
     * @param interval - The interval at which this app requests location
     * @param fastestInterval - Fastest Location Update from requested by other applications
     * @return
     */
    /*public LocationRequest createLocationRequest(long interval, long fastestInterval)
    {
        //Instantiate Location Request
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(fastestInterval);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

         return locationRequest;
    }
    */

    /**
     * Start Register for Location Updates
     */
    public void startRegisterForLocationUpdates()
    {
        //Check if Location Settings are Properly Configured
        checkLocationSettings();
    }

    /**
     * Build Location Settings Request
     */
    private void checkLocationSettings()
    {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        //check whether current location settings are satisfied:
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

        //Set Result Callback
        result.setResultCallback(new LocationSettingsResultCallback());
    }

    /**
     * Location Settings Result Callback
     */
    private class LocationSettingsResultCallback implements ResultCallback<LocationSettingsResult>
    {
        @Override
        public void onResult(LocationSettingsResult locationSettingsResult)
        {
            final Status status = locationSettingsResult.getStatus();
            final LocationSettingsStates locationSettingsStates = locationSettingsResult.getLocationSettingsStates();

            switch (status.getStatusCode())
            {
                case LocationSettingsStatusCodes.SUCCESS:

                    if(locationSettingsListener != null)
                    {
                        locationSettingsListener.locationSettingStatus(true);
                    }

                    if(isGoogleApiClientAliveAndConnected())
                    {
                        // All location settings are satisfied. Initialize location requests here.
                        requestLocationUpdates(null);
                    }

                    break;

                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                    if(locationSettingsListener != null)
                    {
                        locationSettingsListener.locationSettingStatus(false);
                    }

                    // Location settings are not satisfied. But could be fixed by showing the user
                    // a dialog.
                    try
                    {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        status.startResolutionForResult(context, REQUEST_CHECK_LOCATION_SETTINGS);
                    }
                    catch (IntentSender.SendIntentException e)
                    {
                        // Ignore the error.
                    }
                    break;

                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    // Location settings are not satisfied. However, we have no way to fix the
                    // settings so we won't show the dialog.
                    break;
            }
        }
    }

    /**
     * Register for Location Update Request
     */
    public void requestLocationUpdates(LocationRequest request)
    {
        // Todo Un  comment thi line.
//        //Create Handler for Location Listener
//        locationHandler = new LocationListenerHandler();
//
//        if(request == null) //First Time, Use Default Request
//        {
//            //Request for Location Updates
//            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationHandler);
//
//            //Get Last Known Location
//            Location lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//            if(lastKnownLocation != null)
//            {
//                //Set Location
//                GeoLocation geoLocation = new GeoLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
//
//                if(googleLocationListener != null)
//                {
//                    //Callback to onConnected
//                    ((GoogleLocationListener) googleLocationListener).onGetLastKnownLocation(geoLocation);
//                }
//            }
//        }
//        else //Use this Request
//        {
//
//            //Request for Location Updates
//            //LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request, locationHandler);
//        }
    }

    /**
     * Location Listener Handler
     */
    private  class LocationListenerHandler implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            //Log.i("Google Location Handler", "Google API Client : on Location Changed.");

            if(location != null)
            {
                //Set Location
                GeoLocation geoLocation = new GeoLocation(location.getLatitude(), location.getLongitude());

                if(googleLocationListener != null)
                {
                    //CallBack to onLocationUpdate
                    ((GoogleLocationListener) googleLocationListener).onLocationUpdated(geoLocation);
                }
            }
        }
    }

    public void removeLocationUpdates()
    {
        if(googleApiClient != null && googleApiClient.isConnected() && locationHandler != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationHandler);
        }

        locationHandler = null;
    }

    /**
     *  Disconnect Google Api Client Object
     */
    public void disconnect()
    {
        if(googleApiClient != null && googleApiClient.isConnected())
        {
            removeLocationUpdates();
            googleApiClient.disconnect();
            googleApiClient = null;
            googleLocationListener = null;
        }
    }

    /**
     *
     * @return
     */
    public boolean isGoogleApiClientAliveAndConnected()
    {
        return googleApiClient != null && googleApiClient.isConnected();
    }

    public interface LocationSettingListener
    {
        public void locationSettingStatus(boolean status);
    }

    public void setLocationSettingsListener(LocationSettingListener locationSettingsListener)
    {
        this.locationSettingsListener  = locationSettingsListener;
    }

}
