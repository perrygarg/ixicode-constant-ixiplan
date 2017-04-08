package com.ixicode.constant.ixiplan.common.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.ixicode.constant.ixiplan.common.util.MyLogs;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GeoCodingHelper
{
    Context context;
    GeocoderListener listener;
    List<Address> addressList;

    public interface GeocoderListener
    {
        void onAddressFound(String addressName);
    }

    public GeoCodingHelper(GeocoderListener listener, Context context)
    {
        this.listener = listener;
        this.context = (Context) context;
    }

    public void getLocationName(GeoLocation geoLocation)
    {
        GetLocationAddressAsyncTask asyncTask = new GetLocationAddressAsyncTask();
        asyncTask.execute(geoLocation);
    }

    /**
     * Get Location Address AsyncTask
     */
    private class GetLocationAddressAsyncTask extends AsyncTask<GeoLocation, Void, Void>
    {
        List<Address> addresses = null;
        Geocoder geocoder;

        GetLocationAddressAsyncTask()
        {
            geocoder = new Geocoder(context, Locale.getDefault());
        }

        @Override
        protected Void doInBackground(GeoLocation... params)
        {
            GeoLocation geoLocation = params[0];

            try
            {
                addresses = geocoder.getFromLocation(geoLocation.latitude, geoLocation.longitude, 1); //Get One Result Only
            }
            catch (IOException ioException)
            {
                // Catch network or other I/O problems.
                Log.e(MyLogs.TAG, "Reverse Geocoding - Service not available");
            }
            catch (IllegalArgumentException illegalArgumentException)
            {
                Log.e(MyLogs.TAG, "Reverse Geocoding - Invalid LatLong");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            GeoCodingHelper.this.addressList = addresses;

            // Handle case where no address was found.
            if (addresses == null || addresses.size()  == 0)
            {
                if(listener != null)
                {
                    listener.onAddressFound("");
                }
            }
            else
            {
                String addressName = getAddressName(addresses);

                if(listener != null)
                {
                    listener.onAddressFound(addressName);
                }
            }

            //Free References
            listener = null;
            geocoder = null;
            context = null;
        }
    }

    private String getAddressName(List<Address> addresses)
    {
        String shorAddressName = "";

        Address address = addresses.get(0); //Get First and Only Address
        if(address != null)
        {
            StringBuilder stringBuilder = new StringBuilder();

            int maxlineIndex = address.getMaxAddressLineIndex();
            for(int i = 0; i < maxlineIndex; i++)
            {
                stringBuilder.append(address.getAddressLine(i));

                if(i != maxlineIndex - 1)
                {
                    stringBuilder.append(", ");
                }
            }

            shorAddressName = stringBuilder.toString();
//            shorAddressName = AppUtil.getShortLocationName(shorAddressName);
        }

        return shorAddressName;
    }
}
