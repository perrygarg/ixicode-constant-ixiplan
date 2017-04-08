package com.ixicode.constant.ixiplan.common.network;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.network.volley.HttpClient;
import com.ixicode.constant.ixiplan.dashboard.GetTrendingLocationService;
import com.ixicode.constant.ixiplan.dashboard.PlaceAutocompleteService;
import com.ixicode.constant.ixiplan.triplisting.service.FetchModesWebService;
import java.util.HashMap;
import java.util.Map;

public class WebManager
{
    /**
     * Get Service Instance for a Tasks
     * @param taskCode - The Task code
     * @param serviceListener - Listener to send Success or Failure Callbacks
     * @return - Service Instance
     */
    public static WebService getService(int taskCode, WebServiceListener serviceListener, Context context)
    {
        switch (taskCode)
        {
            case WebConstants.WS_CODE_AUTOCOMPLETE_PLACE:
                return new PlaceAutocompleteService(taskCode, serviceListener, context);
            case WebConstants.WS_CODE_FETCH_MODES_BW_PLACES:
                return new FetchModesWebService(taskCode, serviceListener, context);
            case WebConstants.WS_CODE_FETCH_MODES_TRENDING_PLACES:
                return new GetTrendingLocationService(taskCode, serviceListener, context);

        }

        return null;
    }

    /**
     * Get headers
     * @return
     */
    public static Map<String , String> getHeaders()
    {
        Map<String, String> headers = new HashMap<>();

        return headers;
    }

    public static void cancelService(int taskCode, Context context)
    {
        HttpClient httpClient = new HttpClient(context);
        httpClient.cancelRequests(String.valueOf(taskCode));
    }
}
