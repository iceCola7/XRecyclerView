package com.cxz.xrecyclerview;

/**
 * @author chenxz
 * @date 2019/1/5
 * @desc
 */
public interface RefreshTrigger {

    void onStart(boolean automatic, int headerHeight, int finalHeight);

    void onMove(boolean finished, boolean automatic, int moved);

    void onRefresh();

    void onRelease();

    void onComplete();

    void onReset();

}
