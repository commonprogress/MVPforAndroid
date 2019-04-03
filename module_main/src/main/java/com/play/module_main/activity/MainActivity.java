package com.play.module_main.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_base.router.RouterFragmentPath;
import com.play.module_main.R;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
//                if (i == R.id.navigation_find) {// 发现
//                    Fragment findFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Find.FIND).navigation();
//                    showFragment(findFragment);
//
//                } else if (i == R.id.navigation_new) {// 新闻
//                    Fragment newFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.News.NEWS).navigation();
//                    showFragment(newFragment);
//
//                } else if (i == R.id.navigation_me) {// 我的
//                    Fragment meFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.User.USER).navigation();
//                    showFragment(meFragment);
//                }
                return true;
            }
        });
    }

    /**
     * 加载fragment
     */
    private void showFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment, fragment).commitAllowingStateLoss();
    }
}
