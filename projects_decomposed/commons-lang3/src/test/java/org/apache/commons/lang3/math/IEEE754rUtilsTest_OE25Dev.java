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
package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests {@link org.apache.commons.lang3.math.IEEE754rUtils}.
 */
public class IEEE754rUtilsTest_OE25Dev  {

    @Test
    public void testConstructorExists() {
        new IEEE754rUtils();
    }

    @Test
    public void testEnforceExceptions() {
        assertThrows(
                NullPointerException.class,
                () -> IEEE754rUtils.min( (float[]) null),
                "IllegalArgumentException expected for null input");

        assertThrows(
                IllegalArgumentException.class,
                IEEE754rUtils::min,
                "IllegalArgumentException expected for empty input");

        assertThrows(
                NullPointerException.class,
                () -> IEEE754rUtils.max( (float[]) null),
                "IllegalArgumentException expected for null input");

        assertThrows(
                IllegalArgumentException.class,
                IEEE754rUtils::max,
                "IllegalArgumentException expected for empty input");

        assertThrows(
                NullPointerException.class,
                () -> IEEE754rUtils.min( (double[]) null),
                "IllegalArgumentException expected for null input");

        assertThrows(
                IllegalArgumentException.class,
                IEEE754rUtils::min,
                "IllegalArgumentException expected for empty input");

        assertThrows(
                NullPointerException.class,
                () -> IEEE754rUtils.max( (double[]) null),
                "IllegalArgumentException expected for null input");

        assertThrows(
                IllegalArgumentException.class,
                IEEE754rUtils::max,
                "IllegalArgumentException expected for empty input");
    }

    @Test
    public void testLang381() {
        assertEquals(1.2, IEEE754rUtils.min(1.2, 2.5, Double.NaN), 0.01);
        assertEquals(2.5, IEEE754rUtils.max(1.2, 2.5, Double.NaN), 0.01);
        assertTrue(Double.isNaN(IEEE754rUtils.max(Double.NaN, Double.NaN, Double.NaN)));
        assertEquals(1.2f, IEEE754rUtils.min(1.2f, 2.5f, Float.NaN), 0.01);
        assertEquals(2.5f, IEEE754rUtils.max(1.2f, 2.5f, Float.NaN), 0.01);
        assertTrue(Float.isNaN(IEEE754rUtils.max(Float.NaN, Float.NaN, Float.NaN)));

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(42.0, IEEE754rUtils.max(a), 0.01);
        assertEquals(1.2, IEEE754rUtils.min(a), 0.01);

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(42.0, IEEE754rUtils.max(b), 0.01);
        assertEquals(1.2, IEEE754rUtils.min(b), 0.01);

        final float[] aF = new float[] { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(1.2f, IEEE754rUtils.min(aF), 0.01);
        assertEquals(42.0f, IEEE754rUtils.max(aF), 0.01);

        final float[] bF = new float[] { Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(1.2f, IEEE754rUtils.min(bF), 0.01);
        assertEquals(42.0f, IEEE754rUtils.max(bF), 0.01);
    }

    @Test
    public void testEnforceExceptions_1_oe() throws Exception {
        try {
    IEEE754rUtils.min( (float[]) null);
    fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_2_oe() throws Exception {

        try {
    IEEE754rUtils.min();
    fail("IllegalArgumentException: IllegalArgumentException expected for empty input");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEnforceExceptions_3_oe() throws Exception {


        try {
    IEEE754rUtils.max( (float[]) null);
    fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_4_oe() throws Exception {



        try {
    IEEE754rUtils.max();
    fail("IllegalArgumentException: IllegalArgumentException expected for empty input");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEnforceExceptions_5_oe() throws Exception {




        try {
    IEEE754rUtils.min( (double[]) null);
    fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_6_oe() throws Exception {





        try {
    IEEE754rUtils.min();
    fail("IllegalArgumentException: IllegalArgumentException expected for empty input");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testEnforceExceptions_7_oe() throws Exception {






        try {
    IEEE754rUtils.max( (double[]) null);
    fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_8_oe() throws Exception {







        try {
    IEEE754rUtils.max();
    fail("IllegalArgumentException: IllegalArgumentException expected for empty input");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testLang381_1_oe() {
        assertEquals(1.2, IEEE754rUtils.min(1.2, 2.5, Double.NaN), 0.01);
    }

    @Test
    public void testLang381_2_oe() {
        assertEquals(2.5, IEEE754rUtils.max(1.2, 2.5, Double.NaN), 0.01);
    }

    @Test
    public void testLang381_3_oe() {
        assertTrue(Double.isNaN(IEEE754rUtils.max(Double.NaN, Double.NaN, Double.NaN)));
    }

    @Test
    public void testLang381_4_oe() {
        assertEquals(1.2f, IEEE754rUtils.min(1.2f, 2.5f, Float.NaN), 0.01);
    }

    @Test
    public void testLang381_5_oe() {
        assertEquals(2.5f, IEEE754rUtils.max(1.2f, 2.5f, Float.NaN), 0.01);
    }

    @Test
    public void testLang381_6_oe() {
        assertTrue(Float.isNaN(IEEE754rUtils.max(Float.NaN, Float.NaN, Float.NaN)));
    }

    @Test
    public void testLang381_7_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(42.0, IEEE754rUtils.max(a), 0.01);
    }

    @Test
    public void testLang381_8_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(1.2, IEEE754rUtils.min(a), 0.01);
    }

    @Test
    public void testLang381_9_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(42.0, IEEE754rUtils.max(b), 0.01);
    }

    @Test
    public void testLang381_10_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertEquals(1.2, IEEE754rUtils.min(b), 0.01);
    }

    @Test
    public void testLang381_11_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final float[] aF = new float[] { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(1.2f, IEEE754rUtils.min(aF), 0.01);
    }

    @Test
    public void testLang381_12_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final float[] aF = new float[] { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(42.0f, IEEE754rUtils.max(aF), 0.01);
    }

    @Test
    public void testLang381_13_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final float[] aF = new float[] { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };

        final float[] bF = new float[] { Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(1.2f, IEEE754rUtils.min(bF), 0.01);
    }

    @Test
    public void testLang381_14_oe() {

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };

        final float[] aF = new float[] { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };

        final float[] bF = new float[] { Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertEquals(42.0f, IEEE754rUtils.max(bF), 0.01);
    }

}
