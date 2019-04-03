package com.play.library_mvp.lce.impl.fragment;

import android.os.Bundle;
import android.view.View;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.lce.MvpLceView;

/**
 * Created by jhonjson on 2019/4/3
 * @describe
 */
public abstract class BaseMvpRefreshLceFragment<D, V extends MvpLceView<D>, P extends MvpPresenter<V>>
        extends BaseMvpLceFragment<D, V, P> {

    //google下啦刷新组件也是可以的
//    private XRefreshView refreshView;
//    private RecyclerView recyclerView;
//    private BaseRefreshAdapter recyclerAdapter;
//    private LinearLayoutManager linearLayoutManager;
//
//    public XRefreshView getRefreshView() {
//        return refreshView;
//    }
//
//    public BaseRefreshAdapter getAdapter() {
//        return recyclerAdapter;
//    }
//
//    public LinearLayoutManager getLinearLayoutManager() {
//        return linearLayoutManager;
//    }

    private boolean isDownRefresh = true;

    public boolean isDownRefresh() {
        return isDownRefresh;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取数据
    }

    @Override
    public void initContentView(View contentView) {
        initNavigation(contentView);
        initRefreshView(contentView);
    }

    @Override
    public void initNavigation(View contentView) {

    }

    /**
     * 初始化下拉刷新组件
     *
     * @param contentView
     */
    public void initRefreshView(View contentView) {
//        refreshView = (XRefreshView) contentView.findViewById(R.id.xrefreshview);
//        //是否可以下拉刷新,true代表可以,false代表不支持
//        refreshView.setPullRefreshEnable(true);
//        //是否允许加载更多
//        refreshView.setPullLoadEnable(true);
//        //设置下拉刷新完成之后,刷新头部停留的时间
//        refreshView.setPinnedTime(1000);
//        //设置是否自动刷新(进入页面自动刷新)
//        refreshView.setAutoRefresh(false);
//
//        recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        //设置列表为垂直方向显示
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//
//        //绑定Adapter
//        recyclerAdapter = bindAdapter();
//        recyclerView.setAdapter(recyclerAdapter);
//
//        //给我们的Adapter添加加载更多的布局
//        recyclerAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getContext()));
//
//        //添加下拉刷新监听
//        refreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
//
//            /**
//             * 下拉刷新
//             */
//            @Override
//            public void onRefresh() {
//                super.onRefresh();
//                refreshData(true);
//            }
//
//            /**
//             * 上拉加载更多
//             * @param isSlience
//             */
//            @Override
//            public void onLoadMore(boolean isSlience) {
//                super.onLoadMore(isSlience);
//                refreshData(false);
//            }
//        });
    }

    //提供给子类决定是否需要下拉刷新功能
    public void setRefresh(boolean isRefresh) {
        //是否可以下拉刷新,true代表可以,false代表不支持
//        refreshView.setPullRefreshEnable(isRefresh);
//        //是否允许加载更多
//        refreshView.setPullLoadEnable(isRefresh);
    }

    //提供给子类设置条目分割线的高度
    public void setDidiver(int height) {
        //添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(height));
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        super.loadData(pullToRefresh);

    }

//    public abstract BaseRefreshAdapter bindAdapter();

    @Override
    public void bindData(D data) {
        super.bindData(data);
        //如果你是下拉刷新组件,那么我就处理
        //刷新UI界面
//        if (isDownRefresh()) {
//            //网络请求完成,关闭下拉刷新组件加载视图
//            getRefreshView().stopRefresh();
//        } else {
//            getRefreshView().stopLoadMore();
//        }
    }

    public void refreshData(boolean isDownRefresh) {
        this.isDownRefresh = isDownRefresh;
    }

}
