package com.play.library_mvp.base.common;

import android.os.Bundle;

import com.play.library_base.base.BaseContainerActivity;
import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.ActivityMvpDelegate;
import com.play.library_mvp.support.ActivityMvpDelegateImpl;
import com.play.library_mvp.support.MvpDelegateCallback;

/**
 * Created by jhonjson on 2019/4/2
 * E-mail:824483029@qq.com
 */
public class MvpBaseActivity <V extends MvpView, P extends MvpPresenter<V>>
        extends BaseContainerActivity implements MvpView, MvpDelegateCallback<V, P> {

    //目标对象
    protected ActivityMvpDelegate<V, P> mvpDelegate;

    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null){
            this.mvpDelegate = new ActivityMvpDelegateImpl<V, P>(this, this, true);
        }
        return mvpDelegate;
    }


    /***********第二重代理****start*******/
    private P presenter;

    @Override
    public P createPresenter() {
        return presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V)this;
    }
    /************end**********/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

}
