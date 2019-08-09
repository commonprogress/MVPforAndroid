package com.play.module_main.model;/*
package com.play.module_login.model;

import com.play.library_net.base.BaseModel;
import com.play.library_net.base.BaseServiceHolder;
import com.play.module_login.apiservice.LoginApiService;
import com.play.module_login.bean.LoginBean;
import com.play.module_login.bean.RegisterBean;

import io.reactivex.Observable;

public class LoginModelRequt extends BaseModel {

    private static LoginModelRequt instance;

    public static synchronized LoginModelRequt getInstance() {
        if (instance == null) {
            instance = new LoginModelRequt();
        }

        return instance;
    }

    public static void setInstanceNull() {
        instance = null;
    }

    private LoginModelRequt() {
    }

    private LoginApiService getService() {
        return BaseServiceHolder.getInstance().getService(LoginApiService.class);
    }


    */
/**
     * 注册
     *
     * @param observer
     *//*

    public void register(String username, String password,
                         String repassword, LoadingObserver<RegisterBean> observer) {
        Observable observable = getService().register(username, password, repassword)
                .map(new ResultFunc<RegisterBean>());
        makeSubscribe(observable, observer);
    }

    */
/**
     * 登陆
     *
     * @param observer
     *//*

    public void login(String username, String password, LoadingObserver<LoginBean> observer) {
        Observable observable = getService().login(username, password)
                .map(new ResultFunc<LoginBean>());
        makeSubscribe(observable, observer);
    }


}
*/
