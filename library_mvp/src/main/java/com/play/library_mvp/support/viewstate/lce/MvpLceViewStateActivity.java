package com.play.library_mvp.support.viewstate.lce;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.lce.MvpLceView;
import com.play.library_mvp.lce.impl.MvpLceActivity;
import com.play.library_mvp.support.ActivityMvpDelegate;
import com.play.library_mvp.support.viewstate.MvpViewStateDelegateCallback;
import com.play.library_mvp.support.viewstate.activity.ActivityMvpViewStateDelegateImpl;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public abstract class MvpLceViewStateActivity <M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<M, V, P>
        implements MvpLceView<M>, MvpViewStateDelegateCallback<V, P, LceViewState<M, V>> {

    //LCE状态
    protected LceViewState<M, V> viewState;

    //是否正在恢复
    protected boolean restoringViewState = false;

    @Override
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpViewStateDelegateImpl<>(this, this, true);
        }
        return mvpDelegate;
    }

    @Override
    public LceViewState<M, V> getViewState() {
        return viewState;
    }

    @Override
    public void setViewState(LceViewState<M, V> viewState) {
        this.viewState = viewState;
    }

    @Override
    public void setRestoringViewState(boolean restoringViewState) {
        this.restoringViewState = restoringViewState;
    }

    @Override
    public boolean isRestoringViewState() {
        return restoringViewState;
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(false);
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetainedInMemory) {
        if (!instanceStateRetainedInMemory && viewState.isLoadingState()) {
            loadData(viewState.isPullToRefreshLoadingState());
        }
    }

    @Override
    public void showContent() {
//        super.showContent();
        viewState.setStateShowContent(getData());
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
//        super.showError(e, pullToRefresh);
        viewState.setStateShowError(e, pullToRefresh);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
//        super.showLoading(pullToRefresh);
        viewState.setStateShowLoading(pullToRefresh);
    }

    public abstract M getData();
}
