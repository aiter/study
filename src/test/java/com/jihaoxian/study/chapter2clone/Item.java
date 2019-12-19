package com.jihaoxian.study.chapter2clone;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/19 7:22 PM
 */
public class Item implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Item clone() throws CloneNotSupportedException {
        return (Item)super.clone();
    }
}
