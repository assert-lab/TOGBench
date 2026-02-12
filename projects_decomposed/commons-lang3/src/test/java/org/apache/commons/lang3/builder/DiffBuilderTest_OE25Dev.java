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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;


/**
 * Unit tests {@link DiffBuilder}.
 */
public class DiffBuilderTest_OE25Dev {

    private static class TypeTestClass implements Diffable<TypeTestClass> {
        private ToStringStyle style = SHORT_STYLE;
        private boolean booleanField = true;
        private boolean[] booleanArrayField = {true};
        private byte byteField = (byte) 0xFF;
        private byte[] byteArrayField = {(byte) 0xFF};
        private char charField = 'a';
        private char[] charArrayField = {'a'};
        private double doubleField = 1.0;
        private double[] doubleArrayField = {1.0};
        private float floatField = 1.0f;
        private float[] floatArrayField = {1.0f};
        private int intField = 1;
        private int[] intArrayField = {1};
        private long longField = 1L;
        private long[] longArrayField = {1L};
        private short shortField = 1;
        private short[] shortArrayField = {1};
        private Object objectField = null;
        private Object[] objectArrayField = {null};

        @Override
        public DiffResult<TypeTestClass> diff(final TypeTestClass obj) {
            return new DiffBuilder<>(this, obj, style)
                .append("boolean", booleanField, obj.booleanField)
                .append("booleanArray", booleanArrayField, obj.booleanArrayField)
                .append("byte", byteField, obj.byteField)
                .append("byteArray", byteArrayField, obj.byteArrayField)
                .append("char", charField, obj.charField)
                .append("charArray", charArrayField, obj.charArrayField)
                .append("double", doubleField, obj.doubleField)
                .append("doubleArray", doubleArrayField, obj.doubleArrayField)
                .append("float", floatField, obj.floatField)
                .append("floatArray", floatArrayField, obj.floatArrayField)
                .append("int", intField, obj.intField)
                .append("intArray", intArrayField, obj.intArrayField)
                .append("long", longField, obj.longField)
                .append("longArray", longArrayField, obj.longArrayField)
                .append("short", shortField, obj.shortField)
                .append("shortArray", shortArrayField, obj.shortArrayField)
                .append("objectField", objectField, obj.objectField)
                .append("objectArrayField", objectArrayField, obj.objectArrayField)
                .build();
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, false);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }
    }

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    /**
     * Test that "left" and "right" are the same instance but are equal.
     */

    /**
     * Test that "left" and "right" are not the same instance and are not equal.
     */

    /**
     * Test that "left" and "right" are the same instance and are equal.
     */

    @Test
    public void testNullLhs_1_oe() throws Exception {
        try {
    new DiffBuilder<>(null, this, ToStringStyle.DEFAULT_STYLE);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullRhs_1_oe() throws Exception {
        try {
    new DiffBuilder<>(this, null, ToStringStyle.DEFAULT_STYLE);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
