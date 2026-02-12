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

package org.apache.commons.imaging.common.bytesource;

import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingParameters;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import org.apache.commons.imaging.internal.Debug;
import org.apache.commons.imaging.internal.Util;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByteSourceImageTest_OE25Dev extends ByteSourceTest {

    public static Stream<File> data() throws Exception {
        return getTestImages().stream();
    }

    public void checkGetBufferedImage(final File file, final byte[] bytes) throws Exception {
        final BufferedImage bufferedImage = Imaging.getBufferedImage(file);
        assertNotNull(bufferedImage);
        assertTrue(bufferedImage.getWidth() > 0);
        assertTrue(bufferedImage.getHeight() > 0);
        final int imageFileWidth = bufferedImage.getWidth();
        final int imageFileHeight = bufferedImage.getHeight();

        final BufferedImage imageBytes = Imaging.getBufferedImage(bytes);
        assertNotNull(imageBytes);
        assertEquals(imageFileWidth, imageBytes.getWidth());
        assertEquals(imageFileHeight, imageBytes.getHeight());
    }

    public void checkGetImageSize(final File imageFile, final byte[] imageFileBytes)
            throws Exception {
        final Dimension imageSizeFile = Imaging.getImageSize(imageFile);
        assertNotNull(imageSizeFile);
        assertTrue(imageSizeFile.width > 0);
        assertTrue(imageSizeFile.height > 0);

        final Dimension imageSizeBytes = Imaging.getImageSize(imageFileBytes);
        assertNotNull(imageSizeBytes);
        assertEquals(imageSizeFile.width, imageSizeBytes.width);
        assertEquals(imageSizeFile.height, imageSizeBytes.height);
    }

    public void checkGuessFormat(final File imageFile, final byte[] imageFileBytes)
            throws Exception {
        // check guessFormat()
        final ImageFormat imageFormatFile = Imaging.guessFormat(imageFile);
        assertNotNull(imageFormatFile);
        assertNotSame(imageFormatFile, ImageFormats.UNKNOWN);
        // Debug.debug("imageFormatFile", imageFormatFile);

        final ImageFormat imageFormatBytes = Imaging.guessFormat(imageFileBytes);
        assertNotNull(imageFormatBytes);
        assertNotSame(imageFormatBytes, ImageFormats.UNKNOWN);
        // Debug.debug("imageFormatBytes", imageFormatBytes);

        assertSame(imageFormatBytes, imageFormatFile);
    }

    public void checkGetICCProfileBytes(final File imageFile, final byte[] imageFileBytes)
            throws Exception {
        // check guessFormat()
        final byte[] iccBytesFile = Imaging.getICCProfileBytes(imageFile);

        final byte[] iccBytesBytes = Imaging.getICCProfileBytes(imageFileBytes);

        assertEquals((iccBytesFile != null), (iccBytesBytes != null));

        if (iccBytesFile == null) {
            return;
        }

        assertArrayEquals(iccBytesFile, iccBytesBytes);
    }

    public void checkGetImageInfo(final File imageFile, final byte[] imageFileBytes) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ImageReadException {
        final boolean ignoreImageData = isPhilHarveyTestImage(imageFile);
        final ImageFormat imageFormat = Imaging.guessFormat(imageFile);
        ImagingParameters params = null;
        if (imageFormat == ImageFormats.TIFF) {
            params = new TiffImagingParameters();
            ((TiffImagingParameters) params).setReadThumbnails(!ignoreImageData);
        }
        if (imageFormat == ImageFormats.JPEG) {
            params = new JpegImagingParameters();
        }

        ImageParser imageParser = Util.getImageParser(imageFormat);

        final ImageInfo imageInfoFile = imageParser.getImageInfo(imageFile, params);

        final ImageInfo imageInfoBytes = imageParser.getImageInfo(imageFileBytes, params);

        assertNotNull(imageInfoFile);
        assertNotNull(imageInfoBytes);

        final Method[] methods = ImageInfo.class.getMethods();
        for (final Method method2 : methods) {
            if (!Modifier.isPublic(method2.getModifiers())) {
                continue;
            }
            if (!method2.getName().startsWith("get")) {
                continue;
            }
            if (method2.getName().equals("getClass"))
             {
                continue;
            // if (method.getGenericParameterTypes().length > 0)
            // continue;
            }

            final Object valueFile = method2.invoke(imageInfoFile, (Object[])null);
            final Object valueBytes = method2.invoke(imageInfoBytes, (Object[])null);

            assertEquals(valueFile, valueBytes);
        }

        // only have to test values from imageInfoFile; we already know values
        // match.
        assertTrue(imageInfoFile.getBitsPerPixel() > 0);

        assertNotNull(imageInfoFile.getFormat());
        assertNotSame(imageInfoFile.getFormat(), ImageFormats.UNKNOWN);

        assertNotNull(imageInfoFile.getFormatName());

        assertTrue(imageInfoFile.getWidth() > 0);
        assertTrue(imageInfoFile.getHeight() > 0);

        assertNotNull(imageInfoFile.getMimeType());

        // TODO: not all adapters count images yet.
        // assertTrue(imageInfoFile.getNumberOfImages() > 0);

    }


}
