package com.play.library_mvp.support.viewstate.lce;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.lce.MvpLceView;
import com.play.library_mvp.lce.impl.fragment.MvpLceFragment;
import com.play.library_mvp.support.fragment.FragmentMvpDelegate;
import com.play.library_mvp.support.viewstate.MvpViewStateDelegateCallback;
import com.play.library_mvp.support.viewstate.fragment.FragmentMvpViewStateDelegateImpl;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public abstract class MvpLceViewStateFragment <D, V extends MvpLceView<D>, P extends MvpPresenter<V>>
        extends MvpLceFragment<D, V, P> implements MvpLceView<D>, MvpViewStateDelegateCallback<V, P, LceViewState<D, V>> {

    //持有目标对象引用
    @Override
    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null){
            mvpDelegate = new FragmentMvpViewStateDelegateImpl<>(this, this, true, true);
        }
        return mvpDelegate;
    }

    //-----------------//
    //集成ViewState功能
    protected LceViewState<D, V> viewState;
    //是否正在恢复
    private boolean restoringViewState = false;

    @Override
    public LceViewState<D, V> getViewState() {
        return viewState;
    }

    @Override
    public void setViewState(LceViewState<D, V> viewState) {
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

    //-----------------//


    //------集成LCE实现------//

    @Override
    public void showContent() {
        super.showContent();
        viewState.setStateShowContent(getData());
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        viewState.setStateShowError(e, pullToRefresh);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        viewState.setStateShowLoading(pullToRefresh);
    }

    @Override
    public void onNewViewStateInstance() {
        loadData(false);
    }

    @Override
    public void onViewStateInstanceRestored(boolean instanceStateRetained) {
        if (!instanceStateRetained && viewState.isLoadingState()){
            //不是恢复状态，并且是需要加载状态
            loadData(viewState.isPullToRefreshLoadingState());
        }
    }

    public abstract D getData();
    //-----------------//
}
