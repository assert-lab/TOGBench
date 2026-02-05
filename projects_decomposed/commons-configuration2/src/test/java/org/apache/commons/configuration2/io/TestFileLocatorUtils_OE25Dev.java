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
package org.apache.commons.configuration2.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for {@code FileLocatorUtils}.
 *
 */
public class TestFileLocatorUtils_OE25Dev {
    /** Constant for a file name. */
    private static final String FILE_NAME = "test.xml";

    /** Constant for a base path. */
    private static final String BASE_PATH = "/etc/test/path/";

    /** Constant for a test encoding. */
    private static final String ENCODING = "utf-8";

    /** A test URL. */
    private static URL sourceURL;

    /** A test file system. */
    private static FileSystem fileSystem;

    /**
     * Checks whether the specified locator points to the expected test configuration file.
     *
     * @param locator the locator to check
     * @throws ConfigurationException if an error occurs
     */
    private static void checkFullyInitializedLocator(final FileLocator locator) throws ConfigurationException {
        assertNotNull("No base path", locator.getBasePath());
        assertNotNull("No file name", locator.getFileName());
        assertNotNull("No source URL", locator.getSourceURL());

        FileHandler handler = new FileHandler();
        handler.setBasePath(locator.getBasePath());
        handler.setFileName(locator.getFileName());
        checkTestConfiguration(handler);

        handler = new FileHandler();
        handler.setURL(locator.getSourceURL());
        checkTestConfiguration(handler);
    }

    /**
     * Checks whether the expected test configuration can be loaded using the specified handler.
     *
     * @param handler the file handler
     * @throws ConfigurationException if an error occurs
     */
    private static void checkTestConfiguration(final FileHandler handler) throws ConfigurationException {
        final XMLConfiguration config = new XMLConfiguration();
        final FileHandler h2 = new FileHandler(config, handler);
        h2.load();
        assertEquals("Wrong content", "value", config.getString("element"));
    }

    @BeforeClass
    public static void setUpOnce() throws Exception {
        sourceURL = ConfigurationAssert.getTestURL(FILE_NAME);
        fileSystem = EasyMock.createMock(FileSystem.class);
        EasyMock.replay(fileSystem);
    }

    /**
     * Tests a successful conversion from a file to a URL.
     */
    @Test
    public void testConvertFileToURL() throws ConfigurationException {
        final File file = ConfigurationAssert.getTestFile(FILE_NAME);
        final FileHandler handler = new FileHandler();
        handler.setURL(FileLocatorUtils.convertFileToURL(file));
        checkTestConfiguration(handler);
    }

    /**
     * Tests whether exceptions are handled when converting a URI to a URL.
     */

    /**
     * Tests the definition of the default location strategy.
     */

    /**
     * Tests whether fileFromURL() handles null URLs correctly.
     */

    /**
     * Tests whether an encoded "%" character in the file name is handled correctly by fileFromURL(). This test is related
     * to CONFIGURATION-521.
     */

    /**
     * Tests whether a "+" character in the file name is handled correctly by fileFromURL(). This test is related to
     * CONFIGURATION-415.
     */

    /**
     * Tests whether fromMap() can handle a null map.
     */

    /**
     * Tests fullyInitializedLocator() if the locator is already fully initialized.
     */

    /**
     * Tests whether a fully initialized locator can be obtained if a file name is available.
     */
    @Test
    public void testFullyInitializedLocatorFileName() throws ConfigurationException {
        final FileLocator locator = FileLocatorUtils.fileLocator().fileName(FILE_NAME).create();
        checkFullyInitializedLocator(FileLocatorUtils.fullyInitializedLocator(locator));
    }

    /**
     * Tests fullyInitializedLocator() if a locate() operation fails.
     */

    /**
     * Tries to obtain a fully initialized locator if the source locator is not defined.
     */

