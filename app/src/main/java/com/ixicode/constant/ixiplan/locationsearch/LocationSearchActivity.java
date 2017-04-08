package com.ixicode.constant.ixiplan.locationsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapterListener;
import com.ixicode.constant.ixiplan.common.listners.OnItemClickListener;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceRequestModel;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

import java.util.ArrayList;
import java.util.Arrays;

public class LocationSearchActivity extends AppCompatActivity implements LocationSearchContract.View {

    private RecyclerView recyclerView = null;
    private EditText editTextSearch = null;
    private LocationSearchContract.Presenter presenter = null;
    private AutocompletePlaceRequestModel request = null;
    private LocationSearchAdapter locationSearchAdapter = null;
    private ArrayList<AutocompletePlaceResponseModel> arrayListResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayListResponse = new ArrayList<>();
        locationSearchAdapter = new LocationSearchAdapter(null, false, getApplicationContext(),
                arrayListResponse, new HandleItemClickListener());
        recyclerView.setAdapter(locationSearchAdapter);
        editTextSearch= (EditText) findViewById(R.id.editTextSearch);

        presenter = new LocationSearchPrensenter(this, getApplicationContext());
        request = new AutocompletePlaceRequestModel();

        editTextSearch.addTextChangedListener(new  HandleTextChange());
    }

    @Override
    public void onSuccessFetchAutoPlaces(MasterResponse[] places) {

        AutocompletePlaceResponseModel autocompletePlaceResponseModel[] = (AutocompletePlaceResponseModel[]) places;

        locationSearchAdapter.setArrayList(Arrays.asList(autocompletePlaceResponseModel));
    }

    @Override
    public void onFailureFetchAutoPlaces(ErrorDisplay errorDisplay) {

    }

    private class HandleTextChange implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String str = s.toString();

            if(AppUtil.isStringEmpty(str))
            {
                locationSearchAdapter.setArrayList(null);
            }
            else {
                request.placeStr = str;
                presenter.fetchAutoCompletePlaces(request);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class HandleItemClickListener implements OnItemClickListener
    {
        @Override
        public void onItemClick(int position) {

            AutocompletePlaceResponseModel response = arrayListResponse.get(position);

            Intent intent = new Intent();
            intent.putExtra(LocationConstant.CITY_LOCATION, response);
            setResult(RESULT_OK, intent);
            finish();

        }
    }




}
