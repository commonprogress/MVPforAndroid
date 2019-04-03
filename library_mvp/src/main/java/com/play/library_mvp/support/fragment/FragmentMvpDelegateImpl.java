package com.play.library_mvp.support.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.BackstackAccessor;
import android.support.v4.app.Fragment;
import android.view.View;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.MvpDelegateCallback;
import com.play.library_mvp.support.ProxyMvpDelegateCallback;

/**
 * Created by jhonjson on 2019/4/2
 * @describe
 */
public class FragmentMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        implements FragmentMvpDelegate<V, P> {

    private ProxyMvpDelegateCallback<V, P> proxy;

    public FragmentMvpDelegateImpl(
            MvpDelegateCallback<V, P> delegateCallback) {
        this.proxy = new ProxyMvpDelegateCallback<V, P>(delegateCallback);
    }

    public static boolean retainPresenterInstance(Activity activity, Fragment fragment,
                                                  boolean keepPresenterInstanceDuringScreenOrientationChanges,
                                                  boolean keepPresenterOnBackstack) {
        if (activity.isChangingConfigurations()) {
            return keepPresenterInstanceDuringScreenOrientationChanges;
        }
        if (activity.isFinishing()) {
            return false;
        }
        if (keepPresenterOnBackstack && BackstackAccessor.isFragmentOnBackStack(fragment)) {
            return true;
        }
        return !fragment.isRemoving();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.proxy.createPresenter();
        this.proxy.attachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {
        this.proxy.detachView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onDetach() {

    }
}
