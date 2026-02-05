package org.json.junit;

/*
Public Domain.
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.junit.data.MyEnum;
import org.json.junit.data.MyEnumClass;
import org.json.junit.data.MyEnumField;
import org.junit.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * Enums are not explicitly supported in JSON-Java. But because enums act like
 * classes, all required behavior is already be present in some form. 
 * These tests explore how enum serialization works with JSON-Java.
 */
public class EnumTest_OE25Dev {

    /**
     * To serialize an enum by its getters, use the JSONObject Object constructor.
     * The JSONObject ctor handles enum like any other bean. A JSONobject 
     * is created whose entries are the getter name/value pairs.
     */

    /**
     * To serialize an enum by its set of allowed values, use getNames()
     * and the JSONObject Object with names constructor.
     */
    
    /**
     * Verify that enums are handled consistently between JSONArray and JSONObject
     */

    /**
     * To serialize by assigned value, use the put() methods. The value
     * will be stored as a enum type. 
     */

    /**
     * The default action of valueToString() is to call object.toString().
     * For enums, this means the assigned value will be returned as a string.
     */

    /**
     * In whatever form the enum was added to the JSONObject or JSONArray,
     * json[Object|Array].toString should serialize it in a reasonable way.
     */

    /**
     * Wrap should handle enums exactly as a value type like Integer, Boolean, or String.
     */

    /**
     * It was determined that some API methods should be added to 
     * support enums:<br>
     * JSONObject.getEnum(class, key)<br>
     * JSONObject.optEnum(class, key)<br>
     * JSONObject.optEnum(class, key, default)<br>
     * JSONArray.getEnum(class, index)<br>
     * JSONArray.optEnum(class, index)<br>
     * JSONArray.optEnum(class, index, default)<br>
     * <p>
     * Exercise these enum API methods on JSONObject and JSONArray
     */

@Test
    public void jsonObjectFromEnum_1_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        assertTrue("simple enum has no getters", jsonObject.isEmpty());
    }

@Test
    public void jsonObjectFromEnum_2_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        assertTrue("expecting 2 items in top level object", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void jsonObjectFromEnum_3_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expecting val 2", "val 2".equals(jsonObject.query("/value")));
    }

@Test
    public void jsonObjectFromEnum_4_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expecting 2", Integer.valueOf(2).equals(jsonObject.query("/intVal")));
    }

@Test
    public void jsonObjectFromEnum_5_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * class which contains enum instances. Each enum should be stored
         * in its own JSONObject
         */
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void jsonObjectFromEnum_6_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * class which contains enum instances. Each enum should be stored
         * in its own JSONObject
         */
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 2 myEnumField items", "VAL3".equals((JsonPath.read(doc, "$.myEnumField"))));
    }

@Test
    public void jsonObjectFromEnum_7_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * class which contains enum instances. Each enum should be stored
         * in its own JSONObject
         */
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 0 myEnum items", "VAL1".equals((JsonPath.read(doc, "$.myEnum"))));
    }

@Test
    public void jsonObjectFromEnum_8_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * class which contains enum instances. Each enum should be stored
         * in its own JSONObject
         */
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("expecting MyEnumField.VAL3", MyEnumField.VAL3.equals(jsonObject.query("/myEnumField")));
    }

@Test
    public void jsonObjectFromEnum_9_oe() {
        // If there are no getters then the object is empty.
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        // removed other assertion

         // enum with a getters should create a non-empty object 
        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * class which contains enum instances. Each enum should be stored
         * in its own JSONObject
         */
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("expecting MyEnum.VAL1", MyEnum.VAL1.equals(jsonObject.query("/myEnum")));
    }

@Test
    public void jsonObjectFromEnumWithNames_1_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 3 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

@Test
    public void jsonObjectFromEnumWithNames_2_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL1", MyEnum.VAL1.equals(jsonObject.query("/VAL1")));
    }

@Test
    public void jsonObjectFromEnumWithNames_3_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL2", MyEnum.VAL2.equals(jsonObject.query("/VAL2")));
    }

@Test
    public void jsonObjectFromEnumWithNames_4_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL3", MyEnum.VAL3.equals(jsonObject.query("/VAL3")));
    }

@Test
    public void jsonObjectFromEnumWithNames_5_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL3;
        names = JSONObject.getNames(myEnumField);
        // The values will be MyEnmField fields
        jsonObject = new JSONObject(myEnumField, names);
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 3 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

@Test
    public void jsonObjectFromEnumWithNames_6_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL3;
        names = JSONObject.getNames(myEnumField);
        // The values will be MyEnmField fields
        jsonObject = new JSONObject(myEnumField, names);
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL1", MyEnumField.VAL1.equals(jsonObject.query("/VAL1")));
    }

