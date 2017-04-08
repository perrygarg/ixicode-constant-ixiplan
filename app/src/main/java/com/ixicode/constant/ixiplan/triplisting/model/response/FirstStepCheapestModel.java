package com.ixicode.constant.ixiplan.triplisting.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PerryGarg on 09-04-2017.
 */

public class FirstStepCheapestModel implements Serializable{

    @SerializedName("carriers")
    public CarriersCheapestModel[] carriers;

    @SerializedName("mode")
    public String mode;


    public class CarriersCheapestModel implements Serializable{

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

        @SerializedName("trainType")
        public String trainType;

        @SerializedName("destinationCode")
        public String destinationCode;

        @SerializedName("originCode")
        public String originCode;

        @SerializedName("daysOfOperation")
        public String daysOfOperation;

        @SerializedName("commaSeparatedDOO")
        public String commaSeparatedDOO;
    }
}
