package com.play.library_base.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.play.library_base.utils.Utils;

public class BaseApplication extends Application {
    private static Application sInstance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
        context = this;
//        ModuleMaster.getInstance().setModuleMaster(this);
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    public static synchronized void setApplication(Application application) {
        sInstance = application;
        //初始化工具类
        Utils.init(application);

        //注册监听每个activity的生命周期,便于堆栈式管理
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                BaseAppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                BaseAppManager.getAppManager().removeActivity(activity);
            }
        });
    }

    /**
     * 获得当前app运行的Application
     */
    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }

    public Context getContext() {
        return context;
    }


//    ModuleNativeModule moduleNativeModule;
//
//    @Override
//    public ModuleNativeModule getModuleNativeModule() {
//
//        if (null == moduleNativeModule) {
//            moduleNativeModule = new ModuleNativeModule();
//        }
//        return moduleNativeModule;
//    }
}
