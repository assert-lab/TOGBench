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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReflectionToStringBuilderExcludeNullValuesTest_OE25Dev {

    static class TestFixture {
        @SuppressWarnings("unused")
        private final Integer testIntegerField;
        @SuppressWarnings("unused")
        private final String testStringField;

        TestFixture(final Integer a, final String b) {
            this.testIntegerField = a;
            this.testStringField = b;
        }
    }

    private static final String INTEGER_FIELD_NAME = "testIntegerField";
    private static final String STRING_FIELD_NAME = "testStringField";
    private final TestFixture BOTH_NON_NULL = new TestFixture(0, "str");
    private final TestFixture FIRST_NULL = new TestFixture(null, "str");
    private final TestFixture SECOND_NULL = new TestFixture(0, null);
    private final TestFixture BOTH_NULL = new TestFixture(null, null);

    @Test
    public void test_NonExclude_1_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_2_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_3_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_4_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_5_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_6_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, false, null);
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_7_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make the both null
        toString = ReflectionToStringBuilder.toString(BOTH_NULL, null, false, false, false, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_NonExclude_8_oe() {
        //normal case=
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, false, null);
        // removed other assertion
        // removed other assertion

        //make the both null
        toString = ReflectionToStringBuilder.toString(BOTH_NULL, null, false, false, false, null);
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_1_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_2_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_3_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_4_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_5_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, true, null);
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_6_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, true, null);
        // removed other assertion
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_7_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //both null
        toString = ReflectionToStringBuilder.toString(BOTH_NULL, null, false, false, true, null);
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_excludeNull_8_oe() {

        //test normal case
        String toString = ReflectionToStringBuilder.toString(BOTH_NON_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //make one null
        toString = ReflectionToStringBuilder.toString(FIRST_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //other one null
        toString = ReflectionToStringBuilder.toString(SECOND_NULL, null, false, false, true, null);
        // removed other assertion
        // removed other assertion

        //both null
        toString = ReflectionToStringBuilder.toString(BOTH_NULL, null, false, false, true, null);
        // removed other assertion
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_1_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        assertTrue(builder.isExcludeNullValues());
    }

    @Test
    public void test_ConstructorOption_2_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_3_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_4_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_5_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_6_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(SECOND_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_7_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(SECOND_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_8_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(SECOND_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_9_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NON_NULL, null, null, null, false, false, true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(FIRST_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(SECOND_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_1_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        assertFalse(builder.isExcludeNullValues());
    }

    @Test
    public void test_ConstructorOptionNormal_2_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_3_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_4_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        //regression test older constructors
        ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        toString = oldBuilder.toString();
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_5_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        //regression test older constructors
        ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        toString = oldBuilder.toString();
        // removed other assertion
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_6_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        //regression test older constructors
        ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        toString = oldBuilder.toString();
        // removed other assertion
        // removed other assertion

        oldBuilder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false);
        toString = oldBuilder.toString();
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_7_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        //regression test older constructors
        ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        toString = oldBuilder.toString();
        // removed other assertion
        // removed other assertion

        oldBuilder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false);
        toString = oldBuilder.toString();
        // removed other assertion
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_8_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        //regression test older constructors
        ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        toString = oldBuilder.toString();
        // removed other assertion
        // removed other assertion

        oldBuilder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false);
        toString = oldBuilder.toString();
        // removed other assertion
        // removed other assertion

        oldBuilder = new ReflectionToStringBuilder(BOTH_NULL, null, null);
        toString = oldBuilder.toString();
        assertTrue(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOptionNormal_9_oe() {
        final ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        //regression test older constructors
        ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        toString = oldBuilder.toString();
        // removed other assertion
        // removed other assertion

        oldBuilder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false);
        toString = oldBuilder.toString();
        // removed other assertion
        // removed other assertion

        oldBuilder = new ReflectionToStringBuilder(BOTH_NULL, null, null);
        toString = oldBuilder.toString();
        // removed other assertion
        assertTrue(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_1_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        assertTrue(builder.isExcludeNullValues());
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_2_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_3_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_4_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_5_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_6_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        final ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        oldBuilder.setExcludeNullValues(true);
        assertTrue(oldBuilder.isExcludeNullValues());
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_7_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        final ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        oldBuilder.setExcludeNullValues(true);
        // removed other assertion
        toString = oldBuilder.toString();
        assertFalse(toString.contains(STRING_FIELD_NAME));
    }

    @Test
    public void test_ConstructorOption_ExcludeNull_8_oe() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, false);
        builder.setExcludeNullValues(true);
        // removed other assertion
        String toString = builder.toString();
        // removed other assertion
        // removed other assertion

        builder = new ReflectionToStringBuilder(BOTH_NULL, null, null, null, false, false, true);
        toString = builder.toString();
        // removed other assertion
        // removed other assertion

        final ReflectionToStringBuilder oldBuilder = new ReflectionToStringBuilder(BOTH_NULL);
        oldBuilder.setExcludeNullValues(true);
        // removed other assertion
        toString = oldBuilder.toString();
        // removed other assertion
        assertFalse(toString.contains(INTEGER_FIELD_NAME));
    }

}
