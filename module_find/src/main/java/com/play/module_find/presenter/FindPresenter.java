package com.play.module_find.presenter;

import android.content.Context;

import com.play.module_find.contract.FindContract;


public class FindPresenter extends FindContract.Presenter {

    private Context context;

    public FindPresenter(Context context) {
        this.context = context;
    }


}
