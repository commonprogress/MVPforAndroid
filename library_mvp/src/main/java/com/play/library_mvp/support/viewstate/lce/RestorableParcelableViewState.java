package com.play.library_mvp.support.viewstate.lce;

import android.os.Parcelable;

import com.play.library_mvp.base.interfaces.MvpView;
import com.play.library_mvp.support.viewstate.RestorableViewState;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public interface RestorableParcelableViewState <V extends MvpView>
        extends RestorableViewState<V>, Parcelable {

}

