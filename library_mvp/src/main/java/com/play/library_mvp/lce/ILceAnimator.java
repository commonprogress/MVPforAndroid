package com.play.library_mvp.lce;

import android.view.View;
/**
 * Created by jhonjson on 2019/4/2
 * @describe
 */
public interface ILceAnimator {

    /**
     * 加载页面
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showLoadingView(View loadingView, View contentView, View errorView);

    /**
     * 内容页面
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showContentView(View loadingView, View contentView, View errorView);

    /**
     * 错误页面
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showErrorView(View loadingView, View contentView, View errorView);
}
