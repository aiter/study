package com.jihaoxian.study.chapter2clone;

import java.util.Date;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/20 7:29 AM
 */
public class SubCopyConstructor2 extends CopyConstructor {
    public SubCopyConstructor2(int id, String name, Date startDate) {
        super(id, name, startDate);
    }

    public SubCopyConstructor2(SubCopyConstructor2 subCopyConstructor) {
        super(subCopyConstructor.id, subCopyConstructor.name, subCopyConstructor.startDate);
        //this.directReports = directReports.stream()
        //    .collect(Collectors.toList());
    }
}
