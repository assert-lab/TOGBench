/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.text.StrBuilder;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.ObjectUtils}.
 */
@SuppressWarnings("deprecation") // deliberate use of deprecated code
public class ObjectUtilsTest_OE25Dev {
    static final class CharSequenceComparator implements Comparator<CharSequence> {

        @Override
        public int compare(final CharSequence o1, final CharSequence o2) {
            return o1.toString().compareTo(o2.toString());
        }

    }
    /**
     * String that is cloneable.
     */
    static final class CloneableString extends MutableObject<String> implements Cloneable {
        private static final long serialVersionUID = 1L;
        CloneableString(final String s) {
            super(s);
        }

        @Override
        public CloneableString clone() throws CloneNotSupportedException {
            return (CloneableString) super.clone();
        }
    }
    static final class NonComparableCharSequence implements CharSequence {
        final String value;

        /**
         * Create a new NonComparableCharSequence instance.
         *
         * @param value the CharSequence value
         */
        NonComparableCharSequence(final String value) {
            Validate.notNull(value);
            this.value = value;
        }

        @Override
        public char charAt(final int arg0) {
            return value.charAt(arg0);
        }

        @Override
        public int length() {
            return value.length();
        }

        @Override
        public CharSequence subSequence(final int arg0, final int arg1) {
            return value.subSequence(arg0, arg1);
        }

        @Override
        public String toString() {
            return value;
        }
    }
    /**
     * String that is not cloneable.
     */
    static final class UncloneableString extends MutableObject<String> implements Cloneable {
        private static final long serialVersionUID = 1L;
        UncloneableString(final String s) {
            super(s);
        }
    }
    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String[] NON_EMPTY_ARRAY = new String[] { FOO, BAR, };

    private static final List<String> NON_EMPTY_LIST = Arrays.asList(NON_EMPTY_ARRAY);

    private static final Set<String> NON_EMPTY_SET = new HashSet<>(NON_EMPTY_LIST);

    private static final Map<String, String> NON_EMPTY_MAP = new HashMap<>();

    static {
        NON_EMPTY_MAP.put(FOO, BAR);
    }

    /**
     * Tests {@link ObjectUtils#allNotNull(Object...)}.
     */

    /**
     * Tests {@link ObjectUtils#allNull(Object...)}.
     */

    /**
     * Tests {@link ObjectUtils#anyNotNull(Object...)}.
     */

    /**
     * Tests {@link ObjectUtils#anyNull(Object...)}.
     */

    /**
     * Tests {@link ObjectUtils#clone(Object)} with a cloneable object.
     */

    /**
     * Tests {@link ObjectUtils#clone(Object)} with a not cloneable object.
     */

    /**
     * Tests {@link ObjectUtils#clone(Object)} with an array of primitives.
     */

    /**
     * Tests {@link ObjectUtils#clone(Object)} with an object array.
     */

    /**
     * Tests {@link ObjectUtils#clone(Object)} with an uncloneable object.
     */

    /**
     * Tests {@link ObjectUtils#compare(Comparable, Comparable, boolean)}.
     */

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------

    /**
     * Tests {@link ObjectUtils#cloneIfPossible(Object)} with a cloneable object.
     */

    /**
     * Tests {@link ObjectUtils#cloneIfPossible(Object)} with a not cloneable object.
     */

    /**
     * Tests {@link ObjectUtils#cloneIfPossible(Object)} with an uncloneable object.
     */

    @Test
    public void testAllNotNull_1_oe() {
        assertFalse(ObjectUtils.allNotNull((Object) null));
    }

    @Test
    public void testAllNotNull_2_oe() {
        assertFalse(ObjectUtils.allNotNull((Object[]) null));
    }

    @Test
    public void testAllNotNull_3_oe() {
        assertFalse(ObjectUtils.allNotNull(null, null, null));
    }

    @Test
    public void testAllNotNull_4_oe() {
        assertFalse(ObjectUtils.allNotNull(null, FOO, BAR));
    }

    @Test
    public void testAllNotNull_5_oe() {
        assertFalse(ObjectUtils.allNotNull(FOO, BAR, null));
    }

    @Test
    public void testAllNotNull_6_oe() {
        assertFalse(ObjectUtils.allNotNull(FOO, BAR, null, FOO, BAR));
    }

    @Test
    public void testAllNotNull_7_oe() {

        assertTrue(ObjectUtils.allNotNull());
    }

    @Test
    public void testAllNotNull_8_oe() {

        assertTrue(ObjectUtils.allNotNull(FOO));
    }

    @Test
    public void testAllNotNull_9_oe() {

        assertTrue(ObjectUtils.allNotNull(FOO, BAR, 1, Boolean.TRUE, new Object(), new Object[]{}));
    }

    @Test
    public void testAllNull_1_oe() {
        assertTrue(ObjectUtils.allNull());
    }

    @Test
    public void testAllNull_2_oe() {
        assertTrue(ObjectUtils.allNull((Object) null));
    }

    @Test
    public void testAllNull_3_oe() {
        assertTrue(ObjectUtils.allNull((Object[]) null));
    }

    @Test
    public void testAllNull_4_oe() {
        assertTrue(ObjectUtils.allNull(null, null, null));
    }

