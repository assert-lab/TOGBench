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
package org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Performs unit tests for palette entries based on range of values.
 */
public class PaletteEntryForRangeTest_OE25Dev {

    public PaletteEntryForRangeTest_OE25Dev() {
    }

    /**
     * Test of isCovered method, of class PaletteEntryForRange.
     */

    /**
     * Test of getARGB method, of class PaletteEntryForRange.
     */

    /**
     * Test of getColor method, of class PaletteEntryForRange.
     */

    /**
     * Test of coversSingleEntry method, of class PaletteEntryForRange.
     */

    /**
     * Test of getLowerBound method, of class PaletteEntryForRange.
     */

    /**
     * Test of getUpperBound method, of class PaletteEntryForRange.
     */

    @Test
    public void testFaultyConstructors() {
        final Color c0 = new Color(0xff0000ff);
        final Color c1 = new Color(0xff00ff00);
        PaletteEntryForRange pTest;

        // test the two-color variations -----------------------
        try {
            pTest = new PaletteEntryForRange(0.0f, 0.0f, c0, c1);
            fail("Constructor failed to detect invalid range");
        } catch (final IllegalArgumentException iex) {
            // successful test
        }

        try {
            pTest = new PaletteEntryForRange(0.0f, 1.0f, null, c1);
            fail("Constructor failed to detect null color");
        } catch (final IllegalArgumentException iex) {
            // successful test
        }
        try {
            pTest = new PaletteEntryForRange(0.0f, 1.0f, c0, null);
            fail("Constructor failed to detect invalid color");
        } catch (final IllegalArgumentException iex) {
            // successful test
        }

        // test the one-color variations -----------------------
        try {
            pTest = new PaletteEntryForRange(0.0f, 0.0f, c0);
            fail("Constructor failed to detect invalid range");
        } catch (final IllegalArgumentException iex) {
            // successful test
        }

        try {
            pTest = new PaletteEntryForRange(0.0f, 1.0f, null);
            fail("Constructor failed to detect null color");
        } catch (final IllegalArgumentException iex) {
            // successful test
        }
    }


}
