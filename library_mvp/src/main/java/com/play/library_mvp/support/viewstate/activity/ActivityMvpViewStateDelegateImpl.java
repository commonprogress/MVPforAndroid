package com.play.library_mvp.support.viewstate.activity;

import android.app.Activity;
import android.os.Bundle;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.ActivityMvpDelegateImpl;
import com.play.library_mvp.support.manager.PresenterManager;
import com.play.library_mvp.support.viewstate.MvpViewStateDelegateCallback;
import com.play.library_mvp.support.viewstate.RestorableViewState;
import com.play.library_mvp.support.viewstate.ViewState;

public class ActivityMvpViewStateDelegateImpl <V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>> extends ActivityMvpDelegateImpl<V, P> {

    private MvpViewStateDelegateCallback<V, P, VS> delegateCallback;

    public ActivityMvpViewStateDelegateImpl(Activity activity, MvpViewStateDelegateCallback<V, P, VS> delegateCallback, boolean keepPresenterInstance){
        super(activity, delegateCallback, keepPresenterInstance);
        this.delegateCallback = delegateCallback;
    }

    private void setViewState(VS viewState, boolean applyViewState, boolean applyViewStateFromMemory){
        if (viewState == null){
            throw new NullPointerException("viewState不能空");
        }
        delegateCallback.setViewState(viewState);

        if (applyViewState){
            //恢复
            delegateCallback.setRestoringViewState(true);
            //调用具体的备份方法(由子类决定)
            delegateCallback.getViewState().apply(delegateCallback.getMvpView(), applyViewStateFromMemory);
            delegateCallback.setRestoringViewState(false);
            delegateCallback.onViewStateInstanceRestored(applyViewStateFromMemory);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean keepInstance = retainPresenterInstance(keepPresenterInstance, activity);
        VS viewState = delegateCallback.getViewState();
        if (viewState == null){
            throw new NullPointerException("viewState不能空");
        }

        //存在P层，并且viewState是恢复状态，那么需要缓存
        if (keepInstance && viewState instanceof RestorableViewState){
            ((RestorableViewState)viewState).onSaveInstanceState(outState);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        VS viewState = null;
        if (viewId != null){
            viewState = PresenterManager.getViewState(activity, viewId);
            if (viewState != null){
                //缓存
                setViewState(viewState, true, true);
                return;
            }
        }

        //如果为空创建一个ViewState
        viewState = delegateCallback.createViewState();
        if (viewState == null){
            throw new NullPointerException("你没有创建viewState，需要创建");
        }

        if (savedInstanceState != null && viewState instanceof RestorableViewState){
            RestorableViewState restorableViewState = (RestorableViewState)viewState;
            restorableViewState.onRestoreInstanceState(savedInstanceState);

            if (restorableViewState != null){
                viewState = (VS) restorableViewState;
                setViewState(viewState, true, false);
                if (keepPresenterInstance){
                    if (viewId == null){
                        throw new NullPointerException("viewID不能为空");
                    }
                    PresenterManager.putViewState(activity, viewId, viewState);
                }
                return;
            }
        }


        if (keepPresenterInstance){
            if (viewId == null){
                throw new NullPointerException("viewID不能为空");
            }
            PresenterManager.putViewState(activity, viewId, viewState);
        }

        //恢复完毕
        setViewState(viewState, false, false);

        //创建新的缓存实例对象为下一次备份使用
        delegateCallback.onNewViewStateInstance();
    }

}
