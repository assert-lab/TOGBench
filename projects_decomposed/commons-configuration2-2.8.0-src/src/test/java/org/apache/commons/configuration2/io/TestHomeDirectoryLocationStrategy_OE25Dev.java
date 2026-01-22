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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Test class for {@code HomeDirectoryLocationStrategy}.
 *
 */
public class TestHomeDirectoryLocationStrategy_OE25Dev {
    /** Constant for a test file name. */
    private static final String FILE_NAME = "test.tst";

    /** Constant for a base path to be used. */
    private static final String BASE_PATH = "sub";

    /** An object for dealing with temporary files. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /** A mock for the file system. */
    private FileSystem fileSystem;

    @Before
    public void setUp() throws Exception {
        fileSystem = EasyMock.createMock(FileSystem.class);
        EasyMock.replay(fileSystem);
    }

    /**
     * Creates a strategy test object which uses the temporary root directory as its home directory.
     *
     * @param withBasePath the base path flag
     * @return the test strategy
     */
    private HomeDirectoryLocationStrategy setUpStrategy(final boolean withBasePath) {
        return new HomeDirectoryLocationStrategy(folder.getRoot().getAbsolutePath(), withBasePath);
    }

    /**
     * Tests whether default values are correctly set by the constructor.
     */

    /**
     * Tests whether the base is actually evaluated if the flag is set.
     */

    /**
     * Tests whether a file can be located if the base path is ignored.
     */

    /**
     * Tests whether a file in a sub folder can be located.
     */

    /**
     * Tests a locate() operation which evaluates the base path if no base path is set.
     */

    /**
     * Tests a locate() operation if no file name is specified.
     */

    @Test
    public void testInitDefaults_1_oe() {
        final HomeDirectoryLocationStrategy strategy = new HomeDirectoryLocationStrategy();
        assertEquals("Wrong home directory", System.getProperty("user.home"), strategy.getHomeDirectory());
    }

    @Test
    public void testInitDefaults_2_oe() {
        final HomeDirectoryLocationStrategy strategy = new HomeDirectoryLocationStrategy();
        // removed other assertion
        assertFalse("Wrong base path flag", strategy.isEvaluateBasePath());
    }

    @Test
    public void testLocateFailedWithBasePath_1_oe() throws IOException {
        folder.newFile(FILE_NAME);
        final FileLocator locator = FileLocatorUtils.fileLocator().basePath(BASE_PATH).fileName(FILE_NAME).create();
        final HomeDirectoryLocationStrategy strategy = setUpStrategy(true);
        assertNull("Got a URL", strategy.locate(fileSystem, locator));
    }

    @Test
    public void testLocateSuccessIgnoreBasePath_1_oe() throws IOException {
        final File file = folder.newFile(FILE_NAME);
        final FileLocator locator = FileLocatorUtils.fileLocator().basePath(BASE_PATH).fileName(FILE_NAME).create();
        final HomeDirectoryLocationStrategy strategy = setUpStrategy(false);
        final URL url = strategy.locate(fileSystem, locator);
        assertEquals("Wrong URL", file.getAbsoluteFile(), FileLocatorUtils.fileFromURL(url).getAbsoluteFile());
    }

    @Test
    public void testLocateSuccessInSubFolder_1_oe() throws IOException {
        final File sub = folder.newFolder(BASE_PATH);
        final File file = new File(sub, FILE_NAME);
        assertTrue("Could not create file", file.createNewFile());
    }

    @Test
    public void testLocateSuccessInSubFolder_2_oe() throws IOException {
        final File sub = folder.newFolder(BASE_PATH);
        final File file = new File(sub, FILE_NAME);
        // removed other assertion
        final FileLocator locator = FileLocatorUtils.fileLocator().basePath(BASE_PATH).fileName(FILE_NAME).create();
        final HomeDirectoryLocationStrategy strategy = setUpStrategy(true);
        final URL url = strategy.locate(fileSystem, locator);
        assertEquals("Wrong URL", file.getAbsoluteFile(), FileLocatorUtils.fileFromURL(url).getAbsoluteFile());
    }

    @Test
    public void testLocateSuccessNoBasePath_1_oe() throws IOException {
        final File file = folder.newFile(FILE_NAME);
        final FileLocator locator = FileLocatorUtils.fileLocator().fileName(FILE_NAME).create();
        final HomeDirectoryLocationStrategy strategy = setUpStrategy(true);
        final URL url = strategy.locate(fileSystem, locator);
        assertEquals("Wrong URL", file.getAbsoluteFile(), FileLocatorUtils.fileFromURL(url).getAbsoluteFile());
    }

    @Test
    public void testNoFileName_1_oe() {
        final FileLocator locator = FileLocatorUtils.fileLocator().basePath(BASE_PATH).create();
        final HomeDirectoryLocationStrategy strategy = setUpStrategy(true);
        assertNull("Got a URL", strategy.locate(fileSystem, locator));
    }

}
