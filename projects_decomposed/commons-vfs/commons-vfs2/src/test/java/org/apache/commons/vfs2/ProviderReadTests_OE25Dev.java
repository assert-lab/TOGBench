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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Read-only test cases for file providers.
 * <p>
 * TODO - Test getLastModified(), getAttribute().
 */
public class ProviderReadTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Walks a folder structure, asserting it contains exactly the expected files and folders.
     */
    protected void assertSameStructure(final FileObject folder, final FileInfo expected) throws Exception {
        // Setup the structure
        final List<FileInfo> queueExpected = new ArrayList<>();
        queueExpected.add(expected);

        final List<FileObject> queueActual = new ArrayList<>();
        queueActual.add(folder);

        while (!queueActual.isEmpty()) {
            final FileObject file = queueActual.remove(0);
            final FileInfo info = queueExpected.remove(0);

            // Check the type is correct
            assertSame(info.type, file.getType());

            if (info.type == FileType.FILE) {
                continue;
            }

            // Check children
            final FileObject[] children = file.getChildren();

            // Make sure all children were found
            assertNotNull(children);
            int length = children.length;
            if (info.children.size() != children.length) {
                for (final FileObject element : children) {
                    if (element.getName().getBaseName().startsWith(".")) {
                        --length;
                        continue;
                    }
                    System.out.println(element.getName());
                }
            }

            assertEquals("count children of \"" + file.getName() + "\"", info.children.size(), length);

            // Recursively check each child
            for (final FileObject child : children) {
                final String childName = child.getName().getBaseName();
                if (childName.startsWith(".")) {
                    continue;
                }
                final FileInfo childInfo = info.children.get(childName);

                // Make sure the child is expected
                assertNotNull(childInfo);

                // Add to the queue of files to check
                queueExpected.add(childInfo);
                queueActual.add(child);
            }
        }
    }

    /**
     * Returns the read folder named "dir1".
     *
     * @return the read folder named "dir1".
     * @throws FileSystemException
     */
    protected FileObject getReadFolderDir1() throws FileSystemException {
        return getReadFolder().resolveFile("dir1");
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.GET_TYPE, Capability.LIST_CHILDREN, Capability.READ_CONTENT };
    }

    private FileObject resolveFile1Txt() throws FileSystemException {
        return getReadFolder().resolveFile("file1.txt");
    }

    /**
     * Tests can perform operations on a folder while reading from a different files.
     */

    /**
     * Tests that we can traverse a folder that has JAR name.
     */

    /**
     * Tests that a folder can't be layered.
     */

    /**
     * Tests that findFiles() works.
     */

    /**
     * Tests that folders have no content.
     */
    @Test
    public void testFolderContent() throws Exception {
        if (getFileSystem().hasCapability(Capability.DIRECTORY_READ_CONTENT)) {
            // test wont fail
            return;
        }

        // Try getting the content of a folder
        final FileObject folder = getReadFolderDir1();
        try {
            folder.getContent().getInputStream();
            fail();
        } catch (final FileSystemException e) {
            assertSameMessage("vfs.provider/read-not-file.error", folder, e);
        }
    }

    /**
     * Tests that test read folder is not hidden.
     */

    /**
     * Tests that test read folder is readable.
     */

    /**
     * Tests that test read folder is not a symbolic link.
     */

    /**
     * Tests can read multiple time end of stream of empty file
     */

    /**
     * Tests can read multiple time end of stream
     */

    /**
     * Tests the contents of root of file system can be listed.
     */
    @Test
    public void testRoot() throws FileSystemException {
        if (!this.getProviderConfig().isFileSystemRootAccessible()) {
            return;
        }
        final FileSystem fs = getFileSystem();
        final String uri = fs.getRootURI();
        final FileObject file = getManager().resolveFile(uri, fs.getFileSystemOptions());
        file.getChildren();
    }

    /**
     * Tests that FileObjects can be sorted.
     */

    /**
     * Walks the base folder structure, asserting it contains exactly the expected files and folders.
     */
    @Test
    public void testStructure() throws Exception {
        final FileInfo baseInfo = buildExpectedStructure();
        assertSameStructure(getReadFolder(), baseInfo);
    }

    /**
     * Tests type determination.
     */

