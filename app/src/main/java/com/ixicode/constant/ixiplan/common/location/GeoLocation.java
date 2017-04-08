package com.ixicode.constant.ixiplan.common.location;

import android.os.Parcel;
import android.os.Parcelable;

public class GeoLocation implements Parcelable
{
    public double latitude;
    public double longitude;

    public GeoLocation()
    {
    }

    public GeoLocation(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoLocation(Parcel parcel)
    {
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public final static Creator<GeoLocation> CREATOR = new Creator<GeoLocation>()
    {
        @Override
        public GeoLocation createFromParcel(Parcel source)
        {
            return new GeoLocation(source);
        }

        @Override
        public GeoLocation[] newArray(int size) {
            return new GeoLocation[size];
        }
    };
}