@Test
    public void jsonObjectFromEnumWithNames_7_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL3;
        names = JSONObject.getNames(myEnumField);
        // The values will be MyEnmField fields
        jsonObject = new JSONObject(myEnumField, names);
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL2", MyEnumField.VAL2.equals(jsonObject.query("/VAL2")));
    }

@Test
    public void jsonObjectFromEnumWithNames_8_oe() {
        String [] names;
        JSONObject jsonObject;
 
        MyEnum myEnum = MyEnum.VAL1;
        names = JSONObject.getNames(myEnum);
        // The values will be MyEnum fields
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON object
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL3;
        names = JSONObject.getNames(myEnumField);
        // The values will be MyEnmField fields
        jsonObject = new JSONObject(myEnumField, names);
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL3", MyEnumField.VAL3.equals(jsonObject.query("/VAL3")));
    }

@Test
    public void verifyEnumConsistency_1_oe(){
        JSONObject jo = new JSONObject();
        
        jo.put("value", MyEnumField.VAL2);
        String expected="{\"value\":\"VAL2\"}";
        String actual = jo.toString();
        assertTrue("Expected "+expected+" but actual was "+actual, expected.equals(actual));
    }

@Test
    public void verifyEnumConsistency_2_oe(){
        JSONObject jo = new JSONObject();
        
        jo.put("value", MyEnumField.VAL2);
        String expected="{\"value\":\"VAL2\"}";
        String actual = jo.toString();
        // removed other assertion

        jo.accumulate("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL2\",\"VAL1\"]}";
        actual = jo.toString();
        assertTrue("Expected "+expected+" but actual was "+actual, expected.equals(actual));
    }

@Test
    public void verifyEnumConsistency_3_oe(){
        JSONObject jo = new JSONObject();
        
        jo.put("value", MyEnumField.VAL2);
        String expected="{\"value\":\"VAL2\"}";
        String actual = jo.toString();
        // removed other assertion

        jo.accumulate("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL2\",\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.remove("value");
        jo.append("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL1\"]}";
        actual = jo.toString();
        assertTrue("Expected "+expected+" but actual was "+actual, expected.equals(actual));
    }

@Test
    public void verifyEnumConsistency_4_oe(){
        JSONObject jo = new JSONObject();
        
        jo.put("value", MyEnumField.VAL2);
        String expected="{\"value\":\"VAL2\"}";
        String actual = jo.toString();
        // removed other assertion

        jo.accumulate("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL2\",\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.remove("value");
        jo.append("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.put("value", EnumSet.of(MyEnumField.VAL2));
        expected="{\"value\":[\"VAL2\"]}";
        actual = jo.toString();
        assertTrue("Expected "+expected+" but actual was "+actual, expected.equals(actual));
    }

@Test
    public void verifyEnumConsistency_5_oe(){
        JSONObject jo = new JSONObject();
        
        jo.put("value", MyEnumField.VAL2);
        String expected="{\"value\":\"VAL2\"}";
        String actual = jo.toString();
        // removed other assertion

        jo.accumulate("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL2\",\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.remove("value");
        jo.append("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.put("value", EnumSet.of(MyEnumField.VAL2));
        expected="{\"value\":[\"VAL2\"]}";
        actual = jo.toString();
        // removed other assertion

        JSONArray ja = new JSONArray();
        ja.put(MyEnumField.VAL2);
        jo.put("value", ja);
        actual = jo.toString();
        assertTrue("Expected "+expected+" but actual was "+actual, expected.equals(actual));
    }

@Test
    public void verifyEnumConsistency_6_oe(){
        JSONObject jo = new JSONObject();
        
        jo.put("value", MyEnumField.VAL2);
        String expected="{\"value\":\"VAL2\"}";
        String actual = jo.toString();
        // removed other assertion

        jo.accumulate("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL2\",\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.remove("value");
        jo.append("value", MyEnumField.VAL1);
        expected="{\"value\":[\"VAL1\"]}";
        actual = jo.toString();
        // removed other assertion

        jo.put("value", EnumSet.of(MyEnumField.VAL2));
        expected="{\"value\":[\"VAL2\"]}";
        actual = jo.toString();
        // removed other assertion

        JSONArray ja = new JSONArray();
        ja.put(MyEnumField.VAL2);
        jo.put("value", ja);
        actual = jo.toString();
        // removed other assertion

        jo.put("value", new MyEnumField[]{MyEnumField.VAL2});
        actual = jo.toString();
        assertTrue("Expected "+expected+" but actual was "+actual, expected.equals(actual));
    }

@Test
    public void enumPut_1_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level objects", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void enumPut_2_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL2", MyEnum.VAL2.equals(jsonObject.query("/myEnum")));
    }

@Test
    public void enumPut_3_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL1", MyEnumField.VAL1.equals(jsonObject.query("/myEnumField")));
    }

@Test
    public void enumPut_4_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2 top level objects", ((List<?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void enumPut_5_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        assertTrue("expected VAL2", MyEnum.VAL2.equals(jsonArray.query("/0")));
    }

@Test
    public void enumPut_6_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL1", MyEnumField.VAL1.equals(jsonArray.query("/1")));
    }

@Test
    public void enumPut_7_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Leaving these tests because they exercise get, opt, and remove
         */
        assertTrue("expecting myEnum value", MyEnum.VAL2.equals(jsonArray.get(0)));
    }

@Test
    public void enumPut_8_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Leaving these tests because they exercise get, opt, and remove
         */
        // removed other assertion
        assertTrue("expecting myEnumField value", MyEnumField.VAL1.equals(jsonArray.opt(1)));
    }

@Test
    public void enumPut_9_oe() {
        JSONObject jsonObject = new JSONObject();
        MyEnum myEnum = MyEnum.VAL2;
        jsonObject.put("myEnum", myEnum);
        MyEnumField myEnumField = MyEnumField.VAL1;
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Leaving these tests because they exercise get, opt, and remove
         */
        // removed other assertion
        // removed other assertion
        assertTrue("expecting myEnumField value", MyEnumField.VAL1.equals(jsonArray.remove(1)));
    }

@Test
    public void enumValueToString_1_oe() {
        String expectedStr1 = "\"VAL1\"";
        String expectedStr2 = "\"VAL1\"";
        MyEnum myEnum = MyEnum.VAL1;
        MyEnumField myEnumField = MyEnumField.VAL1;
        MyEnumClass myEnumClass = new MyEnumClass();
        
        String str1 = JSONObject.valueToString(myEnum);
        assertTrue("actual myEnum: "+str1+" expected: "+expectedStr1,str1.equals(expectedStr1));
    }

@Test
    public void enumValueToString_2_oe() {
        String expectedStr1 = "\"VAL1\"";
        String expectedStr2 = "\"VAL1\"";
        MyEnum myEnum = MyEnum.VAL1;
        MyEnumField myEnumField = MyEnumField.VAL1;
        MyEnumClass myEnumClass = new MyEnumClass();
        
        String str1 = JSONObject.valueToString(myEnum);
        // removed other assertion
        String str2 = JSONObject.valueToString(myEnumField);
        assertTrue("actual myEnumField: "+str2+" expected: "+expectedStr2,str2.equals(expectedStr2));
    }

@Test
    public void enumValueToString_3_oe() {
        String expectedStr1 = "\"VAL1\"";
        String expectedStr2 = "\"VAL1\"";
        MyEnum myEnum = MyEnum.VAL1;
        MyEnumField myEnumField = MyEnumField.VAL1;
        MyEnumClass myEnumClass = new MyEnumClass();
        
        String str1 = JSONObject.valueToString(myEnum);
        // removed other assertion
        String str2 = JSONObject.valueToString(myEnumField);
        // removed other assertion

        /**
         * However, an enum within another class will not be rendered
         * unless that class overrides default toString() 
         */
        String expectedStr3 = "\"org.json.junit.data.MyEnumClass@";
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL1);
        String str3 = JSONObject.valueToString(myEnumClass);
        assertTrue("actual myEnumClass: "+str3+" expected: "+expectedStr3,str3.startsWith(expectedStr3));
    }

