package com.ixicode.constant.ixiplan.PlaceExplore.model;

import com.google.gson.annotations.SerializedName;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

/**
 * Created by akash on 9/4/17.
 */

public class PlaceExploreResponseModel extends MasterResponse
{
    @SerializedName("data")
    public Data data = null;

    public class Data
    {
        @SerializedName("Places To Visit")
        public PlaceData visit[] = null;

        @SerializedName("Things To Do")
        public PlaceData things[] = null;
    }

    public class PlaceData {
        @SerializedName("description")
        public String description = null;

        @SerializedName("keyImageUrl")
        public String imageUrl = null;

        @SerializedName("latitude")
        public Float latitude = null;

        @SerializedName("longitude")
        public Float longitude = null;

        @SerializedName("id")
        public String id = null;

        @SerializedName("whyToVisit")
        public String whyVisit = null;

        @SerializedName("name")
        public String name = null;

        @SerializedName("stateName")
        public String stateName = null;

        @SerializedName("countryName")
        public String countryName = null;

        @SerializedName("address")
        public String address = null;
    }
}