    /**
     * Tests whether a fully initialized locator can be obtained if a URL is available.
     */
    @Test
    public void testFullyInitializedLocatorURL() throws ConfigurationException {
        final FileLocator locator = FileLocatorUtils.fileLocator().sourceURL(sourceURL).create();
        checkFullyInitializedLocator(FileLocatorUtils.fullyInitializedLocator(locator));
    }

    /**
     * Tests whether a missing base path is detected when checking for a fully initialized locator.
     */

    /**
     * Tests isFullyInitialized() for null input.
     */

    /**
     * Tests isLocationDefined() if no location is defined.
     */

    /**
     * Tests isLocationDefined() if a file name is set.
     */

    /**
     * Tests whether isLocationDefined() can handle null input.
     */

    /**
     * Tests isLocationDefined() if a URL is set.
     */

    /**
     * Tests a locate() operation with a null locator.
     */

    /**
     * Tests whether an exception is thrown for a failed locate() operation.
     */
    @Test(expected = ConfigurationException.class)
    public void testLocateOrThrowFailed() throws ConfigurationException {
        final FileLocationStrategy strategy = EasyMock.createMock(FileLocationStrategy.class);
        EasyMock.expect(strategy.locate(EasyMock.anyObject(FileSystem.class), EasyMock.anyObject(FileLocator.class))).andReturn(null);
        EasyMock.replay(strategy);
        final FileLocator locator = FileLocatorUtils.fileLocator().locationStrategy(strategy).create();
        FileLocatorUtils.locateOrThrow(locator);
    }

    /**
     * Tests a successful locate() operation that uses defaults for location strategy and file system.
     */

    /**
     * Tests a successful locate() operation if the passed in locator contains a strategy and a file system.
     */

    /**
     * Tests a successful locate() operation if the passed in locator contains a strategy, but no file system.
     */

    @Test
    public void testLocateWithNullTCCL() throws Exception {
        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(null);
            assertNull(FileLocatorUtils.locate(FileLocatorUtils.fileLocator().basePath("abase").fileName("aname").create()));
        } finally {
            Thread.currentThread().setContextClassLoader(cl);
        }
    }

    /**
     * Tests whether the default file system is returned if it is not set in a locator.
     */

    /**
     * Tests whether obtainFileSystem() can handle a null locator.
     */

    /**
     * Tests whether obtainFileSystem() returns the file system stored in the locator.
     */

    /**
     * Tests whether a location strategy can be obtained if it is not defined by the locator.
     */

    /**
     * Tests whether a location strategy can be obtained if a null locator is passed.
     */

    /**
     * Tests whether a location strategy can be obtained if it is defined by the locator.
     */

    /**
     * Tests whether put() deals with a null locator.
     */

    /**
     * Tries to call put() without a map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutNoMap() {
        FileLocatorUtils.put(FileLocatorUtils.fileLocator().create(), null);
    }

    /**
     * Tests whether a file locator can be stored in a map and read again from there.
     */

@Test
    public void testConvertToURIException_1_oe() throws URISyntaxException {
        final URI uri = new URI("test://test/path/file.tst");
        assertNull("Got a URL", FileLocatorUtils.convertURIToURL(uri));
    }

@Test
    public void testDefaultFileLocationStrategy_1_oe() {
        final CombinedLocationStrategy strategy = (CombinedLocationStrategy) FileLocatorUtils.DEFAULT_LOCATION_STRATEGY;
        final Iterator<FileLocationStrategy> it = strategy.getSubStrategies().iterator();
        assertTrue("Wrong strategy (1)", it.next() instanceof ProvidedURLLocationStrategy);
    }

@Test
    public void testFileFromURLNull_1_oe() throws Exception {
        assertNull("Wrong file for null URL", FileLocatorUtils.fileFromURL(null));
    }

@Test
    public void testFileFromURLWithEncodedPercent_1_oe() throws MalformedURLException {
        final File file = new File("https%3A%2F%2Fwww.apache.org%2F.url").getAbsoluteFile();
        final URL fileURL = file.toURI().toURL();
        final File file2 = FileLocatorUtils.fileFromURL(fileURL);
        assertEquals("Wrong file", file, file2);
    }

