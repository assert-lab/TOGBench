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

    /**
     * Tests type determination.
     */


}
