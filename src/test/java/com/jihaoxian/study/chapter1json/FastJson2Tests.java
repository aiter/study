package com.jihaoxian.study.chapter1json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;


/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/20 8:39 AM
 */
public class FastJson2Tests {

    @Test
    public void test() throws Exception {
        MyValue2 myValue2 = new MyValue2();
        myValue2.name = "my-name-2";
        myValue2.age = 2;
        myValue2.sex = 1;

        myValue2.tags.add("boy");
        myValue2.tags.add("pink");

        myValue2.okrMap.put("1", "eat");
        myValue2.okrMap.put("2", "sleep");

        System.out.println(JSON.toJSONString(myValue2));
    }
}
