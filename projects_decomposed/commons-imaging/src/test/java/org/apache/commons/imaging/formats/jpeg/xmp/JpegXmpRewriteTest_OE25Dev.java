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

package org.apache.commons.imaging.formats.jpeg.xmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.apache.commons.imaging.formats.jpeg.JpegImagingParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class JpegXmpRewriteTest_OE25Dev extends JpegXmpBaseTest {

    public static Stream<File> data() throws Exception {
        return getImagesWithXmpData().stream();
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);
        assertNotNull(xmpXml);

        final File noXmpFile = File.createTempFile(imageFile.getName() + ".", ".jpg");
        {
            // test remove

            try (FileOutputStream fos = new FileOutputStream(noXmpFile);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().removeXmpXml(byteSource, os);
            }

            // Debug.debug("Source Segments:");
            // new JpegUtils().dumpJFIF(new ByteSourceFile(noXmpFile));

            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(noXmpFile), params);
            Assertions.assertNull(outXmp);
        }

        {
            // test update

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(byteSource, os, newXmpXml);
            }

            // Debug.debug("Source Segments:");
            // new JpegUtils().dumpJFIF(new ByteSourceFile(updated));

            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
            assertNotNull(outXmp);
            assertEquals(outXmp, newXmpXml);
        }

        {
            // test insert

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(new ByteSourceFile(
                        noXmpFile), os, newXmpXml);
            }

            // Debug.debug("Source Segments:");
            // new JpegUtils().dumpJFIF(new ByteSourceFile(updated));

            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
            assertNotNull(outXmp);
            assertEquals(outXmp, newXmpXml);
        }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate_1_oe(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);
        assertNotNull(xmpXml);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate_2_oe(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);

        final File noXmpFile = File.createTempFile(imageFile.getName() + ".", ".jpg");
        {

            try (FileOutputStream fos = new FileOutputStream(noXmpFile);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().removeXmpXml(byteSource, os);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(noXmpFile), params);
            Assertions.assertNull(outXmp);
    }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate_3_oe(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);

        final File noXmpFile = File.createTempFile(imageFile.getName() + ".", ".jpg");
        {

            try (FileOutputStream fos = new FileOutputStream(noXmpFile);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().removeXmpXml(byteSource, os);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(noXmpFile), params);
        }

        {

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(byteSource, os, newXmpXml);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
            assertNotNull(outXmp);
    }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate_4_oe(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);

        final File noXmpFile = File.createTempFile(imageFile.getName() + ".", ".jpg");
        {

            try (FileOutputStream fos = new FileOutputStream(noXmpFile);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().removeXmpXml(byteSource, os);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(noXmpFile), params);
        }

        {

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(byteSource, os, newXmpXml);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
            assertEquals(outXmp, newXmpXml);
    }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate_5_oe(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);

        final File noXmpFile = File.createTempFile(imageFile.getName() + ".", ".jpg");
        {

            try (FileOutputStream fos = new FileOutputStream(noXmpFile);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().removeXmpXml(byteSource, os);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(noXmpFile), params);
        }

        {

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(byteSource, os, newXmpXml);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
        }

        {

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(new ByteSourceFile(
                        noXmpFile), os, newXmpXml);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
            assertNotNull(outXmp);
    }
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testRemoveInsertUpdate_6_oe(final File imageFile) throws Exception {
        final ByteSource byteSource = new ByteSourceFile(imageFile);
        final JpegImagingParameters params = new JpegImagingParameters();
        final String xmpXml = new JpegImageParser().getXmpXml(byteSource, params);

        final File noXmpFile = File.createTempFile(imageFile.getName() + ".", ".jpg");
        {

            try (FileOutputStream fos = new FileOutputStream(noXmpFile);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().removeXmpXml(byteSource, os);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(noXmpFile), params);
        }

        {

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(byteSource, os, newXmpXml);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
        }

        {

            final String newXmpXml = "test";
            final File updated = File.createTempFile(imageFile.getName() + ".", ".jpg");
            try (FileOutputStream fos = new FileOutputStream(updated);
                    OutputStream os = new BufferedOutputStream(fos)) {
                new JpegXmpRewriter().updateXmpXml(new ByteSourceFile(
                        noXmpFile), os, newXmpXml);
            }


            final String outXmp = new JpegImageParser().getXmpXml(
                    new ByteSourceFile(updated), params);
            assertEquals(outXmp, newXmpXml);
    }
    }

}
