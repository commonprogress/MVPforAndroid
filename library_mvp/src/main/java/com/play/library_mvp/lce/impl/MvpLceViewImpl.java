package com.play.library_mvp.lce.impl;

import android.view.View;

import com.play.library_mvp.R;
import com.play.library_mvp.lce.ILceAnimator;
import com.play.library_mvp.lce.MvpLceView;
import com.play.library_mvp.lce.impl.animator.DefaultLceAnimator;

/**
 * Created by jhonjson on 2019/4/3
 * @describe
 */
public class MvpLceViewImpl<D> implements MvpLceView<D> {

    //处理动画
    private ILceAnimator animator;

    public void setAnimator(ILceAnimator animator){
        this.animator = animator;
    }

    private ILceAnimator getAnimator() {
        if (this.animator == null){
            this.animator = new DefaultLceAnimator();
        }
        return animator;
    }

    //加载View
    private View contentView;
    private View loadingView;
    private View errorView;

    public void initView(View rootView){
        if (rootView == null){
            throw new NullPointerException("root view 不能给为空");
        }
        if (loadingView == null){
            this.loadingView = rootView.findViewById(R.id.loadingView);
        }
        if (contentView == null){
            this.contentView = rootView.findViewById(R.id.contentView);
        }
        if (errorView == null){
            this.errorView = rootView.findViewById(R.id.errorView);
        }
        if (loadingView == null){
            throw new NullPointerException("loadingView 不能给为空");
        }
        if (contentView == null){
            throw new NullPointerException("contentView 不能给为空");
        }
        if (errorView == null){
            throw new NullPointerException("errorView 不能给为空");
        }
    }

    public void setOnClickErrorListener(View.OnClickListener onClickListener){
        if (this.errorView != null){
            this.errorView.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void loadData(boolean isPullToRefresh) {

    }

    @Override
    public void showLoading(boolean isPullToRefresh) {
        if (!isPullToRefresh){
            getAnimator().showLoadingView(loadingView, contentView, errorView);
        }
    }

    @Override
    public void showContent() {
        getAnimator().showContentView(loadingView, contentView, errorView);
    }

    @Override
    public void bindData(D data) {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        getAnimator().showErrorView(loadingView, contentView, errorView);
    }
}