@Test
    public void enumToString_1_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        assertTrue("myEnum toString() should be empty", expectedStr.equals(jsonObject.toString()));
    }

@Test
    public void enumToString_2_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void enumToString_3_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected val 2", "val 2".equals(jsonObject.query("/value")));
    }

@Test
    public void enumToString_4_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonObject.query("/intVal")));
    }

@Test
    public void enumToString_5_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void enumToString_6_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL3", "VAL3".equals((JsonPath.read(doc, "$.myEnumField"))));
    }

@Test
    public void enumToString_7_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL1", "VAL1".equals((JsonPath.read(doc, "$.myEnum"))));
    }

@Test
    public void enumToString_8_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 3 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

@Test
    public void enumToString_9_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL1", MyEnum.VAL1.equals(jsonObject.query("/VAL1")));
    }

@Test
    public void enumToString_10_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL2", MyEnum.VAL2.equals(jsonObject.query("/VAL2")));
    }

@Test
    public void enumToString_11_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL3", MyEnum.VAL3.equals(jsonObject.query("/VAL3")));
    }

@Test
    public void enumToString_12_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 3 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

@Test
    public void enumToString_13_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL1", MyEnumField.VAL1.equals(jsonObject.query("/VAL1")));
    }

@Test
    public void enumToString_14_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL2", MyEnumField.VAL2.equals(jsonObject.query("/VAL2")));
    }

