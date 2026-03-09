package org.json.junit;

/*
Public Domain.
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.JSONPointerException;
import org.json.JSONTokener;
import org.junit.Test;

public class JSONPointerTest_OE25Dev {

    private static final JSONObject document;
    private static final String EXPECTED_COMPLETE_DOCUMENT = "{\"\":0,\" \":7,\"g|h\":4,\"c%d\":2,\"k\\\"l\":6,\"a/b\":1,\"i\\\\j\":5," +
    		"\"obj\":{\"\":{\"\":\"empty key of an object with an empty key\",\"subKey\":\"Some other value\"}," +
            "\"other~key\":{\"another/key\":[\"val\"]},\"key\":\"value\"},\"foo\":[\"bar\",\"baz\"],\"e^f\":3," +
            "\"m~n\":8}";

    
    static {
        @SuppressWarnings("resource")
        InputStream resourceAsStream = JSONPointerTest_OE25Dev.class.getClassLoader().getResourceAsStream("jsonpointer-testdoc.json");
        if(resourceAsStream == null) {
            throw new ExceptionInInitializerError("Unable to locate test file. Please check your development environment configuration");
        }
        document = new JSONObject(new JSONTokener(resourceAsStream));
    }

    private Object query(String pointer) {
        return new JSONPointer(pointer).queryFrom(document);
    }

    @SuppressWarnings("unused")
    @Test(expected = NullPointerException.class)
    public void nullPointer() {
        new JSONPointer((String) null);
    }

    @Test(expected = JSONPointerException.class)
    public void stringPropOfArrayFailure() {
        query("/foo/bar");
    }

    /**
     * We pass backslashes as-is
     * 
     * @see <a href="https://tools.ietf.org/html/rfc6901#section-3">rfc6901 section 3</a>
     */
    
    /**
     * We pass quotations as-is
     * 
     * @see <a href="https://tools.ietf.org/html/rfc6901#section-3">rfc6901 section 3</a>
     */

    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void syntaxError() {
        new JSONPointer("key");
    }

    @Test(expected = JSONPointerException.class)
    public void arrayIndexFailure() {
        query("/foo/2");
    }

    @Test(expected = JSONPointerException.class)
    public void primitiveFailure() {
        query("/obj/key/failure");
    }
    
    @Test(expected = NullPointerException.class)
    public void nullToken() {
        JSONPointer.builder().append(null);
    }

    /**
     * Coverage for JSONObject query(String)
     */

    /**
     * Coverage for JSONObject query(JSONPointer)
     */

    /**
     * Coverage for JSONObject optQuery(JSONPointer)
     */
    
    /**
     * Coverage for JSONArray query(String)
     */

    /**
     * Coverage for JSONArray query(JSONPointer)
     */

    /**
     * Coverage for JSONArray optQuery(JSONPointer)
     */
    
    /**
     * When creating a jsonObject we need to parse escaped characters "\\\\"
     *  --> it's the string representation of  "\\", so when query'ing via the JSONPointer 
     *  we DON'T escape them
     *  
     */

    @Test
    public void emptyPointer_1_oe() {
        boolean a = new JSONObject(EXPECTED_COMPLETE_DOCUMENT).similar(query(""));
        assertTrue(a);
    }

    @Test
    public void objectPropertyQuery_1_oe() {
        Object a = query("/foo").toString();
        assertEquals("[\"bar\",\"baz\"]", a);
    }

    @Test
    public void arrayIndexQuery_1_oe() {
        Object a = query("/foo/0");
        assertEquals("bar", a);
    }

    @Test
    public void queryByEmptyKey_1_oe() {
        int a = 0;
        assertEquals(a, query("/"));
    }

    @Test
    public void queryByEmptyKeySubObject_1_oe() {
        Object a = query("/obj/").toString();
        assertEquals("{\"\":\"empty key of an object with an empty key\",\"subKey\":\"Some" + " other value\"}", a);
    }

    @Test
    public void queryByEmptyKeySubObjectSubOject_1_oe() {
        Object a = query("/obj//");
        assertEquals("empty key of an object with an empty key", a);
    }

    @Test
    public void queryByEmptyKeySubObjectValue_1_oe() {
        Object a = query("/obj//subKey");
        assertEquals("Some other value", a);
    }

    @Test
    public void slashEscaping_1_oe() {
        int a = 1;
        assertEquals(a, query("/a~1b"));
    }

    @Test
    public void tildeEscaping_1_oe() {
        int a = 8;
        assertEquals(a, query("/m~0n"));
    }

    @Test
    public void backslashHandling_1_oe() {
        int a = 5;
        assertEquals(a, query("/i\\j"));
    }

    @Test
    public void quotationHandling_1_oe() {
        int a = 6;
        assertEquals(a, query("/k\"l"));
    }

    @Test
    public void whitespaceKey_1_oe() {
        int a = 7;
        assertEquals(a, query("/ "));
    }

    @Test
    public void uriFragmentNotation_1_oe() {
        Object a = query("#/foo").toString();
        assertEquals("[\"bar\",\"baz\"]", a);
    }

    @Test
    public void uriFragmentNotationRoot_1_oe() {
        boolean a = new JSONObject(EXPECTED_COMPLETE_DOCUMENT).similar(query("#"));
        assertTrue(a);
    }

    @Test
    public void uriFragmentPercentHandling_1_oe() {
        int a = 2;
        assertEquals(a, query("#/c%25d"));
    }

    @Test
    public void uriFragmentPercentHandling_2_oe() {
        int a = 3;
        assertEquals(a, query("#/e%5Ef"));
    }

    @Test
    public void uriFragmentPercentHandling_3_oe() {
        int a = 4;
        assertEquals(a, query("#/g%7Ch"));
    }

    @Test
    public void uriFragmentPercentHandling_4_oe() {
        int a = 8;
        assertEquals(a, query("#/m~0n"));
    }

    @Test
    public void builderTest_1_oe() {
        JSONPointer pointer = JSONPointer.builder()
                .append("obj")
                .append("other~key").append("another/key")
                .append(0)
                .build();
        assertEquals("val", pointer.queryFrom(document));
    }

    @Test
    public void toStringEscaping_1_oe() {
        JSONPointer pointer = JSONPointer.builder()
                .append("obj")
                .append("other~key").append("another/key")
                .append("\"")
                .append(0)
                .build();
        assertEquals("/obj/other~0key/another~1key/\"/0", pointer.toString());
    }

    @Test
    public void emptyPointerToString_1_oe() {
        Object a = new JSONPointer("").toString();
        assertEquals("", a);
    }

    @Test
    public void toURIFragment_1_oe() {
        Object a = new JSONPointer("/c%d").toURIFragment();
        assertEquals("#/c%25d", a);
    }

    @Test
    public void toURIFragment_2_oe() {
        Object a = new JSONPointer("/e^f").toURIFragment();
        assertEquals("#/e%5Ef", a);
    }

    @Test
    public void toURIFragment_3_oe() {
        Object a = new JSONPointer("/g|h").toURIFragment();
        assertEquals("#/g%7Ch", a);
    }

    @Test
    public void toURIFragment_4_oe() {
        Object a = new JSONPointer("/m~n").toURIFragment();
        assertEquals("#/m%7En", a);
    }

    @Test
    public void tokenListIsCopiedInConstructor_1_oe() {
        JSONPointer.Builder b = JSONPointer.builder().append("key1");
        JSONPointer jp1 = b.build();
        b.append("key2");
        JSONPointer jp2 = b.build();
        if(jp1.toString().equals(jp2.toString())) {
            fail("Oops, my pointers are sharing a backing array");
    }
    }

    @Test
    public void queryFromJSONObject_1_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query("/stringKey");
        assertTrue("Expected 'hello world!'", "hello world!".equals(obj));
    }

    @Test
    public void queryFromJSONObject_2_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query("/stringKey");
        obj = jsonObject.query("/arrayKey/1");
        assertTrue("Expected 1", Integer.valueOf(1).equals(obj));
    }

    @Test
    public void queryFromJSONObject_3_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query("/stringKey");
        obj = jsonObject.query("/arrayKey/1");
        obj = jsonObject.query("/objectKey/b");
        assertTrue("Expected bVal", "bVal".equals(obj));
    }

    @Test
    public void queryFromJSONObject_5_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query("/stringKey");
        obj = jsonObject.query("/arrayKey/1");
        obj = jsonObject.query("/objectKey/b");
        try {
            obj = jsonObject.query("/a/b/c");
        } catch (JSONPointerException e) {
            assertTrue("Expected bad key/value exception","value [null] is not an array or object therefore its key b cannot be resolved". equals(e.getMessage()));
    }
    }

    @Test
    public void queryFromJSONObjectUsingPointer_1_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query(new JSONPointer("/stringKey"));
        assertTrue("Expected 'hello world!'", "hello world!".equals(obj));
    }

    @Test
    public void queryFromJSONObjectUsingPointer_2_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query(new JSONPointer("/stringKey"));
        obj = jsonObject.query(new JSONPointer("/arrayKey/1"));
        assertTrue("Expected 1", Integer.valueOf(1).equals(obj));
    }

    @Test
    public void queryFromJSONObjectUsingPointer_3_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query(new JSONPointer("/stringKey"));
        obj = jsonObject.query(new JSONPointer("/arrayKey/1"));
        obj = jsonObject.query(new JSONPointer("/objectKey/b"));
        assertTrue("Expected bVal", "bVal".equals(obj));
    }

    @Test
    public void queryFromJSONObjectUsingPointer_5_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.query(new JSONPointer("/stringKey"));
        obj = jsonObject.query(new JSONPointer("/arrayKey/1"));
        obj = jsonObject.query(new JSONPointer("/objectKey/b"));
        try {
            obj = jsonObject.query(new JSONPointer("/a/b/c"));
        } catch (JSONPointerException e) {
            assertTrue("Expected bad key/value exception","value [null] is not an array or object therefore its key b cannot be resolved". equals(e.getMessage()));
    }
    }

    @Test
    public void optQueryFromJSONObjectUsingPointer_1_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.optQuery(new JSONPointer("/stringKey"));
        assertTrue("Expected 'hello world!'", "hello world!".equals(obj));
    }

    @Test
    public void optQueryFromJSONObjectUsingPointer_2_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.optQuery(new JSONPointer("/stringKey"));
        obj = jsonObject.optQuery(new JSONPointer("/arrayKey/1"));
        assertTrue("Expected 1", Integer.valueOf(1).equals(obj));
    }

    @Test
    public void optQueryFromJSONObjectUsingPointer_3_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.optQuery(new JSONPointer("/stringKey"));
        obj = jsonObject.optQuery(new JSONPointer("/arrayKey/1"));
        obj = jsonObject.optQuery(new JSONPointer("/objectKey/b"));
        assertTrue("Expected bVal", "bVal".equals(obj));
    }

    @Test
    public void optQueryFromJSONObjectUsingPointer_4_oe() {
        String str = "{"+
                "\"stringKey\":\"hello world!\","+
                "\"arrayKey\":[0,1,2],"+
                "\"objectKey\": {"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "}";    
        JSONObject jsonObject = new JSONObject(str);
        Object obj = jsonObject.optQuery(new JSONPointer("/stringKey"));
        obj = jsonObject.optQuery(new JSONPointer("/arrayKey/1"));
        obj = jsonObject.optQuery(new JSONPointer("/objectKey/b"));
        obj = jsonObject.optQuery(new JSONPointer("/a/b/c"));
        assertTrue("Expected null", obj == null);
    }

    @Test
    public void queryFromJSONArray_1_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query("/0");
        assertTrue("Expected 'hello world!'", "hello world!".equals(obj));
    }

    @Test
    public void queryFromJSONArray_2_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query("/0");
        obj = jsonArray.query("/1/1");
        assertTrue("Expected 1", Integer.valueOf(1).equals(obj));
    }

    @Test
    public void queryFromJSONArray_3_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query("/0");
        obj = jsonArray.query("/1/1");
        obj = jsonArray.query("/2/b");
        assertTrue("Expected bVal", "bVal".equals(obj));
    }

    @Test
    public void queryFromJSONArray_5_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query("/0");
        obj = jsonArray.query("/1/1");
        obj = jsonArray.query("/2/b");
        try {
            obj = jsonArray.query("/a/b/c");
        } catch (JSONPointerException e) {
            assertTrue("Expected bad index exception","a is not an array index".equals(e.getMessage()));
    }
    }

    @Test
    public void queryFromJSONArrayUsingPointer_1_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query(new JSONPointer("/0"));
        assertTrue("Expected 'hello world!'", "hello world!".equals(obj));
    }

    @Test
    public void queryFromJSONArrayUsingPointer_2_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query(new JSONPointer("/0"));
        obj = jsonArray.query(new JSONPointer("/1/1"));
        assertTrue("Expected 1", Integer.valueOf(1).equals(obj));
    }

    @Test
    public void queryFromJSONArrayUsingPointer_3_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query(new JSONPointer("/0"));
        obj = jsonArray.query(new JSONPointer("/1/1"));
        obj = jsonArray.query(new JSONPointer("/2/b"));
        assertTrue("Expected bVal", "bVal".equals(obj));
    }

    @Test
    public void queryFromJSONArrayUsingPointer_5_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.query(new JSONPointer("/0"));
        obj = jsonArray.query(new JSONPointer("/1/1"));
        obj = jsonArray.query(new JSONPointer("/2/b"));
        try {
            obj = jsonArray.query(new JSONPointer("/a/b/c"));
        } catch (JSONPointerException e) {
            assertTrue("Expected bad index exception","a is not an array index".equals(e.getMessage()));
    }
    }

    @Test
    public void optQueryFromJSONArrayUsingPointer_1_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.optQuery(new JSONPointer("/0"));
        assertTrue("Expected 'hello world!'", "hello world!".equals(obj));
    }

    @Test
    public void optQueryFromJSONArrayUsingPointer_2_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.optQuery(new JSONPointer("/0"));
        obj = jsonArray.optQuery(new JSONPointer("/1/1"));
        assertTrue("Expected 1", Integer.valueOf(1).equals(obj));
    }

    @Test
    public void optQueryFromJSONArrayUsingPointer_3_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.optQuery(new JSONPointer("/0"));
        obj = jsonArray.optQuery(new JSONPointer("/1/1"));
        obj = jsonArray.optQuery(new JSONPointer("/2/b"));
        assertTrue("Expected bVal", "bVal".equals(obj));
    }

    @Test
    public void optQueryFromJSONArrayUsingPointer_4_oe() {
        String str = "["+
                "\"hello world!\","+
                "[0,1,2],"+
                "{"+
                    "\"a\":\"aVal\","+
                    "\"b\":\"bVal\""+
                "}"+
            "]";    
        JSONArray jsonArray = new JSONArray(str);
        Object obj = jsonArray.optQuery(new JSONPointer("/0"));
        obj = jsonArray.optQuery(new JSONPointer("/1/1"));
        obj = jsonArray.optQuery(new JSONPointer("/2/b"));
        obj = jsonArray.optQuery(new JSONPointer("/a/b/c"));
        assertTrue("Expected null", obj == null);
    }

    @Test
    public void queryFromJSONObjectUsingPointer0_1_oe() {
    	String str = "{"+
                "\"string\\\\\\\\Key\":\"hello world!\","+

                "\"\\\\\":\"slash test\"," + 
                "}"+
                "}";
            JSONObject jsonObject = new JSONObject(str);
            Object twoBackslahObj = jsonObject.optQuery(new JSONPointer("/\\"));
            assertEquals("slash test", twoBackslahObj);
    }

    @Test
    public void queryFromJSONObjectUsingPointer0_2_oe() {
    	String str = "{"+
                "\"string\\\\\\\\Key\":\"hello world!\","+

                "\"\\\\\":\"slash test\"," + 
                "}"+
                "}";
            JSONObject jsonObject = new JSONObject(str);
            Object twoBackslahObj = jsonObject.optQuery(new JSONPointer("/\\"));

            Object fourBackslashObj = jsonObject.optQuery(new JSONPointer("/string\\\\Key"));
            assertEquals("hello world!", fourBackslashObj);
    }

}
