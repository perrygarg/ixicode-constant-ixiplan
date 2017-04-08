package com.ixicode.constant.ixiplan.triplisting.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class CarriersResponseModel {

    @SerializedName("code")
    public String code;

    @SerializedName("time")
    public Integer time;

    @SerializedName("carrierName")
    public String carrierName;

    @SerializedName("depTime")
    public String depTime;

    @SerializedName("arrTime")
    public String arrTime;

    @SerializedName("destinationCode")
    public String destinationCode;

    @SerializedName("originCode")
    public String originCode;

    @SerializedName("daysOfOperation")
    public String daysOfOperation;

    @SerializedName("commaSeparatedDOO")
    public String commaSeparatedDOO;

    @SerializedName("busAmenitiesList")
    public String[] busAmenitiesList;

}
