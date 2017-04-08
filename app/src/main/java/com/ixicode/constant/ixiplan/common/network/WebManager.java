package com.ixicode.constant.ixiplan.common.network;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.network.volley.HttpClient;
import com.ixicode.constant.ixiplan.common.persistant.PersistenceManager;
import com.ixicode.constant.ixiplan.common.util.AppUtil;


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
    public static WebService getService(int taskCode, WebServiceListener serviceListener)
    {
        switch (taskCode)
        {


        }

        return null;
    }

//    /**
//     * Get headers
//     * @return
//     */
//    public static Map<String , String> getHeaders()
//    {
//        Map<String, String> headers = new HashMap<>();
//
//        headers.put(WebConstants.HEADER_API_ID_KEY, WebConstants.HEADER_API_ID_VALUE);
//        headers.put(WebConstants.HEADER_API_VERSION_KEY, WebConstants.HEADER_API_VERSION_VALUE);
//        headers.put(WebConstants.HEADER_DEVICE_KEY, WebConstants.HEADER_DEVICE_VALUE);
//        headers.put(WebConstants.HEADER_DEVICE_ID_KEY, String.valueOf(AppUtil.getAppVersionCode()));
//
//        String token = PersistenceManager.getToken();
//        if(token != null)
//        {
//            headers.put(WebConstants.HEADER_API_TOKEN_KEY, token);
//        }
//        return headers;
//    }

    public static void cancelService(int taskCode, Context context)
    {
        HttpClient httpClient = new HttpClient(context);
        httpClient.cancelRequests(String.valueOf(taskCode));
    }
}
