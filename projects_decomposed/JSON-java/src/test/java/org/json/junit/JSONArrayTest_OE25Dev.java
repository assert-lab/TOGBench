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

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointerException;
import org.json.JSONString;
import org.json.JSONTokener;
import org.json.junit.data.MyJsonString;
import org.junit.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;


/**
 * Tests for JSON-Java JSONArray.java
 */
public class JSONArrayTest_OE25Dev {
    private final String arrayStr = 
            "["+
                "true,"+
                "false,"+
                "\"true\","+
                "\"false\","+
                "\"hello\","+
                "23.45e-4,"+
                "\"23.45\","+
                "42,"+
                "\"43\","+
                "["+
                    "\"world\""+
                "],"+
                "{"+
                    "\"key1\":\"value1\","+
                    "\"key2\":\"value2\","+
                    "\"key3\":\"value3\","+
                    "\"key4\":\"value4\""+
                "},"+
                "0,"+
                "\"-1\""+
            "]";

    /**
     * Tests that the similar method is working as expected.
     */
        
    /**
     * Attempt to create a JSONArray with a null string.
     * Expects a NullPointerException.
     */

    /**
     * Attempt to create a JSONArray with an empty string.
     * Expects a JSONException.
     */
    
    /**
     * Attempt to create a JSONArray with an unclosed array.
     * Expects an exception
     */
    
    /**
     * Attempt to create a JSONArray with an unclosed array.
     * Expects an exception
     */
    
    /**
     * Attempt to create a JSONArray with an unclosed array.
     * Expects an exception
     */

    /**
     * Attempt to create a JSONArray with a string as object that is
     * not a JSON array doc.
     * Expects a JSONException.
     */
    
    /**
     * Verifies that the constructor has backwards compatibility with RAW types pre-java5.
     */

    /**
     * Tests consecutive calls to putAll with array and collection.
     */

    /**
     * Verifies that the put Collection has backwards compatibility with RAW types pre-java5.
     */

    
    /**
     * Verifies that the put Map has backwards compatibility with RAW types pre-java5.
     */

    /**
     * Create a JSONArray doc with a variety of different elements.
     * Confirm that the values can be accessed via the get[type]() API methods
     */

    /**
     * Create a JSONArray doc with a variety of different elements.
     * Confirm that attempting to get the wrong types via the get[type]()
     * API methods result in JSONExceptions
     */

    /**
     * Exercise JSONArray.join() by converting a JSONArray into a 
     * comma-separated string. Since this is very nearly a JSON document,
     * array braces are added to the beginning and end prior to validation.
     */

    /**
     * Confirm the JSONArray.length() method
     */

    /**
     * Create a JSONArray doc with a variety of different elements.
     * Confirm that the values can be accessed via the opt[type](index)
     * and opt[type](index, default) API methods.
     */
    
    /**
     * Verifies that the opt methods properly convert string values.
     */

    /**
     * Exercise the JSONArray.put(value) method with various parameters
     * and confirm the resulting JSONArray.
     */

    /**
     * Exercise the JSONArray.put(index, value) method with various parameters
     * and confirm the resulting JSONArray.
     */

    /**
     * Exercise the JSONArray.remove(index) method 
     * and confirm the resulting JSONArray.
     */

    /**
     * Exercise the JSONArray.similar() method with various parameters
     * and confirm the results when not similar.
     */

    /**
     * Exercise JSONArray toString() method with various indent levels.
     */

    /**
     * Convert an empty JSONArray to JSONObject
     */

    /**
     * Confirm the creation of a JSONArray from an array of ints
     */

    /**
     * Exercise the JSONArray iterator.
     */
    
    @Test(expected = JSONPointerException.class)
    public void queryWithNoResult() {
        new JSONArray().query("/a/b");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void optQueryWithSyntaxError() {
        new JSONArray().optQuery("invalid");
    }


    /**
     * Exercise the JSONArray write() method
     */
    @Test
    public void write() throws IOException {
        String str = "[\"value1\",\"value2\",{\"key1\":1,\"key2\":2,\"key3\":3}]";
        JSONArray jsonArray = new JSONArray(str);
        String expectedStr = str;
        StringWriter stringWriter = new StringWriter();
        try {
            jsonArray.write(stringWriter);
            String actualStr = stringWriter.toString();
            JSONArray finalArray = new JSONArray(actualStr);
            Util.compareActualVsExpectedJsonArrays(jsonArray, finalArray);
            assertTrue("write() expected " + expectedStr +
                    " but found " + actualStr,
                    actualStr.startsWith("[\"value1\",\"value2\",{")
                    && actualStr.contains("\"key1\":1")
                    && actualStr.contains("\"key2\":2")
                    && actualStr.contains("\"key3\":3")
                    );
        } finally {
            stringWriter.close();
        }
        Util.checkJSONArrayMaps(jsonArray);
    }

    /**
     * Exercise the JSONArray write() method using Appendable.
     */
/*
    @Test
    public void writeAppendable() {
        String str = "[\"value1\",\"value2\",{\"key1\":1,\"key2\":2,\"key3\":3}]";
        JSONArray jsonArray = new JSONArray(str);
        String expectedStr = str;
        StringBuilder stringBuilder = new StringBuilder();
        Appendable appendable = jsonArray.write(stringBuilder);
        String actualStr = appendable.toString();
        assertTrue("write()expected " + expectedStr + " but found " + actualStr,expectedStr.equals(actualStr));
    }
*/

    /**
     * Exercise the JSONArray write(Writer, int, int) method
     */
    @Test
    public void write3Param() throws IOException {
        String str0 = "[\"value1\",\"value2\",{\"key1\":1,\"key2\":false,\"key3\":3.14}]";
        JSONArray jsonArray = new JSONArray(str0);
        String expectedStr = str0;
        StringWriter stringWriter = new StringWriter();
        try {
            String actualStr = jsonArray.write(stringWriter, 0, 0).toString();
            JSONArray finalArray = new JSONArray(actualStr);
            Util.compareActualVsExpectedJsonArrays(jsonArray, finalArray);
            assertTrue("write() expected " + expectedStr +
                " but found " + actualStr,
                actualStr.startsWith("[\"value1\",\"value2\",{")
                && actualStr.contains("\"key1\":1")
                && actualStr.contains("\"key2\":false")
                && actualStr.contains("\"key3\":3.14")
            );
        } finally {
            stringWriter.close();
        }
        
        stringWriter = new StringWriter();
        try {
            String actualStr = jsonArray.write(stringWriter, 2, 1).toString();
            JSONArray finalArray = new JSONArray(actualStr);
            Util.compareActualVsExpectedJsonArrays(jsonArray, finalArray);
            assertTrue("write() expected " + expectedStr +
                " but found " + actualStr,
                actualStr.startsWith("[\n" + 
                        "   \"value1\",\n" + 
                        "   \"value2\",\n" + 
                        "   {")
                && actualStr.contains("\"key1\": 1")
                && actualStr.contains("\"key2\": false")
                && actualStr.contains("\"key3\": 3.14")
            );
            Util.checkJSONArrayMaps(finalArray);
        } finally {
            stringWriter.close();
        }
        Util.checkJSONArrayMaps(jsonArray);
    }

    /**
     * Exercise the JSONArray write(Appendable, int, int) method
     */
/*
    @Test
    public void write3ParamAppendable() {
        String str0 = "[\"value1\",\"value2\",{\"key1\":1,\"key2\":false,\"key3\":3.14}]";
        String str2 =
                "[\n" +
                        "   \"value1\",\n" +
                        "   \"value2\",\n" +
                        "   {\n" +
                        "     \"key1\": 1,\n" +
                        "     \"key2\": false,\n" +
                        "     \"key3\": 3.14\n" +
                        "   }\n" +
                        " ]";
        JSONArray jsonArray = new JSONArray(str0);
        String expectedStr = str0;
        StringBuilder stringBuilder = new StringBuilder();
        Appendable appendable = jsonArray.write(stringBuilder, 0, 0);
        String actualStr = appendable.toString();
        assertEquals(expectedStr, actualStr);

        expectedStr = str2;
        stringBuilder = new StringBuilder();
        appendable = jsonArray.write(stringBuilder, 2, 1);
        actualStr = appendable.toString();
        assertEquals(expectedStr, actualStr);
    }
*/

    /**
     * Exercise JSONArray toString() method with various indent levels.
     */

    /**
     * Create a JSONArray with specified initial capacity.
     * Expects an exception if the initial capacity is specified as a negative integer 
     */
    
    /**
     * Verifies that the object constructor can properly handle any supported collection object.
     */
    
    /**
     * Verifies that the JSONArray constructor properly copies the original.
     */
    
    /**
     * Verifies that the object constructor can properly handle any supported collection object.
     */

    /**
	 * Tests if calling JSONArray clear() method actually makes the JSONArray empty
	 */
	@Test(expected = JSONException.class)
	public void jsonArrayClearMethodTest() {
		//Adds random stuff to the JSONArray
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(123);
		jsonArray.put("456");
		jsonArray.put(new JSONArray());
		jsonArray.clear(); //Clears the JSONArray
		assertTrue("expected jsonArray.length()== 0",jsonArray.length()== 0);//Check if its length is 0 jsonArray.getInt(0);//Should throws org.json.JSONException: JSONArray[0] not found Util.checkJSONArrayMaps(jsonArray);
	}

    /**
    * Tests for stack overflow. See https://github.com/stleary/JSON-java/issues/654
    */

    @Test
    public void verifySimilar_1_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONArray obj1 = new JSONArray()
                .put("abc")
                .put(string1)
                .put(2);
        
        JSONArray obj2 = new JSONArray()
                .put("abc")
                .put(string1)
                .put(3);

        JSONArray obj3 = new JSONArray()
                .put("abc")
                .put(new String(string1))
                .put(2);

        JSONArray obj4 = new JSONArray()
                .put("abc")
                .put(2.0)
        		.put(new String(string1));

        JSONArray obj5 = new JSONArray()
                .put("abc")
                .put(2.0)
        		.put(new String(string2));
        
        assertFalse("obj1-obj2 Should eval to false", obj1.similar(obj2));
    }

