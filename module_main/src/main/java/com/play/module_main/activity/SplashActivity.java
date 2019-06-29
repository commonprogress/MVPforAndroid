package com.play.module_main.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.play.library_mvp.base.common.MvpBaseActivity;
import com.play.module_main.contract.SplashContract;
import com.play.module_main.presenter.SplashPresenter;

public class SplashActivity extends MvpBaseActivity<SplashContract.View,
        SplashContract.Presenter> implements SplashContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        }, 3 * 1000);

    }

    @Override
    public SplashContract.Presenter getPresenter() {
        return new SplashPresenter();
    }

    /**
     * 进入主页面
     */
    private void goMain() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
