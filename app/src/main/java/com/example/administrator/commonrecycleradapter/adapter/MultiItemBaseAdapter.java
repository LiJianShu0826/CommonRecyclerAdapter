package com.example.administrator.commonrecycleradapter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

public abstract class MultiItemBaseAdapter<T> extends BaseAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemBaseAdapter(Context context, List<T> data, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, data);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mData.get(position));
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(mContext, viewGroup, layoutId);
        return viewHolder;
    }
}
