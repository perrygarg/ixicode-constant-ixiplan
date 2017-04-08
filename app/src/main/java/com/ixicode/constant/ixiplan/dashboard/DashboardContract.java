package com.ixicode.constant.ixiplan.dashboard;

import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;

import java.util.ArrayList;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public interface DashboardContract {

    interface View {

        void showProgress();
        void hideProgress();

        void onSuccessFetchCurrentLocation(String location);
        void onFailureFetchCurrentLocation(ErrorDisplay errorDisplay);

        void onSuccessFetchAutoPlaces(ArrayList<String> places);
        void onFailureFetchAutoPlaces(ErrorDisplay errorDisplay);

        void onSubmitSuccess();
        void onSubmitFailure(ErrorDisplay errorDisplay);

    }

    interface Presenter {

        void fetchCurrentLocation();
        void fetchAutoCompletePlaces(String placePartialName);
        void submit(String source, String destination);

    }

}
