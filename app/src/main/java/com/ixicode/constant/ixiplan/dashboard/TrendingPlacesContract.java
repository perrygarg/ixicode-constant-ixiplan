package com.ixicode.constant.ixiplan.dashboard;

import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.dashboard.model.TrendingLocationResponse;

/**
 * Created by akash on 9/4/17.
 */

public interface TrendingPlacesContract {

    interface View
    {
        void onSuccessTrendingPlaces(MasterResponse[] trendingLocationResponse);
        void onFailTrendingPlaces(ErrorDisplay error);
    }

    interface Presenter
    {
        void getTrendingPlaces();

    }

}
