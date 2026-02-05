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

import org.junit.Test;

/**
 * Test cases for file naming.
 * <p>
 * TODO - Add tests for all FileName methods.
 */
public class NamingTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Asserts that a particular relative name is invalid for a particular scope.
     */
    private void assertBadName(final FileName name, final String relName, final NameScope scope) {
        try {
            getManager().resolveName(name, relName, scope);
            fail("expected failure");
        } catch (final FileSystemException e) {
            // TODO - should check error message
        }
    }

    /**
     * Checks that a relative name resolves to the expected absolute path. Tests both forward and back slashes.
     */
    private void assertSameName(final String expectedPath, final FileName baseName, final String relName)
            throws Exception {
        assertSameName(expectedPath, baseName, relName, NameScope.FILE_SYSTEM);
    }

    /**
     * Checks that a relative name resolves to the expected absolute path. Tests both forward and back slashes.
     */
    private void assertSameName(final String expectedPath, final FileName baseName, final String relName,
            final NameScope scope) throws Exception {
        // Try the supplied name
        FileName name = getManager().resolveName(baseName, relName, scope);
        assertEquals(expectedPath, name.getPath());

        String temp;

        // Replace the separators
        temp = relName.replace('\\', '/');
        name = getManager().resolveName(baseName, temp, scope);
        assertEquals(expectedPath, name.getPath());

        // And again
        temp = relName.replace('/', '\\');
        name = getManager().resolveName(baseName, temp, scope);
        assertEquals(expectedPath, name.getPath());
    }

    /**
     * Tests resolution of absolute names.
     */
    private void checkAbsoluteNames(final FileName name) throws Exception {
        // Root
        assertSameName("/", name, "/");
        assertSameName("/", name, "//");
        assertSameName("/", name, "/.");
        assertSameName("/", name, "/some file/..");

        // Some absolute names
        assertSameName("/a", name, "/a");
        assertSameName("/a", name, "/./a");
        assertSameName("/a", name, "/a/.");
        assertSameName("/a/b", name, "/a/b");

        // Some bad names
        assertBadName(name, "/..", NameScope.FILE_SYSTEM);
        assertBadName(name, "/a/../..", NameScope.FILE_SYSTEM);
    }

    /**
     * Name resolution tests that are common for CHILD or DESCENDENT scope.
     */
    private void checkDescendentNames(final FileName name, final NameScope scope) throws Exception {
        // Make some assumptions about the name
        assertFalse(name.getPath().equals("/"));
        assertFalse(name.getPath().endsWith("/a"));
        assertFalse(name.getPath().endsWith("/a/b"));

        // Test names with the same prefix
        final String path = name.getPath() + "/a";
        assertSameName(path, name, path, scope);
        assertSameName(path, name, "../" + name.getBaseName() + "/a", scope);

        // Test an empty name
        assertBadName(name, "", scope);

        // Test . name
        assertBadName(name, ".", scope);
        assertBadName(name, "./", scope);

        // Test ancestor names
        assertBadName(name, "..", scope);
        assertBadName(name, "../a", scope);
        assertBadName(name, "../" + name.getBaseName() + "a", scope);
        assertBadName(name, "a/..", scope);

        // Test absolute names
        assertBadName(name, "/", scope);
        assertBadName(name, "/a", scope);
        assertBadName(name, "/a/b", scope);
        assertBadName(name, name.getPath(), scope);
        assertBadName(name, name.getPath() + "a", scope);
    }

    /**
     * Tests conversion from absolute to relative names.
     */

    /**
     * Tests resolution of absolute names.
     */
    @Test
    public void testAbsoluteNames() throws Exception {
        // Test against the base folder
        FileName name = getReadFolder().getName();
        checkAbsoluteNames(name);

        // Test against the root
        name = getReadFolder().getFileSystem().getRoot().getName();
        checkAbsoluteNames(name);

        // Test against some unknown file
        name = getManager().resolveName(name, "a/b/unknown");
        checkAbsoluteNames(name);
    }

    /**
     * Tests child file names.
     */

    /**
     * Tests descendent name resolution.
     */
    @Test
    public void testDescendentName() throws Exception {
        final FileName baseName = getReadFolder().getName();

        // Test direct child
        String path = baseName.getPath() + "/some-child";
        assertSameName(path, baseName, "some-child", NameScope.DESCENDENT);

        // Test compound name
        path = path + "/grand-child";
        assertSameName(path, baseName, "some-child/grand-child", NameScope.DESCENDENT);

        // Test relative names
        assertSameName(path, baseName, "./some-child/grand-child", NameScope.DESCENDENT);
        assertSameName(path, baseName, "./nada/../some-child/grand-child", NameScope.DESCENDENT);
        assertSameName(path, baseName, "some-child/./grand-child", NameScope.DESCENDENT);

        // Test badly formed descendent names
        checkDescendentNames(baseName, NameScope.DESCENDENT);
    }

    /**
     * Tests relative name resolution, relative to the base folder.
     */
    @Test
    public void testNameResolution() throws Exception {
        final FileName baseName = getReadFolder().getName();
        final String parentPath = baseName.getParent().getPath();
        final String path = baseName.getPath();
        final String childPath = path + "/some-child";

        // Test empty relative path
        assertSameName(path, baseName, "");

        // Test . relative path
        assertSameName(path, baseName, ".");

        // Test ./ relative path
        assertSameName(path, baseName, "./");

        // Test .// relative path
        assertSameName(path, baseName, ".//");

        // Test .///.///. relative path
        assertSameName(path, baseName, ".///.///.");
        assertSameName(path, baseName, "./\\/.\\//.");

        // Test <elem>/.. relative path
        assertSameName(path, baseName, "a/..");

        // Test .. relative path
        assertSameName(parentPath, baseName, "..");

        // Test ../ relative path
        assertSameName(parentPath, baseName, "../");

        // Test ..//./ relative path
        assertSameName(parentPath, baseName, "..//./");
        assertSameName(parentPath, baseName, "..//.\\");

        // Test <elem>/../.. relative path
        assertSameName(parentPath, baseName, "a/../..");

        // Test <elem> relative path
        assertSameName(childPath, baseName, "some-child");

        // Test ./<elem> relative path
        assertSameName(childPath, baseName, "./some-child");

        // Test ./<elem>/ relative path
        assertSameName(childPath, baseName, "./some-child/");

        // Test <elem>/././././ relative path
        assertSameName(childPath, baseName, "./some-child/././././");

        // Test <elem>/../<elem> relative path
        assertSameName(childPath, baseName, "a/../some-child");

        // Test <elem>/<elem>/../../<elem> relative path
        assertSameName(childPath, baseName, "a/b/../../some-child");
    }

    /**
     * Tests resolution of relative file names via the FS manager
     */

    /**
     * Tests encoding of relative URI.
     */

    /**
     * Checks that a file name converts to an expected relative path
     */
    private void testRelName(final FileName baseName, final String relPath) throws Exception {
        final FileName expectedName = getManager().resolveName(baseName, relPath);

        // Convert to relative path, and check
        final String actualRelPath = baseName.getRelativeName(expectedName);
        assertEquals(relPath, actualRelPath);
    }

    /**
     * Tests the root file name.
     */

