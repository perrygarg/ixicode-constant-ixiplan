package com.ixicode.constant.ixiplan.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;

/**
 * Created by akash on 8/4/17.
 */

public class AutocompletePlaceResponseModel extends MasterResponse
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
}
