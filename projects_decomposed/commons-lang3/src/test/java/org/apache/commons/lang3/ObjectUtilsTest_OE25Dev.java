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
    public void testCloneOfUncloneable_1_oe() {
        final UncloneableString string = new UncloneableString("apache");
        final CloneFailedException e = assertThrows(CloneFailedException.class, () -> ObjectUtils.clone(string));
    }

    @Test
    public void testComparatorMedian_emptyItems_1_oe() throws Exception {
        try {
    ObjectUtils.median(new CharSequenceComparator());
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testComparatorMedian_nullComparator_1_oe() throws Exception {
        try {
    ObjectUtils.median((Comparator<CharSequence>) null, new NonComparableCharSequence("foo"));
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testComparatorMedian_nullItems_1_oe() throws Exception {
        try {
    ObjectUtils.median(new CharSequenceComparator(), (CharSequence[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testConstMethods_22_oe() throws Exception {

        // To truly test the CONST() method, we'd want to look in the
        // bytecode to see if the literals were folded into the
        // class, or if the bytecode kept the method call.

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Make sure documentation examples from Javadoc all work
        // (this fixed a lot of my bugs when I these!)
        //
        // My bugs should be in a software engineering textbook
        // for "Can you screw this up?"  The answer is, yes,
        // you can even screw this up.  (When you == Julius)
        // .
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
        try {
    ObjectUtils.CONST_BYTE(-129);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: CONST_BYTE(-129): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstMethods_23_oe() throws Exception {

        // To truly test the CONST() method, we'd want to look in the
        // bytecode to see if the literals were folded into the
        // class, or if the bytecode kept the method call.

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Make sure documentation examples from Javadoc all work
        // (this fixed a lot of my bugs when I these!)
        //
        // My bugs should be in a software engineering textbook
        // for "Can you screw this up?"  The answer is, yes,
        // you can even screw this up.  (When you == Julius)
        // .
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
        try {
    ObjectUtils.CONST_BYTE(128);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: CONST_BYTE(128): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstMethods_24_oe() throws Exception {

        // To truly test the CONST() method, we'd want to look in the
        // bytecode to see if the literals were folded into the
        // class, or if the bytecode kept the method call.

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Make sure documentation examples from Javadoc all work
        // (this fixed a lot of my bugs when I these!)
        //
        // My bugs should be in a software engineering textbook
        // for "Can you screw this up?"  The answer is, yes,
        // you can even screw this up.  (When you == Julius)
        // .
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
        // removed other assertion
        try {
    ObjectUtils.CONST_SHORT(-32769);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: CONST_SHORT(-32769): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstMethods_25_oe() throws Exception {

        // To truly test the CONST() method, we'd want to look in the
        // bytecode to see if the literals were folded into the
        // class, or if the bytecode kept the method call.

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Make sure documentation examples from Javadoc all work
        // (this fixed a lot of my bugs when I these!)
        //
        // My bugs should be in a software engineering textbook
        // for "Can you screw this up?"  The answer is, yes,
        // you can even screw this up.  (When you == Julius)
        // .
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
        // removed other assertion
        // removed other assertion
        try {
    ObjectUtils.CONST_BYTE(32768);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: CONST_SHORT(32768): IllegalArgumentException should have been thrown.");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testIdentityToStringAppendable_2_oe() throws IOException {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final Appendable appendable = new StringBuilder();
        ObjectUtils.identityToString(appendable, i);
        // removed other assertion

        try {
    ObjectUtils.identityToString((Appendable) null, "tmp");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringAppendable_3_oe() throws IOException {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final Appendable appendable = new StringBuilder();
        ObjectUtils.identityToString(appendable, i);
        // removed other assertion

        // removed other assertion

        try {
    ObjectUtils.identityToString((Appendable) (new StringBuilder()), null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStrBuilder_2_oe() throws Exception {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);
        // removed other assertion

        try {
    ObjectUtils.identityToString((StrBuilder) null, "tmp");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStrBuilder_3_oe() throws Exception {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);
        // removed other assertion

        // removed other assertion

        try {
    ObjectUtils.identityToString(new StrBuilder(), null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStringBuffer_2_oe() throws Exception {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);
        // removed other assertion

        try {
    ObjectUtils.identityToString((StringBuffer) null, "tmp");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStringBuffer_3_oe() throws Exception {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);
        // removed other assertion

        // removed other assertion
        try {
    ObjectUtils.identityToString(new StringBuffer(), null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public  void testIdentityToStringStringBuilderNullStringBuilder_1_oe() {
        try {
    ObjectUtils.identityToString((StringBuilder) null, "tmp");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testIdentityToStringStringBuilderNullValue_1_oe() throws Exception {
        try {
    ObjectUtils.identityToString(new StringBuilder(), null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testMedian_emptyItems_1_oe() {
        assertThrows(IllegalArgumentException.class, ObjectUtils::<String>median);
    }

    @Test
    public void testMedian_nullItems_1_oe() throws Exception {
        try {
    ObjectUtils.median((String[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testPossibleCloneOfUncloneable_1_oe() {
        final UncloneableString string = new UncloneableString("apache");
        final CloneFailedException e = assertThrows(CloneFailedException.class, () -> ObjectUtils.cloneIfPossible(string));
    }

    @Test
    public void testRequireNonEmpty_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        //
        try {
    ObjectUtils.requireNonEmpty(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRequireNonEmpty_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        try {
    ObjectUtils.requireNonEmpty(null, "foo");
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRequireNonEmpty_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        //
        try {
    ObjectUtils.requireNonEmpty("");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testRequireNonEmpty_6_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        // removed other assertion
        //
        // removed other assertion
        try {
    ObjectUtils.requireNonEmpty("", "foo");
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testWaitDuration_1_oe() throws Exception {
        try {
    ObjectUtils.wait(new Object(), Duration.ZERO);
    org.junit.jupiter.api.Assertions.fail("IllegalMonitorStateException");
} catch (IllegalMonitorStateException e) {
}
    }

}
