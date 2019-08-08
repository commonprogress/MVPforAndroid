package com.play.module_login.contract;

import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;

public interface LoginContract {

    interface View extends BaseViewImp {

    }

   abstract class Presenter extends BasePresenter<View> {

       public abstract void register(String username, String password, String repassword);

       public abstract void login(String username, String password);
    }

}
