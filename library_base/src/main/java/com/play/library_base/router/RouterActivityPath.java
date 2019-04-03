package com.play.library_base.router;

/**
 *
 * Created by jhonjson on 2019/2/20
 * @describe 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */

public class RouterActivityPath {
    /**
     * 主业务组件
     */
    public static class Main {
        private static final String MAIN = "/main";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN +"/main";
    }

//    /**
//     * 发现组件
//     */
//    public static class Find {
//        private static final String FIND = "/Find";
//        /*发现界面*/
//        public static final String PAGER_FIND = FIND +"/Find";
//    }
//
//    /**
//     * 新闻组件
//     */
//    public static class News {
//        private static final String NEWS = "/News";
//        /*登录界面*/
//        public static final String PAGER_NEWS = NEWS + "/News";
//    }
//
//    /**
//     * 用户组件
//     */
//    public static class User {
//        private static final String USER = "/user";
//        /*用户详情*/
//        public static final String PAGER_USERDETAIL = USER + "/User";
//    }
}
