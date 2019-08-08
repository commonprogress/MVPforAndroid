package com.play.module_login.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.library_mvp.base.common.BaseActivity;
import com.play.module_login.R;
import com.play.library_base.router.RouterActivityPath;
import com.play.module_login.contract.LoginContract;
import com.play.module_login.presenter.LoginPresenter;

/**
 * @author：jhonjson
 * @data：2019/6/28 下午16:40
 * @描述: 登陆
 */
@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements
        LoginContract.View {

    @Autowired(name = "title")
    String title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        ARouter.getInstance().inject(this);

        findViewById(R.id.content_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getPresenter().login("jhonjson","qs110134");
                getPresenter().register("jhonjson12345", "qs110134", "qs110134");
            }
        });
    }
}
