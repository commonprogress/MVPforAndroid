package com.play.module_login.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.play.library_mvp.base.common.BaseFragment;
import com.play.library_mvp.base.common.BaseLazyFragment;
import com.play.module_login.R;
import com.play.module_login.contract.LoginContract;
import com.play.module_login.presenter.LoginPresenter;

/**
 * @author：jhonjson
 * @data：2019/6/28 下午16:40
 * @描述: 登陆
 */

public class LoginFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_login_layout;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(mContext);
    }

    @Override
    public LoginContract.View createView() {
        return null;
    }

    @Override
    public void init() {
//        ARouter.getInstance().build("/play/activity").withString("title", "这是跨组件化跳转过来的").navigation();
    }

}
