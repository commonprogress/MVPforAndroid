package com.play.module_main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.play.library_base.moduleinterface.ModuleMaster;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_base.utils.ToastUtils;
import com.play.library_base.view.ClearEditText;
import com.play.library_mvp.base.common.BaseActivity;
import com.play.module_main.R;
import com.play.module_main.bean.LoginBean;
import com.play.module_main.bean.RegisterBean;
import com.play.module_main.contract.LoginContract;
import com.play.module_main.presenter.LoginPresenter;

/**
 * @author：jhonjson
 * @data：2019/6/28 下午16:40
 * @描述: 登陆
 */
@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements
        LoginContract.View, View.OnClickListener {

    @Autowired(name = "title")
    String title;
    private ClearEditText cetName, cetPsd;
    private TextView tvLogin;

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


//        ARouter.getInstance().build("/play/activity").withString("title", "这是跨组件化跳转过来的").navigation();
        findViewById(R.id.tvLogin).setOnClickListener(this);
//        getPresenter().register("jhonjson12345", "qs110134", "qs110134");

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
//        ModuleMaster.getInstance().getModuleNativeModule().startMainActivity(this);

        startActivity(new Intent(this, MainActivity.class));
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.tvLogin == id) {
            getPresenter().login("jhonjson", "qs110134");
        }
    }
}
