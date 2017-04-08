package com.ixicode.constant.ixiplan.triplisting.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapter;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapterListener;
import com.ixicode.constant.ixiplan.common.constants.AppConstant;
import com.ixicode.constant.ixiplan.common.listners.OnItemClickListener;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.triplisting.model.modeslisting.CheapestRouteModel;
import com.ixicode.constant.ixiplan.triplisting.model.modeslisting.FastestRouteModel;
import com.ixicode.constant.ixiplan.triplisting.model.response.DataModelResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PerryGarg on 8/4/17.
 */

public class CheapestRouteAdapter extends BaseRecyclerAdapter
{
    private ArrayList<CheapestRouteModel> arrayList = null;
    private LayoutInflater layoutInflater = null;

    private final int ITEM_SHOWN = 1;
    private HandleClickListener handleClickListener = null;
    private OnItemClickListener onItemClickListener = null;

    public CheapestRouteAdapter(BaseRecyclerAdapterListener baseRecyclerAdapterListener,
                                boolean isPagination, Context context,
                                ArrayList<CheapestRouteModel> arrayList, OnItemClickListener onItemClickListener)
    {
        super(baseRecyclerAdapterListener, isPagination, context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        handleClickListener = new HandleClickListener();
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.modes_list_row, parent, false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);

        return locationViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        handleOnBindView(holder, position);
    }

    @Override
    public int getCount() {
        return AppUtil.isCollectionEmpty(arrayList) ? 0 : arrayList.size();
    }

    @Override
    protected int getViewType(int position) {
        return ITEM_SHOWN;
    }


    private class LocationViewHolder extends RecyclerView.ViewHolder
    {
        private RelativeLayout rView = null;
        public LocationViewHolder(View itemView)
        {
            super(itemView);

            rView = (RelativeLayout) itemView;
            rView.setOnClickListener(handleClickListener);
        }
    }

    private class HandleClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            int pos = (int) view.getTag(R.id.POSITION);
            onItemClickListener.onItemClick(pos);
        }
    }

    private void handleOnBindView(RecyclerView.ViewHolder holder, int position)
    {
        CheapestRouteModel model = arrayList.get(position);
        CheapestRouteAdapter.LocationViewHolder locationViewHolder = (CheapestRouteAdapter.LocationViewHolder) holder;
        RelativeLayout layout = locationViewHolder.rView;
        TextView name = ((TextView)locationViewHolder.rView.findViewById(R.id.name));
        TextView fare = ((TextView)locationViewHolder.rView.findViewById(R.id.fare));
        TextView duration = ((TextView)locationViewHolder.rView.findViewById(R.id.duration));
        ImageView imageView = ((ImageView) locationViewHolder.rView.findViewById(R.id.icon_view));
        switch (model.type) {
            case AppConstant.FLIGHT:
                imageView.setImageResource(R.drawable.ic_airplanemode_active_white_36dp);
                break;
            case AppConstant.TRAIN:
                imageView.setImageResource(R.drawable.ic_train_white_36dp);
                break;
            case AppConstant.CAR:
                imageView.setImageResource(R.drawable.ic_directions_car_white_36dp);
                break;
            case AppConstant.BUS:
                imageView.setImageResource(R.drawable.ic_directions_bus_white_36dp);
                break;
        }
        name.setText(model.name);
        fare.setText("INR " +model.fare);
        duration.setText(model.duration + " min.");
        locationViewHolder.rView.setTag(R.id.POSITION, position);
    }

    public void setArrayList(List<CheapestRouteModel> arrayList)
    {
        this.arrayList.clear();

        if(!AppUtil.isCollectionEmpty(arrayList)) {
            this.arrayList.addAll(arrayList);
        }

        notifyDataSetChanged();
    }
}
