
package com.play.module_login.presenter;


import android.content.Context;
import android.util.Log;

import com.play.library_mvp.base.common.BasePresenter;
import com.play.module_login.contract.LoginContract;

import static com.play.module_login.contract.LoginContract.*;
//import com.play.module_login.model.LoginModelRequt;

public class LoginPresenter extends LoginContract.Presenter{

    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }
//    LoadingObserver<LoginBean> observer;
//    LoadingObserver<RegisterBean> registerobserver;

    @Override
    public void register(String username, String password, String repassword) {
//        registerobserver = new LoadingObserver<RegisterBean>() {
//            @Override
//            protected void onSuccess(RegisterBean data) {
//
//            }
//        };
//        LoginModelRequt.getInstance().register(username, username, repassword,registerobserver);
    }

    //登陆
    @Override
    public void login(String username, String password) {
//        Log.e("login", "login");
//        observer = new LoadingObserver<LoginBean>() {
//            @Override
//            protected void onSuccess(LoginBean data) {
//
//            }
//        };
//        LoginModelRequt.getInstance().login(username, username, observer);
    }
}

