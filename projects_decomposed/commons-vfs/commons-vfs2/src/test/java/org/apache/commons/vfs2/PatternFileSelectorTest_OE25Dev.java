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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests FileExtensionSelector.
 *
 * @since 2.1
 */
public class PatternFileSelectorTest_OE25Dev {
    private static FileObject BaseFolder;

    /**
     * 9 files and 1 directory = 10
     */
    private static final int EntryCount = 10;

    private static final int ExtensionCount = 3;

    private static final int FilesPerExtensionCount = 3;

    static FileObject getBaseFolder() {
        return BaseFolder;
    }

    /**
     * Creates a RAM FS.
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        BaseFolder = VFS.getManager().resolveFile("ram://" + PatternFileSelectorTest_OE25Dev.class.getName());
        BaseFolder.deleteAll();
        BaseFolder.createFolder();
        BaseFolder.resolveFile("aa.htm").createFile();
        BaseFolder.resolveFile("aa.html").createFile();
        BaseFolder.resolveFile("aa.xhtml").createFile();
        BaseFolder.resolveFile("b.htm").createFile();
        BaseFolder.resolveFile("b.html").createFile();
        BaseFolder.resolveFile("b.xhtml").createFile();
        BaseFolder.resolveFile("c.htm").createFile();
        BaseFolder.resolveFile("c.html").createFile();
        BaseFolder.resolveFile("c.xhtml").createFile();
    }

    /**
     * Deletes RAM FS files.
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        if (BaseFolder != null) {
            BaseFolder.deleteAll();
        }
    }

    /**
     * Tests a one extension selector.
     *
     * @throws Exception
     */

    /**
     * Tests matching all
     *
     * @throws Exception
     */

    /**
     * Tests matching partial file names
     *
     * @throws Exception
     */

    /**
     * Tests matching partial file names with delimiter
     *
     * @throws Exception
     */

    /**
     * Tests a null selector.
     *
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testNullString() throws Exception {
        // Yep, this will blow up.
        new PatternFileSelector((String) null);
    }

@Test
    public void testFileExtensions_1_oe() throws Exception {
        final FileObject[] foArray = BaseFolder.findFiles(Selectors.SELECT_FILES);
        Assert.assertTrue(foArray.length > 0);
    }

@Test
    public void testFileExtensions_2_oe() throws Exception {
        final FileObject[] foArray = BaseFolder.findFiles(Selectors.SELECT_FILES);
        // removed other assertion
        final String regExPrefix = ".*\\.";
        // gather file extensions.
        final Set<String> extensionSet = new HashSet<>();
        for (final FileObject fo : foArray) {
            extensionSet.add(regExPrefix + fo.getName().getExtension());
        }
        final String message = String.format("Extensions: %s; files: %s", extensionSet.toString(),
                Arrays.asList(foArray).toString());
        assertEquals(message, ExtensionCount, extensionSet.size());
    }

@Test
    public void testFileExtensions_3_oe() throws Exception {
        final FileObject[] foArray = BaseFolder.findFiles(Selectors.SELECT_FILES);
        // removed other assertion
        final String regExPrefix = ".*\\.";
        // gather file extensions.
        final Set<String> extensionSet = new HashSet<>();
        for (final FileObject fo : foArray) {
            extensionSet.add(regExPrefix + fo.getName().getExtension());
        }
        final String message = String.format("Extensions: %s; files: %s", extensionSet.toString(),
                Arrays.asList(foArray).toString());
        // removed other assertion
        // check each extension
        for (final String extension : extensionSet) {
            final FileSelector selector = new PatternFileSelector(extension);
            final FileObject[] list = BaseFolder.findFiles(selector);
            assertEquals(FilesPerExtensionCount, list.length);
    }
    }

@Test
    public void testFileExtensions_4_oe() throws Exception {
        final FileObject[] foArray = BaseFolder.findFiles(Selectors.SELECT_FILES);
        // removed other assertion
        final String regExPrefix = ".*\\.";
        // gather file extensions.
        final Set<String> extensionSet = new HashSet<>();
        for (final FileObject fo : foArray) {
            extensionSet.add(regExPrefix + fo.getName().getExtension());
        }
        final String message = String.format("Extensions: %s; files: %s", extensionSet.toString(),
                Arrays.asList(foArray).toString());
        // removed other assertion
        // check each extension
        for (final String extension : extensionSet) {
            final FileSelector selector = new PatternFileSelector(extension);
            final FileObject[] list = BaseFolder.findFiles(selector);
            // removed other assertion
        }
        // check each file against itself
        for (final FileObject fo : foArray) {
            final FileSelector selector = new PatternFileSelector(regExPrefix + fo.getName().getExtension());
            final FileObject[] list = BaseFolder.findFiles(selector);
            assertEquals(FilesPerExtensionCount, list.length);
    }
    }

@Test
    public void testMatchAll_1_oe() throws Exception {
        final FileObject[] list = BaseFolder.findFiles(new PatternFileSelector(".*"));
        assertEquals(EntryCount, list.length);
    }

@Test
    public void testMatchPartial_1_oe() throws Exception {
        final FileObject[] list = BaseFolder.findFiles(new PatternFileSelector(".*a.htm"));
        assertEquals(1, list.length);
    }

@Test
    public void testMatchPartial_2_oe() throws Exception {
        final FileObject[] list = BaseFolder.findFiles(new PatternFileSelector(".*a.htm"));
        // removed other assertion
        assertEquals("aa.htm", list[0].getName().getBaseName());
    }

@Test
    public void testMatchPartialDelimited_1_oe() throws Exception {
        final FileObject[] list = BaseFolder.findFiles(new PatternFileSelector("^.*\\/b.htm$"));
        assertEquals(1, list.length);
    }

@Test
    public void testMatchPartialDelimited_2_oe() throws Exception {
        final FileObject[] list = BaseFolder.findFiles(new PatternFileSelector("^.*\\/b.htm$"));
        // removed other assertion
        assertEquals("b.htm", list[0].getName().getBaseName());
    }

}
