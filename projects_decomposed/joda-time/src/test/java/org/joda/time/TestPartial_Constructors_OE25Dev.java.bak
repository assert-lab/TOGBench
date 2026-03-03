/*
 *  Copyright 2001-2013 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time;

import java.util.Arrays;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;

/**
 * This class is a Junit unit test for Partial.
 *
 * @author Stephen Colebourne
 */
public class TestPartial_Constructors_OE25Dev extends TestCase {

    private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    private static final Chronology ISO_UTC = ISOChronology.getInstanceUTC();
    private static final Chronology GREGORIAN_PARIS = GregorianChronology.getInstance(PARIS);
    private static final Chronology GREGORIAN_UTC = GregorianChronology.getInstanceUTC();
    
    private long TEST_TIME_NOW =
            10L * DateTimeConstants.MILLIS_PER_HOUR
            + 20L * DateTimeConstants.MILLIS_PER_MINUTE
            + 30L * DateTimeConstants.MILLIS_PER_SECOND
            + 40L;
        
    private DateTimeZone zone = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestPartial_Constructors_OE25Dev.class);
    }

    public TestPartial_Constructors_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        zone = DateTimeZone.getDefault();
        DateTimeZone.setDefault(LONDON);
    }

    @Override
    protected void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        zone = null;
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */
    public void testConstructorEx2_Type_int() throws Throwable {
        try {
            new Partial(DateTimeFieldType.dayOfYear(), 0);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */
    public void testConstructorEx2_Type_int_Chrono() throws Throwable {
        try {
            new Partial(DateTimeFieldType.dayOfYear(), 0, ISO_UTC);
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    //-----------------------------------------------------------------------
    /**
     * Test constructor
     */

    /**
     * Test constructor
     */

    //-----------------------------------------------------------------------
    /**
     * Checks if the exception message is valid.
     * 
     * @param ex  the exception to check
     * @param str  the string to check
     */
    private void assertMessageContains(Exception ex, String str) {
        assertEquals(ex.getMessage()+ ": " + str,true,ex.getMessage().indexOf(str)>= 0);
    }

    /**
     * Checks if the exception message is valid.
     * 
     * @param ex  the exception to check
     * @param str1  the string to check
     * @param str2  the string to check
     */
    private void assertMessageContains(Exception ex, String str1, String str2) {
        assertEquals(ex.getMessage()+ ": " + str1 + "/" + str2,true,ex.getMessage().indexOf(str1)>= 0 && ex.getMessage().indexOf(str2)>= 0 && ex.getMessage().indexOf(str1)< ex.getMessage().indexOf(str2));
    }

    public void testConstructor_1_oe() throws Throwable {
        Partial test = new Partial();
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_2_oe() throws Throwable {
        Partial test = new Partial();
        // removed other assertion
        assertEquals(0,test.size());
    }

    public void testConstructor_Chrono_1_oe() throws Throwable {
        Partial test = new Partial((Chronology) null);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Chrono_2_oe() throws Throwable {
        Partial test = new Partial((Chronology) null);
        // removed other assertion
        assertEquals(0,test.size());
    }

    public void testConstructor_Chrono_3_oe() throws Throwable {
        Partial test = new Partial((Chronology) null);
        // removed other assertion
        // removed other assertion
        
        test = new Partial(GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Chrono_4_oe() throws Throwable {
        Partial test = new Partial((Chronology) null);
        // removed other assertion
        // removed other assertion
        
        test = new Partial(GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(0,test.size());
    }

    public void testConstructor_Type_int_1_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_Type_int_2_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4);
        // removed other assertion
        assertEquals(1,test.size());
    }

    public void testConstructor_Type_int_3_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4);
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.getValue(0));
    }

    public void testConstructor_Type_int_4_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_Type_int_5_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_Type_int_Chrono_1_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Type_int_Chrono_2_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(1,test.size());
    }

    public void testConstructor_Type_int_Chrono_3_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.getValue(0));
    }

    public void testConstructor_Type_int_Chrono_4_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4,test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_Type_int_Chrono_5_oe() throws Throwable {
        Partial test = new Partial(DateTimeFieldType.dayOfYear(), 4, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_TypeArray_intArray_1_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_TypeArray_intArray_2_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        assertEquals(2,test.size());
    }

    public void testConstructor_TypeArray_intArray_3_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.getValue(0));
    }

    public void testConstructor_TypeArray_intArray_4_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.get(DateTimeFieldType.year()));
    }

    public void testConstructor_TypeArray_intArray_5_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
    }

    public void testConstructor_TypeArray_intArray_6_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(33,test.getValue(1));
    }

    public void testConstructor_TypeArray_intArray_7_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(33,test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_TypeArray_intArray_8_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_TypeArray_intArray_9_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Arrays.equals(test.getFieldTypes(),types));
    }

    public void testConstructor_TypeArray_intArray_10_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Arrays.equals(test.getValues(),values));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_1_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_2_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        assertEquals(2,test.size());
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_3_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.getValue(0));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_4_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.get(DateTimeFieldType.year()));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_5_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_6_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2006,test.getValue(1));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_7_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2006,test.get(DateTimeFieldType.weekyear()));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_8_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.weekyear()));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_9_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Arrays.equals(test.getFieldTypes(),types));
    }

    public void testConstructor_TypeArray_intArray_year_weekyear_10_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.weekyear()
        };
        int[] values = new int[] {2005, 2006};
        Partial test = new Partial(types, values);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Arrays.equals(test.getValues(),values));
    }

    public void testConstructor2_TypeArray_intArray_1_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[0];
        int[] values = new int[0];
        Partial test = new Partial(types, values);
        assertEquals(ISO_UTC,test.getChronology());
    }

    public void testConstructor2_TypeArray_intArray_2_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[0];
        int[] values = new int[0];
        Partial test = new Partial(types, values);
        // removed other assertion
        assertEquals(0,test.size());
    }

    public void testConstructor_TypeArray_intArray_Chrono_1_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_TypeArray_intArray_Chrono_2_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        assertEquals(2,test.size());
    }

    public void testConstructor_TypeArray_intArray_Chrono_3_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.getValue(0));
    }

    public void testConstructor_TypeArray_intArray_Chrono_4_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.get(DateTimeFieldType.year()));
    }

    public void testConstructor_TypeArray_intArray_Chrono_5_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
    }

    public void testConstructor_TypeArray_intArray_Chrono_6_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(33,test.getValue(1));
    }

    public void testConstructor_TypeArray_intArray_Chrono_7_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(33,test.get(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_TypeArray_intArray_Chrono_8_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfYear()));
    }

    public void testConstructor_TypeArray_intArray_Chrono_9_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Arrays.equals(test.getFieldTypes(),types));
    }

    public void testConstructor_TypeArray_intArray_Chrono_10_oe() throws Throwable {
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.dayOfYear()
        };
        int[] values = new int[] {2005, 33};
        Partial test = new Partial(types, values, GREGORIAN_PARIS);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Arrays.equals(test.getValues(),values));
    }

    public void testConstructor_Partial_1_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        assertEquals(GREGORIAN_UTC,test.getChronology());
    }

    public void testConstructor_Partial_2_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        assertEquals(3,test.size());
    }

    public void testConstructor_Partial_3_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.getValue(0));
    }

    public void testConstructor_Partial_4_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2005,test.get(DateTimeFieldType.year()));
    }

    public void testConstructor_Partial_5_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.year()));
    }

    public void testConstructor_Partial_6_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.getValue(1));
    }

    public void testConstructor_Partial_7_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6,test.get(DateTimeFieldType.monthOfYear()));
    }

    public void testConstructor_Partial_8_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.monthOfYear()));
    }

    public void testConstructor_Partial_9_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(25,test.getValue(2));
    }

    public void testConstructor_Partial_10_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(25,test.get(DateTimeFieldType.dayOfMonth()));
    }

    public void testConstructor_Partial_11_oe() throws Throwable {
        YearMonthDay ymd = new YearMonthDay(2005, 6, 25, GREGORIAN_PARIS);
        Partial test = new Partial(ymd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,test.isSupported(DateTimeFieldType.dayOfMonth()));
    }

    public void testConstructorEx1_Type_int_2_oe_1_oe() throws Throwable {
        try {
            new Partial(null, 4);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "must not be null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx_Type_int_Chrono_2_oe_1_oe() throws Throwable {
        try {
            new Partial(null, 4, ISO_UTC);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "must not be null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx1_TypeArray_intArray_2_oe_1_oe() throws Throwable {
        try {
            new Partial((DateTimeFieldType[]) null, new int[] {1});
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "must not be null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx3_TypeArray_intArray_2_oe_1_oe() throws Throwable {
        try {
            new Partial(new DateTimeFieldType[] {DateTimeFieldType.dayOfYear()}, null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "must not be null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx5_TypeArray_intArray_2_oe_1_oe() throws Throwable {
        try {
            new Partial(new DateTimeFieldType[] {DateTimeFieldType.dayOfYear()}, new int[2]);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "same length";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx6_TypeArray_intArray_2_oe_1_oe() throws Throwable {
        try {
            new Partial(new DateTimeFieldType[] {null, DateTimeFieldType.dayOfYear()}, new int[2]);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "contain null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx6_TypeArray_intArray_4_oe_1_oe() throws Throwable {
        try {
            new Partial(new DateTimeFieldType[] {null, DateTimeFieldType.dayOfYear()}, new int[2]);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        try {
            new Partial(new DateTimeFieldType[] {DateTimeFieldType.dayOfYear(), null}, new int[2]);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "contain null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx9_TypeArray_intArray_2_oe_1_oe() throws Throwable {
        int[] values = new int[] {3, 0};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfWeek()};
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "Value 0";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

    public void testConstructorEx_Partial_2_oe_1_oe() throws Throwable {
        try {
            new Partial((ReadablePartial) null);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
                        final Exception ex0 = ex;
            final String str0 = "must not be null";
            assertEquals(ex0.getMessage()+ ": " + str0,true,ex0.getMessage().indexOf(str0)>= 0);
    }
    }

public void testConstructorEx7_TypeArray_intArray_inOrder_2_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_4_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_6_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_8_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.era() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_10_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.era() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_12_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.era() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.yearOfEra(), DateTimeFieldType.year(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_14_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.era() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.yearOfEra(), DateTimeFieldType.year(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.weekyear(), DateTimeFieldType.yearOfCentury(), DateTimeFieldType.dayOfMonth() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx7_TypeArray_intArray_inOrder_16_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.era() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.yearOfEra(), DateTimeFieldType.year(), DateTimeFieldType.dayOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.weekyear(), DateTimeFieldType.yearOfCentury(), DateTimeFieldType.dayOfMonth() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.weekyear(), DateTimeFieldType.year(), DateTimeFieldType.dayOfMonth() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must be in order","largest-smallest");
}
}

public void testConstructorEx8_TypeArray_intArray_duplicate_2_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.year(), DateTimeFieldType.year() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must not","duplicate");
}
}

public void testConstructorEx8_TypeArray_intArray_duplicate_4_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.year(), DateTimeFieldType.year() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must not","duplicate");
}
}

public void testConstructorEx8_TypeArray_intArray_duplicate_6_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.year(), DateTimeFieldType.year() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfMonth() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must not","duplicate");
}
}

public void testConstructorEx8_TypeArray_intArray_duplicate_8_oe() throws Throwable {
        int[] values = new int[] {1, 1, 1};
        DateTimeFieldType[] types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.year(), DateTimeFieldType.year() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.era(), DateTimeFieldType.era(), DateTimeFieldType.monthOfYear() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.dayOfMonth() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            // removed other assertion
        }
        
        types = new DateTimeFieldType[] {
            DateTimeFieldType.dayOfMonth(), DateTimeFieldType.clockhourOfDay(), DateTimeFieldType.hourOfDay() };
        try {
            new Partial(types, values);
            // removed other assertion
        } catch (IllegalArgumentException ex) {
            assertMessageContains(ex,"must not","duplicate");
}
}

}
