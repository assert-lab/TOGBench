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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Provides unit tests for the TIFF simple-statistics class
 */
public class TiffRasterStatisticsTest_OE25Dev {

    int width = 11;
    int height = 10;
    float[] data;
    TiffRasterData raster;
    float meanValue;
    TiffRasterStatistics stat0;
    TiffRasterStatistics stat1;

    public TiffRasterStatisticsTest_OE25Dev() {
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
        data[width * height / 2] = Float.NaN;
        raster = new TiffRasterDataFloat(width, height, data);
        meanValue = (float) (sum / k);
        stat0 = raster.getSimpleStatistics();
        stat1 = raster.getSimpleStatistics(stat0.getMaxValue());
    }

    /**
     * Test of getCountOfSamples method, of class TiffRasterStatistics.
     */

    /**
     * Test of getCountOfNulls method, of class TiffRasterStatistics.
     */

    /**
     * Test of getMinValue method, of class TiffRasterStatistics.
     */

    /**
     * Test of getMaxValue method, of class TiffRasterStatistics.
     */

    /**
     * Test of getMeanValue method, of class TiffRasterStatistics.
     */

    /**
     * Test of isAnExcludedValueSet method, of class TiffRasterStatistics.
     */

    /**
     * Test of getExcludedValue method, of class TiffRasterStatistics.
     */


}
