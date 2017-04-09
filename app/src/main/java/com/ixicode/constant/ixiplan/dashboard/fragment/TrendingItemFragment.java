package com.ixicode.constant.ixiplan.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.network.WebManager;
import com.ixicode.constant.ixiplan.common.network.volley.MyVolley;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.model.TrendingLocationResponse;

/**
 * Created by akash on 9/4/17.
 */

public class TrendingItemFragment extends Fragment
{

    private TrendingLocationResponse.Flight flight = null;

    public static TrendingItemFragment createFragment(TrendingLocationResponse.Flight flight)
    {
        TrendingItemFragment trendingItemFragment = new TrendingItemFragment();
        trendingItemFragment.flight = flight;
        return trendingItemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.trending_frag_item, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NetworkImageView imageViewBack = (NetworkImageView) view.findViewById(R.id.imageViewBack);
        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);

        if(flight != null) {
            imageViewBack.setImageUrl(AppUtil.setStringNotNull(flight.image), MyVolley.getInstance(getContext()).getImageLoader());
            textViewName.setText(flight.cityName);
            textViewPrice.setText("INR " + flight.price);
        }

    }
}
