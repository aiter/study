package com.jihaoxian.study.chapter1json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
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

        for (Map.Entry<String, Object> entry : scoreByName.entrySet()) {
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

    /**
     * <pre>
     *     1. data-binding (to/from POJO)
     *     2. Tree Mode
     *     3. incremental Mode (aka streaming)。1、2的底层实现。也暴露给用户使用。
     * </pre>
     *
     * @throws Exception
     */
    @Test
    public void testParserGenerator() throws Exception {
        JsonFactory f = mapper.getFactory();

        // First: write simple JSON output
        File jsonFile = new File("test.json");
        JsonGenerator g = f.createGenerator(new FileWriter(jsonFile));
        // write JSON: { "message" : "Hello world!" }
        g.writeStartObject();
        g.writeStringField("message", "Hello world!");
        g.writeEndObject();
        g.close();



        // Second: read file back
        JsonParser p = f.createParser(jsonFile);

        JsonToken t = p.nextToken(); // Should be JsonToken.START_OBJECT
        t = p.nextToken(); // JsonToken.FIELD_NAME
        if ((t != JsonToken.FIELD_NAME) || !"message".equals(p.getCurrentName())) {
            // handle error
        }
        t = p.nextToken();
        if (t != JsonToken.VALUE_STRING) {
            // similarly
        }
        String msg = p.getText();
        System.out.printf("My message to you is: %s!\n", msg);
        p.close();

        jsonFile.delete();
    }

    /**
     * <pre>
     *     1. 使用Features的方式配置
     *     2. 使用Annotaions的方式配置
     * </pre>
     * @throws Exception
     */
    @Test
    public void testConfiguration() throws Exception {
        // SerializationFeature for changing how JSON is written

        // to enable standard indentation ("pretty-printing"):  缩进,边缘阅读json
        /**
         * <pre>
         * {"name":"aiter","age":19}
         *
         * {
         *   "name" : "aiter",
         *   "age" : 19
         * }
         * </pre>
         *
         */
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // DeserializationFeature for changing how JSON is read as POJOs:

        /**
         * mapper使用enable/disable这种builder模式。在{@link SerializationFeature} 和 ${@link DeserializationFeature}
         * 查看序列化和反序列化配置
         */

        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        /**
         * 使用configure   反序列化：{@link JsonParser.Feature}   序列化：{@link JsonGenerator.Feature}
         */

        // JsonParser.Feature for configuring parsing settings:

        // to allow C/C++ style comments in JSON (non-standard, disabled by default)
        // (note: with Jackson 2.5, there is also `mapper.enable(feature)` / `mapper.disable(feature)`)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // to allow (non-standard) unquoted field names in JSON:
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // to allow use of apostrophes (single quotes), non standard
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // JsonGenerator.Feature for configuring low-level JSON generation:

        // to force escaping of non-ASCII characters:
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);


        MyValue myValue = new MyValue();
        myValue.name = "aiter";
        myValue.age = 19;
        //mapper.writeValue(new File("data.json"), myValue);

        System.out.println(mapper.writeValueAsString(myValue));
    }
}
