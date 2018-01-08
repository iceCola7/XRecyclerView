package com.cxz.xrecyclerview.rvadapter.base;

/**
 * Created by chenxz on 2017/12/28.
 */

public interface BaseItemDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(BaseViewHolder holder, T t, int position);

}
