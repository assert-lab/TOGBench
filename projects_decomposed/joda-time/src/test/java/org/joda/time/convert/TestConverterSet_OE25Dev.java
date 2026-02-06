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
package org.joda.time.convert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.ReadWritableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;

/**
 * This class is a JUnit test for ConverterSet.
 * Mostly for coverage.
 *
 * @author Stephen Colebourne
 */
public class TestConverterSet_OE25Dev extends TestCase {

    private static final Converter c1 = new Converter() {
        public Class getSupportedType() {return Boolean.class;}
    };
    private static final Converter c2 = new Converter() {
        public Class getSupportedType() {return Character.class;}
    };
    private static final Converter c3 = new Converter() {
        public Class getSupportedType() {return Byte.class;}
    };
    private static final Converter c4 = new Converter() {
        public Class getSupportedType() {return Short.class;}
    };
    private static final Converter c4a = new Converter() {
        public Class getSupportedType() {return Short.class;}
    };
    private static final Converter c5 = new Converter() {
        public Class getSupportedType() {return Integer.class;}
    };
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static TestSuite suite() {
        return new TestSuite(TestConverterSet_OE25Dev_OE25Dev.class);
    }

    public TestConverterSet_OE25Dev(String name) {
        super(name);
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    public void testClass_1_oe() throws Exception {
        Class cls = ConverterSet.class;
        assertEquals(false,Modifier.isPublic(cls.getModifiers()));
    }

    public void testClass_2_oe() throws Exception {
        Class cls = ConverterSet.class;
        // removed other assertion
        assertEquals(false,Modifier.isProtected(cls.getModifiers()));
    }

    public void testClass_3_oe() throws Exception {
        Class cls = ConverterSet.class;
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(cls.getModifiers()));
    }

    public void testClass_4_oe() throws Exception {
        Class cls = ConverterSet.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        assertEquals(1,cls.getDeclaredConstructors().length);
    }

    public void testClass_5_oe() throws Exception {
        Class cls = ConverterSet.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        assertEquals(false,Modifier.isPublic(con.getModifiers()));
    }

    public void testClass_6_oe() throws Exception {
        Class cls = ConverterSet.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        // removed other assertion
        assertEquals(false,Modifier.isProtected(con.getModifiers()));
    }

    public void testClass_7_oe() throws Exception {
        Class cls = ConverterSet.class;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        
        // removed other assertion
        Constructor con = cls.getDeclaredConstructors()[0];
        // removed other assertion
        // removed other assertion
        assertEquals(false,Modifier.isPrivate(con.getModifiers()));
    }

    public void testBigHashtable_1_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        set.select(Boolean.class);
        set.select(Character.class);
        set.select(Byte.class);
        set.select(Short.class);
        set.select(Integer.class);
        set.select(Long.class);
        set.select(Float.class);
        set.select(Double.class);
        set.select(null);
        set.select(Calendar.class);
        set.select(GregorianCalendar.class);
        set.select(DateTime.class);
        set.select(DateMidnight.class);
        set.select(ReadableInstant.class);
        set.select(ReadableDateTime.class);
        set.select(ReadWritableInstant.class);  // 16
        set.select(ReadWritableDateTime.class);
        set.select(DateTime.class);
        assertEquals(4,set.size());
    }

    public void testAddNullRemoved1_1_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.add(c5, null);
        assertEquals(4,set.size());
    }

    public void testAddNullRemoved1_2_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.add(c5, null);
        // removed other assertion
        assertEquals(5,result.size());
    }

    public void testAddNullRemoved2_1_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.add(c4, null);
        assertSame(set,result);
    }

    public void testAddNullRemoved3_1_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.add(c4a, null);
        assertTrue(set != result);
    }

    public void testAddNullRemoved3_2_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.add(c4a, null);
        // removed other assertion
        assertEquals(4,set.size());
    }

    public void testAddNullRemoved3_3_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.add(c4a, null);
        // removed other assertion
        // removed other assertion
        assertEquals(4,result.size());
    }

    public void testRemoveNullRemoved1_1_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.remove(c3, null);
        assertEquals(4,set.size());
    }

    public void testRemoveNullRemoved1_2_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.remove(c3, null);
        // removed other assertion
        assertEquals(3,result.size());
    }

    public void testRemoveNullRemoved2_1_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        ConverterSet result = set.remove(c5, null);
        assertSame(set,result);
    }

    public void testRemoveBadIndex1_2_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        try {
            set.remove(200, null);
            // removed other assertion
        } catch (IndexOutOfBoundsException ex) {}
        assertEquals(4,set.size());
    }

    public void testRemoveBadIndex2_2_oe() {
        Converter[] array = new Converter[] {
            c1, c2, c3, c4,
        };
        ConverterSet set = new ConverterSet(array);
        try {
            set.remove(-1, null);
            // removed other assertion
        } catch (IndexOutOfBoundsException ex) {}
        assertEquals(4,set.size());
    }

}
