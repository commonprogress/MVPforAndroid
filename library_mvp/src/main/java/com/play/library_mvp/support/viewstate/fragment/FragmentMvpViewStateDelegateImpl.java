package com.play.library_mvp.support.viewstate.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.fragment.FragmentMvpDelegate;
import com.play.library_mvp.support.fragment.FragmentMvpDelegateImpl;
import com.play.library_mvp.support.manager.PresenterManager;
import com.play.library_mvp.support.viewstate.MvpViewStateDelegateCallback;
import com.play.library_mvp.support.viewstate.RestorableViewState;
import com.play.library_mvp.support.viewstate.ViewState;

import java.util.UUID;

public class FragmentMvpViewStateDelegateImpl <V extends MvpView,
        P extends MvpPresenter<V>, VS extends ViewState<V>>
        implements FragmentMvpDelegate<V, P> {

    protected static final String KEY_VIEW_ID = "com.tz.dream.architect.mvp.framework.id";

    //解决问题：横竖屏切换
    private Fragment fragment;
    private MvpViewStateDelegateCallback<V, P, VS> delegateCallback;
    //当屏幕方向(横竖屏切换)变化的时候是否缓存Presenter实例
    protected final boolean keepPresenterInstanceDuringScreenOrientationChanges;
    //当返回的时候是否缓存Presenter实例
    protected final boolean keepPresenterOnBackstack;
    private String viewId;

    //请求使用视图状态
    protected boolean applyViewState = false;
    //是否从内存中使用视图状态
    protected boolean applyViewStateFromMemory = false;

    //是否创建视图回调
    private boolean onViewCreatedCalled = false;

    //1、初始化参数
    public FragmentMvpViewStateDelegateImpl(Fragment fragment,
                                            MvpViewStateDelegateCallback<V, P, VS> delegateCallback,
                                            boolean keepPresenterInstanceDuringScreenOrientationChanges,
                                            boolean keepPresenterOnBackstack){
        if (delegateCallback == null){
            throw new NullPointerException("delegateCallback not null");
        }
        if (fragment == null){
            throw new NullPointerException("fragment not null");
        }
        if (!keepPresenterInstanceDuringScreenOrientationChanges && keepPresenterOnBackstack){
            //有一个冲突问题
            //keepPresenterInstanceDuringScreenOrientationChanges
            //false: 当横竖屏切换我不缓存Presenter
            //keepPresenterOnBackstack
            //true: 表示按下返回建，缓存Presenter
            throw new IllegalArgumentException("参数异常...");
        }

        this.fragment = fragment;
        this.delegateCallback = delegateCallback;
        this.keepPresenterInstanceDuringScreenOrientationChanges = keepPresenterInstanceDuringScreenOrientationChanges;
        this.keepPresenterOnBackstack = keepPresenterOnBackstack;
    }

    //2、通过生命周期方法->控制View状态和数据缓存（恢复）
    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //当Activity创建的时候
        if (savedInstanceState != null && keepPresenterInstanceDuringScreenOrientationChanges){
            //缓存->读取数据->恢复
            //缓存：P层（Presenter）和V层（数据->Bundle）
            viewId = savedInstanceState.getString(KEY_VIEW_ID);
            //读取缓存P层->恢复
            P presneter = restorePresenterOrRecreateNewPresenterAfterProcessDeath();
            delegateCallback.setPresenter(presneter);
            //读取缓存V层（数据）
            VS viewState = restoreViewStateOrRecreateViewStateAfterProcessDeath(savedInstanceState);
            delegateCallback.setViewState(viewState);
        } else {
            //重新创建
            P presenter = createViewIdAndPresenter();
            delegateCallback.setPresenter(presenter);
            VS viewState = createViewState();
            delegateCallback.setViewState(viewState);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        onViewCreatedCalled = true;
    }

    @Override
    public void onStart() {
        if (!onViewCreatedCalled) {
            throw new IllegalArgumentException("必需要进行回调...");
        }
        //准备数据
        //MVP回调是有顺序的
        if (applyViewState){
            //是否现实这个视图
            VS viewState = delegateCallback.getViewState();
            V mvpView = delegateCallback.getMvpView();
            if (viewState == null){
                throw new NullPointerException("ViewState not null");
            }
            //恢复
            delegateCallback.setRestoringViewState(true);
            viewState.apply(mvpView, applyViewStateFromMemory);
            delegateCallback.setRestoringViewState(false);
        }

        //绑定V层
        delegateCallback.getPresenter().attachView(delegateCallback.getMvpView());
        if (applyViewState){
            if (!applyViewStateFromMemory && keepPresenterInstanceDuringScreenOrientationChanges){
                //applyViewStateFromMemory
                //false:表示缓存中不存在数据
                //keepPresenterInstanceDuringScreenOrientationChanges
                //true:表示横竖屏切换需要缓存
                //说白了：内存中不存在缓存数据，这个时候我们需要缓存新的数据
                if (viewId == null){
                    throw new IllegalArgumentException("ViewId not null...");
                }
                PresenterManager.putViewState(getActivity(), viewId, delegateCallback.getViewState());
            }
            delegateCallback.onViewStateInstanceRestored(applyViewStateFromMemory);
        }else {
            delegateCallback.onNewViewStateInstance();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {
        delegateCallback.getPresenter().detachView();
        if (keepPresenterInstanceDuringScreenOrientationChanges){
            applyViewState = true;
            applyViewStateFromMemory = true;
        } else {
            applyViewState = false;
            applyViewStateFromMemory = false;
        }
    }

    @Override
    public void onDestroyView() {
        onViewCreatedCalled = false;
    }

    @Override
    public void onDestroy() {
        Activity activity = getActivity();
        boolean retainPresenterInstance = FragmentMvpDelegateImpl.retainPresenterInstance(activity, fragment, keepPresenterInstanceDuringScreenOrientationChanges, keepPresenterOnBackstack);
        P presenter = delegateCallback.getPresenter();
        //是否保留数据
        if (!retainPresenterInstance){
            //false：表示需要销毁，不在使用了
            presenter.destory();
        }

        //清空缓存
        if (!retainPresenterInstance && viewId != null){
            PresenterManager.remove(activity, viewId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if  ( (keepPresenterInstanceDuringScreenOrientationChanges || keepPresenterOnBackstack) && outState != null ){
            //缓存
            outState.putString(KEY_VIEW_ID, viewId);
        }

        boolean retainPresenterInstance = FragmentMvpDelegateImpl.retainPresenterInstance(getActivity(), fragment, keepPresenterInstanceDuringScreenOrientationChanges, keepPresenterOnBackstack);
        VS viewState = delegateCallback.getViewState();
        if (viewState == null){
            throw new NullPointerException("view state not null...");
        }

        if (retainPresenterInstance && viewState instanceof RestorableViewState){
            //保存到->序列号保存
            //通过数据...
            ((RestorableViewState) viewState).onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDetach() {

    }

    private Activity getActivity() {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new NullPointerException(
                    "Activity returned by Fragment.getActivity() is null. Fragment is " + fragment);
        }

        return activity;
    }

    //恢复Presenter或者在进程死亡之后重新创建Presenter
    private P restorePresenterOrRecreateNewPresenterAfterProcessDeath() {
        P presenter;
        if (keepPresenterInstanceDuringScreenOrientationChanges) {
            if (viewId != null
                    && (presenter = PresenterManager.getPresenter(getActivity(), viewId)) != null) {
                return presenter;
            }
        }
        presenter = createViewIdAndPresenter();
        return presenter;
    }

    private P createViewIdAndPresenter() {
        P presenter = delegateCallback.createPresenter();
        if (presenter == null) {
            throw new NullPointerException(
                    "Presenter returned from createPresenter() is null. Fragment is " + fragment);
        }

        if (keepPresenterInstanceDuringScreenOrientationChanges) {
            viewId = UUID.randomUUID().toString();
            PresenterManager.putPresenter(getActivity(), viewId, presenter);
        }
        return presenter;
    }


    private VS restoreViewStateOrRecreateViewStateAfterProcessDeath(Bundle bundle) {

        if (bundle == null) {
            throw new NullPointerException("Bundle is null. This should never be the case"
                    + "Please report this issue at https://github.com/sockeqwe/mosby/issues");
        }

        if (viewId == null) {
            throw new NullPointerException(
                    "The (internal) Mosby View id is null although bundle is not null. "
                            + "This should never be the case while restoring ViewState instance. "
                            + "Please report this issue at https://github.com/sockeqwe/mosby/issues");
        }

        VS viewState = PresenterManager.getViewState(fragment.getActivity(), viewId);
        if (viewState != null) {
            applyViewState = true;
            applyViewStateFromMemory = true;
            return viewState;
        }

        viewState = delegateCallback.createViewState();
        if (viewState == null) {
            throw new NullPointerException(
                    "ViewState returned from createViewState() is null! MvpView that has returned null as ViewState is: "
                            + delegateCallback.getMvpView());
        }

        if (viewState instanceof RestorableViewState) {
            // A little bit hacky that we need an instance of the viewstate to restore a view state
            // (may creates another view state object) but I don't know any better way :)
            RestorableViewState restoredViewState =
                    ((RestorableViewState) viewState).onRestoreInstanceState(bundle);

            if (restoredViewState != null) {
                //
                // ViewState restored from bundle
                //
                viewState = (VS) restoredViewState;
                applyViewState = true;
                applyViewStateFromMemory = false;

                if (keepPresenterInstanceDuringScreenOrientationChanges) {
                    PresenterManager.putViewState(getActivity(), viewId, viewState);
                }
                return viewState;
            }
        }

        //
        // Entirely new ViewState has been created, typically because process death and mosby view id points to
        // a  old id but view got a new one because of process death.
        //

        applyViewState = false;
        applyViewStateFromMemory = false;

        if (keepPresenterInstanceDuringScreenOrientationChanges) {
            PresenterManager.putViewState(getActivity(), viewId, viewState);
        }
        return viewState;
    }

    private VS createViewState() {
        VS viewState = delegateCallback.createViewState();
        if (viewState == null) {
            throw new NullPointerException(
                    "ViewState returned from createViewState() is null. Fragment is " + fragment);
        }

        if (keepPresenterInstanceDuringScreenOrientationChanges) {
            PresenterManager.putViewState(getActivity(), viewId, viewState);
        }

        return viewState;
    }

}

