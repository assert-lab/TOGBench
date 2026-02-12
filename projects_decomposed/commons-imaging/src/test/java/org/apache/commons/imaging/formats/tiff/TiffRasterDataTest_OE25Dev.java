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
package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Provides unit test for the raster-data class.
 */
public class TiffRasterDataTest_OE25Dev {

    int width = 11;
    int height = 10;
    float[] data;
    TiffRasterData raster;
    float meanValue;

    public TiffRasterDataTest_OE25Dev() {
        double sum = 0;
        data = new float[width * height];
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[k] = k;
                sum += k;
                k++;
            }
        }
        raster = new TiffRasterDataFloat(width, height, data);
        meanValue = (float) (sum / k);
    }

    /**
     * Test of setValue method, of class TiffRasterData.
     */

    /**
     * Test of getValue method, of class TiffRasterData.
     */

    /**
     * Test of setValue method, of class TiffRasterData.
     */

    /**
     * Test of getValue method, of class TiffRasterData.
     */

    /**
     * Test of getSimpleStatistics method, of class TiffRasterData.
     */

    /**
     * Test of getSimpleStatistics method, of class TiffRasterData.
     */

    /**
     * Test of getWidth method, of class TiffRasterData.
     */

    /**
     * Test of getHeight method, of class TiffRasterData.
     */

    /**
     * Test of getData method, of class TiffRasterData.
     */

    /**
     * Test of getData method, of class TiffRasterData.
     */


    /**
     * Test of getData method, of class TiffRasterData.
     */


    /**
     * Test of constructors with bad arguments, of class TiffRasterData.
     */

    /**
     * Test of access with bad coordinates, of class TiffRasterData.
     */

    @Test
    public void testBadConstructor_1_oe() throws Exception {
        try {
    new TiffRasterDataFloat(-1, 10);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect bad width");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_2_oe() throws Exception {
        // removed other assertion
        try {
    new TiffRasterDataFloat(10, -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect bad height");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    new TiffRasterDataFloat(1, 1, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect bad samplesPerPixel");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float []f = new float[10];
        try {
    new TiffRasterDataFloat(2, 10, f);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect insufficient input array size");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float []f = new float[10];
        // removed other assertion
        try {
    new TiffRasterDataFloat(2, 3, 2, f);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect insufficient input array size");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadCoordinates_1_oe() throws Exception {
        final float []f = new float[100];
        final TiffRasterData instance = new TiffRasterDataFloat(10, 10, 1, f);
        try {
    instance.getValue(11, 11);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Access method getValue() did not detect bad coordinates");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadCoordinates_2_oe() throws Exception {
        final float []f = new float[100];
        final TiffRasterData instance = new TiffRasterDataFloat(10, 10, 1, f);
        // removed other assertion
        try {
    instance.setValue(11, 11, 5.0f);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Access method setValue() did not detect bad coordinates");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadCoordinates_3_oe() throws Exception {
        final float []f = new float[100];
        final TiffRasterData instance = new TiffRasterDataFloat(10, 10, 1, f);
        // removed other assertion
        // removed other assertion
        try {
    instance.getValue(1, 1, 2);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Access method setValue() did not detect bad sample index");
} catch (IllegalArgumentException e) {
}
    }

}
