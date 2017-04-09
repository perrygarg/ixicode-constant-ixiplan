package com.ixicode.constant.ixiplan.PlaceExplore;

import android.content.Context;

import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreRequestModel;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;

/**
 * Created by akash on 9/4/17.
 */

public class PlaceExplorePresenter implements WebServiceListener, PlaceExploreContract.Presenter {

    private Context context = null;
    private PlaceExploreContract.View view = null;

    public PlaceExplorePresenter(PlaceExploreContract.View view, Context context)
    {
        this.context = context;
        this.view = view;
    }


    @Override
    public void getPlaceList(PlaceExploreRequestModel placeExploreRequestModel) {

        WebService webService = WebManager.getService(WebConstants.WS_CODE_FETCH_MODES_PLACE_EXPLORE, this, context);
        webService.getData(placeExploreRequestModel);

    }

    @Override
    public void onServiceBegin(int taskCode) {

    }

    @Override
    public void onServiceSuccess(MasterResponse[] masterResponse, int taskCode) {

        if(view != null)
        {
            view.onSuccessPlaceExplore(masterResponse, taskCode);
        }

    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {

        if(view != null)
        {
            view.onErrorPlaceExplore(null, taskCode);
        }

    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

        if(view != null)
        {
            view.onErrorPlaceExplore(null, taskCode);
        }

    }
}
