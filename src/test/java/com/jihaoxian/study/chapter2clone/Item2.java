package com.jihaoxian.study.chapter2clone;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/19 7:22 PM
 */
public class Item2 implements Cloneable {
    private String name;

    private List<String> tags = new ArrayList<>(5);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    public List<String> getTags(){
        return tags;
    }

    @Override
    public Item2 clone() throws CloneNotSupportedException {
        return (Item2)super.clone();
    }
}