    @Test
    public void verifySimilar_2_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONArray obj1 = new JSONArray()
                .put("abc")
                .put(string1)
                .put(2);
        
        JSONArray obj2 = new JSONArray()
                .put("abc")
                .put(string1)
                .put(3);

        JSONArray obj3 = new JSONArray()
                .put("abc")
                .put(new String(string1))
                .put(2);

        JSONArray obj4 = new JSONArray()
                .put("abc")
                .put(2.0)
        		.put(new String(string1));

        JSONArray obj5 = new JSONArray()
                .put("abc")
                .put(2.0)
        		.put(new String(string2));
        
        assertTrue("obj1-obj3 Should eval to true", obj1.similar(obj3));
    }

    @Test
    public void verifySimilar_3_oe() {
        final String string1 = "HasSameRef";
        final String string2 = "HasDifferentRef";
        JSONArray obj1 = new JSONArray()
                .put("abc")
                .put(string1)
                .put(2);
        
        JSONArray obj2 = new JSONArray()
                .put("abc")
                .put(string1)
                .put(3);

        JSONArray obj3 = new JSONArray()
                .put("abc")
                .put(new String(string1))
                .put(2);

        JSONArray obj4 = new JSONArray()
                .put("abc")
                .put(2.0)
        		.put(new String(string1));

        JSONArray obj5 = new JSONArray()
                .put("abc")
                .put(2.0)
        		.put(new String(string2));
        
        assertFalse("obj4-obj5 Should eval to false", obj4.similar(obj5));
    }

    @Test(expected=NullPointerException.class)
    public void nullException_1_oe() {
        String str = null;
        assertNull("Should throw an exception", new JSONArray(str));
    }

    @Test
    public void emptStr_2_oe() {
        String str = "";
        try {
        } catch (JSONException e) {
            assertEquals("Expected an exception message","A JSONArray text must start with '[' at 0 [character 1 line 1]",e.getMessage());
    }
    }

    @Test
    public void unclosedArray_2_oe() {
        try {
        } catch (JSONException e) {
            assertEquals("Expected an exception message","Expected a ',' or ']' at 1 [character 2 line 1]",e.getMessage());
    }
    }

    @Test
    public void unclosedArray2_2_oe() {
        try {
        } catch (JSONException e) {
            assertEquals("Expected an exception message","Expected a ',' or ']' at 7 [character 8 line 1]",e.getMessage());
    }
    }

    @Test
    public void unclosedArray3_2_oe() {
        try {
        } catch (JSONException e) {
            assertEquals("Expected an exception message","Expected a ',' or ']' at 8 [character 9 line 1]",e.getMessage());
    }
    }

    @Test
    public void badObject_2_oe() {
        String str = "abc";
        try {
        } catch (JSONException e) {
            assertTrue("Expected an exception message","JSONArray initial value should be a string or collection or array.". equals(e.getMessage()));
    }
    }

    @Test
    public void verifyConstructor_1_oe() {
        
        final JSONArray expected = new JSONArray("[10]");
        
        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray(myRawC);

        Collection<Integer> myCInt = Collections.singleton(Integer.valueOf(10));
        JSONArray jaInt = new JSONArray(myCInt);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONArray jaObj = new JSONArray(myCObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaRaw));
    }

    @Test
    public void verifyConstructor_2_oe() {
        
        final JSONArray expected = new JSONArray("[10]");
        
        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray(myRawC);

        Collection<Integer> myCInt = Collections.singleton(Integer.valueOf(10));
        JSONArray jaInt = new JSONArray(myCInt);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONArray jaObj = new JSONArray(myCObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaInt));
    }

    @Test
    public void verifyConstructor_3_oe() {
        
        final JSONArray expected = new JSONArray("[10]");
        
        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray(myRawC);

        Collection<Integer> myCInt = Collections.singleton(Integer.valueOf(10));
        JSONArray jaInt = new JSONArray(myCInt);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONArray jaObj = new JSONArray(myCObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaObj));
    }

    @Test
    public void verifyPutAll_1_oe() {
        final JSONArray jsonArray = new JSONArray();

        int[] myInts = { 1, 2, 3, 4, 5 };
        jsonArray.putAll(myInts);

        assertEquals("int arrays lengths should be equal",jsonArray.length(),myInts.length);
    }

    @Test
    public void verifyPutAll_2_oe() {
        final JSONArray jsonArray = new JSONArray();

        int[] myInts = { 1, 2, 3, 4, 5 };
        jsonArray.putAll(myInts);


        for (int i = 0; i < myInts.length; i++) {
            assertEquals("int arrays elements should be equal",myInts[i],jsonArray.getInt(i));
    }
    }

    @Test
    public void verifyPutAll_3_oe() {
        final JSONArray jsonArray = new JSONArray();

        int[] myInts = { 1, 2, 3, 4, 5 };
        jsonArray.putAll(myInts);


        for (int i = 0; i < myInts.length; i++) {
        }

        List<String> myList = Arrays.asList("one", "two", "three", "four", "five");
        jsonArray.putAll(myList);

        int len = myInts.length + myList.size();

        assertEquals("arrays lengths should be equal",jsonArray.length(),len);
    }

    @Test
    public void verifyPutAll_4_oe() {
        final JSONArray jsonArray = new JSONArray();

        int[] myInts = { 1, 2, 3, 4, 5 };
        jsonArray.putAll(myInts);


        for (int i = 0; i < myInts.length; i++) {
        }

        List<String> myList = Arrays.asList("one", "two", "three", "four", "five");
        jsonArray.putAll(myList);

        int len = myInts.length + myList.size();


        for (int i = 0; i < myList.size(); i++) {
            assertEquals("collection elements should be equal",myList.get(i),jsonArray.getString(myInts.length + i));
    }
    }

    @Test
    public void verifyPutCollection_1_oe() {
        
        final JSONArray expected = new JSONArray("[[10]]");

        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONArray jaObj = new JSONArray();
        jaObj.put(myCObj);

        Collection<Integer> myCInt = Collections.singleton(Integer.valueOf(10));
        JSONArray jaInt = new JSONArray();
        jaInt.put(myCInt);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaRaw));
    }

    @Test
    public void verifyPutCollection_2_oe() {
        
        final JSONArray expected = new JSONArray("[[10]]");

        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONArray jaObj = new JSONArray();
        jaObj.put(myCObj);

        Collection<Integer> myCInt = Collections.singleton(Integer.valueOf(10));
        JSONArray jaInt = new JSONArray();
        jaInt.put(myCInt);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaObj));
    }

    @Test
    public void verifyPutCollection_3_oe() {
        
        final JSONArray expected = new JSONArray("[[10]]");

        @SuppressWarnings("rawtypes")
        Collection myRawC = Collections.singleton(Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Collection<Object> myCObj = Collections.singleton((Object) Integer
                .valueOf(10));
        JSONArray jaObj = new JSONArray();
        jaObj.put(myCObj);

        Collection<Integer> myCInt = Collections.singleton(Integer.valueOf(10));
        JSONArray jaInt = new JSONArray();
        jaInt.put(myCInt);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaInt));
    }

    @Test
    public void verifyPutMap_1_oe() {
        
        final JSONArray expected = new JSONArray("[{\"myKey\":10}]");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaStrObj = new JSONArray();
        jaStrObj.put(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONArray jaStrInt = new JSONArray();
        jaStrInt.put(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaObjObj = new JSONArray();
        jaObjObj.put(myCObjObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaRaw));
    }

    @Test
    public void verifyPutMap_2_oe() {
        
        final JSONArray expected = new JSONArray("[{\"myKey\":10}]");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaStrObj = new JSONArray();
        jaStrObj.put(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONArray jaStrInt = new JSONArray();
        jaStrInt.put(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaObjObj = new JSONArray();
        jaObjObj.put(myCObjObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaStrObj));
    }

    @Test
    public void verifyPutMap_3_oe() {
        
        final JSONArray expected = new JSONArray("[{\"myKey\":10}]");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaStrObj = new JSONArray();
        jaStrObj.put(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONArray jaStrInt = new JSONArray();
        jaStrInt.put(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaObjObj = new JSONArray();
        jaObjObj.put(myCObjObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaStrInt));
    }

    @Test
    public void verifyPutMap_4_oe() {
        
        final JSONArray expected = new JSONArray("[{\"myKey\":10}]");

        @SuppressWarnings("rawtypes")
        Map myRawC = Collections.singletonMap("myKey", Integer.valueOf(10));
        JSONArray jaRaw = new JSONArray();
        jaRaw.put(myRawC);

        Map<String, Object> myCStrObj = Collections.singletonMap("myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaStrObj = new JSONArray();
        jaStrObj.put(myCStrObj);

        Map<String, Integer> myCStrInt = Collections.singletonMap("myKey",
                Integer.valueOf(10));
        JSONArray jaStrInt = new JSONArray();
        jaStrInt.put(myCStrInt);

        Map<?, ?> myCObjObj = Collections.singletonMap((Object) "myKey",
                (Object) Integer.valueOf(10));
        JSONArray jaObjObj = new JSONArray();
        jaObjObj.put(myCObjObj);

        assertTrue("The RAW Collection should give me the same as the Typed Collection",expected.similar(jaObjObj));
    }

    @Test
    public void getArrayValues_1_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array true",true == jsonArray.getBoolean(0));
    }

    @Test
    public void getArrayValues_2_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array false",false == jsonArray.getBoolean(1));
    }

    @Test
    public void getArrayValues_3_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array string true",true == jsonArray.getBoolean(2));
    }

    @Test
    public void getArrayValues_4_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array string false",false == jsonArray.getBoolean(3));
    }

    @Test
    public void getArrayValues_5_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array value string","hello".equals(jsonArray.getString(4)));
    }

    @Test
    public void getArrayValues_6_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array double",new Double(23.45e-4).equals(jsonArray.getDouble(5)));
    }

    @Test
    public void getArrayValues_7_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array string double",new Double(23.45).equals(jsonArray.getDouble(6)));
    }

    @Test
    public void getArrayValues_8_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array double can be float",new Float(23.45e-4f).equals(jsonArray.getFloat(5)));
    }

    @Test
    public void getArrayValues_9_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array value int",new Integer(42).equals(jsonArray.getInt(7)));
    }

    @Test
    public void getArrayValues_10_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array value string int",new Integer(43).equals(jsonArray.getInt(8)));
    }

    @Test
    public void getArrayValues_11_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        JSONArray nestedJsonArray = jsonArray.getJSONArray(9);
        assertTrue("Array value JSONArray", nestedJsonArray != null);
    }

    @Test
    public void getArrayValues_12_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        JSONArray nestedJsonArray = jsonArray.getJSONArray(9);
        JSONObject nestedJsonObject = jsonArray.getJSONObject(10);
        assertTrue("Array value JSONObject", nestedJsonObject != null);
    }

    @Test
    public void getArrayValues_13_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        JSONArray nestedJsonArray = jsonArray.getJSONArray(9);
        JSONObject nestedJsonObject = jsonArray.getJSONObject(10);
        assertTrue("Array value long",new Long(0).equals(jsonArray.getLong(11)));
    }

    @Test
    public void getArrayValues_14_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        JSONArray nestedJsonArray = jsonArray.getJSONArray(9);
        JSONObject nestedJsonObject = jsonArray.getJSONObject(10);
        assertTrue("Array value string long",new Long(-1).equals(jsonArray.getLong(12)));
    }

    @Test
    public void getArrayValues_15_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        JSONArray nestedJsonArray = jsonArray.getJSONArray(9);
        JSONObject nestedJsonObject = jsonArray.getJSONObject(10);

        assertTrue("Array value null", jsonArray.isNull(-1));
    }

    @Test
    public void failedGetArrayValues_4_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        try {
            jsonArray.getBoolean(4);
        } catch (JSONException e) {
        }
        try {
            jsonArray.get(-1);
        } catch (JSONException e) {
            assertEquals("Expected an exception message","JSONArray[-1] not found.",e.getMessage());
    }
    }

    @Test
    public void join_1_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected 13 items in top level object", ((List<?>)(JsonPath.read(doc, "$"))).size() == 13);
    }

    @Test
    public void join_2_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected true", Boolean.TRUE.equals(jsonArray.query("/0")));
    }

    @Test
    public void join_3_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected false", Boolean.FALSE.equals(jsonArray.query("/1")));
    }

    @Test
    public void join_4_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected \"true\"", "true".equals(jsonArray.query("/2")));
    }

    @Test
    public void join_5_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected \"false\"", "false".equals(jsonArray.query("/3")));
    }

    @Test
    public void join_6_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected hello", "hello".equals(jsonArray.query("/4")));
    }

    @Test
    public void join_7_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected 0.002345", BigDecimal.valueOf(0.002345).equals(jsonArray.query("/5")));
    }

    @Test
    public void join_8_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected \"23.45\"", "23.45".equals(jsonArray.query("/6")));
    }

    @Test
    public void join_9_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected 42", Integer.valueOf(42).equals(jsonArray.query("/7")));
    }

    @Test
    public void join_10_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected \"43\"", "43".equals(jsonArray.query("/8")));
    }

    @Test
    public void join_11_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected 1 item in [9]", ((List<?>)(JsonPath.read(doc, "$[9]"))).size() == 1);
    }

    @Test
    public void join_12_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected world", "world".equals(jsonArray.query("/9/0")));
    }

    @Test
    public void join_13_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected 4 items in [10]", ((Map<?,?>)(JsonPath.read(doc, "$[10]"))).size() == 4);
    }

    @Test
    public void join_14_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected value1", "value1".equals(jsonArray.query("/10/key1")));
    }

    @Test
    public void join_15_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected value2", "value2".equals(jsonArray.query("/10/key2")));
    }

    @Test
    public void join_16_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected value3", "value3".equals(jsonArray.query("/10/key3")));
    }

    @Test
    public void join_17_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected value4", "value4".equals(jsonArray.query("/10/key4")));
    }

    @Test
    public void join_18_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected 0", Integer.valueOf(0).equals(jsonArray.query("/11")));
    }

    @Test
    public void join_19_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        String joinStr = jsonArray.join(",");

        /**
         * Don't need to remake the JSONArray to perform the parsing
         */
        Object doc = Configuration.defaultConfiguration().jsonProvider().parse("["+joinStr+"]");
        assertTrue("expected \"-1\"", "-1".equals(jsonArray.query("/12")));
    }

    @Test 
    public void length_1_oe() {
        assertTrue("expected empty JSONArray length 0",new JSONArray().length()== 0);
    }

    @Test 
    public void length_2_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("expected JSONArray length 13. instead found "+jsonArray.length(), jsonArray.length() == 13);
    }

    @Test 
    public void length_3_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        JSONArray nestedJsonArray = jsonArray.getJSONArray(9);
        assertTrue("expected JSONArray length 1", nestedJsonArray.length() == 1);
    }

    @Test 
    public void opt_1_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array opt value true",Boolean.TRUE == jsonArray.opt(0));
    }

    @Test 
    public void opt_2_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        assertTrue("Array opt value out of range",null == jsonArray.opt(-1));
    }

    @Test 
    public void opt_3_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);

        assertTrue("Array opt value out of range",null == jsonArray.opt(jsonArray.length()));
    }

    @Test 
    public void opt_4_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);


         assertTrue("Array opt boolean",Boolean.TRUE == jsonArray.optBoolean(0));
    }

    @Test 
    public void opt_5_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);


        assertTrue("Array opt boolean default",Boolean.FALSE == jsonArray.optBoolean(-1,Boolean.FALSE));
    }

    @Test 
    public void opt_6_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);


        assertTrue("Array opt boolean implicit default",Boolean.FALSE == jsonArray.optBoolean(-1));
    }

    @Test 
    public void opt_7_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);



        assertTrue("Array opt double",new Double(23.45e-4).equals(jsonArray.optDouble(5)));
    }

    @Test 
    public void opt_8_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);



        assertTrue("Array opt double default",new Double(1).equals(jsonArray.optDouble(0,1)));
    }

    @Test 
    public void opt_9_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);



        assertTrue("Array opt double default implicit",new Double(jsonArray.optDouble(99)).isNaN());
    }

    @Test 
    public void opt_10_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);




        assertTrue("Array opt float",new Float(23.45e-4).equals(jsonArray.optFloat(5)));
    }

    @Test 
    public void opt_11_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);




        assertTrue("Array opt float default",new Float(1).equals(jsonArray.optFloat(0,1)));
    }

    @Test 
    public void opt_12_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);




        assertTrue("Array opt float default implicit",new Float(jsonArray.optFloat(99)).isNaN());
    }

    @Test 
    public void opt_13_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);





        assertTrue("Array opt Number",BigDecimal.valueOf(23.45e-4).equals(jsonArray.optNumber(5)));
    }

    @Test 
    public void opt_14_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);





        assertTrue("Array opt Number default",new Double(1).equals(jsonArray.optNumber(0,1d)));
    }

    @Test 
    public void opt_15_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);





        assertTrue("Array opt Number default implicit",new Double(jsonArray.optNumber(99,Double.NaN).doubleValue()).isNaN());
    }

    @Test 
    public void opt_16_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);






        assertTrue("Array opt int",new Integer(42).equals(jsonArray.optInt(7)));
    }

    @Test 
    public void opt_17_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);






        assertTrue("Array opt int default",new Integer(-1).equals(jsonArray.optInt(0,-1)));
    }

    @Test 
    public void opt_18_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);






        assertTrue("Array opt int default implicit",0 == jsonArray.optInt(0));
    }

    @Test 
    public void opt_19_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);
        assertTrue("Array opt JSONArray", nestedJsonArray != null);
    }

    @Test 
    public void opt_20_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);
        assertTrue("Array opt JSONArray default",null == jsonArray.optJSONArray(99));
    }

    @Test 
    public void opt_21_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);
        assertTrue("Array opt JSONObject", nestedJsonObject != null);
    }

    @Test 
    public void opt_22_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);
        assertTrue("Array opt JSONObject default",null == jsonArray.optJSONObject(99));
    }

    @Test 
    public void opt_23_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);

        assertTrue("Array opt long",0 == jsonArray.optLong(11));
    }

    @Test 
    public void opt_24_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);

        assertTrue("Array opt long default",-2 == jsonArray.optLong(-1,-2));
    }

    @Test 
    public void opt_25_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);

        assertTrue("Array opt long default implicit",0 == jsonArray.optLong(-1));
    }

    @Test 
    public void opt_26_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);


        assertTrue("Array opt string","hello".equals(jsonArray.optString(4)));
    }

    @Test 
    public void opt_27_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);







        JSONArray nestedJsonArray = jsonArray.optJSONArray(9);

        JSONObject nestedJsonObject = jsonArray.optJSONObject(10);


        assertTrue("Array opt string default implicit","".equals(jsonArray.optString(-1)));
    }

    @Test
    public void optStringConversion_1_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optBoolean value",ja.optBoolean(1,false)==true);
    }

    @Test
    public void optStringConversion_2_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optBoolean value",ja.optBoolean(2,true)==false);
    }

    @Test
    public void optStringConversion_3_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optInt value",ja.optInt(0,0)==123);
    }

    @Test
    public void optStringConversion_4_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optLong value",ja.optLong(0,0)==123);
    }

    @Test
    public void optStringConversion_5_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optDouble value",ja.optDouble(0,0.0)==123.0);
    }

    @Test
    public void optStringConversion_6_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optBigInteger value",ja.optBigInteger(0,BigInteger.ZERO).compareTo(new BigInteger("123"))==0);
    }

    @Test
    public void optStringConversion_7_oe(){
        JSONArray ja = new JSONArray("[\"123\",\"true\",\"false\"]");
        assertTrue("unexpected optBigDecimal value",ja.optBigDecimal(0,BigDecimal.ZERO).compareTo(new BigDecimal("123"))==0);
    }

    @Test
    public void put_1_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 10 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 10);
    }

    @Test
    public void put_2_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected true", Boolean.TRUE.equals(jsonArray.query("/0")));
    }

    @Test
    public void put_3_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected false", Boolean.FALSE.equals(jsonArray.query("/1")));
    }

    @Test
    public void put_4_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2 items in [2]", ((List<?>)(JsonPath.read(doc, "$[2]"))).size() == 2);
    }

    @Test
    public void put_5_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected hello", "hello".equals(jsonArray.query("/2/0")));
    }

    @Test
    public void put_6_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected world", "world".equals(jsonArray.query("/2/1")));
    }

    @Test
    public void put_7_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2.5", Double.valueOf(2.5).equals(jsonArray.query("/3")));
    }

    @Test
    public void put_8_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/4")));
    }

    @Test
    public void put_9_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 45", Long.valueOf(45).equals(jsonArray.query("/5")));
    }

    @Test
    public void put_10_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected objectPut", "objectPut".equals(jsonArray.query("/6")));
    }

    @Test
    public void put_11_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 3 items in [7]", ((Map<?,?>)(JsonPath.read(doc, "$[7]"))).size() == 3);
    }

    @Test
    public void put_12_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected val10", "val10".equals(jsonArray.query("/7/key10")));
    }

    @Test
    public void put_13_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected val20", "val20".equals(jsonArray.query("/7/key20")));
    }

    @Test
    public void put_14_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected val30", "val30".equals(jsonArray.query("/7/key30")));
    }

    @Test
    public void put_15_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1 item in [8]", ((Map<?,?>)(JsonPath.read(doc, "$[8]"))).size() == 1);
    }

    @Test
    public void put_16_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected v1", "v1".equals(jsonArray.query("/8/k1")));
    }

    @Test
    public void put_17_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2 items in [9]", ((List<?>)(JsonPath.read(doc, "$[9]"))).size() == 2);
    }

    @Test
    public void put_18_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/9/0")));
    }

    @Test
    public void put_19_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(true);
        jsonArray.put(false);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(new JSONArray(jsonArrayStr));

        jsonArray.put(2.5);
        jsonArray.put(1);
        jsonArray.put(45L);

        jsonArray.put("objectPut");

        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(jsonObject);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(map);

        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(collection);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonArray.query("/9/1")));
    }

    @Test
    public void putIndex_2_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 11 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 11);
    }

    @Test
    public void putIndex_3_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected true", Boolean.TRUE.equals(jsonArray.query("/0")));
    }

    @Test
    public void putIndex_4_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected false", Boolean.FALSE.equals(jsonArray.query("/1")));
    }

    @Test
    public void putIndex_5_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2 items in [2]", ((List<?>)(JsonPath.read(doc, "$[2]"))).size() == 2);
    }

    @Test
    public void putIndex_6_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected hello", "hello".equals(jsonArray.query("/2/0")));
    }

    @Test
    public void putIndex_7_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected world", "world".equals(jsonArray.query("/2/1")));
    }

    @Test
    public void putIndex_8_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2.5", Double.valueOf(2.5).equals(jsonArray.query("/3")));
    }

    @Test
    public void putIndex_9_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/4")));
    }

    @Test
    public void putIndex_10_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 45", Long.valueOf(45).equals(jsonArray.query("/5")));
    }

    @Test
    public void putIndex_11_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected objectPut", "objectPut".equals(jsonArray.query("/6")));
    }

    @Test
    public void putIndex_12_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected null", JSONObject.NULL.equals(jsonArray.query("/7")));
    }

    @Test
    public void putIndex_13_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 3 items in [8]", ((Map<?,?>)(JsonPath.read(doc, "$[8]"))).size() == 3);
    }

    @Test
    public void putIndex_14_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected val10", "val10".equals(jsonArray.query("/8/key10")));
    }

    @Test
    public void putIndex_15_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected val20", "val20".equals(jsonArray.query("/8/key20")));
    }

    @Test
    public void putIndex_16_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected val30", "val30".equals(jsonArray.query("/8/key30")));
    }

    @Test
    public void putIndex_17_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2 items in [9]", ((List<?>)(JsonPath.read(doc, "$[9]"))).size() == 2);
    }

    @Test
    public void putIndex_18_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/9/0")));
    }

    @Test
    public void putIndex_19_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonArray.query("/9/1")));
    }

    @Test
    public void putIndex_20_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1 item in [10]", ((Map<?,?>)(JsonPath.read(doc, "$[10]"))).size() == 1);
    }

    @Test
    public void putIndex_21_oe() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(1, false);
        jsonArray.put(0, true);

        String jsonArrayStr =
            "["+
                "hello,"+
                "world"+
            "]";
        jsonArray.put(2, new JSONArray(jsonArrayStr));

        jsonArray.put(5, 45L);
        jsonArray.put(4, 1);
        jsonArray.put(3, 2.5);

        jsonArray.put(6, "objectPut");


        String jsonObjectStr = 
            "{"+
                "\"key10\":\"val10\","+
                "\"key20\":\"val20\","+
                "\"key30\":\"val30\""+
            "}";
        JSONObject jsonObject = new JSONObject(jsonObjectStr);
        jsonArray.put(8, jsonObject);
        Collection<Object> collection = new ArrayList<Object>();
        collection.add(1);
        collection.add(2);
        jsonArray.put(9,collection);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k1", "v1");
        jsonArray.put(10, map);
        try {
            jsonArray.put(-1, "abc");
        } catch(Exception ignored) {}

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected v1", "v1".equals(jsonArray.query("/10/k1")));
    }

    @Test
    public void remove_1_oe() {
        String arrayStr1 = 
            "["+
                "1"+
            "]";
        JSONArray jsonArray = new JSONArray(arrayStr1);
        jsonArray.remove(0);
        assertTrue("array should be empty", null == jsonArray.remove(5));
    }

    @Test
    public void remove_2_oe() {
        String arrayStr1 = 
            "["+
                "1"+
            "]";
        JSONArray jsonArray = new JSONArray(arrayStr1);
        jsonArray.remove(0);
        assertTrue("jsonArray should be empty", jsonArray.isEmpty());
    }

    @Test
    public void notSimilar_1_oe() {
        String arrayStr1 = 
            "["+
                "1"+
            "]";
        JSONArray jsonArray = new JSONArray(arrayStr1);
        JSONArray otherJsonArray = new JSONArray();
        assertTrue("arrays lengths differ", !jsonArray.similar(otherJsonArray));
    }

    @Test
    public void notSimilar_2_oe() {
        String arrayStr1 = 
            "["+
                "1"+
            "]";
        JSONArray jsonArray = new JSONArray(arrayStr1);
        JSONArray otherJsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject("{\"k1\":\"v1\"}");
        JSONObject otherJsonObject = new JSONObject();
        jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        otherJsonArray = new JSONArray();
        otherJsonArray.put(otherJsonObject);
        assertTrue("arrays JSONObjects differ", !jsonArray.similar(otherJsonArray));
    }

    @Test
    public void notSimilar_3_oe() {
        String arrayStr1 = 
            "["+
                "1"+
            "]";
        JSONArray jsonArray = new JSONArray(arrayStr1);
        JSONArray otherJsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject("{\"k1\":\"v1\"}");
        JSONObject otherJsonObject = new JSONObject();
        jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        otherJsonArray = new JSONArray();
        otherJsonArray.put(otherJsonObject);

        JSONArray nestedJsonArray = new JSONArray("[1, 2]");
        JSONArray otherNestedJsonArray = new JSONArray();
        jsonArray = new JSONArray();
        jsonArray.put(nestedJsonArray);
        otherJsonArray = new JSONArray();
        otherJsonArray.put(otherNestedJsonArray);
        assertTrue("arrays nested JSONArrays differ",!jsonArray.similar(otherJsonArray));
    }

    @Test
    public void notSimilar_4_oe() {
        String arrayStr1 = 
            "["+
                "1"+
            "]";
        JSONArray jsonArray = new JSONArray(arrayStr1);
        JSONArray otherJsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject("{\"k1\":\"v1\"}");
        JSONObject otherJsonObject = new JSONObject();
        jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        otherJsonArray = new JSONArray();
        otherJsonArray.put(otherJsonObject);

        JSONArray nestedJsonArray = new JSONArray("[1, 2]");
        JSONArray otherNestedJsonArray = new JSONArray();
        jsonArray = new JSONArray();
        jsonArray.put(nestedJsonArray);
        otherJsonArray = new JSONArray();
        otherJsonArray.put(otherNestedJsonArray);

        jsonArray = new JSONArray();
        jsonArray.put("hello");
        otherJsonArray = new JSONArray();
        otherJsonArray.put("world");
        assertTrue("arrays values differ",!jsonArray.similar(otherJsonArray));
    }

    @Test
    public void jsonArrayToStringIndent_1_oe() {
        String jsonArray0Str =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":\"val2\"}" +
                    "}," +
                    "[" +
                        "[1,2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        String jsonArray1Strs [] = 
            {
                "[",
                " [",
                "  1,",
                "  2,",
                "  {\"key3\": true}",
                " ],",
                " {",
                "  \"key1\": \"val1\",",
                "  \"key2\": {\"key2\": \"val2\"}",
                " },",
                " [",
                "  [",
                "   1,",
                "   2.1",
                "  ],",
                "  [null]",
                " ]",
                "]"
            };
        String jsonArray4Strs [] =
            {
                "[",
                "    [",
                "        1,",
                "        2,",
                "        {\"key3\": true}",
                "    ],",
                "    {",
                "        \"key1\": \"val1\",",
                "        \"key2\": {\"key2\": \"val2\"}",
                "    },",
                "    [",
                "        [",
                "            1,",
                "            2.1",
                "        ],",
                "        [null]",
                "    ]",
                "]"
            };
        JSONArray jsonArray = new JSONArray(jsonArray0Str);
        String [] actualStrArray = jsonArray.toString().split("\\r?\\n");
        assertEquals("Expected 1 line", 1, actualStrArray.length);
    }

    @Test
    public void jsonArrayToStringIndent_2_oe() {
        String jsonArray0Str =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":\"val2\"}" +
                    "}," +
                    "[" +
                        "[1,2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        String jsonArray1Strs [] = 
            {
                "[",
                " [",
                "  1,",
                "  2,",
                "  {\"key3\": true}",
                " ],",
                " {",
                "  \"key1\": \"val1\",",
                "  \"key2\": {\"key2\": \"val2\"}",
                " },",
                " [",
                "  [",
                "   1,",
                "   2.1",
                "  ],",
                "  [null]",
                " ]",
                "]"
            };
        String jsonArray4Strs [] =
            {
                "[",
                "    [",
                "        1,",
                "        2,",
                "        {\"key3\": true}",
                "    ],",
                "    {",
                "        \"key1\": \"val1\",",
                "        \"key2\": {\"key2\": \"val2\"}",
                "    },",
                "    [",
                "        [",
                "            1,",
                "            2.1",
                "        ],",
                "        [null]",
                "    ]",
                "]"
            };
        JSONArray jsonArray = new JSONArray(jsonArray0Str);
        String [] actualStrArray = jsonArray.toString().split("\\r?\\n");
        actualStrArray = jsonArray.toString(0).split("\\r?\\n");
        assertEquals("Expected 1 line", 1, actualStrArray.length);
    }

    @Test
    public void jsonArrayToStringIndent_3_oe() {
        String jsonArray0Str =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":\"val2\"}" +
                    "}," +
                    "[" +
                        "[1,2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        String jsonArray1Strs [] = 
            {
                "[",
                " [",
                "  1,",
                "  2,",
                "  {\"key3\": true}",
                " ],",
                " {",
                "  \"key1\": \"val1\",",
                "  \"key2\": {\"key2\": \"val2\"}",
                " },",
                " [",
                "  [",
                "   1,",
                "   2.1",
                "  ],",
                "  [null]",
                " ]",
                "]"
            };
        String jsonArray4Strs [] =
            {
                "[",
                "    [",
                "        1,",
                "        2,",
                "        {\"key3\": true}",
                "    ],",
                "    {",
                "        \"key1\": \"val1\",",
                "        \"key2\": {\"key2\": \"val2\"}",
                "    },",
                "    [",
                "        [",
                "            1,",
                "            2.1",
                "        ],",
                "        [null]",
                "    ]",
                "]"
            };
        JSONArray jsonArray = new JSONArray(jsonArray0Str);
        String [] actualStrArray = jsonArray.toString().split("\\r?\\n");
        actualStrArray = jsonArray.toString(0).split("\\r?\\n");

        actualStrArray = jsonArray.toString(1).split("\\r?\\n");
        assertEquals("Expected lines", jsonArray1Strs.length, actualStrArray.length);
    }

    @Test
    public void jsonArrayToStringIndent_4_oe() {
        String jsonArray0Str =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":\"val2\"}" +
                    "}," +
                    "[" +
                        "[1,2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        String jsonArray1Strs [] = 
            {
                "[",
                " [",
                "  1,",
                "  2,",
                "  {\"key3\": true}",
                " ],",
                " {",
                "  \"key1\": \"val1\",",
                "  \"key2\": {\"key2\": \"val2\"}",
                " },",
                " [",
                "  [",
                "   1,",
                "   2.1",
                "  ],",
                "  [null]",
                " ]",
                "]"
            };
        String jsonArray4Strs [] =
            {
                "[",
                "    [",
                "        1,",
                "        2,",
                "        {\"key3\": true}",
                "    ],",
                "    {",
                "        \"key1\": \"val1\",",
                "        \"key2\": {\"key2\": \"val2\"}",
                "    },",
                "    [",
                "        [",
                "            1,",
                "            2.1",
                "        ],",
                "        [null]",
                "    ]",
                "]"
            };
        JSONArray jsonArray = new JSONArray(jsonArray0Str);
        String [] actualStrArray = jsonArray.toString().split("\\r?\\n");
        actualStrArray = jsonArray.toString(0).split("\\r?\\n");

        actualStrArray = jsonArray.toString(1).split("\\r?\\n");
        List<String> list = Arrays.asList(actualStrArray);
        for (String s : jsonArray1Strs) {
            list.contains(s);
        }
        
        actualStrArray = jsonArray.toString(4).split("\\r?\\n");
        assertEquals("Expected lines", jsonArray1Strs.length, actualStrArray.length);
    }

    @Test
    public void toJSONObject_1_oe() {
        JSONArray names = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        assertTrue("toJSONObject should return null",null == jsonArray.toJSONObject(names));
    }

    @Test
    public void objectArrayVsIsArray_1_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 7 top level items", ((List<?>)(JsonPath.read(doc, "$"))).size() == 7);
    }

    @Test
    public void objectArrayVsIsArray_2_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 1", Integer.valueOf(1).equals(jsonArray.query("/0")));
    }

    @Test
    public void objectArrayVsIsArray_3_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 2", Integer.valueOf(2).equals(jsonArray.query("/1")));
    }

    @Test
    public void objectArrayVsIsArray_4_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 3", Integer.valueOf(3).equals(jsonArray.query("/2")));
    }

    @Test
    public void objectArrayVsIsArray_5_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 4", Integer.valueOf(4).equals(jsonArray.query("/3")));
    }

    @Test
    public void objectArrayVsIsArray_6_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 5", Integer.valueOf(5).equals(jsonArray.query("/4")));
    }

    @Test
    public void objectArrayVsIsArray_7_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 6", Integer.valueOf(6).equals(jsonArray.query("/5")));
    }

    @Test
    public void objectArrayVsIsArray_8_oe() {
        int[] myInts = { 1, 2, 3, 4, 5, 6, 7 };
        Object myObject = myInts;
        JSONArray jsonArray = new JSONArray(myObject);

        Object doc = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.toString());
        assertTrue("expected 7", Integer.valueOf(7).equals(jsonArray.query("/6")));
    }

    @Test
    public void iteratorTest_1_oe() {
        JSONArray jsonArray = new JSONArray(this.arrayStr);
        Iterator<Object> it = jsonArray.iterator();
        assertTrue("Array true",Boolean.TRUE.equals(it.next()));
    }

    @Test
    public void optQueryWithNoResult_1_oe() {
        assertNull(new JSONArray().optQuery("/a/b"));
    }

    @Test
    public void toList_1_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();

        assertTrue("List should not be null", list != null);
    }

    @Test
    public void toList_2_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();

        assertTrue("List should have 3 elements", list.size() == 3);
    }

    @Test
    public void toList_3_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);
        assertTrue("val1 should not be null", val1List != null);
    }

    @Test
    public void toList_4_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);
        assertTrue("val1 should have 3 elements", val1List.size() == 3);
    }

    @Test
    public void toList_5_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);

        assertTrue("val1 value 1 should be 1", val1List.get(0).equals(Integer.valueOf(1)));
    }

    @Test
    public void toList_6_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);

        assertTrue("val1 value 2 should be 2", val1List.get(1).equals(Integer.valueOf(2)));
    }

    @Test
    public void toList_7_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);
        assertTrue("Map should not be null", key1Value3Map != null);
    }

    @Test
    public void toList_8_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);
        assertTrue("Map should have 1 element", key1Value3Map.size() == 1);
    }

    @Test
    public void toList_9_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);
        assertTrue("Map key3 should be true", key1Value3Map.get("key3").equals(Boolean.TRUE));
    }

    @Test
    public void toList_10_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);
        assertTrue("val2 should not be null", val2Map != null);
    }

    @Test
    public void toList_11_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);
        assertTrue("val2 should have 4 elements", val2Map.size() == 4);
    }

    @Test
    public void toList_12_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);
        assertTrue("val2 map key 1 should be val1", val2Map.get("key1").equals("val1"));
    }

    @Test
    public void toList_13_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);
        assertTrue("val2 map key 3 should be 42", val2Map.get("key3").equals(Integer.valueOf(42)));
    }

    @Test
    public void toList_14_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");
        assertTrue("val2 map key 2 should not be null", val2Key2Map != null);
    }

    @Test
    public void toList_15_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");
        assertTrue("val2 map key 2 should have an entry", val2Key2Map.containsKey("key2"));
    }

    @Test
    public void toList_16_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");
        assertTrue("val2 map key 2 value should be null", val2Key2Map.get("key2") == null);
    }

    @Test
    public void toList_17_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");
        assertTrue("val2 map key 4 should not be null", val2Key4List != null);
    }

    @Test
    public void toList_18_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");
        assertTrue("val2 map key 4 should be empty", val2Key4List.isEmpty());
    }

    @Test
    public void toList_19_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);
        assertTrue("val3 should not be null", val3List != null);
    }

    @Test
    public void toList_20_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);
        assertTrue("val3 should have 2 elements", val3List.size() == 2);
    }

    @Test
    public void toList_21_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);
        assertTrue("val3 list val 1 should not be null", val3Val1List != null);
    }

    @Test
    public void toList_22_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);
        assertTrue("val3 list val 1 should have 2 elements", val3Val1List.size() == 2);
    }

    @Test
    public void toList_23_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);
        assertTrue("val3 list val 1 list element 1 should be value1", val3Val1List.get(0).equals("value1"));
    }

    @Test
    public void toList_24_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);
        assertTrue("val3 list val 1 list element 2 should be 2.1", val3Val1List.get(1).equals(new BigDecimal("2.1")));
    }

    @Test
    public void toList_25_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);

        List<?> val3Val2List = (List<?>)val3List.get(1);
        assertTrue("val3 list val 2 should not be null", val3Val2List != null);
    }

    @Test
    public void toList_26_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);

        List<?> val3Val2List = (List<?>)val3List.get(1);
        assertTrue("val3 list val 2 should have 1 element", val3Val2List.size() == 1);
    }

    @Test
    public void toList_27_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);

        List<?> val3Val2List = (List<?>)val3List.get(1);
        assertTrue("val3 list val 2 list element 1 should be null", val3Val2List.get(0) == null);
    }

    @Test
    public void toList_28_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);

        List<?> val3Val2List = (List<?>)val3List.get(1);

        jsonArray.getJSONObject(1).put("key1", "still val1");
        assertTrue("val2 map key 1 should be val1", val2Map.get("key1").equals("val1"));
    }

    @Test
    public void toList_29_oe() {
        String jsonArrayStr =
                "[" +
                    "[1,2," +
                        "{\"key3\":true}" +
                    "]," +
                    "{\"key1\":\"val1\",\"key2\":" +
                        "{\"key2\":null}," +
                    "\"key3\":42,\"key4\":[]" +
                    "}," +
                    "[" +
                        "[\"value1\",2.1]" +
                    "," +
                        "[null]" +
                    "]" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        List<?> list = jsonArray.toList();


        List<?> val1List = (List<?>) list.get(0);


        Map<?,?> key1Value3Map = (Map<?,?>)val1List.get(2);

        Map<?,?> val2Map = (Map<?,?>) list.get(1);

        Map<?,?> val2Key2Map = (Map<?,?>)val2Map.get("key2");

        List<?> val2Key4List = (List<?>)val2Map.get("key4");

        List<?> val3List = (List<?>) list.get(2);

        List<?> val3Val1List = (List<?>)val3List.get(0);

        List<?> val3Val2List = (List<?>)val3List.get(1);

        jsonArray.getJSONObject(1).put("key1", "still val1");

        assertTrue("Removing an entry should succeed", list.remove(2) != null);
    }

    @Test
    public void testJSONArrayInt_1_oe() {
        assertNotNull(new JSONArray(0));
    }

    @Test
    public void testJSONArrayInt_2_oe() {
        assertNotNull(new JSONArray(5));
    }

    @Test
    public void testJSONArrayInt_4_oe() {
        try {
        } catch (JSONException e) {
            assertEquals("Expected an exception message","JSONArray initial capacity cannot be negative.",e.getMessage());
    }
    }

    public void testObjectConstructor_1_oe() {
        Object o = new Object[] {2, "test2", true};
        JSONArray a = new JSONArray(o);
        assertNotNull("Should not error", a);
    }

    public void testObjectConstructor_2_oe() {
        Object o = new Object[] {2, "test2", true};
        JSONArray a = new JSONArray(o);
        assertEquals("length", 3, a.length());
    }

    @Test
    public void testJSONArrayConstructor_1_oe() {
        JSONArray a1 = new JSONArray("[2, \"test2\", true]");
        JSONArray a2 = new JSONArray(a1);
        assertNotNull("Should not error", a2);
    }

    @Test
    public void testJSONArrayConstructor_2_oe() {
        JSONArray a1 = new JSONArray("[2, \"test2\", true]");
        JSONArray a2 = new JSONArray(a1);
        assertEquals("length", a1.length(), a2.length());
    }

    @Test
    public void testJSONArrayConstructor_3_oe() {
        JSONArray a1 = new JSONArray("[2, \"test2\", true]");
        JSONArray a2 = new JSONArray(a1);
        
        for(int i = 0; i < a1.length(); i++) {
            assertEquals("index " + i + " are equal", a1.get(i), a2.get(i));
    }
    }

    @Test
    public void testJSONArrayPutAll_1_oe() {
        JSONArray a1 = new JSONArray("[2, \"test2\", true]");
        JSONArray a2 = new JSONArray();
        a2.putAll(a1);
        assertNotNull("Should not error", a2);
    }

    @Test
    public void testJSONArrayPutAll_2_oe() {
        JSONArray a1 = new JSONArray("[2, \"test2\", true]");
        JSONArray a2 = new JSONArray();
        a2.putAll(a1);
        assertEquals("length", a1.length(), a2.length());
    }

    @Test
    public void testJSONArrayPutAll_3_oe() {
        JSONArray a1 = new JSONArray("[2, \"test2\", true]");
        JSONArray a2 = new JSONArray();
        a2.putAll(a1);
        
        for(int i = 0; i < a1.length(); i++) {
            assertEquals("index " + i + " are equal", a1.get(i), a2.get(i));
    }
    }

    @Test(expected = JSONException.class)
    public void issue654StackOverflowInputWellFormed_1_oe() {
        final InputStream resourceAsStream = JSONObjectTest.class.getClassLoader().getResourceAsStream("Issue654WellFormedArray.json");
        JSONTokener tokener = new JSONTokener(resourceAsStream);
        JSONArray json_input = new JSONArray(tokener);
        assertNotNull(json_input);
    }

    @Test(expected = JSONException.class)
    public void issue654StackOverflowInputWellFormed_2_oe() {
        final InputStream resourceAsStream = JSONObjectTest.class.getClassLoader().getResourceAsStream("Issue654WellFormedArray.json");
        JSONTokener tokener = new JSONTokener(resourceAsStream);
        JSONArray json_input = new JSONArray(tokener);
        fail("Excepected Exception.");
    }

    @Test
    public void testIssue682SimilarityOfJSONString_1_oe() {
        JSONArray ja1 = new JSONArray()
                .put(new MyJsonString())
                .put(2);
        JSONArray ja2 = new JSONArray()
                .put(new MyJsonString())
                .put(2);
        assertTrue(ja1.similar(ja2));
    }

    @Test
    public void testIssue682SimilarityOfJSONString_2_oe() {
        JSONArray ja1 = new JSONArray()
                .put(new MyJsonString())
                .put(2);
        JSONArray ja2 = new JSONArray()
                .put(new MyJsonString())
                .put(2);

        JSONArray ja3 = new JSONArray()
                .put(new JSONString() {
                    @Override
                    public String toJSONString() {
                        return "\"different value\"";
                    }
                })
                .put(2);
        assertFalse(ja1.similar(ja3));
    }

}
