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
package org.apache.commons.imaging.common;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RasterFormatException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Provides unit tests for the ImageBuilder class.
 */
public class ImageBuilderTest_OE25Dev {


    /**
     * Test of bad dimensions in constructor
     */
    @Test
    public void testConstructorBounds() {
        executeBadConstructor(0, 10);
        executeBadConstructor(10, 0);
    }


    /**
     * Test of bad bounds in sub-image
     */
    @Test
    public void testBoundsCheck() {

        final ImageBuilder imageBuilder = new ImageBuilder(100, 100, false );

        executeBadBounds(imageBuilder, -1,  0, 50, 50);
        executeBadBounds(imageBuilder,  0, -1, 50, 50);
        executeBadBounds(imageBuilder,  0,  0,  0, 50);
        executeBadBounds(imageBuilder,  0,  0, 50, 0);
        executeBadBounds(imageBuilder, 90,  0, 50, 50);
        executeBadBounds(imageBuilder,  0, 90, 50, 50);
    }

    /**
     * Test whether sub-image is consistent with source
     */

    /**
     * Test whether color model is properly applied to buffered images
     */

    void executeBadBounds(final ImageBuilder imageBuilder, final int x, final int y, final int w, final int h){
        try{
            final ImageBuilder sub = imageBuilder.getSubset(x, y, w, h);
            fail("Failed to detect bad bounds "+x+", "+y+", "+w+", "+h);
        }catch(final RasterFormatException rfe){
            // success, no action required
        }
    }

    void executeBadConstructor(final int w, final int h){
        try{
            final ImageBuilder iBuilder = new ImageBuilder(w, h, true);
            fail("Failed to detect bad constructor "+w+", "+h);
        }catch(final RasterFormatException rfe){
            // success, no action required
        }
    }


    void populate(final ImageBuilder imageBuilder){
        for(int x=0; x<100; x++){
            for(int y=0; y<100; y++){
                final int k = y*100+x;
                final int rgb = 0xff000000|k;
                imageBuilder.setRGB(x, y, rgb);
            }
        }
    }




}
