package com.play.library_base.router;

/**
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */

public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        /*首页*/
        public static final String HOME = "/home";
    }

    /**
     * 发现组件
     */
    public static class Find {
        /*发现界面*/
        public static final String FIND = "Fragment/Find";
        /*发现详情*/
        public static final String PAGER_FIND = FIND +"/details";
    }

    /**
     * 新闻组件
     */
    public static class News {
        /*新闻界面*/
        public static final String NEWS = "Fragment/News";
        /*新闻详情*/
        public static final String PAGER_NEWS = NEWS + "/details";
    }

    /**
     * 用户组件
     */
    public static class User {
        /*用户界面*/
        public static final String USER = "Fragment/user";
        /*用户详情*/
        public static final String PAGER_USERDETAIL = USER + "/details";
    }
}
