package com.ixicode.constant.ixiplan.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.fragment.BaseFragment;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.dashboard.adapter.AutocompletePlaceAdapter;
import com.ixicode.constant.ixiplan.dashboard.contract.InputFormContract;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

import java.util.ArrayList;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class InputFormFragment extends BaseFragment implements InputFormContract.View{

    AutocompletePlaceAdapter fromAdapter = null;
    AutocompletePlaceAdapter toAdapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_form_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AutoCompleteTextView fromInput = (AutoCompleteTextView) view.findViewById(R.id.from_input);
        AutoCompleteTextView toInput = (AutoCompleteTextView) view.findViewById(R.id.to_input);

        fromAdapter = new AutocompletePlaceAdapter(getContext().getApplicationContext(), null);
        toAdapter = new AutocompletePlaceAdapter(getContext().getApplicationContext(), null);
    }

    @Override
    public void onSuccessFetchAutoPlaces(ArrayList<AutocompletePlaceResponseModel> places) {
        toAdapter.setPlaceArraylist(places);
    }

    @Override
    public void onFailureFetchAutoPlaces(ErrorDisplay errorDisplay) {

    }

    @Override
    public void onSubmitSuccess() {

    }

    @Override
    public void onSubmitFailure(ErrorDisplay errorDisplay) {

    }
}