@Test
    public void testConcurrentReadFolder_1_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        assertTrue(file.exists());
    }

@Test
    public void testConcurrentReadFolder_2_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        // removed other assertion
        final FileObject folder = getReadFolderDir1();
        assertTrue(folder.exists());
    }

@Test
    public void testDotJarFolderName_1_oe() throws Exception {
        final FileObject folder = getReadFolderDir1().resolveFile("subdir4.jar");
        Assert.assertTrue(folder.exists());
    }

@Test
    public void testDotJarFolderName_2_oe() throws Exception {
        final FileObject folder = getReadFolderDir1().resolveFile("subdir4.jar");
        // removed other assertion
        final FileObject file = folder.resolveFile("file1.txt");
        Assert.assertTrue(file.exists());
    }

@Test
    public void testDotJarFolderNameLayer_1_oe() throws Exception {
        final FileObject folder = getReadFolderDir1().resolveFile("subdir4.jar");
        Assert.assertTrue("subdir4.jar/ must exist as folder, check test setup.", folder.isFolder());
    }

@Test
    public void testDotJarFolderNameLayer_2_oe() throws Exception {
        final FileObject folder = getReadFolderDir1().resolveFile("subdir4.jar");
        // removed other assertion
        Assert.assertFalse("subdir4.jar/ must not be layerable", getManager().canCreateFileSystem(folder));
    }

@Test
    public void testDotJarFolderNameLayer_4_oe() throws Exception {
        final FileObject folder = getReadFolderDir1().resolveFile("subdir4.jar");
        // removed other assertion
        // removed other assertion
        try {
            final FileObject ignored = getManager().createFileSystem(folder);
            // removed other assertion
        } catch (final FileSystemException e) {
            assertSame("Creation of layered filesystem should fail" + e,"vfs.impl/no-provider-for-file.error",e.getCode());
    }
    }

@Test
    public void testFindFiles_1_oe() throws Exception {
        final FileInfo fileInfo = buildExpectedStructure();
        final VerifyingFileSelector selector = new VerifyingFileSelector(fileInfo);

        // Find the files
        final FileObject[] actualFiles = getReadFolder().findFiles(selector);

        // Compare actual and expected list of files
        final List<FileObject> expectedFiles = selector.finish();
        assertEquals(expectedFiles.size(), actualFiles.length);
    }

@Test
    public void testFindFiles_2_oe() throws Exception {
        final FileInfo fileInfo = buildExpectedStructure();
        final VerifyingFileSelector selector = new VerifyingFileSelector(fileInfo);

        // Find the files
        final FileObject[] actualFiles = getReadFolder().findFiles(selector);

        // Compare actual and expected list of files
        final List<FileObject> expectedFiles = selector.finish();
        // removed other assertion
        final int count = expectedFiles.size();
        for (int i = 0; i < count; i++) {
            final FileObject expected = expectedFiles.get(i);
            final FileObject actual = actualFiles[i];
            assertEquals(expected, actual);
    }
    }

@Test
    public void testFolderIsHidden_1_oe() throws Exception {
        final FileObject folder = getReadFolderDir1();
        Assert.assertFalse(folder.isHidden());
    }

@Test
    public void testFolderIsReadable_1_oe() throws Exception {
        final FileObject folder = getReadFolderDir1();
        Assert.assertTrue(folder.isReadable());
    }

@Test
    public void testFolderIsSymbolicLink_1_oe() throws Exception {
        final FileObject folder = getReadFolderDir1();
        Assert.assertFalse(folder.isSymbolicLink());
    }

@Test
    public void testGetContent_1_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        assertTrue(file.exists());
    }

@Test
    public void testGetContent_2_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        // removed other assertion
        final FileContent content = file.getContent();
        assertNotNull(content);
    }

@Test
    public void testGetContentInfo_1_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        assertTrue(file.exists());
    }

@Test
    public void testGetContentInfo_2_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        // removed other assertion
        final FileContent content = file.getContent();
        assertNotNull(content);
    }

