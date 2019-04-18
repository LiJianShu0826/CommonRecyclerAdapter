package com.example.administrator.commonrecycleradapter.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;//提供一个容器存储item中的view
    private Context mContext;
    private View mConvertView;
    private BaseAdapter adapter;

    private BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static BaseViewHolder createViewHolder(Context context, View itemView) {
        return new BaseViewHolder(context, itemView);
    }

    public static BaseViewHolder createViewHolder(Context context, ViewGroup parent, @LayoutRes int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseViewHolder(context, view);
    }

    public <T extends View> T getView(@IdRes int resId) {
        if (mViews != null) {
            View view = mViews.get(resId);
            if (view == null) {
                view = mConvertView.findViewById(resId);
                mViews.put(resId, view);
            }
            return (T) view;
        }
        return null;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public final void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    public final BaseViewHolder setOnChildClickListener(@IdRes int resId) {
        View view = getView(resId);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.getOnItemChildClickListener().itemChildClickListener(v, getLayoutPosition());
                }
            });
        }
        return this;
    }

    public final BaseViewHolder setOnChildLongClickListener(@IdRes int resId) {
        View view = getView(resId);
        if (view != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return adapter.getOnChildLongClickListener().childLongClickListener(v, getLayoutPosition());
                }
            });
        }
        return this;
    }

    /**
     * 可以添加一些辅助方法
     */

    public BaseViewHolder setText(int resId, String s) {
        TextView view = getView(resId);
        view.setText(s);
        return this;
    }
}
