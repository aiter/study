package com.jihaoxian.study.chapter1json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/20 8:39 AM
 */
public class Jackson2Tests {
    //创建对象实例，不用每次都新建，重复使用
    private ObjectMapper mapper = new ObjectMapper();

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

        System.out.println(mapper.writeValueAsString(myValue2));
    }
}
