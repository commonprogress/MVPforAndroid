package com.play.module_me.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.play.library_base.router.RouterFragmentPath;
import com.play.module_me.R;

/**
 * @author：jhonjson
 * @data：2019/2/20 下午16:40
 * @描述:
 */

@Route(path = RouterFragmentPath.User.USER)
public class MeFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me_layout, container, false);
//        rootView.findViewById(R.id.tv_me).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ARouter.getInstance().build("/play/activity").withString("title", "这是跨组件化跳转过来的").navigation();
//            }
//        });
        return rootView;
    }
}