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
    public void testSetValue_1_oe() {
        final TiffRasterData instance = new TiffRasterDataFloat(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, index);
                final int test = (int) instance.getValue(x, y);
                assertEquals(index, test, "Set/get value test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testSetValue_2_oe() {
        final TiffRasterData instance = new TiffRasterDataFloat(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, index);
                final int test = (int) instance.getValue(x, y);
                // removed other assertion
                instance.setIntValue(x, y, index);
                final int iTest = instance.getIntValue(x, y);
                assertEquals(index, iTest, "Get/set value test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testGetValue_1_oe() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                final int test = (int) raster.getValue(x, y);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testGetValue_2_oe() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                final int test = (int) raster.getValue(x, y);
                // removed other assertion
                final int iTest = raster.getIntValue(x, y);
                assertEquals(index, iTest, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testSetValue2_1_oe() {
        final TiffRasterData instance = new TiffRasterDataFloat(width, height, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, 1, index);
                final int test = (int) instance.getValue(x, y, 1);
                assertEquals(index, test, "Set/get value test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testSetValue2_2_oe() {
        final TiffRasterData instance = new TiffRasterDataFloat(width, height, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, 1, index);
                final int test = (int) instance.getValue(x, y, 1);
                // removed other assertion
                instance.setIntValue(x, y, 1, index);
                final int iTest = instance.getIntValue(x, y, 1);
                assertEquals(index, iTest, "Get/set value test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testGetValue2_1_oe() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                final int test = (int) raster.getValue(x, y, 0);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testGetValue2_2_oe() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                final int test = (int) raster.getValue(x, y, 0);
                // removed other assertion
                final int iTest = raster.getIntValue(x, y, 0);
                assertEquals(index, iTest, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testGetSimpleStatistics_0args_1_oe() {

        final TiffRasterStatistics result = raster.getSimpleStatistics();
        assertEquals(0, result.getMinValue(), "Min value failure");
    }

@Test
    public void testGetSimpleStatistics_0args_2_oe() {

        final TiffRasterStatistics result = raster.getSimpleStatistics();
        // removed other assertion
        assertEquals(width * height - 1, result.getMaxValue(), "Max value failure");
    }

@Test
    public void testGetSimpleStatistics_0args_3_oe() {

        final TiffRasterStatistics result = raster.getSimpleStatistics();
        // removed other assertion
        // removed other assertion
        assertEquals(meanValue, result.getMeanValue(), "Mean value failure");
    }

@Test
    public void testGetSimpleStatistics_float_1_oe() {
        // exclude the maximum value (width*height-1).  This will result
        // in a max value of width*height-2
        final TiffRasterStatistics result = raster.getSimpleStatistics(width * height - 1);
        assertEquals(width * height - 2, result.getMaxValue(), "Max value failure");
    }

@Test
    public void testGetWidth_1_oe() {
        assertEquals(width, raster.getWidth(), "Improper width stored");
    }

@Test
    public void testGetHeight_1_oe() {
        assertEquals(width, raster.getWidth(), "Improper height stored");
    }

@Test
    public void testGetData_1_oe() {
        final float[] result = raster.getData();
        assertArrayEquals(data, result);
    }

@Test
    public void testGetData_2_oe() {
        final float[] result = raster.getData();
        // removed other assertion
        int samplesPerPixel = raster.getSamplesPerPixel();
        assertEquals(1, samplesPerPixel, "Incorrect number of samples per pixel");
    }

@Test
    public void testGetIntData_1_oe() {
        final int[] result = raster.getIntData();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                final int test = (int) data[index];
                assertEquals(index, test, "Integer array access test failed at (" + x + "," + y + ")");
    }
    }
    }

@Test
    public void testGetDataType_1_oe() {
        TiffRasterDataType dataType = raster.getDataType();
        assertTrue(dataType == TiffRasterDataType.FLOAT, "Unexpected data type "+dataType.name());
    }

@Test
    public void testBadConstructor_1_oe() {
        assertThrows(IllegalArgumentException.class, ()-> new TiffRasterDataFloat(-1, 10), "Constructor did not detect bad width");
    }

@Test
    public void testBadConstructor_2_oe() {
        // removed other assertion
        assertThrows(IllegalArgumentException.class, ()-> new TiffRasterDataFloat(10, -1), "Constructor did not detect bad height");
    }

@Test
    public void testBadConstructor_3_oe() {
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, ()-> new TiffRasterDataFloat(1, 1, 0), "Constructor did not detect bad samplesPerPixel");
    }

@Test
    public void testBadConstructor_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float []f = new float[10];
        assertThrows(IllegalArgumentException.class, ()-> new TiffRasterDataFloat(2, 10, f), "Constructor did not detect insufficient input array size");
    }

@Test
    public void testBadConstructor_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final float []f = new float[10];
        // removed other assertion
        assertThrows(IllegalArgumentException.class, ()-> new TiffRasterDataFloat(2, 3, 2, f), "Constructor did not detect insufficient input array size");
    }

@Test
    public void testBadCoordinates_1_oe() {
        final float []f = new float[100];
        final TiffRasterData instance = new TiffRasterDataFloat(10, 10, 1, f);
        assertThrows(IllegalArgumentException.class, ()->instance.getValue(11, 11),       "Access method getValue() did not detect bad coordinates");
    }

@Test
    public void testBadCoordinates_2_oe() {
        final float []f = new float[100];
        final TiffRasterData instance = new TiffRasterDataFloat(10, 10, 1, f);
        // removed other assertion
        assertThrows(IllegalArgumentException.class, ()->instance.setValue(11, 11, 5.0f), "Access method setValue() did not detect bad coordinates");
    }

@Test
    public void testBadCoordinates_3_oe() {
        final float []f = new float[100];
        final TiffRasterData instance = new TiffRasterDataFloat(10, 10, 1, f);
        // removed other assertion
        // removed other assertion
        assertThrows(IllegalArgumentException.class, ()->instance.getValue(1, 1, 2),  "Access method setValue() did not detect bad sample index");
    }

}
