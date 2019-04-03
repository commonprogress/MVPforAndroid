package com.play.library_base.config;

/**
 * Created by jhonjson on 2019/2/20
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.play.library_base.base.BaseModuleInit";
    //主业务模块
    private static final String MainInit = "com.play.module_main.MainModuleInit";
    //发现业务模块
    private static final String FindInit = "com.play.module_me.FindModuleInit";
    //新闻业务模块
    private static final String NewsInit = "com.play.module_news.NewsModuleInit";
    //用户业务模块
    private static final String UserInit = "com.play.module_find.MeModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit, FindInit, NewsInit,UserInit};
}
