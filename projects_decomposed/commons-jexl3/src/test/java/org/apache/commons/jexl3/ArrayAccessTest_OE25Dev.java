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
package org.apache.commons.jexl3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jexl3.junit.Asserter;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for array access operator []
 *
 * @since 2.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class ArrayAccessTest_OE25Dev extends JexlTestCase {

    private Asserter asserter;

    private static final String GET_METHOD_STRING = "GetMethod string";

    // Needs to be accessible by Foo.class
    static final String[] GET_METHOD_ARRAY =
        new String[] { "One", "Two", "Three" };

    // Needs to be accessible by Foo.class
    static final String[][] GET_METHOD_ARRAY2 =
        new String[][] { {"One", "Two", "Three"},{"Four", "Five", "Six"} };

    public ArrayAccessTest_OE25Dev() {
        super("ArrayAccessTest_OE25Dev");
    }

    @Override
    @Before
    public void setUp() {
        asserter = new Asserter(JEXL);
    }

    /**
     * test simple array access
     */

    /**
     * test some simple double array lookups
     */

    // This is JEXL-26

    public static class Sample {
        private int[] array;
        public void setFoo(final int[] a) {
            array = a;
        }
        public int[] getFoo() {
            return array;
        }
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_1_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        asserter.assertExpression("list[1]", new Integer(2));
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_2_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        asserter.assertExpression("list[1+1]", new Integer(3));
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_3_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        asserter.assertExpression("list[loc+1]", new Integer(3));
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_4_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        // removed other assertion

        /*
         * test array access
         */

        final String[] args = { "hello", "there" };
        asserter.setVariable("array", args);
        asserter.assertExpression("array[0]", "hello");
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_5_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        // removed other assertion

        /*
         * test array access
         */

        final String[] args = { "hello", "there" };
        asserter.setVariable("array", args);
        // removed other assertion

        /*
         * to think that this was an intentional syntax...
         */
        asserter.assertExpression("array.0", "hello");
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_6_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        // removed other assertion

        /*
         * test array access
         */

        final String[] args = { "hello", "there" };
        asserter.setVariable("array", args);
        // removed other assertion

        /*
         * to think that this was an intentional syntax...
         */
        // removed other assertion

        /*
         * test map access
         */
        final Map<String, String> m = new HashMap<String, String>();
        m.put("foo", "bar");

        asserter.setVariable("map", m);
        asserter.setVariable("key", "foo");

        asserter.assertExpression("map[\"foo\"]", "bar");
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_7_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        // removed other assertion

        /*
         * test array access
         */

        final String[] args = { "hello", "there" };
        asserter.setVariable("array", args);
        // removed other assertion

        /*
         * to think that this was an intentional syntax...
         */
        // removed other assertion

        /*
         * test map access
         */
        final Map<String, String> m = new HashMap<String, String>();
        m.put("foo", "bar");

        asserter.setVariable("map", m);
        asserter.setVariable("key", "foo");

        // removed other assertion
        asserter.assertExpression("map[key]", "bar");
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_8_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        // removed other assertion

        /*
         * test array access
         */

        final String[] args = { "hello", "there" };
        asserter.setVariable("array", args);
        // removed other assertion

        /*
         * to think that this was an intentional syntax...
         */
        // removed other assertion

        /*
         * test map access
         */
        final Map<String, String> m = new HashMap<String, String>();
        m.put("foo", "bar");

        asserter.setVariable("map", m);
        asserter.setVariable("key", "foo");

        // removed other assertion
        // removed other assertion

        /*
         * test bean access
         */
        asserter.setVariable("foo", new Foo());
        asserter.assertExpression("foo[\"bar\"]", GET_METHOD_STRING);
    }

// TODO: verify inlining
    @Test
    public void testArrayAccess_9_oe() throws Exception {

        /*
         * test List access
         */

        final List<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(1));
        l.add(new Integer(2));
        l.add(new Integer(3));

        asserter.setVariable("list", l);

        // removed other assertion
        // removed other assertion
        asserter.setVariable("loc", new Integer(1));
        // removed other assertion

        /*
         * test array access
         */

        final String[] args = { "hello", "there" };
        asserter.setVariable("array", args);
        // removed other assertion

        /*
         * to think that this was an intentional syntax...
         */
        // removed other assertion

        /*
         * test map access
         */
        final Map<String, String> m = new HashMap<String, String>();
        m.put("foo", "bar");

        asserter.setVariable("map", m);
        asserter.setVariable("key", "foo");

        // removed other assertion
        // removed other assertion

        /*
         * test bean access
         */
        asserter.setVariable("foo", new Foo());
        // removed other assertion
        asserter.assertExpression("foo[\"bar\"] == foo.bar", Boolean.TRUE);
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_1_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        asserter.assertExpression("foo[0][1]", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_2_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        asserter.assertExpression("foo[0][1] = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_3_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo[0][1]", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_4_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.assertExpression("foo.0[1]", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_5_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        asserter.assertExpression("foo.0[1] = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_6_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0[1]", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_7_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.assertExpression("foo.0.'1'", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_8_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        asserter.assertExpression("foo.0.'1' = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_9_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0.'1'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_10_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.assertExpression("foo.'0'.'1'", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_11_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        asserter.assertExpression("foo.'0'.'1' = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_12_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.'0'.'1'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_13_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion


        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.assertExpression("foo.0.1", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_14_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion


        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        asserter.assertExpression("foo.0.1 = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleArrays_15_oe() throws Exception {
        final Object[][] foo = new Object[2][2];

        foo[0][0] = "one";
        foo[0][1] = "two";
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        // removed other assertion


        foo[0][0] = "one";
        foo[0][1] = "two";
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0.1", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_1_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        asserter.assertExpression("foo[0][1]", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_2_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        asserter.assertExpression("foo[0][1] = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_3_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo[0][1]", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_4_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo[0]['3.0']", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_5_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        asserter.assertExpression("foo.0[1]", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_6_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        asserter.assertExpression("foo.0[1] = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_7_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0[1]", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_8_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0['3.0']", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_9_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        asserter.assertExpression("foo.0.'1'", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_10_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        asserter.assertExpression("foo.0.'1' = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_11_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0.'1'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_12_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        asserter.assertExpression("foo.'0'.'1'", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_13_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        asserter.assertExpression("foo.'0'.'1' = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_14_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.'0'.'1'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_15_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        asserter.assertExpression("foo.0.1", "two");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_16_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        asserter.assertExpression("foo.0.1 = 'three'", "three");
    }

// TODO: verify inlining
    @Test
    public void testDoubleMaps_17_oe() throws Exception {
        final Map<Object, Map<Object, Object>> foo = new HashMap<Object, Map<Object, Object>>();
        final Map<Object, Object> foo0 = new HashMap<Object, Object>();
        foo.put(0, foo0);
        foo0.put(0, "one");
        foo0.put(1, "two");
        foo0.put("3.0", "three");
        asserter.setVariable("foo", foo);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        foo0.put(0, "one");
        foo0.put(1, "two");
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.0.1", "three");
    }

// TODO: verify inlining
    @Test
    public void testArrayProperty_1_oe() throws Exception {
        final Foo foo = new Foo();

        asserter.setVariable("foo", foo);

        asserter.assertExpression("foo.array[1]", GET_METHOD_ARRAY[1]);
    }

// TODO: verify inlining
    @Test
    public void testArrayProperty_2_oe() throws Exception {
        final Foo foo = new Foo();

        asserter.setVariable("foo", foo);

        // removed other assertion
        asserter.assertExpression("foo.array.1", GET_METHOD_ARRAY[1]);
    }

// TODO: verify inlining
    @Test
    public void testArrayProperty_3_oe() throws Exception {
        final Foo foo = new Foo();

        asserter.setVariable("foo", foo);

        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.array2[1][1]", GET_METHOD_ARRAY2[1][1]);
    }

// TODO: verify inlining
    @Test
    public void testArrayProperty_4_oe() throws Exception {
        final Foo foo = new Foo();

        asserter.setVariable("foo", foo);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("foo.array2[1].1", GET_METHOD_ARRAY2[1][1]);
    }

// TODO: verify inlining
    @Test
    public void testArrayAndDottedConflict_1_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};
        asserter.setStrict(false);
        asserter.setSilent(true);
        asserter.setVariable("objects", objects);
        asserter.setVariable("status", "Enabled");
        asserter.assertExpression("objects[1].status", null);
    }

// TODO: verify inlining
    @Test
    public void testArrayAndDottedConflict_2_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};
        asserter.setStrict(false);
        asserter.setSilent(true);
        asserter.setVariable("objects", objects);
        asserter.setVariable("status", "Enabled");
        // removed other assertion
        asserter.assertExpression("objects.1.status", null);
    }

// TODO: verify inlining
    @Test
    public void testArrayAndDottedConflict_3_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};
        asserter.setStrict(false);
        asserter.setSilent(true);
        asserter.setVariable("objects", objects);
        asserter.setVariable("status", "Enabled");
        // removed other assertion
        // removed other assertion

        asserter.setVariable("base.status", "Ok");
        asserter.assertExpression("base.objects[1].status", null);
    }

// TODO: verify inlining
    @Test
    public void testArrayAndDottedConflict_4_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};
        asserter.setStrict(false);
        asserter.setSilent(true);
        asserter.setVariable("objects", objects);
        asserter.setVariable("status", "Enabled");
        // removed other assertion
        // removed other assertion

        asserter.setVariable("base.status", "Ok");
        // removed other assertion
        asserter.assertExpression("base.objects.1.status", null);
    }

// TODO: verify inlining
    @Test
    public void testArrayIdentifierParsing_1_oe() throws Exception {
        final Map<Object, Number> map = new HashMap<Object, Number>();
        map.put("00200", -42.42d);
        map.put(200, 42.42d);
        asserter.setVariable("objects", map);
        asserter.assertExpression("objects.get('00200')", -42.42d);
    }

// TODO: verify inlining
    @Test
    public void testArrayIdentifierParsing_2_oe() throws Exception {
        final Map<Object, Number> map = new HashMap<Object, Number>();
        map.put("00200", -42.42d);
        map.put(200, 42.42d);
        asserter.setVariable("objects", map);
        // removed other assertion
        asserter.assertExpression("objects.'00200'", -42.42d);
    }

// TODO: verify inlining
    @Test
    public void testArrayIdentifierParsing_3_oe() throws Exception {
        final Map<Object, Number> map = new HashMap<Object, Number>();
        map.put("00200", -42.42d);
        map.put(200, 42.42d);
        asserter.setVariable("objects", map);
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("objects.get(200)", 42.42d);
    }

// TODO: verify inlining
    @Test
    public void testArrayIdentifierParsing_4_oe() throws Exception {
        final Map<Object, Number> map = new HashMap<Object, Number>();
        map.put("00200", -42.42d);
        map.put(200, 42.42d);
        asserter.setVariable("objects", map);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("objects.'200'", 42.42d);
    }

// TODO: verify inlining
    @Test
    public void testArrayIdentifierParsing_5_oe() throws Exception {
        final Map<Object, Number> map = new HashMap<Object, Number>();
        map.put("00200", -42.42d);
        map.put(200, 42.42d);
        asserter.setVariable("objects", map);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        asserter.assertExpression("objects.200", 42.42d);
    }

// TODO: verify inlining
    @Test
    public void testArrayMethods_1_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};

        asserter.setVariable("objects", objects);
        asserter.assertExpression("objects.get(1)", "array");
    }

// TODO: verify inlining
    @Test
    public void testArrayMethods_2_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};

        asserter.setVariable("objects", objects);
        // removed other assertion
        asserter.assertExpression("objects.size()", new Integer(3));
    }

