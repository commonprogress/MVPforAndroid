package com.play.library_base.base;

import android.app.Application;

/**
 * Created by jhonjson on 2019/2/20
 * @describe  动态配置Application，有需要初始化的组件实现该接口，
 * 统一在主app的Application中初始化
 */

public interface IModuleInit {
    //初始化优先的
    boolean onInitAhead(Application application);

    //初始化靠后的
    boolean onInitLow(Application application);
}
