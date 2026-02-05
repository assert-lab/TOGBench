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
package org.apache.commons.vfs2.filter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileFilter;
import org.apache.commons.vfs2.FileFilterSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link EmptyFileFilter}.
 */
// CHECKSTYLE:OFF Test code
public class EmptyFileFilterTest_OE25Dev extends BaseFilterTest {

    private static File testDir;

    private static File notEmptyFile;

    private static FileSelectInfo notEmptyFileInfo;

    private static File emptyFile;

    private static FileSelectInfo emptyFileInfo;

    private static File notEmptyDir;

    private static FileSelectInfo notEmptyDirInfo;

    private static File emptyDir;

    private static FileSelectInfo emptyDirInfo;

    private static File notExistingFile;

    private static FileSelectInfo notExistingFileInfo;

    private static File zipFile;

    private static FileObject zipFileObj;

    @AfterClass
    public static void afterClass() throws IOException {

        notEmptyFile = null;
        notEmptyFileInfo = null;
        emptyFile = null;
        emptyFileInfo = null;
        notEmptyDir = null;
        notEmptyDirInfo = null;
        emptyDir = null;
        emptyDirInfo = null;
        notExistingFile = null;
        notExistingFileInfo = null;

        zipFileObj.close();
        FileUtils.deleteQuietly(zipFile);
        zipFile = null;

        FileUtils.deleteDirectory(testDir);
        testDir = null;
    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        testDir = getTestDir(EmptyFileFilterTest_OE25Dev.class.getName());
        testDir.mkdir();

        notEmptyFile = new File(testDir, "full.txt");
        FileUtils.write(notEmptyFile, "whatever");
        notEmptyFileInfo = createFileSelectInfo(notEmptyFile);

        emptyFile = new File(testDir, "empty.txt");
        FileUtils.touch(emptyFile);
        emptyFileInfo = createFileSelectInfo(emptyFile);

        notEmptyDir = new File(testDir, "full-dir");
        notEmptyDir.mkdir();
        notEmptyDirInfo = createFileSelectInfo(notEmptyDir);
        FileUtils.touch(new File(notEmptyDir, "foobar.txt"));

        emptyDir = new File(testDir, "empty-dir");
        emptyDir.mkdir();
        emptyDirInfo = createFileSelectInfo(emptyDir);

        notExistingFile = new File(testDir, "not-existing-file.txt");
        notExistingFileInfo = createFileSelectInfo(notExistingFile);

        // Zip the test directory
        zipFile = new File(getTempDir(), EmptyFileFilterTest_OE25Dev.class.getName() + ".zip");
        zipDir(testDir, "", zipFile);
        zipFileObj = getZipFileObject(zipFile);

    }

    @Test
    public void testAcceptEmpty_1_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.EMPTY;

        Assert.assertFalse(testee.accept(notEmptyFileInfo));
    }

    @Test
    public void testAcceptEmpty_2_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.EMPTY;

        // removed other assertion
        Assert.assertTrue(testee.accept(emptyFileInfo));
    }

    @Test
    public void testAcceptEmpty_3_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.EMPTY;

        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(notEmptyDirInfo));
    }

    @Test
    public void testAcceptEmpty_4_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.EMPTY;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(emptyDirInfo));
    }

    @Test
    public void testAcceptEmpty_5_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.EMPTY;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(notExistingFileInfo));
    }

    @Test
    public void testAcceptNotEmpty_1_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.NOT_EMPTY;

        Assert.assertTrue(testee.accept(notEmptyFileInfo));
    }

    @Test
    public void testAcceptNotEmpty_2_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.NOT_EMPTY;

        // removed other assertion
        Assert.assertFalse(testee.accept(emptyFileInfo));
    }

    @Test
    public void testAcceptNotEmpty_3_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.NOT_EMPTY;

        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(notEmptyDirInfo));
    }

    @Test
    public void testAcceptNotEmpty_4_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.NOT_EMPTY;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(emptyDirInfo));
    }

    @Test
    public void testAcceptNotEmpty_5_oe() throws FileSystemException {

        final FileFilter testee = EmptyFileFilter.NOT_EMPTY;

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(notExistingFileInfo));
    }

    @Test
    public void testZipFile_2_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(EmptyFileFilter.EMPTY));
        // removed other assertion
        Assert.assertEquals(1, files.length);
    }

    @Test
    public void testZipFile_4_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(EmptyFileFilter.EMPTY));
        // removed other assertion
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(EmptyFileFilter.NOT_EMPTY));
        // removed other assertion
        Assert.assertEquals(2, files.length);
    }

}
// CHECKSTYLE:ON
