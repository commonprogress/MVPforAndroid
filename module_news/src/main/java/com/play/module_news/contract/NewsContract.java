package com.play.module_news.contract;

import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;

public interface NewsContract {

    interface View extends BaseViewImp {


    }

    abstract class Presenter extends BasePresenter<View> {

    }

}
