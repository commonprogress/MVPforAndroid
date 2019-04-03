package com.play.library_mvp.support.viewstate.lce;

import com.play.library_mvp.lce.MvpLceView;
/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public class AbsLceViewState <D, V extends MvpLceView<D>> implements LceViewState<D, V> {

    //当前状态(错误状态、内容状态、加载状态)
    protected int currentViewState;
    //是否是下拉刷新组件
    protected boolean pullToRefresh;
    //异常
    protected Throwable exception;
    //数据(需要更新UI)
    //例如：String类型->json字符串、Bean->UserBean...
    protected D loadedData;

    @Override
    public void setStateShowContent(D loadedData) {
        currentViewState = STATE_SHOW_CONTENT;
        this.loadedData = loadedData;
        exception = null;
    }

    @Override
    public void setStateShowError(Throwable e, boolean pullToRefresh) {
        currentViewState = STATE_SHOW_ERROR;
        exception = e;
        this.pullToRefresh = pullToRefresh;
        if (!pullToRefresh) {
            loadedData = null;
        }
    }

    @Override
    public void setStateShowLoading(boolean pullToRefresh) {
        currentViewState = STATE_SHOW_LOADING;
        this.pullToRefresh = pullToRefresh;
        exception = null;

        if (!pullToRefresh) {
            loadedData = null;
        }
    }

    //注意：我们以往开发，loading页面、content页面、error页面调用逻辑都是客户端自己写，然而现在交给MVP框架内部实现
    @Override
    public void apply(V view, boolean retained) {
        if (currentViewState == STATE_SHOW_CONTENT) {
            view.bindData(loadedData);
            view.showContent();
        } else if (currentViewState == STATE_SHOW_LOADING) {
            boolean ptr = pullToRefresh;
            if (pullToRefresh) {
                view.bindData(loadedData);
                view.showContent();
            }

            view.showLoading(ptr);
        } else if (currentViewState == STATE_SHOW_ERROR) {

            boolean ptr = pullToRefresh;
            Throwable e = exception;
            if (pullToRefresh) {
                view.bindData(loadedData);
                view.showContent();
            }
            view.showError(e, ptr);
        }
    }

    @Override
    public boolean isLoadingState() {
        return STATE_SHOW_LOADING == currentViewState;
    }

    @Override
    public boolean isPullToRefreshLoadingState() {
        return isLoadingState() && pullToRefresh;
    }

}

