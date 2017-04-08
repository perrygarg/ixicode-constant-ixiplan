package com.ixicode.constant.ixiplan.common.network;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.network.volley.HttpClient;
import com.ixicode.constant.ixiplan.dashboard.PlaceAutocompleteService;

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