@Test
    public void testAbsoluteNameConvert_1_oe() throws Exception {
        final FileName baseName = getReadFolder().getName();

        String path = "/test1/test2";
        FileName name = getManager().resolveName(baseName, path);
        assertEquals(path, name.getPath());
    }

@Test
    public void testAbsoluteNameConvert_2_oe() throws Exception {
        final FileName baseName = getReadFolder().getName();

        String path = "/test1/test2";
        FileName name = getManager().resolveName(baseName, path);
        // removed other assertion

        // Try child and descendent names
        testRelName(name, "child");
        testRelName(name, "child1/child2");

        // Try own name
        testRelName(name, ".");

        // Try parent, and root
        testRelName(name, "..");
        testRelName(name, "../..");

        // Try sibling and descendent of sibling
        testRelName(name, "../sibling");
        testRelName(name, "../sibling/child");

        // Try siblings with similar names
        testRelName(name, "../test2_not");
        testRelName(name, "../test2_not/child");
        testRelName(name, "../test");
        testRelName(name, "../test/child");

        // Try unrelated
        testRelName(name, "../../unrelated");
        testRelName(name, "../../test");
        testRelName(name, "../../test/child");

        // Test against root
        path = "/";
        name = getManager().resolveName(baseName, path);
        assertEquals(path, name.getPath());
    }

