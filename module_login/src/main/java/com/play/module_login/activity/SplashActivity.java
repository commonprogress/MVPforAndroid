package com.play.module_login.activity;

import android.content.Intent;
import android.os.Handler;

import com.play.library_mvp.base.common.BaseActivity;
import com.play.module_login.R;
import com.play.module_login.contract.SplashContract;
import com.play.module_login.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity<SplashContract.View,
        SplashContract.Presenter> implements SplashContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }




    @Override
    public SplashContract.Presenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public SplashContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        }, 3 * 1000);
    }


    /**
     * 进入主页面
     */
    private void goMain() {

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
