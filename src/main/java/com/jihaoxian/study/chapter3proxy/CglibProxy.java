package com.jihaoxian.study.chapter3proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2020/01/04 12:28 AM
 */
public class CglibProxy implements MethodInterceptor {
    private Object animal;

    /**
     * 创建代理对象
     *
     * @param object
     * @return
     */
    public Object getInstance(Object object) {
        this.animal = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.animal.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        methodProxy.invokeSuper(obj, args);
        System.out.println("end");
        return null;
    }
}