    @Test
    public void testAllNull_5_oe() {

        assertFalse(ObjectUtils.allNull(FOO));
    }

    @Test
    public void testAllNull_6_oe() {

        assertFalse(ObjectUtils.allNull(null, FOO, null));
    }

    @Test
    public void testAllNull_7_oe() {

        assertFalse(ObjectUtils.allNull(null, null, null, null, FOO, BAR));
    }

    @Test
    public void testAnyNotNull_1_oe() {
        assertFalse(ObjectUtils.anyNotNull());
    }

    @Test
    public void testAnyNotNull_2_oe() {
        assertFalse(ObjectUtils.anyNotNull((Object) null));
    }

    @Test
    public void testAnyNotNull_3_oe() {
        assertFalse(ObjectUtils.anyNotNull((Object[]) null));
    }

    @Test
    public void testAnyNotNull_4_oe() {
        assertFalse(ObjectUtils.anyNotNull(null, null, null));
    }

    @Test
    public void testAnyNotNull_5_oe() {

        assertTrue(ObjectUtils.anyNotNull(FOO));
    }

    @Test
    public void testAnyNotNull_6_oe() {

        assertTrue(ObjectUtils.anyNotNull(null, FOO, null));
    }

    @Test
    public void testAnyNotNull_7_oe() {

        assertTrue(ObjectUtils.anyNotNull(null, null, null, null, FOO, BAR));
    }

    @Test
    public void testAnyNull_1_oe() {
        assertTrue(ObjectUtils.anyNull((Object) null));
    }

    @Test
    public void testAnyNull_2_oe() {
        assertTrue(ObjectUtils.anyNull(null, null, null));
    }

    @Test
    public void testAnyNull_3_oe() {
        assertTrue(ObjectUtils.anyNull(null, FOO, BAR));
    }

    @Test
    public void testAnyNull_4_oe() {
        assertTrue(ObjectUtils.anyNull(FOO, BAR, null));
    }

    @Test
    public void testAnyNull_5_oe() {
        assertTrue(ObjectUtils.anyNull(FOO, BAR, null, FOO, BAR));
    }

    @Test
    public void testAnyNull_6_oe() {

        assertFalse(ObjectUtils.anyNull());
    }

    @Test
    public void testAnyNull_7_oe() {

        assertFalse(ObjectUtils.anyNull(FOO));
    }

    @Test
    public void testAnyNull_8_oe() {

        assertFalse(ObjectUtils.anyNull(FOO, BAR, 1, Boolean.TRUE, new Object(), new Object[]{}));
    }

    @Test
    public void testCloneOfCloneable_1_oe() {
        final CloneableString string = new CloneableString("apache");
        final CloneableString stringClone = ObjectUtils.clone(string);
        assertEquals("apache", stringClone.getValue());
    }

    @Test
    public void testCloneOfNotCloneable_1_oe() {
        final String string = "apache";
        assertNull(ObjectUtils.clone(string));
    }

    @Test
    public void testCloneOfPrimitiveArray_1_oe() {
        assertArrayEquals(new int[]{1}, ObjectUtils.clone(new int[]{1}));
    }

    @Test
    public void testCloneOfStringArray_1_oe() {
        assertTrue(Arrays.deepEquals( new String[]{"string"}, ObjectUtils.clone(new String[]{"string"})));
    }

    @Test
    public void testCloneOfUncloneable_1_oe() throws Exception {
        final UncloneableString string = new UncloneableString("apache");
        try {
    ObjectUtils.clone(string);
    fail("CloneFailedException");
} catch (CloneFailedException e) {
}
    }

    @Test
    public void testComparatorMedian_1_oe() {
        final CharSequenceComparator cmp = new CharSequenceComparator();
        final NonComparableCharSequence foo = new NonComparableCharSequence("foo");
        final NonComparableCharSequence bar = new NonComparableCharSequence("bar");
        final NonComparableCharSequence baz = new NonComparableCharSequence("baz");
        final NonComparableCharSequence blah = new NonComparableCharSequence("blah");
        final NonComparableCharSequence wah = new NonComparableCharSequence("wah");
        assertSame(foo, ObjectUtils.median(cmp, foo));
    }

    @Test
    public void testComparatorMedian_2_oe() {
        final CharSequenceComparator cmp = new CharSequenceComparator();
        final NonComparableCharSequence foo = new NonComparableCharSequence("foo");
        final NonComparableCharSequence bar = new NonComparableCharSequence("bar");
        final NonComparableCharSequence baz = new NonComparableCharSequence("baz");
        final NonComparableCharSequence blah = new NonComparableCharSequence("blah");
        final NonComparableCharSequence wah = new NonComparableCharSequence("wah");
        assertSame(bar, ObjectUtils.median(cmp, foo, bar));
    }

    @Test
    public void testComparatorMedian_3_oe() {
        final CharSequenceComparator cmp = new CharSequenceComparator();
        final NonComparableCharSequence foo = new NonComparableCharSequence("foo");
        final NonComparableCharSequence bar = new NonComparableCharSequence("bar");
        final NonComparableCharSequence baz = new NonComparableCharSequence("baz");
        final NonComparableCharSequence blah = new NonComparableCharSequence("blah");
        final NonComparableCharSequence wah = new NonComparableCharSequence("wah");
        assertSame(baz, ObjectUtils.median(cmp, foo, bar, baz));
    }

