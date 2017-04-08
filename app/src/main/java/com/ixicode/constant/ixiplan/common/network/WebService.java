package com.ixicode.constant.ixiplan.common.network;

import android.app.Application;
import android.content.Context;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.application.IxiPlanApp;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;
import com.ixicode.constant.ixiplan.common.util.MyLogs;


public abstract class WebService implements Response.Listener, Response.ErrorListener
{
    protected String tag;
    protected int taskCode;
    protected WebServiceListener serviceListener = null;
    private Context context = null;

    public WebService(int taskCode, WebServiceListener serviceListener, Context context)
    {
        this.taskCode = taskCode;
        this.serviceListener = serviceListener;
        this.tag = String.valueOf(taskCode);
        this.context = context;

        if(serviceListener != null)
            serviceListener.onServiceBegin(taskCode);
    }

    abstract public void getData(Object... args);

    @Override
    public void onResponse(Object object)
    {
        if(serviceListener != null)
        {
            serviceListener.onServiceSuccess((MasterResponse[]) object, taskCode);
        }
    }

    @Override
    public void onErrorResponse(VolleyError volleyError)
    {
        MyLogs.d(MyLogs.TAG, volleyError.getMessage());

        if(serviceListener != null)
        {
            if(volleyError.networkResponse != null && volleyError.networkResponse.statusCode == 400)
            {
                ValidationError[] validationError = null;

                try
                {
                    String str = new String(volleyError.networkResponse.data, "utf-8");
                    validationError = getValidationError(str);
                }
                catch(Exception exception)
                {}

                if(serviceListener != null)
                    serviceListener.onValidationError(validationError, taskCode);
            }
            else if(volleyError instanceof TimeoutError)
            {
                serviceListener.onServiceError(context.getString(R.string.error_time_out_error), taskCode, AppConstant.ERROR_TYPE_SIMPLE);
            }
            else if(volleyError instanceof NetworkError)
            {
                serviceListener.onServiceError(context.getString(R.string.error_netwrok_error), taskCode, AppConstant.ERROR_TYPE_NO_NETWORK);
            }
            else
            {
                serviceListener.onServiceError(context.getString(R.string.error_tech_error), taskCode, AppConstant.ERROR_TYPE_SIMPLE);
            }
        }
    }

    private ValidationError[] getValidationError(String error)
    {
        ValidationError[] validationError = null;
        Gson gson = new Gson();

        if(error != null)
        {
            validationError = gson.fromJson(error, ValidationError[].class);
        }
        return validationError;
    }
}

