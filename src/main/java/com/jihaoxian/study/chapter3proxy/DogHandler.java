package com.jihaoxian.study.chapter3proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2020/01/04 12:17 AM
 */
public class DogHandler implements InvocationHandler {
    //真实类的对象
    private Object animal;
    //构造方法赋值给真实的类
    public DogHandler(Object obj){
        this.animal = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object res = method.invoke(animal, args);
        System.out.println("after");
        return res;
    }
}
