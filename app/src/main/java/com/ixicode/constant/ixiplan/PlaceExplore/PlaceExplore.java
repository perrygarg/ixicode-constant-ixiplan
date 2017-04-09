package com.ixicode.constant.ixiplan.PlaceExplore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreRequestModel;
import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreResponseModel;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapterListener;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

public class PlaceExplore extends AppCompatActivity implements BaseRecyclerAdapterListener, PlaceExploreContract.View {

    private PlaceExploreContract.Presenter presenter = null;
    private PlaceExploreRequestModel placeExploreRequestModel = null;
    private PlaceExploreAdapter placeExploreAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_explore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new PlaceExplorePresenter(this, getApplicationContext());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        placeExploreAdapter = new PlaceExploreAdapter(this, true, getApplicationContext(), null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(placeExploreAdapter);

        placeExploreRequestModel= new PlaceExploreRequestModel();
        placeExploreRequestModel.cityId = "503b2acde4b032e338f1cd9f";
        placeExploreRequestModel.type = "Places To Visit";
        presenter.getPlaceList(placeExploreRequestModel);
    }

    @Override
    public void loadMore()
    {
        placeExploreRequestModel.skip = ( placeExploreRequestModel.skip + placeExploreRequestModel.limit);
    }

    @Override
    public void handleError()
    {
        placeExploreAdapter.setLoadMore(false);
        placeExploreAdapter.notifyDataSetChanged();
    }


    @Override
    public void onSuccessPlaceExplore(MasterResponse[] getPlaceDetailResponseModel, int taskCode)
    {

        placeExploreAdapter.addData(getPlaceData(getPlaceDetailResponseModel));
    }

    @Override
    public void onErrorPlaceExplore(ErrorDisplay errorDisplay, int taskCode) {

    }

    private PlaceExploreResponseModel.PlaceData[] getPlaceData(MasterResponse[] getPlaceDetailResponseModel)
    {
        PlaceExploreResponseModel[] getPlaceDetailResponseModels = (PlaceExploreResponseModel[]) getPlaceDetailResponseModel;
        return getPlaceDetailResponseModels[0].data.visit;
    }
}
