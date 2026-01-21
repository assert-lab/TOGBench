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

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.RecursiveToStringStyleTest}.
 */
public class RecursiveToStringStyleTest_OE25Dev {

    private final Integer base = Integer.valueOf(5);
    private final String baseStr = base.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(base));

    @BeforeEach
    public void setUp() {
        ToStringBuilder.setDefaultStyle(new RecursiveToStringStyle());
    }

    @AfterEach
    public void tearDown() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.DEFAULT_STYLE);
    }

    //----------------------------------------------------------------

    static class Person {
        /**
         * Test String field.
         */
        String name;

        /**
         * Test integer field.
         */
        int age;

        /**
         * Test boolean field.
         */
        boolean smoker;

        /**
         * Test Object field.
         */
        Job job;
    }

    static class Job {
        /**
         * Test String field.
         */
        String title;
    }

    @Test
    public void testBlank_1_oe() {
        assertEquals(baseStr + "[]", new ToStringBuilder(base).toString());
    }

    @Test
    public void testAppendSuper_1_oe() {
        assertEquals(baseStr + "[]", new ToStringBuilder(base).appendSuper("Integer@8888[]").toString());
    }

    @Test
    public void testAppendSuper_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).appendSuper("Integer@8888[<null>]").toString());
    }

    @Test
    public void testAppendSuper_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals(baseStr + "[a=hello]", new ToStringBuilder(base).appendSuper("Integer@8888[]").append("a", "hello").toString());
    }

    @Test
    public void testAppendSuper_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(baseStr + "[<null>,a=hello]", new ToStringBuilder(base).appendSuper("Integer@8888[<null>]").append("a", "hello").toString());
    }

    @Test
    public void testAppendSuper_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=hello]", new ToStringBuilder(base).appendSuper(null).append("a", "hello").toString());
    }

    @Test
    public void testObject_1_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) null).toString());
    }

    @Test
    public void testObject_2_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append(i3).toString());
    }

    @Test
    public void testObject_3_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<null>]", new ToStringBuilder(base).append("a", (Object) null).toString());
    }

    @Test
    public void testObject_4_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", i3).toString());
    }

    @Test
    public void testObject_5_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", i3).append("b", i4).toString());
    }

    @Test
    public void testObject_6_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<Integer>]", new ToStringBuilder(base).append("a", i3, false).toString());
    }

    @Test
    public void testObject_7_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", emptyList, false).toString());
    }

    @Test
    public void testObject_8_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=java.util.ArrayList@" + Integer.toHexString(System.identityHashCode(emptyList)) + "{}]", new ToStringBuilder(base).append("a", emptyList, true).toString());
    }

    @Test
    public void testObject_9_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", new HashMap<>(), false).toString());
    }

    @Test
    public void testObject_10_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a={}]", new ToStringBuilder(base).append("a", new HashMap<>(), true).toString());
    }

    @Test
    public void testObject_11_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

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
        assertEquals(baseStr + "[a=<size=0>]", new ToStringBuilder(base).append("a", (Object) new String[0], false).toString());
    }

    @Test
    public void testObject_12_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        final ArrayList<Object> emptyList = new ArrayList<>();

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
        assertEquals(baseStr + "[a={}]", new ToStringBuilder(base).append("a", (Object) new String[0], true).toString());
    }

    @Test
    public void testPerson_1_oe() {
        final Person p = new Person();
        p.name = "John Doe";
        p.age = 33;
        p.smoker = false;
        p.job = new Job();
        p.job.title = "Manager";
        final String pBaseStr = p.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(p));
        final String pJobStr  = p.job.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(p.job));
        assertEquals(pBaseStr + "[age=33,job=" + pJobStr + "[title=Manager],name=John Doe,smoker=false]", new ReflectionToStringBuilder(p, new RecursiveToStringStyle()).toString());
    }

    @Test
    public void testLong_1_oe() {
        assertEquals(baseStr + "[3]", new ToStringBuilder(base).append(3L).toString());
    }

    @Test
    public void testLong_2_oe() {
        // removed other assertion
        assertEquals(baseStr + "[a=3]", new ToStringBuilder(base).append("a", 3L).toString());
    }

    @Test
    public void testLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals(baseStr + "[a=3,b=4]", new ToStringBuilder(base).append("a", 3L).append("b", 4L).toString());
    }

    @Test
    public void testObjectArray_1_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        assertEquals(baseStr + "[{<null>,5,{3,6}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testObjectArray_2_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        assertEquals(baseStr + "[{<null>,5,{3,6}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testObjectArray_3_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testObjectArray_4_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArray_1_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArray_2_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        assertEquals(baseStr + "[{1,2,-3,4}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArray_3_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArray_4_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArrayArray_1_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArrayArray_2_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        assertEquals(baseStr + "[{{1,2},<null>,{5}}]", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArrayArray_3_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArrayArray_4_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals(baseStr + "[<null>]", new ToStringBuilder(base).append((Object) array).toString());
    }

}
