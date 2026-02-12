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
package org.apache.commons.vfs2.provider.test;

import java.io.File;

import org.apache.commons.vfs2.AbstractProviderTestCase;
import org.apache.commons.vfs2.AbstractVfsTestCase;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.Test;

/**
 * Additional junction test cases.
 */
public class JunctionTests_OE25Dev extends AbstractProviderTestCase {

    private FileObject getBaseDir() throws FileSystemException {
        final File file = AbstractVfsTestCase.getTestDirectoryFile();
        assertTrue(file.exists());
        return getManager().toFileObject(file);
    }

    /**
     * Checks ancestors are created when a junction is created.
     */

    /**
     * Checks nested junctions are not supported.
     */

    // Check that file @ junction point exists only when backing file exists
    // Add 2 junctions with common parent
    // Compare real and virtual files
    // Events
    // Remove junctions

    @Test
    public void testAncestors_1_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs://").getFileSystem();
        final FileObject baseDir = getBaseDir();

        // Make sure the file at the junction point and its ancestors do not exist
        FileObject file = fs.resolveFile("/a/b");
        assertFalse(file.exists());
    }

    @Test
    public void testAncestors_2_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs://").getFileSystem();
        final FileObject baseDir = getBaseDir();

        // Make sure the file at the junction point and its ancestors do not exist
        FileObject file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        assertFalse(file.exists());
    }

    @Test
    public void testAncestors_3_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs://").getFileSystem();
        final FileObject baseDir = getBaseDir();

        // Make sure the file at the junction point and its ancestors do not exist
        FileObject file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        // removed other assertion
        file = file.getParent();
        assertFalse(file.exists());
    }

    @Test
    public void testAncestors_4_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs://").getFileSystem();
        final FileObject baseDir = getBaseDir();

        // Make sure the file at the junction point and its ancestors do not exist
        FileObject file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        // removed other assertion
        file = file.getParent();
        // removed other assertion

        // Add the junction
        fs.addJunction("/a/b", baseDir);

        // Make sure the file at the junction point and its ancestors exist
        file = fs.resolveFile("/a/b");
        assertTrue("Does not exist", file.exists());
    }

    @Test
    public void testAncestors_5_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs://").getFileSystem();
        final FileObject baseDir = getBaseDir();

        // Make sure the file at the junction point and its ancestors do not exist
        FileObject file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        // removed other assertion
        file = file.getParent();
        // removed other assertion

        // Add the junction
        fs.addJunction("/a/b", baseDir);

        // Make sure the file at the junction point and its ancestors exist
        file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        assertTrue("Does not exist", file.exists());
    }

    @Test
    public void testAncestors_6_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs://").getFileSystem();
        final FileObject baseDir = getBaseDir();

        // Make sure the file at the junction point and its ancestors do not exist
        FileObject file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        // removed other assertion
        file = file.getParent();
        // removed other assertion

        // Add the junction
        fs.addJunction("/a/b", baseDir);

        // Make sure the file at the junction point and its ancestors exist
        file = fs.resolveFile("/a/b");
        // removed other assertion
        file = file.getParent();
        // removed other assertion
        file = file.getParent();
        assertTrue("Does not exist", file.exists());
    }

    @Test
    public void testNestedJunction_2_oe_1_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs:").getFileSystem();
        final FileObject baseDir = getBaseDir();
        fs.addJunction("/a", baseDir);

        // Nested
        try {
            fs.addJunction("/a/b", baseDir);
            // removed other assertion
        } catch (final Exception e) {
                        final String code0 = "vfs.impl/nested-junction.error";
            final Object param0 = "vfs:/a/b";
            final Throwable throwable0 = e;
            assertSameMessage(code0, new Object[] { param0 }, throwable0);
    }
    }

    @Test
    public void testNestedJunction_4_oe_1_oe() throws Exception {
        final FileSystem fs = getManager().createVirtualFileSystem("vfs:").getFileSystem();
        final FileObject baseDir = getBaseDir();
        fs.addJunction("/a", baseDir);

        // Nested
        try {
            fs.addJunction("/a/b", baseDir);
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }

        // At same point
        try {
            fs.addJunction("/a", baseDir);
            // removed other assertion
        } catch (final Exception e) {
                        final String code0 = "vfs.impl/nested-junction.error";
            final Object param0 = "vfs:/a";
            final Throwable throwable0 = e;
            assertSameMessage(code0, new Object[] { param0 }, throwable0);
    }
    }

}
