package com.play.module_main.activity;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_mvp.base.common.BaseActivity;
import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;
import com.play.module_main.R;

@Route(path = RouterActivityPath.Setting.PAGER_SETTING_ABOUT)
public class AboutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseViewImp createView() {
        return null;
    }

    @Override
    public void init() {

    }
}
