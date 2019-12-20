package com.jihaoxian.study.chapter1json;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入门: https://github.com/FasterXML/jackson-databind
 *
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/06 7:06 AM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyValue2 {

    // Note: can use getters/setters as well; here we just use public fields directly:
    public String name;
    public int age;
    //transient json序列化时，也不会被序列化(jackson/fastjson)
    public transient int sex;

    @JsonProperty
    @JSONField
    private final String nation = "c";
    //stack修饰的也不会序列化，
    public static String haveEye = "yes";

    //null时，fastjson不输出，jackson输出null
    public String address;

    public List<String> tags = new ArrayList<>(10);
    public Map<String,String> okrMap = new HashMap<>(10);
    // NOTE: if using getters/setters, can keep fields `protected` or `private`

    @JsonProperty(value = "testKey")
    @JSONField(name = "testKey")
    private String test() {
        return "test-values";
    }
}
