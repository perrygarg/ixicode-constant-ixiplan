package com.ixicode.constant.ixiplan.triplisting.contract;

import java.util.ArrayList;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public interface ModeSelectorContract {

    interface View {

        void showProgress();
        void hideProgress();

        void onSuccessFetchingModes(ArrayList<String> avlblModes);

    }

    interface Presenter {

        void fetchModes(String srcXid, String desXid);

    }

}
