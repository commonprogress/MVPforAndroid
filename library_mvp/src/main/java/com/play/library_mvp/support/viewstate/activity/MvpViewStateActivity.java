package com.play.library_mvp.support.viewstate.activity;

import com.play.library_mvp.base.common.MvpBaseActivity;
import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.ActivityMvpDelegate;
import com.play.library_mvp.support.viewstate.MvpViewStateDelegateCallback;
import com.play.library_mvp.support.viewstate.ViewState;

public abstract class MvpViewStateActivity<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>> extends MvpBaseActivity<V, P>
        implements MvpViewStateDelegateCallback<V, P, VS> {

    private VS viewState;
    //是否已经恢复状态
    protected boolean restoringViewState = false;

    @Override
    public ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpViewStateDelegateImpl<V, P, VS>(this, this, true);
        }
        return super.getMvpDelegate();
    }

    @Override
    public VS getViewState() {
        return viewState;
    }

    @Override
    public void setViewState(VS viewState) {
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
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {

    }

}
