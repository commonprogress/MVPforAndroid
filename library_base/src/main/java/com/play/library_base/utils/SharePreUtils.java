/*
 * Copyright (c) 2018.
 * Author：Zhao
 * Email：joeyzhao1005@gmail.com
 */

package com.play.library_base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.play.library_base.base.BaseApplication;

/**
 * Created by jhonjson on 2017/9/5.
 * <p>
 * 添加异步提交方法 lz 2017/11/23
 */

public class SharePreUtils {

    private static Context context = BaseApplication.getInstance().getBaseContext();

    public static SharedPreferences getSp() {
        return context.getSharedPreferences("share", Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }

    public static synchronized void setInteger(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 异步方法
     *
     * @param key
     * @param value
     */
    public static void setAsyncInteger(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(key, value);
        editor.apply();
    }

    public static synchronized int getInteger(String key, int defaultValue) {
        return getSp().getInt(key, defaultValue);
    }

    public static synchronized void setLong(String key, long value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void setAsyncLong(String key, long value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(key, value);
        editor.apply();
    }

    public static synchronized long getLong(String key, long defaultValue) {
        return getSp().getLong(key, defaultValue);
    }

    public static synchronized void setString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setAsyncString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.apply();
    }

    public static synchronized String getString(String key) {
        return getSp().getString(key, "");
    }

    public static synchronized void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setAsyncBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static synchronized boolean getBoolean(String key, boolean defaultValue) {
        return getSp().getBoolean(key, defaultValue);
    }
}
