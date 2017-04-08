package com.ixicode.constant.ixiplan.common.network;


import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;

public interface WebServiceListener
{
    void onServiceBegin(int taskCode);
    void onServiceSuccess(MasterResponse[] masterResponse, int taskCode);
    void onServiceError(String message, int taskCode, int errorType);
    void onValidationError(ValidationError[] validationError, int taskCode);
}
