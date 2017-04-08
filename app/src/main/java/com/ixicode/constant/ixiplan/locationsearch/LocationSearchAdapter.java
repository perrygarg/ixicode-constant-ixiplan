package com.ixicode.constant.ixiplan.locationsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapter;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapterListener;
import com.ixicode.constant.ixiplan.common.listners.OnItemClickListener;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 8/4/17.
 */

public class LocationSearchAdapter extends BaseRecyclerAdapter
{
    private List<AutocompletePlaceResponseModel> arrayList = null;
    private LayoutInflater layoutInflater = null;

    private final int ITEM_SHOWN = 1;
    private HandleClickListener handleClickListener = null;
    private OnItemClickListener onItemClickListener = null;

    public LocationSearchAdapter(BaseRecyclerAdapterListener baseRecyclerAdapterListener,
                                 boolean isPagination, Context context,
                                 List<AutocompletePlaceResponseModel> arrayList, OnItemClickListener onItemClickListener)
    {
        super(baseRecyclerAdapterListener, isPagination, context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        handleClickListener = new HandleClickListener();
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.textview, parent, false);
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
        private TextView textView = null;
        public LocationViewHolder(View itemView)
        {
            super(itemView);

            textView = (TextView) itemView;
            textView.setOnClickListener(handleClickListener);
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
        AutocompletePlaceResponseModel model = arrayList.get(position);
        LocationViewHolder locationViewHolder = (LocationViewHolder) holder;
        locationViewHolder.textView.setText(model.cityName);

        locationViewHolder.textView.setTag(R.id.POSITION, position);
    }

    public void setArrayList(List<AutocompletePlaceResponseModel> arrayList)
    {
        this.arrayList.clear();

        if(!AppUtil.isCollectionEmpty(arrayList)) {
            this.arrayList.addAll(arrayList);
        }

        notifyDataSetChanged();
    }
}
