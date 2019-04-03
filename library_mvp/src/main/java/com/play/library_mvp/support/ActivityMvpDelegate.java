package com.play.library_mvp.support;

import android.os.Bundle;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;
/**
 * Created by jhonjson on 2019/4/2
 *
 * @describe
 */
public interface ActivityMvpDelegate <V extends MvpView, P extends MvpPresenter<V>> {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    //增加数据缓存生命周期方法
    void onSaveInstanceState(Bundle outState);

    void onPostCreate(Bundle savedInstanceState);

}