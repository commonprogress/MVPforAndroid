package com.play.library_mvp.lce;

import com.play.library_mvp.base.interfaces.MvpView;

/**
 * Created by jhonjson on 2019/4/2
 * @describe LCE设计->代理模式->抽象统一动画->回调UI层
 */
public interface MvpLceView<D> extends MvpView {

    /**
     * 1、加载数据
     * @param isPullToRefresh->是否是下拉刷新组件
     */
    void loadData(boolean isPullToRefresh);

    /**
     * 2、显示加载页面
     * @param isPullToRefresh
     */
    void showLoading(boolean isPullToRefresh);

    /**
     * 3、成功->更新UI层（显示内容View）
     */
    void showContent();

    /**
     * 4、绑定数据
     */
    void bindData(D data);

    /**
     * 5、失败->显示错误页面
     */
    void showError(Throwable e, boolean pullToRefresh);
}
