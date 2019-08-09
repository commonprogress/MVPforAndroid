package com.play.module_main.contract;

import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;

public interface SplashContract {

    interface View extends BaseViewImp {

    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void geSplashData();
    }

}
