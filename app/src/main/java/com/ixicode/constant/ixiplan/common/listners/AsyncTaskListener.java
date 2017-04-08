package com.ixicode.constant.ixiplan.common.listners;

/**
 * Created by jaswinder.singh on 12/13/2016.
 */

public interface AsyncTaskListener
{
    void onBeginAsyncTask();
    void onSuccess();
    void onError(String errorMessage);
}
