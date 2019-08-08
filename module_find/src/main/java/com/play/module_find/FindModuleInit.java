package com.play.module_find;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.play.library_base.base.IModuleInit;
import com.play.library_base.utils.LogUtils;

/**
 * @author：jhonjson
 * @data：2019/2/21 下午10:40
 * @描述:
 */

public class FindModuleInit implements IModuleInit {
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