@Test
    public void testFileFromURLWithPlus_1_oe() throws MalformedURLException {
        final File file = new File(new File("target"), "foo+bar.txt").getAbsoluteFile();
        final URL fileURL = file.toURI().toURL();
        final File file2 = FileLocatorUtils.fileFromURL(fileURL);
        assertEquals("Wrong file", file, file2);
    }

@Test
    public void testFromMapNoMap_1_oe() {
        final FileLocator fileLocator = FileLocatorUtils.fromMap(null);
        assertEquals("Locator is initialized", FileLocatorUtils.fileLocator().create(), fileLocator);
    }

@Test
    public void testFullyInitializedLocatorAlreadyComplete_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().fileName(FILE_NAME).create();
        final FileLocator fullLocator = FileLocatorUtils.fullyInitializedLocator(locator);
        assertSame("Different instance", fullLocator, FileLocatorUtils.fullyInitializedLocator(fullLocator));
    }

@Test
    public void testFullyInitializedLocatorLocateFails_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().fileName("non existing file").create();
        assertNull("Wrong result", FileLocatorUtils.fullyInitializedLocator(locator));
    }

@Test
    public void testFullyInitializedLocatorUndefined_1_oe() {
        assertNull("Got a result", FileLocatorUtils.fullyInitializedLocator(FileLocatorUtils.fileLocator().create()));
    }

@Test
    public void testGetBasePath_1_oe() throws Exception {
        URL url = new URL("http://xyz.net/foo/bar.xml");
        assertEquals("base path of " + url, "http://xyz.net/foo/", FileLocatorUtils.getBasePath(url));
    }

@Test
    public void testGetBasePath_2_oe() throws Exception {
        URL url = new URL("http://xyz.net/foo/bar.xml");
        // removed other assertion

        url = new URL("http://xyz.net/foo/");
        assertEquals("base path of " + url, "http://xyz.net/foo/", FileLocatorUtils.getBasePath(url));
    }

@Test
    public void testGetBasePath_3_oe() throws Exception {
        URL url = new URL("http://xyz.net/foo/bar.xml");
        // removed other assertion

        url = new URL("http://xyz.net/foo/");
        // removed other assertion

        url = new URL("http://xyz.net/foo");
        assertEquals("base path of " + url, "http://xyz.net/", FileLocatorUtils.getBasePath(url));
    }

@Test
    public void testGetBasePath_4_oe() throws Exception {
        URL url = new URL("http://xyz.net/foo/bar.xml");
        // removed other assertion

        url = new URL("http://xyz.net/foo/");
        // removed other assertion

        url = new URL("http://xyz.net/foo");
        // removed other assertion

        url = new URL("http://xyz.net/");
        assertEquals("base path of " + url, "http://xyz.net/", FileLocatorUtils.getBasePath(url));
    }

@Test
    public void testGetBasePath_5_oe() throws Exception {
        URL url = new URL("http://xyz.net/foo/bar.xml");
        // removed other assertion

        url = new URL("http://xyz.net/foo/");
        // removed other assertion

        url = new URL("http://xyz.net/foo");
        // removed other assertion

        url = new URL("http://xyz.net/");
        // removed other assertion

        url = new URL("http://xyz.net");
        assertEquals("base path of " + url, "http://xyz.net", FileLocatorUtils.getBasePath(url));
    }

@Test
    public void testGetFile_1_oe() throws Exception {
        final File directory = new File("target");
        final File reference = new File(directory, "test.txt").getAbsoluteFile();

        assertEquals(reference, FileLocatorUtils.getFile(null, reference.getAbsolutePath()));
    }

