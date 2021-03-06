package com.ixicode.constant.ixiplan.triplisting.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class DataModelResponse implements Serializable{

    @SerializedName("originName")
    public String originName;

    @SerializedName("destinationName")
    public String destinationName;

    @SerializedName("routes")
    public RoutesModelResponse[] routes;

    @SerializedName("fastestRoute")
    public FastestRouteResponse fastestRoute;

    @SerializedName("cheapestRoute")
    public CheapestRouteResponse cheapestRoute;

    public class FastestRouteResponse implements Serializable{

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

        @SerializedName("timePretty")
        public String timePretty;

        @SerializedName("timePrettySuffix")
        public String timePrettySuffix;

        @SerializedName("durationPretty")
        public String durationPretty;

        @SerializedName("firstStep")
        public FirstStepFastestModel firstStep;

    }

    public class RoutesModelResponse implements Serializable{

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

    public class CheapestRouteResponse implements Serializable{

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

        @SerializedName("timePretty")
        public String timePretty;

        @SerializedName("timePrettySuffix")
        public String timePrettySuffix;

        @SerializedName("durationPretty")
        public String durationPretty;

        @SerializedName("firstStep")
        public FirstStepCheapestModel firstStep;

    }
}
