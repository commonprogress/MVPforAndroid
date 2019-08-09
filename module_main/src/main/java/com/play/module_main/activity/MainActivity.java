package com.play.module_main.activity;

import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_base.router.RouterFragmentPath;
import com.play.library_mvp.base.common.BaseActivity;
import com.play.library_mvp.base.common.BasePresenter;
import com.play.library_mvp.base.common.BaseViewImp;
import com.play.module_main.R;

import java.util.HashMap;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends BaseActivity {
    private BottomNavigationView mNavigationView;
    private HashMap<Integer, Fragment> mFragments;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
        initView();
    }

    private void initView() {
        mFragments = new HashMap<>();
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switchTab(item.getItemId());
                return true;
            }
        });
        switchTab(0);
    }

    @Override
    public boolean isOpenImmersive() {
        return true;
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
