package com.ixicode.constant.ixiplan.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.fragment.BaseFragment;
import com.ixicode.constant.ixiplan.common.model.ErrorDisplay;
import com.ixicode.constant.ixiplan.common.model.MasterResponse;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.TrendingPlacesContract;
import com.ixicode.constant.ixiplan.dashboard.TrendingPlacesPresenter;
import com.ixicode.constant.ixiplan.dashboard.model.TrendingLocationResponse;

/**
 * Created by PerryGarg on 08-04-2017.
 */

public class TrendingTripsFragment extends BaseFragment implements TrendingPlacesContract.View {

    private TrendingPlacesContract.Presenter presenter = null;
    private TrendingLocationResponse trendingLocationResponse = null;
    private HandleViewPageAdapter handleViewPageAdapter = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trending_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        presenter = new TrendingPlacesPresenter(this, getContext().getApplicationContext());
        handleViewPageAdapter = new HandleViewPageAdapter(getChildFragmentManager(), null);
        viewPager.setAdapter(handleViewPageAdapter);

        TextView textViewAll = (TextView) view.findViewById(R.id.textViewAll);
        TextView textViewbudget = (TextView) view.findViewById(R.id.textViewbudget);

        HandleClickListener handleClickListener = new HandleClickListener();
        textViewAll.setOnClickListener(handleClickListener);
        textViewbudget.setOnClickListener(handleClickListener);

        presenter.getTrendingPlaces();
    }

    @Override
    public void onSuccessTrendingPlaces(MasterResponse[] trendingLocationResponse) {

        changeAdapterData(trendingLocationResponse);
    }

    private void changeAdapterData(MasterResponse[] trendingLocationResponse) {
        this.trendingLocationResponse = (TrendingLocationResponse) trendingLocationResponse[0];

        if(isTrendingDataNull(this.trendingLocationResponse))
        {
            handleViewPageAdapter.setFlight(null);
        }
        else
        {
            handleViewPageAdapter.setFlight(this.trendingLocationResponse.data.flight);
        }

        handleViewPageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailTrendingPlaces(ErrorDisplay error) {

    }

    private class HandleViewPageAdapter extends FragmentPagerAdapter {

        private TrendingLocationResponse.Flight flight[] = null;

        public HandleViewPageAdapter(FragmentManager fm, TrendingLocationResponse.Flight flight[]) {
            super(fm);
            this.flight = flight;
        }

        public void setFlight(TrendingLocationResponse.Flight flight[])
        {
            this.flight = flight;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return TrendingItemFragment.createFragment(getFlight(position));
        }

        @Override
        public int getCount() {
            return AppUtil.isArrayEmpty(flight) ? 0 : flight.length;
        }
    }

    private boolean isTrendingDataNull(TrendingLocationResponse trendingLocationResponse)
    {
        if(trendingLocationResponse == null || trendingLocationResponse.data == null || AppUtil.isArrayEmpty(trendingLocationResponse.data.flight))
            return true;

        return false;
    }

    private TrendingLocationResponse.Flight getFlight(int position)
    {
        return trendingLocationResponse.data.flight[position];
    }

    private class HandleClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            int id = v.getId();

            switch(id)
            {
                case R.id.textViewAll:

                    handleViewPageAdapter.setFlight(TrendingTripsFragment.this.trendingLocationResponse.data.flight);

                    break;

                case R.id.textViewPrice:
                    handleViewPageAdapter.setFlight(TrendingTripsFragment.this.trendingLocationResponse.data.budgetFlight);
                    break;
            }

        }
    }

}
