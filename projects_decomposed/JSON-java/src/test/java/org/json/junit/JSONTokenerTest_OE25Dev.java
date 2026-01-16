package org.json.junit;

/*
Public Domain.
*/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

/**
 * Test specific to the {@link org.json.JSONTokener} class.
 * @author John Aylward
 *
 */
public class JSONTokenerTest_OE25Dev {

    /**
     * verify that back() fails as expected.
     * @throws IOException thrown if something unexpected happens.
     */
    @Test
    public void verifyBackFailureZeroIndex() throws IOException {
        Reader reader = new StringReader("some test string"); 
        try {
            final JSONTokener tokener = new JSONTokener(reader);
            try {
                // this should fail since the index is 0;
                tokener.back();
                fail("Expected an exception");
            } catch (JSONException e) {
                assertEquals("Stepping back two steps is not supported", e.getMessage());
            } catch (Exception e) {
                fail("Unknown Exception type " + e.getClass().getCanonicalName()+" with message "+e.getMessage());
            }
            
        } finally {
            reader.close();
        }
    }
    /**
     * verify that back() fails as expected.
     * @throws IOException thrown if something unexpected happens.
     */
    @Test
    public void verifyBackFailureDoubleBack() throws IOException {
        Reader reader = new StringReader("some test string");
        try {
            final JSONTokener tokener = new JSONTokener(reader);
            tokener.next();
            tokener.back();
            try {
                // this should fail since the index is 0;
                tokener.back();
                fail("Expected an exception");
            } catch (JSONException e) {
                assertEquals("Stepping back two steps is not supported", e.getMessage());
            } catch (Exception e) {
                fail("Unknown Exception type " + e.getClass().getCanonicalName()+" with message "+e.getMessage());
            }
       } finally {
           reader.close();
       }
    }
    
    @Test
    public void testValid() {
        checkValid("0",Number.class);
        checkValid(" 0  ",Number.class);
        checkValid("23",Number.class);
        checkValid("23.5",Number.class);
        checkValid(" 23.5  ",Number.class);
        checkValid("null",null);
        checkValid(" null  ",null);
        checkValid("true",Boolean.class);
        checkValid(" true\n",Boolean.class);
        checkValid("false",Boolean.class);
        checkValid("\nfalse  ",Boolean.class);
        checkValid("{}",JSONObject.class);
        checkValid(" {}  ",JSONObject.class);
        checkValid("{\"a\":1}",JSONObject.class);
        checkValid(" {\"a\":1}  ",JSONObject.class);
        checkValid("[]",JSONArray.class);
        checkValid(" []  ",JSONArray.class);
        checkValid("[1,2]",JSONArray.class);
        checkValid("\n\n[1,2]\n\n",JSONArray.class);
        checkValid("1 2", String.class);
    }
    
    @Test
    public void testErrors() {
        // Check that stream can detect that a value is found after
        // the first one
        checkError(" { \"a\":1 }  4 ");
        checkError("null \"a\"");
        checkError("{} true");
    }
    
    private Object checkValid(String testStr, Class<?> aClass)  {
        Object result = nextValue(testStr);

        // Check class of object returned
        if( null == aClass ) {
            if(JSONObject.NULL.equals(result)) {
                // OK
            } else {
                throw new JSONException("Unexpected class: "+result.getClass().getSimpleName());
            }
        } else {
            if( null == result ) {
                throw new JSONException("Unexpected null result");
            } else if(!aClass.isAssignableFrom(result.getClass()) ) {
                throw new JSONException("Unexpected class: "+result.getClass().getSimpleName());
            }
        }
        
        return result;
    }

    private void checkError(String testStr) {
        try {
            nextValue(testStr);
            
            fail("Error should be triggered: (\""+testStr+"\")");
        } catch (JSONException e) {
            // OK
        }
    }
    
    /**
     * Verifies that JSONTokener can read a stream that contains a value. After
     * the reading is done, check that the stream is left in the correct state
     * by reading the characters after. All valid cases should reach end of stream.
     * @param testStr
     * @return
     * @throws Exception
     */
    private Object nextValue(String testStr) throws JSONException {
        StringReader sr = new StringReader(testStr);
        try {
            JSONTokener tokener = new JSONTokener(sr);
    
            Object result = tokener.nextValue();
    
            if( result == null ) {
                throw new JSONException("Unable to find value token in JSON stream: ("+tokener+"): "+testStr);
            }
            
            char c = tokener.nextClean();
            if( 0 != c ) {
                throw new JSONException("Unexpected character found at end of JSON stream: "+c+ " ("+tokener+"): "+testStr);
            }
    
            return result;
        } finally {
            sr.close();
        }

    }
    
