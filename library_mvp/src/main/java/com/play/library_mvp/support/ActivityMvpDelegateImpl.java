package com.play.library_mvp.support;

import android.app.Activity;
import android.os.Bundle;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.manager.PresenterManager;

import java.util.UUID;
/**
 * Created by jhonjson on 2019/4/2
 *
 * @describe
 */
public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>> implements ActivityMvpDelegate<V, P> {

    private ProxyMvpDelegateCallback<V, P> proxyMvpCallback;

    public ActivityMvpDelegateImpl(Activity activity, MvpDelegateCallback<V, P> mvpCallback, boolean keepPresenterInstance){
        this.proxyMvpCallback = new ProxyMvpDelegateCallback<V, P>(mvpCallback);

        this.activity = activity;
        this.keepPresenterInstance = keepPresenterInstance;
    }

    protected Activity activity;
    //keepPresenterInstance表示：是否创建新的对象
    protected boolean keepPresenterInstance;
    private static final String KEY_VIEW_ID = "com.tz.dream.architect.mvp.framework.support.id";
    protected String viewId;

    private P getPresenter(){
        P presenter = proxyMvpCallback.getPresenter();
        if (presenter == null){
            throw new NullPointerException("presenter不能为空!!!");
        }
        return presenter;
    }

    private V getMvpView(){
        V view = proxyMvpCallback.getMvpView();
        if (view == null){
            throw new NullPointerException("view不能给为空!!!");
        }
        return view;
    }

    @Override
    public void onCreate(Bundle bundle) {
        //原始代码
//        this.proxyMvpCallback.createPresenter();
//        this.proxyMvpCallback.attachView();

        //优化代码->缓存P
        //缓存P层
        P presenter = null;
        if (bundle != null && keepPresenterInstance){
            viewId = bundle.getString(KEY_VIEW_ID);
            //说明存在
            if (viewId == null){
                //创建P层
                presenter = createViewIdAndCreatePresenter();
            }
            //否则不处理
        } else {
            //直接创建->说明没有缓存
            presenter = createViewIdAndCreatePresenter();
        }

        //绑定P
        proxyMvpCallback.setPresenter(presenter);
        //绑定V层
        getPresenter().attachView(getMvpView());
    }

    private P createViewIdAndCreatePresenter(){
        //缓存P
        P presenter = proxyMvpCallback.createPresenter();
        if (presenter == null){
            //如果为空，说明客户端实现类没有创建P对象
            throw new NullPointerException("presenter不能为空!!!");
        }
        if (keepPresenterInstance){
            //第一次进来都会创建
            viewId = UUID.randomUUID().toString();
            //缓存->PresenterManager
            PresenterManager.putPresenter(activity, viewId, presenter);
        }
        return presenter;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    //是否存在这个实例(不存在要销毁->返回false，存在返回true不需要销毁)
    protected static boolean retainPresenterInstance(boolean keepPresenterInstance, Activity activity){
        return keepPresenterInstance &&
                (activity.isChangingConfigurations() || !activity.isFinishing());
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onDestroy() {
        //原始代码
//        this.proxyMvpCallback.detachView();


        //优化代码
        boolean retainPresenterInstance = retainPresenterInstance(keepPresenterInstance, activity);
        getPresenter().detachView();

        //同时P层也需要销毁
        if (!retainPresenterInstance){
            getPresenter().destory();
        }

        if (!retainPresenterInstance && viewId != null){
            PresenterManager.remove(activity, viewId);
        }
    }
}