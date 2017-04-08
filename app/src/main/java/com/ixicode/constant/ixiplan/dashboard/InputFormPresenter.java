package com.ixicode.constant.ixiplan.dashboard;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;
import com.ixicode.constant.ixiplan.dashboard.contract.InputFormContract;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceRequestModel;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;
import com.ixicode.constant.ixiplan.dashboard.util.DashboardConstant;
import com.ixicode.constant.ixiplan.dashboard.util.DashboardUtil;

/**
 * Created by akash on 8/4/17.
 */

public class InputFormPresenter implements WebServiceListener, InputFormContract.Presenter
{
    private Context context = null;
    private InputFormContract.View view = null;

    public InputFormPresenter(Context context, InputFormContract.View view)
    {
        this.context = context;
        this.view = view;
    }

    private void getAutocompletePlaceName(AutocompletePlaceRequestModel autocompletePlaceRequestModel, int mode) {

        if(autocompletePlaceRequestModel != null)
        {
            WebService webService = WebManager.getService(WebConstants.WS_CODE_AUTOCOMPLETE_PLACE, this, context);
            webService.getData(autocompletePlaceRequestModel);
        }
    }

    @Override
    public void onServiceBegin(int taskCode) {

        // TODO calback to view.
    }

    @Override
    public void onServiceSuccess(MasterResponse[] masterResponse, int taskCode) {

        if(view != null) {

        }

    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {
        // TODO calback to view.
    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {
        // TODO calbac  k to view.
    }

    @Override
    public void fetchAutoCompletePlaces(AutocompletePlaceRequestModel autocompletePlaceRequestModel, int mode) {
        getAutocompletePlaceName(autocompletePlaceRequestModel, mode);
    }

    @Override
    public void submit(String source, String destination) {

    }


}
