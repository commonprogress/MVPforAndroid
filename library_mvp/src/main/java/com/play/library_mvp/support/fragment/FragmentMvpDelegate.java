package com.play.library_mvp.support.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;

/**
 * Created by jhonjson on 2019/4/2
 * @describe
 */
public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {
    void onAttach(Context context);

    void onCreate(Bundle savedInstanceState);

    void onActivityCreated(Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onStart();

    void onPause();

    void onResume();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    void onDetach();
}
