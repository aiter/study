package com.jihaoxian.study.chapter1json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

/**
 * @author aiter(aiter2006 @ gmail.com)
 * @date 2019/12/06 7:08 AM
 */
public class JacksonTests {

    //创建对象实例，不用每次都新建，重复使用
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * <pre>
     *     1. 需要是public
     *     2. 每个测试方法执行之前
     * </pre>
     */
    @Before
    public void setup() {
        System.out.println(Thread.currentThread().getName() + ":setup");
        try {
            MyValue myValue = new MyValue();
            myValue.name = "aiter";
            myValue.age = 19;
            mapper.writeValue(new File("data.json"), myValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     *     1. 需要是public
     *     2. 每个测试方法执行之后
     * </pre>
     */
    @After
    public void cleanup() {
        System.out.println(Thread.currentThread().getName() + ":cleanup");

        File file = new File("data.json");
        file.delete();
    }

    /**
     * 将json转换为java对象
     *
     * @throws Exception e
     */
    @Test
    public void testDeserialize() throws Exception {
        MyValue value = mapper.readValue(new File("data.json"), MyValue.class);

        Assert.assertEquals(value.age, 19);
        Assert.assertEquals(value.name, "aiter");

        // or:
        //value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
        // or:
        value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);

        Assert.assertEquals(value.age, 13);
        Assert.assertEquals(value.name, "Bob");
    }

    @Test
    public void test1() throws Exception {
        MyValue myValue = new MyValue();
        myValue.name = "aiter";
        myValue.age = 19;
        mapper.writeValue(new File("data.json"), myValue);
    }

    @Test
    public void testMap() throws Exception {
        Map<String, Object> scoreByName = mapper.readValue(new File("data.json"), Map.class);

        for(Map.Entry<String, Object> entry : scoreByName.entrySet()) {
            System.out.println(entry);
        }

        //Map<String, MyValue> results = mapper.readValue(jsonSource,
        //    new TypeReference<Map<String, MyValue>>() { } );
    }

    @Test
    public void testNodeTree() throws Exception {
        //JsonNode root = mapper.readTree(new File("data.json"));
        //
        ObjectNode root = (ObjectNode)mapper.readTree(new File("data.json"));
        String name = root.get("name").asText();
        int age = root.get("age").asInt();

        Assert.assertEquals(age, 19);
        Assert.assertEquals(name, "aiter");


        // ObjectNode.with存在 other, 必须是ObjectNode，否则抛异常
        // 不存在，就创建ObjectNode
        root.with("other").put("type", "student");

        System.out.println(root);
    }
}
