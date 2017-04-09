package com.ixicode.constant.ixiplan.placedetail;

import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;

/**
 * Created by akash on 9/4/17.
 */

public interface PlaceDetailContract
{
    interface View
    {

        void onSuccessPlaceDetail(MasterResponse masterResponse[], int taskCode);
        void onErroPlaceDetail(ErrorDisplay errorDisplay, int taskCode);

    }

    interface Presenter
    {

        void getPlaceDetail(String cityId);

    }

}
