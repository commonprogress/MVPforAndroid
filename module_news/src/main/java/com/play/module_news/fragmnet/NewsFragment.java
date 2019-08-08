package com.play.module_news.fragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.play.library_base.router.RouterFragmentPath;
import com.play.module_news.R;

/**
 * @author：jhonjson
 * @data：2019/2/20 下午9:40
 * @描述: 新闻
 */

@Route(path = RouterFragmentPath.News.PAGER_NEWS)
public class NewsFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_layout, container, false);
        rootView.findViewById(R.id.tv_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build("/play/activity").withString("title", "这是跨组件化跳转过来的").navigation();
            }
        });
        return rootView;
    }
}
