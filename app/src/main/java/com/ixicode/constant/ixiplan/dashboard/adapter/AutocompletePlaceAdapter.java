package com.ixicode.constant.ixiplan.dashboard.adapter;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

import java.util.ArrayList;

public class AutocompletePlaceAdapter extends BaseAdapter
{
    private LayoutInflater layoutInflater = null;
    private ArrayList<AutocompletePlaceResponseModel> arrayList = null;

    public AutocompletePlaceAdapter(Context context, ArrayList<AutocompletePlaceResponseModel> arrayList)
    {
        Application ixiPlanApp = (Application) context;
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater) ixiPlanApp.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return AppUtil.isCollectionEmpty(arrayList) ? 0 : arrayList.size();
    }

    @Override
    public AutocompletePlaceResponseModel getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;

        if(view == null)
        {
            view = createView(parent);
        }

        TextViewHolder textViewHolder = onBindView(view);

        String place = getItem(position).cityName;

        textViewHolder.textViewName.setText(AppUtil.setStringNotNull(place));

        return view;
    }

    private View createView(ViewGroup parent)
    {
        View view = null;
        view = layoutInflater.inflate(R.layout.textview, parent, false);

        TextViewHolder textViewHolder = new TextViewHolder();
        textViewHolder.textViewName = (TextView) view.findViewById(R.id.textViewName);

        view.setTag(textViewHolder);

        return view;
    }

    private TextViewHolder onBindView(View view)
    {
        TextViewHolder textViewHolder = (TextViewHolder) view.getTag();
        return textViewHolder;
    }

    private static class TextViewHolder
    {
        TextView textViewName = null;
    }

    public void setPlaceArraylist(ArrayList<AutocompletePlaceResponseModel> autocompletePlaceResponseModels)
    {
        this.arrayList = autocompletePlaceResponseModels;
        notifyDataSetChanged();
    }

}
