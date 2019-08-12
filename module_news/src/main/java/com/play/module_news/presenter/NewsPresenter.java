package com.play.module_news.presenter;

import android.content.Context;

import com.play.module_news.contract.NewsContract;


public class NewsPresenter extends NewsContract.Presenter {

    private Context context;

    public NewsPresenter(Context context) {
        this.context = context;
    }


}
