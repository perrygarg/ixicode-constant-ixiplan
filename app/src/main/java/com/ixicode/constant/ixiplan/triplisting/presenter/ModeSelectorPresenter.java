package com.ixicode.constant.ixiplan.triplisting.presenter;

import android.content.Context;

import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.model.ValidationError;
import com.ixicode.constant.ixiplan.common.network.WebConstants;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.WebService;
import com.ixicode.constant.ixiplan.common.network.WebServiceListener;
import com.ixicode.constant.ixiplan.common.util.MyLogs;
import com.ixicode.constant.ixiplan.triplisting.contract.ModeSelectorContract;
import com.ixicode.constant.ixiplan.triplisting.model.FetchModesBetweenLocsRequest;
import com.ixicode.constant.ixiplan.triplisting.model.response.FetchModesBetweenLocsModel;

import java.util.ArrayList;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class ModeSelectorPresenter implements ModeSelectorContract.Presenter, WebServiceListener {
    private ModeSelectorContract.View view;
    private Context context;

    public ModeSelectorPresenter(ModeSelectorContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void fetchModes(String srcXid, String desXid) {
        FetchModesBetweenLocsRequest request = new FetchModesBetweenLocsRequest();
        request.originCityId = srcXid;
        request.destinationCityId = desXid;

        WebService webService = WebManager.getService(WebConstants.WS_CODE_FETCH_MODES_BW_PLACES, this, context);
        webService.getData(request);
    }

    @Override
    public void onServiceBegin(int taskCode) {
        view.showProgress();
    }

    @Override
    public void onServiceSuccess(MasterResponse[] masterResponse, int taskCode) {
        view.hideProgress();

        FetchModesBetweenLocsModel model = (FetchModesBetweenLocsModel) masterResponse[0];
        ArrayList<String> modes = new ArrayList<>();
        int size = model.data.routes.length;
        for(int i = 0; i < size; i++) {
            modes.add(model.data.routes[i].allStepModes);
        }

        view.onSuccessFetchingModes(modes);
    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {
        MyLogs.e("Perry " + message);
    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

    }
}
