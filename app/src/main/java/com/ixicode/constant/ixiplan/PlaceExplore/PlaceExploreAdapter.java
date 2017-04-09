package com.ixicode.constant.ixiplan.PlaceExplore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ixicode.constant.ixiplan.PlaceExplore.model.PlaceExploreResponseModel;
import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapter;
import com.ixicode.constant.ixiplan.common.adapter.BaseRecyclerAdapterListener;
import com.ixicode.constant.ixiplan.common.network.volley.MyVolley;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.placedetail.model.GetPlaceDetailResponseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by akash on 9/4/17.
 */

public class PlaceExploreAdapter extends BaseRecyclerAdapter
{
    private final int ITEM_SHOWN = 1;
    private LayoutInflater layoutInflater = null;
    private List<PlaceExploreResponseModel.PlaceData> arrayList = null;
    private Context context = null;

    protected PlaceExploreAdapter(BaseRecyclerAdapterListener baseRecyclerAdapterListener,
                                  boolean isPagination, Context context, List<PlaceExploreResponseModel.PlaceData> arrayList) {
        super(baseRecyclerAdapterListener, isPagination, context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;

        if(arrayList == null)
            this.arrayList = new ArrayList<>();

        this.context = context;
    }

    @Override
    protected int getCount() {
        return (AppUtil.isCollectionEmpty(arrayList) || !isLoadMore) ? 0 : arrayList.size();
    }

    @Override
    protected int getViewType(int position) {

        return ITEM_SHOWN;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        switch(viewType)
        {
            case ITEM_SHOWN:

                View view = layoutInflater.inflate(R.layout.place_explore_item, parent, false);
                viewHolder = new PlaceExploreItemViewHolder(view);

                break;

            default:

                viewHolder = super.onCreateViewHolder(parent, viewType);
        }

        return viewHolder;
    }

    private class PlaceExploreItemViewHolder extends RecyclerView.ViewHolder
    {

        NetworkImageView networkImageView = null;
        TextView textViewName = null;
        TextView textViewAddress = null;


        public PlaceExploreItemViewHolder(View itemView)
        {
            super(itemView);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageview);
            textViewName= (TextView) itemView.findViewById(R.id.textViewName);
            textViewAddress= (TextView) itemView.findViewById(R.id.textViewAddress);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getViewType(position);

        switch (viewType)
        {
            case ITEM_SHOWN:
                hanldeBindView(holder, position);
                break;

            default:
                super.onBindViewHolder(holder, position);
        }


    }

    private void hanldeBindView(RecyclerView.ViewHolder holder, int position) {

        PlaceExploreItemViewHolder placeExploreItemViewHolder = (PlaceExploreItemViewHolder) holder;
        PlaceExploreResponseModel.PlaceData placeExploreResponseModel = arrayList.get(position);

        placeExploreItemViewHolder.networkImageView.setImageUrl(placeExploreResponseModel.imageUrl, MyVolley.getInstance(context).getImageLoader());
        placeExploreItemViewHolder.textViewName.setText(AppUtil.setStringNotNull(placeExploreResponseModel.name));
        placeExploreItemViewHolder.textViewAddress.setText(AppUtil.setStringNotNull(placeExploreResponseModel.address));
    }

    public void setLoadMore(boolean isLoadMore)
    {
        this.isLoadMore = isLoadMore;
    }

    public void addData(PlaceExploreResponseModel.PlaceData placeData[])
    {
        if(!AppUtil.isArrayEmpty(placeData))
        {
            List<PlaceExploreResponseModel.PlaceData> arrayList = (List<PlaceExploreResponseModel.PlaceData>) Arrays.asList(placeData);
            this.arrayList.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
}
