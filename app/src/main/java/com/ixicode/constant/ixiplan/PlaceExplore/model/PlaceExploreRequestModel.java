package com.ixicode.constant.ixiplan.PlaceExplore.model;

import com.ixicode.constant.ixiplan.common.model.MasterRequest;

/**
 * Created by akash on 9/4/17.
 */

public class PlaceExploreRequestModel extends MasterRequest
{
    public String type = null;

    public String cityId = null;

    public int skip = 0;

    public int limit = 10;
}