@Test
    public void testChildName_1_oe() throws Exception {
        final FileName baseName = getReadFolder().getName();
        final String basePath = baseName.getPath();
        final FileName name = getManager().resolveName(baseName, "some-child", NameScope.CHILD);

        // Test path is absolute
        assertTrue("is absolute", basePath.startsWith("/"));
    }

@Test
    public void testChildName_2_oe() throws Exception {
        final FileName baseName = getReadFolder().getName();
        final String basePath = baseName.getPath();
        final FileName name = getManager().resolveName(baseName, "some-child", NameScope.CHILD);

        // Test path is absolute
        // removed other assertion

        // Test base name
        assertEquals("base name", "some-child", name.getBaseName());
    }

@Test
    public void testChildName_3_oe() throws Exception {
        final FileName baseName = getReadFolder().getName();
        final String basePath = baseName.getPath();
        final FileName name = getManager().resolveName(baseName, "some-child", NameScope.CHILD);

        // Test path is absolute
        // removed other assertion

        // Test base name
        // removed other assertion

        // Test absolute path
        assertEquals("absolute path", basePath + "/some-child", name.getPath());
    }

@Test
    public void testChildName_4_oe() throws Exception {
        final FileName baseName = getReadFolder().getName();
        final String basePath = baseName.getPath();
        final FileName name = getManager().resolveName(baseName, "some-child", NameScope.CHILD);

        // Test path is absolute
        // removed other assertion

        // Test base name
        // removed other assertion

        // Test absolute path
        // removed other assertion

        // Test parent path
        assertEquals("parent absolute path", basePath, name.getParent().getPath());
    }

@Test
    public void testRelativeURI_1_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());

        // Locate the base dir
        FileObject file = getManager().resolveFile(".");
        assertSame("file object", getReadFolder(), file);
    }

@Test
    public void testRelativeURI_2_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());

        // Locate the base dir
        FileObject file = getManager().resolveFile(".");
        // removed other assertion

        // Locate a child
        file = getManager().resolveFile("some-child");
        assertSame("file object", getReadFolder(), file.getParent());
    }

@Test
    public void testRelativeURI_3_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());

        // Locate the base dir
        FileObject file = getManager().resolveFile(".");
        // removed other assertion

        // Locate a child
        file = getManager().resolveFile("some-child");
        // removed other assertion

        // Locate a descendent
        file = getManager().resolveFile("some-folder/some-file");
        assertSame("file object", getReadFolder(), file.getParent().getParent());
    }

@Test
    public void testRelativeURI_4_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());

        // Locate the base dir
        FileObject file = getManager().resolveFile(".");
        // removed other assertion

        // Locate a child
        file = getManager().resolveFile("some-child");
        // removed other assertion

        // Locate a descendent
        file = getManager().resolveFile("some-folder/some-file");
        // removed other assertion

        // Locate parent
        file = getManager().resolveFile("..");
        assertSame("file object", getReadFolder().getParent(), file);
    }

@Test
    public void testRelativeUriEncoding_1_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());
        final String path = getReadFolder().getName().getPath();

        // §1 Encode "some file"
        FileObject file = getManager().resolveFile("%73%6f%6d%65%20%66%69%6c%65");
        assertEquals(path + "/some file", file.getName().getPathDecoded());
    }

