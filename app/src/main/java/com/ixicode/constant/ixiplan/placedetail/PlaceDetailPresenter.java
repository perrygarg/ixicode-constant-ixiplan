package com.ixicode.constant.ixiplan.placedetail;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceRequestModel;

/**
 * Created by akash on 9/4/17.
 */

public class PlaceDetailPresenter implements PlaceDetailContract.Presenter, WebServiceListener {

    private PlaceDetailContract.View view = null;
    private Context context = null;

    public PlaceDetailPresenter(PlaceDetailContract.View view, Context context)
    {
        this.view = view;
        this.context = context;
    }


    @Override
    public void onServiceBegin(int taskCode) {

    }

    @Override
    public void onServiceSuccess(MasterResponse[] masterResponse, int taskCode) {

        if(view != null)
        {
            view.onSuccessPlaceDetail(masterResponse, taskCode);
        }

    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {

    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

    }

    @Override
    public void getPlaceDetail(String cityId) {

        WebService webService = WebManager.getService(WebConstants.WS_CODE_FETCH_MODES_PLACE_DETAIL, this, context);

        GetPlaceRequestModel getPlaceRequestModel = new GetPlaceRequestModel();
        getPlaceRequestModel.cityId = cityId;
        webService.getData(getPlaceRequestModel);
    }
}
