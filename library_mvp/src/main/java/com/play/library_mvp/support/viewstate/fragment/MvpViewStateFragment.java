package com.play.library_mvp.support.viewstate.fragment;

import com.play.library_mvp.base.common.MvpBaseFragment;
import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.fragment.FragmentMvpDelegate;
import com.play.library_mvp.support.viewstate.MvpViewStateDelegateCallback;
import com.play.library_mvp.support.viewstate.ViewState;

public abstract class MvpViewStateFragment <V extends MvpView,
        P extends MvpPresenter<V>, VS extends ViewState<V>> extends MvpBaseFragment<V, P> implements MvpViewStateDelegateCallback<V, P, VS> {

    //特点：持有目标对象引用
    @Override
    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null){
            mvpDelegate = new FragmentMvpViewStateDelegateImpl<V, P, VS>(this, this, true, true);
        }
        return mvpDelegate;
    }

    protected VS viewState;

    //是否正在恢复
    private boolean restoringViewState = false;

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

}