    @Test
    public void testComparatorMedian_4_oe() {
        final CharSequenceComparator cmp = new CharSequenceComparator();
        final NonComparableCharSequence foo = new NonComparableCharSequence("foo");
        final NonComparableCharSequence bar = new NonComparableCharSequence("bar");
        final NonComparableCharSequence baz = new NonComparableCharSequence("baz");
        final NonComparableCharSequence blah = new NonComparableCharSequence("blah");
        final NonComparableCharSequence wah = new NonComparableCharSequence("wah");
        assertSame(baz, ObjectUtils.median(cmp, foo, bar, baz, blah));
    }

    @Test
    public void testComparatorMedian_5_oe() {
        final CharSequenceComparator cmp = new CharSequenceComparator();
        final NonComparableCharSequence foo = new NonComparableCharSequence("foo");
        final NonComparableCharSequence bar = new NonComparableCharSequence("bar");
        final NonComparableCharSequence baz = new NonComparableCharSequence("baz");
        final NonComparableCharSequence blah = new NonComparableCharSequence("blah");
        final NonComparableCharSequence wah = new NonComparableCharSequence("wah");
        assertSame(blah, ObjectUtils.median(cmp, foo, bar, baz, blah, wah));
    }

    @Test
    public void testComparatorMedian_emptyItems_1_oe() throws Exception {
        try {
    ObjectUtils.median(new CharSequenceComparator());
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testComparatorMedian_nullComparator_1_oe() throws Exception {
        try {
    ObjectUtils.median((Comparator<CharSequence>) null, new NonComparableCharSequence("foo"));
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testComparatorMedian_nullItems_1_oe() throws Exception {
        try {
    ObjectUtils.median(new CharSequenceComparator(), (CharSequence[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testCompare_1_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;

        assertEquals(0, ObjectUtils.compare(nullValue, nullValue), "Null Null false");
    }

    @Test
    public void testCompare_2_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;

        assertEquals(0, ObjectUtils.compare(nullValue, nullValue, true), "Null Null true");
    }

    @Test
    public void testCompare_3_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;


        assertEquals(-1, ObjectUtils.compare(nullValue, one), "Null one false");
    }

    @Test
    public void testCompare_4_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;


        assertEquals(1, ObjectUtils.compare(nullValue, one, true), "Null one true");
    }

    @Test
    public void testCompare_5_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;



        assertEquals(1, ObjectUtils.compare(one, nullValue), "one Null false");
    }

    @Test
    public void testCompare_6_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;



        assertEquals(-1, ObjectUtils.compare(one, nullValue, true), "one Null true");
    }

    @Test
    public void testCompare_7_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;




        assertEquals(-1, ObjectUtils.compare(one, two), "one two false");
    }

    @Test
    public void testCompare_8_oe() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;




        assertEquals(-1, ObjectUtils.compare(one, two, true), "one two true");
    }

    @Test
    public void testConstMethods_1_oe() {


        assertTrue(ObjectUtils.CONST(true), "CONST(boolean)");
    }

    @Test
    public void testConstMethods_2_oe() {


        assertEquals((byte) 3, ObjectUtils.CONST((byte) 3), "CONST(byte)");
    }

    @Test
    public void testConstMethods_3_oe() {


        assertEquals((char) 3, ObjectUtils.CONST((char) 3), "CONST(char)");
    }

    @Test
    public void testConstMethods_4_oe() {


        assertEquals((short) 3, ObjectUtils.CONST((short) 3), "CONST(short)");
    }

    @Test
    public void testConstMethods_5_oe() {


        assertEquals(3, ObjectUtils.CONST(3), "CONST(int)");
    }

    @Test
    public void testConstMethods_6_oe() {


        assertEquals(3L, ObjectUtils.CONST(3L), "CONST(long)");
    }

    @Test
    public void testConstMethods_7_oe() {


        assertEquals(3f, ObjectUtils.CONST(3f), "CONST(float)");
    }

    @Test
    public void testConstMethods_8_oe() {


        assertEquals(3.0, ObjectUtils.CONST(3.0), "CONST(double)");
    }

    @Test
    public void testConstMethods_9_oe() {


        assertEquals("abc", ObjectUtils.CONST("abc"), "CONST(Object)");
    }

    @Test
    public void testConstMethods_10_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertTrue(MAGIC_FLAG);
    }

