package org.json.junit;

/*
Public Domain.
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointerException;
import org.json.JSONString;
import org.json.JSONTokener;
import org.json.XML;
import org.json.junit.data.BrokenToString;
import org.json.junit.data.ExceptionalBean;
import org.json.junit.data.Fraction;
import org.json.junit.data.GenericBean;
import org.json.junit.data.GenericBeanInt;
import org.json.junit.data.MyBean;
import org.json.junit.data.MyBeanCustomName;
import org.json.junit.data.MyBeanCustomNameSubClass;
import org.json.junit.data.MyBigNumberBean;
import org.json.junit.data.MyEnum;
import org.json.junit.data.MyEnumField;
import org.json.junit.data.MyJsonString;
import org.json.junit.data.MyNumber;
import org.json.junit.data.MyNumberContainer;
import org.json.junit.data.MyPublicClass;
import org.json.junit.data.RecursiveBean;
import org.json.junit.data.RecursiveBeanEquals;
import org.json.junit.data.Singleton;
import org.json.junit.data.SingletonEnum;
import org.json.junit.data.WeirdList;
import org.junit.Test;
import org.json.junit.Util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * JSONObject, along with JSONArray, are the central classes of the reference app.
 * All of the other classes interact with them, and JSON functionality would
 * otherwise be impossible.
 */
public class JSONObjectTest_OE25Dev {

    /**
     *  Regular Expression Pattern that matches JSON Numbers. This is primarily used for
     *  output to guarantee that we are always writing valid JSON. 
     */
    static final Pattern NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");

    /**
     * Tests that the similar method is working as expected.
     */

    @Test
    public void timeNumberParsing() {
        // test data to use
        final String[] testData = new String[] {
                null,
                "",
                "100",
                "-100",
                "abc123",
                "012345",
                "100.5e199",
                "-100.5e199",
                "DEADBEEF",
                "0xDEADBEEF",
                "1234567890.1234567890",
                "-1234567890.1234567890",
                "adloghakuidghauiehgauioehgdkjfb nsruoh aeu noerty384 nkljfgh "
                    + "395h tdfn kdz8yt3 4hkls gn.ey85 4hzfhnz.o8y5a84 onvklt "
                    + "yh389thub nkz8y49lihv al4itlaithknty8hnbl"
                // long (in length) number sequences with invalid data at the end of the
                // string offer very poor performance for the REGEX.
                ,"123467890123467890123467890123467890123467890123467890123467"
                    + "8901234678901234678901234678901234678901234678901234678"
                    + "9012346789012346789012346789012346789012346789012346789"
                    + "0a"
        };
        final int testDataLength = testData.length;
        /**
         * Changed to 1000 for faster test runs
         */
        // final int iterations = 1000000;
        final int iterations = 1000;

        // 10 million iterations 1,000,000 * 10 (currently 100,000)
        long startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            for(int j = 0; j < testDataLength; j++) {
                try {
                BigDecimal v1 = new BigDecimal(testData[j]);
                v1.signum();
                } catch(Exception ignore) {
                    //do nothing
                }
            }
        }
        final long elapsedNano1 = System.nanoTime() - startTime;
        System.out.println("new BigDecimal(testData[]) : " + elapsedNano1 / 1000000 + " ms");

        startTime = System.nanoTime();
        for(int i = 0; i < iterations; i++) {
            for(int j = 0; j < testDataLength; j++) {
                try {
                boolean v2 = NUMBER_PATTERN.matcher(testData[j]).matches();
                assert v2 == !!v2;
                } catch(Exception ignore) {
                    //do nothing
                }
            }
        }
        final long elapsedNano2 = System.nanoTime() - startTime;
        System.out.println("NUMBER_PATTERN.matcher(testData[]).matches() : " + elapsedNano2 / 1000000 + " ms");
        // don't assert normally as the testing is machine dependent.
        // assertTrue("Expected Pattern matching to be faster than BigDecimal constructor",elapsedNano2<elapsedNano1);
   }

    /**
     * JSONObject built from a bean, but only using a null value.
     * Nothing good is expected to happen.
     * Expects NullPointerException
     */
    
    /**
     * The JSON parser is permissive of unambiguous unquoted keys and values.
     * Such JSON text should be allowed, even if it does not strictly conform
     * to the spec. However, after being parsed, toString() should emit strictly
     * conforming JSON text.  
     */
    
    /**
     * A JSONObject can be created with no content
     */

    /**
     * A JSONObject can be created from another JSONObject plus a list of names.
     * In this test, some of the starting JSONObject keys are not in the 
     * names list.
     */

    /**
     * JSONObjects can be built from a Map<String, Object>. 
     * In this test the map is null.
     * the JSONObject(JsonTokener) ctor is not tested directly since it already
     * has full coverage from other tests.
     */

    /**
     * JSONObjects can be built from a Map<String, Object>. 
     * In this test all of the map entries are valid JSON types.
     */

    /**
     * Verifies that the constructor has backwards compatability with RAW types pre-java5.
     */
    
    /**
     * Tests Number serialization.
     */

    /**
     * Verifies that the put Collection has backwards compatibility with RAW types pre-java5.
     */

    
    /**
     * Verifies that the put Map has backwards compatibility with RAW types pre-java5.
     */


    /**
     * JSONObjects can be built from a Map<String, Object>. 
     * In this test the map entries are not valid JSON types.
     * The actual conversion is kind of interesting.
     */

    /**
     * JSONObjects can be built from a Map<String, Object>. 
     * In this test one of the map values is null 
     */

    /**
     * JSONObject built from a bean. In this case all but one of the 
     * bean getters return valid JSON types
     */

    /**
     * JSONObject built from a bean that has custom field names.
     */
    
    /**
     * JSONObject built from a bean that has custom field names inherited from a parent class.
     */

    /**
     * A bean is also an object. But in order to test the JSONObject
     * ctor that takes an object and a list of names, 
     * this particular bean needs some public
     * data members, which have been added to the class.
     */

    /**
     * Exercise the JSONObject from resource bundle functionality.
     * The test resource bundle is uncomplicated, but provides adequate test coverage.
     */
    
    /**
     * Exercise the JSONObject.accumulate() method
     */

    /**
     * Exercise the JSONObject append() functionality
     */

    /**
     * Exercise the JSONObject doubleToString() method
     */

    /**
     * Exercise some JSONObject get[type] and opt[type] methods
     */

    /**
     * Check whether JSONObject handles large or high precision numbers correctly
     */

    /**
     * This test documents numeric values which could be numerically
     * handled as BigDecimal or BigInteger. It helps determine what outputs
     * will change if those types are supported.
     */

    /**
     * This test documents how JSON-Java handles invalid numeric input.
     */

    /**
     * Tests how JSONObject get[type] handles incorrect types
     */

    /**
     * This test documents an unexpected numeric behavior.
     * A double that ends with .0 is parsed, serialized, then
     * parsed again. On the second parse, it has become an int.
     */

    /**
     * Document behaviors of big numbers. Includes both JSONObject
     * and JSONArray tests
     */

    /**
     * The purpose for the static method getNames() methods are not clear.
     * This method is not called from within JSON-Java. Most likely
     * uses are to prep names arrays for:  
     * JSONObject(JSONObject jo, String[] names)
     * JSONObject(Object object, String names[]),
     */

    /**
     * Populate a JSONArray from an empty JSONObject names() method.
     * It should be empty.
     */

    /**
     * Populate a JSONArray from a JSONObject names() method.
     * Confirm that it contains the expected names.
     */

    /**
     * Exercise the JSONObject increment() method.
     */

    /**
     * Exercise JSONObject numberToString() method
     */

    /**
     * Exercise JSONObject put() and similar() methods
     */

    /**
     * Exercise JSONObject toString() method
     */

    /**
     * Exercise JSONObject toString() method with various indent levels.
     */

    /**
     * Explores how JSONObject handles maps. Insert a string/string map
     * as a value in a JSONObject. It will remain a map. Convert the 
     * JSONObject to string, then create a new JSONObject from the string. 
     * In the new JSONObject, the value will be stored as a nested JSONObject.
     * Confirm that map and nested JSONObject have the same contents.
     */

    /**
     * Explores how JSONObject handles collections. Insert a string collection
     * as a value in a JSONObject. It will remain a collection. Convert the 
     * JSONObject to string, then create a new JSONObject from the string. 
     * In the new JSONObject, the value will be stored as a nested JSONArray.
     * Confirm that collection and nested JSONArray have the same contents.
     */

    /**
     * Exercises the JSONObject.valueToString() method for various types
     */

    /**
     * Confirm that https://github.com/douglascrockford/JSON-java/issues/167 is fixed.
     * The following code was throwing a ClassCastException in the 
     * JSONObject(Map<String, Object>) constructor
     */

    /**
     * Exercise the JSONObject wrap() method. Sometimes wrap() will change
     * the object being wrapped, other times not. The purpose of wrap() is
     * to ensure the value is packaged in a way that is compatible with how
     * a JSONObject value or JSONArray value is supposed to be stored.
     */

    
    /**
     * RFC 7159 defines control characters to be U+0000 through U+001F. This test verifies that the parser is checking for these in expected ways.
     */

    /**
     * Explore how JSONObject handles parsing errors.
     */

    /**
     * Confirm behavior when putOnce() is called with null parameters
     */

    /**
     * Exercise JSONObject opt(key, default) method.
     */
    
    /**
     * Exercise JSONObject opt(key, default) method when the key doesn't exist.
     */
    
    /**
     * Verifies that the opt methods properly convert string values.
     */
    
    /**
     * Verifies that the opt methods properly convert string values to numbers and coerce them consistently.
     */
    
    /**
     * Verifies that the optBigDecimal method properly converts values to BigDecimal and coerce them consistently.
     */
    
    /**
     * Verifies that the optBigDecimal method properly converts values to BigDecimal and coerce them consistently.
     */

    /**
     * Confirm behavior when JSONObject put(key, null object) is called
     */

    /**
     * Exercise JSONObject quote() method
     * This purpose of quote() is to ensure that for strings with embedded
     * quotes, the quotes are properly escaped.
     */

    /**
     * Confirm behavior when JSONObject stringToValue() is called for an
     * empty string
     */

    /**
     * Confirm behavior when toJSONArray is called with a null value
     */

    /**
     * Exercise the JSONObject write() method
     */
    @Test
    public void write() throws IOException {
        String str = "{\"key1\":\"value1\",\"key2\":[1,2,3]}";
        String expectedStr = str;
        JSONObject jsonObject = new JSONObject(str);
        StringWriter stringWriter = new StringWriter();
        try {
            String actualStr = jsonObject.write(stringWriter).toString();
            // key order may change. verify length and individual key content
            assertEquals("length", expectedStr.length(), actualStr.length());
            assertTrue("key1", actualStr.contains("\"key1\":\"value1\""));
            assertTrue("key2", actualStr.contains("\"key2\":[1,2,3]"));
        } finally {
            stringWriter.close();
        }
        Util.checkJSONObjectMaps(jsonObject);
    }
    
    /**
     * Confirms that exceptions thrown when writing values are wrapped properly.
     */


    /**
     * Exercise the JSONObject write() method
     */
/*
    @Test
    public void writeAppendable() {
        String str = "{\"key1\":\"value1\",\"key2\":[1,2,3]}";
        String expectedStr = str;
        JSONObject jsonObject = new JSONObject(str);
        StringBuilder stringBuilder = new StringBuilder();
        Appendable appendable = jsonObject.write(stringBuilder);
        String actualStr = appendable.toString();
        assertTrue("write() expected " +expectedStr+
                        " but found " +actualStr,
                expectedStr.equals(actualStr));
    }
*/

    /**
     * Exercise the JSONObject write(Writer, int, int) method
     */
    @Test
    public void write3Param() throws IOException {
        String str0 = "{\"key1\":\"value1\",\"key2\":[1,false,3.14]}";
        String str2 =
                "{\n" +
                "   \"key1\": \"value1\",\n" +
                "   \"key2\": [\n" +
                "     1,\n" +
                "     false,\n" +
                "     3.14\n" +
                "   ]\n" +
                " }";
        JSONObject jsonObject = new JSONObject(str0);
        StringWriter stringWriter = new StringWriter();
        try {
            String actualStr = jsonObject.write(stringWriter,0,0).toString();
            
            assertEquals("length", str0.length(), actualStr.length());
            assertTrue("key1", actualStr.contains("\"key1\":\"value1\""));
            assertTrue("key2", actualStr.contains("\"key2\":[1,false,3.14]"));
        } finally {
            try {
                stringWriter.close();
            } catch (Exception e) {}
        }
        
        stringWriter = new StringWriter();
        try {
            String actualStr = jsonObject.write(stringWriter,2,1).toString();

            assertEquals("length", str2.length(), actualStr.length());
            assertTrue("key1", actualStr.contains("   \"key1\": \"value1\""));
            assertTrue("key2", actualStr.contains("   \"key2\": [\n" +
                            "     1,\n" +
                            "     false,\n" +
                            "     3.14\n" +
                            "   ]")
            );
        } finally {
            try {
                stringWriter.close();
            } catch (Exception e) {}
        }
        Util.checkJSONObjectMaps(jsonObject);
    }

    /**
     * Exercise the JSONObject write(Appendable, int, int) method
     */
