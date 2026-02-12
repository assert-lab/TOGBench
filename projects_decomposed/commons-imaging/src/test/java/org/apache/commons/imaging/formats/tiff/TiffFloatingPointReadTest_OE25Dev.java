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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImagingTestConstants;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntry;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntryForRange;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PaletteEntryForValue;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.floatingpoint.PhotometricInterpreterFloat;
import org.junit.jupiter.api.Test;

/**
 * Performs tests that access the content of TIFF files containing floating
 * point data.
 */
public class TiffFloatingPointReadTest_OE25Dev {

    /**
     * Gets a file from the TIFF test directory that contains floating-point
     * data.
     *
     * @param name a valid file name
     * @return a valid file reference.
     */
    private File getTiffFile(final String name) {
        final File tiffFolder = new File(ImagingTestConstants.TEST_IMAGE_FOLDER, "tiff");
        final File fpFolder = new File(tiffFolder, "9");
        return new File(fpFolder, name);
    }

    /**
     * Read a TIFF file using a PhotometricInterpreter with entries for the
     * specified range of values and an arbitrary no-data value. If the image is
     * successfully read, the interpreter instance will be returned.
     *
     * @param target the specified TIFF file
     * @param f0 the expected minimum bound or lower
     * @param f1 the expected maximum bound or higher
     * @param fNot an arbitrary non-data value or NaN
     * @return if successful, a valid photometric interpreter.
     * @throws ImageReadException in the event of an unsupported or malformed
     * file data element.
     * @throws IOException in the event of an I/O error
     */
    private PhotometricInterpreterFloat readAndInterpretTIFF(
        final File target, final float f0, final float f1, final float fNot) throws ImageReadException, IOException {
        final ByteSourceFile byteSource = new ByteSourceFile(target);
        final TiffReader tiffReader = new TiffReader(true);
        final TiffContents contents = tiffReader.readDirectories(
            byteSource,
            true, // indicates that application should read image data, if present
            FormatCompliance.getDefault());
        final ByteOrder byteOrder = tiffReader.getByteOrder();
        final TiffDirectory directory = contents.directories.get(0);
        if (!directory.hasTiffFloatingPointRasterData()) {
            fail("Internal error,sample file does not have floating-point data " + target.getName());
        }
        final List<PaletteEntry> pList = new ArrayList<>();
        pList.add(new PaletteEntryForValue(fNot, Color.red));
        pList.add(new PaletteEntryForRange(f0, f1, Color.black, Color.white));
        final PhotometricInterpreterFloat pInterp = new PhotometricInterpreterFloat(pList);
        final TiffImagingParameters params = new TiffImagingParameters();
        params.setCustomPhotometricInterpreter(pInterp);
        final BufferedImage bImage = directory.getTiffImage(byteOrder, params);
        if (bImage == null) {
            return null;
        }
        return pInterp;
    }

    /**
     * Read the floating-point content from a TIFF file.
     *
     * @param target the specified TIFF file
     * @param params an optional map of parameters for reading.
     * @return if successful, a valid raster data instance
     * @throws ImageReadException in the event of an unsupported or malformed
     * file data element.
     * @throws IOException in the event of an I/O error
     */
    private TiffRasterData readRasterFromTIFF(
        final File target, final TiffImagingParameters params)
        throws ImageReadException, IOException {
        final ByteSourceFile byteSource = new ByteSourceFile(target);
        final TiffReader tiffReader = new TiffReader(true);
        final TiffContents contents = tiffReader.readDirectories(
            byteSource,
            true, // indicates that application should read image data, if present
            FormatCompliance.getDefault());
        final TiffDirectory directory = contents.directories.get(0);
        return directory.getRasterData(params);
    }


    private void checkSubImage(final File target, final TiffRasterData fullRaster, final int x0, final int y0, final int width, final int height){
        try{
            final TiffImagingParameters params = new TiffImagingParameters();
            params.setSubImage(x0, y0, width, height);
            final TiffRasterData partRaster = readRasterFromTIFF(target, params);
            assertEquals(width, partRaster.getWidth(), "Invalid width in partial for " + target.getName());
            assertEquals(height, partRaster.getHeight(), "Invalid height in partial for " + target.getName());
            for (int y = y0; y < y0+height; y++) {
                for (int x = x0; x < x0+width; x++) {
                    final float vFull = fullRaster.getValue(x, y);
                    final float vPart = partRaster.getValue(x - x0, y - y0);
                    assertEquals(vFull, vPart, "Invalid value match for partial at (" + x + "," + y + ") for "+target.getName());
                }
            }
        }catch (ImageReadException | IOException ex) {
            fail("Exception during test " + ex.getMessage());
        }
    }


}
