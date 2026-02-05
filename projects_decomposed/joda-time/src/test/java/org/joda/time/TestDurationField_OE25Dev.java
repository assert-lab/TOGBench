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

import org.joda.time.chrono.ISOChronology;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * This class is a Junit unit test for DurationField.
 *
 * @author Stephen Colebourne
 */
public class TestDurationField_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestDurationField_OE25Dev.class);
    }

    public TestDurationField_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------

public void test_subtract_1_oe() throws Exception {
        DurationField fld = ISOChronology.getInstanceUTC().millis();
        assertEquals(900,fld.subtract(1000L,100));
    }

public void test_subtract_2_oe() throws Exception {
        DurationField fld = ISOChronology.getInstanceUTC().millis();
        // removed other assertion
        assertEquals(900L,fld.subtract(1000L,100L));
    }

public void test_subtract_3_oe() throws Exception {
        DurationField fld = ISOChronology.getInstanceUTC().millis();
        // removed other assertion
        // removed other assertion
        assertEquals((1000L - Integer.MAX_VALUE),fld.subtract(1000L,Integer.MAX_VALUE));
    }

public void test_subtract_4_oe() throws Exception {
        DurationField fld = ISOChronology.getInstanceUTC().millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((1000L - Integer.MIN_VALUE),fld.subtract(1000L,Integer.MIN_VALUE));
    }

public void test_subtract_5_oe() throws Exception {
        DurationField fld = ISOChronology.getInstanceUTC().millis();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals((1000L - Long.MAX_VALUE),fld.subtract(1000L,Long.MAX_VALUE));
    }

}
