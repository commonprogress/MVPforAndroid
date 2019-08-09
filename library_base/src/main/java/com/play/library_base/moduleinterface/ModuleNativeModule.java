package com.play.library_base.moduleinterface;


import android.content.Context;

/**
 * Module间 回调
 */
public abstract class ModuleNativeModule {

    public ModuleNativeModule() {
        super();
    }

    /**
     * 跳转主界面
     */
    public abstract void startMainActivity(Context context);
}
