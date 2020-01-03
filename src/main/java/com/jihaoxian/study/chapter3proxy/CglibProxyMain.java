package com.jihaoxian.study.chapter3proxy;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2020/01/04 12:19 AM
 */
public class CglibProxyMain {

    public static void main(String [] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Duck duck = (Duck)cglibProxy.getInstance(new Duck());
        duck.run();;
    }
}
