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
package org.apache.commons.imaging.formats.pnm;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PnmImageParserTest_OE25Dev {

    private static final Charset US_ASCII = StandardCharsets.US_ASCII;

    /**
     * If an invalid width is specified, should throw {@link ImageReadException} rather than
     * {@link NumberFormatException}.
     */

    @Test
    public void testGetImageInfo_invalidWidth_1_oe() throws Exception {
        final byte[] bytes = "P1\na 2\n0 0 0 0 0 0 0 0 0 0 0\n1 1 1 1 1 1 1 1 1 1 1\n".getBytes(US_ASCII);
        final PnmImagingParameters params = new PnmImagingParameters();
        final PnmImageParser underTest = new PnmImageParser();
        try {
    underTest.getImageInfo(bytes, params);
    org.junit.jupiter.api.Assertions.fail("ImageReadException");
} catch (ImageReadException e) {
}
    }

    @Test
    public void testGetImageInfo_invalidHeight_1_oe() throws Exception {
        final byte[] bytes = "P1\n2 a\n0 0\n0 0\n0 0\n0 0\n0 0\n0 1\n1 1\n1 1\n1 1\n1 1\n1 1\n".getBytes(US_ASCII);
        final PnmImagingParameters params = new PnmImagingParameters();
        final PnmImageParser underTest = new PnmImageParser();
        try {
    underTest.getImageInfo(bytes, params);
    org.junit.jupiter.api.Assertions.fail("ImageReadException");
} catch (ImageReadException e) {
}
    }

    @Test
    public void testGetImageInfo_missingWidthValue_1_oe() throws Exception {
        final byte[] bytes = "P7\nWIDTH \n".getBytes(US_ASCII);
        final PnmImagingParameters params = new PnmImagingParameters();
        final PnmImageParser underTest = new PnmImageParser();
        try {
    underTest.getImageInfo(bytes, params);
    org.junit.jupiter.api.Assertions.fail("ImageReadException");
} catch (ImageReadException e) {
}
    }

}
