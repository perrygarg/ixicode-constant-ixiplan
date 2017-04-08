package com.ixicode.constant.ixiplan.dashboard.contract;

import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

import java.util.ArrayList;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public interface InputFormContract {

    interface View {

        void onSuccessFetchAutoPlaces(ArrayList<AutocompletePlaceResponseModel> places);
        void onFailureFetchAutoPlaces(ErrorDisplay errorDisplay);

        void onSubmitSuccess();
        void onSubmitFailure(ErrorDisplay errorDisplay);

    }

    interface Presenter {

        void fetchAutoCompletePlaces(String placePartialName);
        void submit(String source, String destination);

    }

}
