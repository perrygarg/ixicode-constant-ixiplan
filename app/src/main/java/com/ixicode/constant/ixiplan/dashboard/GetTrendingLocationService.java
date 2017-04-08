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
import com.ixicode.constant.ixiplan.dashboard.model.TrendingLocationResponse;

import java.util.Locale;

/**
 * Created by akash on 9/4/17.
 */

public class GetTrendingLocationService extends WebService
{
    private Context context = null;

    public GetTrendingLocationService(int taskCode, WebServiceListener serviceListener, Context context)
    {
        super(taskCode, serviceListener, context);

        this.context = context;
    }

    @Override
    public void getData(Object... args)
    {

        HttpClient httpClient = new HttpClient(context);
        httpClient.sendGSONRequest(Request.Method.GET, WebConstants.TRENDING_PLACES_API, null, TrendingLocationResponse.class, WebManager.getHeaders(), this, this, tag);
    }

    @Override
    public void onResponse(Object object)
    {
        if(serviceListener != null) {

            TrendingLocationResponse[] responses = new TrendingLocationResponse[1];
            responses[0] = (TrendingLocationResponse) object;
            if (!AppUtil.isArrayEmpty(responses) && responses[0] != null) {

                serviceListener.onServiceSuccess(responses, taskCode);

            } else {

                serviceListener.onServiceError(context.getString(R.string.error_tech_error), taskCode, AppConstant.ERROR_TYPE_SIMPLE);
            }
        }
    }

}