@Test
    public void enumToString_15_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL3", MyEnumField.VAL3.equals(jsonObject.query("/VAL3")));
    }

@Test
    public void enumToString_16_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        expectedStr = "{\"myEnum\":\"VAL2\", \"myEnumField\":\"VAL2\"}";
        jsonObject = new JSONObject();
        jsonObject.putOpt("myEnum", myEnum);
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void enumToString_17_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        expectedStr = "{\"myEnum\":\"VAL2\", \"myEnumField\":\"VAL2\"}";
        jsonObject = new JSONObject();
        jsonObject.putOpt("myEnum", myEnum);
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL2", MyEnum.VAL2.equals(jsonObject.query("/myEnum")));
    }

@Test
    public void enumToString_18_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        expectedStr = "{\"myEnum\":\"VAL2\", \"myEnumField\":\"VAL2\"}";
        jsonObject = new JSONObject();
        jsonObject.putOpt("myEnum", myEnum);
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL2", MyEnumField.VAL2.equals(jsonObject.query("/myEnumField")));
    }

@Test
    public void enumToString_19_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        expectedStr = "{\"myEnum\":\"VAL2\", \"myEnumField\":\"VAL2\"}";
        jsonObject = new JSONObject();
        jsonObject.putOpt("myEnum", myEnum);
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void enumToString_20_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        expectedStr = "{\"myEnum\":\"VAL2\", \"myEnumField\":\"VAL2\"}";
        jsonObject = new JSONObject();
        jsonObject.putOpt("myEnum", myEnum);
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        assertTrue("expected VAL2", MyEnum.VAL2.equals(jsonArray.query("/0")));
    }

@Test
    public void enumToString_21_oe() {
        MyEnum myEnum = MyEnum.VAL2;
        JSONObject jsonObject = new JSONObject(myEnum);
        String expectedStr = "{}";
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        jsonObject = new JSONObject(myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = new JSONObject(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String [] names = JSONObject.getNames(myEnum);
        jsonObject = new JSONObject(myEnum, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        names = JSONObject.getNames(myEnumField);
        jsonObject = new JSONObject(myEnumField, names);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        expectedStr = "{\"myEnum\":\"VAL2\", \"myEnumField\":\"VAL2\"}";
        jsonObject = new JSONObject();
        jsonObject.putOpt("myEnum", myEnum);
        jsonObject.putOnce("myEnumField", myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(myEnum);
        jsonArray.put(1, myEnumField);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL2", MyEnumField.VAL2.equals(jsonArray.query("/1")));
    }

@Test
    public void wrap_1_oe() {
        assertTrue("simple enum has no getters", JSONObject.wrap(MyEnum.VAL2) instanceof MyEnum);
    }

@Test
    public void wrap_2_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 1 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

@Test
    public void wrap_3_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL2", MyEnumField.VAL2.equals(jsonObject.query("/enum")));
    }

@Test
    public void wrap_4_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = (JSONObject)JSONObject.wrap(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

@Test
    public void wrap_5_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = (JSONObject)JSONObject.wrap(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected VAL3", "VAL3".equals((JsonPath.read(doc, "$.myEnumField"))));
    }

@Test
    public void wrap_6_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = (JSONObject)JSONObject.wrap(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected VAL1", "VAL1".equals((JsonPath.read(doc, "$.myEnum"))));
    }

@Test
    public void wrap_7_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = (JSONObject)JSONObject.wrap(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("expecting MyEnumField.VAL3", MyEnumField.VAL3.equals(jsonObject.query("/myEnumField")));
    }

@Test
    public void wrap_8_oe() {
        // removed other assertion

        MyEnumField myEnumField = MyEnumField.VAL2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enum",myEnumField);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion

        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        myEnumClass.setMyEnumField(MyEnumField.VAL3);
        jsonObject = (JSONObject)JSONObject.wrap(myEnumClass);

        // validate JSON content
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertTrue("expecting MyEnum.VAL1", MyEnum.VAL1.equals(jsonObject.query("/myEnum")));
    }

@Test
    public void enumAPI_1_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        assertTrue("get myEnumField", actualEnum == MyEnumField.VAL2);
    }

@Test
    public void enumAPI_3_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        assertTrue("get enum", actualEnumClass.getMyEnum() == MyEnum.VAL1);
    }

@Test
    public void enumAPI_4_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        assertTrue("opt myEnumField", actualEnum == MyEnumField.VAL2);
    }

@Test
    public void enumAPI_5_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        assertTrue("opt null", actualEnum == null);
    }

