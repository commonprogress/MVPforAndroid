package com.play.library_mvp.support.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import com.play.library_mvp.base.interfaces.MvpPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PresenterManager {

    static final String KEY_ACTIVITY_ID = "com.play.library_mvp.support.manager.PresenterManagerActivityId";

    //缓存集合->Map集合->缓存Activity->ID
    private final static Map<Activity, String> activityIdMap = new HashMap<>();
    //缓存Activtiy
    private final static Map<String, ActivityScopeCache> activityScopeCacheMap = new HashMap<String, ActivityScopeCache>();

    //非常重要环节->Activity监听起
    //知识：通过Application监听Activity创建过程
    static final Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {

        //onActivityCreated方法->对应->Activity中->onCreate方法
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (bundle != null) {
                //需要进行缓存处理
                String activityId = bundle.getString(KEY_ACTIVITY_ID);
                if (activityId != null) {
                    //缓存
                    activityIdMap.put(activity, activityId);
                }
            }
        }

        //学习Android基础学习过这个缓存
        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            String activityId = activityIdMap.get(activity);
            if (activityId != null) {
                //缓存数据
                outState.putString(KEY_ACTIVITY_ID, activityId);
            }

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        //销毁对象(释放)
        @Override
        public void onActivityDestroyed(Activity activity) {
            //释放对象
            if (!activity.isChangingConfigurations()) {
                //需要释放
                String activityId = activityIdMap.get(activity);
                if (activityId != null) {
                    //获取当前这个Activity->对应的->Presenter
                    ActivityScopeCache scopeCache = activityScopeCacheMap.get(activityId);
                    if (scopeCache != null) {
                        //释放
                        scopeCache.clear();
                        activityScopeCacheMap.remove(activityId);
                    }

                    if (activityScopeCacheMap.isEmpty()) {
                        //取消Activity注册监听
                        activity.getApplication().unregisterActivityLifecycleCallbacks(callbacks);
                    }
                }
            }
            activityIdMap.remove(activity);
        }
    };

    //规定PresenterManager类中都是类方法，不能存在实例方法
    private PresenterManager() {
        throw new RuntimeException("运行时异常，这个对象不允许创建...");
    }


    //简单版本(不一定存在)->抽象一下
    //如果为空：做什么逻辑处理(做一些相应逻辑处理)
    static ActivityScopeCache getActivityScopeCache(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity空指针异常...");
        }
        String activityId = activityIdMap.get(activity);
        if (activityId == null) {
            return null;
        }
        return activityScopeCacheMap.get(activityId);
    }

    //详细版本->得到P缓存管理器(一定存在)
    static ActivityScopeCache getOrCreateActivityScopeCache(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity空指针异常...");
        }
        String activityId = activityIdMap.get(activity);
        if (activityId == null) {
            activityId = UUID.randomUUID().toString();
            activityIdMap.put(activity, activityId);

            if (activityIdMap.size() == 1) {
                //注册Activity生命周期监听起
                activity.getApplication().registerActivityLifecycleCallbacks(callbacks);
            }
        }

        ActivityScopeCache activityScopeCache = activityScopeCacheMap.get(activityId);
        if (activityScopeCache == null) {
            activityScopeCache = new ActivityScopeCache();
            activityScopeCacheMap.put(activityId, activityScopeCache);
        }

        return activityScopeCache;
    }


    //得到具体Presenter
    public static <P> P getPresenter(Activity activity, String viewId) {
        if (activity == null) {
            throw new NullPointerException("activity不能为空");
        }
        if (viewId == null) {
            throw new NullPointerException("viewId不能为空");
        }

        ActivityScopeCache scopeCache = getActivityScopeCache(activity);
        return scopeCache == null ? null : (P) scopeCache.getPresenter(viewId);
    }

    //获取Activity
    public static Activity getActivity(Context context) {
        if (context == null) {
            throw new NullPointerException("context不能为空");
        }

        if (context instanceof Activity) {
            return (Activity) context;
        }

        //循环获取父类类型
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        //抛出异常
        throw new IllegalStateException("找不到Activity");
    }

    //重置集合
    static void reset() {
        activityIdMap.clear();
        for (ActivityScopeCache scopeCache : activityScopeCacheMap.values()) {
            scopeCache.clear();
        }

        activityScopeCacheMap.clear();
    }

    //添加Presenter
    public static void putPresenter(Activity activity, String viewId, MvpPresenter<?> presenter) {
        if (activity == null) {
            throw new NullPointerException("activity不能为空");
        }
        ActivityScopeCache scopeCache = getOrCreateActivityScopeCache(activity);
        scopeCache.putPresenter(viewId, presenter);
    }

    //删除指定P
    public static void remove(Activity activity, String viewId) {
        if (activity == null) {
            throw new NullPointerException("activity不能为空");
        }
        ActivityScopeCache scopeCache = getActivityScopeCache(activity);
        if (scopeCache != null) {
            scopeCache.remove(viewId);
        }
    }


    /********备忘录模式->备份ViewState********/
    //提供get和put方法
    public static <VS> VS getViewState(Activity activity, String viewId) {
        if (activity == null) {
            throw new NullPointerException("activity不能为空");
        }
        if (viewId == null) {
            throw new NullPointerException("viewId不能为空");
        }

        ActivityScopeCache scopeCache = getActivityScopeCache(activity);
        return scopeCache == null ? null : (VS) scopeCache.getViewState(viewId);
    }

    public static void putViewState(Activity activity, String viewId, Object viewState) {
        if (activity == null) {
            throw new NullPointerException("activity不能为空");
        }
        if (viewId == null) {
            throw new NullPointerException("viewId不能为空");
        }
        ActivityScopeCache scopeCache = getOrCreateActivityScopeCache(activity);
        scopeCache.putViewState(viewId, viewState);
    }

}