    @Test
    public void testConstMethods_11_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(127, MAGIC_BYTE1);
    }

    @Test
    public void testConstMethods_12_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(127, MAGIC_BYTE2);
    }

    @Test
    public void testConstMethods_13_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals('a', MAGIC_CHAR);
    }

    @Test
    public void testConstMethods_14_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(123, MAGIC_SHORT1);
    }

    @Test
    public void testConstMethods_15_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(127, MAGIC_SHORT2);
    }

    @Test
    public void testConstMethods_16_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(123, MAGIC_INT);
    }

    @Test
    public void testConstMethods_17_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(123, MAGIC_LONG1);
    }

    @Test
    public void testConstMethods_18_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(3, MAGIC_LONG2);
    }

    @Test
    public void testConstMethods_19_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(1.0f, MAGIC_FLOAT);
    }

    @Test
    public void testConstMethods_20_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals(1.0, MAGIC_DOUBLE);
    }

    @Test
    public void testConstMethods_21_oe() {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        assertEquals("abc", MAGIC_STRING);
    }

    @Test
    public void testConstMethods_22_oe() throws Exception {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        try {
    ObjectUtils.CONST_BYTE(-129);
    fail("IllegalArgumentException: CONST_BYTE(-129): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstMethods_23_oe() throws Exception {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        try {
    ObjectUtils.CONST_BYTE(128);
    fail("IllegalArgumentException: CONST_BYTE(128): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstMethods_24_oe() throws Exception {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        try {
    ObjectUtils.CONST_SHORT(-32769);
    fail("IllegalArgumentException: CONST_SHORT(-32769): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstMethods_25_oe() throws Exception {



        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        try {
    ObjectUtils.CONST_BYTE(32768);
    fail("IllegalArgumentException: CONST_SHORT(32768): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructor_1_oe() {
        assertNotNull(new ObjectUtils());
    }

    @Test
    public void testConstructor_2_oe() {
        final Constructor<?>[] cons = ObjectUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
    }

    @Test
    public void testConstructor_3_oe() {
        final Constructor<?>[] cons = ObjectUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
    }

    @Test
    public void testConstructor_4_oe() {
        final Constructor<?>[] cons = ObjectUtils.class.getDeclaredConstructors();
        assertTrue(Modifier.isPublic(ObjectUtils.class.getModifiers()));
    }

    @Test
    public void testConstructor_5_oe() {
        final Constructor<?>[] cons = ObjectUtils.class.getDeclaredConstructors();
        assertFalse(Modifier.isFinal(ObjectUtils.class.getModifiers()));
    }

    @Test
    public void testDefaultIfNull_1_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(dflt, ObjectUtils.defaultIfNull(null, dflt), "dflt was not returned when o was null");
    }

    @Test
    public void testDefaultIfNull_2_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(o, ObjectUtils.defaultIfNull(o, dflt), "dflt was returned when o was not null");
    }

    @Test
    public void testDefaultIfNull_3_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(dflt, ObjectUtils.getIfNull(null, () -> dflt), "dflt was not returned when o was null");
    }

    @Test
    public void testDefaultIfNull_4_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(o, ObjectUtils.getIfNull(o, () -> dflt), "dflt was returned when o was not null");
    }

    @Test
    public void testDefaultIfNull_5_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(o, ObjectUtils.getIfNull(FOO, () -> dflt), "dflt was returned when o was not null");
    }

    @Test
    public void testDefaultIfNull_6_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        assertSame(o, ObjectUtils.getIfNull("foo", () -> dflt), "dflt was returned when o was not null");
    }

    @Test
    public void testDefaultIfNull_7_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        final MutableInt callsCounter = new MutableInt(0);
        final Supplier<Object> countingDefaultSupplier = () -> {
            callsCounter.increment();
            return dflt;
        };
        ObjectUtils.getIfNull(o, countingDefaultSupplier);
        assertEquals(0, callsCounter.getValue());
    }

    @Test
    public void testDefaultIfNull_8_oe() {
        final Object o = FOO;
        final Object dflt = BAR;
        final MutableInt callsCounter = new MutableInt(0);
        final Supplier<Object> countingDefaultSupplier = () -> {
            callsCounter.increment();
            return dflt;
        };
        ObjectUtils.getIfNull(o, countingDefaultSupplier);
        ObjectUtils.getIfNull(null, countingDefaultSupplier);
        assertEquals(1, callsCounter.getValue());
    }

    @Test
    public void testEquals_1_oe() {
        assertTrue(ObjectUtils.equals(null, null), "ObjectUtils.equals(null, null) returned false");
    }

    @Test
    public void testEquals_2_oe() {
        assertTrue(!ObjectUtils.equals(FOO, null), "ObjectUtils.equals(\"foo\", null) returned true");
    }

    @Test
    public void testEquals_3_oe() {
        assertTrue(!ObjectUtils.equals(null, BAR), "ObjectUtils.equals(null, \"bar\") returned true");
    }

    @Test
    public void testEquals_4_oe() {
        assertTrue(!ObjectUtils.equals(FOO, BAR), "ObjectUtils.equals(\"foo\", \"bar\") returned true");
    }

    @Test
    public void testEquals_5_oe() {
        assertTrue(ObjectUtils.equals(FOO, FOO), "ObjectUtils.equals(\"foo\", \"foo\") returned false");
    }

    @Test
    public void testFirstNonNull_1_oe() {
        assertEquals("", ObjectUtils.firstNonNull(null, ""));
    }

    @Test
    public void testFirstNonNull_2_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");
        assertEquals("123", firstNonNullGenerics);
    }

    @Test
    public void testFirstNonNull_3_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");
        assertEquals("123", ObjectUtils.firstNonNull("123", null, "456", null));
    }

    @Test
    public void testFirstNonNull_4_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");
        assertSame(Boolean.TRUE, ObjectUtils.firstNonNull(Boolean.TRUE));
    }

    @Test
    public void testFirstNonNull_5_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");

        assertNull(ObjectUtils.firstNonNull());
    }

    @Test
    public void testFirstNonNull_6_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");


        assertNull(ObjectUtils.firstNonNull(null, null));
    }

    @Test
    public void testFirstNonNull_7_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");



        assertNull(ObjectUtils.firstNonNull((Object) null));
    }

    @Test
    public void testFirstNonNull_8_oe() {
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");



        assertNull(ObjectUtils.firstNonNull((Object[]) null));
    }

    @Test
    public void testGetFirstNonNull_1_oe() {
        assertEquals("", ObjectUtils.getFirstNonNull(() -> null, () -> ""));
    }

    @Test
    public void testGetFirstNonNull_2_oe() {
        assertEquals("1", ObjectUtils.getFirstNonNull(() -> null, () -> "1", () -> "2", () -> null));
    }

    @Test
    public void testGetFirstNonNull_3_oe() {
        assertEquals("123", ObjectUtils.getFirstNonNull(() -> "123", () -> null, () -> "456"));
    }

    @Test
    public void testGetFirstNonNull_4_oe() {
        assertEquals("123", ObjectUtils.getFirstNonNull(() -> null, () -> "123", () -> fail("Supplier after first non-null value should not be evaluated")));
    }

    @Test
    public void testGetFirstNonNull_5_oe() {
        assertNull(ObjectUtils.getFirstNonNull(null, () -> null));
    }

    @Test
    public void testGetFirstNonNull_6_oe() {
        assertNull(ObjectUtils.getFirstNonNull());
    }

    @Test
    public void testGetFirstNonNull_7_oe() {
        assertNull(ObjectUtils.getFirstNonNull((Supplier<Object>) null));
    }

    @Test
    public void testGetFirstNonNull_8_oe() {
        assertNull(ObjectUtils.getFirstNonNull((Supplier<Object>[]) null));
    }

    @Test
    public void testGetFirstNonNull_9_oe() {
        assertEquals(1, ObjectUtils.getFirstNonNull(() -> null, () -> 1));
    }

    @Test
    public void testGetFirstNonNull_10_oe() {
        assertEquals(Boolean.TRUE, ObjectUtils.getFirstNonNull(() -> null, () -> Boolean.TRUE));
    }

    @Test
    public void testHashCode_1_oe() {
        assertEquals(0, ObjectUtils.hashCode(null));
    }

    @Test
    public void testHashCode_2_oe() {
        assertEquals("a".hashCode(), ObjectUtils.hashCode("a"));
    }

    @Test
    public void testHashCodeMulti_multiple_emptyArray_1_oe() {
        final Object[] array = new Object[0];
        assertEquals(1, ObjectUtils.hashCodeMulti(array));
    }

    @Test
    public void testHashCodeMulti_multiple_likeList_1_oe() {
        final List<Object> list0 = new ArrayList<>(Collections.emptyList());
        assertEquals(list0.hashCode(), ObjectUtils.hashCodeMulti());
    }

    @Test
    public void testHashCodeMulti_multiple_likeList_2_oe() {
        final List<Object> list0 = new ArrayList<>(Collections.emptyList());

        final List<Object> list1 = new ArrayList<>(Collections.singletonList("a"));
        assertEquals(list1.hashCode(), ObjectUtils.hashCodeMulti("a"));
    }

    @Test
    public void testHashCodeMulti_multiple_likeList_3_oe() {
        final List<Object> list0 = new ArrayList<>(Collections.emptyList());

        final List<Object> list1 = new ArrayList<>(Collections.singletonList("a"));

        final List<Object> list2 = new ArrayList<>(Arrays.asList("a", "b"));
        assertEquals(list2.hashCode(), ObjectUtils.hashCodeMulti("a", "b"));
    }

    @Test
    public void testHashCodeMulti_multiple_likeList_4_oe() {
        final List<Object> list0 = new ArrayList<>(Collections.emptyList());

        final List<Object> list1 = new ArrayList<>(Collections.singletonList("a"));

        final List<Object> list2 = new ArrayList<>(Arrays.asList("a", "b"));

        final List<Object> list3 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        assertEquals(list3.hashCode(), ObjectUtils.hashCodeMulti("a", "b", "c"));
    }

    @Test
    public void testHashCodeMulti_multiple_nullArray_1_oe() {
        final Object[] array = null;
        assertEquals(1, ObjectUtils.hashCodeMulti(array));
    }

    @Test
    public void testIdentityToStringAppendable_1_oe() throws IOException {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final Appendable appendable = new StringBuilder();
        ObjectUtils.identityToString(appendable, i);
        assertEquals(expected, appendable.toString());
    }

    @Test
    public void testIdentityToStringAppendable_2_oe() throws IOException {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final Appendable appendable = new StringBuilder();
        ObjectUtils.identityToString(appendable, i);

        try {
    ObjectUtils.identityToString((Appendable) null, "tmp");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringAppendable_3_oe() throws IOException {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final Appendable appendable = new StringBuilder();
        ObjectUtils.identityToString(appendable, i);


        try {
    ObjectUtils.identityToString((Appendable) (new StringBuilder()), null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringInteger_1_oe() {
        final Integer i = Integer.valueOf(90);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        assertEquals(expected, ObjectUtils.identityToString(i));
    }

    @Test
    public void testIdentityToStringObjectNull_1_oe() {
        assertNull(ObjectUtils.identityToString(null));
    }

    @Test
    public void testIdentityToStringStrBuilder_1_oe() {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);
        assertEquals(expected, builder.toString());
    }

    @Test
    public void testIdentityToStringStrBuilder_2_oe() throws Exception {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);

        try {
    ObjectUtils.identityToString((StrBuilder) null, "tmp");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStrBuilder_3_oe() throws Exception {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);


        try {
    ObjectUtils.identityToString(new StrBuilder(), null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringString_1_oe() {
        assertEquals("java.lang.String@" + Integer.toHexString(System.identityHashCode(FOO)),ObjectUtils.identityToString(FOO));
    }

    @Test
    public void testIdentityToStringStringBuffer_1_oe() {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);
        assertEquals(expected, buffer.toString());
    }

    @Test
    public void testIdentityToStringStringBuffer_2_oe() throws Exception {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);

        try {
    ObjectUtils.identityToString((StringBuffer) null, "tmp");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStringBuffer_3_oe() throws Exception {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);

        try {
    ObjectUtils.identityToString(new StringBuffer(), null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStringBuilder_1_oe() {
        final Integer i = Integer.valueOf(90);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuilder builder = new StringBuilder();
        ObjectUtils.identityToString(builder, i);
        assertEquals(expected, builder.toString());
    }

    @Test
    public void testIdentityToStringStringBuilderInUse_1_oe() {
        final Integer i = Integer.valueOf(90);
        final String expected = "ABC = java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuilder builder = new StringBuilder("ABC = ");
        ObjectUtils.identityToString(builder, i);
        assertEquals(expected, builder.toString());
    }

    @Test
    public  void testIdentityToStringStringBuilderNullStringBuilder_1_oe() {
        try {
    ObjectUtils.identityToString((StringBuilder) null, "tmp");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStringBuilderNullValue_1_oe() throws Exception {
        try {
    ObjectUtils.identityToString(new StringBuilder(), null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIsEmpty_1_oe() {
        assertTrue(ObjectUtils.isEmpty(null));
    }

    @Test
    public void testIsEmpty_2_oe() {
        assertTrue(ObjectUtils.isEmpty(""));
    }

    @Test
    public void testIsEmpty_3_oe() {
        assertTrue(ObjectUtils.isEmpty(new int[] {}));
    }

    @Test
    public void testIsEmpty_4_oe() {
        assertTrue(ObjectUtils.isEmpty(Collections.emptyList()));
    }

    @Test
    public void testIsEmpty_5_oe() {
        assertTrue(ObjectUtils.isEmpty(Collections.emptySet()));
    }

    @Test
    public void testIsEmpty_6_oe() {
        assertTrue(ObjectUtils.isEmpty(Collections.emptyMap()));
    }

    @Test
    public void testIsEmpty_7_oe() {

        assertFalse(ObjectUtils.isEmpty("  "));
    }

    @Test
    public void testIsEmpty_8_oe() {

        assertFalse(ObjectUtils.isEmpty("ab"));
    }

    @Test
    public void testIsEmpty_9_oe() {

        assertFalse(ObjectUtils.isEmpty(NON_EMPTY_ARRAY));
    }

    @Test
    public void testIsEmpty_10_oe() {

        assertFalse(ObjectUtils.isEmpty(NON_EMPTY_LIST));
    }

    @Test
    public void testIsEmpty_11_oe() {

        assertFalse(ObjectUtils.isEmpty(NON_EMPTY_SET));
    }

    @Test
    public void testIsEmpty_12_oe() {

        assertFalse(ObjectUtils.isEmpty(NON_EMPTY_MAP));
    }

    @Test
    public void testIsNotEmpty_1_oe() {
        assertFalse(ObjectUtils.isNotEmpty(null));
    }

    @Test
    public void testIsNotEmpty_2_oe() {
        assertFalse(ObjectUtils.isNotEmpty(""));
    }

    @Test
    public void testIsNotEmpty_3_oe() {
        assertFalse(ObjectUtils.isNotEmpty(new int[] {}));
    }

    @Test
    public void testIsNotEmpty_4_oe() {
        assertFalse(ObjectUtils.isNotEmpty(Collections.emptyList()));
    }

    @Test
    public void testIsNotEmpty_5_oe() {
        assertFalse(ObjectUtils.isNotEmpty(Collections.emptySet()));
    }

    @Test
    public void testIsNotEmpty_6_oe() {
        assertFalse(ObjectUtils.isNotEmpty(Collections.emptyMap()));
    }

    @Test
    public void testIsNotEmpty_7_oe() {

        assertTrue(ObjectUtils.isNotEmpty("  "));
    }

    @Test
    public void testIsNotEmpty_8_oe() {

        assertTrue(ObjectUtils.isNotEmpty("ab"));
    }

    @Test
    public void testIsNotEmpty_9_oe() {

        assertTrue(ObjectUtils.isNotEmpty(NON_EMPTY_ARRAY));
    }

    @Test
    public void testIsNotEmpty_10_oe() {

        assertTrue(ObjectUtils.isNotEmpty(NON_EMPTY_LIST));
    }

    @Test
    public void testIsNotEmpty_11_oe() {

        assertTrue(ObjectUtils.isNotEmpty(NON_EMPTY_SET));
    }

    @Test
    public void testIsNotEmpty_12_oe() {

        assertTrue(ObjectUtils.isNotEmpty(NON_EMPTY_MAP));
    }

    @Test
    public void testMax_1_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();

        assertNotSame( nonNullComparable1, nonNullComparable2 );
    }

    @Test
    public void testMax_2_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertNull(ObjectUtils.max( (String) null ) );
    }

    @Test
    public void testMax_3_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertNull(ObjectUtils.max( nullArray ) );
    }

    @Test
    public void testMax_4_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( null, nonNullComparable1 ) );
    }

    @Test
    public void testMax_5_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( nonNullComparable1, null ) );
    }

    @Test
    public void testMax_6_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( null, nonNullComparable1, null ) );
    }

    @Test
    public void testMax_7_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( nonNullComparable1, nonNullComparable2 ) );
    }

    @Test
    public void testMax_8_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable2, ObjectUtils.max( nonNullComparable2, nonNullComparable1 ) );
    }

    @Test
    public void testMax_9_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( nonNullComparable1, minComparable ) );
    }

    @Test
    public void testMax_10_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( minComparable, nonNullComparable1 ) );
    }

    @Test
    public void testMax_11_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.max( null, minComparable, null, nonNullComparable1 ) );
    }

    @Test
    public void testMax_12_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();



        assertNull( ObjectUtils.max(null, null) );
    }

    @Test
    public void testMedian_1_oe() {
        assertEquals("foo", ObjectUtils.median("foo"));
    }

    @Test
    public void testMedian_2_oe() {
        assertEquals("bar", ObjectUtils.median("foo", "bar"));
    }

    @Test
    public void testMedian_3_oe() {
        assertEquals("baz", ObjectUtils.median("foo", "bar", "baz"));
    }

    @Test
    public void testMedian_4_oe() {
        assertEquals("baz", ObjectUtils.median("foo", "bar", "baz", "blah"));
    }

    @Test
    public void testMedian_5_oe() {
        assertEquals("blah", ObjectUtils.median("foo", "bar", "baz", "blah", "wah"));
    }

    @Test
    public void testMedian_6_oe() {
        assertEquals(Integer.valueOf(5),ObjectUtils.median(Integer.valueOf(1),Integer.valueOf(5),Integer.valueOf(10)));
    }

    @Test
    public void testMedian_7_oe() {
        assertEquals(Integer.valueOf(7),ObjectUtils.median(Integer.valueOf(5),Integer.valueOf(6),Integer.valueOf(7),Integer.valueOf(8),Integer.valueOf(9)));
    }

    @Test
    public void testMedian_8_oe() {
        assertEquals(Integer.valueOf(6),ObjectUtils.median(Integer.valueOf(5),Integer.valueOf(6),Integer.valueOf(7),Integer.valueOf(8)));
    }

    @Test
    public void testMedian_emptyItems_1_oe() throws Exception {
        try {
    ObjectUtils.<String>median();
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testMedian_nullItems_1_oe() throws Exception {
        try {
    ObjectUtils.median((String[]) null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMin_1_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();

        assertNotSame( nonNullComparable1, nonNullComparable2 );
    }

    @Test
    public void testMin_2_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertNull(ObjectUtils.min( (String) null ) );
    }

    @Test
    public void testMin_3_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertNull(ObjectUtils.min( nullArray ) );
    }

    @Test
    public void testMin_4_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.min( null, nonNullComparable1 ) );
    }

    @Test
    public void testMin_5_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.min( nonNullComparable1, null ) );
    }

    @Test
    public void testMin_6_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.min( null, nonNullComparable1, null ) );
    }

    @Test
    public void testMin_7_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable1, ObjectUtils.min( nonNullComparable1, nonNullComparable2 ) );
    }

    @Test
    public void testMin_8_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( nonNullComparable2, ObjectUtils.min( nonNullComparable2, nonNullComparable1 ) );
    }

    @Test
    public void testMin_9_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( minComparable, ObjectUtils.min( nonNullComparable1, minComparable ) );
    }

    @Test
    public void testMin_10_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( minComparable, ObjectUtils.min( minComparable, nonNullComparable1 ) );
    }

    @Test
    public void testMin_11_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();


        assertSame( minComparable, ObjectUtils.min( null, nonNullComparable1, null, minComparable ) );
    }

    @Test
    public void testMin_12_oe() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullArray = null;

        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();



        assertNull( ObjectUtils.min(null, null) );
    }

    @Test
    public void testMode_1_oe() {
        assertNull(ObjectUtils.mode((Object[]) null));
    }

    @Test
    public void testMode_2_oe() {
        assertNull(ObjectUtils.mode());
    }

    @Test
    public void testMode_3_oe() {
        assertNull(ObjectUtils.mode("foo", "bar", "baz"));
    }

    @Test
    public void testMode_4_oe() {
        assertNull(ObjectUtils.mode("foo", "bar", "baz", "foo", "bar"));
    }

    @Test
    public void testMode_5_oe() {
        assertEquals("foo", ObjectUtils.mode("foo", "bar", "baz", "foo"));
    }

    @Test
    public void testMode_6_oe() {
        assertEquals(Integer.valueOf(9),ObjectUtils.mode("foo","bar","baz",Integer.valueOf(9),Integer.valueOf(10),Integer.valueOf(9)));
    }

    @Test
    public void testNotEqual_1_oe() {
        assertFalse(ObjectUtils.notEqual(null, null), "ObjectUtils.notEqual(null, null) returned false");
    }

    @Test
    public void testNotEqual_2_oe() {
        assertTrue(ObjectUtils.notEqual(FOO, null), "ObjectUtils.notEqual(\"foo\", null) returned true");
    }

    @Test
    public void testNotEqual_3_oe() {
        assertTrue(ObjectUtils.notEqual(null, BAR), "ObjectUtils.notEqual(null, \"bar\") returned true");
    }

    @Test
    public void testNotEqual_4_oe() {
        assertTrue(ObjectUtils.notEqual(FOO, BAR), "ObjectUtils.notEqual(\"foo\", \"bar\") returned true");
    }

    @Test
    public void testNotEqual_5_oe() {
        assertFalse(ObjectUtils.notEqual(FOO, FOO), "ObjectUtils.notEqual(\"foo\", \"foo\") returned false");
    }

    @Test
    public void testNull_1_oe() {
        assertNotNull(ObjectUtils.NULL);
    }

    @Test
    public void testNull_2_oe() {
        assertTrue(ObjectUtils.NULL instanceof ObjectUtils.Null);
    }

    @Test
    public void testNull_3_oe() {
        assertSame(ObjectUtils.NULL, SerializationUtils.clone(ObjectUtils.NULL));
    }

    @Test
    public void testPossibleCloneOfCloneable_1_oe() {
        final CloneableString string = new CloneableString("apache");
        final CloneableString stringClone = ObjectUtils.cloneIfPossible(string);
        assertEquals("apache", stringClone.getValue());
    }

    @Test
    public void testPossibleCloneOfNotCloneable_1_oe() {
        final String string = "apache";
        assertSame(string, ObjectUtils.cloneIfPossible(string));
    }

    @Test
    public void testPossibleCloneOfUncloneable_1_oe() throws Exception {
        final UncloneableString string = new UncloneableString("apache");
        try {
    ObjectUtils.cloneIfPossible(string);
    fail("CloneFailedException");
} catch (CloneFailedException e) {
}
    }

    @Test
    public void testRequireNonEmpty_1_oe() {
        assertEquals("foo", ObjectUtils.requireNonEmpty("foo"));
    }

    @Test
    public void testRequireNonEmpty_2_oe() {
        assertEquals("foo", ObjectUtils.requireNonEmpty("foo", "foo"));
    }

    @Test
    public void testRequireNonEmpty_3_oe() throws Exception {
        try {
    ObjectUtils.requireNonEmpty(null);
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRequireNonEmpty_4_oe() throws Exception {
        try {
    ObjectUtils.requireNonEmpty(null, "foo");
    fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRequireNonEmpty_5_oe() throws Exception {
        try {
    ObjectUtils.requireNonEmpty("");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testRequireNonEmpty_6_oe() throws Exception {
        try {
    ObjectUtils.requireNonEmpty("", "foo");
    fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testToString_Object_1_oe() {
        assertEquals("", ObjectUtils.toString(null) );
    }

    @Test
    public void testToString_Object_2_oe() {
        assertEquals(Boolean.TRUE.toString(), ObjectUtils.toString(Boolean.TRUE) );
    }

    @Test
    public void testToString_ObjectString_1_oe() {
        assertEquals(BAR, ObjectUtils.toString(null, BAR) );
    }

    @Test
    public void testToString_ObjectString_2_oe() {
        assertEquals(Boolean.TRUE.toString(), ObjectUtils.toString(Boolean.TRUE, BAR) );
    }

    @Test
    public void testToString_SupplierString_1_oe() {
        assertEquals(null, ObjectUtils.toString(null, (Supplier<String>) null));
    }

    @Test
    public void testToString_SupplierString_2_oe() {
        assertEquals(null, ObjectUtils.toString(null, () -> null));
    }

    @Test
    public void testToString_SupplierString_3_oe() {
        assertEquals(BAR, ObjectUtils.toString(null, () -> BAR));
    }

    @Test
    public void testToString_SupplierString_4_oe() {
        assertEquals(Boolean.TRUE.toString(), ObjectUtils.toString(Boolean.TRUE, () -> BAR));
    }

    @Test
    public void testWaitDuration_1_oe() throws Exception {
        try {
    ObjectUtils.wait(new Object(), Duration.ZERO);
    fail("IllegalMonitorStateException");
} catch (IllegalMonitorStateException e) {
}
    }

}
