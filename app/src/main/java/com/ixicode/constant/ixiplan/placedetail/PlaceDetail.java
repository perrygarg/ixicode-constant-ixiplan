package com.ixicode.constant.ixiplan.placedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ixicode.constant.ixiplan.PlaceExplore.PlaceExplore;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.activity.BaseActivity;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.network.volley.MyVolley;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

import java.util.ArrayList;

public class PlaceDetail extends BaseActivity implements PlaceDetailContract.View {

    private PlaceDetailView placeDetailView = null;
    private PlaceDetailPresenter presenter = null;
    ArrayList<String> ids = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        setupToolbar(getString(R.string.abt_des), true);

        ids = getIntent().getStringArrayListExtra(AppConstant.CITIES_IDS);
        String toId = getIntent().getStringExtra("CITY_ID");

        placeDetailView = new PlaceDetailView();
        presenter = new PlaceDetailPresenter(this, getApplicationContext());
        if(toId == null) {
            presenter.getPlaceDetail(ids.get(1));
        } else {
            presenter.getPlaceDetail(toId);
        }

    }

    @Override
    public void onSuccessPlaceDetail(MasterResponse[] masterResponse, int taskCode) {

        GetPlaceDetailResponseModel.Data getPlaceDetailResponseModel = ((GetPlaceDetailResponseModel) masterResponse[0]).data;

        placeDetailView.networkImageview.setImageUrl(getPlaceDetailResponseModel.imageUrl, MyVolley.getInstance(getApplicationContext()).getImageLoader());

        String city = getPlaceDetailResponseModel.name;
        String state = getPlaceDetailResponseModel.stateName;
        String country = getPlaceDetailResponseModel.countryName;

        placeDetailView.textViewName.setText(getName(new String[]{city, state, country}));

        placeDetailView.textViewWhyVisit.setText(getPlaceDetailResponseModel.whyVisit);
        placeDetailView.textViewDesc.setText(Html.fromHtml(AppUtil.setStringNotNull(getPlaceDetailResponseModel.description)));

    }

    public String getName(String str[]) {
        int len = str.length;

        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < len; i++) {

            stringBuilder.append(str[i]);

            if (i < (len - 1))
                stringBuilder.append(",");

        }

        return stringBuilder.toString();
    }

    @Override
    public void onErroPlaceDetail(ErrorDisplay errorDisplay, int taskCode) {

    }

    private class PlaceDetailView {
        NetworkImageView networkImageview = null;
        TextView textViewName = null;
        TextView textViewDesc = null;
        TextView textViewWhyVisit = null;
        TextView placesToGo = null;
        TextView thingsToDo = null;

        public PlaceDetailView() {
            networkImageview = (NetworkImageView) findViewById(R.id.networkImageview);
            textViewName = (TextView) findViewById(R.id.textViewName);
            textViewDesc = (TextView) findViewById(R.id.textViewDesc);
            textViewWhyVisit = (TextView) findViewById(R.id.textViewWhyVisit);
            placesToGo = (TextView) findViewById(R.id.places_to_visit);
            thingsToDo = (TextView) findViewById(R.id.things_to_do);
        }


    }

    private class handleClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();

            Intent intent = new Intent(PlaceDetail.this, PlaceExplore.class);
            switch (viewId) {
                case R.id.places_to_visit:

                    String str = "Places To Visit";
                    intent.putExtra(AppConstant.TAG_CITY, str);
                    intent.putStringArrayListExtra(AppConstant.CITIES_IDS, ids);
                    break;

                case R.id.things_to_do:
                    intent.putExtra(AppConstant.TAG_CITY, "Things To Do");
                    intent.putStringArrayListExtra(AppConstant.CITIES_IDS, ids);
                    break;
            }

            startActivity(intent);
        }
    }


}
