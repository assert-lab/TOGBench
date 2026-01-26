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
package org.joda.time.format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTimeFieldType;
import org.joda.time.Partial;

/**
 * This class is a Junit unit test for ISODateTimeFormat.
 *
 * @author Stephen Colebourne
 */
public class TestISODateTimeFormat_Fields_OE25Dev extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestISODateTimeFormat_Fields_OE25Dev_OE25Dev.class);
    }

    public TestISODateTimeFormat_Fields_OE25Dev(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    //-----------------------------------------------------------------------
    public void testForFields_null() {
        try {
            ISODateTimeFormat.forFields((Collection) null, true, true);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    public void testForFields_empty() {
        try {
            ISODateTimeFormat.forFields(new ArrayList(), true, true);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testForFields_calBased_YMD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YMD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YMD_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("20050625", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YMD_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("20050625", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YMD_unmodifiable_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = Collections.unmodifiableList(new ArrayList(Arrays.asList(fields)));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_unmodifiable_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = Collections.unmodifiableList(new ArrayList(Arrays.asList(fields)));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(3, types.size());
    }

    public void testForFields_calBased_YMD_unmodifiable_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = Collections.unmodifiableList(new ArrayList(Arrays.asList(fields)));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = Arrays.asList(fields);
        f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_unmodifiable_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = Collections.unmodifiableList(new ArrayList(Arrays.asList(fields)));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = Arrays.asList(fields);
        f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(3, types.size());
    }

    public void testForFields_calBased_YMD_duplicates_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        DateTimeFieldType[] dupFields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(dupFields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_duplicates_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        DateTimeFieldType[] dupFields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(dupFields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YMD_duplicates_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        DateTimeFieldType[] dupFields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(dupFields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = Arrays.asList(dupFields);
        f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YMD_duplicates_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        DateTimeFieldType[] dupFields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6, 25};
        List types = new ArrayList(Arrays.asList(dupFields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = Arrays.asList(dupFields);
        f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(4, types.size());
    }

    public void testForFields_calBased_Y_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_Y_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_Y_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_Y_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_Y_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_Y_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_Y_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_Y_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_M_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("--06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_M_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_M_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("--06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_M_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_M_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("--06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_M_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_M_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("--06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_M_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_D_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("---25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_D_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_D_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("---25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_D_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_D_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("---25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_D_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_D_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("---25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_D_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YM_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YM_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YM_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YM_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YM_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005-06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YM_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YM_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005-06", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YM_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
        };
        int[] values = new int[] {2005, 6};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_MD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("--06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_MD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_MD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("--06-25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_MD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_MD_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("--0625", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_MD_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_MD_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("--0625", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_MD_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {6, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005--25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_calBased_YD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005--25", f.print(new Partial(fields, values)));
    }

    public void testForFields_calBased_YD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfMonth(),
        };
        int[] values = new int[] {2005, 25};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YWD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-W08-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YWD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YWD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-W08-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YWD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YWD_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005W085", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YWD_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YWD_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005W085", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YWD_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_Y_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_Y_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_Y_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_Y_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_Y_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_Y_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_Y_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_Y_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_W_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_W_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_W_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_W_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_W_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_W_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_W_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_W_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_D_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-W-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_D_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_D_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-W-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_D_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_D_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-W-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_D_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_D_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-W-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_D_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YW_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YW_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YW_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YW_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YW_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YW_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YW_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005W08", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YW_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.weekOfWeekyear(),
        };
        int[] values = new int[] {2005, 8};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_WD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-W08-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_WD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_WD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-W08-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_WD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_WD_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-W085", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_WD_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_WD_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-W085", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_WD_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekOfWeekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {8, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-W-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_weekBased_YD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005W-5", f.print(new Partial(fields, values)));
    }

    public void testForFields_weekBased_YD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.weekyear(),
                DateTimeFieldType.dayOfWeek(),
        };
        int[] values = new int[] {2005, 5};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_YD_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_YD_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_YD_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_YD_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_YD_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_YD_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_YD_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_YD_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {2005, 177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_Y_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_Y_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_Y_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_Y_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_Y_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_Y_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_Y_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_Y_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
        };
        int[] values = new int[] {2005};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_D_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_D_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_D_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_D_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_D_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_D_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_ordinalBased_D_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-177", f.print(new Partial(fields, values)));
    }

    public void testForFields_ordinalBased_D_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfYear(),
        };
        int[] values = new int[] {177};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMSm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("10:20:30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMSm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMSm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10:20:30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMSm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMSm_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("102030.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMSm_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMSm_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("102030.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMSm_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMS_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("10:20:30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMS_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMS_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10:20:30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMS_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMS_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("102030", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMS_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMS_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("102030", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMS_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HM_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("10:20", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HM_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HM_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10:20", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HM_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HM_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("1020", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HM_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HM_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("1020", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HM_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {10, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_H_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("10", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_H_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_H_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_H_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_H_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("10", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_H_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_H_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("10", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_H_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {10};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MSm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-20:30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MSm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MSm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-20:30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MSm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MSm_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-2030.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MSm_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MSm_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-2030.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MSm_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MS_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-20:30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MS_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MS_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-20:30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MS_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MS_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-2030", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MS_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_MS_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-2030", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_MS_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {20, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_M_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("-20", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_M_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_M_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-20", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_M_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_M_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("-20", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_M_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_M_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-20", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_M_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Sm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("--30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Sm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Sm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("--30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Sm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Sm_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("--30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Sm_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Sm_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("--30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Sm_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_S_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("--30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_S_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_S_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("--30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_S_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_S_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("--30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_S_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_S_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("--30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_S_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_m_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("---.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_m_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_m_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("---.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_m_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Hm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10--.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Hm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Hm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("10--.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Hm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HS_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10-30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HS_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HS_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("10-30", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HS_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
        };
        int[] values = new int[] {10, 30};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Mm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("-20-.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Mm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_Mm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("-20-.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_Mm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HSm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10-30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HSm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HSm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("10-30.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HSm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.secondOfMinute(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 30, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMm_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("10:20-.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMm_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_time_HMm_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("1020-.040", f.print(new Partial(fields, values)));
    }

    public void testForFields_time_HMm_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.hourOfDay(),
                DateTimeFieldType.minuteOfHour(),
                DateTimeFieldType.millisOfSecond(),
        };
        int[] values = new int[] {10, 20, 40};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_YMDH_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("2005-06-25T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_YMDH_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_YMDH_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005-06-25T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_YMDH_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_YMDH_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("20050625T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_YMDH_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_YMDH_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("20050625T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_YMDH_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.monthOfYear(),
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 6, 25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_DH_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        assertEquals("---25T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_DH_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_DH_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("---25T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_DH_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_DH_5_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        assertEquals("---25T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_DH_6_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_DH_7_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("---25T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_DH_8_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {25, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, true);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_YH_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("2005T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_YH_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_YH_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("2005T12", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_YH_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.year(),
                DateTimeFieldType.hourOfDay(),
        };
        int[] values = new int[] {2005, 12};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_DM_1_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {25, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        assertEquals("---25T-20", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_DM_2_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {25, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

    public void testForFields_datetime_DM_3_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {25, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        assertEquals("---25T-20", f.print(new Partial(fields, values)));
    }

    public void testForFields_datetime_DM_4_oe() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] {
                DateTimeFieldType.dayOfMonth(),
                DateTimeFieldType.minuteOfHour(),
        };
        int[] values = new int[] {25, 20};
        List types = new ArrayList(Arrays.asList(fields));
        DateTimeFormatter f = ISODateTimeFormat.forFields(types, true, false);
        // removed other assertion
        // removed other assertion
        
        types = new ArrayList(Arrays.asList(fields));
        f = ISODateTimeFormat.forFields(types, false, false);
        // removed other assertion
        assertEquals(0, types.size());
    }

}
