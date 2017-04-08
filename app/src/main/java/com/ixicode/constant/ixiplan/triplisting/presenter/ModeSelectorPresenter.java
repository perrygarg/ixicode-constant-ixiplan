package com.ixicode.constant.ixiplan.triplisting.presenter;

import com.ixicode.constant.ixiplan.triplisting.contract.ModeSelectorContract;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class ModeSelectorPresenter implements ModeSelectorContract.Presenter {
    ModeSelectorContract.View view;

    public ModeSelectorPresenter(ModeSelectorContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchModes(String srcXid, String desXid) {

    }
}
