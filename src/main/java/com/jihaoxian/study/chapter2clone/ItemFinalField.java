package com.jihaoxian.study.chapter2clone;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/19 7:22 PM
 */
public class ItemFinalField implements Cloneable {
    private String name;

    private final String tag;

    public ItemFinalField() {
        tag = "person";
    }

    public ItemFinalField(ItemFinalField itemFinalField) {
        tag = "person";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ItemFinalField clone() throws CloneNotSupportedException {
        return (ItemFinalField)super.clone();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("name=").append(name)
            .append(",tag=").append(tag);

        return builder.toString();
    }
}
