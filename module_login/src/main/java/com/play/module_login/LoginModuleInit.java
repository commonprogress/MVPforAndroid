package com.play.module_login;

import android.app.Application;

import com.play.library_base.base.IModuleInit;
import com.play.library_base.utils.LogUtils;

/**
 * @author：jhonjson
 * @data：2019/6/28 下午10:40
 * @描述: 登陆
 */

public class LoginModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        LogUtils.e("主业务模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        LogUtils.e("主业务模块初始化 -- onInitLow");
        return false;
    }
}
