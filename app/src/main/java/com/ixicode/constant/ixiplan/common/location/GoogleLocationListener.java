package com.ixicode.constant.ixiplan.common.location;

import com.google.android.gms.common.ConnectionResult;

public interface GoogleLocationListener
{
    public abstract void onGetLastKnownLocation(GeoLocation geoLocation);
    public abstract void onLocationUpdated(GeoLocation geoLocation);
    public abstract void onConnectionFailed(ConnectionResult connectionResult);
}
