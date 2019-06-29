package com.play.module_main.presenter;

import android.util.Log;

import com.play.library_mvp.base.common.MvpBasePresenter;
import com.play.module_main.contract.SplashContract;

public class SplashPresenter extends MvpBasePresenter<SplashContract.View>
        implements SplashContract.Presenter {


    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);

    }

    @Override
    public void getList() {

        Log.e("getList", "getList 请求数据  getList");
    }
}
