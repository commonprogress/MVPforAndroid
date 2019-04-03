package com.play.library_mvp.base.interfaces;

/**
 * Created by jhonjson on 2019/4/2
 * E-mail:824483029@qq.com
 */

//抽象中介者
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    //销毁功能(例如：网络请求停止、数据库查询停止等等...)
    void destory();

}
