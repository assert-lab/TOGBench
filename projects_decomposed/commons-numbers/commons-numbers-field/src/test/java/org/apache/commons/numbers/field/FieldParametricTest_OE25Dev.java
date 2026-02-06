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
package org.apache.commons.numbers.field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import org.apache.commons.numbers.core.Addition;
import org.apache.commons.numbers.core.Multiplication;

/**
 * Tests for fields.
 */
class FieldParametricTest_OE25Dev {

    private static Stream<FieldTestData<?>> getList() {
        return FieldsList.list().stream();
    }

    /**
     * @param a Instance.
     * @param b Instance.
     */
    private static void assertEquals(Object a,
                                     Object b) {
        Assertions.assertEquals(a, b, a + " != " + b);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testAdditionAssociativity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();
        T c = data.getC();
        final T r1 = field.add(field.add(a, b), c);
        final T r2 = field.add(a, field.add(b, c));
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testAdditionCommutativity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();
        final T r1 = field.add(a, b);
        final T r2 = field.add(b, a);
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testAdditiveIdentity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        final T r1 = field.add(a, field.zero());
        final T r2 = a;
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testAdditiveInverse_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        final T r1 = field.add(a, field.negate(a));
        final T r2 = field.zero();
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testMultiplicationAssociativity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();
        T c = data.getC();
        final T r1 = field.multiply(field.multiply(a, b), c);
        final T r2 = field.multiply(a, field.multiply(b, c));
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testMultiplicationCommutativity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();
        final T r1 = field.multiply(a, b);
        final T r2 = field.multiply(b, a);
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testMultiplicativeIdentity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        final T r1 = field.multiply(a, field.one());
        final T r2 = a;
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testMultiplicativeInverse_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        final T r1 = field.multiply(a, field.reciprocal(a));
        final T r2 = field.one();
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T> void testDistributivity_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();
        T c = data.getC();
        final T r1 = field.multiply(a, field.add(b, c));
        final T r2 = field.add(field.multiply(a, b), field.multiply(a, c));
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Addition<T>> void testAdd_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();

        final T r1 = field.add(a, b);
        final T r2 = a.add(b);
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Addition<T>> void testSubtract_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();

        final T r1 = field.subtract(a, b);
        final T r2 = a.add(b.negate());
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Addition<T>> void testMultiplyInt_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        final int n = 5;

        final T r1 = field.multiply(n, a);

        T r2 = field.zero();
        for (int i = 0; i < n; i++) {
            r2 = r2.add(a);
        }

        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Addition<T>> void testZero_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();

        final T r1 = field.zero();
        final T r2 = a.zero();
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Multiplication<T>> void testMultiply_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();

        final T r1 = field.multiply(a, b);
        final T r2 = a.multiply(b);
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Multiplication<T>> void testDivide_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();
        T b = data.getB();

        final T r1 = field.divide(a, b);
        final T r2 = a.multiply(b.reciprocal());
        assertEquals(r1, r2);
    }

    @ParameterizedTest
    @MethodSource("getList")
    <T extends Multiplication<T>> void testOne_1_oe(FieldTestData<T> data) {
        Field<T> field = data.getField();
        T a = data.getA();

        final T r1 = field.one();
        final T r2 = a.one();
        assertEquals(r1, r2);
    }

}
