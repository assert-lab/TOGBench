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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

/**
 * Test cases for reading file content.
 */
public class ContentTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Asserts every file in a folder exists and has the expected content.
     */
    private void assertSameContent(final FileInfo expected, final FileObject folder) throws Exception {
        for (final FileInfo fileInfo : expected.children.values()) {
            final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);

            assertTrue(child.getName().toString(), child.exists());
            if (fileInfo.type == FileType.FILE) {
                assertSameContent(fileInfo.content, child);
            } else {
                assertSameContent(fileInfo, child);
            }
        }
    }

    /**
     * Asserts that every expected file exists, and has the expected content.
     */


    /**
     * Tests attributes
     */
    @Test
    public void testAttributes() throws FileSystemException {
        this.getReadFolder().getContent().getAttributes();
    }

    /**
     * Tests that input streams are cleaned up on file close.
     */
    @Test
    public void testByteArrayReadAll() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
            assertTrue(file.isFile());

            assertEquals(FILE1_CONTENT, new String(file.getContent().getByteArray()));
        }
    }

    /**
     * Tests that children cannot be listed for non-folders.
     */

    /**
     * Tests content.
     */

    /**
     * Tests existence determination.
     */

    @Test
    public void testGetString_Charset() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
            assertTrue(file.isFile());

            assertEquals(FILE1_CONTENT, new String(file.getContent().getString(StandardCharsets.UTF_8)));
        }
    }

    @Test
    public void testGetString_String() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
            assertTrue(file.isFile());

            assertEquals(FILE1_CONTENT, new String(file.getContent().getString(StandardCharsets.UTF_8.name())));
        }
    }

    /**
     * Tests that input streams are cleaned up on file close.
     */

    /**
     * Tests that input streams are cleaned up on file close.
     */
    @Test
    public void testInputStreamReadAll() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
            assertTrue(file.isFile());

            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            file.getContent().write(output);
            assertEquals(FILE1_CONTENT, new String(output.toByteArray()));
        }
    }

    /**
     * Tests that input streams are cleaned up on file close.
     */

    /**
     * Tests parent identity
     */

    /**
     * Tests concurrent reads on different files works.
     */

    /**
     * Tests concurrent reads on a file.
     */

    /**
     * Tests concurrent reads on a file.
     */

    /**
     * Tests that content and file objects are usable after being closed.
     */

    private void testRoot(final FileObject root) throws FileSystemException {
        assertTrue(root.exists());
        assertNotSame(root.getType(), FileType.IMAGINARY);
    }

    /**
     * Tests root of file system exists.
     */
    @Test
    public void testRootAPI() throws FileSystemException {
        if (!this.getProviderConfig().isFileSystemRootAccessible()) {
            return;
        }
        testRoot(getFileSystem().getRoot());
    }

    /**
     * Tests root of file system exists.
     */
    @Test
    public void testRootURI() throws FileSystemException {
        if (!this.getProviderConfig().isFileSystemRootAccessible()) {
            return;
        }
        final FileSystem fileSystem = getFileSystem();
        final String uri = fileSystem.getRootURI();
        testRoot(getManager().resolveFile(uri, fileSystem.getFileSystemOptions()));
    }

    /**
     * Tests that unknown files have no content.
     */


}
