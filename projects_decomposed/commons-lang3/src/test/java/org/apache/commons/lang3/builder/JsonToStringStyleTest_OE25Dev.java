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
package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringStyleTest.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.JsonToStringStyleTest_OE25Dev}.
 */
public class JsonToStringStyleTest_OE25Dev {

    private final Integer base = Integer.valueOf(5);

    @BeforeEach
    public void setUp() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
    }

    @AfterEach
    public void tearDown() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.DEFAULT_STYLE);
    }

    // ----------------------------------------------------------------

    /**
     * An object with nested object structures used to test {@code ToStringStyle.JsonToStringStyle}.
     */
    static class NestingPerson {
        /**
         * Test String field.
         */
        String pid;

        /**
         * Test nested object field.
         */
        Person person;
    }

    enum Hobby {
        SPORT,
        BOOK,
        MUSIC
    }

    enum EmptyEnum {
    }

    static class Student {
        List<Hobby> hobbies;

        public List<Hobby> getHobbies() {
            return hobbies;
        }

        public void setHobbies(final List<Hobby> hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    static class Teacher {
        Hobby[] hobbies;

        public Hobby[] getHobbies() {
            return hobbies;
        }

        public void setHobbies(final Hobby[] hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    static class AcademyClass {
        Teacher teacher;
        List<Student> students;

        public void setTeacher(final Teacher teacher) {
            this.teacher = teacher;
        }

        public void setStudents(final List<Student> students) {
            this.students = students;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public List<Student> getStudents() {
            return students;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    /**
     * An object with a Map field used to test {@code ToStringStyle.JsonToStringStyle}.
     */
    static class InnerMapObject {
        /**
         * Test String field.
         */
        String pid;

        /**
         * Test inner map field.
         */
        Map<String, Object> map;
    }

    @Test
    public void testChar_1_oe() throws Exception {
        try {
    new ToStringBuilder(base).append('A').toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testDate_1_oe() throws Exception {
        final Date now = new Date();
        final Date afterNow = new Date(System.currentTimeMillis() + 1);

        try {
    new ToStringBuilder(base).append(now).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_1_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        try {
    new ToStringBuilder(base).append((Object) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_2_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        try {
    new ToStringBuilder(base).append(i3).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_6_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        try {
    new ToStringBuilder(base).append("a", i3, false).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_7_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        try {
    new ToStringBuilder(base).append("a", new ArrayList<>(), false).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_9_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

        // removed other assertion

        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    new ToStringBuilder(base).append("a", new HashMap<>(), false).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_11_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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
    new ToStringBuilder(base).append("a", (Object) new String[0], false).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_13_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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
    new ToStringBuilder(base).append("a", (Object) new int[]{1, 2, 3}, false).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObject_15_oe() throws Exception {

        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);

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
    new ToStringBuilder(base).append("a", (Object) new String[]{"v", "x", "y", "z"}, false).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLong_1_oe() throws Exception {
        try {
    new ToStringBuilder(base).append(3L).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObjectArray_1_oe() throws Exception {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObjectArray_3_oe() throws Exception {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObjectArray_4_oe() throws Exception {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testObjectArray_5_oe() throws Exception {
        final Object[] array = new Object[]{null, base, new int[]{3, 6}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArray_1_oe() throws Exception {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArray_3_oe() throws Exception {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArray_4_oe() throws Exception {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArray_5_oe() throws Exception {
        final long[] array = new long[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testIntArray_1_oe() throws Exception {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testIntArray_3_oe() throws Exception {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testIntArray_4_oe() throws Exception {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testIntArray_5_oe() throws Exception {
        final int[] array = new int[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testByteArray_1_oe() throws Exception {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testByteArray_3_oe() throws Exception {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testByteArray_4_oe() throws Exception {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testByteArray_5_oe() throws Exception {
        final byte[] array = new byte[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testShortArray_1_oe() throws Exception {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testShortArray_3_oe() throws Exception {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testShortArray_4_oe() throws Exception {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testShortArray_5_oe() throws Exception {
        final short[] array = new short[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testDoubleArray_1_oe() throws Exception {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testDoubleArray_3_oe() throws Exception {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testDoubleArray_4_oe() throws Exception {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testDoubleArray_5_oe() throws Exception {
        final double[] array = new double[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testFloatArray_1_oe() throws Exception {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testFloatArray_3_oe() throws Exception {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testFloatArray_4_oe() throws Exception {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testFloatArray_5_oe() throws Exception {
        final float[] array = new float[]{1, 2, -3, 4};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testCharArray_1_oe() throws Exception {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testCharArray_3_oe() throws Exception {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testCharArray_4_oe() throws Exception {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testCharArray_5_oe() throws Exception {
        final char[] array = new char[]{'1', '2', '3', '4'};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testBooleanArray_1_oe() throws Exception {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testBooleanArray_3_oe() throws Exception {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testBooleanArray_4_oe() throws Exception {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testBooleanArray_5_oe() throws Exception {
        final boolean[] array = new boolean[]{true, false};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArrayArray_1_oe() throws Exception {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        try {
    toStringBuilder.append(array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArrayArray_2_oe() throws Exception {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArrayArray_3_oe() throws Exception {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((long[][]) null).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

    @Test
    public void testLongArrayArray_4_oe() throws Exception {
        final long[][] array = new long[][]{{1, 2}, null, {5}};

        final ToStringBuilder toStringBuilder = new ToStringBuilder(base);
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    toStringBuilder.append((Object) array).toString();
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
