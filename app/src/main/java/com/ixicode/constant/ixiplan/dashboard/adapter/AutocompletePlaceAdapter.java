package com.ixicode.constant.ixiplan.dashboard.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.application.IxiPlanApp;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

public class AutocompletePlaceAdapter extends ArrayAdapter<AutocompletePlaceResponseModel>
{
    private LayoutInflater layoutInflater = null;

    public AutocompletePlaceAdapter(@NonNull Context context, @LayoutRes int resource)
    {
        super(context, resource);
        IxiPlanApp ixiPlanApp = (IxiPlanApp) context;
        layoutInflater = (LayoutInflater) ixiPlanApp.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return super.getCount();
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

}
