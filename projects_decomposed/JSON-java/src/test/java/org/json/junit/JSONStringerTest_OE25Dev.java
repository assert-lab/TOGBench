package org.json.junit;

/*
Public Domain.
*/

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.*;

import org.json.*;
import org.junit.Test;

import com.jayway.jsonpath.*;


/**
 * Tests for JSON-Java JSONStringer and JSONWriter.
 */
public class JSONStringerTest_OE25Dev {

    /**
     * Object with a null key.
     * Expects a JSONException.
     */

    /**
     * Add a key with no object.
     * Expects a JSONException.
     */

    /**
     * Missplace an array.
     * Expects a JSONException
     */

    /**
     * Missplace an endErray.
     * Expects a JSONException
     */

    /**
     * Missplace an endObject.
     * Expects a JSONException
     */

    /**
     * Missplace an object.
     * Expects a JSONException.
     */

    /**
     * Exceeds implementation max nesting depth.
     * Expects a JSONException
     */

    /**
     * Build a JSON doc using JSONString API calls,
     * then convert to JSONObject
     */

    /**
     * Build a JSON doc using JSONString API calls,
     * then convert to JSONArray
     */

    /**
     * Build a nested JSON doc using JSONString API calls, then convert to
     * JSONObject. Will create a long cascade of output by reusing the
     * returned values..
     */

@Test
    public void nullKeyException_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        try {
            jsonStringer.key(null);
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Null key.". equals(e.getMessage()));
    }
    }

@Test
    public void outOfSequenceException_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        try {
            jsonStringer.key("hi");
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Misplaced key.". equals(e.getMessage()));
    }
    }

@Test
    public void missplacedArrayException_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().endObject();
        try {
            jsonStringer.array();
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Misplaced array.". equals(e.getMessage()));
    }
    }

@Test
    public void missplacedEndArrayException_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        try {
            jsonStringer.endArray();
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Misplaced endArray.". equals(e.getMessage()));
    }
    }

@Test
    public void missplacedEndObjectException_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        try {
            jsonStringer.endObject();
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Misplaced endObject.". equals(e.getMessage()));
    }
    }

@Test
    public void missplacedObjectException_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().endObject();
        try {
            jsonStringer.object();
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Misplaced object.". equals(e.getMessage()));
    }
    }

@Test
    public void exceedNestDepthException_2_oe() {
        try {
            JSONStringer s = new JSONStringer();
            s.object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object();
            s.key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object().
            key("k").object().key("k").object().key("k").object().key("k").object().key("k").object();
            // removed other assertion
        } catch (JSONException e) {
            assertTrue("Expected an exception message","Nesting too deep.". equals(e.getMessage()));
    }
    }

@Test
    public void simpleObjectString_1_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 7 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 7);
    }

@Test
    public void simpleObjectString_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/trueValue")));
    }

@Test
    public void simpleObjectString_3_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/falseValue")));
    }

@Test
    public void simpleObjectString_4_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected null", JSONObject.NULL.equals(jsonObject.query("/nullValue")));
    }

@Test
    public void simpleObjectString_5_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected hello world!", "hello world!".equals(jsonObject.query("/stringValue")));
    }

@Test
    public void simpleObjectString_6_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected h\be\tllo w\u1234orld!", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/complexStringValue")));
    }

@Test
    public void simpleObjectString_7_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", Integer.valueOf(42).equals(jsonObject.query("/intValue")));
    }

@Test
    public void simpleObjectString_8_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("trueValue").value(true);
        jsonStringer.key("falseValue").value(false);
        jsonStringer.key("nullValue").value(null);
        jsonStringer.key("stringValue").value("hello world!");
        jsonStringer.key("complexStringValue").value("h\be\tllo w\u1234orld!");
        jsonStringer.key("intValue").value(42);
        jsonStringer.key("doubleValue").value(-23.45e67);
        jsonStringer.endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected -23.45e67", BigDecimal.valueOf(-23.45e67).equals(jsonObject.query("/doubleValue")));
    }

@Test
    public void simpleArrayString_1_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 6 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

@Test
    public void simpleArrayString_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonArray.query("/0")));
    }

@Test
    public void simpleArrayString_3_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonArray.query("/1")));
    }

@Test
    public void simpleArrayString_4_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected null", JSONObject.NULL.equals(jsonArray.query("/2")));
    }

@Test
    public void simpleArrayString_5_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected hello world!", "hello world!".equals(jsonArray.query("/3")));
    }

@Test
    public void simpleArrayString_6_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", Integer.valueOf(42).equals(jsonArray.query("/4")));
    }

