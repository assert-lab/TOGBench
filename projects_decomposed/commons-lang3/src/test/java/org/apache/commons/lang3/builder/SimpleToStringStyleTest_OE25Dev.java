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

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.builder.ToStringStyleTest.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.SimpleToStringStyleTest_OE25Dev}.
 */
public class SimpleToStringStyleTest_OE25Dev {

    private final Integer base = Integer.valueOf(5);

    @BeforeEach
    public void setUp() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
    }

    @AfterEach
    public void tearDown() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.DEFAULT_STYLE);
    }

    //----------------------------------------------------------------

    @Test
    public void testBlank_1_oe() {
        assertEquals("", new ToStringBuilder(base).toString());
    }

    @Test
    public void testAppendSuper_1_oe() {
        assertEquals("", new ToStringBuilder(base).appendSuper("").toString());
    }

    @Test
    public void testAppendSuper_2_oe() {
        // removed other assertion
        assertEquals("<null>", new ToStringBuilder(base).appendSuper("<null>").toString());
    }

    @Test
    public void testAppendSuper_3_oe() {
        // removed other assertion
        // removed other assertion

        assertEquals("hello", new ToStringBuilder(base).appendSuper("").append("a", "hello").toString());
    }

    @Test
    public void testAppendSuper_4_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("<null>,hello", new ToStringBuilder(base).appendSuper("<null>").append("a", "hello").toString());
    }

    @Test
    public void testAppendSuper_5_oe() {
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("hello", new ToStringBuilder(base).appendSuper(null).append("a", "hello").toString());
    }

    @Test
    public void testObject_1_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        assertEquals("<null>", new ToStringBuilder(base).append((Object) null).toString());
    }

    @Test
    public void testObject_2_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        assertEquals("3", new ToStringBuilder(base).append(i3).toString());
    }

    @Test
    public void testObject_3_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        assertEquals("<null>", new ToStringBuilder(base).append("a", (Object) null).toString());
    }

    @Test
    public void testObject_4_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3", new ToStringBuilder(base).append("a", i3).toString());
    }

    @Test
    public void testObject_5_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3,4", new ToStringBuilder(base).append("a", i3).append("b", i4).toString());
    }

    @Test
    public void testObject_6_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<Integer>", new ToStringBuilder(base).append("a", i3, false).toString());
    }

    @Test
    public void testCollection_1_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        assertEquals("<size=0>", new ToStringBuilder(base).append("a", Collections.emptyList(), false).toString());
    }

    @Test
    public void testCollection_2_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        assertEquals("[]", new ToStringBuilder(base).append("a", Collections.emptyList(), true).toString());
    }

    @Test
    public void testCollection_3_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        assertEquals("<size=1>", new ToStringBuilder(base).append("a", Collections.singletonList(i3), false).toString());
    }

    @Test
    public void testCollection_4_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[3]", new ToStringBuilder(base).append("a", Collections.singletonList(i3), true).toString());
    }

    @Test
    public void testCollection_5_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<size=2>", new ToStringBuilder(base).append("a", Arrays.asList(i3, i4), false).toString());
    }

    @Test
    public void testCollection_6_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("[3, 4]", new ToStringBuilder(base).append("a", Arrays.asList(i3, i4), true).toString());
    }

    @Test
    public void testMap_1_oe() {
        assertEquals("<size=0>", new ToStringBuilder(base).append("a", Collections.emptyMap(), false).toString());
    }

    @Test
    public void testMap_2_oe() {
        // removed other assertion
        assertEquals("{}", new ToStringBuilder(base).append("a", Collections.emptyMap(), true).toString());
    }

    @Test
    public void testMap_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("<size=1>", new ToStringBuilder(base).append("a", Collections.singletonMap("k", "v"), false).toString());
    }

    @Test
    public void testMap_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("{k=v}", new ToStringBuilder(base).append("a", Collections.singletonMap("k", "v"), true).toString());
    }

    @Test
    public void testArray_1_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        assertEquals("<size=0>", new ToStringBuilder(base).append("a", (Object) new Integer[0], false).toString());
    }

    @Test
    public void testArray_2_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        assertEquals("{}", new ToStringBuilder(base).append("a", (Object) new Integer[0], true).toString());
    }

    @Test
    public void testArray_3_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        assertEquals("<size=1>", new ToStringBuilder(base).append("a", (Object) new Integer[]{i3}, false).toString());
    }

    @Test
    public void testArray_4_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("{3}", new ToStringBuilder(base).append("a", (Object) new Integer[]{i3}, true).toString());
    }

    @Test
    public void testArray_5_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("<size=2>", new ToStringBuilder(base).append("a", (Object) new Integer[]{i3, i4}, false).toString());
    }

    @Test
    public void testArray_6_oe() {
        final Integer i3 = Integer.valueOf(3);
        final Integer i4 = Integer.valueOf(4);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("{3,4}", new ToStringBuilder(base).append("a", (Object) new Integer[]{i3, i4}, true).toString());
    }

    @Test
    public void testPerson_1_oe() {
        final Person p = new Person();
        p.name = "Jane Q. Public";
        p.age = 47;
        p.smoker = false;
        assertEquals("Jane Q. Public,47,false", new ToStringBuilder(p).append("name", p.name).append("age", p.age).append("smoker", p.smoker).toString());
    }

    @Test
    public void testLong_1_oe() {
        assertEquals("3", new ToStringBuilder(base).append(3L).toString());
    }

    @Test
    public void testLong_2_oe() {
        // removed other assertion
        assertEquals("3", new ToStringBuilder(base).append("a", 3L).toString());
    }

    @Test
    public void testLong_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("3,4", new ToStringBuilder(base).append("a", 3L).append("b", 4L).toString());
    }

    @Test
    public void testObjectArray_1_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        assertEquals("{<null>,5,{3,6}}", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testObjectArray_2_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        assertEquals("{<null>,5,{3,6}}", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testObjectArray_3_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals("<null>", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testObjectArray_4_oe() {
        Object[] array = new Object[] {null, base, new int[] {3, 6}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals("<null>", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArray_1_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        assertEquals("{1,2,-3,4}", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArray_2_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        assertEquals("{1,2,-3,4}", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArray_3_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals("<null>", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArray_4_oe() {
        long[] array = new long[] {1, 2, -3, 4};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals("<null>", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArrayArray_1_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        assertEquals("{{1,2},<null>,{5}}", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArrayArray_2_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        assertEquals("{{1,2},<null>,{5}}", new ToStringBuilder(base).append((Object) array).toString());
    }

    @Test
    public void testLongArrayArray_3_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        assertEquals("<null>", new ToStringBuilder(base).append(array).toString());
    }

    @Test
    public void testLongArrayArray_4_oe() {
        long[][] array = new long[][] {{1, 2}, null, {5}};
        // removed other assertion
        // removed other assertion
        array = null;
        // removed other assertion
        assertEquals("<null>", new ToStringBuilder(base).append((Object) array).toString());
    }

}
