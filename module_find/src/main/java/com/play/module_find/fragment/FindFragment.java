package com.play.module_find.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.library_base.router.RouterActivityPath;
import com.play.library_base.router.RouterFragmentPath;
import com.play.module_find.R;

/**
 * @author：jhonjson
 * @data：2019/2/19 下午16:40
 * @描述: 发现屏
 */

@Route(path = RouterFragmentPath.Find.PAGER_FIND)
public class FindFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_find_layout, container, false);
        rootView.findViewById(R.id.tv_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build(RouterActivityPath.Login.PAGER_LOGIN).withString("title", "这是跨组件化跳转过来的").navigation();
            }
        });
        return rootView;
    }
}
