package com.ixicode.constant.ixiplan.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;

/**
 * Created by akash on 9/4/17.
 */

public class TrendingLocationResponse extends MasterResponse
{

    @SerializedName("data")
    public Data data = null;

    public class Data
    {
        @SerializedName("flight")
        public Flight flight[] = null;

        @SerializedName("budget_flight")
        public Flight budgetFlight[] = null;
    }

    public class Flight
    {
        @SerializedName("image")
        public String image = null;

        @SerializedName("name")
        public String name = null;

        @SerializedName("countryName")
        public String coutryName = null;

        @SerializedName("url")
        public String url = null;

        @SerializedName("type")
        public String type = null;

        @SerializedName("cityName")
        public String cityName = null;

        @SerializedName("stateName")
        public String stateName = null;

        @SerializedName("price")
        public String price = null;

        @SerializedName("currency")
        public String currency = null;

        @SerializedName("cityId")
        public String cityId = null;
    }
}
