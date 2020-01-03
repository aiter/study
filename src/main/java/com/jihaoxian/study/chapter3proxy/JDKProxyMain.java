package com.jihaoxian.study.chapter3proxy;

import java.lang.reflect.Proxy;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2020/01/04 12:19 AM
 */
public class JDKProxyMain {

    public static void main(String [] args) {
        //实现类，实现了Animal接口
        Dog dog = new Dog();
        //InvocationHandler对象
        DogHandler dogHandler = new DogHandler(dog);

        Animal proxy = (Animal)Proxy.newProxyInstance(
            //第一个参数，获取ClassLoader
            JDKProxyMain.class.getClassLoader(),
            //第二个参数，获取被代理类的接口
            dog.getClass().getInterfaces(),
            //第三个参数，一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
            dogHandler);

        proxy.run();
    }
}
