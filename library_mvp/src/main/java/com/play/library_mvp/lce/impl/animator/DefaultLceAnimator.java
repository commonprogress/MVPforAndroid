package com.play.library_mvp.lce.impl.animator;

import android.view.View;

import com.play.library_mvp.lce.ILceAnimator;

/**
 * Created by jhonjson on 2019/4/3
 * @describe
 */
public class DefaultLceAnimator implements ILceAnimator {

    @Override
    public void showLoadingView(View loadingView, View contentView, View errorView) {
        AnimatorUtils.getInstance().showLoading(loadingView, contentView, errorView);
    }

    @Override
    public void showContentView(View loadingView, View contentView, View errorView) {
        AnimatorUtils.getInstance().showContent(loadingView, contentView, errorView);
    }

    @Override
    public void showErrorView(View loadingView, View contentView, View errorView) {
        AnimatorUtils.getInstance().showErrorView(loadingView, contentView, errorView);
    }

}