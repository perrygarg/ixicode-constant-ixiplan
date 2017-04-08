package com.ixicode.constant.ixiplan.common.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;

public class BaseFragment extends android.support.v4.app.Fragment
{
    @TargetApi(23)
    @Override public void onAttach(Context context)
    {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23)
            onAttachToContext(activity);
    }

    /*
     * This method will be called from one of the two previous method
     */
    protected void onAttachToContext(Context context) {}
}
