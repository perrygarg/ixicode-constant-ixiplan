package com.ixicode.constant.ixiplan.triplisting.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class FetchModesBetweenLocsRequest {

    @SerializedName("originCityId")
    public String originCityId;

    @SerializedName("destinationCityId")
    public String destinationCityId;

}
