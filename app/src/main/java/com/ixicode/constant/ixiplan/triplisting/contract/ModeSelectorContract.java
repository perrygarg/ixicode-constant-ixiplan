package com.ixicode.constant.ixiplan.triplisting.contract;

import com.ixicode.constant.ixiplan.triplisting.model.response.DataModelResponse;

import java.util.ArrayList;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public interface ModeSelectorContract {

    interface View {

        void showProgress();
        void hideProgress();

        void onSuccessFetchingModes(ArrayList<String> avlblModes);

        void routeDetailsForRequestedMode(DataModelResponse.RoutesModelResponse route);

        void routeDetailsForRequestedMode(DataModelResponse.FastestRouteResponse routeResponse);

        void routeDetailsForRequestedMode(DataModelResponse.CheapestRouteResponse routeResponse);

    }

    interface Presenter {

        void fetchModes(String srcXid, String desXid);

        void onClickOnMode(String modeId);

    }

}
