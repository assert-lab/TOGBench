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
    @Test
    public void testAllContent() throws Exception {
        final FileInfo expectedFileInfo = buildExpectedStructure();
        final FileObject actualFolder = getReadFolder();

        assertSameContent(expectedFileInfo, actualFolder);
    }

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

    /**
     * Tests that children cannot be listed for non-folders.
     */

    /**
     * Tests content.
     */
    @Test
    public void testContent() throws Exception {
        // Test non-empty file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        assertSameContent(FILE1_CONTENT, file);

        // Test empty file
        file = getReadFolder().resolveFile("empty.txt");
        assertSameContent("", file);
    }

    /**
     * Tests existence determination.
     */

    /**
     * Tests that input streams are cleaned up on file close.
     */

    /**
     * Tests that input streams are cleaned up on file close.
     */

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
    @Test
    public void testUnknownContent() throws Exception {

        // Try getting the content of an unknown file
        final FileObject unknownFile = getReadFolder().resolveFile("unknown-file");
        final FileContent content = unknownFile.getContent();
        try {
            content.getInputStream();
            fail();
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/read-not-file.error", unknownFile, e);
        }
        try {
            content.getSize();
            fail();
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/get-size-not-file.error", unknownFile, e);
        }
    }

    @Test
    public void testByteArrayReadAll_1_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
    }
    }

    @Test
    public void testByteArrayReadAll_2_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            assertTrue(file.isFile());
    }
    }

    @Test
    public void testByteArrayReadAll_3_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            // removed other assertion

            assertEquals(FILE1_CONTENT, new String(file.getContent().getByteArray()));
    }
    }

    @Test
    public void testChildren_1_oe() throws FileSystemException {
        // Check for file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        assertSame(FileType.FILE, file.getType());
    }

    @Test
    public void testChildren_2_oe() throws FileSystemException {
        // Check for file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testChildren_4_oe() throws FileSystemException {
        // Check for file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion
        try {
            file.getChildren();
            // removed other assertion
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/list-children-not-folder.error", file, e);
        }

        // Should be able to get child by name
        file = file.resolveFile("some-child");
        assertNotNull(file);
    }

    @Test
    public void testChildren_5_oe() throws FileSystemException {
        // Check for file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion
        try {
            file.getChildren();
            // removed other assertion
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/list-children-not-folder.error", file, e);
        }

        // Should be able to get child by name
        file = file.resolveFile("some-child");
        // removed other assertion

        // Check for unknown file
        file = getReadFolder().resolveFile("unknown-file");
        assertFalse(file.exists());
    }

    @Test
    public void testChildren_7_oe() throws FileSystemException {
        // Check for file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion
        try {
            file.getChildren();
            // removed other assertion
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/list-children-not-folder.error", file, e);
        }

        // Should be able to get child by name
        file = file.resolveFile("some-child");
        // removed other assertion

        // Check for unknown file
        file = getReadFolder().resolveFile("unknown-file");
        // removed other assertion
        try {
            file.getChildren();
            // removed other assertion
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/list-children-not-folder.error", file, e);
        }

        // Should be able to get child by name
        final FileObject child = file.resolveFile("some-child");
        assertNotNull(child);
    }

    @Test
    public void testExists_1_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        assertTrue("file exists", file.exists());
    }

    @Test
    public void testExists_2_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        assertNotSame("file exists", file.getType(), FileType.IMAGINARY);
    }

    @Test
    public void testExists_3_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolder().resolveFile("dir1");
        assertTrue("folder exists", file.exists());
    }

    @Test
    public void testExists_4_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolder().resolveFile("dir1");
        // removed other assertion
        assertNotSame("folder exists", file.getType(), FileType.IMAGINARY);
    }

    @Test
    public void testExists_5_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolder().resolveFile("dir1");
        // removed other assertion
        // removed other assertion

        // Test an unknown file
        file = getReadFolder().resolveFile("unknown-child");
        assertFalse("unknown file does not exist", file.exists());
    }

    @Test
    public void testExists_6_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolder().resolveFile("dir1");
        // removed other assertion
        // removed other assertion

        // Test an unknown file
        file = getReadFolder().resolveFile("unknown-child");
        // removed other assertion
        assertSame("unknown file does not exist", file.getType(), FileType.IMAGINARY);
    }

    @Test
    public void testExists_7_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolder().resolveFile("dir1");
        // removed other assertion
        // removed other assertion

        // Test an unknown file
        file = getReadFolder().resolveFile("unknown-child");
        // removed other assertion
        // removed other assertion

        // Test an unknown file in an unknown folder
        file = getReadFolder().resolveFile("unknown-folder/unknown-child");
        assertFalse("unknown file does not exist", file.exists());
    }

    @Test
    public void testExists_8_oe() throws Exception {
        // Test a file
        FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolder().resolveFile("dir1");
        // removed other assertion
        // removed other assertion

        // Test an unknown file
        file = getReadFolder().resolveFile("unknown-child");
        // removed other assertion
        // removed other assertion

        // Test an unknown file in an unknown folder
        file = getReadFolder().resolveFile("unknown-folder/unknown-child");
        // removed other assertion
        assertSame("unknown file does not exist", file.getType(), FileType.IMAGINARY);
    }

    @Test
    public void testGetString_Charset_1_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
    }
    }

    @Test
    public void testGetString_Charset_2_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            assertTrue(file.isFile());
    }
    }

    @Test
    public void testGetString_Charset_3_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            // removed other assertion

            assertEquals(FILE1_CONTENT, new String(file.getContent().getString(StandardCharsets.UTF_8)));
    }
    }

    @Test
    public void testGetString_String_1_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
    }
    }

    @Test
    public void testGetString_String_2_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            assertTrue(file.isFile());
    }
    }

    @Test
    public void testGetString_String_3_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            // removed other assertion

            assertEquals(FILE1_CONTENT, new String(file.getContent().getString(StandardCharsets.UTF_8.name())));
    }
    }

    @Test
    public void testInputStreamMultipleCleanup_1_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertEquals(FileType.FILE, file.getType());
    }

    @Test
    public void testInputStreamMultipleCleanup_2_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testInputStreamMultipleCleanup_3_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Open some input streams
        final InputStream instr1 = file.getContent().getInputStream();
        assertEquals(instr1.read(), FILE1_CONTENT.charAt(0));
    }

    @Test
    public void testInputStreamMultipleCleanup_4_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Open some input streams
        final InputStream instr1 = file.getContent().getInputStream();
        // removed other assertion
        final InputStream instr2 = file.getContent().getInputStream();
        assertEquals(instr2.read(), FILE1_CONTENT.charAt(0));
    }

    @Test
    public void testInputStreamMultipleCleanup_5_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Open some input streams
        final InputStream instr1 = file.getContent().getInputStream();
        // removed other assertion
        final InputStream instr2 = file.getContent().getInputStream();
        // removed other assertion

        // Close the file
        file.close();

        // Check
        assertEquals(instr1.read(), -1);
    }

    @Test
    public void testInputStreamMultipleCleanup_6_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Open some input streams
        final InputStream instr1 = file.getContent().getInputStream();
        // removed other assertion
        final InputStream instr2 = file.getContent().getInputStream();
        // removed other assertion

        // Close the file
        file.close();

        // Check
        // removed other assertion
        assertEquals(instr2.read(), -1);
    }

    @Test
    public void testInputStreamReadAll_1_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            assertEquals(FileType.FILE, file.getType());
    }
    }

    @Test
    public void testInputStreamReadAll_2_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            assertTrue(file.isFile());
    }
    }

    @Test
    public void testInputStreamReadAll_3_oe() throws Exception {
        // Get the test file
        try (final FileObject file = getReadFolder().resolveFile("file1.txt")) {
            // removed other assertion
            // removed other assertion

            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            file.getContent().write(output);
            assertEquals(FILE1_CONTENT, new String(output.toByteArray()));
    }
    }

    @Test
    public void testInputStreamSingleCleanup_1_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertEquals(FileType.FILE, file.getType());
    }

    @Test
    public void testInputStreamSingleCleanup_2_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testInputStreamSingleCleanup_3_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Open some input streams
        final InputStream instr1 = file.getContent().getInputStream();
        assertEquals(instr1.read(), FILE1_CONTENT.charAt(0));
    }

    @Test
    public void testInputStreamSingleCleanup_4_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        // removed other assertion

        // Open some input streams
        final InputStream instr1 = file.getContent().getInputStream();
        // removed other assertion

        // Close the file
        file.close();

        // Check
        assertEquals(instr1.read(), -1);
    }

    @Test
    public void testParent_1_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        assertTrue("folder exists", folder.exists());
    }

    @Test
    public void testParent_2_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        assertTrue("child exists", child.exists());
    }

    @Test
    public void testParent_3_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        assertSame(folder, child.getParent());
    }

    @Test
    public void testParent_4_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        assertTrue("folder exists", folder.exists());
    }

    @Test
    public void testParent_5_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        assertFalse("child does not exist", child.exists());
    }

    @Test
    public void testParent_6_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        assertSame(folder, child.getParent());
    }

    @Test
    public void testParent_7_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when neither exists
        folder = getReadFolder().resolveFile("unknown-folder");
        child = folder.resolveFile("unknown-file");
        assertFalse("folder does not exist", folder.exists());
    }

    @Test
    public void testParent_8_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when neither exists
        folder = getReadFolder().resolveFile("unknown-folder");
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        assertFalse("child does not exist", child.exists());
    }

    @Test
    public void testParent_9_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when neither exists
        folder = getReadFolder().resolveFile("unknown-folder");
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        assertSame(folder, child.getParent());
    }

    @Test
    public void testParent_10_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when neither exists
        folder = getReadFolder().resolveFile("unknown-folder");
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test the parent of the root of the file system
        // TODO - refactor out test cases for layered vs originating fs
        final FileSystem fileSystem = getFileSystem();
        final FileObject root = fileSystem.getRoot();
        if (fileSystem.getParentLayer() == null) {
            // No parent layer, so parent should be null
            assertNull("root has null parent", root.getParent());
    }
    }

    @Test
    public void testParent_11_oe() throws FileSystemException {
        // Test when both exist
        FileObject folder = getReadFolder().resolveFile("dir1");
        FileObject child = folder.resolveFile("file3.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when file does not exist
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test when neither exists
        folder = getReadFolder().resolveFile("unknown-folder");
        child = folder.resolveFile("unknown-file");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test the parent of the root of the file system
        // TODO - refactor out test cases for layered vs originating fs
        final FileSystem fileSystem = getFileSystem();
        final FileObject root = fileSystem.getRoot();
        if (fileSystem.getParentLayer() == null) {
            // No parent layer, so parent should be null
            // removed other assertion
        } else {
            // Parent should be parent of parent layer.
            assertSame(fileSystem.getParentLayer().getParent(), root.getParent());
    }
    }

    @Test
    public void testReadMultipleConcurrent_1_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testReadMultipleConcurrent_2_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        final FileObject emptyFile = getReadFolder().resolveFile("empty.txt");
        assertTrue(emptyFile.exists());
    }

    @Test
    public void testReadSingleConcurrent_1_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testReadSingleSequencial_1_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testReuse_1_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertEquals(FileType.FILE, file.getType());
    }

    @Test
    public void testReuse_2_oe() throws Exception {
        // Get the test file
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        assertTrue(file.isFile());
    }

}
