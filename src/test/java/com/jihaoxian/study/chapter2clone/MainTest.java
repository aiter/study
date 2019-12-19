package com.jihaoxian.study.chapter2clone;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/19 7:23 PM
 */
public class MainTest {
    public static void main(String [] args) {
        /**
         * 1. 最普通的clone
         */
        Item srcItem = new Item();
        srcItem.setName("name1");

        try {
            Item cloneItem = srcItem.clone();

            System.out.println(cloneItem.getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        /**
         * 2. 深复制、还是浅复制。 -- 浅复制，内部的ArrayList是浅复制，clone对象的tags指向同一个ArrayList
         */

        Item2 srcItem2 = new Item2();
        srcItem2.setName("name2");
        srcItem2.addTag("male");
        srcItem2.addTag("engineer");


        try {
            Item2 cloneItem2 = srcItem2.clone();

            cloneItem2.addTag("clone-man");

            System.out.println(cloneItem2.getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        /**
         * clone对象，添加了"clone-man",源对象的标签也添加了
         */
        for(String tag: srcItem2.getTags()) {
            System.out.println(tag);
        }
    }
}
