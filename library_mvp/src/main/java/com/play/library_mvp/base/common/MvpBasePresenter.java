package com.play.library_mvp.base.common;

import com.play.library_mvp.base.interfaces.MvpPresenter;
import com.play.library_mvp.base.interfaces.MvpView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jhonjson on 2019/4/2
 * E-mail:824483029@qq.com
 */
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    //弱引用
    private V viewProxy;
    private V view;

    public V getView() {
        return viewProxy;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
        //目标接口->实际上是MvpView
        Class<?>[] interfaces = view.getClass().getInterfaces();
        try {
            viewProxy = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), interfaces, new ProxyInvocationHandler<V>(view));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void destory() {

    }

    private class ProxyInvocationHandler<V extends MvpView> implements InvocationHandler {

        private V view;
        public ProxyInvocationHandler(V view){
            this.view = view;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (view != null){
                //执行
                return method.invoke(view, args);
            }
            //不存在->报错了
            throw new NullPointerException("空异常");
        }
    }
}
