package com.play.library_base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by jhonjson on 2019/4/3
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 显示提示信息
     */
    public static void showToast(Context context, String text) {
        if (TextUtils.isEmpty(text)) return;
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);

        } else {
            toast.setText(text);
        }
        toast.getGravity();
        toast.show();

    }

    /**
     * 显示提示信息
     */
    public static void showToast(Context context, int rId) {
        if (toast == null) {
            toast = Toast.makeText(context, rId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(rId);
        }
        toast.getGravity();
        toast.show();

    }

    /**
     * 显示提示信息(时间较长)
     */
    public static void showLongToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.getGravity();
        toast.show();

    }

    /**
     * 显示提示信息(时间较长)
     */
    public static void showLongToast(Context context, int rId) {
        if (toast == null) {
            toast = Toast.makeText(context, rId, Toast.LENGTH_LONG);
        } else {
            toast.setText(rId);
        }
        toast.getGravity();
        toast.show();

    }
}
