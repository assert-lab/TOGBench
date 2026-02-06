/*
 *  Copyright 2001-2005 Stephen Colebourne
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.chrono.ISOChronology;
/**
 * This class is a Junit unit test for the
 * org.joda.time.DateTimeComparator class.
 *
 * @author Guy Allard
 */
public class TestDateTimeComparator_OE25Dev extends TestCase {

    private static final Chronology ISO = ISOChronology.getInstance();
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDateTimeComparator_OE25Dev.class);
    }

    public TestDateTimeComparator_OE25Dev(String name) {
        super(name);
    }

    /**
     * A reference to a DateTime object.
     */
    DateTime aDateTime = null;
    /**
     * A reference to a DateTime object.
     */
    DateTime bDateTime = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for millis of seconds.
     */
    Comparator cMillis = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for seconds.
     */
    Comparator cSecond = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for minutes.
     */
    Comparator cMinute = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for hours.
     */
    Comparator cHour = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for day of the week.
     */
    Comparator cDayOfWeek = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for day of the month.
     */
    Comparator cDayOfMonth = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for day of the year.
     */
    Comparator cDayOfYear = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for week of the weekyear.
     */
    Comparator cWeekOfWeekyear = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for year given a week of the year.
     */
    Comparator cWeekyear = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for months.
     */
    Comparator cMonth = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for year.
     */
    Comparator cYear = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for the date portion of an
     * object.
     */
    Comparator cDate = null;
    /**
     * A reference to a DateTimeComparator object
     * (a Comparator) for the time portion of an
     * object.
     */
    Comparator cTime = null;
    /**
     * Junit <code>setUp()</code> method.
     */
    @Override
    public void setUp() /* throws Exception */ {
        Chronology chrono = ISOChronology.getInstanceUTC();

        // super.setUp();
        // Obtain comparator's
        cMillis = DateTimeComparator.getInstance(null, DateTimeFieldType.secondOfMinute());
        cSecond = DateTimeComparator.getInstance(DateTimeFieldType.secondOfMinute(), DateTimeFieldType.minuteOfHour());
        cMinute = DateTimeComparator.getInstance(DateTimeFieldType.minuteOfHour(), DateTimeFieldType.hourOfDay());
        cHour = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        cDayOfWeek = DateTimeComparator.getInstance(DateTimeFieldType.dayOfWeek(), DateTimeFieldType.weekOfWeekyear());
        cDayOfMonth = DateTimeComparator.getInstance(DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear());
        cDayOfYear = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
        cWeekOfWeekyear = DateTimeComparator.getInstance(DateTimeFieldType.weekOfWeekyear(), DateTimeFieldType.weekyear());
        cWeekyear = DateTimeComparator.getInstance(DateTimeFieldType.weekyear());
        cMonth = DateTimeComparator.getInstance(DateTimeFieldType.monthOfYear(), DateTimeFieldType.year());
        cYear = DateTimeComparator.getInstance(DateTimeFieldType.year());
        cDate = DateTimeComparator.getDateOnlyInstance();
        cTime = DateTimeComparator.getTimeOnlyInstance();
    }

    /**
     * Junit <code>tearDown()</code> method.
     */
    @Override
    protected void tearDown() /* throws Exception */ {
        // super.tearDown();
        aDateTime = null;
        bDateTime = null;
        //
        cMillis = null;
        cSecond = null;
        cMinute = null;
        cHour = null;
        cDayOfWeek = null;
        cDayOfMonth = null;
        cDayOfYear = null;
        cWeekOfWeekyear = null;
        cWeekyear = null;
        cMonth = null;
        cYear = null;
        cDate = null;
        cTime = null;
    }

    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------
    
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    /**
     * Test all basic comparator operation with DateTime objects.
     */


    /**
     * Test all basic comparator operation with ReadableInstant objects.
     */

    /**
     * Test all basic comparator operation with java Date objects.
     */

    /**
     * Test all basic comparator operation with Long objects.
     */

    /**
     * Test all basic comparator operation with Calendar objects.
     */


    /**
     * Test unequal comparisons with millis of second comparators.
     */

    /**
     * Test unequal comparisons with second comparators.
     */

    /**
     * Test unequal comparisons with minute comparators.
     */

    /**
     * Test unequal comparisons with hour comparators.
     */

    /**
     * Test unequal comparisons with day of week comparators.
     */

    /**
     * Test unequal comparisons with day of month comparators.
     */

    /**
     * Test unequal comparisons with day of year comparators.
     */

    /**
     * Test unequal comparisons with week of weekyear comparators.
     */

    /**
     * Test unequal comparisons with year given the week comparators.
     */

    /**
     * Test unequal comparisons with month comparators.
     */

    /**
     * Test unequal comparisons with year comparators.
     */

    /*
     * 'List' processing tests follow.
     */

     /**
      * Test sorting with full default comparator.
      */

     /**
      * Test sorting with millis of second comparator.
      */


     /**
      * Test sorting with second comparator.
      */

     /**
      * Test sorting with minute comparator.
      */

     /**
      * Test sorting with hour comparator.
      */


     /**
      * Test sorting with day of week comparator.
      */

     /**
      * Test sorting with day of month comparator.
      */

     /**
      * Test sorting with day of year comparator.
      */

     /**
      * Test sorting with week of weekyear comparator.
      */

     /**
      * Test sorting with year (given week) comparator.
      */


     /**
      * Test sorting with month comparator.
      */

     /**
      * Test sorting with year comparator.
      */

     /**
      * Test sorting with date only comparator.
      */

     /**
      * Test sorting with time only comparator.
      */


    /**
     * Test comparator operation with null object(s).
     */

    /**
     * Test comparator operation with an invalid object type.
     */
    public void testInvalidObj() {
        aDateTime = getADate("2000-01-01T00:00:00");
        try {
            cYear.compare("FreeBird", aDateTime);
            fail("Invalid object failed");
        } catch (IllegalArgumentException cce) {}
    }

    // private convenience methods
    //-----------------------------------------------------------------------
    /**
     * Creates a date to test with.
     */
    private DateTime getADate(String s) {
        DateTime retDT = null;
        try {
            retDT = new DateTime(s, DateTimeZone.UTC);
        } catch (IllegalArgumentException pe) {
            pe.printStackTrace();
        }
        return retDT;
    }

    /**
     * Load a string array.
     */
    private List loadAList(String[] someStrs) {
        List newList = new ArrayList();
        try {
            for (int i = 0; i < someStrs.length; ++i) {
                newList.add(new DateTime(someStrs[i], DateTimeZone.UTC));
            } // end of the for
        } catch (IllegalArgumentException pe) {
            pe.printStackTrace();
        }
        return newList;
    }

    /**
     * Check if the list is sorted.
     */
    private boolean isListSorted(List tl) {
        // tl must be populated with DateTime objects.
        DateTime lhDT = (DateTime)tl.get(0);
        DateTime rhDT = null;
        Long lhVal = new Long( lhDT.getMillis() );
        Long rhVal = null;
        for (int i = 1; i < tl.size(); ++i) {
            rhDT = (DateTime)tl.get(i);
            rhVal = new Long( rhDT.getMillis() );
            if ( lhVal.compareTo( rhVal) > 0 ) return false;
            //
            lhVal = rhVal;  // swap for next iteration
            lhDT = rhDT;    // swap for next iteration
        }
        return true;
    }

    public void testClass_1_oe() {
        assertEquals(true,Modifier.isPublic(DateTimeComparator.class.getModifiers()));
    }

    public void testClass_2_oe() {
        // removed other assertion
        assertEquals(false,Modifier.isFinal(DateTimeComparator.class.getModifiers()));
    }

    public void testClass_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(1,DateTimeComparator.class.getDeclaredConstructors().length);
    }

    public void testClass_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,Modifier.isProtected(DateTimeComparator.class.getDeclaredConstructors()[0].getModifiers()));
    }

    public void testStaticGetInstance_1_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance();
        assertEquals(null,c.getLowerLimit());
    }

    public void testStaticGetInstance_2_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance();
        // removed other assertion
        assertEquals(null,c.getUpperLimit());
    }

    public void testStaticGetInstance_3_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        assertEquals("DateTimeComparator[]",c.toString());
    }

    public void testStaticGetDateOnlyInstance_1_oe() {
        DateTimeComparator c = DateTimeComparator.getDateOnlyInstance();
        assertEquals(DateTimeFieldType.dayOfYear(),c.getLowerLimit());
    }

    public void testStaticGetDateOnlyInstance_2_oe() {
        DateTimeComparator c = DateTimeComparator.getDateOnlyInstance();
        // removed other assertion
        assertEquals(null,c.getUpperLimit());
    }

    public void testStaticGetDateOnlyInstance_3_oe() {
        DateTimeComparator c = DateTimeComparator.getDateOnlyInstance();
        // removed other assertion
        // removed other assertion
        assertEquals("DateTimeComparator[dayOfYear-]",c.toString());
    }

    public void testStaticGetDateOnlyInstance_4_oe() {
        DateTimeComparator c = DateTimeComparator.getDateOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertSame(DateTimeComparator.getDateOnlyInstance(),DateTimeComparator.getDateOnlyInstance());
    }

    public void testStaticGetTimeOnlyInstance_1_oe() {
        DateTimeComparator c = DateTimeComparator.getTimeOnlyInstance();
        assertEquals(null,c.getLowerLimit());
    }

    public void testStaticGetTimeOnlyInstance_2_oe() {
        DateTimeComparator c = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        assertEquals(DateTimeFieldType.dayOfYear(),c.getUpperLimit());
    }

    public void testStaticGetTimeOnlyInstance_3_oe() {
        DateTimeComparator c = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        assertEquals("DateTimeComparator[-dayOfYear]",c.toString());
    }

    public void testStaticGetTimeOnlyInstance_4_oe() {
        DateTimeComparator c = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertSame(DateTimeComparator.getTimeOnlyInstance(),DateTimeComparator.getTimeOnlyInstance());
    }

    public void testStaticGetInstanceLower_1_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay());
        assertEquals(DateTimeFieldType.hourOfDay(),c.getLowerLimit());
    }

    public void testStaticGetInstanceLower_2_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay());
        // removed other assertion
        assertEquals(null,c.getUpperLimit());
    }

    public void testStaticGetInstanceLower_3_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay());
        // removed other assertion
        // removed other assertion
        assertEquals("DateTimeComparator[hourOfDay-]",c.toString());
    }

    public void testStaticGetInstanceLower_4_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(null);
        assertSame(DateTimeComparator.getInstance(),c);
    }

    public void testStaticGetInstanceLowerUpper_1_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        assertEquals(DateTimeFieldType.hourOfDay(),c.getLowerLimit());
    }

    public void testStaticGetInstanceLowerUpper_2_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        assertEquals(DateTimeFieldType.dayOfYear(),c.getUpperLimit());
    }

    public void testStaticGetInstanceLowerUpper_3_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        assertEquals("DateTimeComparator[hourOfDay-dayOfYear]",c.toString());
    }

    public void testStaticGetInstanceLowerUpper_4_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
        assertEquals(DateTimeFieldType.hourOfDay(),c.getLowerLimit());
    }

    public void testStaticGetInstanceLowerUpper_5_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
        // removed other assertion
        assertEquals(DateTimeFieldType.hourOfDay(),c.getUpperLimit());
    }

    public void testStaticGetInstanceLowerUpper_6_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
        // removed other assertion
        // removed other assertion
        assertEquals("DateTimeComparator[hourOfDay]",c.toString());
    }

    public void testStaticGetInstanceLowerUpper_7_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(null, null);
        assertSame(DateTimeComparator.getInstance(),c);
    }

    public void testStaticGetInstanceLowerUpper_8_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(null, null);
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), null);
        assertSame(DateTimeComparator.getDateOnlyInstance(),c);
    }

    public void testStaticGetInstanceLowerUpper_9_oe() {
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.hourOfDay());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        c = DateTimeComparator.getInstance(null, null);
        // removed other assertion
        
        c = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), null);
        // removed other assertion
        
        c = DateTimeComparator.getInstance(null, DateTimeFieldType.dayOfYear());
        assertSame(DateTimeComparator.getTimeOnlyInstance(),c);
    }

    public void testNullNowCheckedOnce_1_oe() {
        // checks a race condition against the system clock, issue #404
        for (int i = 0; i < 10000; i++) {
            if (DateTimeComparator.getInstance().compare(null, null) != 0) {
                fail("Comparing (null, null) should always return 0");
    }
    }
    }

    public void testEqualsHashCode_1_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        assertEquals(true,c1.equals(c1));
    }

    public void testEqualsHashCode_2_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        assertEquals(false,c1.equals(null));
    }

    public void testEqualsHashCode_3_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        assertEquals(true,c1.hashCode()== c1.hashCode());
    }

    public void testEqualsHashCode_4_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        assertEquals(true,c2.equals(c2));
    }

    public void testEqualsHashCode_5_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        assertEquals(false,c2.equals(c1));
    }

    public void testEqualsHashCode_6_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        assertEquals(false,c1.equals(c2));
    }

    public void testEqualsHashCode_7_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,c2.equals(null));
    }

    public void testEqualsHashCode_8_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,c1.hashCode()== c2.hashCode());
    }

    public void testEqualsHashCode_9_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        assertEquals(true,c3.equals(c3));
    }

    public void testEqualsHashCode_10_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        assertEquals(false,c3.equals(c1));
    }

    public void testEqualsHashCode_11_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        assertEquals(true,c3.equals(c2));
    }

    public void testEqualsHashCode_12_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,c1.equals(c3));
    }

    public void testEqualsHashCode_13_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,c2.equals(c3));
    }

    public void testEqualsHashCode_14_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(false,c1.hashCode()== c3.hashCode());
    }

    public void testEqualsHashCode_15_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(true,c2.hashCode()== c3.hashCode());
    }

    public void testEqualsHashCode_16_oe() {
        DateTimeComparator c1 = DateTimeComparator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c2 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c3 = DateTimeComparator.getTimeOnlyInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        DateTimeComparator c4 = DateTimeComparator.getDateOnlyInstance();
        assertEquals(false,c4.hashCode()== c3.hashCode());
    }

    public void testSerialization1_1_oe() throws Exception {
        DateTimeField f = ISO.dayOfYear();
        f.toString();
        DateTimeComparator c = DateTimeComparator.getInstance(DateTimeFieldType.hourOfDay(), DateTimeFieldType.dayOfYear());
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(c);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeComparator result = (DateTimeComparator) ois.readObject();
        ois.close();
        
        assertEquals(c,result);
    }

    public void testSerialization2_1_oe() throws Exception {
        DateTimeComparator c = DateTimeComparator.getInstance();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(c);
        oos.close();
        byte[] bytes = baos.toByteArray();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        DateTimeComparator result = (DateTimeComparator) ois.readObject();
        ois.close();
        
        assertSame(c,result);
    }

    public void testBasicComps1_1_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        assertEquals("getMillis",aDateTime.getMillis(),bDateTime.getMillis());
    }

    public void testBasicComps1_2_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        assertEquals("MILLIS",0,cMillis.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_3_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        assertEquals("SECOND",0,cSecond.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_4_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MINUTE",0,cMinute.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_5_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("HOUR",0,cHour.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_6_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOW",0,cDayOfWeek.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_7_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOM",0,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_8_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOY",0,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_9_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WOW",0,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_10_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WY",0,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_11_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MONTH",0,cMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_12_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YEAR",0,cYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_13_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DATE",0,cDate.compare(aDateTime,bDateTime));
    }

    public void testBasicComps1_14_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("TIME",0,cTime.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_1_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        assertEquals("getMillis",aDateTime.getMillis(),bDateTime.getMillis());
    }

    public void testBasicComps2_2_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        assertEquals("MILLIS",0,cMillis.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_3_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        assertEquals("SECOND",0,cSecond.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_4_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MINUTE",0,cMinute.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_5_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("HOUR",0,cHour.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_6_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOW",0,cDayOfWeek.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_7_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOM",0,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_8_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOY",0,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_9_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WOW",0,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_10_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WY",0,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_11_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MONTH",0,cMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_12_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YEAR",0,cYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_13_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DATE",0,cDate.compare(aDateTime,bDateTime));
    }

    public void testBasicComps2_14_oe() {
        ReadableInstant aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        ReadableInstant bDateTime = new DateTime( aDateTime.getMillis(), DateTimeZone.UTC );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("TIME",0,cTime.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_1_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        assertEquals("MILLIS",0,cMillis.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_2_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        assertEquals("SECOND",0,cSecond.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_3_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        assertEquals("MINUTE",0,cMinute.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_4_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("HOUR",0,cHour.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_5_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOW",0,cDayOfWeek.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_6_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOM",0,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_7_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOY",0,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_8_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WOW",0,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_9_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WY",0,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_10_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MONTH",0,cMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_11_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YEAR",0,cYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_12_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DATE",0,cDate.compare(aDateTime,bDateTime));
    }

    public void testBasicComps3_13_oe() {
        Date aDateTime
            = new Date( System.currentTimeMillis() );
        Date bDateTime
            = new Date( aDateTime.getTime() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("TIME",0,cTime.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_1_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        assertEquals("MILLIS",0,cMillis.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_2_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        assertEquals("SECOND",0,cSecond.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_3_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        assertEquals("MINUTE",0,cMinute.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_4_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("HOUR",0,cHour.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_5_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOW",0,cDayOfWeek.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_6_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOM",0,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_7_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOY",0,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_8_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WOW",0,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_9_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WY",0,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_10_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MONTH",0,cMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_11_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YEAR",0,cYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_12_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DATE",0,cDate.compare(aDateTime,bDateTime));
    }

    public void testBasicComps4_13_oe() {
        Long aDateTime
            = new Long( System.currentTimeMillis() );
        Long bDateTime
            = new Long( aDateTime.longValue() );
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("TIME",0,cTime.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_1_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        assertEquals("MILLIS",0,cMillis.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_2_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        assertEquals("SECOND",0,cSecond.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_3_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        assertEquals("MINUTE",0,cMinute.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_4_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("HOUR",0,cHour.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_5_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOW",0,cDayOfWeek.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_6_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOM",0,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_7_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DOY",0,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_8_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WOW",0,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_9_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("WY",0,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_10_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("MONTH",0,cMonth.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_11_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("YEAR",0,cYear.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_12_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("DATE",0,cDate.compare(aDateTime,bDateTime));
    }

    public void testBasicComps5_13_oe() {
        Calendar aDateTime
            = Calendar.getInstance();   // right now
        Calendar bDateTime = aDateTime;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("TIME",0,cTime.compare(aDateTime,bDateTime));
    }

    public void testMillis_1_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis() + 1, DateTimeZone.UTC );
        assertEquals("MillisM1",-1,cMillis.compare(aDateTime,bDateTime));
    }

    public void testMillis_2_oe() {
        aDateTime = new DateTime( System.currentTimeMillis(), DateTimeZone.UTC );
        bDateTime = new DateTime( aDateTime.getMillis() + 1, DateTimeZone.UTC );
        // removed other assertion
        assertEquals("MillisP1",1,cMillis.compare(bDateTime,aDateTime));
    }

    public void testSecond_1_oe() {
        aDateTime = getADate( "1969-12-31T23:59:58" );
        bDateTime = getADate( "1969-12-31T23:50:59" );
        assertEquals("SecondM1a",-1,cSecond.compare(aDateTime,bDateTime));
    }

    public void testSecond_2_oe() {
        aDateTime = getADate( "1969-12-31T23:59:58" );
        bDateTime = getADate( "1969-12-31T23:50:59" );
        // removed other assertion
        assertEquals("SecondP1a",1,cSecond.compare(bDateTime,aDateTime));
    }

    public void testSecond_3_oe() {
        aDateTime = getADate( "1969-12-31T23:59:58" );
        bDateTime = getADate( "1969-12-31T23:50:59" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T00:00:01" );
        assertEquals("SecondM1b",-1,cSecond.compare(aDateTime,bDateTime));
    }

    public void testSecond_4_oe() {
        aDateTime = getADate( "1969-12-31T23:59:58" );
        bDateTime = getADate( "1969-12-31T23:50:59" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T00:00:01" );
        // removed other assertion
        assertEquals("SecondP1b",1,cSecond.compare(bDateTime,aDateTime));
    }

    public void testMinute_1_oe() {
        aDateTime = getADate( "1969-12-31T23:58:00" );
        bDateTime = getADate( "1969-12-31T23:59:00" );
        assertEquals("MinuteM1a",-1,cMinute.compare(aDateTime,bDateTime));
    }

    public void testMinute_2_oe() {
        aDateTime = getADate( "1969-12-31T23:58:00" );
        bDateTime = getADate( "1969-12-31T23:59:00" );
        // removed other assertion
        assertEquals("MinuteP1a",1,cMinute.compare(bDateTime,aDateTime));
    }

    public void testMinute_3_oe() {
        aDateTime = getADate( "1969-12-31T23:58:00" );
        bDateTime = getADate( "1969-12-31T23:59:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T00:01:00" );
        assertEquals("MinuteM1b",-1,cMinute.compare(aDateTime,bDateTime));
    }

    public void testMinute_4_oe() {
        aDateTime = getADate( "1969-12-31T23:58:00" );
        bDateTime = getADate( "1969-12-31T23:59:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T00:01:00" );
        // removed other assertion
        assertEquals("MinuteP1b",1,cMinute.compare(bDateTime,aDateTime));
    }

    public void testHour_1_oe() {
        aDateTime = getADate( "1969-12-31T22:00:00" );
        bDateTime = getADate( "1969-12-31T23:00:00" );
        assertEquals("HourM1a",-1,cHour.compare(aDateTime,bDateTime));
    }

    public void testHour_2_oe() {
        aDateTime = getADate( "1969-12-31T22:00:00" );
        bDateTime = getADate( "1969-12-31T23:00:00" );
        // removed other assertion
        assertEquals("HourP1a",1,cHour.compare(bDateTime,aDateTime));
    }

    public void testHour_3_oe() {
        aDateTime = getADate( "1969-12-31T22:00:00" );
        bDateTime = getADate( "1969-12-31T23:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T01:00:00" );
        assertEquals("HourM1b",-1,cHour.compare(aDateTime,bDateTime));
    }

    public void testHour_4_oe() {
        aDateTime = getADate( "1969-12-31T22:00:00" );
        bDateTime = getADate( "1969-12-31T23:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T01:00:00" );
        // removed other assertion
        assertEquals("HourP1b",1,cHour.compare(bDateTime,aDateTime));
    }

    public void testHour_5_oe() {
        aDateTime = getADate( "1969-12-31T22:00:00" );
        bDateTime = getADate( "1969-12-31T23:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T01:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1969-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        assertEquals("HourP1c",1,cHour.compare(aDateTime,bDateTime));
    }

    public void testHour_6_oe() {
        aDateTime = getADate( "1969-12-31T22:00:00" );
        bDateTime = getADate( "1969-12-31T23:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1970-01-01T00:00:00" );
        bDateTime = getADate( "1970-01-01T01:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1969-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        // removed other assertion
        assertEquals("HourM1c",-1,cHour.compare(bDateTime,aDateTime));
    }

    public void testDOW_1_oe() {
        /*
         * Dates chosen when I wrote the code, so I know what day of
         * the week it is.
         */
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        assertEquals("DOWM1a",-1,cDayOfWeek.compare(aDateTime,bDateTime));
    }

    public void testDOW_2_oe() {
        /*
         * Dates chosen when I wrote the code, so I know what day of
         * the week it is.
         */
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        assertEquals("DOWP1a",1,cDayOfWeek.compare(bDateTime,aDateTime));
    }

    public void testDOM_1_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        assertEquals("DOMM1a",-1,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testDOM_2_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        assertEquals("DOMP1a",1,cDayOfMonth.compare(bDateTime,aDateTime));
    }

    public void testDOM_3_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "2000-12-01T00:00:00" );
        bDateTime = getADate( "1814-04-30T00:00:00" );
        assertEquals("DOMM1b",-1,cDayOfMonth.compare(aDateTime,bDateTime));
    }

    public void testDOM_4_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "2000-12-01T00:00:00" );
        bDateTime = getADate( "1814-04-30T00:00:00" );
        // removed other assertion
        assertEquals("DOMP1b",1,cDayOfMonth.compare(bDateTime,aDateTime));
    }

    public void testDOY_1_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        assertEquals("DOYM1a",-1,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testDOY_2_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        assertEquals("DOYP1a",1,cDayOfYear.compare(bDateTime,aDateTime));
    }

    public void testDOY_3_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "2000-02-29T00:00:00" );
        bDateTime = getADate( "1814-11-30T00:00:00" );
        assertEquals("DOYM1b",-1,cDayOfYear.compare(aDateTime,bDateTime));
    }

    public void testDOY_4_oe() {
        aDateTime = getADate( "2002-04-12T00:00:00" );
        bDateTime = getADate( "2002-04-13T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "2000-02-29T00:00:00" );
        bDateTime = getADate( "1814-11-30T00:00:00" );
        // removed other assertion
        assertEquals("DOYP1b",1,cDayOfYear.compare(bDateTime,aDateTime));
    }

    public void testWOW_1_oe() {
        // 1st week of year contains Jan 04.
        aDateTime = getADate( "2000-01-04T00:00:00" );
        bDateTime = getADate( "2000-01-11T00:00:00" );
        assertEquals("WOWM1a",-1,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testWOW_2_oe() {
        // 1st week of year contains Jan 04.
        aDateTime = getADate( "2000-01-04T00:00:00" );
        bDateTime = getADate( "2000-01-11T00:00:00" );
        // removed other assertion
        assertEquals("WOWP1a",1,cWeekOfWeekyear.compare(bDateTime,aDateTime));
    }

    public void testWOW_3_oe() {
        // 1st week of year contains Jan 04.
        aDateTime = getADate( "2000-01-04T00:00:00" );
        bDateTime = getADate( "2000-01-11T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "2000-01-04T00:00:00" );
        bDateTime = getADate( "1999-12-31T00:00:00" );
        assertEquals("WOWM1b",-1,cWeekOfWeekyear.compare(aDateTime,bDateTime));
    }

    public void testWOW_4_oe() {
        // 1st week of year contains Jan 04.
        aDateTime = getADate( "2000-01-04T00:00:00" );
        bDateTime = getADate( "2000-01-11T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "2000-01-04T00:00:00" );
        bDateTime = getADate( "1999-12-31T00:00:00" );
        // removed other assertion
        assertEquals("WOWP1b",1,cWeekOfWeekyear.compare(bDateTime,aDateTime));
    }

    public void testWOYY_1_oe() {
        // How do I test the end conditions of this?
        // Don't understand ......
        aDateTime = getADate( "1998-12-31T23:59:59" );
        bDateTime = getADate( "1999-01-01T00:00:00" );
        assertEquals("YOYYZ",0,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testWOYY_2_oe() {
        // How do I test the end conditions of this?
        // Don't understand ......
        aDateTime = getADate( "1998-12-31T23:59:59" );
        bDateTime = getADate( "1999-01-01T00:00:00" );
        // removed other assertion
        bDateTime = getADate( "1999-01-04T00:00:00" );
        assertEquals("YOYYM1",-1,cWeekyear.compare(aDateTime,bDateTime));
    }

    public void testWOYY_3_oe() {
        // How do I test the end conditions of this?
        // Don't understand ......
        aDateTime = getADate( "1998-12-31T23:59:59" );
        bDateTime = getADate( "1999-01-01T00:00:00" );
        // removed other assertion
        bDateTime = getADate( "1999-01-04T00:00:00" );
        // removed other assertion
        assertEquals("YOYYP1",1,cWeekyear.compare(bDateTime,aDateTime));
    }

    public void testMonth_1_oe() {
        aDateTime = getADate( "2002-04-30T00:00:00" );
        bDateTime = getADate( "2002-05-01T00:00:00" );
        assertEquals("MONTHM1a",-1,cMonth.compare(aDateTime,bDateTime));
    }

    public void testMonth_2_oe() {
        aDateTime = getADate( "2002-04-30T00:00:00" );
        bDateTime = getADate( "2002-05-01T00:00:00" );
        // removed other assertion
        assertEquals("MONTHP1a",1,cMonth.compare(bDateTime,aDateTime));
    }

    public void testMonth_3_oe() {
        aDateTime = getADate( "2002-04-30T00:00:00" );
        bDateTime = getADate( "2002-05-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1900-01-01T00:00:00" );
        bDateTime = getADate( "1899-12-31T00:00:00" );
        assertEquals("MONTHM1b",-1,cMonth.compare(aDateTime,bDateTime));
    }

    public void testMonth_4_oe() {
        aDateTime = getADate( "2002-04-30T00:00:00" );
        bDateTime = getADate( "2002-05-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1900-01-01T00:00:00" );
        bDateTime = getADate( "1899-12-31T00:00:00" );
        // removed other assertion
        assertEquals("MONTHP1b",1,cMonth.compare(bDateTime,aDateTime));
    }

    public void testYear_1_oe() {
        aDateTime = getADate( "2000-01-01T00:00:00" );
        bDateTime = getADate( "2001-01-01T00:00:00" );
        assertEquals("YEARM1a",-1,cYear.compare(aDateTime,bDateTime));
    }

    public void testYear_2_oe() {
        aDateTime = getADate( "2000-01-01T00:00:00" );
        bDateTime = getADate( "2001-01-01T00:00:00" );
        // removed other assertion
        assertEquals("YEARP1a",1,cYear.compare(bDateTime,aDateTime));
    }

    public void testYear_3_oe() {
        aDateTime = getADate( "2000-01-01T00:00:00" );
        bDateTime = getADate( "2001-01-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1968-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        assertEquals("YEARM1b",-1,cYear.compare(aDateTime,bDateTime));
    }

    public void testYear_4_oe() {
        aDateTime = getADate( "2000-01-01T00:00:00" );
        bDateTime = getADate( "2001-01-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1968-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        // removed other assertion
        assertEquals("YEARP1b",1,cYear.compare(bDateTime,aDateTime));
    }

    public void testYear_5_oe() {
        aDateTime = getADate( "2000-01-01T00:00:00" );
        bDateTime = getADate( "2001-01-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1968-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1969-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        assertEquals("YEARM1c",-1,cYear.compare(aDateTime,bDateTime));
    }

    public void testYear_6_oe() {
        aDateTime = getADate( "2000-01-01T00:00:00" );
        bDateTime = getADate( "2001-01-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1968-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        // removed other assertion
        // removed other assertion
        aDateTime = getADate( "1969-12-31T23:59:59" );
        bDateTime = getADate( "1970-01-01T00:00:00" );
        // removed other assertion
        assertEquals("YEARP1c",1,cYear.compare(bDateTime,aDateTime));
    }

     public void testListBasic_1_oe() {
        String[] dtStrs = {
            "1999-02-01T00:00:00",
            "1998-01-20T00:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListBasic",!isSorted1,isSorted2);
     }

    public void testListMillis_1_oe() {
        //
        List sl = new ArrayList();
        long base = 12345L * 1000L;
        sl.add( new DateTime( base + 999L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 222L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 456L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 888L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 123L, DateTimeZone.UTC ) );
        sl.add( new DateTime( base + 000L, DateTimeZone.UTC ) );
        //
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cMillis );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListLillis",!isSorted1,isSorted2);
    }

    public void testListSecond_1_oe() {
        String[] dtStrs = {
            "1999-02-01T00:00:10",
            "1999-02-01T00:00:30",
            "1999-02-01T00:00:25",
            "1999-02-01T00:00:18",
            "1999-02-01T00:00:01",
            "1999-02-01T00:00:59",
            "1999-02-01T00:00:22"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cSecond );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListSecond",!isSorted1,isSorted2);
    }

    public void testListMinute_1_oe() {
        String[] dtStrs = {
            "1999-02-01T00:10:00",
            "1999-02-01T00:30:00",
            "1999-02-01T00:25:00",
            "1999-02-01T00:18:00",
            "1999-02-01T00:01:00",
            "1999-02-01T00:59:00",
            "1999-02-01T00:22:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cMinute );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListMinute",!isSorted1,isSorted2);
    }

    public void testListHour_1_oe() {
        String[] dtStrs = {
            "1999-02-01T10:00:00",
            "1999-02-01T23:00:00",
            "1999-02-01T01:00:00",
            "1999-02-01T15:00:00",
            "1999-02-01T05:00:00",
            "1999-02-01T20:00:00",
            "1999-02-01T17:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cHour );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListHour",!isSorted1,isSorted2);
    }

    public void testListDOW_1_oe() {
        String[] dtStrs = {
            /* 2002-04-15 = Monday */
            "2002-04-21T10:00:00",
            "2002-04-16T10:00:00",
            "2002-04-15T10:00:00",
            "2002-04-17T10:00:00",
            "2002-04-19T10:00:00",
            "2002-04-18T10:00:00",
            "2002-04-20T10:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cDayOfWeek );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDOW",!isSorted1,isSorted2);
    }

    public void testListDOM_1_oe() {
        String[] dtStrs = {
            /* 2002-04-14 = Sunday */
            "2002-04-20T10:00:00",
            "2002-04-16T10:00:00",
            "2002-04-15T10:00:00",
            "2002-04-17T10:00:00",
            "2002-04-19T10:00:00",
            "2002-04-18T10:00:00",
            "2002-04-14T10:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cDayOfMonth );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDOM",!isSorted1,isSorted2);
    }

    public void testListDOY_1_oe() {
        String[] dtStrs = {
            "2002-04-20T10:00:00",
            "2002-01-16T10:00:00",
            "2002-12-31T10:00:00",
            "2002-09-14T10:00:00",
            "2002-09-19T10:00:00",
            "2002-02-14T10:00:00",
            "2002-10-30T10:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cDayOfYear );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDOY",!isSorted1,isSorted2);
    }

    public void testListWOW_1_oe() {
        String[] dtStrs = {
            "2002-04-01T10:00:00",
            "2002-01-01T10:00:00",
            "2002-12-01T10:00:00",
            "2002-09-01T10:00:00",
            "2002-09-01T10:00:00",
            "2002-02-01T10:00:00",
            "2002-10-01T10:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cWeekOfWeekyear );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListWOW",!isSorted1,isSorted2);
    }

    public void testListYOYY_1_oe() {
        // ?? How to catch end conditions ??
        String[] dtStrs = {
            "2010-04-01T10:00:00",
            "2002-01-01T10:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cWeekyear );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListYOYY",!isSorted1,isSorted2);
    }

    public void testListMonth_1_oe() {
        String[] dtStrs = {
            "2002-04-01T10:00:00",
            "2002-01-01T10:00:00",
            "2002-12-01T10:00:00",
            "2002-09-01T10:00:00",
            "2002-09-01T10:00:00",
            "2002-02-01T10:00:00",
            "2002-10-01T10:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cMonth );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListMonth",!isSorted1,isSorted2);
    }

     public void testListYear_1_oe() {
        String[] dtStrs = {
            "1999-02-01T00:00:00",
            "1998-02-01T00:00:00",
            "2525-02-01T00:00:00",
            "1776-02-01T00:00:00",
            "1863-02-01T00:00:00",
            "1066-02-01T00:00:00",
            "2100-02-01T00:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cYear );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListYear",!isSorted1,isSorted2);
     }

    public void testListDate_1_oe() {
        String[] dtStrs = {
            "1999-02-01T00:00:00",
            "1998-10-03T00:00:00",
            "2525-05-20T00:00:00",
            "1776-12-25T00:00:00",
            "1863-01-31T00:00:00",
            "1066-09-22T00:00:00",
            "2100-07-04T00:00:00"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cDate );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListDate",!isSorted1,isSorted2);
    }

    public void testListTime_1_oe() {
        String[] dtStrs = {
            "1999-02-01T01:02:05",
            "1999-02-01T22:22:22",
            "1999-02-01T05:30:45",
            "1999-02-01T09:17:59",
            "1999-02-01T09:17:58",
            "1999-02-01T15:30:00",
            "1999-02-01T17:00:44"
        };
        //
        List sl = loadAList( dtStrs );
        boolean isSorted1 = isListSorted( sl );
        Collections.sort( sl, cTime );
        boolean isSorted2 = isListSorted( sl );
        assertEquals("ListTime",!isSorted1,isSorted2);
    }

    public void testNullDT_1_oe() {
        // null means now
        aDateTime = getADate("2000-01-01T00:00:00");
        assertTrue(cYear.compare(null,aDateTime)> 0);
    }

    public void testNullDT_2_oe() {
        // null means now
        aDateTime = getADate("2000-01-01T00:00:00");
        // removed other assertion
        assertTrue(cYear.compare(aDateTime,null)< 0);
    }

}
