package com.example.administrator.commonrecycleradapter;

import android.content.Context;

import com.example.administrator.commonrecycleradapter.adapter.BaseAdapter;
import com.example.administrator.commonrecycleradapter.adapter.BaseViewHolder;

import java.util.List;

public class MyAdapter extends BaseAdapter<String> {

    public MyAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, int position, String data) {
        baseViewHolder.setText(R.id.textView, data)
                .setOnChildClickListener(R.id.btn)
                .setOnChildLongClickListener(R.id.btn);
    }
}
