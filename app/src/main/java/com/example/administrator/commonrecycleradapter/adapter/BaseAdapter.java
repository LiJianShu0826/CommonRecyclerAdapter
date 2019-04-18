package com.example.administrator.commonrecycleradapter.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected List<T> mData;
    @LayoutRes
    private int mLayoutId;

    private OnItemClickListener mOnItemClickListener;
    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private OnChildLongClickListener mOnChildLongClickListener;

    public BaseAdapter(Context context, @LayoutRes int layoutId, List<T> data) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mData = data;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int viewType) {
        final BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(mContext, viewGroup, mLayoutId);
        if (mOnItemClickListener != null) {
            viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.itemClickListener(viewHolder, viewHolder.getAdapterPosition());
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.itemLongClickListener(viewHolder, viewHolder.getAdapterPosition());
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        baseViewHolder.setAdapter(this);
        convert(baseViewHolder, position, mData.get(position));
    }

    public abstract void convert(BaseViewHolder baseViewHolder, int position, T data);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        this.mOnItemChildClickListener = listener;
    }

    public OnItemChildClickListener getOnItemChildClickListener() {
        return mOnItemChildClickListener;
    }

    public void setOnChildLongClickListener(OnChildLongClickListener listener) {
        this.mOnChildLongClickListener = listener;
    }

    public OnChildLongClickListener getOnChildLongClickListener() {
        return mOnChildLongClickListener;
    }

    public interface OnItemClickListener {
        void itemClickListener(BaseViewHolder viewHolder, int position);
    }

    public interface OnItemChildClickListener {
        void itemChildClickListener(View v, int position);
    }

    public interface OnItemLongClickListener {
        boolean itemLongClickListener(BaseViewHolder viewHolder, int position);
    }

    public interface OnChildLongClickListener {
        boolean childLongClickListener(View v, int position);
    }
}
