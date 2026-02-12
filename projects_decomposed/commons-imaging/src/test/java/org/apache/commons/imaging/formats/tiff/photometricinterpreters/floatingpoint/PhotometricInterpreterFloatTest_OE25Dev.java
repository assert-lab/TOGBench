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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Provides a unit test for the TIFF photometric interpreter used for mapping
 * floating-point values to a color palette.
 */
public class PhotometricInterpreterFloatTest_OE25Dev {

    private static PhotometricInterpreterFloat pInterp;
    private static PhotometricInterpreterFloat bandedInterp;
    private static ImageBuilder imageBuilder;
    private static ImageBuilder bandedImageBuilder;

    private static final Color orange = new Color(255, 136, 62);
    private static final Color green = new Color(22, 155, 98);

    public PhotometricInterpreterFloatTest_OE25Dev() {
    }

    @BeforeAll
    public static void setUpClass() throws ImageReadException, IOException {
        // the setup is to assign color (grayscale) values to the
        // pixels along the main diagonal at coordinates
        // (0, 0), (1, 1), ... (256, 256).
        // The floating point values at each pixel are just the
        // index divided by 256.

        final List<PaletteEntry> paletteList = new ArrayList<>();
        final List<PaletteEntry> reverseList = new ArrayList<>();
        for (int i = 0; i < 256; i += 32) {
            final int i1 = i + 31;
            final float f0 = i / 256f;
            final float f1 = (i + 32) / 256f;
            final int argb0 = 0xff000000 | (i << 8) | i;
            final int argb1 = 0xff000000 | (i1 << 8) | i;
            final Color c0 = new Color(argb0);
            final Color c1 = new Color(argb1);
            final PaletteEntryForRange entry = new PaletteEntryForRange(f0, f1, c0, c1);
            paletteList.add(entry);
        }
        // The interpreter is supposed to sort entries.  To test that,
        // we copy them to a list in reverse order.
        for (int i = paletteList.size() - 1; i >= 0; i--) {
            final PaletteEntry entry = paletteList.get(i);
            reverseList.add(entry);
        }

        pInterp = new PhotometricInterpreterFloat(reverseList);

        // pre-populate the state data for the interpreter with
        // some values so that we can test min/max access methods.
        imageBuilder = new ImageBuilder(257, 257, false);
        final int[] samples = new int[1];
        for (int i = 0; i <= 256; i++) {
            final float f = i / 256f;
            samples[0] = Float.floatToRawIntBits(f);
            pInterp.interpretPixel(imageBuilder, samples, i, i);
        }

        // Now set up a palette than maps values in a range to a single color.
        final List<PaletteEntry> bandedPaletteList = new ArrayList<>();
        bandedPaletteList.add(new PaletteEntryForRange(0f, 0.33f, green));
        bandedPaletteList.add(new PaletteEntryForRange(0.33f, 0.66f, Color.white));
        bandedPaletteList.add(new PaletteEntryForRange(0.66f, 1.0f, orange));
        bandedPaletteList.add(new PaletteEntryForValue(Float.NaN, Color.gray));
        bandedPaletteList.add(new PaletteEntryForValue(-1, Color.gray));
        bandedInterp = new PhotometricInterpreterFloat(bandedPaletteList);
        bandedImageBuilder = new ImageBuilder(300, 200, false);
        for (int j = 0; j < 300; j++) {
            final float f = j / 299.0f;
            samples[0] = Float.floatToRawIntBits(f);
            for (int i = 0; i < 200; i++) {
                bandedInterp.interpretPixel(bandedImageBuilder, samples, j, i);
            }
        }
        samples[0] = Float.floatToRawIntBits(Float.NaN);
        for (int i = 0; i < 200; i++) {
            bandedInterp.interpretPixel(bandedImageBuilder, samples, 0, i);
            bandedInterp.interpretPixel(bandedImageBuilder, samples, 299, i);
        }
        samples[0] = Float.floatToRawIntBits(-1);
        for (int i = 0; i < 300; i++) {
            bandedInterp.interpretPixel(bandedImageBuilder, samples, i, 0);
            bandedInterp.interpretPixel(bandedImageBuilder, samples, i, 199);
        }
    }

    /**
     * Test of interpretPixel method, of class PhotometricInterpreterFloat.
     */

    /**
     * Test of getMinFound method, of class PhotometricInterpreterFloat.
     */

    /**
     * Test of getMaxXY method, of class PhotometricInterpreterFloat.
     */

    /**
     * Test of getMaxFound method, of class PhotometricInterpreterFloat.
     */

    /**
     * Test of getMinXY method, of class PhotometricInterpreterFloat.
     */

    /**
     * Test of getMeanFound method, of class PhotometricInterpreterFloat.
     */

    /**
     * Test of interpretPixel method, of class PhotometricInterpreterFloat.
     */

    @Test
    public void testConstructors() {
        PhotometricInterpreterFloat ptest;
        ptest = new PhotometricInterpreterFloat(0, 1);
        ptest = new PhotometricInterpreterFloat(1, 0);
        try {
            ptest = new PhotometricInterpreterFloat(null);
            fail("Constructor failed to detect null arguments");
        } catch (final IllegalArgumentException iex) {

        }

        try {
            ptest = new PhotometricInterpreterFloat(0.1f, 0.1f);
            fail("Constructor failed to detect bad-range argument values");
        } catch (final IllegalArgumentException iex) {

        }

    }

     /**
     * Test of overlapping entries
     */


}
