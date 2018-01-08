package com.cxz.demo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxz.demo.R;
import com.cxz.demo.adapter.RecyclerViewAdapter;
import com.cxz.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private XRecyclerView mXRecyclerView;
    private int mCount = 1;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mXRecyclerView = view.findViewById(R.id.pullLoadMoreRecycleView);
        mXRecyclerView.setRefreshing(true);
        //mXRecyclerView.setFooterViewText("loading...");

        new DataAsyncTask().execute();
        mXRecyclerView.setLinearLayout();
        mXRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }

    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        int start = 20 * (mCount - 1);
        for (int i = start; i < 20 * mCount; i++) {
            dataList.add("First " + i);
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
                mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), R.layout.item_recycle_view, strings);
                mXRecyclerView.setAdapter(mRecyclerViewAdapter);
            } else {
                mRecyclerViewAdapter.appendLists(strings);
            }
            mXRecyclerView.setPullLoadMoreCompleted();
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
