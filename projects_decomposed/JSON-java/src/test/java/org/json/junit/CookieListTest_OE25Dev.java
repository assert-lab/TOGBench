package org.json.junit;

/*
Public Domain.
*/

import static org.junit.Assert.*;

import java.util.*;

import org.json.*;
import org.junit.Test;

import com.jayway.jsonpath.*;

/**
 * HTTP cookie specification RFC6265: http://tools.ietf.org/html/rfc6265
 * <p>
 * A cookie list is a JSONObject whose members are presumed to be cookie
 * name/value pairs. Entries are unescaped while being added, and escaped in 
 * the toString() output.
 * Unescaping means to convert %hh hex strings to the ascii equivalent
 * and converting '+' to ' '.
 * Escaping converts '+', '%', '=', ';' and ascii control chars to %hh hex strings.
 * <p>
 * CookieList should not be considered as just a list of Cookie objects:<br>
 * - CookieList stores a cookie name/value pair as a single entry; Cookie stores 
 *      it as 2 entries (key="name" and key="value").<br>
 * - CookieList requires multiple name/value pairs as input; Cookie allows the
 *      'secure' name with no associated value<br>
 * - CookieList has no special handling for attribute name/value pairs.<br>
 */
public class CookieListTest_OE25Dev {

    /**
     * Attempts to create a CookieList from a null string.
     * Expects a NullPointerException.
     */
    @Test(expected=NullPointerException.class)
    public void nullCookieListException() {
        String cookieStr = null;
        CookieList.toJSONObject(cookieStr);
    }

    /**
     * Attempts to create a CookieList from a malformed string.
     * Expects a JSONException.
     */

    /**
     * Creates a CookieList from an empty string.
     */

    /**
     * CookieList with the simplest cookie - a name/value pair with no delimiter.
     */

    /**
     * CookieList with a single a cookie which has a name/value pair and delimiter.
     */

    /**
     * CookieList with multiple cookies consisting of name/value pairs
     * with delimiters.
     */

    /**
     * CookieList from a JSONObject with valid key and null value
     */

    /**
     * CookieList with multiple entries converted to a JSON document. 
     */

    /**
     * CookieList with multiple entries and some '+' chars and URL-encoded
     * values converted to a JSON document. 
     */

@Test
    public void malFormedCookieListException_2_oe() {
        String cookieStr = "thisCookieHasNoEqualsChar";
        try {
            CookieList.toJSONObject(cookieStr);
            // removed other assertion
        } catch (JSONException e) {
            /**
             * Not sure of the missing char, but full string compare fails 
             */
            assertEquals("Expecting an exception message","Expected '=' and instead saw '' at 25 [character 26 line 1]",e.getMessage());
    }
    }

@Test
    public void emptyStringCookieList_1_oe() {
        String cookieStr = "";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        assertTrue(jsonObject.isEmpty());
    }

@Test
    public void simpleCookieList_1_oe() {
        String cookieStr = "SID=31d4d96e407aad42";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("Expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

@Test
    public void simpleCookieList_2_oe() {
        String cookieStr = "SID=31d4d96e407aad42";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 31d4d96e407aad42", "31d4d96e407aad42".equals(jsonObject.query("/SID")));
    }

@Test
    public void simpleCookieListWithDelimiter_1_oe() {
        String cookieStr = "SID=31d4d96e407aad42;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("Expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

@Test
    public void simpleCookieListWithDelimiter_2_oe() {
        String cookieStr = "SID=31d4d96e407aad42;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 31d4d96e407aad42", "31d4d96e407aad42".equals(jsonObject.query("/SID")));
    }

@Test
    public void multiPartCookieList_1_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("Expected 6 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

@Test
    public void multiPartCookieList_2_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected myCookieValue1", "myCookieValue1".equals(jsonObject.query("/name1")));
    }

@Test
    public void multiPartCookieList_3_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue2", "myCookieValue2".equals(jsonObject.query("/name2")));
    }

@Test
    public void multiPartCookieList_4_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue3", "myCookieValue3".equals(jsonObject.query("/name3")));
    }

@Test
    public void multiPartCookieList_5_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue4", "myCookieValue4".equals(jsonObject.query("/name4")));
    }

@Test
    public void multiPartCookieList_6_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue5", "myCookieValue5".equals(jsonObject.query("/name5")));
    }

@Test
    public void multiPartCookieList_7_oe() {
        String cookieStr = 
            "name1=myCookieValue1;  "+
            "  name2=myCookieValue2;"+
            "name3=myCookieValue3;"+
            "  name4=myCookieValue4;  "+
            "name5=myCookieValue5;"+
            "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue6", "myCookieValue6".equals(jsonObject.query("/name6")));
    }

@Test
    public void convertCookieListWithNullValueToString_1_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key",  JSONObject.NULL);
        String cookieToStr = CookieList.toString(jsonObject);
        assertTrue("toString() should be empty", "".equals(cookieToStr));
    }

@Test
    public void convertCookieListToString_1_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("Expected 6 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

@Test
    public void convertCookieListToString_2_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected myCookieValue1", "myCookieValue1".equals(jsonObject.query("/name1")));
    }

@Test
    public void convertCookieListToString_3_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue2", "myCookieValue2".equals(jsonObject.query("/name2")));
    }

@Test
    public void convertCookieListToString_4_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue3", "myCookieValue3".equals(jsonObject.query("/name3")));
    }

@Test
    public void convertCookieListToString_5_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue4", "myCookieValue4".equals(jsonObject.query("/name4")));
    }

@Test
    public void convertCookieListToString_6_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue5", "myCookieValue5".equals(jsonObject.query("/name5")));
    }

@Test
    public void convertCookieListToString_7_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=myCookieValue2;"+
                "name3=myCookieValue3;"+
                "  name4=myCookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // exercise CookieList.toString()
        String cookieListString = CookieList.toString(jsonObject);
        // have to convert it back for validation
        jsonObject = CookieList.toJSONObject(cookieListString);

        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue6", "myCookieValue6".equals(jsonObject.query("/name6")));
    }

@Test   
    public void convertEncodedCookieListToString_1_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("Expected 6 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

@Test   
    public void convertEncodedCookieListToString_2_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected myCookieValue1", "myCookieValue1".equals(jsonObject.query("/name1")));
    }

@Test   
    public void convertEncodedCookieListToString_3_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected my Cookie Value 2", "my Cookie Value 2".equals(jsonObject.query("/name2")));
    }

@Test   
    public void convertEncodedCookieListToString_4_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected my+Cookie&Value;3=", "my+Cookie&Value;3=".equals(jsonObject.query("/name3")));
    }

@Test   
    public void convertEncodedCookieListToString_5_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected my%CookieValue4", "my%CookieValue4".equals(jsonObject.query("/name4")));
    }

@Test   
    public void convertEncodedCookieListToString_6_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected my%CookieValue5", "myCookieValue5".equals(jsonObject.query("/name5")));
    }

@Test   
    public void convertEncodedCookieListToString_7_oe() {
        String cookieStr = 
                "name1=myCookieValue1;  "+
                "  name2=my+Cookie+Value+2;"+
                "name3=my%2BCookie%26Value%3B3%3D;"+
                "  name4=my%25CookieValue4;  "+
                "name5=myCookieValue5;"+
                "  name6=myCookieValue6;";
        JSONObject jsonObject = CookieList.toJSONObject(cookieStr);
        // validate JSON content
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myCookieValue6", "myCookieValue6".equals(jsonObject.query("/name6")));
    }

}
