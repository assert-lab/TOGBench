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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Performs unit tests for palette entries based on single-value targets.
 */
public class PaletteEntryForValueTest_OE25Dev {

    public PaletteEntryForValueTest_OE25Dev() {
    }

    /**
     * Test of isCovered method, of class PaletteEntryForValue.
     */

    /**
     * Test of getARGB method, of class PaletteEntryForValue.
     */

    /**
     * Test of getColor method, of class PaletteEntryForValue.
     */


    @Test
    public void testFaultyConstructors() {
        final Color c0 = new Color(0xff0000ff);
        final Color c1 = new Color(0xff00ff00);
        PaletteEntryForValue pTest;


        try {
            pTest = new PaletteEntryForValue(0.0f, null);
            fail("Constructor failed to detect null color");
        } catch (final IllegalArgumentException iex) {
            // successful test
        }

    }


}
