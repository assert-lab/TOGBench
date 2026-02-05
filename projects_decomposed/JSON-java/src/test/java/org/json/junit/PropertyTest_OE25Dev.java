package org.json.junit;

/*
Public Domain.
*/

import java.util.*;

import static org.junit.Assert.*;

import org.json.*;
import org.junit.Test;


/**
 * Tests for JSON-Java Property
 */
public class PropertyTest_OE25Dev {

    /**
     * JSONObject from null properties object should
     * result in an empty JSONObject.
     */

    /**
     * JSONObject from empty properties object should
     * result in an empty JSONObject.
     */

    /**
     * JSONObject from simple properties object.
     */

    /**
     * Null JSONObject toProperties() should result in an empty
     * Properties object.
     */

    /**
     * Properties should convert to JSONObject, and back to
     * Properties without changing.
     */

@Test
    public void shouldHandleNullProperties_1_oe() {
        Properties properties = null;
        JSONObject jsonObject = Property.toJSONObject(properties);
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

@Test
    public void shouldHandleEmptyProperties_1_oe() {
        Properties properties = new Properties();
        JSONObject jsonObject = Property.toJSONObject(properties);
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

@Test
    public void shouldHandleProperties_1_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);

        assertTrue("jsonObject should contain 5 items", jsonObject.length() == 5);
    }

@Test
    public void shouldHandleProperties_2_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);

        // removed other assertion
        assertTrue("jsonObject should contain Illinois property","Springfield".equals(jsonObject.get("Illinois")));
    }

@Test
    public void shouldHandleProperties_3_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);

        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject should contain Missouri property","Jefferson City".equals(jsonObject.get("Missouri")));
    }

@Test
    public void shouldHandleProperties_4_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject should contain Washington property","Olympia".equals(jsonObject.get("Washington")));
    }

@Test
    public void shouldHandleProperties_5_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject should contain California property","Sacramento".equals(jsonObject.get("California")));
    }

@Test
    public void shouldHandleProperties_6_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject should contain Indiana property","Indianapolis".equals(jsonObject.get("Indiana")));
    }

@Test
    public void shouldHandleNullJSONProperty_1_oe() {
        JSONObject jsonObject= null;
        Properties properties = Property.toProperties(jsonObject);
        assertTrue("properties should be empty",properties.size()== 0);
    }

@Test
    public void shouldHandleJSONProperty_1_oe() {
        Properties properties = new Properties();
        
        properties.put("Illinois", "Springfield");
        properties.put("Missouri", "Jefferson City");
        properties.put("Washington", "Olympia");
        properties.put("California", "Sacramento");
        properties.put("Indiana", "Indianapolis");

        JSONObject jsonObject = Property.toJSONObject(properties);
        Properties jsonProperties = Property.toProperties(jsonObject);

        assertTrue("property objects should match",properties.equals(jsonProperties));
    }

}