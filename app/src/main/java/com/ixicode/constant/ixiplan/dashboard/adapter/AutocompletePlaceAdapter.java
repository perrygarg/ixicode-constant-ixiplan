package com.ixicode.constant.ixiplan.dashboard.adapter;

import android.app.Application;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ixicode.constant.ixiplan.R;
import com.ixicode.constant.ixiplan.common.util.AppUtil;
import com.ixicode.constant.ixiplan.dashboard.contract.InputFormContract;
import com.ixicode.constant.ixiplan.dashboard.fragment.InputFormFragment;
import com.ixicode.constant.ixiplan.dashboard.model.AutocompletePlaceResponseModel;

import java.util.ArrayList;
import java.util.List;

public class AutocompletePlaceAdapter extends ArrayAdapter<AutocompletePlaceResponseModel>
{
    private LayoutInflater layoutInflater = null;
    private ArrayList<AutocompletePlaceResponseModel> arrayList = null;
    private InputFormFragment inputFormFragment = null;
    private int mode = 0;

    public AutocompletePlaceAdapter(@NonNull Context context, List<AutocompletePlaceResponseModel> response) {
        super(context, android.R.layout.simple_list_item_1, response);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

        AutocompletePlaceResponseModel model = getItem(position);
        String place = model.cityName;

        textViewHolder.textViewName.setText(AppUtil.setStringNotNull(place));
        textViewHolder.textViewName.setTag(R.id.CITY_ID, model.cityId);
        textViewHolder.textViewName.setTag(R.id.CITY_X_ID, model.xId);

        textViewHolder.textViewName.setOnClickListener(new HandleClickListener());

        return view;
    }

    private View createView(ViewGroup parent)
    {
        View view = null;
        view = layoutInflater.inflate(R.layout.textview, parent, false);

        TextViewHolder textViewHolder = new TextViewHolder();
        textViewHolder.textViewName = (TextView) view.findViewById(R.id.textViewName);

        view.setTag(R.id.VIEW, textViewHolder);

        return view;
    }

    private TextViewHolder onBindView(View view)
    {
        TextViewHolder textViewHolder = (TextViewHolder) view.getTag(R.id.VIEW);
        return textViewHolder;
    }

    private static class TextViewHolder
    {
        TextView textViewName = null;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                AutocompletePlaceResponseModel responseModel = (AutocompletePlaceResponseModel) resultValue;
                return responseModel.cityName;
            }
        };
    }

    private class HandleClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            System.out.println("view...........");
        }
    }
}
