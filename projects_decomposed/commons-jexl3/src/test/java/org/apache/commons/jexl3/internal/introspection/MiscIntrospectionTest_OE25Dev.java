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
package org.apache.commons.jexl3.internal.introspection;

import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.annotations.NoJexl;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Checks the CacheMap.MethodKey implementation
 */

public class MiscIntrospectionTest_OE25Dev {

    public static class A {
        public int i;
        public A() {}
        public int method() { return 0; }
    }

    @NoJexl
    public interface InterNoJexl0 {
        int method();
    }

    public interface InterNoJexl1 {
        @NoJexl
        int method();
    }


    public static class A0 extends A implements InterNoJexl0 {
        @NoJexl public int i0;
        @NoJexl public A0() {}
        @Override public int method() { return 1; }
    }

    public static class A1 extends A implements InterNoJexl1 {
        private int i1;
        @NoJexl public A1() {}
        @Override public int method() { return 2; }
    }

    @NoJexl
    public static class A2 extends A  {
        public A2() {}
        @Override public int method() { return 3; }
    }

    protected static class A3 {
        protected int i3;
        protected A3() {}
        int method() { return 4; }
    }

    public static class A5 implements InterNoJexl5 {
        public A5() {}
        @Override public int method() { return 0; }
    }

    @NoJexl
    public interface InterNoJexl5 {
        int method();
    }

    @Test
    public void testEmptyContext_2_oe() {
        try {
            JexlEngine.EMPTY_CONTEXT.set("nope", 42);
        } catch(UnsupportedOperationException xun) {
            Assert.assertNotNull(xun);
    }
    }

