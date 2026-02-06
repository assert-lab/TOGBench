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

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * File system test that check that a file system can be modified.
 */
public class ProviderWriteTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * A test listener.
     */
    private static class TestListener implements FileListener {
        private static final Object CREATE = "create";
        private static final Object DELETE = "delete";
        private static final Object CHANGED = "changed";
        private final FileObject file;
        private final ArrayList<Object> events = new ArrayList<>();

        public TestListener(final FileObject file) {
            this.file = file;
        }

        public void addCreateEvent() {
            events.add(CREATE);
        }

        public void addDeleteEvent() {
            events.add(DELETE);
        }

        public void assertFinished() {
            assertEquals("Missing event", 0, events.size());
        }

        @Override
        public void fileChanged(final FileChangeEvent event) throws Exception {
            assertFalse("Unexpected changed event", events.isEmpty());
            assertSame("Expecting a changed event", CHANGED, events.remove(0));
            assertEquals(Objects.toString(file), file, event.getFileObject());
            try {
                assertFalse(Objects.toString(file), file.exists());
            } catch (final FileSystemException e) {
                fail();
            }
        }

        /**
         * Called when a file is created.
         */
        @Override
        public void fileCreated(final FileChangeEvent event) {
            assertFalse("Unexpected create event", events.isEmpty());
            assertSame("Expecting a create event", CREATE, events.remove(0));
            assertEquals(Objects.toString(file), file, event.getFileObject());
            try {
                assertTrue(Objects.toString(file), file.exists());
            } catch (final FileSystemException e) {
                fail();
            }
        }

        /**
         * Called when a file is deleted.
         */
        @Override
        public void fileDeleted(final FileChangeEvent event) {
            assertFalse("Unexpected delete event", events.isEmpty());
            assertSame("Expecting a delete event", DELETE, events.remove(0));
            assertEquals(Objects.toString(file), file, event.getFileObject());
            try {
                assertFalse(Objects.toString(file), file.exists());
            } catch (final FileSystemException e) {
                fail();
            }
        }
    }

    /**
     * Ensures the names of a set of files match an expected set.
     */
    private void assertSameFileSet(final Set<String> names, final FileObject[] files) {
        // Make sure the sets are the same length
        assertEquals(names.size(), files.length);

        // Check for unexpected names
        for (final FileObject file : files) {
            assertTrue(names.contains(file.getName().getBaseName()));
        }
    }

    /**
     * Sets up a scratch folder for the test to use.
     */
    protected FileObject createScratchFolder() throws Exception {
        final FileObject scratchFolder = getWriteFolder();

        // Make sure the test folder is empty
        scratchFolder.delete(Selectors.EXCLUDE_SELF);
        scratchFolder.createFolder();

        return scratchFolder;
    }

    protected FileObject getReadFolderDir1() throws FileSystemException {
        return getReadFolder().resolveFile("dir1");
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.CREATE, Capability.DELETE, Capability.GET_TYPE, Capability.LIST_CHILDREN,
                Capability.READ_CONTENT, Capability.WRITE_CONTENT };
    }

    /**
     * Tests overwriting a file on the same file system.
     */

    /**
     * Tests file copy to and from the same file system type. This was a problem w/ FTP.
     */

    /**
     * Tests create-delete-create-a-file sequence on the same file system.
     */

    /*
      Tests concurrent read and write on the same file fails.
     */
    /*
     * imario@apache.org leave this to some sort of LockManager public void testConcurrentReadWrite() throws Exception {
     * final FileObject scratchFolder = createScratchFolder();
     *
     * final FileObject file = scratchFolder.resolveFile("file1.txt"); file.createFile();
     *
     * // Start reading from the file final InputStream instr = file.getContent().getInputStream();
     *
     * try { // Try to write to the file file.getContent().getOutputStream(); fail(); } catch (final FileSystemException
     * e) { // Check error message assertSameMessage("vfs.provider/write-in-use.error", file, e); } finally {
     * instr.close(); } }
     */

    /*
      Tests concurrent writes on the same file fails.
     */
    /*
     * imario@apache.org leave this to some sort of LockManager public void testConcurrentWrite() throws Exception {
     * final FileObject scratchFolder = createScratchFolder();
     *
     * final FileObject file = scratchFolder.resolveFile("file1.txt"); file.createFile();
     *
     * // Start writing to the file final OutputStream outstr = file.getContent().getOutputStream(); final String
     * testContent = "some content"; try { // Write some content to the first stream
     * outstr.write(testContent.getBytes());
     *
     * // Try to open another output stream file.getContent().getOutputStream(); fail(); } catch (final
     * FileSystemException e) { // Check error message assertSameMessage("vfs.provider/write-in-use.error", file, e); }
     * finally { outstr.close(); }
     *
     * // Make sure that the content written to the first stream is actually applied assertSameContent(testContent,
     * file); }
     */

    /**
     * Tests deletion
     */

    /**
     * Tests deletion
     */

    /**
     * Tests file creation
     */

    /**
     * Tests file/folder creation with mismatched types.
     */

    /**
     * Tests folder creation.
     */

    /**
     * Tests that test read folder is not hidden.
     */

    /**
     * Tests that test read folder is readable.
     */

    /**
     * Tests that test folder iswritable.
     */

    /**
     * Test that children are handled correctly by create and delete.
     */

    /**
     * Check listeners are notified of changes.
     */

    /**
     * Tests overwriting a file on the same file system.
     */

    /**
     * Tests file write to and from the same file system type
     */

    @Test
    public void testCopyFromOverwriteSameFileSystem_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        final FileObject file = scratchFolder.resolveFile("file1.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testCopySameFileSystem_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        final FileObject file = scratchFolder.resolveFile("file1.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testCreateDeleteCreateSameFileSystem_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        final FileObject file = scratchFolder.resolveFile("file1.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testDelete_1_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testDelete_2_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDelete_3_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testDelete_4_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDelete_5_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        assertTrue(file.exists());
    }

    @Test
    public void testDelete_6_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDelete_7_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testDelete_8_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        assertTrue(file2.exists());
    }

    @Test
    public void testDelete_9_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDelete_10_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        // removed other assertion
        assertFalse(file2.exists());
    }

    @Test
    public void testDelete_11_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        // removed other assertion
        // removed other assertion

        // Delete a file that does not exist
        file = folder.resolveFile("some-folder/some-file");
        assertFalse(file.exists());
    }

    @Test
    public void testDelete_12_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        // removed other assertion
        // removed other assertion

        // Delete a file that does not exist
        file = folder.resolveFile("some-folder/some-file");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_1_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_2_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_3_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_4_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_5_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        assertTrue(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_6_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_7_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_8_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        assertTrue(file2.exists());
    }

    @Test
    public void testDeleteAllDescendents_9_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_10_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        // removed other assertion
        assertFalse(file2.exists());
    }

    @Test
    public void testDeleteAllDescendents_11_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        // removed other assertion
        // removed other assertion

        // Delete a file that does not exist
        file = folder.resolveFile("some-folder/some-file");
        assertFalse(file.exists());
    }

    @Test
    public void testDeleteAllDescendents_12_oe() throws Exception {
        // Set-up the test structure
        final FileObject folder = createScratchFolder();
        folder.resolveFile("file1.txt").createFile();
        folder.resolveFile("file%25.txt").createFile();
        folder.resolveFile("emptydir").createFolder();
        folder.resolveFile("dir1/file1.txt").createFile();
        folder.resolveFile("dir1/dir2/file2.txt").createFile();

        // Delete a file
        FileObject file = folder.resolveFile("file1.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete a special name file
        file = folder.resolveFile("file%25.txt");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Delete an empty folder
        file = folder.resolveFile("emptydir");
        // removed other assertion
        file.deleteAll();
        // removed other assertion

        // Recursive delete
        file = folder.resolveFile("dir1");
        final FileObject file2 = file.resolveFile("dir2/file2.txt");
        // removed other assertion
        // removed other assertion
        file.deleteAll();
        // removed other assertion
        // removed other assertion

        // Delete a file that does not exist
        file = folder.resolveFile("some-folder/some-file");
        // removed other assertion
        file.deleteAll();
        assertFalse(file.exists());
    }

    @Test
    public void testFileCreate_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testFileCreate_2_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        assertTrue(file.exists());
    }

    @Test
    public void testFileCreate_3_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        assertSame(FileType.FILE, file.getType());
    }

    @Test
    public void testFileCreate_4_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testFileCreate_5_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, file.getContent().getSize());
    }

    @Test
    public void testFileCreate_6_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.getContent().isEmpty());
    }

    @Test
    public void testFileCreate_7_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isHidden());
    }

    @Test
    public void testFileCreate_8_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isSymbolicLink());
    }

    @Test
    public void testFileCreate_9_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isReadable());
    }

    @Test
    public void testFileCreate_10_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isWriteable());
    }

    @Test
    public void testFileCreate_11_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testFileCreate_12_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        assertTrue(file.exists());
    }

    @Test
    public void testFileCreate_13_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        assertSame(FileType.FILE, file.getType());
    }

    @Test
    public void testFileCreate_14_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testFileCreate_15_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, file.getContent().getSize());
    }

    @Test
    public void testFileCreate_16_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isHidden());
    }

    @Test
    public void testFileCreate_17_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isReadable());
    }

    @Test
    public void testFileCreate_18_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isWriteable());
    }

    @Test
    public void testFileCreate_19_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testFileCreate_20_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        assertFalse(file.getParent().exists());
    }

    @Test
    public void testFileCreate_21_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        assertFalse(file.getParent().getParent().exists());
    }

    @Test
    public void testFileCreate_22_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        assertTrue(file.exists());
    }

    @Test
    public void testFileCreate_23_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        assertSame(FileType.FILE, file.getType());
    }

    @Test
    public void testFileCreate_24_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testFileCreate_25_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, file.getContent().getSize());
    }

    @Test
    public void testFileCreate_26_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.getParent().exists());
    }

    @Test
    public void testFileCreate_27_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.getParent().getParent().exists());
    }

    @Test
    public void testFileCreate_28_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.getParent().isHidden());
    }

    @Test
    public void testFileCreate_29_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.getParent().getParent().isHidden());
    }

    @Test
    public void testFileCreate_30_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test creating a file that already exists
        assertTrue(file.exists());
    }

    @Test
    public void testFileCreate_31_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test creating a file that already exists
        // removed other assertion
        file.createFile();
        assertTrue(file.exists());
    }

    @Test
    public void testFileCreate_32_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test creating a file that already exists
        // removed other assertion
        file.createFile();
        // removed other assertion
        assertTrue(file.isReadable());
    }

    @Test
    public void testFileCreate_33_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject file = scratchFolder.resolveFile("file1.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create direct child of the test folder - special name
        file = scratchFolder.resolveFile("file1%25.txt");
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        file = scratchFolder.resolveFile("dir1/dir1/file1.txt");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test creating a file that already exists
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        assertTrue(file.isWriteable());
    }

    @Test
    public void testFileCreateMismatched_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create a test file and folder
        final FileObject file = scratchFolder.resolveFile("dir1/file1.txt");
        file.createFile();
        assertEquals(FileType.FILE, file.getType());
    }

    @Test
    public void testFileCreateMismatched_2_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create a test file and folder
        final FileObject file = scratchFolder.resolveFile("dir1/file1.txt");
        file.createFile();
        // removed other assertion
        assertTrue(file.isFile());
    }

    @Test
    public void testFileCreateMismatched_3_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create a test file and folder
        final FileObject file = scratchFolder.resolveFile("dir1/file1.txt");
        file.createFile();
        // removed other assertion
        // removed other assertion

        final FileObject folder = scratchFolder.resolveFile("dir1/dir2");
        folder.createFolder();
        assertEquals(FileType.FOLDER, folder.getType());
    }

    @Test
    public void testFileCreateMismatched_4_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create a test file and folder
        final FileObject file = scratchFolder.resolveFile("dir1/file1.txt");
        file.createFile();
        // removed other assertion
        // removed other assertion

        final FileObject folder = scratchFolder.resolveFile("dir1/dir2");
        folder.createFolder();
        // removed other assertion
        assertTrue(folder.isFolder());
    }

    @Test
    public void testFolderCreate_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        assertFalse(folder.exists());
    }

    @Test
    public void testFolderCreate_2_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        assertTrue(folder.exists());
    }

    @Test
    public void testFolderCreate_3_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        assertSame(FileType.FOLDER, folder.getType());
    }

    @Test
    public void testFolderCreate_4_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        assertTrue(folder.isFolder());
    }

    @Test
    public void testFolderCreate_5_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, folder.getChildren().length);
    }

    @Test
    public void testFolderCreate_6_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        assertFalse(folder.exists());
    }

    @Test
    public void testFolderCreate_7_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        assertFalse(folder.getParent().exists());
    }

    @Test
    public void testFolderCreate_8_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        assertFalse(folder.getParent().getParent().exists());
    }

    @Test
    public void testFolderCreate_9_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        assertTrue(folder.exists());
    }

    @Test
    public void testFolderCreate_10_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        assertSame(FileType.FOLDER, folder.getType());
    }

    @Test
    public void testFolderCreate_11_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        assertTrue(folder.isFolder());
    }

    @Test
    public void testFolderCreate_12_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, folder.getChildren().length);
    }

    @Test
    public void testFolderCreate_13_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(folder.getParent().exists());
    }

    @Test
    public void testFolderCreate_14_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(folder.getParent().getParent().exists());
    }

    @Test
    public void testFolderCreate_15_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        FileObject folder = scratchFolder.resolveFile("dir1");
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Create a descendant, where the intermediate folders don't exist
        folder = scratchFolder.resolveFile("dir2/dir1/dir1");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        folder.createFolder();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Test creating a folder that already exists
        assertTrue(folder.exists());
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
    public void testFolderIsWritable_1_oe() throws Exception {
        final FileObject folder = getWriteFolder().resolveFile("dir1");
        Assert.assertTrue(folder.isWriteable());
    }

    @Test
    public void testListChildren_1_oe() throws Exception {
        final FileObject folder = createScratchFolder();
        final HashSet<String> names = new HashSet<>();

        // Make sure the folder is empty
        assertEquals(0, folder.getChildren().length);
    }

    @Test
    public void testListChildren_8_oe() throws Exception {
        final FileObject folder = createScratchFolder();
        final HashSet<String> names = new HashSet<>();

        // Make sure the folder is empty
        // removed other assertion

        // Create a child folder
        folder.resolveFile("dir1").createFolder();
        names.add("dir1");
        // removed other assertion

        // Create a child file
        folder.resolveFile("file1.html").createFile();
        names.add("file1.html");
        // removed other assertion

        // Create a descendent
        folder.resolveFile("dir2/file1.txt").createFile();
        names.add("dir2");
        // removed other assertion

        // Create a child file via an output stream
        final OutputStream outstr = folder.resolveFile("file2.txt").getContent().getOutputStream();
        outstr.close();
        names.add("file2.txt");
        // removed other assertion

        // Delete a child folder
        folder.resolveFile("dir1").deleteAll();
        names.remove("dir1");
        // removed other assertion

        // Delete a child file
        folder.resolveFile("file1.html").deleteAll();
        names.remove("file1.html");
        // removed other assertion

        // Recreate the folder
        folder.deleteAll();
        folder.createFolder();
        assertEquals(0, folder.getChildren().length);
    }

    @Test
    public void testListener_1_oe() throws Exception {
        final FileObject baseFile = createScratchFolder();

        final FileObject child = baseFile.resolveFile("newfile.txt");
        assertFalse(child.exists());
    }

    @Test
    public void testOverwriteSameFileSystem_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        final FileObject file = scratchFolder.resolveFile("file1.txt");
        assertFalse(file.exists());
    }

    @Test
    public void testWriteSameFileSystem_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();

        // Create direct child of the test folder
        final FileObject fileSource = scratchFolder.resolveFile("file1.txt");
        assertFalse(fileSource.exists());
    }

}
