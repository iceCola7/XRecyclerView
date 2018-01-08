package com.cxz.demo.adapter;

import android.content.Context;

import com.cxz.demo.R;
import com.cxz.xrecyclerview.rvadapter.CommonAdapter;
import com.cxz.xrecyclerview.rvadapter.base.BaseViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends CommonAdapter<String> {

    public RecyclerViewAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, String s, int position) {
        holder.setText(R.id.title, s);
    }

    public void setLists(List<String> lists) {
        mDatas.clear();
        appendLists(lists);
    }

    public void appendLists(List<String> lists) {
        mDatas.addAll(lists);
        notifyDataSetChanged();
    }

}
