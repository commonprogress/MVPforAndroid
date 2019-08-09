
package com.play.module_main.presenter;


import android.content.Context;

import com.play.library_mvp.base.common.BaseObserver;
import com.play.module_main.apiservice.LoginApiService;
import com.play.module_main.bean.LoginBean;
import com.play.module_main.bean.RegisterBean;
import com.play.module_main.contract.LoginContract;

public class LoginPresenter extends LoginContract.Presenter {

    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }


    //注册
    @Override
    public void register(String username, String password, String repassword) {
        addSubscribe(create(LoginApiService.class).register(username, password, password), new BaseObserver<RegisterBean>(getView()) {
            @Override
            protected void onSuccess(RegisterBean data) {
               getView().backRegisterSuc(data);
            }

            @Override
            protected void onFail(Throwable data) {
                getView().backRegisterFail(data.getMessage());
            }

        });
    }

    //登陆
    @Override
    public void login(String username, String password) {

        addSubscribe(create(LoginApiService.class).login(username, password), new BaseObserver<LoginBean>(getView()) {
            @Override
            protected void onSuccess(LoginBean data) {
                getView().backLoginSuc(data);
            }

            @Override
            protected void onFail(Throwable data) {
                getView().backLoginFail(data.getMessage());
            }

        });
    }
}

