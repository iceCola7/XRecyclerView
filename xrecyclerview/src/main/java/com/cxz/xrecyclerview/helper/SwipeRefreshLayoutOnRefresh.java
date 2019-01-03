package com.cxz.xrecyclerview.helper;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cxz.xrecyclerview.XRecyclerView;

public class SwipeRefreshLayoutOnRefresh implements SwipeRefreshLayout.OnRefreshListener {

    XRecyclerView mXRecyclerView;

    public SwipeRefreshLayoutOnRefresh(XRecyclerView xRecyclerView) {
        this.mXRecyclerView = xRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mXRecyclerView.isRefresh()) {
            mXRecyclerView.setIsRefresh(true);
            mXRecyclerView.refresh();
        }
    }
}
