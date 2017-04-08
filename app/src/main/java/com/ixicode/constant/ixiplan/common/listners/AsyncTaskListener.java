package com.ixicode.constant.ixiplan.common.listners;

public interface AsyncTaskListener
{
    void onBeginAsyncTask();
    void onSuccess();
    void onError(String errorMessage);
}
