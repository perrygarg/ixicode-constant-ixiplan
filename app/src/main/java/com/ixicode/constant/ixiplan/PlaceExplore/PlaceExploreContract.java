package com.ixicode.constant.ixiplan.PlaceExplore;

import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreRequestModel;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

/**
 * Created by akash on 9/4/17.
 */

public interface PlaceExploreContract
{
    interface View
    {
        void onSuccessPlaceExplore(MasterResponse[] getPlaceDetailResponseModel, int taskCode);
        void onErrorPlaceExplore(ErrorDisplay errorDisplay, int taskCode);
    }

    interface Presenter
    {

        void getPlaceList(PlaceExploreRequestModel placeExploreRequestModel);

    }
}