@Test
    public void testGetFile_2_oe() throws Exception {
        final File directory = new File("target");
        final File reference = new File(directory, "test.txt").getAbsoluteFile();

        // removed other assertion
        assertEquals(reference, FileLocatorUtils.getFile(directory.getAbsolutePath(), reference.getAbsolutePath()));
    }

@Test
    public void testGetFile_3_oe() throws Exception {
        final File directory = new File("target");
        final File reference = new File(directory, "test.txt").getAbsoluteFile();

        // removed other assertion
        // removed other assertion
        assertEquals(reference, FileLocatorUtils.getFile(directory.getAbsolutePath(), reference.getName()));
    }

@Test
    public void testGetFile_4_oe() throws Exception {
        final File directory = new File("target");
        final File reference = new File(directory, "test.txt").getAbsoluteFile();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(reference, FileLocatorUtils.getFile(directory.toURI().toURL().toString(), reference.getName()));
    }

@Test
    public void testGetFile_5_oe() throws Exception {
        final File directory = new File("target");
        final File reference = new File(directory, "test.txt").getAbsoluteFile();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(reference, FileLocatorUtils.getFile("invalid", reference.toURI().toURL().toString()));
    }

@Test
    public void testGetFile_6_oe() throws Exception {
        final File directory = new File("target");
        final File reference = new File(directory, "test.txt").getAbsoluteFile();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(reference, FileLocatorUtils.getFile("jar:file:/C:/myjar.jar!/my-config.xml/someprops.properties", reference.getAbsolutePath()));
    }

@Test
    public void testGetFileName_1_oe() throws Exception {
        assertNull("file name for a null URL", FileLocatorUtils.getFileName(null));
    }

@Test
    public void testGetFileName_2_oe() throws Exception {
        // removed other assertion

        URL url = new URL("http://xyz.net/foo/");
        assertNull("file for a directory URL " + url, FileLocatorUtils.getFileName(url));
    }

@Test
    public void testGetFileName_3_oe() throws Exception {
        // removed other assertion

        URL url = new URL("http://xyz.net/foo/");
        // removed other assertion

        url = new URL("http://xyz.net/foo/bar.xml");
        assertEquals("file name for a valid URL " + url, "bar.xml", FileLocatorUtils.getFileName(url));
    }

@Test
    public void testIsFullyInitializedNoBasePath_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().sourceURL(ConfigurationAssert.getTestURL(FILE_NAME)).fileName(FILE_NAME).create();
        assertFalse("Wrong result", FileLocatorUtils.isFullyInitialized(locator));
    }

@Test
    public void testIsFullyInitializedNull_1_oe() {
        assertFalse("Wrong result", FileLocatorUtils.isFullyInitialized(null));
    }

@Test
    public void testIsLocationDefinedFalse_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().encoding(ENCODING).basePath(BASE_PATH).fileSystem(FileLocatorUtils.DEFAULT_FILE_SYSTEM)
            .create();
        assertFalse("Wrong result", FileLocatorUtils.isLocationDefined(locator));
    }

@Test
    public void testIsLocationDefinedFileName_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().fileName(FILE_NAME).create();
        assertTrue("Wrong result", FileLocatorUtils.isLocationDefined(locator));
    }

@Test
    public void testIsLocationDefinedNull_1_oe() {
        assertFalse("Wrong result", FileLocatorUtils.isLocationDefined(null));
    }

@Test
    public void testIsLocationDefinedURL_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().sourceURL(ConfigurationAssert.getTestURL(FILE_NAME)).create();
        assertTrue("Wrong result", FileLocatorUtils.isLocationDefined(locator));
    }

@Test
    public void testLocateNullLocator_1_oe() {
        assertNull("Wrong result", FileLocatorUtils.locate(null));
    }

@Test
    public void testLocateSuccessWithDefaults_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().sourceURL(sourceURL).create();
        assertSame("Wrong URL", sourceURL, FileLocatorUtils.locate(locator));
    }

