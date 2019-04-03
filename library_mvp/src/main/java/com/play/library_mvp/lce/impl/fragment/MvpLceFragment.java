package com.play.library_mvp.lce.impl.fragment;

import android.os.Bundle;
import android.view.View;

import com.play.library_mvp.base.common.MvpBaseFragment;
import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.lce.ILceAnimator;
import com.play.library_mvp.lce.MvpLceView;
import com.play.library_mvp.lce.impl.MvpLceViewImpl;

/**
 * Created by jhonjson on 2019/4/3
 * @describe
 */
public class MvpLceFragment<D, V extends MvpLceView<D>, P extends MvpPresenter<V>>
        extends MvpBaseFragment<V, P> implements MvpLceView<D> {

    // 初始化Lce UI布局（规定你的Lce布局文件的id）
    private MvpLceViewImpl<D> lceViewImpl;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lceViewImpl == null) {
            lceViewImpl = new MvpLceViewImpl<D>();
        }
        initLceView(view);
    }

    private void initLceView(View v) {
        lceViewImpl.initView(v);
        lceViewImpl.setOnClickErrorListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onErrorClick();
            }
        });
    }

    public void setLceAnimator(ILceAnimator lceAnimator) {
        lceViewImpl.setAnimator(lceAnimator);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        lceViewImpl.showLoading(pullToRefresh);
    }

    @Override
    public void showContent() {
        lceViewImpl.showContent();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        lceViewImpl.showError(e, pullToRefresh);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        lceViewImpl.loadData(pullToRefresh);
    }

    @Override
    public void bindData(D data) {
        lceViewImpl.bindData(data);
    }

    public void onErrorClick() {
        loadData(false);
    }

}