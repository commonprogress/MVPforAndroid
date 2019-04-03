package com.play.library_mvp.support.viewstate;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.MvpDelegateCallback;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public interface MvpViewStateDelegateCallback<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>> extends MvpDelegateCallback<V, P> {

    VS getViewState();

    void setViewState(VS viewState);

    VS createViewState();

    void setRestoringViewState(boolean restoringViewState);

    boolean isRestoringViewState();

    void onViewStateInstanceRestored(boolean instanceStateRetained);

    void onNewViewStateInstance();

}