@Test
    public void testRelativeUriEncoding_2_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());
        final String path = getReadFolder().getName().getPath();

        // §1 Encode "some file"
        FileObject file = getManager().resolveFile("%73%6f%6d%65%20%66%69%6c%65");
        // removed other assertion

        // §2 Encode "."
        file = getManager().resolveFile("%2e");
        // 18-6-2005 imario@apache.org: no need to keep the "current directory"
        // assertEquals(path + "/.", file.getName().getPathDecoded());
        assertEquals(path, file.getName().getPathDecoded());
    }

@Test
    public void testRelativeUriEncoding_3_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());
        final String path = getReadFolder().getName().getPath();

        // §1 Encode "some file"
        FileObject file = getManager().resolveFile("%73%6f%6d%65%20%66%69%6c%65");
        // removed other assertion

        // §2 Encode "."
        file = getManager().resolveFile("%2e");
        // 18-6-2005 imario@apache.org: no need to keep the "current directory"
        // assertEquals(path + "/.", file.getName().getPathDecoded());
        // removed other assertion

        // §3 Encode '%'
        file = getManager().resolveFile("a%25");
        assertEquals(path + "/a%", file.getName().getPathDecoded());
    }

@Test
    public void testRelativeUriEncoding_4_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());
        final String path = getReadFolder().getName().getPath();

        // §1 Encode "some file"
        FileObject file = getManager().resolveFile("%73%6f%6d%65%20%66%69%6c%65");
        // removed other assertion

        // §2 Encode "."
        file = getManager().resolveFile("%2e");
        // 18-6-2005 imario@apache.org: no need to keep the "current directory"
        // assertEquals(path + "/.", file.getName().getPathDecoded());
        // removed other assertion

        // §3 Encode '%'
        file = getManager().resolveFile("a%25");
        // removed other assertion

        // §4 Encode /
        file = getManager().resolveFile("dir%2fchild");
        assertEquals(path + "/dir/child", file.getName().getPathDecoded());
    }

@Test
    public void testRelativeUriEncoding_5_oe() throws Exception {
        // Build base dir
        getManager().setBaseFile(getReadFolder());
        final String path = getReadFolder().getName().getPath();

        // §1 Encode "some file"
        FileObject file = getManager().resolveFile("%73%6f%6d%65%20%66%69%6c%65");
        // removed other assertion

        // §2 Encode "."
        file = getManager().resolveFile("%2e");
        // 18-6-2005 imario@apache.org: no need to keep the "current directory"
        // assertEquals(path + "/.", file.getName().getPathDecoded());
        // removed other assertion

        // §3 Encode '%'
        file = getManager().resolveFile("a%25");
        // removed other assertion

        // §4 Encode /
        file = getManager().resolveFile("dir%2fchild");
        // removed other assertion

        // §5 Encode \
        file = getManager().resolveFile("dir%5cchild");
        // 18-6-2005 imario@apache.org: all file separators normalized to "/"
        // decided to do this to get the same behavior as in §4 on windows
        // platforms
        // assertEquals(path + "/dir\\child", file.getName().getPathDecoded());
        assertEquals(path + "/dir/child", file.getName().getPathDecoded());
    }

@Test
    public void testRootFileName_1_oe() throws Exception {
        // Locate the root file
        final FileName rootName = getReadFolder().getFileSystem().getRoot().getName();

        // Test that the root path is "/"
        assertEquals("root path", "/", rootName.getPath());
    }

@Test
    public void testRootFileName_2_oe() throws Exception {
        // Locate the root file
        final FileName rootName = getReadFolder().getFileSystem().getRoot().getName();

        // Test that the root path is "/"
        // removed other assertion

        // Test that the root basname is ""
        assertEquals("root base name", "", rootName.getBaseName());
    }

@Test
    public void testRootFileName_3_oe() throws Exception {
        // Locate the root file
        final FileName rootName = getReadFolder().getFileSystem().getRoot().getName();

        // Test that the root path is "/"
        // removed other assertion

        // Test that the root basname is ""
        // removed other assertion

        // Test that the root name has no parent
        assertNull("root parent", rootName.getParent());
    }

}
