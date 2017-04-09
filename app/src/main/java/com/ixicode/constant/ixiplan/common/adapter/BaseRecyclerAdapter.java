package com.ixicode.constant.ixiplan.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ixicode.constant.ixiplan.R;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private BaseRecyclerAdapterListener baseRecyclerAdapterListener =  null;
    private LayoutInflater layoutInflater = null;

    private int total = 0;
    private int count = 0;
    protected boolean isLoadMore = true;
    private boolean isPagination = true;

    private final int ITEM_PROGRESS_SHOWN = 100;
    private final int ITEM_ERROR_SHOWN = 101;
    private Context context = null;

    protected BaseRecyclerAdapter(BaseRecyclerAdapterListener baseRecyclerAdapterListener, boolean isPagination, Context context)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.baseRecyclerAdapterListener = baseRecyclerAdapterListener;
        this.isPagination = isPagination;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case ITEM_PROGRESS_SHOWN:

                View view = layoutInflater.inflate(R.layout.progress_layout, parent, false);
                viewHolder = new ViewHolderProgressItem(view);
                view.setVisibility(View.GONE);

                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        int viewType = getItemViewType(position);

        switch (viewType)
        {
            case ITEM_PROGRESS_SHOWN:
                displayProgress(holder, position);
                break;
        }

    }

    private void displayProgress(RecyclerView.ViewHolder holder, int position)
    {
        if(isLoadMore) {
        if (count < total) {

            ViewHolderProgressItem viewHolderProgressItem = (ViewHolderProgressItem) holder;

            if (position < total)
            {
                if(isLoadMore) {

                    viewHolderProgressItem.linearLayoutParent.setVisibility(View.VISIBLE);

                    if (baseRecyclerAdapterListener != null) {
                        baseRecyclerAdapterListener.loadMore();
                    }
                }
                else
                {
                    viewHolderProgressItem.linearLayoutParent.setVisibility(View.INVISIBLE);

                    if (baseRecyclerAdapterListener != null) {
                        baseRecyclerAdapterListener.handleError();
                    }
                }
            }
            else
            {
                viewHolderProgressItem.linearLayoutParent.setVisibility(View.INVISIBLE);
            }
        }
        }
//        else
//        {
//            displayError();
//        }
    }

//    private void displayError()
//    {
//        if(baseRecyclerAdapterListener != null)
//        {
//            baseRecyclerAdapterListener.handleError();
//        }
//    }

    private static class ViewHolderProgressItem extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayoutParent = null;
        ProgressBar progressBar = null;


        public ViewHolderProgressItem(View itemView)
        {
            super(itemView);

            linearLayoutParent = (LinearLayout) itemView;
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

        }
    }

    @Override
    public int getItemCount()
    {
        count = getCount();
//        int temp = count == 0 ? 1 : count;

        return count;
    }

    @Override
    public int getItemViewType(int position) {

        if((position + 1) == count && isPagination)
        {
            return ITEM_PROGRESS_SHOWN;
        }

        return getViewType(position);
    }

    public void setLoadMore(boolean isLoadMore)
    {
        this.isLoadMore = isLoadMore;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    protected abstract int getCount();

    protected abstract int getViewType(int position);

//    protected abstract RecyclerView.ViewHolder onCreateViewHolder();
}

