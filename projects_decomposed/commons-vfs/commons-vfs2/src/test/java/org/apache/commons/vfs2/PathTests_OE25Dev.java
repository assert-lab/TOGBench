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
package org.apache.commons.vfs2;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * Path test cases for providers.
 */
public class PathTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Returns the capabilities required by the tests of this test case. The tests are not run if the provider being
     * tested does not support all the required capabilities. Return null or an empty array to always run the tests.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] {Capability.URI};
    }

    /**
     * Tests resolution of absolute URI.
     */

    /**
     * Tests content.
     */

    @Test
    public void testAbsoluteURI_1_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its Path
        final String pathStr = readFolder.getPath().toString();
        try (FileObject fileObject = getManager().resolveFile(pathStr,
            readFolder.getFileSystem().getFileSystemOptions())) {
            assertSame("file object", readFolder, fileObject);
    }
    }

    @Test
    public void testAbsoluteURI_2_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its Path
        final String pathStr = readFolder.getPath().toString();
        try (FileObject fileObject = getManager().resolveFile(pathStr,
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
        }

        // Try fetching the filesystem root by its Path
        final Path rootPath = Paths.get(readFolder.getName().getRootURI());
        try (FileObject fileObject = getManager().resolveFile(rootPath.toString(),
            readFolder.getFileSystem().getFileSystemOptions())) {
            assertSame(readFolder.getFileSystem().getRoot(), fileObject);
    }
    }

    @Test
    public void testAbsoluteURI_3_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its Path
        final String pathStr = readFolder.getPath().toString();
        try (FileObject fileObject = getManager().resolveFile(pathStr,
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
        }

        // Try fetching the filesystem root by its Path
        final Path rootPath = Paths.get(readFolder.getName().getRootURI());
        try (FileObject fileObject = getManager().resolveFile(rootPath.toString(),
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
            assertEquals(rootPath, Paths.get(fileObject.getName().getRootURI()));
    }
    }

    @Test
    public void testAbsoluteURI_4_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its Path
        final String pathStr = readFolder.getPath().toString();
        try (FileObject fileObject = getManager().resolveFile(pathStr,
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
        }

        // Try fetching the filesystem root by its Path
        final Path rootPath = Paths.get(readFolder.getName().getRootURI());
        try (FileObject fileObject = getManager().resolveFile(rootPath.toString(),
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
            // removed other assertion
            assertEquals(rootPath, fileObject.getName().getPath());
    }
    }

    @Test
    public void testAbsoluteURI_5_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its Path
        final String pathStr = readFolder.getPath().toString();
        try (FileObject fileObject = getManager().resolveFile(pathStr,
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
        }

        // Try fetching the filesystem root by its Path
        final Path rootPath = Paths.get(readFolder.getName().getRootURI());
        try (FileObject fileObject = getManager().resolveFile(rootPath.toString(),
            readFolder.getFileSystem().getFileSystemOptions())) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(FileName.ROOT_PATH, fileObject.getName().getPath());
    }
    }

    @Test
    public void testGetPath_1_oe() throws Exception {
        try (final FileObject fileObject = getReadFolder().resolveFile("some-dir/")) {
            final Path path = fileObject.getPath();

            // FileName#getURI() returns a String, not a URI.
            assertEquals(Paths.get(fileObject.getName().getURI()).toString(), path.toString());
    }
    }

    @Test
    public void testGetPath_2_oe() throws Exception {
        try (final FileObject fileObject = getReadFolder().resolveFile("some-dir/")) {
            final Path path = fileObject.getPath();

            // FileName#getURI() returns a String, not a URI.
            // removed other assertion
            assertEquals(Paths.get(fileObject.getName().getURI()), path);
    }
    }

    @Test
    public void testGetPath_3_oe() throws Exception {
        try (final FileObject fileObject = getReadFolder().resolveFile("some-dir/")) {
            final Path path = fileObject.getPath();

            // FileName#getURI() returns a String, not a URI.
            // removed other assertion
            // removed other assertion

            assertEquals(fileObject.getPath().toString(), fileObject.getURI().toString());
    }
    }

    @Test
    public void testReservedCharacterSpace_1_oe() throws FileSystemException {
        try (final FileObject fileObject = getReadFolder().resolveFile("file with spaces.txt")) {
            final Path path = fileObject.getPath();
            final String string = path.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
    }
    }

    @Test
    public void testReservedCharacterSpace_2_oe() throws FileSystemException {
        try (final FileObject fileObject = getReadFolder().resolveFile("file with spaces.txt")) {
            final Path path = fileObject.getPath();
            final String string = path.toString();
            // removed other assertion
        }
        try (final FileObject fileObject = getReadFolder().resolveFile("file%20with%20spaces.txt")) {
            final Path path = fileObject.getPath();
            final String string = path.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
    }
    }

    @Test
    public void testURIContentProvider_1_oe() throws Exception {
        // Test non-empty file
        try (final FileObject fileObject = getReadFolder().resolveFile("file1.txt")) {
            assertTrue(fileObject.exists());
    }
    }

    @Test
    public void testURIContentProvider_2_oe() throws Exception {
        // Test non-empty file
        try (final FileObject fileObject = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion

            final Path path = fileObject.getPath();
            final String pathStr = path.toString();
            final FileSystemOptions options = getReadFolder().getFileSystem().getFileSystemOptions();

            try (final FileObject f1 = getManager().resolveFile(pathStr, options);
                final FileObject f2 = getManager().resolveFile(pathStr, options)) {

                assertEquals("Two files resolved by URI must be equals on " + pathStr, f1, f2);
    }
    }
    }

    @Test
    public void testURIContentProvider_3_oe() throws Exception {
        // Test non-empty file
        try (final FileObject fileObject = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion

            final Path path = fileObject.getPath();
            final String pathStr = path.toString();
            final FileSystemOptions options = getReadFolder().getFileSystem().getFileSystemOptions();

            try (final FileObject f1 = getManager().resolveFile(pathStr, options);
                final FileObject f2 = getManager().resolveFile(pathStr, options)) {

                // removed other assertion
                assertSame("Resolving two times should not produce new filesystem on " + pathStr,f1.getFileSystem(),f2.getFileSystem());
    }
    }
    }

}