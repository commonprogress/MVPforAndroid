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
import com.play.library_base.base.BaseContainerActivity;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_base.router.RouterFragmentPath;
import com.play.module_main.R;

import java.util.HashMap;
import java.util.List;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseContainerActivity {
    private BottomNavigationView mNavigationView;
    private HashMap<Integer, Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mFragments = new HashMap<>();
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchTab(item.getItemId());
                return true;
            }
        });
        switchTab(0);
    }

    // 切换tab
    private void switchTab(int id) {
        Fragment currentFragment = null;
        if (id == R.id.navigation_find) {// 发现

            currentFragment = mFragments.get(R.id.navigation_find);
            if (currentFragment == null) {
                currentFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Find.PAGER_FIND).navigation();
                mFragments.put(R.id.navigation_find, currentFragment);
            }
            showFragment(currentFragment);
        } else if (id == R.id.navigation_new) {// 新闻
            currentFragment = mFragments.get(R.id.navigation_new);
            if (currentFragment == null) {
                currentFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.News.PAGER_NEWS).navigation();
                mFragments.put(R.id.navigation_new, currentFragment);
            }
            showFragment(currentFragment);
        } else if (id == R.id.navigation_me) {// 我的
            currentFragment = mFragments.get(R.id.navigation_me);
            if (currentFragment == null) {
                currentFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.User.PAGER_USERDETAIL).navigation();
                mFragments.put(R.id.navigation_me, currentFragment);
            }
            showFragment(currentFragment);
        } else {// 默认
            Fragment findFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Find.PAGER_FIND).navigation();
            showFragment(findFragment);
        }

    }

    /**
     * 加载fragment
     */
    private void showFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, fragment).commitAllowingStateLoss();
    }
}