    @Test
    public void testArrayIterator_1_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
            Assert.assertNotNull(xill);
    }
    }

    @Test
    public void testArrayIterator_2_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
        }
        ArrayIterator ai0 = new ArrayIterator(null);
        Assert.assertFalse(ai0.hasNext());
    }

    @Test
    public void testArrayIterator_4_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
        }
        ArrayIterator ai0 = new ArrayIterator(null);
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
            Assert.assertNotNull(no);
    }
    }

    @Test
    public void testArrayIterator_5_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
        }
        ArrayIterator ai0 = new ArrayIterator(null);
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
        }
        ai0 = new ArrayIterator(new int[]{42});
        Assert.assertTrue(ai0.hasNext());
    }

    @Test
    public void testArrayIterator_6_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
        }
        ArrayIterator ai0 = new ArrayIterator(null);
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
        }
        ai0 = new ArrayIterator(new int[]{42});
        Assert.assertEquals(42, ai0.next());
    }

    @Test
    public void testArrayIterator_9_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
        }
        ArrayIterator ai0 = new ArrayIterator(null);
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
        }
        ai0 = new ArrayIterator(new int[]{42});
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
            Assert.assertNotNull(no);
    }
    }

    @Test
    public void testArrayIterator_11_oe() {
        try {
            new ArrayIterator(new ArrayList<>());
        } catch(IllegalArgumentException xill) {
        }
        ArrayIterator ai0 = new ArrayIterator(null);
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
        }
        ai0 = new ArrayIterator(new int[]{42});
        try {
            ai0.next();
        } catch(NoSuchElementException no) {
        }
        try {
            ai0.remove();
        } catch(UnsupportedOperationException no) {
            Assert.assertNotNull(no);
    }
    }

    @Test
    public void testArrayListWrapper_2_oe() {
        ArrayListWrapper alw ;
        try {
            new ArrayListWrapper(1);
        } catch(IllegalArgumentException xil) {
            Assert.assertNotNull(xil);
    }
    }

    @Test
    public void testArrayListWrapper_3_oe() {
        ArrayListWrapper alw ;
        try {
            new ArrayListWrapper(1);
        } catch(IllegalArgumentException xil) {
        }
        Integer[] ai = new Integer[]{1, 2};
        alw = new ArrayListWrapper(ai);
        Assert.assertEquals(1, alw.indexOf(2));
    }

    @Test
    public void testArrayListWrapper_4_oe() {
        ArrayListWrapper alw ;
        try {
            new ArrayListWrapper(1);
        } catch(IllegalArgumentException xil) {
        }
        Integer[] ai = new Integer[]{1, 2};
        alw = new ArrayListWrapper(ai);
        Assert.assertEquals(-1, alw.indexOf(null));
    }

    @Test
    public void testPermissions_1_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;
        Assert.assertFalse(p.allow((Field) null));
    }

    @Test
    public void testPermissions_2_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;
        Assert.assertFalse(p.allow((Package) null));
    }

    @Test
    public void testPermissions_3_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;
        Assert.assertFalse(p.allow((Method) null));
    }

    @Test
    public void testPermissions_4_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;
        Assert.assertFalse(p.allow((Constructor<?>) null));
    }

    @Test
    public void testPermissions_5_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;
        Assert.assertFalse(p.allow((Class<?>) null));
    }

    @Test
    public void testPermissions_6_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;

        Assert.assertTrue(p.allow(A2.class));
    }

    @Test
    public void testPermissions_7_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;

        Assert.assertFalse(p.allow(A3.class));
    }

    @Test
    public void testPermissions_8_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;

        Assert.assertFalse(p.allow(A5.class));
    }

    @Test
    public void testPermissions_9_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Assert.assertNotNull(mA);
    }

    @Test
    public void testPermissions_10_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Assert.assertNotNull(mA0);
    }

    @Test
    public void testPermissions_11_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Assert.assertNotNull(mA1);
    }

    @Test
    public void testPermissions_12_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Assert.assertNotNull(mA1);
    }

    @Test
    public void testPermissions_13_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");
        Assert.assertNotNull(mA1);
    }

    @Test
    public void testPermissions_14_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");

        Assert.assertTrue(p.allow(mA));
    }

    @Test
    public void testPermissions_15_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");

        Assert.assertFalse(p.allow(mA0));
    }

    @Test
    public void testPermissions_16_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");

        Assert.assertFalse(p.allow(mA1));
    }

    @Test
    public void testPermissions_17_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");

        Assert.assertFalse(p.allow(mA2));
    }

    @Test
    public void testPermissions_18_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");

        Assert.assertFalse(p.allow(mA3));
    }

    @Test
    public void testPermissions_19_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");
        Assert.assertNotNull(fA);
    }

    @Test
    public void testPermissions_20_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");
        Assert.assertTrue(p.allow(fA));
    }

    @Test
    public void testPermissions_21_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Assert.assertNotNull(fA0);
    }

    @Test
    public void testPermissions_22_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Assert.assertFalse(p.allow(fA0));
    }

    @Test
    public void testPermissions_23_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");
        Assert.assertNotNull(fA1);
    }

    @Test
    public void testPermissions_24_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");
        Assert.assertFalse(p.allow(fA0));
    }

    @Test
    public void testPermissions_25_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");

        Constructor<?> cA = A.class.getConstructor();
        Assert.assertNotNull(cA);
    }

    @Test
    public void testPermissions_26_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");

        Constructor<?> cA = A.class.getConstructor();
        Assert.assertTrue(p.allow(cA));
    }

    @Test
    public void testPermissions_27_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");

        Constructor<?> cA = A.class.getConstructor();

        Constructor<?> cA0 = A0.class.getConstructor();
        Assert.assertNotNull(cA0);
    }

    @Test
    public void testPermissions_28_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");

        Constructor<?> cA = A.class.getConstructor();

        Constructor<?> cA0 = A0.class.getConstructor();
        Assert.assertFalse(p.allow(cA0));
    }

    @Test
    public void testPermissions_29_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");

        Constructor<?> cA = A.class.getConstructor();

        Constructor<?> cA0 = A0.class.getConstructor();

        Constructor<?> cA3 = A3.class.getDeclaredConstructor();
        Assert.assertNotNull(cA3);
    }

    @Test
    public void testPermissions_30_oe() throws Exception {
        Permissions p = Permissions.DEFAULT;


        Method mA = A.class.getMethod("method");
        Method mA0 = A0.class.getMethod("method");
        Method mA1 = A1.class.getMethod("method");
        Method mA2 = A2.class.getMethod("method");
        Method mA3 = A2.class.getDeclaredMethod("method");


        Field fA = A.class.getField("i");

        Field fA0 = A0.class.getField("i0");
        Field fA1 = A1.class.getDeclaredField("i1");

        Constructor<?> cA = A.class.getConstructor();

        Constructor<?> cA0 = A0.class.getConstructor();

        Constructor<?> cA3 = A3.class.getDeclaredConstructor();
        Assert.assertFalse(p.allow(cA3));
    }

}