// TODO: verify inlining
    @Test
    public void testArrayMethods_3_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};

        asserter.setVariable("objects", objects);
        // removed other assertion
        // removed other assertion
        // setting an index returns the old value
        asserter.assertExpression("objects.set(1, 'dion')", "array");
    }

// TODO: verify inlining
    @Test
    public void testArrayMethods_4_oe() throws Exception {
        final Object[] objects = new Object[] {"an", "array", new Long(0)};

        asserter.setVariable("objects", objects);
        // removed other assertion
        // removed other assertion
        // setting an index returns the old value
        // removed other assertion
        asserter.assertExpression("objects[1]", "dion");
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_1_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            asserter.assertExpression("foo[0]", foo);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_2_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            asserter.assertExpression("foo[0][0]", foo);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_3_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[1]", foo[1]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_4_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][1]", foo[1]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_5_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][1] = 43", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_6_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][1]", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_7_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][1] = 42", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_8_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][1]", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_9_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][1]", foo[1]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_10_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][1] = 43", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_11_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[0][0][1]", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_12_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[0][0][1] = 42", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_13_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[0][0][1]", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_14_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[2]", foo[2]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_15_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[0][2]", foo[2]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_16_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[0][2] = 'fourty-three'", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_17_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            asserter.assertExpression("foo[0][2]", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_18_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][2] = 'fourty-two'", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_19_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][2]", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_20_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][2]", foo[2]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_21_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][2] = 'fourty-three'", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_22_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][2]", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_23_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][2] = 'fourty-two'", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_24_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[0][0][2]", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_25_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

            asserter.assertExpression("foo[zero]", foo);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_26_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][zero]", foo);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_27_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[one]", foo[1]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_28_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][one]", foo[1]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_29_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][one] = 43", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_30_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][one]", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_31_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][one] = 42", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_32_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            asserter.assertExpression("foo[zero][one]", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_33_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][one]", foo[1]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_34_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][one] = 43", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_35_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][one]", i43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_36_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][one] = 42", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_37_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][one]", i42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_38_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[two]", foo[2]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_39_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][two]", foo[2]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_40_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][two] = 'fourty-three'", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_41_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][two]", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_42_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][two] = 'fourty-two'", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_43_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][two]", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_44_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][zero][two]", foo[2]);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_45_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][zero][two] = 'fourty-three'", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_46_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            asserter.assertExpression("foo[zero][zero][two]", s43);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_47_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][two] = 'fourty-two'", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayArray_48_oe() throws Exception {
        final Integer i42 = Integer.valueOf(42);
        final Integer i43 = Integer.valueOf(43);
        final String s42 = "fourty-two";
        final String s43 = "fourty-three";
        final Object[] foo = new Object[3];
        foo[0] = foo;
        foo[1] = i42;
        foo[2] = s42;
        asserter.setVariable("foo", foo);
        asserter.setVariable("zero", Integer.valueOf(0));
        asserter.setVariable("one", Integer.valueOf(1));
        asserter.setVariable("two", Integer.valueOf(2));
        for(int l = 0; l < 2; ++l) {
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
            // removed other assertion
            // removed other assertion
            asserter.assertExpression("foo[zero][zero][two]", s42);
    }
    }

// TODO: verify inlining
    @Test
    public void testArrayGetSet_1_oe() throws Exception {
        final Sample bar  = new Sample();
        bar.setFoo(new int[]{24});
        asserter.setVariable("bar", bar);
        asserter.assertExpression("bar.foo[0]", 24);
    }

// TODO: verify inlining
    @Test
    public void testArrayGetSet_2_oe() throws Exception {
        final Sample bar  = new Sample();
        bar.setFoo(new int[]{24});
        asserter.setVariable("bar", bar);
        // removed other assertion
        asserter.assertExpression("bar.foo = []", new int[0]);
    }

}