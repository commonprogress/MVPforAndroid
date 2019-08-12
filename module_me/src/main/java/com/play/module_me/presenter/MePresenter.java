package com.play.module_me.presenter;

import android.content.Context;

import com.play.module_me.contract.MeContract;


public class MePresenter extends MeContract.Presenter {

    private Context context;

    public MePresenter(Context context) {
        this.context = context;
    }


}
