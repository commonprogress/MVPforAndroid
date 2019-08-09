package com.play.library_mvp.base.common;


import android.content.Context;

import com.play.library_net.factory.RetrofitClient;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * BasePresenter
 */
public abstract class BasePresenter<V extends BaseViewImp> {

    protected WeakReference<V> viewRef;
    // 管理订阅关系，用于取消订阅
    protected CompositeDisposable compositeDisposable;

    protected Context mContext;


    public V getView() {
        return viewRef.get();
    }

    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
        V v = viewRef.get();
    }

    public void detachView() {
        this.viewRef = null;
        unsubscribe();
    }

    /**
     * 添加订阅
     */
    public void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        BaseObserver baseObserver = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
        compositeDisposable.add(baseObserver);
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    protected <T> T create(Class<T> clazz) {
        return RetrofitClient.getInstance().getRetrofit().create(clazz);
    }

}
