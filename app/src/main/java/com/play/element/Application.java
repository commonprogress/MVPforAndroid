package com.play.element;


import com.play.library_base.base.BaseApplication;
import com.play.library_base.config.ModuleLifecycleConfig;

/**
 * Created by jhonjson on 2019/2/20
 */

public class Application extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}
