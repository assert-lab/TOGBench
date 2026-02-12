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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.HashCodeBuilder}.
 */
public class HashCodeBuilderTest_OE25Dev {

    /**
     * A reflection test fixture.
     */
    static class ReflectionTestCycleA {
        ReflectionTestCycleB b;

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    /**
     * A reflection test fixture.
     */
    static class ReflectionTestCycleB {
        ReflectionTestCycleA a;

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }
    }

    // -----------------------------------------------------------------------

    static class TestObject {
        private int a;

        TestObject(final int a) {
            this.a = a;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestObject)) {
                return false;
            }
            final TestObject rhs = (TestObject) o;
            return a == rhs.a;
        }

        @Override
        public int hashCode() {
            return a;
        }

        public void setA(final int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }

    static class TestSubObject extends TestObject {
        private int b;

        @SuppressWarnings("unused")
        private transient int t;

        TestSubObject() {
            super(0);
        }

        TestSubObject(final int a, final int b, final int t) {
            super(a);
            this.b = b;
            this.t = t;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TestSubObject)) {
                return false;
            }
            final TestSubObject rhs = (TestSubObject) o;
            return super.equals(o) && b == rhs.b;
        }

        @Override
        public int hashCode() {
            return b*17 + super.hashCode();
        }

    }

    static class TestObjectWithMultipleFields {
        @SuppressWarnings("unused")
        private int one = 0;

        @SuppressWarnings("unused")
        private int two = 0;

        @SuppressWarnings("unused")
        private int three = 0;

        TestObjectWithMultipleFields(final int one, final int two, final int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }
    }

    /**
     * Test Objects pointing to each other.
     */

    /**
     * Ensures LANG-520 remains true
     */

    static class TestObjectHashCodeExclude {
        @HashCodeExclude
        private final int a;
        private final int b;

        TestObjectHashCodeExclude(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    static class TestObjectHashCodeExclude2 {
        @HashCodeExclude
        private final int a;
        @HashCodeExclude
        private final int b;

        TestObjectHashCodeExclude2(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    @Test
    public void testConstructorExZero_1_oe() throws Exception {
        try {
    new HashCodeBuilder(0, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructorExEvenFirst_1_oe() throws Exception {
        try {
    new HashCodeBuilder(2, 3);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructorExEvenSecond_1_oe() throws Exception {
        try {
    new HashCodeBuilder(3, 2);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testConstructorExEvenNegative_1_oe() throws Exception {
        try {
    new HashCodeBuilder(-2, -2);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHierarchyHashCodeEx1_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(0, 0, new TestSubObject(0, 0, 0), true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHierarchyHashCodeEx2_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(2, 2, new TestSubObject(0, 0, 0), true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCodeEx1_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(0, 0, new TestObject(0), true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCodeEx2_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(2, 2, new TestObject(0), true);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testReflectionHashCodeEx3_1_oe() throws Exception {
        try {
    HashCodeBuilder.reflectionHashCode(13, 19, null, true);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
