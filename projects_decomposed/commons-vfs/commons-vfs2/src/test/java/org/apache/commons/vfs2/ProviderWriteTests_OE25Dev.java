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


}
