package com.ixicode.constant.ixiplan.placedetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.network.volley.MyVolley;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

public class PlaceDetail extends AppCompatActivity  implements PlaceDetailContract.View{

    private PlaceDetailView placeDetailView = null;
    private PlaceDetailPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        placeDetailView = new PlaceDetailView();
        presenter = new PlaceDetailPresenter(this, getApplicationContext());
        presenter.getPlaceDetail("503b2a92e4b032e338f13fd5");

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

    public String getName(String str[])
    {
        int len = str.length;

        StringBuilder stringBuilder = new StringBuilder("");

        for(int i  = 0; i < len; i++){

            stringBuilder.append(str[i]);

            if(i < (len - 1))
                stringBuilder.append(",");

        }

        return stringBuilder.toString();
    }

    @Override
    public void onErroPlaceDetail(ErrorDisplay errorDisplay, int taskCode) {

    }

    private class PlaceDetailView
    {
        NetworkImageView networkImageview = null;
        TextView textViewName = null;
        TextView textViewDesc = null;
        TextView textViewWhyVisit = null;

        public PlaceDetailView()
        {
            networkImageview = (NetworkImageView) findViewById(R.id.networkImageview);
            textViewName = (TextView) findViewById(R.id.textViewName);
            textViewDesc = (TextView) findViewById(R.id.textViewDesc);
            textViewWhyVisit = (TextView) findViewById(R.id.textViewWhyVisit);
        }

    }

}