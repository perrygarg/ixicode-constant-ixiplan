package com.ixicode.constant.ixiplan.triplisting.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class DataModelResponse {

    @SerializedName("originName")
    public String originName;

    @SerializedName("destinationName")
    public String destinationName;

    @SerializedName("routes")
    public RoutesModelResponse[] routes;

    public class RoutesModelResponse {

        @SerializedName("price")
        public Integer price;

        @SerializedName("time")
        public Integer time;

        @SerializedName("fastestDuration")
        public Integer fastestDuration;

        @SerializedName("modeViaString")
        public String modeViaString;

        @SerializedName("timeUnit")
        public String timeUnit;

        @SerializedName("firstModeTypesCss")
        public String firstModeTypesCss;

        @SerializedName("durationPretty")
        public String durationPretty;

        @SerializedName("durationHours")
        public String durationHours;

        @SerializedName("durationMinutes")
        public String durationMinutes;

        @SerializedName("allStepModes")
        public String allStepModes;

        @SerializedName("firstStep")
        public FirstStepResponseModel firstStep;
    }

}