/*
    @Test
    public void write3ParamAppendable() {
        String str0 = "{\"key1\":\"value1\",\"key2\":[1,false,3.14]}";
        String str2 =
                "{\n" +
                        "   \"key1\": \"value1\",\n" +
                        "   \"key2\": [\n" +
                        "     1,\n" +
                        "     false,\n" +
                        "     3.14\n" +
                        "   ]\n" +
                        " }";
        JSONObject jsonObject = new JSONObject(str0);
        String expectedStr = str0;
        StringBuilder stringBuilder = new StringBuilder();
        Appendable appendable = jsonObject.write(stringBuilder,0,0);
        String actualStr = appendable.toString();
        assertEquals(expectedStr, actualStr);

        expectedStr = str2;
        stringBuilder = new StringBuilder();
        appendable = jsonObject.write(stringBuilder,2,1);
        actualStr = appendable.toString();
        assertEquals(expectedStr, actualStr);
    }
*/

    /**
     * Exercise the JSONObject equals() method
     */

    /**
     * JSON null is not the same as Java null. This test examines the differences
     * in how they are handled by JSON-java.
     */
    
    @Test(expected = JSONPointerException.class)
    public void queryWithNoResult() {
        new JSONObject().query("/a/b");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void optQueryWithSyntaxError() {
        new JSONObject().optQuery("invalid");
    }

    /**
     * Exercise JSONObject toMap() method.
     */
    
    /**
     * test that validates a singleton can be serialized as a bean.
     */

    /**
     * test that validates a singleton can be serialized as a bean.
     */
    
    /**
     * Test to validate that a generic class can be serialized as a bean.
     */
    
    /**
     * Test to validate that a generic class can be serialized as a bean.
     */
    
    /**
     * Test to verify <code>key</code> limitations in the JSONObject bean serializer.
     */
    
    /**
     * Sample test case from https://github.com/stleary/JSON-java/issues/531
     * which verifies that no regression in double/BigDecimal support is present.
     */
    
    /**
     * Tests the exception portions of populateMap.
     */
    @Test
    public void testRepeatObjectNotRecursive() {
        // C -> B -> A
        //        -> A
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        RecursiveBean ObjB = new RecursiveBean("ObjB");
        RecursiveBean ObjC = new RecursiveBean("ObjC");
        ObjC.setRef(ObjA);
        ObjB.setRef(ObjA);
        ObjB.setRef2(ObjA);
        JSONObject j0 = new JSONObject(ObjC);
        JSONObject j1 = new JSONObject(ObjB);
        JSONObject j2 = new JSONObject(ObjA);
        Util.checkJSONObjectsMaps(new ArrayList<JSONObject>(Arrays.asList(
                j0, j1, j2
        )));
    }
    @Test
    public void testLongRepeatObjectNotRecursive() {
        // C -> B -> A -> D -> E
        //        -> D -> E
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        RecursiveBean ObjB = new RecursiveBean("ObjB");
        RecursiveBean ObjC = new RecursiveBean("ObjC");
        RecursiveBean ObjD = new RecursiveBean("ObjD");
        RecursiveBean ObjE = new RecursiveBean("ObjE");
        ObjC.setRef(ObjB);
        ObjB.setRef(ObjA);
        ObjB.setRef2(ObjD);
        ObjA.setRef(ObjD);
        ObjD.setRef(ObjE);
        JSONObject j0 = new JSONObject(ObjC);
        JSONObject j1 = new JSONObject(ObjB);
        JSONObject j2 = new JSONObject(ObjA);
        JSONObject j3 = new JSONObject(ObjD);
        JSONObject j4 = new JSONObject(ObjE);
        Util.checkJSONObjectsMaps(new ArrayList<JSONObject>(Arrays.asList(
                j0, j1, j2, j3, j4
        )));
    }
    @Test(expected=JSONException.class)
    public void testRecursiveEquals() {
        RecursiveBeanEquals a = new RecursiveBeanEquals("same");
        a.setRef(a);
        JSONObject j0 = new JSONObject(a);
        Util.checkJSONObjectMaps(j0);
    }
    @Test
    public void testNotRecursiveEquals() {
        RecursiveBeanEquals a = new RecursiveBeanEquals("same");
        RecursiveBeanEquals b = new RecursiveBeanEquals("same");
        RecursiveBeanEquals c = new RecursiveBeanEquals("same");
        a.setRef(b);
        b.setRef(c);
        JSONObject j0 = new JSONObject(a);
        Util.checkJSONObjectMaps(j0);
    }

    /**
    * Tests if calling JSONObject clear() method actually makes the JSONObject empty
    */

    /**
    * Tests for stack overflow. See https://github.com/stleary/JSON-java/issues/654
    */
    @Test(expected = JSONException.class)
    public void issue654StackOverflowInput() {
        //String base64Bytes ="eyJHWiI6Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7ewl7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMCkwLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7CXt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7ewl7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMCkwLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7CXt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3sJe3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTApMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7ewl7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3sJe3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTApMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMCkwLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7CXt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7ewl7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMCkwLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7CXt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3sJe3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTApMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7ewl7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3sJe3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTApMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7ewl7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7c3t7e3t7e3vPAAAAAAAAAHt7e3t7e3t7e3t7e3t7e3t7e3t7e1ste3t7e3t7e3t7e3t7e3t7e3t7e3t7CXt7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3tbLTAtMCx7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e1stMC0wLHt7e3t7e3t7e3t7e3t7e3t7e88AAAAAAAAAe3t7e3t7e3t7e3t7e3t7e3t7e3t7Wy0wLTAse3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7e3t7f3syMv//e3t7e3t7e3t7e3t7e3sx//////8=";
        //String input = new String(java.util.Base64.getDecoder().decode(base64Bytes));
        String input = "{\"GZ\":[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{  {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{    {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{  {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{    {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{    {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{  {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{    {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0)0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{   {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{{{{{{{{{{{{{{{{{{{{[-0-0,{{{{{{{{{{s{{{{{{{";
        JSONObject json_input = new JSONObject(input);
        assertNotNull(json_input);
        fail("Excepected Exception.");
        Util.checkJSONObjectMaps(json_input);
    }

    /**
    * Tests for incorrect object/array nesting. See https://github.com/stleary/JSON-java/issues/654
    */
    @Test(expected = JSONException.class)
    public void issue654IncorrectNestingNoKey1() {
        JSONObject json_input = new JSONObject("{{\"a\":0}}");
        assertNotNull(json_input);
        fail("Expected Exception.");
    }

    /**
    * Tests for incorrect object/array nesting. See https://github.com/stleary/JSON-java/issues/654
    */
    @Test(expected = JSONException.class)
    public void issue654IncorrectNestingNoKey2() {
        JSONObject json_input = new JSONObject("{[\"a\"]}");
        assertNotNull(json_input);
        fail("Excepected Exception.");
    }
    
    /**
    * Tests for stack overflow. See https://github.com/stleary/JSON-java/issues/654
    */
    @Test(expected = JSONException.class)
    public void issue654StackOverflowInputWellFormed() {
        //String input = new String(java.util.Base64.getDecoder().decode(base64Bytes));
        final InputStream resourceAsStream = JSONObjectTest_OE25Dev.class.getClassLoader().getResourceAsStream("Issue654WellFormedObject.json");
        JSONTokener tokener = new JSONTokener(resourceAsStream);
        JSONObject json_input = new JSONObject(tokener);
        assertNotNull(json_input);
        fail("Excepected Exception.");
    }

    @Test
    public void testIssue682SimilarityOfJSONString() {
        JSONObject jo1 = new JSONObject()
                .put("a", new MyJsonString())
                .put("b", 2);
        JSONObject jo2 = new JSONObject()
                .put("a", new MyJsonString())
                .put("b", 2);
        assertTrue(jo1.similar(jo2));

        JSONObject jo3 = new JSONObject()
                .put("a", new JSONString() {
                    @Override
                    public String toJSONString() {
                        return "\"different value\"";
                    }
                })
                .put("b", 2);
        assertFalse(jo1.similar(jo3));
    }

    @Test
    public void verifySimilar_1_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONObject obj1 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", string1);
        
        JSONObject obj2 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 3)
                .put("key3", string1);

        JSONObject obj3 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", new String(string1));
        
        JSONObject obj4 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string1));

        JSONObject obj5 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string2));
        
        assertFalse("obj1-obj2 Should eval to false", obj1.similar(obj2));
    }

    @Test
    public void verifySimilar_2_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONObject obj1 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", string1);
        
        JSONObject obj2 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 3)
                .put("key3", string1);

        JSONObject obj3 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", new String(string1));
        
        JSONObject obj4 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string1));

        JSONObject obj5 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string2));
        
        // removed other assertion
        assertTrue("obj1-obj3 Should eval to true", obj1.similar(obj3));
    }

    @Test
    public void verifySimilar_3_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONObject obj1 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", string1);
        
        JSONObject obj2 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 3)
                .put("key3", string1);

        JSONObject obj3 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", new String(string1));
        
        JSONObject obj4 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string1));

        JSONObject obj5 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string2));
        
        // removed other assertion
        // removed other assertion
        assertTrue("obj1-obj4 Should eval to true", obj1.similar(obj4));
    }

    @Test
    public void verifySimilar_4_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONObject obj1 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", string1);
        
        JSONObject obj2 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 3)
                .put("key3", string1);

        JSONObject obj3 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", new String(string1));
        
        JSONObject obj4 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string1));

        JSONObject obj5 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string2));
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("obj1-obj5 Should eval to false", obj1.similar(obj5));
    }

    @Test
    public void verifySimilar_5_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONObject obj1 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", string1);
        
        JSONObject obj2 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 3)
                .put("key3", string1);

        JSONObject obj3 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", new String(string1));
        
        JSONObject obj4 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string1));

        JSONObject obj5 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string2));
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify that a double and big decimal are "similar"
        assertTrue("should eval to true",new JSONObject().put("a",1.1d).similar(new JSONObject("{\"a\":1.1}")));
    }

    @Test
    public void verifySimilar_6_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONObject obj1 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", string1);
        
        JSONObject obj2 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 3)
                .put("key3", string1);

        JSONObject obj3 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2)
                .put("key3", new String(string1));
        
        JSONObject obj4 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string1));

        JSONObject obj5 = new JSONObject()
                .put("key1", "abc")
                .put("key2", 2.0)
                .put("key3", new String(string2));
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify that a double and big decimal are "similar"
        // removed other assertion
        // Confirm #618 is fixed (compare should not exit early if similar numbers are found)
        // Note that this test may not work if the JSONObject map entry order changes
        JSONObject first = new JSONObject("{\"a\": 1, \"b\": 2, \"c\": 3}");
        JSONObject second = new JSONObject("{\"a\": 1, \"b\": 2.0, \"c\": 4}");
        assertFalse("first-second should eval to false", first.similar(second));
    }

    @Test(expected=NullPointerException.class)
    public void jsonObjectByNullBean_1_oe() {
        JSONObject jsonObject = new JSONObject((MyBean)null);
        assertNull("Expected an exception", jsonObject);
    }

    @Test
    public void unquotedText_1_oe() {
        String str = "{key1:value1, key2:42}";
        JSONObject jsonObject = new JSONObject(str);
        String textStr = jsonObject.toString();
        assertTrue("expected key1", textStr.contains("\"key1\""));
    }

    @Test
    public void unquotedText_2_oe() {
        String str = "{key1:value1, key2:42}";
        JSONObject jsonObject = new JSONObject(str);
        String textStr = jsonObject.toString();
        // removed other assertion
        assertTrue("expected value1", textStr.contains("\"value1\""));
    }

    @Test
    public void unquotedText_3_oe() {
        String str = "{key1:value1, key2:42}";
        JSONObject jsonObject = new JSONObject(str);
        String textStr = jsonObject.toString();
        // removed other assertion
        // removed other assertion
        assertTrue("expected key2", textStr.contains("\"key2\""));
    }

    @Test
    public void unquotedText_4_oe() {
        String str = "{key1:value1, key2:42}";
        JSONObject jsonObject = new JSONObject(str);
        String textStr = jsonObject.toString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", textStr.contains("42"));
    }

    @Test
    public void testLongFromString_1_oe(){
        String str = "26315000000253009";
        JSONObject json = new JSONObject();
        json.put("key", str);
        
        final Object actualKey = json.opt("key");
        assert str.equals(actualKey) : "Incorrect key value. Got " + actualKey + " expected " + str;
    }

    @Test
    public void testLongFromString_2_oe(){
        String str = "26315000000253009";
        JSONObject json = new JSONObject();
        json.put("key", str);
        
        final Object actualKey = json.opt("key");
        // removed other assertion
        
        final long actualLong = json.optLong("key");
        assert actualLong != 0 : "Unable to extract long value for string " + str;
    }

    @Test
    public void testLongFromString_3_oe(){
        String str = "26315000000253009";
        JSONObject json = new JSONObject();
        json.put("key", str);
        
        final Object actualKey = json.opt("key");
        // removed other assertion
        
        final long actualLong = json.optLong("key");
        // removed other assertion
        assert 26315000000253009L == actualLong : "Incorrect key value. Got " + actualLong + " expected " + str;
    }

    @Test
    public void testLongFromString_4_oe(){
        String str = "26315000000253009";
        JSONObject json = new JSONObject();
        json.put("key", str);
        
        final Object actualKey = json.opt("key");
        // removed other assertion
        
        final long actualLong = json.optLong("key");
        // removed other assertion
        // removed other assertion

        final String actualString = json.optString("key");
        assert str.equals(actualString) : "Incorrect key value. Got " + actualString + " expected " + str;
    }

    @Test
    public void emptyJsonObject_1_oe() {
        JSONObject jsonObject = new JSONObject();
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

    @Test
    public void jsonObjectByNames_1_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"nullKey\":null,"+
                "\"stringKey\":\"hello world!\","+
                "\"escapeStringKey\":\"h\be\tllo w\u1234orld!\","+
                "\"intKey\":42,"+
                "\"doubleKey\":-23.45e67"+
            "}";
        String[] keys = {"falseKey", "stringKey", "nullKey", "doubleKey"};
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        JSONObject jsonObjectByName = new JSONObject(jsonObject, keys);
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObjectByName.toString());
        assertTrue("expected 4 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 4);
    }

    @Test
    public void jsonObjectByNames_2_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"nullKey\":null,"+
                "\"stringKey\":\"hello world!\","+
                "\"escapeStringKey\":\"h\be\tllo w\u1234orld!\","+
                "\"intKey\":42,"+
                "\"doubleKey\":-23.45e67"+
            "}";
        String[] keys = {"falseKey", "stringKey", "nullKey", "doubleKey"};
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        JSONObject jsonObjectByName = new JSONObject(jsonObject, keys);
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObjectByName.toString());
        // removed other assertion
        assertTrue("expected \"falseKey\":false", Boolean.FALSE.equals(jsonObjectByName.query("/falseKey")));
    }

    @Test
    public void jsonObjectByNames_3_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"nullKey\":null,"+
                "\"stringKey\":\"hello world!\","+
                "\"escapeStringKey\":\"h\be\tllo w\u1234orld!\","+
                "\"intKey\":42,"+
                "\"doubleKey\":-23.45e67"+
            "}";
        String[] keys = {"falseKey", "stringKey", "nullKey", "doubleKey"};
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        JSONObject jsonObjectByName = new JSONObject(jsonObject, keys);
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObjectByName.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"nullKey\":null", JSONObject.NULL.equals(jsonObjectByName.query("/nullKey")));
    }

    @Test
    public void jsonObjectByNames_4_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"nullKey\":null,"+
                "\"stringKey\":\"hello world!\","+
                "\"escapeStringKey\":\"h\be\tllo w\u1234orld!\","+
                "\"intKey\":42,"+
                "\"doubleKey\":-23.45e67"+
            "}";
        String[] keys = {"falseKey", "stringKey", "nullKey", "doubleKey"};
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        JSONObject jsonObjectByName = new JSONObject(jsonObject, keys);
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObjectByName.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"stringKey\":\"hello world!\"", "hello world!".equals(jsonObjectByName.query("/stringKey")));
    }

    @Test
    public void jsonObjectByNames_5_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"nullKey\":null,"+
                "\"stringKey\":\"hello world!\","+
                "\"escapeStringKey\":\"h\be\tllo w\u1234orld!\","+
                "\"intKey\":42,"+
                "\"doubleKey\":-23.45e67"+
            "}";
        String[] keys = {"falseKey", "stringKey", "nullKey", "doubleKey"};
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        JSONObject jsonObjectByName = new JSONObject(jsonObject, keys);
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObjectByName.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"doubleKey\":-23.45e67", new BigDecimal("-23.45e67").equals(jsonObjectByName.query("/doubleKey")));
    }

    @Test
    public void jsonObjectByNullMap_1_oe() {
        Map<String, Object> map = null;
        JSONObject jsonObject = new JSONObject(map);
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

    @Test
    public void jsonObjectByMap_1_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 6 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

    @Test
    public void jsonObjectByMap_2_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected \"trueKey\":true", Boolean.TRUE.equals(jsonObject.query("/trueKey")));
    }

    @Test
    public void jsonObjectByMap_3_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"falseKey\":false", Boolean.FALSE.equals(jsonObject.query("/falseKey")));
    }

    @Test
    public void jsonObjectByMap_4_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"stringKey\":\"hello world!\"", "hello world!".equals(jsonObject.query("/stringKey")));
    }

    @Test
    public void jsonObjectByMap_5_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"escapeStringKey\":\"h\be\tllo w\u1234orld!\"", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/escapeStringKey")));
    }

    @Test
    public void jsonObjectByMap_6_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"doubleKey\":-23.45e67", Double.valueOf("-23.45e67").equals(jsonObject.query("/doubleKey")));
    }

    @Test
    public void verifyConstructor_1_oe() {
        
        final JSONObject expected = new JSONObject("{\"myKey\":10}");
        
        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject(myCObjObj);

        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaRaw));
    }

    @Test
    public void verifyConstructor_2_oe() {
        
        final JSONObject expected = new JSONObject("{\"myKey\":10}");
        
        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject(myCObjObj);

        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaStrObj));
    }

    @Test
    public void verifyConstructor_3_oe() {
        
        final JSONObject expected = new JSONObject("{\"myKey\":10}");
        
        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject(myCObjObj);

        // removed other assertion
        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaStrInt));
    }

    @Test
    public void verifyConstructor_4_oe() {
        
        final JSONObject expected = new JSONObject("{\"myKey\":10}");
        
        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject(myCObjObj);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaObjObj));
    }

    @Test
    public void verifyNumberOutput_1_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        assertEquals("Equal", expected , actual);
    }

    @Test
    public void verifyNumberOutput_2_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        assertEquals("Equal", expected , actual);
    }

    @Test
    public void verifyNumberOutput_3_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        assertEquals("Equal", expected , actual);
    }

    @Test
    public void verifyNumberOutput_4_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        // removed other assertion

        /**
         * JSONObject.put() inserts the AtomicInteger directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * AtomicInteger is recognized as a Number, and converted via
         * numberToString() into the unquoted string '42'.
         */
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("myNumber", new AtomicInteger(42));
        actual = jsonObject3.toString();
        expected = "{\"myNumber\":42}";
        assertEquals("Equal", expected , actual);
    }

    @Test
    public void verifyNumberOutput_5_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        // removed other assertion

        /**
         * JSONObject.put() inserts the AtomicInteger directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * AtomicInteger is recognized as a Number, and converted via
         * numberToString() into the unquoted string '42'.
         */
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("myNumber", new AtomicInteger(42));
        actual = jsonObject3.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * Fraction is a Number, but is not recognized by wrap(), per
         * current implementation. As a POJO, Fraction is handled as a
         * bean and inserted into a contained JSONObject. It has 2 getters,
         * for numerator and denominator. 
         */
        JSONObject jsonObject4 = new JSONObject(Collections.singletonMap("myNumber", new Fraction(4,2)));
        assertEquals(1, jsonObject4.length());
    }

    @Test
    public void verifyNumberOutput_6_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        // removed other assertion

        /**
         * JSONObject.put() inserts the AtomicInteger directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * AtomicInteger is recognized as a Number, and converted via
         * numberToString() into the unquoted string '42'.
         */
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("myNumber", new AtomicInteger(42));
        actual = jsonObject3.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * Fraction is a Number, but is not recognized by wrap(), per
         * current implementation. As a POJO, Fraction is handled as a
         * bean and inserted into a contained JSONObject. It has 2 getters,
         * for numerator and denominator. 
         */
        JSONObject jsonObject4 = new JSONObject(Collections.singletonMap("myNumber", new Fraction(4,2)));
        // removed other assertion
        assertEquals(2, ((JSONObject)(jsonObject4.get("myNumber"))).length());
    }

    @Test
    public void verifyNumberOutput_7_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        // removed other assertion

        /**
         * JSONObject.put() inserts the AtomicInteger directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * AtomicInteger is recognized as a Number, and converted via
         * numberToString() into the unquoted string '42'.
         */
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("myNumber", new AtomicInteger(42));
        actual = jsonObject3.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * Fraction is a Number, but is not recognized by wrap(), per
         * current implementation. As a POJO, Fraction is handled as a
         * bean and inserted into a contained JSONObject. It has 2 getters,
         * for numerator and denominator. 
         */
        JSONObject jsonObject4 = new JSONObject(Collections.singletonMap("myNumber", new Fraction(4,2)));
        // removed other assertion
        // removed other assertion
        assertEquals("Numerator", BigInteger.valueOf(4) , jsonObject4.query("/myNumber/numerator"));
    }

    @Test
    public void verifyNumberOutput_8_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        // removed other assertion

        /**
         * JSONObject.put() inserts the AtomicInteger directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * AtomicInteger is recognized as a Number, and converted via
         * numberToString() into the unquoted string '42'.
         */
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("myNumber", new AtomicInteger(42));
        actual = jsonObject3.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * Fraction is a Number, but is not recognized by wrap(), per
         * current implementation. As a POJO, Fraction is handled as a
         * bean and inserted into a contained JSONObject. It has 2 getters,
         * for numerator and denominator. 
         */
        JSONObject jsonObject4 = new JSONObject(Collections.singletonMap("myNumber", new Fraction(4,2)));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Denominator", BigInteger.valueOf(2) , jsonObject4.query("/myNumber/denominator"));
    }

    @Test
    public void verifyNumberOutput_9_oe(){
        /**
         * MyNumberContainer is a POJO, so call JSONObject(bean), 
         * which builds a map of getter names/values
         * The only getter is getMyNumber (key=myNumber), 
         * whose return value is MyNumber. MyNumber extends Number, 
         * but is not recognized as such by wrap() per current
         * implementation, so wrap() returns the default new JSONObject(bean).
         * The only getter is getNumber (key=number), whose return value is
         * BigDecimal(42). 
         */
        JSONObject jsonObject0 = new JSONObject(new MyNumberContainer());
        String actual = jsonObject0.toString();
        String expected = "{\"myNumber\":{\"number\":42}}";
        // removed other assertion
        
        /**
         * JSONObject.put() handles objects differently than the 
         * bean constructor. Where the bean ctor wraps objects before 
         * placing them in the map, put() inserts the object without wrapping.
         * In this case, a MyNumber instance is the value.
         * The MyNumber.toString() method is responsible for
         * returning a reasonable value: the string '42'.
         */
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("myNumber", new MyNumber());
        actual = jsonObject1.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * AtomicInteger is a Number, but is not recognized by wrap(), per
         * current implementation. However, the type is
         * 'java.util.concurrent.atomic', so due to the 'java' prefix,
         * wrap() inserts the value as a string. That is why 42 comes back
         * wrapped in quotes.
         */
        JSONObject jsonObject2 = new JSONObject(Collections.singletonMap("myNumber", new AtomicInteger(42)));
        actual = jsonObject2.toString();
        expected = "{\"myNumber\":\"42\"}";
        // removed other assertion

        /**
         * JSONObject.put() inserts the AtomicInteger directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * AtomicInteger is recognized as a Number, and converted via
         * numberToString() into the unquoted string '42'.
         */
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("myNumber", new AtomicInteger(42));
        actual = jsonObject3.toString();
        expected = "{\"myNumber\":42}";
        // removed other assertion

        /**
         * Calls the JSONObject(Map) ctor, which calls wrap() for values.
         * Fraction is a Number, but is not recognized by wrap(), per
         * current implementation. As a POJO, Fraction is handled as a
         * bean and inserted into a contained JSONObject. It has 2 getters,
         * for numerator and denominator. 
         */
        JSONObject jsonObject4 = new JSONObject(Collections.singletonMap("myNumber", new Fraction(4,2)));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject.put() inserts the Fraction directly into the
         * map not calling wrap(). In toString()->write()->writeValue(), 
         * Fraction is recognized as a Number, and converted via
         * numberToString() into the unquoted string '4/2'. But the 
         * BigDecimal sanity check fails, so writeValue() defaults
         * to returning a safe JSON quoted string. Pretty slick!
         */
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("myNumber", new Fraction(4,2));
        actual = jsonObject5.toString();
        expected = "{\"myNumber\":\"4/2\"}"; // valid JSON, bug fixed
        assertEquals("Equal", expected , actual);
    }

    @Test
    public void verifyPutCollection_1_oe() {
        
        final JSONObject expected = new JSONObject("{\"myCollection\":[10]}");

        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myCollection", myRawC);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONObject jaObj = new JSONObject();
        jaObj.put("myCollection", myCObj);

        Collection<Integer> myCInt = Collections.singleton(Integer
                .valueOf(10));
        JSONObject jaInt = new JSONObject();
        jaInt.put("myCollection", myCInt);

        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaRaw));
    }

    @Test
    public void verifyPutCollection_2_oe() {
        
        final JSONObject expected = new JSONObject("{\"myCollection\":[10]}");

        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myCollection", myRawC);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONObject jaObj = new JSONObject();
        jaObj.put("myCollection", myCObj);

        Collection<Integer> myCInt = Collections.singleton(Integer
                .valueOf(10));
        JSONObject jaInt = new JSONObject();
        jaInt.put("myCollection", myCInt);

        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaObj));
    }

    @Test
    public void verifyPutCollection_3_oe() {
        
        final JSONObject expected = new JSONObject("{\"myCollection\":[10]}");

        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myCollection", myRawC);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONObject jaObj = new JSONObject();
        jaObj.put("myCollection", myCObj);

        Collection<Integer> myCInt = Collections.singleton(Integer
                .valueOf(10));
        JSONObject jaInt = new JSONObject();
        jaInt.put("myCollection", myCInt);

        // removed other assertion
        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaInt));
    }

    @Test
    public void verifyPutMap_1_oe() {
        
        final JSONObject expected = new JSONObject("{\"myMap\":{\"myKey\":10}}");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myMap", myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject();
        jaStrObj.put("myMap", myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject();
        jaStrInt.put("myMap", myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject();
        jaObjObj.put("myMap", myCObjObj);

        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaRaw));
    }

    @Test
    public void verifyPutMap_2_oe() {
        
        final JSONObject expected = new JSONObject("{\"myMap\":{\"myKey\":10}}");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myMap", myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject();
        jaStrObj.put("myMap", myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject();
        jaStrInt.put("myMap", myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject();
        jaObjObj.put("myMap", myCObjObj);

        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaStrObj));
    }

    @Test
    public void verifyPutMap_3_oe() {
        
        final JSONObject expected = new JSONObject("{\"myMap\":{\"myKey\":10}}");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myMap", myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject();
        jaStrObj.put("myMap", myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject();
        jaStrInt.put("myMap", myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject();
        jaObjObj.put("myMap", myCObjObj);

        // removed other assertion
        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaStrInt));
    }

    @Test
    public void verifyPutMap_4_oe() {
        
        final JSONObject expected = new JSONObject("{\"myMap\":{\"myKey\":10}}");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONObject jaRaw = new JSONObject();
        jaRaw.put("myMap", myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaStrObj = new JSONObject();
        jaStrObj.put("myMap", myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONObject jaStrInt = new JSONObject();
        jaStrInt.put("myMap", myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONObject jaObjObj = new JSONObject();
        jaObjObj.put("myMap", myCObjObj);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "The RAW Collection should give me the same as the Typed Collection", expected.similar(jaObjObj));
    }

    @Test
    public void jsonObjectByMapWithUnsupportedValues_1_oe() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // Just insert some random objects
        jsonMap.put("key1", new CDL());
        jsonMap.put("key2", new Exception());

        JSONObject jsonObject = new JSONObject(jsonMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

    @Test
    public void jsonObjectByMapWithUnsupportedValues_2_oe() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // Just insert some random objects
        jsonMap.put("key1", new CDL());
        jsonMap.put("key2", new Exception());

        JSONObject jsonObject = new JSONObject(jsonMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 0 key1 items", ((Map<?,?>)(JsonPath.read(doc, "$.key1"))).size() == 0);
    }

    @Test
    public void jsonObjectByMapWithUnsupportedValues_3_oe() {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // Just insert some random objects
        jsonMap.put("key1", new CDL());
        jsonMap.put("key2", new Exception());

        JSONObject jsonObject = new JSONObject(jsonMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"key2\":java.lang.Exception","java.lang.Exception".equals(jsonObject.query("/key2")));
    }

    @Test
    public void jsonObjectByMapWithNullValue_1_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 6 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

    @Test
    public void jsonObjectByMapWithNullValue_2_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected \"trueKey\":true", Boolean.TRUE.equals(jsonObject.query("/trueKey")));
    }

    @Test
    public void jsonObjectByMapWithNullValue_3_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"falseKey\":false", Boolean.FALSE.equals(jsonObject.query("/falseKey")));
    }

    @Test
    public void jsonObjectByMapWithNullValue_4_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"stringKey\":\"hello world!\"", "hello world!".equals(jsonObject.query("/stringKey")));
    }

    @Test
    public void jsonObjectByMapWithNullValue_5_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"escapeStringKey\":\"h\be\tllo w\u1234orld!\"", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/escapeStringKey")));
    }

    @Test
    public void jsonObjectByMapWithNullValue_6_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"intKey\":42", Long.valueOf("42").equals(jsonObject.query("/intKey")));
    }

    @Test
    public void jsonObjectByMapWithNullValue_7_oe() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("trueKey", new Boolean(true));
        map.put("falseKey", new Boolean(false));
        map.put("stringKey", "hello world!");
        map.put("nullKey", null);
        map.put("escapeStringKey", "h\be\tllo w\u1234orld!");
        map.put("intKey", new Long(42));
        map.put("doubleKey", new Double(-23.45e67));
        JSONObject jsonObject = new JSONObject(map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"doubleKey\":-23.45e67", Double.valueOf("-23.45e67").equals(jsonObject.query("/doubleKey")));
    }

    @Test
    public void jsonObjectByBean1_1_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 8 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 8);
    }

    @Test
    public void jsonObjectByBean1_2_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 0 items in stringReaderKey", ((Map<?, ?>) (JsonPath.read(doc, "$.stringReaderKey"))).size() == 0);
    }

    @Test
    public void jsonObjectByBean1_3_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/trueKey")));
    }

    @Test
    public void jsonObjectByBean1_4_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/falseKey")));
    }

    @Test
    public void jsonObjectByBean1_5_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected hello world!","hello world!".equals(jsonObject.query("/stringKey")));
    }

    @Test
    public void jsonObjectByBean1_6_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected h\be\tllo w\u1234orld!", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/escapeStringKey")));
    }

    @Test
    public void jsonObjectByBean1_7_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", Integer.valueOf("42").equals(jsonObject.query("/intKey")));
    }

    @Test
    public void jsonObjectByBean1_8_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected -23.45e7", Double.valueOf("-23.45e7").equals(jsonObject.query("/doubleKey")));
    }

    @Test
    public void jsonObjectByBean1_9_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // sorry, mockito artifact
        assertTrue("expected 2 callbacks items", ((List<?>)(JsonPath.read(doc, "$.callbacks"))).size() == 2);
    }

    @Test
    public void jsonObjectByBean1_10_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // sorry, mockito artifact
        // removed other assertion
        assertTrue("expected 0 handler items", ((Map<?,?>)(JsonPath.read(doc, "$.callbacks[0].handler"))).size() == 0);
    }

    @Test
    public void jsonObjectByBean1_11_oe() {
        /**
         * Default access classes have to be mocked since JSONObject, which is
         * not in the same package, cannot call MyBean methods by reflection.
         */
        MyBean myBean = mock(MyBean.class);
        when(myBean.getDoubleKey()).thenReturn(-23.45e7);
        when(myBean.getIntKey()).thenReturn(42);
        when(myBean.getStringKey()).thenReturn("hello world!");
        when(myBean.getEscapeStringKey()).thenReturn("h\be\tllo w\u1234orld!");
        when(myBean.isTrueKey()).thenReturn(true);
        when(myBean.isFalseKey()).thenReturn(false);
        when(myBean.getStringReaderKey()).thenReturn(
            new StringReader("") {
            });

        JSONObject jsonObject = new JSONObject(myBean);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // sorry, mockito artifact
        // removed other assertion
        // removed other assertion
        assertTrue("expected 0 callbacks[1] items", ((Map<?,?>)(JsonPath.read(doc, "$.callbacks[1]"))).size() == 0);
    }

    @Test
    public void jsonObjectByBean2_1_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        assertNotNull(jsonObject);
    }

    @Test
    public void jsonObjectByBean2_2_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        assertEquals("Wrong number of keys found:", 5, jsonObject.keySet().size());
    }

    @Test
    public void jsonObjectByBean2_3_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        assertFalse("Normal field name (someString) processing did not work", jsonObject.has("someString"));
    }

    @Test
    public void jsonObjectByBean2_4_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Normal field name (myDouble) processing did not work", jsonObject.has("myDouble"));
    }

    @Test
    public void jsonObjectByBean2_5_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Normal field name (someFloat) processing did not work", jsonObject.has("someFloat"));
    }

    @Test
    public void jsonObjectByBean2_6_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Ignored field not found!", jsonObject.has("ignoredInt"));
    }

    @Test
    public void jsonObjectByBean2_7_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // getSomeInt() has no user-defined annotation
        assertTrue("Normal field name (someInt) should have been found", jsonObject.has("someInt"));
    }

    @Test
    public void jsonObjectByBean2_8_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // getSomeInt() has no user-defined annotation
        // removed other assertion
        // the user-defined annotation does not replace any value, so someLong should be found
        assertTrue("Normal field name (someLong) should have been found", jsonObject.has("someLong"));
    }

    @Test
    public void jsonObjectByBean2_9_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // getSomeInt() has no user-defined annotation
        // removed other assertion
        // the user-defined annotation does not replace any value, so someLong should be found
        // removed other assertion
        // myStringField replaces someString property name via user-defined annotation
        assertTrue("Overridden String field name (myStringField) should have been found", jsonObject.has("myStringField"));
    }

    @Test
    public void jsonObjectByBean2_10_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // getSomeInt() has no user-defined annotation
        // removed other assertion
        // the user-defined annotation does not replace any value, so someLong should be found
        // removed other assertion
        // myStringField replaces someString property name via user-defined annotation
        // removed other assertion
        // weird name replaces myDouble property name via user-defined annotation
        assertTrue("Overridden String field name (Some Weird NAme that Normally Wouldn't be possible!) should have been found", jsonObject.has("Some Weird NAme that Normally Wouldn't be possible!"));
    }

    @Test
    public void jsonObjectByBean2_11_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomName());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // getSomeInt() has no user-defined annotation
        // removed other assertion
        // the user-defined annotation does not replace any value, so someLong should be found
        // removed other assertion
        // myStringField replaces someString property name via user-defined annotation
        // removed other assertion
        // weird name replaces myDouble property name via user-defined annotation
        // removed other assertion
        // InterfaceField replaces someFloat property name via user-defined annotation
        assertTrue("Overridden String field name (InterfaceField) should have been found", jsonObject.has("InterfaceField"));
    }

    @Test
    public void jsonObjectByBean3_1_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        assertNotNull(jsonObject);
    }

    @Test
    public void jsonObjectByBean3_2_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        assertEquals("Wrong number of keys found:", 7, jsonObject.keySet().size());
    }

    @Test
    public void jsonObjectByBean3_3_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        assertFalse("Normal int field name (someInt) found, but was overridden", jsonObject.has("someInt"));
    }

    @Test
    public void jsonObjectByBean3_4_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Normal field name (myDouble) processing did not work", jsonObject.has("myDouble"));
    }

    @Test
    public void jsonObjectByBean3_5_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        assertFalse("Overridden String field name (Some Weird NAme that Normally Wouldn't be possible!) should not be FOUND!", jsonObject.has("Some Weird NAme that Normally Wouldn't be possible!"));
    }

    @Test
    public void jsonObjectByBean3_6_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        assertFalse("Normal field name (someFloat) found, but was overridden", jsonObject.has("someFloat"));
    }

    @Test
    public void jsonObjectByBean3_7_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        assertFalse("Ignored field found! but was overridden", jsonObject.has("ignoredInt"));
    }

    @Test
    public void jsonObjectByBean3_8_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        assertFalse("Ignored field at the same level as forced name should not have been found", jsonObject.has("ShouldBeIgnored"));
    }

    @Test
    public void jsonObjectByBean3_9_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        assertFalse("Normally ignored field (able) with explicit property name should not have been found", jsonObject.has("able"));
    }

    @Test
    public void jsonObjectByBean3_10_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        assertTrue("Overridden int field name (newIntFieldName) should have been found", jsonObject.has("newIntFieldName"));
    }

    @Test
    public void jsonObjectByBean3_11_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        // removed other assertion
        // property name someLong was not replaced via user-defined annotation
        assertTrue("Normal field name (someLong) should have been found", jsonObject.has("someLong"));
    }

    @Test
    public void jsonObjectByBean3_12_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        // removed other assertion
        // property name someLong was not replaced via user-defined annotation
        // removed other assertion
        // property name someString was replaced by myStringField via user-defined annotation
        assertTrue("Overridden String field name (myStringField) should have been found", jsonObject.has("myStringField"));
    }

    @Test
    public void jsonObjectByBean3_13_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        // removed other assertion
        // property name someLong was not replaced via user-defined annotation
        // removed other assertion
        // property name someString was replaced by myStringField via user-defined annotation
        // removed other assertion
        // property name myDouble was replaced by a weird name, followed by AMoreNormalName via user-defined annotations
        assertTrue("Overridden double field name (AMoreNormalName) should have been found", jsonObject.has("AMoreNormalName"));
    }

    @Test
    public void jsonObjectByBean3_14_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        // removed other assertion
        // property name someLong was not replaced via user-defined annotation
        // removed other assertion
        // property name someString was replaced by myStringField via user-defined annotation
        // removed other assertion
        // property name myDouble was replaced by a weird name, followed by AMoreNormalName via user-defined annotations
        // removed other assertion
        // property name someFloat was replaced by InterfaceField via user-defined annotation
        assertTrue("Overridden String field name (InterfaceField) should have been found", jsonObject.has("InterfaceField"));
    }

    @Test
    public void jsonObjectByBean3_15_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        // removed other assertion
        // property name someLong was not replaced via user-defined annotation
        // removed other assertion
        // property name someString was replaced by myStringField via user-defined annotation
        // removed other assertion
        // property name myDouble was replaced by a weird name, followed by AMoreNormalName via user-defined annotations
        // removed other assertion
        // property name someFloat was replaced by InterfaceField via user-defined annotation
        // removed other assertion
        // property name ignoredInt was replaced by none, followed by forcedInt via user-defined annotations
        assertTrue("Forced field should have been found!", jsonObject.has("forcedInt"));
    }

    @Test
    public void jsonObjectByBean3_16_oe() {
        JSONObject jsonObject = new JSONObject(new MyBeanCustomNameSubClass());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // myDouble was replaced by weird name, and then replaced again by AMoreNormalName via user-defined annotation
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // shouldNotBeJSON property name was first ignored, then replaced by ShouldBeIgnored via user-defined annotations
        // removed other assertion
        // able property name was replaced by Getable via user-defined annotation
        // removed other assertion
        // property name someInt was replaced by newIntFieldName via user-defined annotation
        // removed other assertion
        // property name someLong was not replaced via user-defined annotation
        // removed other assertion
        // property name someString was replaced by myStringField via user-defined annotation
        // removed other assertion
        // property name myDouble was replaced by a weird name, followed by AMoreNormalName via user-defined annotations
        // removed other assertion
        // property name someFloat was replaced by InterfaceField via user-defined annotation
        // removed other assertion
        // property name ignoredInt was replaced by none, followed by forcedInt via user-defined annotations
        // removed other assertion
        // property name able was replaced by Getable via user-defined annotation
        assertTrue("Overridden boolean field name (Getable) should have been found", jsonObject.has("Getable"));
    }

    @Test
    public void jsonObjectByObjectAndNames_1_oe() {
        String[] keys = {"publicString", "publicInt"};
        // just need a class that has public data members
        MyPublicClass myPublicClass = new MyPublicClass();
        JSONObject jsonObject = new JSONObject(myPublicClass, keys);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

    @Test
    public void jsonObjectByObjectAndNames_2_oe() {
        String[] keys = {"publicString", "publicInt"};
        // just need a class that has public data members
        MyPublicClass myPublicClass = new MyPublicClass();
        JSONObject jsonObject = new JSONObject(myPublicClass, keys);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected \"publicString\":\"abc\"", "abc".equals(jsonObject.query("/publicString")));
    }

    @Test
    public void jsonObjectByObjectAndNames_3_oe() {
        String[] keys = {"publicString", "publicInt"};
        // just need a class that has public data members
        MyPublicClass myPublicClass = new MyPublicClass();
        JSONObject jsonObject = new JSONObject(myPublicClass, keys);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"publicInt\":42", Integer.valueOf(42).equals(jsonObject.query("/publicInt")));
    }

    @Test
    public void jsonObjectByResourceBundle_1_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 2 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 2);
    }

    @Test
    public void jsonObjectByResourceBundle_2_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 2 greetings items", ((Map<?,?>)(JsonPath.read(doc, "$.greetings"))).size() == 2);
    }

    @Test
    public void jsonObjectByResourceBundle_3_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"hello\":\"Hello, \"", "Hello, ".equals(jsonObject.query("/greetings/hello")));
    }

    @Test
    public void jsonObjectByResourceBundle_4_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"world\":\"World!\"", "World!".equals(jsonObject.query("/greetings/world")));
    }

    @Test
    public void jsonObjectByResourceBundle_5_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2 farewells items", ((Map<?,?>)(JsonPath.read(doc, "$.farewells"))).size() == 2);
    }

    @Test
    public void jsonObjectByResourceBundle_6_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"later\":\"Later, \"", "Later, ".equals(jsonObject.query("/farewells/later")));
    }

    @Test
    public void jsonObjectByResourceBundle_7_oe() {
        JSONObject jsonObject = new
                JSONObject("org.json.junit.data.StringsResourceBundle",
                        Locale.getDefault());

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected \"world\":\"World!\"", "Alligator!".equals(jsonObject.query("/farewells/gator")));
    }

    @Test
    public void jsonObjectAccumulate_2_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

    @Test
    public void jsonObjectAccumulate_3_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 6 myArray items", ((List<?>)(JsonPath.read(doc, "$.myArray"))).size() == 6);
    }

    @Test
    public void jsonObjectAccumulate_4_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/myArray/0")));
    }

    @Test
    public void jsonObjectAccumulate_5_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/myArray/1")));
    }

    @Test
    public void jsonObjectAccumulate_6_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected hello world!", "hello world!".equals(jsonObject.query("/myArray/2")));
    }

    @Test
    public void jsonObjectAccumulate_7_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected h\be\tllo w\u1234orld!", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/myArray/3")));
    }

    @Test
    public void jsonObjectAccumulate_8_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", Integer.valueOf(42).equals(jsonObject.query("/myArray/4")));
    }

    @Test
    public void jsonObjectAccumulate_9_oe() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("myArray", true);
        jsonObject.accumulate("myArray", false);
        jsonObject.accumulate("myArray", "hello world!");
        jsonObject.accumulate("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.accumulate("myArray", 42);
        jsonObject.accumulate("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.accumulate("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected -23.45e7", Double.valueOf(-23.45e7).equals(jsonObject.query("/myArray/5")));
    }

    @Test
    public void jsonObjectAppend_2_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

    @Test
    public void jsonObjectAppend_3_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 6 myArray items", ((List<?>)(JsonPath.read(doc, "$.myArray"))).size() == 6);
    }

    @Test
    public void jsonObjectAppend_4_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/myArray/0")));
    }

    @Test
    public void jsonObjectAppend_5_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/myArray/1")));
    }

    @Test
    public void jsonObjectAppend_6_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected hello world!", "hello world!".equals(jsonObject.query("/myArray/2")));
    }

    @Test
    public void jsonObjectAppend_7_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected h\be\tllo w\u1234orld!", "h\be\tllo w\u1234orld!".equals(jsonObject.query("/myArray/3")));
    }

    @Test
    public void jsonObjectAppend_8_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 42", Integer.valueOf(42).equals(jsonObject.query("/myArray/4")));
    }

    @Test
    public void jsonObjectAppend_9_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("myArray", true);
        jsonObject.append("myArray", false);
        jsonObject.append("myArray", "hello world!");
        jsonObject.append("myArray", "h\be\tllo w\u1234orld!");
        jsonObject.append("myArray", 42);
        jsonObject.append("myArray", -23.45e7);
        // include an unsupported object for coverage
        try {
            jsonObject.append("myArray", Double.NaN);
            // removed other assertion
        } catch (JSONException ignored) {}

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected -23.45e7", Double.valueOf(-23.45e7).equals(jsonObject.query("/myArray/5")));
    }

    @Test
    public void jsonObjectDoubleToString_1_oe() {
        String [] expectedStrs = {"1", "1", "-23.4", "-2.345E68", "null", "null" };
        Double [] doubles = { 1.0, 00001.00000, -23.4, -23.45e67, 
                Double.NaN, Double.NEGATIVE_INFINITY }; 
        for (int i = 0; i < expectedStrs.length; ++i) {
            String actualStr = JSONObject.doubleToString(doubles[i]);
            assertTrue("value expected ["+expectedStrs[i]+ "] found ["+actualStr+ "]", expectedStrs[i].equals(actualStr));
    }
    }

    @Test
    public void jsonObjectValues_1_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        assertTrue("trueKey should be true", jsonObject.getBoolean("trueKey"));
    }

    @Test
    public void jsonObjectValues_2_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        assertTrue("opt trueKey should be true", jsonObject.optBoolean("trueKey"));
    }

    @Test
    public void jsonObjectValues_3_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        assertTrue("falseKey should be false", !jsonObject.getBoolean("falseKey"));
    }

    @Test
    public void jsonObjectValues_4_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("trueStrKey should be true", jsonObject.getBoolean("trueStrKey"));
    }

    @Test
    public void jsonObjectValues_5_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("trueStrKey should be true", jsonObject.optBoolean("trueStrKey"));
    }

    @Test
    public void jsonObjectValues_6_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("falseStrKey should be false", !jsonObject.getBoolean("falseStrKey"));
    }

    @Test
    public void jsonObjectValues_7_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("stringKey should be string", jsonObject.getString("stringKey").equals("hello world!"));
    }

    @Test
    public void jsonObjectValues_8_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("doubleKey should be double", jsonObject.getDouble("doubleKey") == -23.45e7);
    }

    @Test
    public void jsonObjectValues_9_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("doubleStrKey should be double", jsonObject.getDouble("doubleStrKey") == 1);
    }

    @Test
    public void jsonObjectValues_10_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("doubleKey can be float", jsonObject.getFloat("doubleKey") == -23.45e7f);
    }

    @Test
    public void jsonObjectValues_11_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("doubleStrKey can be float", jsonObject.getFloat("doubleStrKey") == 1f);
    }

    @Test
    public void jsonObjectValues_12_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt doubleKey should be double", jsonObject.optDouble("doubleKey") == -23.45e7);
    }

    @Test
    public void jsonObjectValues_13_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt doubleKey with Default should be double", jsonObject.optDouble("doubleStrKey", Double.NaN) == 1);
    }

    @Test
    public void jsonObjectValues_14_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt negZeroKey should be a Double", jsonObject.opt("negZeroKey") instanceof Double);
    }

    @Test
    public void jsonObjectValues_15_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("get negZeroKey should be a Double", jsonObject.get("negZeroKey") instanceof Double);
    }

    @Test
    public void jsonObjectValues_16_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber negZeroKey should return Double", jsonObject.optNumber("negZeroKey") instanceof Double);
    }

    @Test
    public void jsonObjectValues_17_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber negZeroStrKey should return Double", jsonObject.optNumber("negZeroStrKey") instanceof Double);
    }

    @Test
    public void jsonObjectValues_18_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt negZeroKey should be double", Double.compare(jsonObject.optDouble("negZeroKey"), -0.0d) == 0);
    }

    @Test
    public void jsonObjectValues_19_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt negZeroStrKey with Default should be double", Double.compare(jsonObject.optDouble("negZeroStrKey"), -0.0d) == 0);
    }

    @Test
    public void jsonObjectValues_20_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber negZeroKey should be -0.0", Double.compare(jsonObject.optNumber("negZeroKey").doubleValue(), -0.0d) == 0);
    }

    @Test
    public void jsonObjectValues_21_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber negZeroStrKey should be -0.0", Double.compare(jsonObject.optNumber("negZeroStrKey").doubleValue(), -0.0d) == 0);
    }

    @Test
    public void jsonObjectValues_22_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optFloat doubleKey should be float", jsonObject.optFloat("doubleKey") == -23.45e7f);
    }

    @Test
    public void jsonObjectValues_23_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optFloat doubleKey with Default should be float", jsonObject.optFloat("doubleStrKey", Float.NaN) == 1f);
    }

    @Test
    public void jsonObjectValues_24_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("intKey should be int", jsonObject.optInt("intKey") == 42);
    }

    @Test
    public void jsonObjectValues_25_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt intKey should be int", jsonObject.optInt("intKey", 0) == 42);
    }

    @Test
    public void jsonObjectValues_26_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt intKey with default should be int", jsonObject.getInt("intKey") == 42);
    }

    @Test
    public void jsonObjectValues_27_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        assertTrue("intStrKey should be int", jsonObject.getInt("intStrKey") == 43);
    }

    @Test
    public void jsonObjectValues_28_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        assertTrue("longKey should be long", jsonObject.getLong("longKey") == 1234567890123456789L);
    }

    @Test
    public void jsonObjectValues_29_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("opt longKey should be long", jsonObject.optLong("longKey") == 1234567890123456789L);
    }

    @Test
    public void jsonObjectValues_30_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("opt longKey with default should be long", jsonObject.optLong("longKey", 0) == 1234567890123456789L);
    }

    @Test
    public void jsonObjectValues_31_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("longStrKey should be long", jsonObject.getLong("longStrKey") == 987654321098765432L);
    }

    @Test
    public void jsonObjectValues_32_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optNumber int should return Integer", jsonObject.optNumber("intKey") instanceof Integer);
    }

    @Test
    public void jsonObjectValues_33_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optNumber long should return Long", jsonObject.optNumber("longKey") instanceof Long);
    }

    @Test
    public void jsonObjectValues_34_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optNumber double should return BigDecimal", jsonObject.optNumber("doubleKey") instanceof BigDecimal);
    }

    @Test
    public void jsonObjectValues_35_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optNumber Str int should return Integer", jsonObject.optNumber("intStrKey") instanceof Integer);
    }

    @Test
    public void jsonObjectValues_36_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber Str long should return Long", jsonObject.optNumber("longStrKey") instanceof Long);
    }

    @Test
    public void jsonObjectValues_37_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber Str double should return BigDecimal", jsonObject.optNumber("doubleStrKey") instanceof BigDecimal);
    }

    @Test
    public void jsonObjectValues_38_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("optNumber BigDecimalStrKey should return BigDecimal", jsonObject.optNumber("BigDecimalStrKey") instanceof BigDecimal);
    }

    @Test
    public void jsonObjectValues_39_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("xKey should not exist", jsonObject.isNull("xKey"));
    }

    @Test
    public void jsonObjectValues_40_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("stringKey should exist", jsonObject.has("stringKey"));
    }

    @Test
    public void jsonObjectValues_41_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt stringKey should string", jsonObject.optString("stringKey").equals("hello world!"));
    }

    @Test
    public void jsonObjectValues_42_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        assertTrue("opt stringKey with default should string", jsonObject.optString("stringKey", "not found").equals("hello world!"));
    }

    @Test
    public void jsonObjectValues_43_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        JSONArray jsonArray = jsonObject.getJSONArray("arrayKey");
        assertTrue("arrayKey should be JSONArray", jsonArray.getInt(0) == 0 && jsonArray.getInt(1) == 1 && jsonArray.getInt(2) == 2);
    }

    @Test
    public void jsonObjectValues_44_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        JSONArray jsonArray = jsonObject.getJSONArray("arrayKey");
        // removed other assertion
        jsonArray = jsonObject.optJSONArray("arrayKey");
        assertTrue("opt arrayKey should be JSONArray", jsonArray.getInt(0) == 0 && jsonArray.getInt(1) == 1 && jsonArray.getInt(2) == 2);
    }

    @Test
    public void jsonObjectValues_45_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"BigDecimalStrKey\":\"19007199254740993.35481234487103587486413587843213584\","+
                "\"negZeroKey\":-0.0,"+
                "\"negZeroStrKey\":\"-0.0\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
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
        JSONArray jsonArray = jsonObject.getJSONArray("arrayKey");
        // removed other assertion
        jsonArray = jsonObject.optJSONArray("arrayKey");
        // removed other assertion
        JSONObject jsonObjectInner = jsonObject.getJSONObject("objectKey");
        assertTrue("objectKey should be JSONObject", jsonObjectInner.get("myKey").equals("myVal"));
    }

    @Test
    public void stringToValueNumbersTest_1_oe() {
        assertTrue("-0 Should be a Double!",JSONObject.stringToValue("-0")  instanceof Double);
    }

    @Test
    public void stringToValueNumbersTest_2_oe() {
        // removed other assertion
        assertTrue("-0.0 Should be a Double!",JSONObject.stringToValue("-0.0") instanceof Double);
    }

    @Test
    public void stringToValueNumbersTest_3_oe() {
        // removed other assertion
        // removed other assertion
        assertTrue("'-' Should be a String!",JSONObject.stringToValue("-") instanceof String);
    }

    @Test
    public void stringToValueNumbersTest_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "0.2 should be a BigDecimal!", JSONObject.stringToValue( "0.2" ) instanceof BigDecimal );
    }

    @Test
    public void stringToValueNumbersTest_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "Doubles should be BigDecimal, even when incorrectly converting floats!", JSONObject.stringToValue( new Double( "0.2f" ).toString() ) instanceof BigDecimal );
    }

    @Test
    public void stringToValueNumbersTest_6_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        /**
         * This test documents a need for BigDecimal conversion.
         */
        Object obj = JSONObject.stringToValue( "299792.457999999984" );
        assertTrue( "does not evaluate to 299792.457999999984 BigDecimal!", obj.equals(new BigDecimal("299792.457999999984")) );
    }

    @Test
    public void stringToValueNumbersTest_7_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        /**
         * This test documents a need for BigDecimal conversion.
         */
        Object obj = JSONObject.stringToValue( "299792.457999999984" );
        // removed other assertion
        assertTrue( "1 should be an Integer!", JSONObject.stringToValue( "1" ) instanceof Integer );
    }

    @Test
    public void stringToValueNumbersTest_8_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        /**
         * This test documents a need for BigDecimal conversion.
         */
        Object obj = JSONObject.stringToValue( "299792.457999999984" );
        // removed other assertion
        // removed other assertion
        assertTrue( "Integer.MAX_VALUE should still be an Integer!", JSONObject.stringToValue( new Integer( Integer.MAX_VALUE ).toString() ) instanceof Integer );
    }

    @Test
    public void stringToValueNumbersTest_9_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        /**
         * This test documents a need for BigDecimal conversion.
         */
        Object obj = JSONObject.stringToValue( "299792.457999999984" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "Large integers should be a Long!", JSONObject.stringToValue( Long.valueOf(((long)Integer.MAX_VALUE) + 1 ) .toString() ) instanceof Long );
    }

    @Test
    public void stringToValueNumbersTest_10_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        /**
         * This test documents a need for BigDecimal conversion.
         */
        Object obj = JSONObject.stringToValue( "299792.457999999984" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "Long.MAX_VALUE should still be an Integer!", JSONObject.stringToValue( new Long( Long.MAX_VALUE ).toString() ) instanceof Long );
    }

    @Test
    public void stringToValueNumbersTest_11_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        /**
         * This test documents a need for BigDecimal conversion.
         */
        Object obj = JSONObject.stringToValue( "299792.457999999984" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        String str = new BigInteger( new Long( Long.MAX_VALUE ).toString() ).add( BigInteger.ONE ).toString();
        assertTrue( "Really large integers currently evaluate to BigInteger", JSONObject.stringToValue(str).equals(new BigInteger("9223372036854775808")));
    }

    @Test
    public void jsonValidNumberValuesNeitherLongNorIEEE754Compatible_1_oe() {
        // Valid JSON Numbers, probably should return BigDecimal or BigInteger objects
        String str = 
            "{"+
                "\"numberWithDecimals\":299792.457999999984,"+
                "\"largeNumber\":12345678901234567890,"+
                "\"preciseNumber\":0.2000000000000000111,"+
                "\"largeExponent\":-23.45e2327"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // Comes back as a double, but loses precision
        assertTrue( "numberWithDecimals currently evaluates to double 299792.458", jsonObject.get( "numberWithDecimals" ).equals( new BigDecimal( "299792.457999999984" ) ) );
    }

    @Test
    public void jsonValidNumberValuesNeitherLongNorIEEE754Compatible_2_oe() {
        // Valid JSON Numbers, probably should return BigDecimal or BigInteger objects
        String str = 
            "{"+
                "\"numberWithDecimals\":299792.457999999984,"+
                "\"largeNumber\":12345678901234567890,"+
                "\"preciseNumber\":0.2000000000000000111,"+
                "\"largeExponent\":-23.45e2327"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // Comes back as a double, but loses precision
        // removed other assertion
        Object obj = jsonObject.get( "largeNumber" );
        assertTrue("largeNumber currently evaluates to BigInteger", new BigInteger("12345678901234567890").equals(obj));
    }

    @Test
    public void jsonValidNumberValuesNeitherLongNorIEEE754Compatible_3_oe() {
        // Valid JSON Numbers, probably should return BigDecimal or BigInteger objects
        String str = 
            "{"+
                "\"numberWithDecimals\":299792.457999999984,"+
                "\"largeNumber\":12345678901234567890,"+
                "\"preciseNumber\":0.2000000000000000111,"+
                "\"largeExponent\":-23.45e2327"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // Comes back as a double, but loses precision
        // removed other assertion
        Object obj = jsonObject.get( "largeNumber" );
        // removed other assertion
        // comes back as a double but loses precision
        assertEquals( "preciseNumber currently evaluates to double 0.2", 0.2, jsonObject.getDouble( "preciseNumber" ), 0.0);
    }

    @Test
    public void jsonValidNumberValuesNeitherLongNorIEEE754Compatible_4_oe() {
        // Valid JSON Numbers, probably should return BigDecimal or BigInteger objects
        String str = 
            "{"+
                "\"numberWithDecimals\":299792.457999999984,"+
                "\"largeNumber\":12345678901234567890,"+
                "\"preciseNumber\":0.2000000000000000111,"+
                "\"largeExponent\":-23.45e2327"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        // Comes back as a double, but loses precision
        // removed other assertion
        Object obj = jsonObject.get( "largeNumber" );
        // removed other assertion
        // comes back as a double but loses precision
        // removed other assertion
        obj = jsonObject.get( "largeExponent" );
        assertTrue("largeExponent should evaluate as a BigDecimal", new BigDecimal("-23.45e2327").equals(obj));
    }

    @Test
    public void jsonInvalidNumberValues_1_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        assertFalse( "hexNumber must not be a number (should throw exception!?)", obj instanceof Number );
    }

    @Test
    public void jsonInvalidNumberValues_2_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        assertTrue("hexNumber currently evaluates to string", obj.equals("-0x123"));
    }

    @Test
    public void jsonInvalidNumberValues_3_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        assertTrue( "tooManyZeros currently evaluates to string", jsonObject.get( "tooManyZeros" ).equals("00"));
    }

    @Test
    public void jsonInvalidNumberValues_4_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        assertTrue( "negativeInfinite currently evaluates to string", obj.equals("-Infinity"));
    }

    @Test
    public void jsonInvalidNumberValues_5_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        assertTrue( "negativeNaN currently evaluates to string", obj.equals("-NaN"));
    }

    @Test
    public void jsonInvalidNumberValues_6_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        // removed other assertion
        assertTrue( "negativeFraction currently evaluates to double -0.01", jsonObject.get( "negativeFraction" ).equals(BigDecimal.valueOf(-0.01)));
    }

    @Test
    public void jsonInvalidNumberValues_7_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        // removed other assertion
        // removed other assertion
        assertTrue( "tooManyZerosFraction currently evaluates to double 0.001", jsonObject.get( "tooManyZerosFraction" ).equals(BigDecimal.valueOf(0.001)));
    }

    @Test
    public void jsonInvalidNumberValues_8_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "negativeHexFloat currently evaluates to double -3.99951171875", jsonObject.get( "negativeHexFloat" ).equals(Double.valueOf(-3.99951171875)));
    }

    @Test
    public void jsonInvalidNumberValues_9_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("hexFloat currently evaluates to double 4.9E-324", jsonObject.get("hexFloat").equals(Double.valueOf(4.9E-324)));
    }

    @Test
    public void jsonInvalidNumberValues_10_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("floatIdentifier currently evaluates to double 0.1", jsonObject.get("floatIdentifier").equals(Double.valueOf(0.1)));
    }

    @Test
    public void jsonInvalidNumberValues_11_oe() {
            // Number-notations supported by Java and invalid as JSON
        String str = 
            "{"+
                "\"hexNumber\":-0x123,"+
                "\"tooManyZeros\":00,"+
                "\"negativeInfinite\":-Infinity,"+
                "\"negativeNaN\":-NaN,"+
                "\"negativeFraction\":-.01,"+
                "\"tooManyZerosFraction\":00.001,"+
                "\"negativeHexFloat\":-0x1.fffp1,"+
                "\"hexFloat\":0x1.0P-1074,"+
                "\"floatIdentifier\":0.1f,"+
                "\"doubleIdentifier\":0.1d"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        Object obj;
        obj = jsonObject.get( "hexNumber" );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        obj = jsonObject.get("negativeInfinite");
        // removed other assertion
        obj = jsonObject.get("negativeNaN");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("doubleIdentifier currently evaluates to double 0.1", jsonObject.get("doubleIdentifier").equals(Double.valueOf(0.1)));
    }

    @Test
    public void jsonObjectNonAndWrongValues_2_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_4_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a Boolean (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_6_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_8_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"trueKey\"] is not a string (class java.lang.Boolean : true).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_10_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_12_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a double (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_14_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_16_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a float (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_18_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_20_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a int (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_22_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_24_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a long (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_26_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_28_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a JSONArray (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_30_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONObject("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"nonKey\"] not found.", e.getMessage());
    }
    }

    @Test
    public void jsonObjectNonAndWrongValues_32_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"trueStrKey\":\"true\","+
                "\"falseStrKey\":\"false\","+
                "\"stringKey\":\"hello world!\","+
                "\"intKey\":42,"+
                "\"intStrKey\":\"43\","+
                "\"longKey\":1234567890123456789,"+
                "\"longStrKey\":\"987654321098765432\","+
                "\"doubleKey\":-23.45e7,"+
                "\"doubleStrKey\":\"00001.000\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{\"myKey\":\"myVal\"}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);
        try {
            jsonObject.getBoolean("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getBoolean("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getString("trueKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getDouble("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getDouble("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getFloat("nonKey");
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        }
        try {
            jsonObject.getFloat("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getInt("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getLong("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONArray("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONObject("nonKey");
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            jsonObject.getJSONObject("stringKey");
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "JSONObject[\"stringKey\"] is not a JSONObject (class java.lang.String : hello world!).", e.getMessage());
    }
    }

    @Test
    public void unexpectedDoubleToIntConversion_1_oe() {
        String key30 = "key30";
        String key31 = "key31";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key30, new Double(3.0));
        jsonObject.put(key31, new Double(3.1));

        assertTrue("3.0 should remain a double", jsonObject.getDouble(key30) == 3);
    }

    @Test
    public void unexpectedDoubleToIntConversion_2_oe() {
        String key30 = "key30";
        String key31 = "key31";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key30, new Double(3.0));
        jsonObject.put(key31, new Double(3.1));

        // removed other assertion
        assertTrue("3.1 should remain a double", jsonObject.getDouble(key31) == 3.1);
    }

    @Test
    public void unexpectedDoubleToIntConversion_3_oe() {
        String key30 = "key30";
        String key31 = "key31";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key30, new Double(3.0));
        jsonObject.put(key31, new Double(3.1));

        // removed other assertion
        // removed other assertion
 
        // turns 3.0 into 3.
        String serializedString = jsonObject.toString();
        JSONObject deserialized = new JSONObject(serializedString);
        assertTrue("3.0 is now an int", deserialized.get(key30) instanceof Integer);
    }

    @Test
    public void unexpectedDoubleToIntConversion_4_oe() {
        String key30 = "key30";
        String key31 = "key31";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key30, new Double(3.0));
        jsonObject.put(key31, new Double(3.1));

        // removed other assertion
        // removed other assertion
 
        // turns 3.0 into 3.
        String serializedString = jsonObject.toString();
        JSONObject deserialized = new JSONObject(serializedString);
        // removed other assertion
        assertTrue("3.0 can still be interpreted as a double", deserialized.getDouble(key30) == 3.0);
    }

    @Test
    public void unexpectedDoubleToIntConversion_5_oe() {
        String key30 = "key30";
        String key31 = "key31";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key30, new Double(3.0));
        jsonObject.put(key31, new Double(3.1));

        // removed other assertion
        // removed other assertion
 
        // turns 3.0 into 3.
        String serializedString = jsonObject.toString();
        JSONObject deserialized = new JSONObject(serializedString);
        // removed other assertion
        // removed other assertion
        assertTrue("3.1 remains a double", deserialized.getDouble(key31) == 3.1);
    }

    @Test
    public void bigNumberOperations_1_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        assertTrue("JSONObject only has 1 value", jsonObject0.length() == 1);
    }

    @Test
    public void bigNumberOperations_2_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        assertTrue("JSONObject parses BigInteger as the Integer lowestBitSet", obj instanceof Integer);
    }

    @Test
    public void bigNumberOperations_3_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        assertTrue("this bigInteger lowestBitSet happens to be 1", obj.equals(1));
    }

    @Test
    public void bigNumberOperations_4_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        assertTrue("large bigDecimal is not stored", jsonObject1.isEmpty());
    }

    @Test
    public void bigNumberOperations_5_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        assertTrue("jsonObject.put() handles bigInt correctly", jsonObject2.get("bigInt").equals(bigInteger));
    }

    @Test
    public void bigNumberOperations_6_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        assertTrue("jsonObject.getBigInteger() handles bigInt correctly", jsonObject2.getBigInteger("bigInt").equals(bigInteger));
    }

    @Test
    public void bigNumberOperations_7_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject.optBigInteger() handles bigInt correctly", jsonObject2.optBigInteger("bigInt", BigInteger.ONE).equals(bigInteger));
    }

    @Test
    public void bigNumberOperations_8_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject serializes bigInt correctly", jsonObject2.toString().equals("{\"bigInt\":123456789012345678901234567890}"));
    }

    @Test
    public void bigNumberOperations_9_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("BigInteger as BigDecimal", jsonObject2.getBigDecimal("bigInt").equals(new BigDecimal(bigInteger)));
    }

    @Test
    public void bigNumberOperations_10_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("bigDec", bigDecimal);
        assertTrue("jsonObject.put() handles bigDec correctly", jsonObject3.get("bigDec").equals(bigDecimal));
    }

    @Test
    public void bigNumberOperations_11_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("bigDec", bigDecimal);
        // removed other assertion
        assertTrue("jsonObject.getBigDecimal() handles bigDec correctly", jsonObject3.getBigDecimal("bigDec").equals(bigDecimal));
    }

    @Test
    public void bigNumberOperations_12_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("bigDec", bigDecimal);
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject.optBigDecimal() handles bigDec correctly", jsonObject3.optBigDecimal("bigDec", BigDecimal.ONE).equals(bigDecimal));
    }

    @Test
    public void bigNumberOperations_13_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("bigDec", bigDecimal);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("jsonObject serializes bigDec correctly", jsonObject3.toString().equals( "{\"bigDec\":123456789012345678901234567890.12345678901234567890123456789}"));
    }

    @Test
    public void bigNumberOperations_14_oe() {
        /**
         * JSONObject tries to parse BigInteger as a bean, but it only has
         * one getter, getLowestBitSet(). The value is lost and an unhelpful
         * value is stored. This should be fixed.
         */
        BigInteger bigInteger = new BigInteger("123456789012345678901234567890");
        JSONObject jsonObject0 = new JSONObject(bigInteger);
        Object obj = jsonObject0.get("lowestSetBit");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * JSONObject tries to parse BigDecimal as a bean, but it has
         * no getters, The value is lost and no value is stored.
         * This should be fixed.
         */
        BigDecimal bigDecimal = new BigDecimal(
                "123456789012345678901234567890.12345678901234567890123456789");
        JSONObject jsonObject1 = new JSONObject(bigDecimal);
        // removed other assertion

        /**
         * JSONObject put(String, Object) method stores and serializes
         * bigInt and bigDec correctly. Nothing needs to change. 
         */
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("bigInt", bigInteger);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("bigDec", bigDecimal);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue("BigDecimal as BigInteger", jsonObject3.getBigInteger("bigDec").equals(bigDecimal.toBigInteger()));
    }

    @Test
    public void jsonObjectNames_1_oe() {

        // getNames() from null JSONObject
        assertTrue("null names from null Object", null == JSONObject.getNames((Object)null));
    }

    @Test
    public void jsonObjectNames_2_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        assertTrue("null names from Object with no fields", null == JSONObject.getNames(new MyJsonString()));
    }

    @Test
    public void jsonObjectNames_3_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        assertTrue("names should be null", names == null);
    }

    @Test
    public void jsonObjectNames_4_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        assertTrue("empty JSONObject should have null names", null == JSONObject.getNames(jsonObject1));
    }

    @Test
    public void jsonObjectNames_5_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        assertTrue("expected 3 items", docList.size() == 3);
    }

    @Test
    public void jsonObjectNames_6_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        assertTrue( "expected to find trueKey", ((List<?>) JsonPath.read(doc, "$[?(@=='trueKey')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_7_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        assertTrue( "expected to find falseKey", ((List<?>) JsonPath.read(doc, "$[?(@=='falseKey')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_8_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "expected to find stringKey", ((List<?>) JsonPath.read(doc, "$[?(@=='stringKey')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_9_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        assertTrue("expected 3 items", docList.size() == 3);
    }

    @Test
    public void jsonObjectNames_10_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        assertTrue( "expected to find VAL1", ((List<?>) JsonPath.read(doc, "$[?(@=='VAL1')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_11_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        assertTrue( "expected to find VAL2", ((List<?>) JsonPath.read(doc, "$[?(@=='VAL2')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_12_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue( "expected to find VAL3", ((List<?>) JsonPath.read(doc, "$[?(@=='VAL3')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_13_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * A bean is also an object. But in order to test the static
         * method getNames(), this particular bean needs some public
         * data members.
         */
        MyPublicClass myPublicClass = new MyPublicClass();
        names = JSONObject.getNames(myPublicClass);

        // validate JSON
        JSONArray jsonArray2 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray2.toString());
        docList = JsonPath.read(doc, "$");
        assertTrue("expected 2 items", docList.size() == 2);
    }

    @Test
    public void jsonObjectNames_14_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * A bean is also an object. But in order to test the static
         * method getNames(), this particular bean needs some public
         * data members.
         */
        MyPublicClass myPublicClass = new MyPublicClass();
        names = JSONObject.getNames(myPublicClass);

        // validate JSON
        JSONArray jsonArray2 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray2.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        assertTrue( "expected to find publicString", ((List<?>) JsonPath.read(doc, "$[?(@=='publicString')]")).size() == 1);
    }

    @Test
    public void jsonObjectNames_15_oe() {

        // getNames() from null JSONObject
        // removed other assertion

        // getNames() from object with no fields
        // removed other assertion

        // getNames from new JSONOjbect
        JSONObject jsonObject0 = new JSONObject();
        String [] names = JSONObject.getNames(jsonObject0);
        // removed other assertion

        
        // getNames() from empty JSONObject
        String emptyStr = "{}";
        JSONObject jsonObject1 = new JSONObject(emptyStr);
        // removed other assertion

        // getNames() from JSONObject
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";
        JSONObject jsonObject2 = new JSONObject(str);
        names = JSONObject.getNames(jsonObject2);
        JSONArray jsonArray0 = new JSONArray(names);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray0.toString());
        List<?> docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * getNames() from an enum with properties has an interesting result.
         * It returns the enum values, not the selected enum properties
         */
        MyEnumField myEnumField = MyEnumField.VAL1;
        names = JSONObject.getNames(myEnumField);

        // validate JSON
        JSONArray jsonArray1 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray1.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * A bean is also an object. But in order to test the static
         * method getNames(), this particular bean needs some public
         * data members.
         */
        MyPublicClass myPublicClass = new MyPublicClass();
        names = JSONObject.getNames(myPublicClass);

        // validate JSON
        JSONArray jsonArray2 = new JSONArray(names);
        doc = Configuration.defaultConfiguration().jsonProvider()
                .parse(jsonArray2.toString());
        docList = JsonPath.read(doc, "$");
        // removed other assertion
        // removed other assertion
        assertTrue( "expected to find publicInt", ((List<?>) JsonPath.read(doc, "$[?(@=='publicInt')]")).size() == 1);
    }

    @Test
    public void emptyJsonObjectNamesToJsonAray_1_oe() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = jsonObject.names();
        assertTrue("jsonArray should be null", jsonArray == null);
    }

    @Test
    public void jsonObjectNamesToJsonAray_1_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";

        JSONObject jsonObject = new JSONObject(str);
        JSONArray jsonArray = jsonObject.names();

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 3 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

    @Test
    public void jsonObjectNamesToJsonAray_2_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";

        JSONObject jsonObject = new JSONObject(str);
        JSONArray jsonArray = jsonObject.names();

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        assertTrue("expected to find trueKey", ((List<?>) JsonPath.read(doc, "$[?(@=='trueKey')]")).size() == 1);
    }

    @Test
    public void jsonObjectNamesToJsonAray_3_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";

        JSONObject jsonObject = new JSONObject(str);
        JSONArray jsonArray = jsonObject.names();

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected to find falseKey", ((List<?>) JsonPath.read(doc, "$[?(@=='falseKey')]")).size() == 1);
    }

    @Test
    public void jsonObjectNamesToJsonAray_4_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"stringKey\":\"hello world!\","+
            "}";

        JSONObject jsonObject = new JSONObject(str);
        JSONArray jsonArray = jsonObject.names();

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected to find stringKey", ((List<?>) JsonPath.read(doc, "$[?(@=='stringKey')]")).size() == 1);
    }

    @Test
    public void jsonObjectIncrement_1_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 6 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 6);
    }

    @Test
    public void jsonObjectIncrement_2_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 3", Integer.valueOf(3).equals(jsonObject.query("/keyInt")));
    }

    @Test
    public void jsonObjectIncrement_3_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 9999999993", Long.valueOf(9999999993L).equals(jsonObject.query("/keyLong")));
    }

    @Test
    public void jsonObjectIncrement_4_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3.1", BigDecimal.valueOf(3.1).equals(jsonObject.query("/keyDouble")));
    }

    @Test
    public void jsonObjectIncrement_5_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 123456789123456789123456789123456781", new BigInteger("123456789123456789123456789123456781").equals(jsonObject.query("/keyBigInt")));
    }

    @Test
    public void jsonObjectIncrement_6_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 123456789123456789123456789123456781.1", new BigDecimal("123456789123456789123456789123456781.1").equals(jsonObject.query("/keyBigDec")));
    }

    @Test
    public void jsonObjectIncrement_7_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        assertEquals(Float.valueOf(3.1f), jsonObject.query("/keyFloat"));
    }

    @Test
    public void jsonObjectIncrement_8_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        assertFalse("Document unexpected behaviour with explicit type-casting float as double!", (double)0.2f == 0.2d );
    }

    @Test
    public void jsonObjectIncrement_9_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        // removed other assertion
        assertFalse("Document unexpected behaviour with implicit type-cast!", 0.2f == 0.2d );
    }

    @Test
    public void jsonObjectIncrement_10_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        // removed other assertion
        // removed other assertion
        Double d1 = new Double( 1.1f );
        Double d2 = new Double( "1.1f" );
        assertFalse( "Document implicit type cast from float to double before calling Double(double d) constructor", d1.equals( d2 ) );
    }

    @Test
    public void jsonObjectIncrement_11_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        // removed other assertion
        // removed other assertion
        Double d1 = new Double( 1.1f );
        Double d2 = new Double( "1.1f" );
        // removed other assertion

        assertTrue( "Correctly converting float to double via base10 (string) representation!", new Double( 3.1d ).equals(  new Double( new Float( 3.1f ).toString() ) ) );
    }

    @Test
    public void jsonObjectIncrement_12_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        // removed other assertion
        // removed other assertion
        Double d1 = new Double( 1.1f );
        Double d2 = new Double( "1.1f" );
        // removed other assertion

        // removed other assertion

        // Pinpointing the not so obvious "buggy" conversion from float to double in JSONObject
        JSONObject jo = new JSONObject();
        jo.put( "bug", 3.1f ); // will call put( String key, double value ) with implicit and "buggy" type-cast from float to double
        assertFalse( "The java-compiler did add some zero bits for you to the mantissa (unexpected, but well documented)", jo.get( "bug" ).equals(  new Double( 3.1d ) ) );
    }

    @Test
    public void jsonObjectIncrement_13_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        // removed other assertion
        // removed other assertion
        Double d1 = new Double( 1.1f );
        Double d2 = new Double( "1.1f" );
        // removed other assertion

        // removed other assertion

        // Pinpointing the not so obvious "buggy" conversion from float to double in JSONObject
        JSONObject jo = new JSONObject();
        jo.put( "bug", 3.1f ); // will call put( String key, double value ) with implicit and "buggy" type-cast from float to double
        // removed other assertion

        JSONObject inc = new JSONObject();
        inc.put( "bug", new Float( 3.1f ) ); // This will put in instance of Float into JSONObject, i.e. call put( String key, Object value )
        assertTrue( "Everything is ok here!", inc.get( "bug" ) instanceof Float );
    }

    @Test
    public void jsonObjectIncrement_14_oe() {
        String str = 
            "{"+
                "\"keyLong\":9999999991,"+
                "\"keyDouble\":1.1"+
             "}";
        JSONObject jsonObject = new JSONObject(str);
        jsonObject.increment("keyInt");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        jsonObject.increment("keyInt");
        jsonObject.increment("keyLong");
        jsonObject.increment("keyDouble");
        /**
         * JSONObject constructor won't handle these types correctly, but
         * adding them via put works.
         */
        jsonObject.put("keyFloat", 1.1f);
        jsonObject.put("keyBigInt", new BigInteger("123456789123456789123456789123456780"));
        jsonObject.put("keyBigDec", new BigDecimal("123456789123456789123456789123456780.1"));
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyFloat");
        jsonObject.increment("keyBigInt");
        jsonObject.increment("keyBigDec");

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        /**
         * Should work the same way on any platform! @see https://docs.oracle
         * .com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.3 This is the
         * effect of a float to double conversion and is inherent to the
         * shortcomings of the IEEE 754 format, when converting 32-bit into
         * double-precision 64-bit. Java type-casts float to double. A 32 bit
         * float is type-casted to 64 bit double by simply appending zero-bits
         * to the mantissa (and extended the signed exponent by 3 bits.) and
         * there is no way to obtain more information than it is stored in the
         * 32-bits float.
         * 
         * Like 1/3 cannot be represented as base10 number because it is
         * periodically, 1/5 (for example) cannot be represented as base2 number
         * since it is periodically in base2 (take a look at
         * http://www.h-schmidt.net/FloatConverter/) The same happens to 3.1,
         * that decimal number (base10 representation) is periodic in base2
         * representation, therefore appending zero-bits is inaccurate. Only
         * repeating the periodically occurring bits (0110) would be a proper
         * conversion. However one cannot detect from a 32 bit IEE754
         * representation which bits would "repeat infinitely", since the
         * missing bits would not fit into the 32 bit float, i.e. the
         * information needed simply is not there!
         */
        // removed other assertion

        /**
         * float f = 3.1f; double df = (double) f; double d = 3.1d;
         * System.out.println
         * (Integer.toBinaryString(Float.floatToRawIntBits(f)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(df)));
         * System.out.println
         * (Long.toBinaryString(Double.doubleToRawLongBits(d)));
         * 
         * - Float:
         * seeeeeeeemmmmmmmmmmmmmmmmmmmmmmm
         * 1000000010001100110011001100110
         * - Double
         * seeeeeeeeeeemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
         * 10000000   10001100110011001100110
         * 100000000001000110011001100110011000000000000000000000000000000
         * 100000000001000110011001100110011001100110011001100110011001101
         */

        /**
        * Examples of well documented but probably unexpected behavior in 
        * java / with 32-bit float to 64-bit float conversion.
        */
        // removed other assertion
        // removed other assertion
        Double d1 = new Double( 1.1f );
        Double d2 = new Double( "1.1f" );
        // removed other assertion

        // removed other assertion

        // Pinpointing the not so obvious "buggy" conversion from float to double in JSONObject
        JSONObject jo = new JSONObject();
        jo.put( "bug", 3.1f ); // will call put( String key, double value ) with implicit and "buggy" type-cast from float to double
        // removed other assertion

        JSONObject inc = new JSONObject();
        inc.put( "bug", new Float( 3.1f ) ); // This will put in instance of Float into JSONObject, i.e. call put( String key, Object value )
        // removed other assertion
        inc.increment( "bug" ); // after adding 1, increment will call put( String key, double value ) with implicit and "buggy" type-cast from float to double!
        // this.put(key, (Float) value + 1);
        // 1.        The (Object)value will be typecasted to (Float)value since it is an instanceof Float actually nothing is done. 
        // 2.        Float instance will be autoboxed into float because the + operator will work on primitives not Objects!
        // 3.        A float+float operation will be performed and results into a float primitive.
        // 4.        There is no method that matches the signature put( String key, float value), java-compiler will choose the method
        //                put( String key, double value) and does an implicit type-cast(!) by appending zero-bits to the mantissa
        assertTrue( "JSONObject increment converts Float to Double", jo.get( "bug" ) instanceof Float );
    }

    @Test
    public void jsonObjectNumberToString_1_oe() {
        String str;
        Double dVal;
        Integer iVal = 1;
        str = JSONObject.numberToString(iVal);
        assertTrue("expected "+iVal+" actual "+str, iVal.toString().equals(str));
    }

    @Test
    public void jsonObjectNumberToString_2_oe() {
        String str;
        Double dVal;
        Integer iVal = 1;
        str = JSONObject.numberToString(iVal);
        // removed other assertion
        dVal = 12.34;
        str = JSONObject.numberToString(dVal);
        assertTrue("expected "+dVal+" actual "+str, dVal.toString().equals(str));
    }

    @Test
    public void jsonObjectNumberToString_3_oe() {
        String str;
        Double dVal;
        Integer iVal = 1;
        str = JSONObject.numberToString(iVal);
        // removed other assertion
        dVal = 12.34;
        str = JSONObject.numberToString(dVal);
        // removed other assertion
        dVal = 12.34e27;
        str = JSONObject.numberToString(dVal);
        assertTrue("expected "+dVal+" actual "+str, dVal.toString().equals(str));
    }

    @Test
    public void jsonObjectNumberToString_4_oe() {
        String str;
        Double dVal;
        Integer iVal = 1;
        str = JSONObject.numberToString(iVal);
        // removed other assertion
        dVal = 12.34;
        str = JSONObject.numberToString(dVal);
        // removed other assertion
        dVal = 12.34e27;
        str = JSONObject.numberToString(dVal);
        // removed other assertion
        // trailing .0 is truncated, so it doesn't quite match toString()
        dVal = 5000000.0000000;
        str = JSONObject.numberToString(dVal);
        assertTrue("expected 5000000 actual "+str, str.equals("5000000"));
    }

    @Test
    public void jsonObjectPut_1_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 4 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 4);
    }

    @Test
    public void jsonObjectPut_2_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/trueKey")));
    }

    @Test
    public void jsonObjectPut_3_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/falseKey")));
    }

    @Test
    public void jsonObjectPut_4_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3 arrayKey items", ((List<?>)(JsonPath.read(doc, "$.arrayKey"))).size() == 3);
    }

    @Test
    public void jsonObjectPut_5_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 0", Integer.valueOf(0).equals(jsonObject.query("/arrayKey/0")));
    }

    @Test
    public void jsonObjectPut_6_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonObject.query("/arrayKey/1")));
    }

    @Test
    public void jsonObjectPut_7_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonObject.query("/arrayKey/2")));
    }

    @Test
    public void jsonObjectPut_8_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 4 objectKey items", ((Map<?,?>)(JsonPath.read(doc, "$.objectKey"))).size() == 4);
    }

    @Test
    public void jsonObjectPut_9_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myVal1", "myVal1".equals(jsonObject.query("/objectKey/myKey1")));
    }

    @Test
    public void jsonObjectPut_10_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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
        assertTrue("expected myVal2", "myVal2".equals(jsonObject.query("/objectKey/myKey2")));
    }

    @Test
    public void jsonObjectPut_11_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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
        assertTrue("expected myVal3", "myVal3".equals(jsonObject.query("/objectKey/myKey3")));
    }

    @Test
    public void jsonObjectPut_12_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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
        assertTrue("expected myVal4", "myVal4".equals(jsonObject.query("/objectKey/myKey4")));
    }

    @Test
    public void jsonObjectPut_13_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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

        jsonObject.remove("trueKey");
        JSONObject expectedJsonObject = new JSONObject(expectedStr);
        assertTrue("unequal jsonObjects should not be similar", !jsonObject.similar(expectedJsonObject));
    }

    @Test
    public void jsonObjectPut_14_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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

        jsonObject.remove("trueKey");
        JSONObject expectedJsonObject = new JSONObject(expectedStr);
        // removed other assertion
        assertTrue("jsonObject should not be similar to jsonArray", !jsonObject.similar(new JSONArray()));
    }

    @Test
    public void jsonObjectPut_15_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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

        jsonObject.remove("trueKey");
        JSONObject expectedJsonObject = new JSONObject(expectedStr);
        // removed other assertion
        // removed other assertion

        String aCompareValueStr = "{\"a\":\"aval\",\"b\":true}";
        String bCompareValueStr = "{\"a\":\"notAval\",\"b\":true}";
        JSONObject aCompareValueJsonObject = new JSONObject(aCompareValueStr);
        JSONObject bCompareValueJsonObject = new JSONObject(bCompareValueStr);
        assertTrue("different values should not be similar", !aCompareValueJsonObject.similar(bCompareValueJsonObject));
    }

    @Test
    public void jsonObjectPut_16_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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

        jsonObject.remove("trueKey");
        JSONObject expectedJsonObject = new JSONObject(expectedStr);
        // removed other assertion
        // removed other assertion

        String aCompareValueStr = "{\"a\":\"aval\",\"b\":true}";
        String bCompareValueStr = "{\"a\":\"notAval\",\"b\":true}";
        JSONObject aCompareValueJsonObject = new JSONObject(aCompareValueStr);
        JSONObject bCompareValueJsonObject = new JSONObject(bCompareValueStr);
        // removed other assertion

        String aCompareObjectStr = "{\"a\":\"aval\",\"b\":{}}";
        String bCompareObjectStr = "{\"a\":\"aval\",\"b\":true}";
        JSONObject aCompareObjectJsonObject = new JSONObject(aCompareObjectStr);
        JSONObject bCompareObjectJsonObject = new JSONObject(bCompareObjectStr);
        assertTrue("different nested JSONObjects should not be similar", !aCompareObjectJsonObject.similar(bCompareObjectJsonObject));
    }

    @Test
    public void jsonObjectPut_17_oe() {
        String expectedStr = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("trueKey", true);
        jsonObject.put("falseKey", false);
        Integer [] intArray = { 0, 1, 2 };
        jsonObject.put("arrayKey", Arrays.asList(intArray));
        Map<String, Object> myMap = new HashMap<String, Object>();
        myMap.put("myKey1", "myVal1");
        myMap.put("myKey2", "myVal2");
        myMap.put("myKey3", "myVal3");
        myMap.put("myKey4", "myVal4");
        jsonObject.put("objectKey", myMap);

        // validate JSON
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

        jsonObject.remove("trueKey");
        JSONObject expectedJsonObject = new JSONObject(expectedStr);
        // removed other assertion
        // removed other assertion

        String aCompareValueStr = "{\"a\":\"aval\",\"b\":true}";
        String bCompareValueStr = "{\"a\":\"notAval\",\"b\":true}";
        JSONObject aCompareValueJsonObject = new JSONObject(aCompareValueStr);
        JSONObject bCompareValueJsonObject = new JSONObject(bCompareValueStr);
        // removed other assertion

        String aCompareObjectStr = "{\"a\":\"aval\",\"b\":{}}";
        String bCompareObjectStr = "{\"a\":\"aval\",\"b\":true}";
        JSONObject aCompareObjectJsonObject = new JSONObject(aCompareObjectStr);
        JSONObject bCompareObjectJsonObject = new JSONObject(bCompareObjectStr);
        // removed other assertion

        String aCompareArrayStr = "{\"a\":\"aval\",\"b\":[]}";
        String bCompareArrayStr = "{\"a\":\"aval\",\"b\":true}";
        JSONObject aCompareArrayJsonObject = new JSONObject(aCompareArrayStr);
        JSONObject bCompareArrayJsonObject = new JSONObject(bCompareArrayStr);
        assertTrue("different nested JSONArrays should not be similar", !aCompareArrayJsonObject.similar(bCompareArrayJsonObject));
    }

    @Test
    public void jsonObjectToString_1_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 4 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 4);
    }

    @Test
    public void jsonObjectToString_2_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected true", Boolean.TRUE.equals(jsonObject.query("/trueKey")));
    }

    @Test
    public void jsonObjectToString_3_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected false", Boolean.FALSE.equals(jsonObject.query("/falseKey")));
    }

    @Test
    public void jsonObjectToString_4_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3 arrayKey items", ((List<?>)(JsonPath.read(doc, "$.arrayKey"))).size() == 3);
    }

    @Test
    public void jsonObjectToString_5_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 0", Integer.valueOf(0).equals(jsonObject.query("/arrayKey/0")));
    }

    @Test
    public void jsonObjectToString_6_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonObject.query("/arrayKey/1")));
    }

    @Test
    public void jsonObjectToString_7_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonObject.query("/arrayKey/2")));
    }

    @Test
    public void jsonObjectToString_8_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 4 objectKey items", ((Map<?,?>)(JsonPath.read(doc, "$.objectKey"))).size() == 4);
    }

    @Test
    public void jsonObjectToString_9_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected myVal1", "myVal1".equals(jsonObject.query("/objectKey/myKey1")));
    }

    @Test
    public void jsonObjectToString_10_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
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
        assertTrue("expected myVal2", "myVal2".equals(jsonObject.query("/objectKey/myKey2")));
    }

    @Test
    public void jsonObjectToString_11_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
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
        assertTrue("expected myVal3", "myVal3".equals(jsonObject.query("/objectKey/myKey3")));
    }

    @Test
    public void jsonObjectToString_12_oe() {
        String str = 
            "{"+
                "\"trueKey\":true,"+
                "\"falseKey\":false,"+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\":{"+
                    "\"myKey1\":\"myVal1\","+
                    "\"myKey2\":\"myVal2\","+
                    "\"myKey3\":\"myVal3\","+
                    "\"myKey4\":\"myVal4\""+
                "}"+
            "}";
        JSONObject jsonObject = new JSONObject(str);

        // validate JSON
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
        assertTrue("expected myVal4", "myVal4".equals(jsonObject.query("/objectKey/myKey4")));
    }

    @Test
    public void jsonObjectToStringIndent_1_oe() {
        String jsonObject0Str =
                "{"+
                        "\"key1\":" +
                                "[1,2," +
                                        "{\"key3\":true}" +
                                "],"+
                        "\"key2\":" +
                                "{\"key1\":\"val1\",\"key2\":" +
                                        "{\"key2\":\"val2\"}" +
                                "},"+
                        "\"key3\":" +
                                "[" +
                                        "[1,2.1]" +
                                "," +
                                        "[null]" +
                                "]"+
                        "}";

        String jsonObject1Str =
                "{\n" +
                " \"key1\": [\n" +
                "  1,\n" +
                "  2,\n" +
                "  {\"key3\": true}\n" +
                " ],\n" +
                " \"key2\": {\n" +
                "  \"key1\": \"val1\",\n" +
                "  \"key2\": {\"key2\": \"val2\"}\n" +
                " },\n" +
                " \"key3\": [\n" +
                "  [\n" +
                "   1,\n" +
                "   2.1\n" +
                "  ],\n" +
                "  [null]\n" +
                " ]\n" +
                "}";
        String jsonObject4Str =
                "{\n" +
                "    \"key1\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        {\"key3\": true}\n" +
                "    ],\n" +
                "    \"key2\": {\n" +
                "        \"key1\": \"val1\",\n" +
                "        \"key2\": {\"key2\": \"val2\"}\n" +
                "    },\n" +
                "    \"key3\": [\n" +
                "        [\n" +
                "            1,\n" +
                "            2.1\n" +
                "        ],\n" +
                "        [null]\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(jsonObject0Str);
        // contents are tested in other methods, in this case just validate the spacing by
        // checking length
        assertEquals("toString() length",jsonObject0Str.length(), jsonObject.toString().length());
    }

    @Test
    public void jsonObjectToStringIndent_2_oe() {
        String jsonObject0Str =
                "{"+
                        "\"key1\":" +
                                "[1,2," +
                                        "{\"key3\":true}" +
                                "],"+
                        "\"key2\":" +
                                "{\"key1\":\"val1\",\"key2\":" +
                                        "{\"key2\":\"val2\"}" +
                                "},"+
                        "\"key3\":" +
                                "[" +
                                        "[1,2.1]" +
                                "," +
                                        "[null]" +
                                "]"+
                        "}";

        String jsonObject1Str =
                "{\n" +
                " \"key1\": [\n" +
                "  1,\n" +
                "  2,\n" +
                "  {\"key3\": true}\n" +
                " ],\n" +
                " \"key2\": {\n" +
                "  \"key1\": \"val1\",\n" +
                "  \"key2\": {\"key2\": \"val2\"}\n" +
                " },\n" +
                " \"key3\": [\n" +
                "  [\n" +
                "   1,\n" +
                "   2.1\n" +
                "  ],\n" +
                "  [null]\n" +
                " ]\n" +
                "}";
        String jsonObject4Str =
                "{\n" +
                "    \"key1\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        {\"key3\": true}\n" +
                "    ],\n" +
                "    \"key2\": {\n" +
                "        \"key1\": \"val1\",\n" +
                "        \"key2\": {\"key2\": \"val2\"}\n" +
                "    },\n" +
                "    \"key3\": [\n" +
                "        [\n" +
                "            1,\n" +
                "            2.1\n" +
                "        ],\n" +
                "        [null]\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(jsonObject0Str);
        // contents are tested in other methods, in this case just validate the spacing by
        // checking length
        // removed other assertion
        assertEquals("toString(0) length",jsonObject0Str.length(), jsonObject.toString(0).length());
    }

    @Test
    public void jsonObjectToStringIndent_3_oe() {
        String jsonObject0Str =
                "{"+
                        "\"key1\":" +
                                "[1,2," +
                                        "{\"key3\":true}" +
                                "],"+
                        "\"key2\":" +
                                "{\"key1\":\"val1\",\"key2\":" +
                                        "{\"key2\":\"val2\"}" +
                                "},"+
                        "\"key3\":" +
                                "[" +
                                        "[1,2.1]" +
                                "," +
                                        "[null]" +
                                "]"+
                        "}";

        String jsonObject1Str =
                "{\n" +
                " \"key1\": [\n" +
                "  1,\n" +
                "  2,\n" +
                "  {\"key3\": true}\n" +
                " ],\n" +
                " \"key2\": {\n" +
                "  \"key1\": \"val1\",\n" +
                "  \"key2\": {\"key2\": \"val2\"}\n" +
                " },\n" +
                " \"key3\": [\n" +
                "  [\n" +
                "   1,\n" +
                "   2.1\n" +
                "  ],\n" +
                "  [null]\n" +
                " ]\n" +
                "}";
        String jsonObject4Str =
                "{\n" +
                "    \"key1\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        {\"key3\": true}\n" +
                "    ],\n" +
                "    \"key2\": {\n" +
                "        \"key1\": \"val1\",\n" +
                "        \"key2\": {\"key2\": \"val2\"}\n" +
                "    },\n" +
                "    \"key3\": [\n" +
                "        [\n" +
                "            1,\n" +
                "            2.1\n" +
                "        ],\n" +
                "        [null]\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(jsonObject0Str);
        // contents are tested in other methods, in this case just validate the spacing by
        // checking length
        // removed other assertion
        // removed other assertion
        assertEquals("toString(1) length",jsonObject1Str.length(), jsonObject.toString(1).length());
    }

    @Test
    public void jsonObjectToStringIndent_4_oe() {
        String jsonObject0Str =
                "{"+
                        "\"key1\":" +
                                "[1,2," +
                                        "{\"key3\":true}" +
                                "],"+
                        "\"key2\":" +
                                "{\"key1\":\"val1\",\"key2\":" +
                                        "{\"key2\":\"val2\"}" +
                                "},"+
                        "\"key3\":" +
                                "[" +
                                        "[1,2.1]" +
                                "," +
                                        "[null]" +
                                "]"+
                        "}";

        String jsonObject1Str =
                "{\n" +
                " \"key1\": [\n" +
                "  1,\n" +
                "  2,\n" +
                "  {\"key3\": true}\n" +
                " ],\n" +
                " \"key2\": {\n" +
                "  \"key1\": \"val1\",\n" +
                "  \"key2\": {\"key2\": \"val2\"}\n" +
                " },\n" +
                " \"key3\": [\n" +
                "  [\n" +
                "   1,\n" +
                "   2.1\n" +
                "  ],\n" +
                "  [null]\n" +
                " ]\n" +
                "}";
        String jsonObject4Str =
                "{\n" +
                "    \"key1\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        {\"key3\": true}\n" +
                "    ],\n" +
                "    \"key2\": {\n" +
                "        \"key1\": \"val1\",\n" +
                "        \"key2\": {\"key2\": \"val2\"}\n" +
                "    },\n" +
                "    \"key3\": [\n" +
                "        [\n" +
                "            1,\n" +
                "            2.1\n" +
                "        ],\n" +
                "        [null]\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(jsonObject0Str);
        // contents are tested in other methods, in this case just validate the spacing by
        // checking length
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("toString(4) length",jsonObject4Str.length(), jsonObject.toString(4).length());
    }

    @Test
    public void jsonObjectToStringIndent_5_oe() {
        String jsonObject0Str =
                "{"+
                        "\"key1\":" +
                                "[1,2," +
                                        "{\"key3\":true}" +
                                "],"+
                        "\"key2\":" +
                                "{\"key1\":\"val1\",\"key2\":" +
                                        "{\"key2\":\"val2\"}" +
                                "},"+
                        "\"key3\":" +
                                "[" +
                                        "[1,2.1]" +
                                "," +
                                        "[null]" +
                                "]"+
                        "}";

        String jsonObject1Str =
                "{\n" +
                " \"key1\": [\n" +
                "  1,\n" +
                "  2,\n" +
                "  {\"key3\": true}\n" +
                " ],\n" +
                " \"key2\": {\n" +
                "  \"key1\": \"val1\",\n" +
                "  \"key2\": {\"key2\": \"val2\"}\n" +
                " },\n" +
                " \"key3\": [\n" +
                "  [\n" +
                "   1,\n" +
                "   2.1\n" +
                "  ],\n" +
                "  [null]\n" +
                " ]\n" +
                "}";
        String jsonObject4Str =
                "{\n" +
                "    \"key1\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        {\"key3\": true}\n" +
                "    ],\n" +
                "    \"key2\": {\n" +
                "        \"key1\": \"val1\",\n" +
                "        \"key2\": {\"key2\": \"val2\"}\n" +
                "    },\n" +
                "    \"key3\": [\n" +
                "        [\n" +
                "            1,\n" +
                "            2.1\n" +
                "        ],\n" +
                "        [null]\n" +
                "    ]\n" +
                "}";
        JSONObject jsonObject = new JSONObject(jsonObject0Str);
        // contents are tested in other methods, in this case just validate the spacing by
        // checking length
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        JSONObject jo = new JSONObject().put("TABLE", new JSONObject().put("yhoo", new JSONObject()));
        assertEquals("toString(2)","{\"TABLE\": {\"yhoo\": {}}}", jo.toString(2));
    }

    @Test
    public void jsonObjectToStringSuppressWarningOnCastToMap_1_oe() {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> map = new HashMap();
        map.put("abc", "def");
        jsonObject.put("key", map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

    @Test
    public void jsonObjectToStringSuppressWarningOnCastToMap_2_oe() {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> map = new HashMap();
        map.put("abc", "def");
        jsonObject.put("key", map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 1 key item", ((Map<?,?>)(JsonPath.read(doc, "$.key"))).size() == 1);
    }

    @Test
    public void jsonObjectToStringSuppressWarningOnCastToMap_3_oe() {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> map = new HashMap();
        map.put("abc", "def");
        jsonObject.put("key", map);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected def", "def".equals(jsonObject.query("/key/abc")));
    }

    @Test
    public void jsonObjectToStringSuppressWarningOnCastToCollection_1_oe() {
        JSONObject jsonObject = new JSONObject();
        Collection<String> collection = new ArrayList<String>();
        collection.add("abc");
        // ArrayList will be added as an object
        jsonObject.put("key", collection);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        assertTrue("expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

    @Test
    public void jsonObjectToStringSuppressWarningOnCastToCollection_2_oe() {
        JSONObject jsonObject = new JSONObject();
        Collection<String> collection = new ArrayList<String>();
        collection.add("abc");
        // ArrayList will be added as an object
        jsonObject.put("key", collection);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        assertTrue("expected 1 key item", ((List<?>)(JsonPath.read(doc, "$.key"))).size() == 1);
    }

    @Test
    public void jsonObjectToStringSuppressWarningOnCastToCollection_3_oe() {
        JSONObject jsonObject = new JSONObject();
        Collection<String> collection = new ArrayList<String>();
        collection.add("abc");
        // ArrayList will be added as an object
        jsonObject.put("key", collection);

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected abc", "abc".equals(jsonObject.query("/key/0")));
    }

    @Test
    public void valueToString_1_oe() {
        
        assertTrue("null valueToString() incorrect", "null".equals(JSONObject.valueToString(null)));
    }

    @Test
    public void valueToString_2_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        assertTrue("jsonstring valueToString() incorrect", "my string".equals(JSONObject.valueToString(jsonString)));
    }

    @Test
    public void valueToString_3_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        assertTrue("boolean valueToString() incorrect", "true".equals(JSONObject.valueToString(Boolean.TRUE)));
    }

    @Test
    public void valueToString_4_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        // removed other assertion
        assertTrue("non-numeric double", "null".equals(JSONObject.doubleToString(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void valueToString_5_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        String jsonObjectStr = 
            "{"+
                "\"key1\":\"val1\","+
                "\"key2\":\"val2\","+
                "\"key3\":\"val3\""+
             "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        assertTrue("jsonObject valueToString() incorrect", JSONObject.valueToString(jsonObject).equals(jsonObject.toString()));
    }

    @Test
    public void valueToString_6_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        String jsonObjectStr = 
            "{"+
                "\"key1\":\"val1\","+
                "\"key2\":\"val2\","+
                "\"key3\":\"val3\""+
             "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion
        String jsonArrayStr = 
            "[1,2,3]";
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        assertTrue("jsonArray valueToString() incorrect", JSONObject.valueToString(jsonArray).equals(jsonArray.toString()));
    }

    @Test
    public void valueToString_7_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        String jsonObjectStr = 
            "{"+
                "\"key1\":\"val1\","+
                "\"key2\":\"val2\","+
                "\"key3\":\"val3\""+
             "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion
        String jsonArrayStr = 
            "[1,2,3]";
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        // removed other assertion
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        assertTrue("map valueToString() incorrect", jsonObject.toString().equals(JSONObject.valueToString(map)));
    }

    @Test
    public void valueToString_8_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        String jsonObjectStr = 
            "{"+
                "\"key1\":\"val1\","+
                "\"key2\":\"val2\","+
                "\"key3\":\"val3\""+
             "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion
        String jsonArrayStr = 
            "[1,2,3]";
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        // removed other assertion
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        // removed other assertion
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        assertTrue("collection valueToString() expected: "+ jsonArray.toString()+ " actual: "+ JSONObject.valueToString(collection), jsonArray.toString().equals(JSONObject.valueToString(collection)));
    }

    @Test
    public void valueToString_9_oe() {
        
        // removed other assertion
        MyJsonString jsonString = new MyJsonString();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        String jsonObjectStr = 
            "{"+
                "\"key1\":\"val1\","+
                "\"key2\":\"val2\","+
                "\"key3\":\"val3\""+
             "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion
        String jsonArrayStr = 
            "[1,2,3]";
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        // removed other assertion
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        // removed other assertion
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        // removed other assertion
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        assertTrue("array valueToString() incorrect", jsonArray.toString().equals(JSONObject.valueToString(array)));
    }

    @Test
    public void valueToStringConfirmException_1_oe() {
        Map<Integer, String> myMap = new HashMap<Integer, String>();
        myMap.put(1,  "myValue");
        // this is the test, it should not throw an exception
        String str = JSONObject.valueToString(myMap);
        // confirm result, just in case
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(str);
        assertTrue("expected 1 top level item", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 1);
    }

    @Test
    public void valueToStringConfirmException_2_oe() {
        Map<Integer, String> myMap = new HashMap<Integer, String>();
        myMap.put(1,  "myValue");
        // this is the test, it should not throw an exception
        String str = JSONObject.valueToString(myMap);
        // confirm result, just in case
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(str);
        // removed other assertion
        assertTrue("expected myValue", "myValue".equals(JsonPath.read(doc, "$.1")));
    }

    @Test
    public void wrapObject_1_oe() {
        // wrap(null) returns NULL
        assertTrue("null wrap() incorrect", JSONObject.NULL == JSONObject.wrap(null));
    }

    @Test
    public void wrapObject_2_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        assertTrue("Integer wrap() incorrect", in == JSONObject.wrap(in));
    }

    @Test
    public void wrapObject_3_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        assertTrue("BigDecimal.ONE evaluates to ONE", bdWrap.equals(BigDecimal.ONE));
    }

    @Test
    public void wrapObject_4_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        assertTrue("JSONObject wrap() incorrect", jsonObject == JSONObject.wrap(jsonObject));
    }

    @Test
    public void wrapObject_5_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 3 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

    @Test
    public void wrapObject_6_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/0")));
    }

    @Test
    public void wrapObject_7_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonArray.query("/1")));
    }

    @Test
    public void wrapObject_8_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3", Integer.valueOf(3).equals(jsonArray.query("/2")));
    }

    @Test
    public void wrapObject_9_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 3 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

    @Test
    public void wrapObject_10_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/0")));
    }

    @Test
    public void wrapObject_11_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonArray.query("/1")));
    }

    @Test
    public void wrapObject_12_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3", Integer.valueOf(3).equals(jsonArray.query("/2")));
    }

    @Test
    public void wrapObject_13_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        assertTrue("expected 3 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

    @Test
    public void wrapObject_14_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/0")));
    }

    @Test
    public void wrapObject_15_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonArray.query("/1")));
    }

    @Test
    public void wrapObject_16_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected 3", Integer.valueOf(3).equals(jsonArray.query("/2")));
    }

    @Test
    public void wrapObject_17_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap map returns JSONObject
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        JSONObject mapJsonObject = (JSONObject) (JSONObject.wrap(map));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(mapJsonObject.toString());
        assertTrue("expected 3 top level items", ((Map<?,?>)(JsonPath.read(doc, "$"))).size() == 3);
    }

    @Test
    public void wrapObject_18_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap map returns JSONObject
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        JSONObject mapJsonObject = (JSONObject) (JSONObject.wrap(map));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(mapJsonObject.toString());
        // removed other assertion
        assertTrue("expected val1", "val1".equals(mapJsonObject.query("/key1")));
    }

    @Test
    public void wrapObject_19_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap map returns JSONObject
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        JSONObject mapJsonObject = (JSONObject) (JSONObject.wrap(map));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(mapJsonObject.toString());
        // removed other assertion
        // removed other assertion
        assertTrue("expected val2", "val2".equals(mapJsonObject.query("/key2")));
    }

    @Test
    public void wrapObject_20_oe() {
        // wrap(null) returns NULL
        // removed other assertion

        // wrap(Integer) returns Integer
        Integer in = new Integer(1);
        // removed other assertion

        /**
         * This test is to document the preferred behavior if BigDecimal is
         * supported. Previously bd returned as a string, since it
         * is recognized as being a Java package class. Now with explicit
         * support for big numbers, it remains a BigDecimal 
         */
        Object bdWrap = JSONObject.wrap(BigDecimal.ONE);
        // removed other assertion

        // wrap JSONObject returns JSONObject
        String jsonObjectStr = 
                "{"+
                    "\"key1\":\"val1\","+
                    "\"key2\":\"val2\","+
                    "\"key3\":\"val3\""+
                 "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        // removed other assertion

        // wrap collection returns JSONArray
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(new Integer(1));
        collection.add(new Integer(2));
        collection.add(new Integer(3));
        JSONArray jsonArray = (JSONArray) (JSONObject.wrap(collection));

        // validate JSON
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap Array returns JSONArray
        Integer[] array = { new Integer(1), new Integer(2), new Integer(3) };
        JSONArray integerArrayJsonArray = (JSONArray)(JSONObject.wrap(array));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(integerArrayJsonArray.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // wrap map returns JSONObject
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        JSONObject mapJsonObject = (JSONObject) (JSONObject.wrap(map));

        // validate JSON
        doc = Configuration.defaultConfiguration().jsonProvider().parse(mapJsonObject.toString());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("expected val3", "val3".equals(mapJsonObject.query("/key3")));
    }

    @Test
    public void jsonObjectParseControlCharacters_2_oe(){
        for(int i = 0;i<=0x001f;i++){
            final String charString = String.valueOf((char)i);
            final String source = "{\"key\":\""+charString+"\"}";
            try {
                JSONObject jo = new JSONObject(source);
                // removed other assertion
                Util.checkJSONObjectMaps(jo);
            } catch (JSONException ex) {
                assertTrue("Only \\0 (U+0000), \\n (U+000A), and \\r (U+000D) should cause an error. Instead "+charString+"("+i+") caused an error", i=='\0' || i=='\n' || i=='\r' );
    }
    }
    }

    @Test
    public void jsonObjectParsingErrors_4_oe() {
        try {
            // does not start with '{'
            String str = "abc";
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            // does not end with '}'
            String str = "{";
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "A JSONObject text must end with '}' at 1 [character 2 line 1]", e.getMessage());
    }
    }

    @Test
    public void jsonObjectParsingErrors_8_oe() {
        try {
            // does not start with '{'
            String str = "abc";
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            // does not end with '}'
            String str = "{";
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            // key with no ':'
            String str = "{\"myKey\" = true}";
            // removed other assertion
        } catch (JSONException e) { 
            // removed other assertion
        }
        try {
            // entries with no ',' separator
            String str = "{\"myKey\":true \"myOtherKey\":false}";
            // removed other assertion
        } catch (JSONException e) { 
            assertEquals("Expecting an exception message", "Expected a ',' or '}' at 15 [character 16 line 1]", e.getMessage());
    }
    }

    @Test
    public void jsonObjectPutOnceNull_1_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce(null, null);
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

    @Test
    public void jsonObjectPutOnceNull_2_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce(null, null);
        // removed other assertion
        jsonObject.putOnce("", null);
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

    @Test
    public void jsonObjectPutOnceNull_3_oe() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce(null, null);
        // removed other assertion
        jsonObject.putOnce("", null);
        // removed other assertion
        jsonObject.putOnce(null, "");
        assertTrue("jsonObject should be empty", jsonObject.isEmpty());
    }

    @Test
    public void jsonObjectOptDefault_1_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        assertTrue("optBigDecimal() should return default BigDecimal", BigDecimal.TEN.compareTo(jsonObject.optBigDecimal("myKey", BigDecimal.TEN))==0);
    }

    @Test
    public void jsonObjectOptDefault_2_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        assertTrue("optBigInteger() should return default BigInteger", BigInteger.TEN.compareTo(jsonObject.optBigInteger("myKey",BigInteger.TEN ))==0);
    }

    @Test
    public void jsonObjectOptDefault_3_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        assertTrue("optBoolean() should return default boolean", jsonObject.optBoolean("myKey", true));
    }

    @Test
    public void jsonObjectOptDefault_4_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optInt() should return default int", 42 == jsonObject.optInt("myKey", 42));
    }

    @Test
    public void jsonObjectOptDefault_5_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optEnum() should return default Enum", MyEnum.VAL1.equals(jsonObject.optEnum(MyEnum.class, "myKey", MyEnum.VAL1)));
    }

    @Test
    public void jsonObjectOptDefault_6_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optJSONArray() should return null ", null==jsonObject.optJSONArray("myKey"));
    }

    @Test
    public void jsonObjectOptDefault_7_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optJSONObject() should return default JSONObject ", jsonObject.optJSONObject("myKey", new JSONObject("{\"testKey\":\"testValue\"}")).getString("testKey").equals("testValue"));
    }

    @Test
    public void jsonObjectOptDefault_8_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optLong() should return default long", 42l == jsonObject.optLong("myKey", 42l));
    }

    @Test
    public void jsonObjectOptDefault_9_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optDouble() should return default double", 42.3d == jsonObject.optDouble("myKey", 42.3d));
    }

    @Test
    public void jsonObjectOptDefault_10_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("optFloat() should return default float", 42.3f == jsonObject.optFloat("myKey", 42.3f));
    }

    @Test
    public void jsonObjectOptDefault_11_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

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
        assertTrue("optNumber() should return default Number", 42l == jsonObject.optNumber("myKey", Long.valueOf(42)).longValue());
    }

    @Test
    public void jsonObjectOptDefault_12_oe() {

        String str = "{\"myKey\": \"myval\", \"hiKey\": null}";
        JSONObject jsonObject = new JSONObject(str);

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
        assertTrue("optString() should return default string", "hi".equals(jsonObject.optString("hiKey", "hi")));
    }

    @Test
    public void jsonObjectOptNoKey_1_oe() {

         JSONObject jsonObject = new JSONObject();
         
         assertNull(jsonObject.opt(null));
    }

    @Test
    public void jsonObjectOptNoKey_2_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         assertTrue("optBigDecimal() should return default BigDecimal", BigDecimal.TEN.compareTo(jsonObject.optBigDecimal("myKey", BigDecimal.TEN))==0);
    }

    @Test
    public void jsonObjectOptNoKey_3_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         assertTrue("optBigInteger() should return default BigInteger", BigInteger.TEN.compareTo(jsonObject.optBigInteger("myKey",BigInteger.TEN ))==0);
    }

    @Test
    public void jsonObjectOptNoKey_4_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         assertTrue("optBoolean() should return default boolean", jsonObject.optBoolean("myKey", true));
    }

    @Test
    public void jsonObjectOptNoKey_5_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         // removed other assertion
         assertTrue("optInt() should return default int", 42 == jsonObject.optInt("myKey", 42));
    }

    @Test
    public void jsonObjectOptNoKey_6_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         assertTrue("optEnum() should return default Enum", MyEnum.VAL1.equals(jsonObject.optEnum(MyEnum.class, "myKey", MyEnum.VAL1)));
    }

    @Test
    public void jsonObjectOptNoKey_7_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         assertTrue("optJSONArray() should return null ", null==jsonObject.optJSONArray("myKey"));
    }

    @Test
    public void jsonObjectOptNoKey_8_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         assertTrue("optJSONObject() should return default JSONObject ", jsonObject.optJSONObject("myKey", new JSONObject("{\"testKey\":\"testValue\"}")).getString("testKey").equals("testValue"));
    }

    @Test
    public void jsonObjectOptNoKey_9_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         assertTrue("optLong() should return default long", 42l == jsonObject.optLong("myKey", 42l));
    }

    @Test
    public void jsonObjectOptNoKey_10_oe() {

         JSONObject jsonObject = new JSONObject();
         
         // removed other assertion

         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         // removed other assertion
         assertTrue("optDouble() should return default double", 42.3d == jsonObject.optDouble("myKey", 42.3d));
    }

    @Test
    public void jsonObjectOptNoKey_11_oe() {

         JSONObject jsonObject = new JSONObject();
         
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
         assertTrue("optFloat() should return default float", 42.3f == jsonObject.optFloat("myKey", 42.3f));
    }

    @Test
    public void jsonObjectOptNoKey_12_oe() {

         JSONObject jsonObject = new JSONObject();
         
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
         assertTrue("optNumber() should return default Number", 42l == jsonObject.optNumber("myKey", Long.valueOf(42)).longValue());
    }

    @Test
    public void jsonObjectOptNoKey_13_oe() {

         JSONObject jsonObject = new JSONObject();
         
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
         assertTrue("optString() should return default string", "hi".equals(jsonObject.optString("hiKey", "hi")));
    }

    @Test
    public void jsonObjectOptStringConversion_1_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        assertTrue("unexpected optBoolean value",jo.optBoolean("true",false)==true);
    }

    @Test
    public void jsonObjectOptStringConversion_2_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        assertTrue("unexpected optBoolean value",jo.optBoolean("false",true)==false);
    }

    @Test
    public void jsonObjectOptStringConversion_3_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optInt value",jo.optInt("int",0)==123);
    }

    @Test
    public void jsonObjectOptStringConversion_4_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optLong value",jo.optLong("int",0)==123l);
    }

    @Test
    public void jsonObjectOptStringConversion_5_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optDouble value",jo.optDouble("int",0.0d)==123.0d);
    }

    @Test
    public void jsonObjectOptStringConversion_6_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optFloat value",jo.optFloat("int",0.0f)==123.0f);
    }

    @Test
    public void jsonObjectOptStringConversion_7_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optBigInteger value",jo.optBigInteger("int",BigInteger.ZERO).compareTo(new BigInteger("123"))==0);
    }

    @Test
    public void jsonObjectOptStringConversion_8_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optBigDecimal value",jo.optBigDecimal("int",BigDecimal.ZERO).compareTo(new BigDecimal("123"))==0);
    }

    @Test
    public void jsonObjectOptStringConversion_9_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optBigDecimal value",jo.optBigDecimal("int",BigDecimal.ZERO).compareTo(new BigDecimal("123"))==0);
    }

    @Test
    public void jsonObjectOptStringConversion_10_oe() {
        JSONObject jo = new JSONObject("{\"int\":\"123\",\"true\":\"true\",\"false\":\"false\"}");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("unexpected optNumber value",jo.optNumber("int",BigInteger.ZERO).longValue()==123l);
    }

    @Test
    public void jsonObjectOptCoercion_1_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        assertEquals(new BigDecimal("19007199254740993.35481234487103587486413587843213584"), jo.optBigDecimal("largeNumber",null));
    }

    @Test
    public void jsonObjectOptCoercion_2_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        assertEquals(new BigInteger("19007199254740993"), jo.optBigInteger("largeNumber",null));
    }

    @Test
    public void jsonObjectOptCoercion_3_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        assertEquals(1.9007199254740992E16, jo.optDouble("largeNumber"),0.0);
    }

    @Test
    public void jsonObjectOptCoercion_4_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1.90071995E16f, jo.optFloat("largeNumber"),0.0f);
    }

    @Test
    public void jsonObjectOptCoercion_5_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(19007199254740993l, jo.optLong("largeNumber"));
    }

    @Test
    public void jsonObjectOptCoercion_6_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1874919425, jo.optInt("largeNumber"));
    }

    @Test
    public void jsonObjectOptCoercion_7_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        assertEquals(new BigDecimal("19007199254740993.35481234487103587486413587843213584"), jo.optBigDecimal("largeNumberStr",null));
    }

    @Test
    public void jsonObjectOptCoercion_8_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        assertEquals(new BigInteger("19007199254740993"), jo.optBigInteger("largeNumberStr",null));
    }

    @Test
    public void jsonObjectOptCoercion_9_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        assertEquals(1.9007199254740992E16, jo.optDouble("largeNumberStr"),0.0);
    }

    @Test
    public void jsonObjectOptCoercion_10_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1.90071995E16f, jo.optFloat("largeNumberStr"),0.0f);
    }

    @Test
    public void jsonObjectOptCoercion_11_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(19007199254740993l, jo.optLong("largeNumberStr"));
    }

    @Test
    public void jsonObjectOptCoercion_12_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1874919425, jo.optInt("largeNumberStr"));
    }

    @Test
    public void jsonObjectOptCoercion_13_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // the integer portion of the actual value is larger than a double can hold.
        assertNotEquals((long)Double.parseDouble("19007199254740993.35481234487103587486413587843213584"), jo.optLong("largeNumber"));
    }

    @Test
    public void jsonObjectOptCoercion_14_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // the integer portion of the actual value is larger than a double can hold.
        // removed other assertion
        assertNotEquals((int)Double.parseDouble("19007199254740993.35481234487103587486413587843213584"), jo.optInt("largeNumber"));
    }

    @Test
    public void jsonObjectOptCoercion_15_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // the integer portion of the actual value is larger than a double can hold.
        // removed other assertion
        // removed other assertion
        assertNotEquals((long)Double.parseDouble("19007199254740993.35481234487103587486413587843213584"), jo.optLong("largeNumberStr"));
    }

    @Test
    public void jsonObjectOptCoercion_16_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // the integer portion of the actual value is larger than a double can hold.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals((int)Double.parseDouble("19007199254740993.35481234487103587486413587843213584"), jo.optInt("largeNumberStr"));
    }

    @Test
    public void jsonObjectOptCoercion_17_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // the integer portion of the actual value is larger than a double can hold.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(19007199254740992l, (long)Double.parseDouble("19007199254740993.35481234487103587486413587843213584"));
    }

    @Test
    public void jsonObjectOptCoercion_18_oe() {
        JSONObject jo = new JSONObject("{\"largeNumberStr\":\"19007199254740993.35481234487103587486413587843213584\"}");
        // currently the parser doesn't recognize BigDecimal, to we have to put it manually
        jo.put("largeNumber", new BigDecimal("19007199254740993.35481234487103587486413587843213584"));
        
        // Test type coercion from larger to smaller
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // conversion from a string
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // the integer portion of the actual value is larger than a double can hold.
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2147483647, (int)Double.parseDouble("19007199254740993.35481234487103587486413587843213584"));
    }

    @Test
    public void jsonObjectOptBigDecimal_1_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        assertEquals(new BigDecimal("123"),jo.optBigDecimal("int", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_2_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        assertEquals(new BigDecimal("654"),jo.optBigDecimal("long", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_3_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        assertEquals(new BigDecimal(1.234f),jo.optBigDecimal("float", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_4_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigDecimal(2.345d),jo.optBigDecimal("double", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_5_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigDecimal("1234"),jo.optBigDecimal("bigInteger", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_6_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigDecimal("1234.56789"),jo.optBigDecimal("bigDecimal", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_7_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(jo.optBigDecimal("nullVal", null));
    }

    @Test
    public void jsonObjectOptBigDecimal_8_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(jo.optBigDecimal("float", null),jo.getBigDecimal("float"));
    }

    @Test
    public void jsonObjectOptBigDecimal_9_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(jo.optBigDecimal("double", null),jo.getBigDecimal("double"));
    }

    @Test
    public void jsonObjectOptBigInteger_1_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        assertEquals(new BigInteger("123"),jo.optBigInteger("int", null));
    }

    @Test
    public void jsonObjectOptBigInteger_2_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        assertEquals(new BigInteger("654"),jo.optBigInteger("long", null));
    }

    @Test
    public void jsonObjectOptBigInteger_3_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("1"),jo.optBigInteger("float", null));
    }

    @Test
    public void jsonObjectOptBigInteger_4_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("2"),jo.optBigInteger("double", null));
    }

    @Test
    public void jsonObjectOptBigInteger_5_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("1234"),jo.optBigInteger("bigInteger", null));
    }

    @Test
    public void jsonObjectOptBigInteger_6_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(new BigInteger("1234"),jo.optBigInteger("bigDecimal", null));
    }

    @Test
    public void jsonObjectOptBigInteger_7_oe() {
        JSONObject jo = new JSONObject().put("int", 123).put("long", 654L)
                .put("float", 1.234f).put("double", 2.345d)
                .put("bigInteger", new BigInteger("1234"))
                .put("bigDecimal", new BigDecimal("1234.56789"))
                .put("nullVal", JSONObject.NULL);
        
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(jo.optBigDecimal("nullVal", null));
    }

    @Test
    public void jsonObjectputNull_1_oe() {

        // put null should remove the item.
        String str = "{\"myKey\": \"myval\"}";
        JSONObject jsonObjectRemove = new JSONObject(str);
        jsonObjectRemove.remove("myKey");
        assertTrue("jsonObject should be empty", jsonObjectRemove.isEmpty());
    }

    @Test
    public void jsonObjectputNull_2_oe() {

        // put null should remove the item.
        String str = "{\"myKey\": \"myval\"}";
        JSONObject jsonObjectRemove = new JSONObject(str);
        jsonObjectRemove.remove("myKey");
        // removed other assertion

        JSONObject jsonObjectPutNull = new JSONObject(str);
        jsonObjectPutNull.put("myKey", (Object) null);
        assertTrue("jsonObject should be empty", jsonObjectPutNull.isEmpty());
    }

    @Test
    public void jsonObjectQuote_1_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped quotes, found "+quotedStr, "\"\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_2_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped quotes, found "+quotedStr, "\"\\\"\\\"\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_3_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "</";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped frontslash, found "+quotedStr, "\"<\\/\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_4_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "</";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\bC";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped backspace, found "+quotedStr, "\"AB\\bC\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_5_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "</";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\bC";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "ABC\n";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped newline, found "+quotedStr, "\"ABC\\n\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_6_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "</";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\bC";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "ABC\n";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\fC";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped formfeed, found "+quotedStr, "\"AB\\fC\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_7_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "</";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\bC";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "ABC\n";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\fC";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\r";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped return, found "+quotedStr, "\"\\r\"".equals(quotedStr));
    }

    @Test
    public void jsonObjectQuote_8_oe() {
        String str;
        str = "";
        String quotedStr;
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\"\"";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "</";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\bC";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "ABC\n";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "AB\fC";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\r";
        quotedStr = JSONObject.quote(str);
        // removed other assertion
        str = "\u1234\u0088";
        quotedStr = JSONObject.quote(str);
        assertTrue("quote() expected escaped unicode, found "+quotedStr, "\"\u1234\\u0088\"".equals(quotedStr));
    }

    @Test
    public void stringToValue_1_oe() {
        String str = "";
        String valueStr = (String)(JSONObject.stringToValue(str));
        assertTrue("stringToValue() expected empty String, found "+valueStr, "".equals(valueStr));
    }

    @Test
    public void toJSONArray_1_oe() {
        assertTrue("toJSONArray() with null names should be null", null == new JSONObject().toJSONArray(null));
    }

    @Test
    public void testJSONWriterException_2_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            assertEquals("Unable to write JSONObject value for key: someKey", e.getMessage());
    }
    }

    @Test
    public void testJSONWriterException_3_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            fail("Expected JSONException");
    }
    }

    @Test
    public void testJSONWriterException_5_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }

        //test multiElement
        jsonObject.put("somethingElse", "a value");
        
        writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            assertEquals("Unable to write JSONObject value for key: someKey", e.getMessage());
    }
    }

    @Test
    public void testJSONWriterException_6_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }

        //test multiElement
        jsonObject.put("somethingElse", "a value");
        
        writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            fail("Expected JSONException");
    }
    }

    @Test
    public void testJSONWriterException_8_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }

        //test multiElement
        jsonObject.put("somethingElse", "a value");
        
        writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }
        
        // test a more complex object
        writer = new StringWriter();
        try {
            new JSONObject()
                .put("somethingElse", "a value")
                .put("someKey", new JSONArray()
                        .put(new JSONObject().put("key1", new BrokenToString())))
                .write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            assertEquals("Unable to write JSONObject value for key: someKey", e.getMessage());
    }
    }

    @Test
    public void testJSONWriterException_9_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }

        //test multiElement
        jsonObject.put("somethingElse", "a value");
        
        writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }
        
        // test a more complex object
        writer = new StringWriter();
        try {
            new JSONObject()
                .put("somethingElse", "a value")
                .put("someKey", new JSONArray()
                        .put(new JSONObject().put("key1", new BrokenToString())))
                .write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            fail("Expected JSONException");
    }
    }

    @Test
    public void testJSONWriterException_11_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }

        //test multiElement
        jsonObject.put("somethingElse", "a value");
        
        writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }
        
        // test a more complex object
        writer = new StringWriter();
        try {
            new JSONObject()
                .put("somethingElse", "a value")
                .put("someKey", new JSONArray()
                        .put(new JSONObject().put("key1", new BrokenToString())))
                .write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }
       
        // test a more slightly complex object
        writer = new StringWriter();
        try {
            new JSONObject()
                .put("somethingElse", "a value")
                .put("someKey", new JSONArray()
                        .put(new JSONObject().put("key1", new BrokenToString()))
                        .put(12345)
                 )
                .write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            assertEquals("Unable to write JSONObject value for key: someKey", e.getMessage());
    }
    }

    @Test
    public void testJSONWriterException_12_oe() {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("someKey",new BrokenToString());

        // test single element JSONObject
        StringWriter writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }

        //test multiElement
        jsonObject.put("somethingElse", "a value");
        
        writer = new StringWriter();
        try {
            jsonObject.write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }
        
        // test a more complex object
        writer = new StringWriter();
        try {
            new JSONObject()
                .put("somethingElse", "a value")
                .put("someKey", new JSONArray()
                        .put(new JSONObject().put("key1", new BrokenToString())))
                .write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            // removed other assertion
        } finally {
            try {
                writer.close();
            } catch (Exception e) {}
        }
       
        // test a more slightly complex object
        writer = new StringWriter();
        try {
            new JSONObject()
                .put("somethingElse", "a value")
                .put("someKey", new JSONArray()
                        .put(new JSONObject().put("key1", new BrokenToString()))
                        .put(12345)
                 )
                .write(writer).toString();
            // removed other assertion
        } catch (JSONException e) {
            // removed other assertion
        } catch(Exception e) {
            fail("Expected JSONException");
    }
    }

    @Test
    public void equals_1_oe() {
        String str = "{\"key\":\"value\"}";
        JSONObject aJsonObject = new JSONObject(str);
        assertTrue("Same JSONObject should be equal to itself", aJsonObject.equals(aJsonObject));
    }

    @Test
    public void jsonObjectNullOperations_1_oe() {
        /**
         * The Javadoc for JSONObject.NULL states:
         *      "JSONObject.NULL is equivalent to the value that JavaScript calls null,
         *      whilst Java's null is equivalent to the value that JavaScript calls
         *      undefined."
         * 
         * Standard ECMA-262 6th Edition / June 2015 (included to help explain the javadoc):
         *      undefined value: primitive value used when a variable has not been assigned a value
         *      Undefined type:  type whose sole value is the undefined value
         *      null value:      primitive value that represents the intentional absence of any object value
         *      Null type:       type whose sole value is the null value
         * Java SE8 language spec (included to help explain the javadoc):
         *      The Kinds of Types and Values ...
         *      There is also a special null type, the type of the expression null, which has no name.
         *      Because the null type has no name, it is impossible to declare a variable of the null 
         *      type or to cast to the null type. The null reference is the only possible value of an 
         *      expression of null type. The null reference can always be assigned or cast to any reference type.
         *      In practice, the programmer can ignore the null type and just pretend that null is merely 
         *      a special literal that can be of any reference type.
         * Extensible Markup Language (XML) 1.0 Fifth Edition / 26 November 2008
         *      No mention of null
         * ECMA-404 1st Edition / October 2013:
         *      JSON Text  ...
         *      These are three literal name tokens: ...
         *      null 
         * 
         * There seems to be no best practice to follow, it's all about what we
         * want the code to do.
         */

        // add JSONObject.NULL then convert to string in the manner of XML.toString() 
        JSONObject jsonObjectJONull = new JSONObject();
        Object obj = JSONObject.NULL;
        jsonObjectJONull.put("key", obj);
        Object value = jsonObjectJONull.opt("key");
        assertTrue("opt() JSONObject.NULL should find JSONObject.NULL", obj.equals(value));
    }

    @Test
    public void jsonObjectNullOperations_2_oe() {
        /**
         * The Javadoc for JSONObject.NULL states:
         *      "JSONObject.NULL is equivalent to the value that JavaScript calls null,
         *      whilst Java's null is equivalent to the value that JavaScript calls
         *      undefined."
         * 
         * Standard ECMA-262 6th Edition / June 2015 (included to help explain the javadoc):
         *      undefined value: primitive value used when a variable has not been assigned a value
         *      Undefined type:  type whose sole value is the undefined value
         *      null value:      primitive value that represents the intentional absence of any object value
         *      Null type:       type whose sole value is the null value
         * Java SE8 language spec (included to help explain the javadoc):
         *      The Kinds of Types and Values ...
         *      There is also a special null type, the type of the expression null, which has no name.
         *      Because the null type has no name, it is impossible to declare a variable of the null 
         *      type or to cast to the null type. The null reference is the only possible value of an 
         *      expression of null type. The null reference can always be assigned or cast to any reference type.
         *      In practice, the programmer can ignore the null type and just pretend that null is merely 
         *      a special literal that can be of any reference type.
         * Extensible Markup Language (XML) 1.0 Fifth Edition / 26 November 2008
         *      No mention of null
         * ECMA-404 1st Edition / October 2013:
         *      JSON Text  ...
         *      These are three literal name tokens: ...
         *      null 
         * 
         * There seems to be no best practice to follow, it's all about what we
         * want the code to do.
         */

        // add JSONObject.NULL then convert to string in the manner of XML.toString() 
        JSONObject jsonObjectJONull = new JSONObject();
        Object obj = JSONObject.NULL;
        jsonObjectJONull.put("key", obj);
        Object value = jsonObjectJONull.opt("key");
        // removed other assertion
        value = jsonObjectJONull.get("key");
        assertTrue("get() JSONObject.NULL should find JSONObject.NULL", obj.equals(value));
    }

    @Test
    public void jsonObjectNullOperations_3_oe() {
        /**
         * The Javadoc for JSONObject.NULL states:
         *      "JSONObject.NULL is equivalent to the value that JavaScript calls null,
         *      whilst Java's null is equivalent to the value that JavaScript calls
         *      undefined."
         * 
         * Standard ECMA-262 6th Edition / June 2015 (included to help explain the javadoc):
         *      undefined value: primitive value used when a variable has not been assigned a value
         *      Undefined type:  type whose sole value is the undefined value
         *      null value:      primitive value that represents the intentional absence of any object value
         *      Null type:       type whose sole value is the null value
         * Java SE8 language spec (included to help explain the javadoc):
         *      The Kinds of Types and Values ...
         *      There is also a special null type, the type of the expression null, which has no name.
         *      Because the null type has no name, it is impossible to declare a variable of the null 
         *      type or to cast to the null type. The null reference is the only possible value of an 
         *      expression of null type. The null reference can always be assigned or cast to any reference type.
         *      In practice, the programmer can ignore the null type and just pretend that null is merely 
         *      a special literal that can be of any reference type.
         * Extensible Markup Language (XML) 1.0 Fifth Edition / 26 November 2008
         *      No mention of null
         * ECMA-404 1st Edition / October 2013:
         *      JSON Text  ...
         *      These are three literal name tokens: ...
         *      null 
         * 
         * There seems to be no best practice to follow, it's all about what we
         * want the code to do.
         */

        // add JSONObject.NULL then convert to string in the manner of XML.toString() 
        JSONObject jsonObjectJONull = new JSONObject();
        Object obj = JSONObject.NULL;
        jsonObjectJONull.put("key", obj);
        Object value = jsonObjectJONull.opt("key");
        // removed other assertion
        value = jsonObjectJONull.get("key");
        // removed other assertion
        if (value == null) {
            value = "";
        }
        String string = value instanceof String ? (String)value : null;
        assertTrue("XML toString() should convert JSONObject.NULL to null", string == null);
    }

    @Test
    public void jsonObjectNullOperations_4_oe() {
        /**
         * The Javadoc for JSONObject.NULL states:
         *      "JSONObject.NULL is equivalent to the value that JavaScript calls null,
         *      whilst Java's null is equivalent to the value that JavaScript calls
         *      undefined."
         * 
         * Standard ECMA-262 6th Edition / June 2015 (included to help explain the javadoc):
         *      undefined value: primitive value used when a variable has not been assigned a value
         *      Undefined type:  type whose sole value is the undefined value
         *      null value:      primitive value that represents the intentional absence of any object value
         *      Null type:       type whose sole value is the null value
         * Java SE8 language spec (included to help explain the javadoc):
         *      The Kinds of Types and Values ...
         *      There is also a special null type, the type of the expression null, which has no name.
         *      Because the null type has no name, it is impossible to declare a variable of the null 
         *      type or to cast to the null type. The null reference is the only possible value of an 
         *      expression of null type. The null reference can always be assigned or cast to any reference type.
         *      In practice, the programmer can ignore the null type and just pretend that null is merely 
         *      a special literal that can be of any reference type.
         * Extensible Markup Language (XML) 1.0 Fifth Edition / 26 November 2008
         *      No mention of null
         * ECMA-404 1st Edition / October 2013:
         *      JSON Text  ...
         *      These are three literal name tokens: ...
         *      null 
         * 
         * There seems to be no best practice to follow, it's all about what we
         * want the code to do.
         */

        // add JSONObject.NULL then convert to string in the manner of XML.toString() 
        JSONObject jsonObjectJONull = new JSONObject();
        Object obj = JSONObject.NULL;
        jsonObjectJONull.put("key", obj);
        Object value = jsonObjectJONull.opt("key");
        // removed other assertion
        value = jsonObjectJONull.get("key");
        // removed other assertion
        if (value == null) {
            value = "";
        }
        String string = value instanceof String ? (String)value : null;
        // removed other assertion

        // now try it with null
        JSONObject jsonObjectNull = new JSONObject();
        obj = null;
        jsonObjectNull.put("key", obj);
        value = jsonObjectNull.opt("key");
        assertNull("opt() null should find null", value);
    }

    @Test
    public void jsonObjectNullOperations_6_oe() {
        /**
         * The Javadoc for JSONObject.NULL states:
         *      "JSONObject.NULL is equivalent to the value that JavaScript calls null,
         *      whilst Java's null is equivalent to the value that JavaScript calls
         *      undefined."
         * 
         * Standard ECMA-262 6th Edition / June 2015 (included to help explain the javadoc):
         *      undefined value: primitive value used when a variable has not been assigned a value
         *      Undefined type:  type whose sole value is the undefined value
         *      null value:      primitive value that represents the intentional absence of any object value
         *      Null type:       type whose sole value is the null value
         * Java SE8 language spec (included to help explain the javadoc):
         *      The Kinds of Types and Values ...
         *      There is also a special null type, the type of the expression null, which has no name.
         *      Because the null type has no name, it is impossible to declare a variable of the null 
         *      type or to cast to the null type. The null reference is the only possible value of an 
         *      expression of null type. The null reference can always be assigned or cast to any reference type.
         *      In practice, the programmer can ignore the null type and just pretend that null is merely 
         *      a special literal that can be of any reference type.
         * Extensible Markup Language (XML) 1.0 Fifth Edition / 26 November 2008
         *      No mention of null
         * ECMA-404 1st Edition / October 2013:
         *      JSON Text  ...
         *      These are three literal name tokens: ...
         *      null 
         * 
         * There seems to be no best practice to follow, it's all about what we
         * want the code to do.
         */

        // add JSONObject.NULL then convert to string in the manner of XML.toString() 
        JSONObject jsonObjectJONull = new JSONObject();
        Object obj = JSONObject.NULL;
        jsonObjectJONull.put("key", obj);
        Object value = jsonObjectJONull.opt("key");
        // removed other assertion
        value = jsonObjectJONull.get("key");
        // removed other assertion
        if (value == null) {
            value = "";
        }
        String string = value instanceof String ? (String)value : null;
        // removed other assertion

        // now try it with null
        JSONObject jsonObjectNull = new JSONObject();
        obj = null;
        jsonObjectNull.put("key", obj);
        value = jsonObjectNull.opt("key");
        // removed other assertion
        // what is this trying to do? It appears to test absolutely nothing...
//        if (value == null) {
//            value = "";
//        }
//        string = value instanceof String ? (String)value : null;
//        assertTrue("should convert null to empty string", "".equals(string));
        try {
            value = jsonObjectNull.get("key");
            // removed other assertion
        } catch (Exception ignored) {}

        /**
         * XML.toString() then goes on to do something with the value
         * if the key val is "content", then value.toString() will be 
         * called. This will evaluate to "null" for JSONObject.NULL,
         * and the empty string for null.
         * But if the key is anything else, then JSONObject.NULL will be emitted
         * as <key>null</key> and null will be emitted as ""
         */
        String sJONull = XML.toString(jsonObjectJONull);
        assertTrue("JSONObject.NULL should emit a null value", "<key>null</key>".equals(sJONull));
    }

    @Test
    public void jsonObjectNullOperations_7_oe() {
        /**
         * The Javadoc for JSONObject.NULL states:
         *      "JSONObject.NULL is equivalent to the value that JavaScript calls null,
         *      whilst Java's null is equivalent to the value that JavaScript calls
         *      undefined."
         * 
         * Standard ECMA-262 6th Edition / June 2015 (included to help explain the javadoc):
         *      undefined value: primitive value used when a variable has not been assigned a value
         *      Undefined type:  type whose sole value is the undefined value
         *      null value:      primitive value that represents the intentional absence of any object value
         *      Null type:       type whose sole value is the null value
         * Java SE8 language spec (included to help explain the javadoc):
         *      The Kinds of Types and Values ...
         *      There is also a special null type, the type of the expression null, which has no name.
         *      Because the null type has no name, it is impossible to declare a variable of the null 
         *      type or to cast to the null type. The null reference is the only possible value of an 
         *      expression of null type. The null reference can always be assigned or cast to any reference type.
         *      In practice, the programmer can ignore the null type and just pretend that null is merely 
         *      a special literal that can be of any reference type.
         * Extensible Markup Language (XML) 1.0 Fifth Edition / 26 November 2008
         *      No mention of null
         * ECMA-404 1st Edition / October 2013:
         *      JSON Text  ...
         *      These are three literal name tokens: ...
         *      null 
         * 
         * There seems to be no best practice to follow, it's all about what we
         * want the code to do.
         */

        // add JSONObject.NULL then convert to string in the manner of XML.toString() 
        JSONObject jsonObjectJONull = new JSONObject();
        Object obj = JSONObject.NULL;
        jsonObjectJONull.put("key", obj);
        Object value = jsonObjectJONull.opt("key");
        // removed other assertion
        value = jsonObjectJONull.get("key");
        // removed other assertion
        if (value == null) {
            value = "";
        }
        String string = value instanceof String ? (String)value : null;
        // removed other assertion

        // now try it with null
        JSONObject jsonObjectNull = new JSONObject();
        obj = null;
        jsonObjectNull.put("key", obj);
        value = jsonObjectNull.opt("key");
        // removed other assertion
        // what is this trying to do? It appears to test absolutely nothing...
//        if (value == null) {
//            value = "";
//        }
//        string = value instanceof String ? (String)value : null;
//        assertTrue("should convert null to empty string", "".equals(string));
        try {
            value = jsonObjectNull.get("key");
            // removed other assertion
        } catch (Exception ignored) {}

        /**
         * XML.toString() then goes on to do something with the value
         * if the key val is "content", then value.toString() will be 
         * called. This will evaluate to "null" for JSONObject.NULL,
         * and the empty string for null.
         * But if the key is anything else, then JSONObject.NULL will be emitted
         * as <key>null</key> and null will be emitted as ""
         */
        String sJONull = XML.toString(jsonObjectJONull);
        // removed other assertion
        String sNull = XML.toString(jsonObjectNull);
        assertTrue("null should emit an empty string", "".equals(sNull));
    }

    @Test
    public void optQueryWithNoResult_1_oe() {
        assertNull(new JSONObject().optQuery("/a/b"));
    }

    @Test(expected = JSONException.class)
    public void invalidEscapeSequence_1_oe() {
      String json = "{ \"\\url\": \"value\" }";
      assertNull("Expected an exception",new JSONObject(json));
    }

    @Test
    public void toMap_1_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        assertTrue("Map should not be null", map != null);
    }

    @Test
    public void toMap_2_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        assertTrue("Map should have 3 elements", map.size() == 3);
    }

    @Test
    public void toMap_3_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        assertTrue("key1 should not be null", key1List != null);
    }

    @Test
    public void toMap_4_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        assertTrue("key1 list should have 3 elements", key1List.size() == 3);
    }

    @Test
    public void toMap_5_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        assertTrue("key1 value 1 should be 1", key1List.get(0).equals(Integer.valueOf(1)));
    }

    @Test
    public void toMap_6_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("key1 value 2 should be 2", key1List.get(1).equals(Integer.valueOf(2)));
    }

    @Test
    public void toMap_7_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        assertTrue("Map should not be null", key1Value3Map != null);
    }

    @Test
    public void toMap_8_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        assertTrue("Map should have 1 element", key1Value3Map.size() == 1);
    }

    @Test
    public void toMap_9_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        assertTrue("Map key3 should be true", key1Value3Map.get("key3").equals(Boolean.TRUE));
    }

    @Test
    public void toMap_10_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        assertTrue("key2 should not be null", key2Map != null);
    }

    @Test
    public void toMap_11_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        assertTrue("key2 map should have 3 elements", key2Map.size() == 3);
    }

    @Test
    public void toMap_12_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        assertTrue("key2 map key 1 should be val1", key2Map.get("key1").equals("val1"));
    }

    @Test
    public void toMap_13_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("key2 map key 3 should be 42", key2Map.get("key3").equals(Integer.valueOf(42)));
    }

    @Test
    public void toMap_14_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        assertTrue("key2 map key 2 should not be null", key2Val2Map != null);
    }

    @Test
    public void toMap_15_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        assertTrue("key2 map key 2 should have an entry", key2Val2Map.containsKey("key2"));
    }

    @Test
    public void toMap_16_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        assertTrue("key2 map key 2 value should be null", key2Val2Map.get("key2") == null);
    }

    @Test
    public void toMap_17_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        assertTrue("key3 should not be null", key3List != null);
    }

    @Test
    public void toMap_18_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        assertTrue("key3 list should have 3 elements", key3List.size() == 2);
    }

    @Test
    public void toMap_19_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        assertTrue("key3 list val 1 should not be null", key3Val1List != null);
    }

    @Test
    public void toMap_20_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        assertTrue("key3 list val 1 should have 2 elements", key3Val1List.size() == 2);
    }

    @Test
    public void toMap_21_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        assertTrue("key3 list val 1 list element 1 should be value1", key3Val1List.get(0).equals("value1"));
    }

    @Test
    public void toMap_22_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("key3 list val 1 list element 2 should be 2.1", key3Val1List.get(1).equals(new BigDecimal("2.1")));
    }

    @Test
    public void toMap_23_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3Val2List = (List<?>)key3List.get(1);
        assertTrue("key3 list val 2 should not be null", key3Val2List != null);
    }

    @Test
    public void toMap_24_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3Val2List = (List<?>)key3List.get(1);
        // removed other assertion
        assertTrue("key3 list val 2 should have 1 element", key3Val2List.size() == 1);
    }

    @Test
    public void toMap_25_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3Val2List = (List<?>)key3List.get(1);
        // removed other assertion
        // removed other assertion
        assertTrue("key3 list val 2 list element 1 should be null", key3Val2List.get(0) == null);
    }

    @Test
    public void toMap_26_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3Val2List = (List<?>)key3List.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Assert that toMap() is a deep copy
        jsonObject.getJSONArray("key3").getJSONArray(0).put(0, "still value 1");
        assertTrue("key3 list val 1 list element 1 should be value1", key3Val1List.get(0).equals("value1"));
    }

    @Test
    public void toMap_27_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3Val2List = (List<?>)key3List.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Assert that toMap() is a deep copy
        jsonObject.getJSONArray("key3").getJSONArray(0).put(0, "still value 1");
        // removed other assertion

        // assert that the new map is mutable
        assertTrue("Removing a key should succeed", map.remove("key3") != null);
    }

    @Test
    public void toMap_28_oe() {
        String jsonObjectStr =
                "{" +
                "\"key1\":" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                "\"key2\":" +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42" +
                    "}," +
                "\"key3\":" +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        Map<?,?> map = jsonObject.toMap();

        // removed other assertion
        // removed other assertion

        List<?> key1List = (List<?>)map.get("key1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key1Value3Map = (Map<?,?>)key1List.get(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Map = (Map<?,?>)map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        Map<?,?> key2Val2Map = (Map<?,?>)key2Map.get("key2");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3List = (List<?>)map.get("key3");
        // removed other assertion
        // removed other assertion

        List<?> key3Val1List = (List<?>)key3List.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        List<?> key3Val2List = (List<?>)key3List.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Assert that toMap() is a deep copy
        jsonObject.getJSONArray("key3").getJSONArray(0).put(0, "still value 1");
        // removed other assertion

        // assert that the new map is mutable
        // removed other assertion
        assertTrue("Map should have 2 elements", map.size() == 2);
    }

    @Test
    public void testSingletonBean_1_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        assertEquals(jo.keySet().toString(), 1, jo.length());
    }

    @Test
    public void testSingletonBean_2_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        assertEquals(0, jo.get("someInt"));
    }

    @Test
    public void testSingletonBean_3_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(null, jo.opt("someString"));
    }

    @Test
    public void testSingletonBean_4_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        Singleton.getInstance().setSomeInt(42);
        Singleton.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(Singleton.getInstance());
        assertEquals(2, jo2.length());
    }

    @Test
    public void testSingletonBean_5_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        Singleton.getInstance().setSomeInt(42);
        Singleton.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(Singleton.getInstance());
        // removed other assertion
        assertEquals(42, jo2.get("someInt"));
    }

    @Test
    public void testSingletonBean_6_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        Singleton.getInstance().setSomeInt(42);
        Singleton.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals("Something", jo2.get("someString"));
    }

    @Test
    public void testSingletonBean_7_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        Singleton.getInstance().setSomeInt(42);
        Singleton.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // ensure our original jo hasn't changed.
        assertEquals(0, jo.get("someInt"));
    }

    @Test
    public void testSingletonBean_8_oe() {
        final JSONObject jo = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        Singleton.getInstance().setSomeInt(42);
        Singleton.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(Singleton.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // ensure our original jo hasn't changed.
        // removed other assertion
        assertEquals(null, jo.opt("someString"));
    }

    @Test
    public void testSingletonEnumBean_1_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        assertEquals(jo.keySet().toString(), 1, jo.length());
    }

    @Test
    public void testSingletonEnumBean_2_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        assertEquals(0, jo.get("someInt"));
    }

    @Test
    public void testSingletonEnumBean_3_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals(null, jo.opt("someString"));
    }

    @Test
    public void testSingletonEnumBean_4_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        SingletonEnum.getInstance().setSomeInt(42);
        SingletonEnum.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(SingletonEnum.getInstance());
        assertEquals(2, jo2.length());
    }

    @Test
    public void testSingletonEnumBean_5_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        SingletonEnum.getInstance().setSomeInt(42);
        SingletonEnum.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        assertEquals(42, jo2.get("someInt"));
    }

    @Test
    public void testSingletonEnumBean_6_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        SingletonEnum.getInstance().setSomeInt(42);
        SingletonEnum.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        assertEquals("Something", jo2.get("someString"));
    }

    @Test
    public void testSingletonEnumBean_7_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        SingletonEnum.getInstance().setSomeInt(42);
        SingletonEnum.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // ensure our original jo hasn't changed.
        assertEquals(0, jo.get("someInt"));
    }

    @Test
    public void testSingletonEnumBean_8_oe() {
        final JSONObject jo = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // Update the singleton values
        SingletonEnum.getInstance().setSomeInt(42);
        SingletonEnum.getInstance().setSomeString("Something");
        final JSONObject jo2 = new JSONObject(SingletonEnum.getInstance());
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // ensure our original jo hasn't changed.
        // removed other assertion
        assertEquals(null, jo.opt("someString"));
    }

    @Test
    public void testGenericBean_1_oe() {
        GenericBean<Integer> bean = new GenericBean(42);
        final JSONObject jo = new JSONObject(bean);
        assertEquals(jo.keySet().toString(), 8, jo.length());
    }

    @Test
    public void testGenericBean_2_oe() {
        GenericBean<Integer> bean = new GenericBean(42);
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        assertEquals(42, jo.get("genericValue"));
    }

    @Test
    public void testGenericBean_3_oe() {
        GenericBean<Integer> bean = new GenericBean(42);
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        // removed other assertion
        assertEquals("Expected the getter to only be called once", 1, bean.genericGetCounter);
    }

    @Test
    public void testGenericBean_4_oe() {
        GenericBean<Integer> bean = new GenericBean(42);
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, bean.genericSetCounter);
    }

    @Test
    public void testGenericIntBean_1_oe() {
        GenericBeanInt bean = new GenericBeanInt(42);
        final JSONObject jo = new JSONObject(bean);
        assertEquals(jo.keySet().toString(), 10, jo.length());
    }

    @Test
    public void testGenericIntBean_2_oe() {
        GenericBeanInt bean = new GenericBeanInt(42);
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        assertEquals(42, jo.get("genericValue"));
    }

    @Test
    public void testGenericIntBean_3_oe() {
        GenericBeanInt bean = new GenericBeanInt(42);
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        // removed other assertion
        assertEquals("Expected the getter to only be called once", 1, bean.genericGetCounter);
    }

    @Test
    public void testGenericIntBean_4_oe() {
        GenericBeanInt bean = new GenericBeanInt(42);
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, bean.genericSetCounter);
    }

    @Test
    public void testWierdListBean_1_oe() {
        @SuppressWarnings("boxing")
        WeirdList bean = new WeirdList(42, 43, 44);
        final JSONObject jo = new JSONObject(bean);
        // get() should have a key of 0 length
        // get(int) should be ignored base on parameter count
        // getInt(int) should also be ignored based on parameter count
        // add(Integer) should be ignore as it doesn't start with get/is and also has a parameter
        // getALL should be mapped
        assertEquals("Expected 1 key to be mapped. Instead found: "+jo.keySet().toString(), 1, jo.length());
    }

    @Test
    public void testWierdListBean_2_oe() {
        @SuppressWarnings("boxing")
        WeirdList bean = new WeirdList(42, 43, 44);
        final JSONObject jo = new JSONObject(bean);
        // get() should have a key of 0 length
        // get(int) should be ignored base on parameter count
        // getInt(int) should also be ignored based on parameter count
        // add(Integer) should be ignore as it doesn't start with get/is and also has a parameter
        // getALL should be mapped
        // removed other assertion
        assertNotNull(jo.get("ALL"));
    }

    public void testObjectToBigDecimal_1_oe() {  
        double value = 1412078745.01074;  
        Reader reader = new StringReader("[{\"value\": " + value + "}]");
        JSONTokener tokener = new JSONTokener(reader);
        JSONArray array = new JSONArray(tokener);
        JSONObject jsonObject = array.getJSONObject(0);

        BigDecimal current = jsonObject.getBigDecimal("value");
        BigDecimal wantedValue = BigDecimal.valueOf(value);

        assertEquals(current, wantedValue);
    }

    @Test
    public void testExceptionalBean_1_oe() {
        ExceptionalBean bean = new ExceptionalBean();
        final JSONObject jo = new JSONObject(bean);
        assertEquals("Expected 1 key to be mapped. Instead found: "+jo.keySet().toString(), 1, jo.length());
    }

    @Test
    public void testExceptionalBean_2_oe() {
        ExceptionalBean bean = new ExceptionalBean();
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        assertTrue(jo.get("closeable") instanceof JSONObject);
    }

    @Test
    public void testExceptionalBean_3_oe() {
        ExceptionalBean bean = new ExceptionalBean();
        final JSONObject jo = new JSONObject(bean);
        // removed other assertion
        // removed other assertion
        assertTrue(jo.getJSONObject("closeable").has("string"));
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullBoolean_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, false);
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullCollection_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, Collections.emptySet());
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullDouble_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, 0.0d);
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullFloat_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, 0.0f);
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullInt_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, 0);
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullLong_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, 0L);
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullMap_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, Collections.emptyMap());
        fail("Expected an exception");
    }

    @Test(expected=NullPointerException.class)
    public void testPutNullObject_1_oe() {
        // null put key 
        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put(null, new Object());
        fail("Expected an exception");
    }

    @Test(expected=JSONException.class)
    public void testSelfRecursiveObject_1_oe() {
        // A -> A ...
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        ObjA.setRef(ObjA);
        new JSONObject(ObjA);
        fail("Expected an exception");
    }

    @Test(expected=JSONException.class)
    public void testLongSelfRecursiveObject_1_oe() {
        // B -> A -> A ...
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        RecursiveBean ObjB = new RecursiveBean("ObjB");
        ObjB.setRef(ObjA);
        ObjA.setRef(ObjA);
        new JSONObject(ObjB);
        fail("Expected an exception");
    }

    @Test(expected=JSONException.class)
    public void testSimpleRecursiveObject_1_oe() {
        // B -> A -> B ...
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        RecursiveBean ObjB = new RecursiveBean("ObjB");
        ObjB.setRef(ObjA);
        ObjA.setRef(ObjB);
        new JSONObject(ObjA);
        fail("Expected an exception");
    }

    @Test(expected=JSONException.class)
    public void testLongRecursiveObject_1_oe() {
        // D -> C -> B -> A -> D ...
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        RecursiveBean ObjB = new RecursiveBean("ObjB");
        RecursiveBean ObjC = new RecursiveBean("ObjC");
        RecursiveBean ObjD = new RecursiveBean("ObjD");
        ObjC.setRef(ObjB);
        ObjB.setRef(ObjA);
        ObjD.setRef(ObjC);
        ObjA.setRef(ObjD);
        new JSONObject(ObjB);
        fail("Expected an exception");
    }

    @Test(expected=JSONException.class)
    public void testRepeatObjectRecursive_1_oe() {
        // C -> B -> A -> D -> C ...
        //        -> D -> C ...
        RecursiveBean ObjA = new RecursiveBean("ObjA");
        RecursiveBean ObjB = new RecursiveBean("ObjB");
        RecursiveBean ObjC = new RecursiveBean("ObjC");
        RecursiveBean ObjD = new RecursiveBean("ObjD");
        ObjC.setRef(ObjB);
        ObjB.setRef(ObjA);
        ObjB.setRef2(ObjD);
        ObjA.setRef(ObjD);
        ObjD.setRef(ObjC);
        new JSONObject(ObjC);
        fail("Expected an exception");
    }

    @Test
    public void testIssue548ObjectWithEmptyJsonArray_1_oe() {
        JSONObject jsonObject = new JSONObject("{\"empty_json_array\": []}");
        assertTrue("missing expected key 'empty_json_array'", jsonObject.has("empty_json_array"));
    }

    @Test
    public void testIssue548ObjectWithEmptyJsonArray_2_oe() {
        JSONObject jsonObject = new JSONObject("{\"empty_json_array\": []}");
        // removed other assertion
        assertNotNull("'empty_json_array' should be an array", jsonObject.getJSONArray("empty_json_array"));
    }

    @Test
    public void testIssue548ObjectWithEmptyJsonArray_3_oe() {
        JSONObject jsonObject = new JSONObject("{\"empty_json_array\": []}");
        // removed other assertion
        // removed other assertion
        assertEquals("'empty_json_array' should have a length of 0", 0, jsonObject.getJSONArray("empty_json_array").length());
    }

    @Test(expected = JSONException.class)
    public void jsonObjectClearMethodTest_1_oe() {
        //Adds random stuff to the JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1", 123);
        jsonObject.put("key2", "456");
        jsonObject.put("key3", new JSONObject());
        jsonObject.clear(); //Clears the JSONObject
        assertTrue("expected jsonObject.length() == 0", jsonObject.length() == 0); //Check if its length is 0;
    }

}
