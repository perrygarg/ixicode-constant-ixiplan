package com.ixicode.constant.ixiplan.dashboard.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;

/**
 * Created by akash on 8/4/17.
 */

public class AutocompletePlaceResponseModel extends MasterResponse implements Parcelable
{
    @SerializedName("text")
    public String cityName = null;

    @SerializedName("url")
    public String url = null;

    @SerializedName("_id")
    public String cityId = null;

    @SerializedName("st")
    public String state = null;

    @SerializedName("address")
    public String address = null;

    @SerializedName("co")
    public String country = null;

    @SerializedName("xid")
    public String xId = null;

    @SerializedName("lat")
    public float lat = 0.0f;

    @SerializedName("lon")
    public float lon = 0.0f;

    public AutocompletePlaceResponseModel()
    {

    }

    public AutocompletePlaceResponseModel(Parcel parcel)
    {
        cityName = parcel.readString();
        url = parcel.readString();
        cityId = parcel.readString();
        state = parcel.readString();
        address = parcel.readString();
        country = parcel.readString();
        xId = parcel.readString();
        lat = parcel.readFloat();
        lon = parcel.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(cityName);
        dest.writeString(url);
        dest.writeString(cityId);
        dest.writeString(state);
        dest.writeString(address);
        dest.writeString(country);
        dest.writeString(xId);
        dest.writeFloat(lat);
        dest.writeFloat(lon);

    }



    public static final Parcelable.Creator<AutocompletePlaceResponseModel> CREATOR
            = new Parcelable.Creator<AutocompletePlaceResponseModel>()
    {
        public AutocompletePlaceResponseModel createFromParcel(Parcel in)
        {
            return new AutocompletePlaceResponseModel(in);
        }

        public AutocompletePlaceResponseModel[] newArray(int size)
        {
            return new AutocompletePlaceResponseModel[size];
        }
    };
}
