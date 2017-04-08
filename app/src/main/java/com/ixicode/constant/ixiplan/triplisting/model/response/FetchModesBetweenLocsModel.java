package com.ixicode.constant.ixiplan.triplisting.model.response;

import com.google.gson.annotations.SerializedName;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class FetchModesBetweenLocsModel extends MasterResponse{

    @SerializedName("data")
    public DataModelResponse data;

}
