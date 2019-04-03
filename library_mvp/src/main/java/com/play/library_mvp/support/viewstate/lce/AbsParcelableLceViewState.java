package com.play.library_mvp.support.viewstate.lce;

import android.os.Bundle;
import android.os.Parcel;

import com.play.library_mvp.lce.MvpLceView;
/**
 * Created by jhonjson on 2019/4/3
 * E-mail:824483029@qq.com
 */
public class AbsParcelableLceViewState <D, V extends MvpLceView<D>>
        extends AbsLceViewState<D, V> implements ParcelableLceViewState<D, V> {

    public static final String KEY_BUNDLE_VIEW_STATE =
            "com.tz.dream.architect.mvp.framework.support.viewstate.lce.bundlekey";

    private Parcel in;

    public AbsParcelableLceViewState() {

    }

    public AbsParcelableLceViewState(Parcel in){
        this.in = in;
    }

    @Override
    public void onSaveInstanceState(Bundle out) {
        //缓存数据
        out.putParcelable(KEY_BUNDLE_VIEW_STATE, this);
    }

    @Override
    public AbsParcelableLceViewState<D, V> onRestoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        //恢复数据
        return (AbsParcelableLceViewState<D, V>) in.getParcelable(KEY_BUNDLE_VIEW_STATE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //缓存
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //缓存->当前状态
        dest.writeInt(currentViewState);
        //缓存->否是下拉刷新组件状态
        writeBoolean(dest, pullToRefresh);
        //缓存->异常信息
        dest.writeSerializable(exception);
    }

    //读取
    protected void readFromParcel(Parcel in) {
        //读取->当前状态
        currentViewState = in.readInt();
        //读取->否是下拉刷新组件状态
        pullToRefresh = readBoolean(in);
        //读取->异常信息
        exception = (Throwable) in.readSerializable();
    }

    //工具方法
    protected void writeBoolean(Parcel dest, boolean b) {
        dest.writeByte((byte) (b ? 1 : 0));
    }

    //工具方法
    protected boolean readBoolean(Parcel p) {
        return p.readByte() == (byte) 1;
    }
}

