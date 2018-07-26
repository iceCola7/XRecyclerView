# XRecyclerView

实现RecyclerView下拉刷新和加载更多

## 效果预览
![](https://github.com/bjchenxz/XRecyclerView/raw/master/gif/app.gif)
## 使用方式
### build.gradle文件
```
implementation 'com.cxz:xrecyclerview:1.0.1'
```

### 布局文件
```
<com.cxz.xrecyclerview.XRecyclerView
    android:id="@+id/xRecyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### java 代码
```
mXRecyclerView.setRefreshing(true);// 设置是否可以刷新
mXRecyclerView.setLinearLayout();// 设置 LayoutManager
mXRecyclerView.setOnPullLoadMoreListener(new XRecyclerView.PullLoadMoreListener());// 设置回调监听
```
