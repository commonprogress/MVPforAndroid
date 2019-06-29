package com.play.module_main.contract;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;

public interface SplashContract {

    interface View extends MvpView {


    }

    interface Presenter extends MvpPresenter<View> {

        void getList();
    }

}
