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
    public void testSetValue_1_oe() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, index+0.4f);
                int test = (int) instance.getValue(x, y);
                assertEquals(index, test, "Set/get value test failed");
    }
    }
    }

    @Test
    public void testSetValue_2_oe() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, index+0.4f);
                int test = (int) instance.getValue(x, y);
                instance.setIntValue(x, y, index);
                test = instance.getIntValue(x, y);
                assertEquals(index, test, "Set/get int value test failed");
    }
    }
    }

    @Test
    public void testGetValue_1_oe() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                int test = (int) raster.getValue(x, y);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

    @Test
    public void testGetValue_2_oe() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                int test = (int) raster.getValue(x, y);
                test = raster.getIntValue(x, y);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

    @Test
    public void testSetValue2_1_oe() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, 1, index+0.4f);
                int test = (int) instance.getValue(x, y, 1);
                assertEquals(index, test, "Set/get value test failed");
    }
    }
    }

    @Test
    public void testSetValue2_2_oe() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, 1, index+0.4f);
                int test = (int) instance.getValue(x, y, 1);
                instance.setIntValue(x, y, 1, index);
                test = instance.getIntValue(x, y, 1);
                assertEquals(index, test, "Set/get int value test failed");
    }
    }
    }

    @Test
    public void testGetValue2_1_oe() {
        int []data = new int[width*height*2];
        data[width*height] = 77;
        TiffRasterDataInt instance = new TiffRasterDataInt(width, height, 2, data);
        int test = instance.getIntValue(0, 0, 1);
        assertEquals(77, test, "Get into source data test failed at (0, 0, 1)");
    }

    @Test
    public void testGetValue2_2_oe() {
        int []data = new int[width*height*2];
        data[width*height] = 77;
        TiffRasterDataInt instance = new TiffRasterDataInt(width, height, 2, data);
        int test = instance.getIntValue(0, 0, 1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                test = (int) raster.getValue(x, y, 0);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
    }
    }
    }

    @Test
    public void testGetValue2_3_oe() {
        int []data = new int[width*height*2];
        data[width*height] = 77;
        TiffRasterDataInt instance = new TiffRasterDataInt(width, height, 2, data);
        int test = instance.getIntValue(0, 0, 1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                test = (int) raster.getValue(x, y, 0);
                test = raster.getIntValue(x, y, 0);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
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
        assertEquals(width * height - 1, result.getMaxValue(), "Max value failure");
    }

    @Test
    public void testGetSimpleStatistics_0args_3_oe() {

        final TiffRasterStatistics result = raster.getSimpleStatistics();
        assertEquals(meanValue, result.getMeanValue(), "Mean value failure");
    }

    @Test
    public void testGetSimpleStatistics_float_1_oe() {
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
        for(int i=0; i<result.length; i++){
            assertEquals((int)result[i], data[i]);
    }
    }

    @Test
    public void testGetData_2_oe() {
        final float[] result = raster.getData();
        for(int i=0; i<result.length; i++){
        }
        final int []iResult = raster.getIntData();
        assertArrayEquals(data, iResult);
    }

    @Test
    public void testGetDataType_1_oe() {
        TiffRasterDataType dataType = raster.getDataType();
        assertTrue(dataType == TiffRasterDataType.INTEGER, "Unexpected data type "+dataType.name());
    }

    @Test
    public void testBadConstructor_1_oe() throws Exception {
        try {
    new TiffRasterDataInt(-1, 10);
    fail("IllegalArgumentException: Constructor did not detect bad width");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_2_oe() throws Exception {
        try {
    new TiffRasterDataInt(10, -1);
    fail("IllegalArgumentException: Constructor did not detect bad height");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_3_oe() throws Exception {
        try {
    new TiffRasterDataInt(1, 1, 0);
    fail("IllegalArgumentException: Constructor did not detect bad samplesPerPixel");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_4_oe() throws Exception {

        final int []s = new int[10];
        try {
    new TiffRasterDataInt(2, 10, s);
    fail("IllegalArgumentException: Constructor did not detect insufficient input array size");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testBadConstructor_5_oe() throws Exception {

        final int []s = new int[10];
        try {
    new TiffRasterDataInt(2, 3, 2, s);
    fail("IllegalArgumentException: Constructor did not detect insufficient input array size");
} catch (IllegalArgumentException e) {
}
    }

}
