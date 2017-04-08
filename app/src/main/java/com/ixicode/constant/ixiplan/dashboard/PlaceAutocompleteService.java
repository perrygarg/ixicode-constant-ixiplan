package com.ixicode.constant.ixiplan.dashboard;

import android.content.Context;

import com.android.volley.Request;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;
import com.ixicode.constant.ixiplan.common.network.volley.HttpClient;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceRequestModel;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

/**
 * Created by akash on 8/4/17.
 */

public class PlaceAutocompleteService extends WebService
{
    private Context context = null;

    public PlaceAutocompleteService(int taskCode, WebServiceListener serviceListener, Context context)
    {
        super(taskCode, serviceListener, context);

        this.context = context;
    }

    @Override
    public void getData(Object... args)
    {
        AutocompletePlaceRequestModel autocompletePlaceRequestModel = (AutocompletePlaceRequestModel) args[0];

        HttpClient httpClient = new HttpClient(context);
        httpClient.sendGSONRequest(Request.Method.GET, WebConstants.AUTOCOMPLETE_PLACE_URL, null, AutocompletePlaceResponseModel[].class, WebManager.getHeaders(), this, this, tag);
    }

    @Override
    public void onResponse(Object object)
    {
        if(serviceListener != null) {

            AutocompletePlaceResponseModel[] responses = (AutocompletePlaceResponseModel[]) object;
            if (!AppUtil.isArrayEmpty(responses) && responses[0] != null) {
                AutocompletePlaceResponseModel response = responses[0];
                serviceListener.onServiceSuccess(responses, taskCode);

            } else {

                serviceListener.onServiceError(context.getString(R.string.error_tech_error), taskCode, AppConstant.ERROR_TYPE_SIMPLE);
            }
        }
    }

}
