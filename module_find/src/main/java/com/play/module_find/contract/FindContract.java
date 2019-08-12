package com.play.module_find.contract;

import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;

public interface FindContract {

    interface View extends BaseViewImp {


    }

    abstract class Presenter extends BasePresenter<View> {

    }

}
