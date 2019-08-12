package com.play.module_login.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.library_base.utils.SharePreUtils;
import com.play.library_base.utils.ToastUtils;
import com.play.library_base.view.ClearEditText;
import com.play.library_mvp.base.common.BaseActivity;
import com.play.module_login.R;
import com.play.library_base.router.RouterActivityPath;
import com.play.module_login.bean.LoginBean;
import com.play.module_login.bean.RegisterBean;
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
    private ClearEditText cetName, cetPsd;
    private TextView tvLogin;
    private TextView tvRegister;

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

        cetName = findViewById(R.id.cetName);
        cetPsd = findViewById(R.id.cetPsd);
        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);


        tvLogin.setOnClickListener(View -> {
            getPresenter().login(cetName.getText().toString(), cetPsd.getText().toString());
        });
        tvRegister.setOnClickListener(View -> {
            ARouter.getInstance().build(RouterActivityPath.Login.PAGER_REGISTER)
                    .navigation();
        });

    }

    @Override
    public boolean isOpenImmersive() {
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void backRegisterSuc(RegisterBean mRegisterBean) {

    }

    @Override
    public void backLoginSuc(LoginBean mLoginBean) {
        SharePreUtils.setInteger("userid", mLoginBean.getId());
        SharePreUtils.setString("username", mLoginBean.getUsername());
        ARouter.getInstance().build(RouterActivityPath.Main.PAGER_MAIN)
                .navigation();
        finish();
    }

    @Override
    public void backRegisterFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void backLoginFail(String msg) {
        ToastUtils.showToast(this, msg);
    }

}
