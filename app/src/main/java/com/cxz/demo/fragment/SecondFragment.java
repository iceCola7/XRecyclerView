package com.cxz.demo.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cxz.demo.R;
import com.cxz.demo.adapter.RecyclerViewAdapter;
import com.cxz.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private XRecyclerView mPullLoadMoreRecyclerView;
    private int mCount = 1;
    private RecyclerViewAdapter mRecyclerViewAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPullLoadMoreRecyclerView = (XRecyclerView) view.findViewById(R.id.pullLoadMoreRecycleView);
        mPullLoadMoreRecyclerView.setRefreshing(true);
        new DataAsyncTask().execute();
        mPullLoadMoreRecyclerView.setGridLayout(2);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }


    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        int start = 30 * (mCount - 1);
        for (int i = start; i < 30 * mCount; i++) {
            dataList.add("Second" + i);
        }
        return dataList;

    }

    class DataAsyncTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return setList();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            if (mRecyclerViewAdapter == null) {
                mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(),R.layout.item_recycle_view, strings);
                mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
            } else {
                mRecyclerViewAdapter.appendLists(strings);
            }
            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        }
    }

    class PullLoadMoreListener implements XRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            mCount = 1;
            new DataAsyncTask().execute();
        }

        @Override
        public void onLoadMore() {
            mCount = mCount + 1;
            new DataAsyncTask().execute();
        }
    }

}
