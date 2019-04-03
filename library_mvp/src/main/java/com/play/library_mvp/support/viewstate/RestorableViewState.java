package com.play.library_mvp.support.viewstate;

import android.os.Bundle;

import com.play.library_mvp.base.interfaces.MvpView;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public interface RestorableViewState <V extends MvpView> extends ViewState<V> {

    void onSaveInstanceState(Bundle outState);

    RestorableViewState<V> onRestoreInstanceState(Bundle inState);

}