package com.play.library_mvp.support;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
/**
 * Created by jhonjson on 2019/4/2
 * @describe
 */
public class ProxyMvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> implements MvpDelegateCallback<V, P> {

    //持有目标对象引用（必需）->mvpCallback（实际上-MvpActivity）
    private MvpDelegateCallback<V, P> mvpCallback;

    public ProxyMvpDelegateCallback(MvpDelegateCallback<V, P> mvpCallback) {
        this.mvpCallback = mvpCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = this.mvpCallback.getPresenter();
        if (presenter == null) {
            presenter = this.mvpCallback.createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("P层不能为空");
        }
        this.mvpCallback.setPresenter(presenter);
        return presenter;
    }

    @Override
    public P getPresenter() {
        return this.mvpCallback.getPresenter();
    }

    @Override
    public void setPresenter(P presenter) {
        this.mvpCallback.setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        return this.mvpCallback.getMvpView();
    }

    //添加两个重要的方法
    public void attachView() {
        getPresenter().attachView(getMvpView());
    }

    public void detachView() {
        getPresenter().detachView();
    }

}

