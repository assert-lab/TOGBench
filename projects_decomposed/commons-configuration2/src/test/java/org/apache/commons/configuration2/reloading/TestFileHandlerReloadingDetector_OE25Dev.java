/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration2.reloading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.apache.commons.configuration2.io.FileHandler;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Test class for {@code FileHandlerReloadingDetector}.
 *
 */
public class TestFileHandlerReloadingDetector_OE25Dev {
    /**
     * A test implementation which allows mocking the monitored file.
     */
    private static class FileHandlerReloadingDetectorTestImpl extends FileHandlerReloadingDetector {
        /** The mock file. */
        private final File mockFile;

        /**
         * Creates a new instance of {@code FileHandlerReloadingDetectorTestImpl} and initializes it with the mock file.
         *
         * @param file the mock file
         */
        public FileHandlerReloadingDetectorTestImpl(final File file) {
            this(file, 0);
        }

        /**
         * Creates a new instance of {@code FileHandlerReloadingDetectorTestImpl} and initializes it with the mock file and a
         * refresh delay.
         *
         * @param file the mock file
         * @param delay the delay
         */
        public FileHandlerReloadingDetectorTestImpl(final File file, final long delay) {
            super(null, delay);
            mockFile = file;
        }

        /**
         * Always returns the mock file.
         */
        @Override
        protected File getFile() {
            return mockFile;
        }
    }

    /** Constant for a file's modification time. */
    private static final long LAST_MODIFIED = 20121008215654L;

    /**
     * Tests the default refresh delay.
     */

    /**
     * Tests whether a jar URL is handled correctly.
     */

    /** The detector to be tested. */
    /**
     * Tests whether an instance can be created with a file handler.
     */

    /**
     * Tests whether a non-existing file is handled correctly.
     */

    /**
     * Tests isReloadingRequired() if no location has been set.
     */

    /**
     * Tests whether a changed file is detected.
     */

    /**
     * Tests that a newly created instance does not have a location.
     */

    /**
     * Tests whether the refresh delay is taken into account.
     */

    /**
     * Tests whether a changed file is detected after initialization and invoking refresh.
     */

    /**
     * Tests a refresh cycle with a detected reload operation and a notification that reloading was performed.
     */

    /**
     * Tests a cycle with a detected reload operation and a notification that reloading was performed.
     */

    @Test
    public void testDefaultRefreshDelay_1_oe() {
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector();
        assertEquals("Wrong delay", 5000, detector.getRefreshDelay());
    }

    @Test
    public void testGetFileJarURL_1_oe() throws Exception {
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector();
        final URL url = new URL("jar:" + new File("conf/resources.jar").getAbsoluteFile().toURI().toURL() + "!/test-jar.xml");
        detector.getFileHandler().setURL(url);
        final File file = detector.getFile();
        assertNotNull("Detector's file is null", file);
    }

    @Test
    public void testGetFileJarURL_2_oe() throws Exception {
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector();
        final URL url = new URL("jar:" + new File("conf/resources.jar").getAbsoluteFile().toURI().toURL() + "!/test-jar.xml");
        detector.getFileHandler().setURL(url);
        final File file = detector.getFile();
        assertEquals("Detector does not monitor the jar file", "resources.jar", file.getName());
    }

    @Test
    public void testInitWithFileHandler_1_oe() {
        final FileHandler handler = new FileHandler();
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector(handler);
        assertSame("Different file handler", handler, detector.getFileHandler());
    }

    @Test
    public void testIsReloadingRequiredFileDoesNotExist_1_oe() {
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector();
        detector.getFileHandler().setFile(new File("NonExistingFile.txt"));
        detector.reloadingPerformed();
        assertFalse("Reloading required", detector.isReloadingRequired());
    }

    @Test
    public void testIsReloadingRequiredNoLocation_1_oe() {
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector();
        assertFalse("Reloading", detector.isReloadingRequired());
    }

    @Test
    public void testIsReloadingRequiredTrue_1_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 1);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f);
        assertFalse("Reloading required", detector.isReloadingRequired());
    }

    @Test
    public void testLocationAfterInit_1_oe() {
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetector();
        assertFalse("Got a location", detector.getFileHandler().isLocationDefined());
    }

    @Test
    public void testRefreshDelay_1_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED).times(2);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f, 60 * 60 * 1000L);
        detector.reloadingPerformed();
        assertFalse("Reloading initially required", detector.isReloadingRequired());
    }

    @Test
    public void testRefreshDelay_2_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED).times(2);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f, 60 * 60 * 1000L);
        detector.reloadingPerformed();
        assertFalse("Reloading required", detector.isReloadingRequired());
    }

    @Test
    public void testRefreshIsReloadingRequiredTrue_1_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 1);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f);
        detector.refresh();
        assertTrue("Reloading not detected", detector.isReloadingRequired());
    }

    @Test
    public void testRefreshReloadingAndReset_1_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED).times(2);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 1).times(3);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 2);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f);
        detector.refresh();
        assertFalse("Reloading required", detector.isReloadingRequired());
    }

    @Test
    public void testRefreshReloadingAndReset_4_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED).times(2);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 1).times(3);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 2);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f);
        detector.refresh();
        detector.reloadingPerformed();
        assertTrue("Next reloading not detected", detector.isReloadingRequired());
    }

    @Test
    public void testReloadingAndReset_1_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 1).times(3);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 2);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f);
        assertFalse("Reloading required", detector.isReloadingRequired());
    }

    @Test
    public void testReloadingAndReset_4_oe() throws Exception {
        final File f = EasyMock.createMock(File.class);
        EasyMock.expect(f.exists()).andReturn(Boolean.TRUE).anyTimes();
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 1).times(3);
        EasyMock.expect(f.lastModified()).andReturn(LAST_MODIFIED + 2);
        EasyMock.replay(f);
        final FileHandlerReloadingDetector detector = new FileHandlerReloadingDetectorTestImpl(f);
        detector.reloadingPerformed();
        assertTrue("Next reloading not detected", detector.isReloadingRequired());
    }

}