@Test
    public void testGetContentInfo_3_oe() throws Exception {
        final FileObject file = resolveFile1Txt();
        // removed other assertion
        final FileContent content = file.getContent();
        // removed other assertion
        final FileContentInfo contentInfo = content.getContentInfo();
        assertNotNull(contentInfo);
    }

@Test
    public void testReadEmptyMultipleEOF_1_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("empty.txt");
        assertTrue(file.exists());
    }

@Test
    public void testReadFileEOFMultiple_1_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        assertTrue(file.exists());
    }

@Test
    public void testReadFileEOFMultiple_2_oe() throws Exception {
        final FileObject file = getReadFolder().resolveFile("file1.txt");
        // removed other assertion
        assertEquals("Expecting 20 bytes test-data file1.txt", 20, file.getContent().getSize());
    }

@Test
    public void testSort_1_oe() throws FileSystemException {
        final FileInfo fileInfo = buildExpectedStructure();
        final VerifyingFileSelector selector = new VerifyingFileSelector(fileInfo);

        // Find the files
        final FileObject[] actualFiles = getReadFolder().findFiles(selector);
        Arrays.sort(actualFiles);
        FileObject prevActualFile = actualFiles[0];
        for (final FileObject actualFile : actualFiles) {
            assertTrue(prevActualFile.toString().compareTo(actualFile.toString()) <= 0);
    }
    }

@Test
    public void testSort_2_oe() throws FileSystemException {
        final FileInfo fileInfo = buildExpectedStructure();
        final VerifyingFileSelector selector = new VerifyingFileSelector(fileInfo);

        // Find the files
        final FileObject[] actualFiles = getReadFolder().findFiles(selector);
        Arrays.sort(actualFiles);
        FileObject prevActualFile = actualFiles[0];
        for (final FileObject actualFile : actualFiles) {
            // removed other assertion
            prevActualFile = actualFile;
        }

        // Compare actual and expected list of files
        final List<FileObject> expectedFiles = selector.finish();
        Collections.sort(expectedFiles);
        assertEquals(expectedFiles.size(), actualFiles.length);
    }

@Test
    public void testSort_3_oe() throws FileSystemException {
        final FileInfo fileInfo = buildExpectedStructure();
        final VerifyingFileSelector selector = new VerifyingFileSelector(fileInfo);

        // Find the files
        final FileObject[] actualFiles = getReadFolder().findFiles(selector);
        Arrays.sort(actualFiles);
        FileObject prevActualFile = actualFiles[0];
        for (final FileObject actualFile : actualFiles) {
            // removed other assertion
            prevActualFile = actualFile;
        }

        // Compare actual and expected list of files
        final List<FileObject> expectedFiles = selector.finish();
        Collections.sort(expectedFiles);
        // removed other assertion
        final int count = expectedFiles.size();
        for (int i = 0; i < count; i++) {
            final FileObject expected = expectedFiles.get(i);
            final FileObject actual = actualFiles[i];
            assertEquals(expected, actual);
    }
    }

@Test
    public void testType_1_oe() throws Exception {
        // Test a file
        FileObject file = resolveFile1Txt();
        assertSame(FileType.FILE, file.getType());
    }

@Test
    public void testType_2_oe() throws Exception {
        // Test a file
        FileObject file = resolveFile1Txt();
        // removed other assertion
        assertTrue(file.isFile());
    }

@Test
    public void testType_3_oe() throws Exception {
        // Test a file
        FileObject file = resolveFile1Txt();
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolderDir1();
        assertSame(FileType.FOLDER, file.getType());
    }

@Test
    public void testType_4_oe() throws Exception {
        // Test a file
        FileObject file = resolveFile1Txt();
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolderDir1();
        // removed other assertion
        assertTrue(file.isFolder());
    }

@Test
    public void testType_5_oe() throws Exception {
        // Test a file
        FileObject file = resolveFile1Txt();
        // removed other assertion
        // removed other assertion

        // Test a folder
        file = getReadFolderDir1();
        // removed other assertion
        // removed other assertion

        // Test an unknown file
        file = getReadFolder().resolveFile("unknown-child");
        assertSame(FileType.IMAGINARY, file.getType());
    }

}
