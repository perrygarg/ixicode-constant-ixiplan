package com.ixicode.constant.ixiplan.triplisting.service;

import android.content.Context;

import com.android.volley.Request;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;
import com.ixicode.constant.ixiplan.common.network.volley.HttpClient;
import com.ixicode.constant.ixiplan.triplisting.model.FetchModesBetweenLocsRequest;
import com.ixicode.constant.ixiplan.triplisting.model.response.FetchModesBetweenLocsModel;

import java.util.Locale;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class FetchModesWebService extends WebService{
    private Context context = null;

    public FetchModesWebService(int taskCode, WebServiceListener serviceListener, Context context) {
        super(taskCode, serviceListener, context);
        this.context = context;    }

    @Override
    public void getData(Object... args)
    {
        FetchModesBetweenLocsRequest requestModel = (FetchModesBetweenLocsRequest) args[0];

        String url = String.format(Locale.US, WebConstants.FETCH_MODES_BETWEEN_PLACES_URL, requestModel.originCityId, requestModel.destinationCityId);

        HttpClient httpClient = new HttpClient(context);
        httpClient.sendGSONRequest(Request.Method.GET, url, null, FetchModesBetweenLocsModel.class, WebManager.getHeaders(), this, this, tag);
    }

    @Override
    public void onResponse(Object object)
    {
        if(serviceListener != null) {

            FetchModesBetweenLocsModel response = (FetchModesBetweenLocsModel) object;
            if (response != null) {
                FetchModesBetweenLocsModel[] responses = new FetchModesBetweenLocsModel[] {response};
                serviceListener.onServiceSuccess(responses, taskCode);
            } else {
                serviceListener.onServiceError(context.getString(R.string.error_tech_error), taskCode, AppConstant.ERROR_TYPE_SIMPLE);
            }
        }
    }
}
