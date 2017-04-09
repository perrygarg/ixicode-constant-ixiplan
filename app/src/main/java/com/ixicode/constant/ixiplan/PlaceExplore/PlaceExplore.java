package com.ixicode.constant.ixiplan.PlaceExplore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreRequestModel;
import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreResponseModel;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapterListener;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.placedetail.PlaceDetail;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

import java.util.ArrayList;

public class PlaceExplore extends BaseActivity implements BaseRecyclerAdapterListener, PlaceExploreContract.View, PlaceExploreAdapter.OnPlaceClickListener {

    private PlaceExploreContract.Presenter presenter = null;
    private PlaceExploreRequestModel placeExploreRequestModel = null;
    private PlaceExploreAdapter placeExploreAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_explore);
        setupToolbar("Explore", true);

        presenter = new PlaceExplorePresenter(this, getApplicationContext());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        placeExploreAdapter = new PlaceExploreAdapter(this, true, getApplicationContext(), null, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(placeExploreAdapter);

        placeExploreRequestModel= new PlaceExploreRequestModel();
        placeExploreRequestModel.cityId = getIntent().getStringArrayListExtra(AppConstant.CITIES_IDS).get(1);
        placeExploreRequestModel.type = getIntent().getStringExtra(AppConstant.TAG_CITY);
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
        if(placeExploreRequestModel.type.equals("Things To Do")) {
            return getPlaceDetailResponseModels[0].data.things;
        } else {
            return getPlaceDetailResponseModels[0].data.visit;
        }

    }

    @Override
    public void onItemClick(PlaceExploreResponseModel.PlaceData placeExploreResponseModel) {

        Intent intent = new Intent(this, PlaceDetail.class);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(placeExploreResponseModel.id);
        arrayList.add(placeExploreResponseModel.id);

        intent.putExtra(AppConstant.CITIES_IDS, arrayList);
        intent.putExtra(AppConstant.COMING_FROM, AppConstant.COMING_FROM_PLACE_EXPLORE);
        startActivity(intent);

    }
}
