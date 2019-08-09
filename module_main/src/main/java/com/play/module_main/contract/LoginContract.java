package com.play.module_main.contract;

import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;
import com.play.module_main.bean.LoginBean;
import com.play.module_main.bean.RegisterBean;

public interface LoginContract {

    interface View extends BaseViewImp {

        void backRegisterSuc(RegisterBean mRegisterBean);
        void backLoginSuc(LoginBean mLoginBean);

        void backRegisterFail(String msg);
        void backLoginFail(String msg);
    }

   abstract class Presenter extends BasePresenter<View> {

       public abstract void register(String username, String password, String repassword);

       public abstract void login(String username, String password);
    }

}
