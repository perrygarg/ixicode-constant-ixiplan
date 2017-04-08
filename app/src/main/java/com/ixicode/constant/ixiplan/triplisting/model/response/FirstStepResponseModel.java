package com.ixicode.constant.ixiplan.triplisting.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class FirstStepResponseModel {

    @SerializedName("origin")
    public String origin;

    @SerializedName("originCode")
    public String originCode;

    @SerializedName("destination")
    public String destination;

    @SerializedName("destinationCode")
    public String destinationCode;

    @SerializedName("originXid")
    public Integer originXid;

    @SerializedName("destinationXid")
    public Integer destinationXid;

    @SerializedName("originMongoId")
    public String originMongoId;

    @SerializedName("destinationMongoId")
    public String destinationMongoId;

    @SerializedName("originId")
    public Integer originId;

    @SerializedName("destinationId")
    public Integer destinationId;

    @SerializedName("fastestCarrier")
    public String fastestCarrier;

    @SerializedName("minPrice")
    public Integer minPrice;

    @SerializedName("minTime")
    public Integer minTime;

    @SerializedName("timeUnits")
    public String timeUnits;

    @SerializedName("originLatitude")
    public Float originLatitude;

    @SerializedName("originLongitude")
    public Float originLongitude;

    @SerializedName("destinationLatitude")
    public Float destinationLatitude;

    @SerializedName("destinationLongitude")
    public Float destinationLongitude;

    @SerializedName("distance")
    public Integer distance;

    @SerializedName("fastestCarrierDuration")
    public Integer fastestCarrierDuration;

    @SerializedName("modePretty")
    public String modePretty;

    @SerializedName("carrierName")
    public String carrierName;

    @SerializedName("typeInfo")
    public String typeInfo;

    @SerializedName("carriers")
    public CarriersResponseModel[] carriers;

}
