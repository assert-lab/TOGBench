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

/**
 * Unit tests {@link org.apache.commons.lang3.math.IEEE754rUtils}.
 */
public class IEEE754rUtilsTest_OE25Dev  {

    @Test
    public void testConstructorExists() {
        new IEEE754rUtils();
    }

    @Test
    public void testEnforceExceptions_1_oe() throws Exception {
        try {
    IEEE754rUtils.min( (float[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_2_oe() {
        // removed other assertion

        assertThrows( IllegalArgumentException.class, IEEE754rUtils::min, "IllegalArgumentException expected for empty input");
    }

    @Test
    public void testEnforceExceptions_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        try {
    IEEE754rUtils.max( (float[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_4_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, IEEE754rUtils::max, "IllegalArgumentException expected for empty input");
    }

    @Test
    public void testEnforceExceptions_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    IEEE754rUtils.min( (double[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_6_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, IEEE754rUtils::min, "IllegalArgumentException expected for empty input");
    }

    @Test
    public void testEnforceExceptions_7_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    IEEE754rUtils.max( (double[]) null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException: IllegalArgumentException expected for null input");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testEnforceExceptions_8_oe() {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertThrows( IllegalArgumentException.class, IEEE754rUtils::max, "IllegalArgumentException expected for empty input");
    }

}
