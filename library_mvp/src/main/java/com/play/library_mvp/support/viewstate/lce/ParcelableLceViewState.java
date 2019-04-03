package com.play.library_mvp.support.viewstate.lce;

import com.play.library_mvp.lce.MvpLceView;

/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public interface ParcelableLceViewState <D, V extends MvpLceView<D>>
        extends RestorableParcelableViewState<V>, LceViewState<D, V> {
}
