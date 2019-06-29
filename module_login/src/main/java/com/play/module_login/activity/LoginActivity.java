package com.play.module_login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.module_login.R;
import com.play.library_base.router.RouterActivityPath;

/**
 * @author：jhonjson
 * @data：2019/6/28 下午16:40
 * @描述: 登陆
 */
@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
public class LoginActivity extends AppCompatActivity {

    @Autowired(name = "title")
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ARouter.getInstance().inject(this);
        Log.e("LoginActivity", "gettitle " + title);
    }


}
