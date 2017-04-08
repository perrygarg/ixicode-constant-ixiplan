package com.ixicode.constant.ixiplan.common.network.volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;

import java.util.Map;

public class HttpClient
{
    private Context context;
    private MyVolley myVolley;

    private final int TIMEOUT = 20000; //In Millis

    public HttpClient(Context context)
    {
        this.context = context;
        this.myVolley = MyVolley.getInstance(this.context);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important to
     * specify a TAG so that the pending/ongoing requests can be cancelled.
     */
    public void cancelRequests(Object tag)
    {
        myVolley.cancelRequests(tag);
    }

    /**
     * Custom Request Methods
     */
    public <K, T> void sendGSONRequest(int method, String url, K clsRequest, Class<T> clsResponse,
                                       final Map<String, String> headers, Response.Listener<T> responseListener,
                                       Response.ErrorListener errorListener, String tag)
    {
        GsonRequest<T, K> gsonRequest = new GsonRequest<>(method, url, clsRequest, clsResponse, headers, responseListener, errorListener);
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, 0, 0.0F));
        gsonRequest.setShouldCache(false); //TODO cache


        //Add To Volly Request Queue
        myVolley.addToRequestQueue(gsonRequest, tag);
    }


    /**
     * Send Multipart Request
     *
     * @param url
     * @param responseClassType
     * @param headers
     * @param errorListener
     * @param listener
     * @param textParts
     * @param fileParts
     * @param <T>
     */
    public <T> void sendMultipartRequest(String url, Class<T> responseClassType,
                                         final Map<String, String> headers, Response.ErrorListener errorListener, Response.Listener<T> listener,
                                         Map<String, String> textParts, Map<String, String> fileParts)
    {
        MultiPartRequest<T> multiplePartRequest = new  MultiPartRequest<T>(url, headers, responseClassType, listener, errorListener, textParts, fileParts);

        //Add To Volly
        myVolley.addToRequestQueue(multiplePartRequest);
    }
}
