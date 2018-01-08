package com.cxz.demo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cxz.demo.R;
import com.cxz.demo.adapter.StaggeredRecycleViewAdapter;
import com.cxz.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdFragment extends Fragment {
    private XRecyclerView mXRecyclerView;
    private int mCount = 1;
    private StaggeredRecycleViewAdapter mRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.pullLoadMoreRecycleView);
        mXRecyclerView.setRefreshing(true);
        new DataAsyncTask().execute();
        mXRecyclerView.setStaggeredGridLayout(2);
        mXRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
    }

    private List<Map<String, String>> setList() {
        List<Map<String, String>> dataList = new ArrayList<>();
        int start = 20 * (mCount - 1);
        Map<String, String> map;
        for (int i = start; i < 20 * mCount; i++) {
            map = new HashMap<>();
            map.put("text", "Third" + i);
            map.put("height", (100 + 2 * i) + "");
            dataList.add(map);
        }
        return dataList;

    }

    class DataAsyncTask extends AsyncTask<Void, Void, List<Map<String, String>>> {
        @Override
        protected List<Map<String, String>> doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return setList();
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> strings) {
            super.onPostExecute(strings);
            if (mRecyclerViewAdapter == null) {
                mRecyclerViewAdapter = new StaggeredRecycleViewAdapter(getActivity(), strings);
                mXRecyclerView.setAdapter(mRecyclerViewAdapter);
            } else {
                mRecyclerViewAdapter.getDataList().addAll(strings);
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
            mXRecyclerView.setPullLoadMoreCompleted();
        }
    }

    class PullLoadMoreListener implements XRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            setRefresh();
            new DataAsyncTask().execute();
        }

        @Override
        public void onLoadMore() {
            mCount = mCount + 1;
            new DataAsyncTask().execute();
        }
    }

    private void setRefresh() {
        mRecyclerViewAdapter.getDataList().clear();
        mCount = 1;
    }

}
