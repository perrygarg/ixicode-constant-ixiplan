package com.ixicode.constant.ixiplan.locationsearch;

import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceRequestModel;

/**
 * Created by akash on 8/4/17.
 */

public interface LocationSearchContract
{

    interface View
    {
        void onSuccessFetchAutoPlaces(MasterResponse[] places);
        void onFailureFetchAutoPlaces(ErrorDisplay errorDisplay);
    }

    interface Presenter
    {

        void fetchAutoCompletePlaces(AutocompletePlaceRequestModel autocompletePlaceRequestModel);

    }
}
