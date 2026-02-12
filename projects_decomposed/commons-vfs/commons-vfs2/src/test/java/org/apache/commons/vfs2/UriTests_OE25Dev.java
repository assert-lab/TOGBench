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

import java.net.URI;

import org.junit.Test;

/**
 * URI test cases for providers.
 */
public class UriTests_OE25Dev extends AbstractProviderTestCase {

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

    @Test
    public void testReservedCharacterSpace() throws FileSystemException {
        try (final FileObject fileObject = getReadFolder().resolveFile("file with spaces.txt")) {
            final URI url = fileObject.getURI();
            final String string = url.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
        }
        try (final FileObject fileObject = getReadFolder().resolveFile("file%20with%20spaces.txt")) {
            final URI url = fileObject.getURI();
            final String string = url.toString();
            assertTrue(string, string.contains("file%20with%20spaces.txt"));
        }
    }

    /**
     * Tests content.
     */

    @Test
    public void testAbsoluteURI_1_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its URI
        final String uri = readFolder.getName().getURI();
        FileObject file = getManager().resolveFile(uri, readFolder.getFileSystem().getFileSystemOptions());
        assertSame("file object", readFolder, file);
    }

    @Test
    public void testAbsoluteURI_2_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its URI
        final String uri = readFolder.getName().getURI();
        FileObject file = getManager().resolveFile(uri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion

        // Try fetching the filesystem root by its URI
        final String rootUri = readFolder.getName().getRootURI();
        file = getManager().resolveFile(rootUri, readFolder.getFileSystem().getFileSystemOptions());
        assertSame(readFolder.getFileSystem().getRoot(), file);
    }

    @Test
    public void testAbsoluteURI_3_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its URI
        final String uri = readFolder.getName().getURI();
        FileObject file = getManager().resolveFile(uri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion

        // Try fetching the filesystem root by its URI
        final String rootUri = readFolder.getName().getRootURI();
        file = getManager().resolveFile(rootUri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion
        assertEquals(rootUri, file.getName().getRootURI());
    }

    @Test
    public void testAbsoluteURI_4_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its URI
        final String uri = readFolder.getName().getURI();
        FileObject file = getManager().resolveFile(uri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion

        // Try fetching the filesystem root by its URI
        final String rootUri = readFolder.getName().getRootURI();
        file = getManager().resolveFile(rootUri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion
        // removed other assertion
        assertEquals(rootUri, file.getName().getURI());
    }

    @Test
    public void testAbsoluteURI_5_oe() throws Exception {
        final FileObject readFolder = getReadFolder();

        // Try fetching base folder again by its URI
        final String uri = readFolder.getName().getURI();
        FileObject file = getManager().resolveFile(uri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion

        // Try fetching the filesystem root by its URI
        final String rootUri = readFolder.getName().getRootURI();
        file = getManager().resolveFile(rootUri, readFolder.getFileSystem().getFileSystemOptions());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(FileName.ROOT_PATH, file.getName().getPath());
    }

    @Test
    public void testGetURI_1_oe() throws Exception {
        final FileObject fileObject = getReadFolder().resolveFile("some-dir/");
        final URI uri = fileObject.getURI();

        // FileName#getURI() returns a String, not a URI.
        assertEquals(fileObject.getName().getURI(), uri.toString());
    }

    @Test
    public void testGetURI_2_oe() throws Exception {
        final FileObject fileObject = getReadFolder().resolveFile("some-dir/");
        final URI uri = fileObject.getURI();

        // FileName#getURI() returns a String, not a URI.
        // removed other assertion
        assertEquals(URI.create(fileObject.getName().getURI()), uri);
    }

    @Test
    public void testGetURI_3_oe() throws Exception {
        final FileObject fileObject = getReadFolder().resolveFile("some-dir/");
        final URI uri = fileObject.getURI();

        // FileName#getURI() returns a String, not a URI.
        // removed other assertion
        // removed other assertion

        assertEquals(fileObject.getURL().toString(), fileObject.getURI().toString());
    }

    @Test
    public void testGetURI_4_oe() throws Exception {
        final FileObject fileObject = getReadFolder().resolveFile("some-dir/");
        final URI uri = fileObject.getURI();

        // FileName#getURI() returns a String, not a URI.
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals(fileObject.getURL().toURI(), fileObject.getURI());
    }

    @Test
    public void testURIContentProvider_1_oe() throws Exception {
        // Test non-empty file
        final FileObject fileObject = getReadFolder().resolveFile("file1.txt");
        assertTrue(fileObject.exists());
    }

    @Test
    public void testURIContentProvider_2_oe() throws Exception {
        // Test non-empty file
        final FileObject fileObject = getReadFolder().resolveFile("file1.txt");
        // removed other assertion

        final URI uri = fileObject.getURI();
        final String uriStr = uri.toString();
        final FileSystemOptions options = getReadFolder().getFileSystem().getFileSystemOptions();

        final FileObject f1 = getManager().resolveFile(uriStr, options);
        final FileObject f2 = getManager().resolveFile(uriStr, options);

        assertEquals("Two files resolved by URI must be equals on " + uriStr, f1, f2);
    }

    @Test
    public void testURIContentProvider_3_oe() throws Exception {
        // Test non-empty file
        final FileObject fileObject = getReadFolder().resolveFile("file1.txt");
        // removed other assertion

        final URI uri = fileObject.getURI();
        final String uriStr = uri.toString();
        final FileSystemOptions options = getReadFolder().getFileSystem().getFileSystemOptions();

        final FileObject f1 = getManager().resolveFile(uriStr, options);
        final FileObject f2 = getManager().resolveFile(uriStr, options);

        // removed other assertion
        assertSame("Resolving two times should not produce new filesystem on " + uriStr,f1.getFileSystem(),f2.getFileSystem());
    }

}
