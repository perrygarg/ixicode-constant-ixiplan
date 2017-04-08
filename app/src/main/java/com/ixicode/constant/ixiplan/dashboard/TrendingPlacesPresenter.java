package com.ixicode.constant.ixiplan.dashboard;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;

/**
 * Created by akash on 9/4/17.
 */

public class TrendingPlacesPresenter implements TrendingPlacesContract.Presenter, WebServiceListener
{

    private Context context = null;
    private TrendingPlacesContract.View view = null;

    public TrendingPlacesPresenter(TrendingPlacesContract.View view, Context context)
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
            view.onSuccessTrendingPlaces(masterResponse);
        }

    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {

    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

    }

    @Override
    public void getTrendingPlaces()
    {
        WebService webService = WebManager.getService(WebConstants.WS_CODE_FETCH_MODES_TRENDING_PLACES, this, context);
        webService.getData();

    }
}