@Test
    public void enumAPI_6_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        assertTrue("get enum", actualEnumClass.getMyEnum() == MyEnum.VAL1);
    }

@Test
    public void enumAPI_7_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        assertTrue("opt myEnumField", actualEnum == MyEnumField.VAL2);
    }

@Test
    public void enumAPI_8_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        assertNull("opt null", actualEnum);
    }

@Test
    public void enumAPI_9_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        assertEquals(MyEnumField.VAL1, actualEnum);
    }

@Test
    public void enumAPI_10_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        assertNull("opt null", actualEnum);
    }

@Test
    public void enumAPI_11_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        assertNull("Expected Null when the enum class is null",jsonObject.optEnum(null,"enumKey"));
    }

@Test
    public void enumAPI_12_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        assertTrue("get myEnumField", actualEnum == MyEnumField.VAL2);
    }

@Test
    public void enumAPI_14_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        assertTrue("get enum", actualEnumClass.getMyEnum() == MyEnum.VAL1);
    }

@Test
    public void enumAPI_15_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1);
        assertTrue("opt myEnumField", actualEnum == MyEnumField.VAL2);
    }

@Test
    public void enumAPI_16_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1);
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0);
        assertTrue("opt null", actualEnum == null);
    }

@Test
    public void enumAPI_17_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1);
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0);
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.opt(2);
        assertTrue("get enum", actualEnumClass.getMyEnum() == MyEnum.VAL1);
    }

@Test
    public void enumAPI_18_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1);
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0);
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.opt(2);
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1, null);
        assertTrue("opt myEnumField", actualEnum == MyEnumField.VAL2);
    }

@Test
    public void enumAPI_19_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1);
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0);
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.opt(2);
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1, null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0, null);
        assertTrue("opt null", actualEnum == null);
    }

@Test
    public void enumAPI_20_oe() {
        MyEnumClass myEnumClass = new MyEnumClass();
        myEnumClass.setMyEnum(MyEnum.VAL1);
        MyEnumField myEnumField = MyEnumField.VAL2;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strKey", "value");
        jsonObject.put("strKey2", "VAL1");
        jsonObject.put("enumKey", myEnumField);
        jsonObject.put("enumClassKey", myEnumClass);

        // get a plain old enum
        MyEnumField actualEnum = jsonObject.getEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonObject.getEnum(MyEnumField.class, "strKey");
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        MyEnumClass actualEnumClass = (MyEnumClass)jsonObject.get("enumClassKey");
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey");
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey");
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonObject.opt("enumClassKey");
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonObject.optEnum(MyEnumField.class, "enumKey", null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey", null);
        // removed other assertion

        // opt with default the string value
        actualEnum = jsonObject.optEnum(MyEnumField.class, "strKey2", null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonObject.optEnum(MyEnumField.class, "noKey", null);
        // removed other assertion
        
        // removed other assertion

        /**
         * Exercise the proposed enum API methods on JSONArray
         */
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("value");
        jsonArray.put(myEnumField);
        jsonArray.put(myEnumClass);

        // get a plain old enum
        actualEnum = jsonArray.getEnum(MyEnumField.class, 1);
        // removed other assertion

        // try to get the wrong value
        try {
            actualEnum = jsonArray.getEnum(MyEnumField.class, 0);
            // removed other assertion
        } catch (Exception ignored) {}

        // get a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.get(2);
        // removed other assertion

        // opt a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1);
        // removed other assertion

        // opt the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0);
        // removed other assertion

        // opt a class that contains an enum
        actualEnumClass = (MyEnumClass)jsonArray.opt(2);
        // removed other assertion

        // opt with default a plain old enum
        actualEnum = jsonArray.optEnum(MyEnumField.class, 1, null);
        // removed other assertion

        // opt with default the wrong value
        actualEnum = jsonArray.optEnum(MyEnumField.class, 0, null);
        // removed other assertion

        // opt with default an index that does not exist
        actualEnum = jsonArray.optEnum(MyEnumField.class, 3, null);
        assertTrue("opt null", actualEnum == null);
    }

}
