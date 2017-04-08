package com.ixicode.constant.ixiplan.common.network.volley;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MyVolley
{
    private static MyVolley myVolley;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context context;

    private final int TIMEOUT = 20000; //In Millis
    private final String TAG = "vollyrequest";

    /**
     * Get Instance
     * @param context
     * @return
     */
    public static synchronized MyVolley getInstance(Context context)
    {
        if (myVolley == null)
        {
            myVolley = new MyVolley(context);
        }
        return myVolley;
    }

    private MyVolley(Context context)
    {
        this.context = context;
        this.requestQueue = getRequestQueue();
        this.imageLoader = new ImageLoader(requestQueue, new BitmapLruCache());
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader()
    {
        return imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag((TextUtils.isEmpty(tag) ? TAG : tag));
        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important to
     * specify a TAG so that the pending/ongoing requests can be cancelled.
     */
    public void cancelRequests(Object tag)
    {
        if (requestQueue != null)
        {
            requestQueue.cancelAll(tag);
        }
    }

    public void getImageFromUrl(ImageView imageView, String imgUrl, int defualtImgRes, int errorImgRes)
    {
        imageLoader.get(imgUrl, ImageLoader.getImageListener(imageView, defualtImgRes, errorImgRes));
    }

    /**
     * Custom Request Methods
     *//*
    public <K, T> void sendGSONRequest(int method, String url, K clsRequest, Class<T> clsResponse,
                                       final Map<String, String> headers, Response.Listener<T> responseListener,
                                       Response.ErrorListener errorListener, String tag)
    {
        GsonRequest<T, K> gsonRequest = new GsonRequest<>(method, url, clsRequest, clsResponse, headers, responseListener, errorListener);
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, 0, 0.0F));
        gsonRequest.setShouldCache(false);
        addToRequestQueue(gsonRequest, tag);
    }*/
/*
    *//**
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
     *//*
    public <T> void sendMultipartRequest(String url, Class<T> responseClassType,
                                         final Map<String, String> headers, Response.ErrorListener errorListener, Response.Listener<T> listener,
                                         Map<String, String> textParts, Map<String, String> fileParts)
    {
        MultiPartRequest<T> multiplePartRequest = new  MultiPartRequest<T>(url, headers, responseClassType, listener, errorListener, textParts, fileParts);
        addToRequestQueue(multiplePartRequest);
    }*/
}
