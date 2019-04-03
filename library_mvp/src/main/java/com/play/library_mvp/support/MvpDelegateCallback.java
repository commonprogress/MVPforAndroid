package com.play.library_mvp.support;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
/**
 * Created by jhonjson on 2019/4/2
 *
 * @describe
 */
public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();

}