@Test
    public void testLocateSuccessWithStrategyAndFileSystem_1_oe() throws ConfigurationException {
        final FileSystem fs = EasyMock.createMock(FileSystem.class);
        final FileLocationStrategy strategy = EasyMock.createMock(FileLocationStrategy.class);
        final FileLocator locator = FileLocatorUtils.fileLocator().fileSystem(fs).locationStrategy(strategy).create();
        EasyMock.expect(strategy.locate(fs, locator)).andReturn(sourceURL);
        EasyMock.replay(fs, strategy);
        assertSame("Wrong URL", sourceURL, FileLocatorUtils.locateOrThrow(locator));
    }

@Test
    public void testLocateSuccessWithStrategyDefaultFileSystem_1_oe() throws ConfigurationException {
        final FileLocationStrategy strategy = EasyMock.createMock(FileLocationStrategy.class);
        final FileLocator locator = FileLocatorUtils.fileLocator().locationStrategy(strategy).create();
        EasyMock.expect(strategy.locate(FileLocatorUtils.DEFAULT_FILE_SYSTEM, locator)).andReturn(sourceURL);
        EasyMock.replay(strategy);
        assertSame("Wrong URL", sourceURL, FileLocatorUtils.locateOrThrow(locator));
    }

@Test
    public void testObtainFileSystemNotSetInLocator_1_oe() {
        assertSame("Wrong file system", FileLocatorUtils.DEFAULT_FILE_SYSTEM, FileLocatorUtils.obtainFileSystem(FileLocatorUtils.fileLocator().create()));
    }

@Test
    public void testObtainFileSystemNullLocator_1_oe() {
        assertSame("Wrong file system", FileLocatorUtils.DEFAULT_FILE_SYSTEM, FileLocatorUtils.obtainFileSystem(null));
    }

@Test
    public void testObtainFileSystemSetInLocator_1_oe() {
        final FileSystem fs = EasyMock.createMock(FileSystem.class);
        final FileLocator locator = FileLocatorUtils.fileLocator().fileSystem(fs).create();
        assertSame("Wrong file system", fs, FileLocatorUtils.obtainFileSystem(locator));
    }

@Test
    public void testObtainLocationStrategyNotSetInLocator_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().create();
        assertSame("Wrong strategy", FileLocatorUtils.DEFAULT_LOCATION_STRATEGY, FileLocatorUtils.obtainLocationStrategy(locator));
    }

@Test
    public void testObtainLocationStrategyNullLocator_1_oe() {
        assertSame("Wrong strategy", FileLocatorUtils.DEFAULT_LOCATION_STRATEGY, FileLocatorUtils.obtainLocationStrategy(null));
    }

@Test
    public void testObtainLocationStrategySetInLocator_1_oe() {
        final FileLocationStrategy strategy = EasyMock.createMock(FileLocationStrategy.class);
        EasyMock.replay(strategy);
        final FileLocator locator = FileLocatorUtils.fileLocator().locationStrategy(strategy).create();
        assertSame("Wrong strategy", strategy, FileLocatorUtils.obtainLocationStrategy(locator));
    }

@Test
    public void testPutNoLocator_1_oe() {
        final Map<String, Object> map = new HashMap<>();
        FileLocatorUtils.put(null, map);
        assertTrue("Got properties", map.isEmpty());
    }

@Test
    public void testStoreFileLocatorInMap_1_oe() {
        final FileLocationStrategy strategy = EasyMock.createMock(FileLocationStrategy.class);
        EasyMock.replay(strategy);
        final FileLocator locator = FileLocatorUtils.fileLocator().basePath(BASE_PATH).encoding(ENCODING).fileName(FILE_NAME).fileSystem(fileSystem)
            .locationStrategy(strategy).sourceURL(sourceURL).create();
        final Map<String, Object> map = new HashMap<>();
        FileLocatorUtils.put(locator, map);
        final FileLocator locator2 = FileLocatorUtils.fromMap(map);
        assertEquals("Different locators", locator, locator2);
    }

}