    /**
     * Tests the failure of the skipTo method with a buffered reader. Preferably
     * we'd like this not to fail but at this time we don't have a good recovery.
     * 
     * @throws IOException thrown if something unexpected happens.
     */
    @Test
    public void testSkipToFailureWithBufferedReader() throws IOException {
        final byte[] superLongBuffer = new byte[1000001];
        // fill our buffer
        for(int i=0;i<superLongBuffer.length;i++) {
            superLongBuffer[i] = 'A';
        }
        
        Reader reader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(superLongBuffer)));
        try {
            final JSONTokener tokener = new JSONTokener(reader);
            try {
                // this should fail since the internal markAhead buffer is only 1,000,000
                // but 'B' doesn't exist in our buffer that is 1,000,001 in size
                tokener.skipTo('B');
                fail("Expected exception");
            } catch (JSONException e) {
                assertEquals("Mark invalid", e.getMessage());
            } catch (Exception e) {
                fail("Unknown Exception type " + e.getClass().getCanonicalName()+" with message "+e.getMessage());
            }
        } finally {
            reader.close();
        }
    }

    /**
     * Tests the success of the skipTo method with a String reader.
     * 
     * @throws IOException thrown if something unexpected happens.
     */
    @Test
    public void testSkipToSuccessWithStringReader() throws IOException {
        final StringBuilder superLongBuffer = new StringBuilder(1000001);
        // fill our buffer
        for(int i=0;i<superLongBuffer.length();i++) {
            superLongBuffer.append('A');
        }
        Reader reader = new StringReader(superLongBuffer.toString());
        try {
            final JSONTokener tokener = new JSONTokener(reader);
            try {
                // this should not fail since the internal markAhead is ignored for StringReaders
                tokener.skipTo('B');
            } catch (Exception e) {
                fail("Unknown Exception type " + e.getClass().getCanonicalName()+" with message "+e.getMessage());
            }
        } finally {
            reader.close();
        }
    }

    /**
     * Verify that next and back are working properly and tracking the correct positions
     * with different new line combinations.
     */

    @Test
    public void testNextBackComboWithNewLines_1_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        assertEquals(" at 0 [character 1 line 1]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_2_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        assertEquals('t',tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_3_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        assertEquals(" at 1 [character 2 line 1]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_4_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        assertEquals("skipTo() improperly modifying indexes"," at 7 [character 8 line 1]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_5_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        assertEquals('\n',tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_6_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        assertEquals(" at 8 [character 0 line 2]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_7_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('A',tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_8_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 9 [character 1 line 2]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_9_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        assertEquals(" at 8 [character 0 line 2]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_10_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        assertEquals("skipTo() improperly modifying indexes"," at 14 [character 6 line 2]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_11_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        assertEquals('\r', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_12_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        assertEquals(" at 15 [character 0 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_13_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        assertEquals('\n', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_14_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 16 [character 0 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_15_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        assertEquals(" at 15 [character 6 line 2]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_16_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        assertEquals('\n', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_17_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        assertEquals(" at 16 [character 0 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_18_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('W', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_19_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 17 [character 1 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_20_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('i', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_21_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 18 [character 2 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_22_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        assertEquals("skipTo() improperly modifying indexes"," at 35 [character 19 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_23_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        assertEquals('\r', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_24_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        assertEquals(" at 36 [character 0 line 4]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_25_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        assertEquals(" at 35 [character 19 line 3]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_26_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        assertEquals('\r', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_27_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        assertEquals(" at 36 [character 0 line 4]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_28_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('N', tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_29_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 37 [character 1 line 4]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_30_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            assertTrue(t2.toString().startsWith(" at " + i + " "));
    }
    }

    @Test
    public void testNextBackComboWithNewLines_31_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            assertEquals(testString.charAt(i), t2.next());
    }
    }

    @Test
    public void testNextBackComboWithNewLines_32_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        assertEquals(" at 7 [character 8 line 1]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_33_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        assertEquals(testString.charAt(7), t2.next());
    }

    @Test
    public void testNextBackComboWithNewLines_34_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        assertEquals(" at 8 [character 0 line 2]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_35_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            assertTrue(t2.toString().startsWith(" at " + i + " "));
    }
    }

    @Test
    public void testNextBackComboWithNewLines_36_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            assertEquals(testString.charAt(i), t2.next());
    }
    }

    @Test
    public void testNextBackComboWithNewLines_37_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        assertEquals(" at 14 [character 6 line 2]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_38_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        assertEquals('\r', t2.next());
    }

    @Test
    public void testNextBackComboWithNewLines_39_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        assertEquals(" at 15 [character 0 line 3]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_40_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('\n', t2.next());
    }

    @Test
    public void testNextBackComboWithNewLines_41_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 16 [character 0 line 3]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_42_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals('W', t2.next());
    }

    @Test
    public void testNextBackComboWithNewLines_43_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(" at 17 [character 1 line 3]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_44_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            assertTrue(t2.toString().startsWith(" at " + i + " "));
    }
    }

    @Test
    public void testNextBackComboWithNewLines_45_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            assertEquals(testString.charAt(i), t2.next());
    }
    }

    @Test
    public void testNextBackComboWithNewLines_46_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            // removed other assertion
        }
        assertEquals(" at 37 [character 1 line 4]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_47_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        for(int i=37; i<testString.length(); i++) {
            assertTrue(t2.toString().startsWith(" at " + i + " "));
    }
    }

    @Test
    public void testNextBackComboWithNewLines_48_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        for(int i=37; i<testString.length(); i++) {
            // removed other assertion
            assertEquals(testString.charAt(i), t2.next());
    }
    }

    @Test
    public void testNextBackComboWithNewLines_49_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        for(int i=37; i<testString.length(); i++) {
            // removed other assertion
            // removed other assertion
        }
        assertEquals(" at "+ testString.length() +" [character 9 line 4]", t2.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_50_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        for(int i=37; i<testString.length(); i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // end of the input
        assertEquals(0, t2.next());
    }

    @Test
    public void testNextBackComboWithNewLines_51_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        //                         ^       ^         ^                    ^
        // index positions         0       8        16                   36
        final JSONTokener tokener = new JSONTokener(testString);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\n');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // verify \r\n combo doesn't increment the line twice
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // verify stepping back after reading the \n of an \r\n combo doesn't  increment the line incorrectly
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.skipTo('\r');
        // removed other assertion
        // removed other assertion
        // removed other assertion
        tokener.back();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // verify we get the same data just walking though, no calls to back
        final JSONTokener t2 = new JSONTokener(testString);
        for(int i=0; i<7; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=8; i<14; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        for(int i=17; i<37; i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        for(int i=37; i<testString.length(); i++) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion
        // end of the input
        // removed other assertion
        assertFalse(t2.more());
    }

}
