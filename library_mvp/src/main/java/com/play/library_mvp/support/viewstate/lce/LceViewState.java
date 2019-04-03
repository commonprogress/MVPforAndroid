package com.play.library_mvp.support.viewstate.lce;

import com.play.library_mvp.lce.MvpLceView;
import com.play.library_mvp.support.viewstate.ViewState;
/**
 * Created by jhonjson on 2019/4/2
 * E-mail:824483029@qq.com
 */
public interface LceViewState <D, V extends MvpLceView<D>> extends ViewState<V> {

    //三种状态
    //第一种：加载页面->加载状态
    int STATE_SHOW_LOADING = 0;
    //第二种：内容页面->显示内容
    int STATE_SHOW_CONTENT = 1;
    //第三种：错误页面->显示错误
    int STATE_SHOW_ERROR = -1;

    //设置内容页面状态->缓存内容数据
    void setStateShowContent(D loadedData);

    //设置错误页面状态->缓存错误信息
    void setStateShowError(Throwable e, boolean pullToRefresh);

    //设置加载页面状态->缓存加载数据
    void setStateShowLoading(boolean pullToRefresh);

    //是否处于加载状态
    boolean isLoadingState();

    //是否处于下拉刷新加载状态
    boolean isPullToRefreshLoadingState();

}
