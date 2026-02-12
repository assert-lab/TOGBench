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

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TiffSubImageTest_OE25Dev extends TiffBaseTest {
    final List<File> imageFileList;

    TiffSubImageTest_OE25Dev() throws IOException, ImageReadException{
        imageFileList = getTiffImages();
    }

    @Test
    public void testSubImage() throws ImageReadException, ImageWriteException, IOException {
        final TiffImageParser tiffImageParser = new TiffImageParser();
        final BufferedImage src = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        final TiffImagingParameters params = new TiffImagingParameters();
        final byte[] imageBytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            tiffImageParser.writeImage(src, baos, params);
            imageBytes = baos.toByteArray();
        }

        params.setSubImage(0, 0, 2, 3);
        final BufferedImage image = tiffImageParser.getBufferedImage(imageBytes, params);
        assertEquals(image.getWidth(), 2);
        assertEquals(image.getHeight(), 3);
    }

    private void processBadParams(final File target, final int x, final int y, final int width, final int height, final String comment) throws IOException{
        final TiffImageParser tiffImageParser = new TiffImageParser();
        try {
            final TiffImagingParameters params = new TiffImagingParameters();
            params.setSubImage(x, y, width, height);
            tiffImageParser.getBufferedImage(target, params);
            fail("Reading TIFF sub-image failed to detect bad parameter: "+comment);
        }catch(final ImageReadException | IllegalArgumentException ire){
            // the test passed
        }
    }



}