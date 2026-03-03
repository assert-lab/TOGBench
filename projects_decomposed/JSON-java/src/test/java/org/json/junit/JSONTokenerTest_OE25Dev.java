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
        final JSONTokener tokener = new JSONTokener(testString);
        assertEquals(" at 0 [character 1 line 1]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_2_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        final JSONTokener tokener = new JSONTokener(testString);
        assertEquals('t',tokener.next());
    }

    @Test
    public void testNextBackComboWithNewLines_4_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        final JSONTokener tokener = new JSONTokener(testString);
        tokener.skipTo('\n');
        assertEquals("skipTo() improperly modifying indexes"," at 7 [character 8 line 1]", tokener.toString());
    }

    @Test
    public void testNextBackComboWithNewLines_5_oe() {
        final String testString = "this is\nA test\r\nWith some different\rNew Lines";
        final JSONTokener tokener = new JSONTokener(testString);
        tokener.skipTo('\n');
        assertEquals('\n',tokener.next());
    }

}
