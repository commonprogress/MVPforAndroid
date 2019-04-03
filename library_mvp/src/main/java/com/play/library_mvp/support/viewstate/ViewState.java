package com.play.library_mvp.support.viewstate;

import com.play.library_mvp.base.interfaces.MvpView;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public interface ViewState <V extends MvpView> {

    void apply(V view, boolean retained);

}
