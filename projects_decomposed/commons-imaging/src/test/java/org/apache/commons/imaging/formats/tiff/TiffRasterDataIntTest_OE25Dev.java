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
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Provides unit test for the raster-data class.
 */
public class TiffRasterDataIntTest_OE25Dev {

    int width = 11;
    int height = 10;
    int[] data;
    TiffRasterData raster;
    float meanValue;

    public TiffRasterDataIntTest_OE25Dev() {
        double sum = 0;
        data = new int[width * height];
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[k] = k;
                sum += k;
                k++;
            }
        }
        raster = new TiffRasterDataInt(width, height, data);
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
     * Test of constructors with bad arguments, of class TiffRasterDataInt.
     */

    /**
     * Test of access with bad coordinates, of class TiffRasterData.
     */
    @Test
    public void testBadCoordinates() {

        try{
            final int []sample = new int[100];
            final TiffRasterData raster = new TiffRasterDataInt(10, 10, sample);
            raster.getIntValue(11, 11);
            fail("Access method getValue() did not detect bad coordinates");
        }catch(final IllegalArgumentException illArgEx){
            // success!
        }
        try{
            final int []sample = new int[100];
            final TiffRasterData raster = new TiffRasterDataInt(10, 10, sample);
            raster.setValue(11, 11, 5.0f);
            fail("Access method setValue() did not detect bad coordinates");
        }catch(final IllegalArgumentException illArgEx){
            // success!
        }
    }

    @Test
    public void testBadConstructor_1_oe() throws Exception {
        try {
    new TiffRasterDataInt(-1, 10);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect bad width");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_2_oe() throws Exception {
        // removed other assertion
        try {
    new TiffRasterDataInt(10, -1);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect bad height");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    new TiffRasterDataInt(1, 1, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect bad samplesPerPixel");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final int []s = new int[10];
        try {
    new TiffRasterDataInt(2, 10, s);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect insufficient input array size");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final int []s = new int[10];
        // removed other assertion
        try {
    new TiffRasterDataInt(2, 3, 2, s);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException: Constructor did not detect insufficient input array size");
} catch (IllegalArgumentException e) {
}
    }

}
