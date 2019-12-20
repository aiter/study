package com.jihaoxian.study.chapter2clone;

import java.util.Date;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/20 7:28 AM
 */
public class CopyConstructor {
    protected int id;
    protected String name;
    protected Date startDate;

    public CopyConstructor(CopyConstructor copyConstructor) {
        this.id = copyConstructor.id;
        this.name = copyConstructor.name;
        this.startDate = new Date(copyConstructor.startDate.getTime());
    }

    public CopyConstructor(int id, String name, Date startDate) {
        this.id = id;
        this.name = name;
        this.startDate = new Date(startDate.getTime());
    }
}
