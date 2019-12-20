package com.jihaoxian.study.chapter2clone;

import java.util.Date;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/20 7:34 AM
 */
public class MainCopyConstructorTest {

    public static void main(String [] args) {
        CopyConstructor source = new SubCopyConstructor(1, "Sub", new Date());


        //需要强制类型转换
        CopyConstructor clone = new SubCopyConstructor((SubCopyConstructor)source);

        // 如果是其他的子类

        CopyConstructor otherSource = new SubCopyConstructor2(1, "Sub", new Date());

        //有 ClassCastException 异常
        CopyConstructor clone2 = new SubCopyConstructor((SubCopyConstructor)otherSource);
    }
}
