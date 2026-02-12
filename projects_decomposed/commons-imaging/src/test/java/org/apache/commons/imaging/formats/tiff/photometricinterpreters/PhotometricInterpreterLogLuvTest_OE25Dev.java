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
package org.apache.commons.imaging.formats.tiff.photometricinterpreters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterLogLuv.TristimulusValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class PhotometricInterpreterLogLuvTest_OE25Dev {

    private PhotometricInterpreterLogLuv p;

    private final int samplesPerPixel = 8;
    private final int[] bitsPerSample = new int[] {1, 2, 3};
    private final int predictor = 1;
    private final int width = 800;
    private final int height = 600;

    @BeforeEach
    public void setUp() {
        p = new PhotometricInterpreterLogLuv(samplesPerPixel, bitsPerSample, predictor,
                width, height);
    }

    @Test
    public void testConstructor_1_oe() {
        assertEquals(samplesPerPixel, p.samplesPerPixel);
    }

    @Test
    public void testConstructor_2_oe() {
        // removed other assertion
        for (int i = 0; i < bitsPerSample.length; i++) {
            assertEquals(bitsPerSample[i], p.getBitsPerSample(i));
    }
    }

    @Test
    public void testConstructor_3_oe() {
        // removed other assertion
        for (int i = 0; i < bitsPerSample.length; i++) {
            // removed other assertion
        }
        assertEquals(predictor, p.predictor);
    }

    @Test
    public void testConstructor_4_oe() {
        // removed other assertion
        for (int i = 0; i < bitsPerSample.length; i++) {
            // removed other assertion
        }
        // removed other assertion
        assertEquals(width, p.width);
    }

    @Test
    public void testConstructor_5_oe() {
        // removed other assertion
        for (int i = 0; i < bitsPerSample.length; i++) {
            // removed other assertion
        }
        // removed other assertion
        // removed other assertion
        assertEquals(height, p.height);
    }

    @Test
    public void testGetTristimulusValues_1_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        assertEquals(0.0d, p.getTristimulusValues(0, 0, 0).x, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_2_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        assertEquals(0.0d, p.getTristimulusValues(0, 0, 0).y, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_3_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        assertEquals(0.0d, p.getTristimulusValues(0, 0, 0).z, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_4_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        assertEquals(0.04126d, p.getTristimulusValues(1, 0, 0).x, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_5_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        assertEquals(0.04341d, p.getTristimulusValues(1, 0, 0).y, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_6_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        // removed other assertion
        assertEquals(0.04727d, p.getTristimulusValues(1, 0, 0).z, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_7_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        assertEquals(29.36116d, p.getTristimulusValues(100, 100, 50).x, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_8_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        assertEquals(10.78483d, p.getTristimulusValues(100, 100, 50).y, 0.001d);
    }

    @Test
    public void testGetTristimulusValues_9_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // values under the threshold used in the if statements
        // removed other assertion
        // removed other assertion
        assertEquals(1.25681d, p.getTristimulusValues(100, 100, 50).z, 0.001d);
    }

    @Test
    public void testGetRgbValues_1_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        final TristimulusValues triValues = new TristimulusValues();
        triValues.x = 0;
        triValues.y = 0;
        triValues.z = 0;
        assertEquals(0, p.getRgbValues(triValues).r);
    }

    @Test
    public void testGetRgbValues_2_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        final TristimulusValues triValues = new TristimulusValues();
        triValues.x = 0;
        triValues.y = 0;
        triValues.z = 0;
        // removed other assertion
        assertEquals(0, p.getRgbValues(triValues).g);
    }

    @Test
    public void testGetRgbValues_3_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        final TristimulusValues triValues = new TristimulusValues();
        triValues.x = 0;
        triValues.y = 0;
        triValues.z = 0;
        // removed other assertion
        // removed other assertion
        assertEquals(0, p.getRgbValues(triValues).b);
    }

    @Test
    public void testGetRgbValues_4_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        final TristimulusValues triValues = new TristimulusValues();
        triValues.x = 0;
        triValues.y = 0;
        triValues.z = 0;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        triValues.x = 1;
        triValues.y = 1;
        triValues.z = 1;
        assertEquals(28, p.getRgbValues(triValues).r);
    }

    @Test
    public void testGetRgbValues_5_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        final TristimulusValues triValues = new TristimulusValues();
        triValues.x = 0;
        triValues.y = 0;
        triValues.z = 0;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        triValues.x = 1;
        triValues.y = 1;
        triValues.z = 1;
        // removed other assertion
        assertEquals(24, p.getRgbValues(triValues).g);
    }

    @Test
    public void testGetRgbValues_6_oe() {
        // any value equals 0 will have its pow(N, 3) equal to 0
        final TristimulusValues triValues = new TristimulusValues();
        triValues.x = 0;
        triValues.y = 0;
        triValues.z = 0;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        triValues.x = 1;
        triValues.y = 1;
        triValues.z = 1;
        // removed other assertion
        // removed other assertion
        assertEquals(23, p.getRgbValues(triValues).b);
    }

    @Test
    public void testInterpretPixelNullSamples_1_oe() throws Exception {
        try {
    p.interpretPixel(null, null, 0, 0);
    fail("ImageReadException");
} catch (ImageReadException e) {
}
    }

    @Test
    public void testInterpretPixelEmptySamples_1_oe() throws Exception {
        try {
    p.interpretPixel(null, new int[] {}, 0, 0);
    fail("ImageReadException");
} catch (ImageReadException e) {
}
    }

    @Test
    public void testInterpretPixel_1_oe() throws ImageReadException, IOException {
        final ImageBuilder imgBuilder = new ImageBuilder(600, 400, /*alpha*/ true);
        final int x = 10;
        final int y = 20;
        p.interpretPixel(imgBuilder, new int[] {100, (byte) 32, (byte) 2}, x, y);
        assertEquals(-7584166, imgBuilder.getRGB(x, y));
    }

}