@Test
    public void simpleArrayString_7_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.array();
        jsonStringer.value(true);
        jsonStringer.value(false);
        jsonStringer.value(null);
        jsonStringer.value("hello world!");
        jsonStringer.value(42);
        jsonStringer.value(-23.45e67);
        jsonStringer.endArray();
        String str = jsonStringer.toString();
        JSONArray jsonArray = new JSONArray(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected -23.45e67", BigDecimal.valueOf(-23.45e67).equals(jsonArray.query("/5")));
    }

@Test
    public void complexObjectString_1_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 8 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 8);
    }

@Test
    public void complexObjectString_2_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 4 object2 items", ((Map<?,?>)(JsonPath.read(doc, "$.object2"))).size() == 4);
    }

@Test
    public void complexObjectString_3_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 5 array1 items", ((List<?>)(JsonPath.read(doc, "$.object2.array1"))).size() == 5);
    }

@Test
    public void complexObjectString_4_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 4 array[2] items", ((Map<?,?>)(JsonPath.read(doc, "$.object2.array1[2]"))).size() == 4);
    }

@Test
    public void complexObjectString_5_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 4 array1[2].array2 items", ((List<?>)(JsonPath.read(doc, "$.object2.array1[2].array2"))).size() == 4);
    }

@Test
    public void complexObjectString_6_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/trueValue")));
    }

@Test
    public void complexObjectString_7_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/falseValue")));
    }

@Test
    public void complexObjectString_8_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected null", JSONObject.NULL.equals(jsonObject.query("/nullValue")));
    }

@Test
    public void complexObjectString_9_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected hello world!", "hello world!".equals(jsonObject.query("/stringValue")));
    }

@Test
    public void complexObjectString_10_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", Integer.valueOf(42).equals(jsonObject.query("/intValue")));
    }

@Test
    public void complexObjectString_11_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected -23.45e67", BigDecimal.valueOf(-23.45e67).equals(jsonObject.query("/doubleValue")));
    }

@Test
    public void complexObjectString_12_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected h\be\tllo w\u1234orld!", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/complexStringValue")));
    }

@Test
    public void complexObjectString_13_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected v1", "v1".equals(jsonObject.query("/object2/k1")));
    }

@Test
    public void complexObjectString_14_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected v2", "v2".equals(jsonObject.query("/object2/k2")));
    }

@Test
    public void complexObjectString_15_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected v3", "v3".equals(jsonObject.query("/object2/k3")));
    }

@Test
    public void complexObjectString_16_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonObject.query("/object2/array1/0")));
    }

@Test
    public void complexObjectString_17_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonObject.query("/object2/array1/1")));
    }

@Test
    public void complexObjectString_18_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected v4", "v4".equals(jsonObject.query("/object2/array1/2/k4")));
    }

@Test
    public void complexObjectString_19_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected v5", "v5".equals(jsonObject.query("/object2/array1/2/k5")));
    }

@Test
    public void complexObjectString_20_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected v6", "v6".equals(jsonObject.query("/object2/array1/2/k6")));
    }

@Test
    public void complexObjectString_21_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 5", Integer.valueOf(5).equals(jsonObject.query("/object2/array1/2/array2/0")));
    }

@Test
    public void complexObjectString_22_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 6", Integer.valueOf(6).equals(jsonObject.query("/object2/array1/2/array2/1")));
    }

@Test
    public void complexObjectString_23_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 7", Integer.valueOf(7).equals(jsonObject.query("/object2/array1/2/array2/2")));
    }

@Test
    public void complexObjectString_24_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 8", Integer.valueOf(8).equals(jsonObject.query("/object2/array1/2/array2/3")));
    }

@Test
    public void complexObjectString_25_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3", Integer.valueOf(3).equals(jsonObject.query("/object2/array1/3")));
    }

@Test
    public void complexObjectString_26_oe() {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object().
            key("trueValue").value(true).
            key("falseValue").value(false).
            key("nullValue").value(null).
            key("stringValue").value("hello world!").
            key("object2").object().
            key("k1").value("v1").
            key("k2").value("v2").
            key("k3").value("v3").
            key("array1").array().
            value(1).
            value(2).
            object().
            key("k4").value("v4").
            key("k5").value("v5").
            key("k6").value("v6").
            key("array2").array().
            value(5).
            value(6).
            value(7).
            value(8).
            endArray().
            endObject().
            value(3).
            value(4).
            endArray().
            endObject().
            key("complexStringValue").value("h\be\tllo w\u1234orld!").
            key("intValue").value(42).
            key("doubleValue").value(-23.45e67).
            endObject();
        String str = jsonStringer.toString();
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 4", Integer.valueOf(4).equals(jsonObject.query("/object2/array1/4")));
    }

}
