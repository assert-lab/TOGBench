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
import org.apache.commons.vfs2.FileFilterSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSystemException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for {@link SizeFileFilter}.
 */
// CHECKSTYLE:OFF Test code
public class SizeFileFilterTest_OE25Dev extends BaseFilterTest {

    private static File testDir;

    private static File minFile;

    private static FileSelectInfo minFileInfo;

    private static File optFile;

    private static FileSelectInfo optFileInfo;

    private static File maxFile;

    private static FileSelectInfo maxFileInfo;

    private static File zipFile;

    private static FileObject zipFileObj;

    @AfterClass
    public static void afterClass() throws IOException {

        minFileInfo = null;
        minFile = null;

        optFileInfo = null;
        optFile = null;

        maxFileInfo = null;
        maxFile = null;

        zipFileObj.close();
        FileUtils.deleteQuietly(zipFile);
        zipFile = null;

        FileUtils.deleteDirectory(testDir);
        testDir = null;
    }

    @BeforeClass
    public static void beforeClass() throws IOException {
        testDir = getTestDir(SizeFileFilterTest_OE25Dev.class.getName());

        // 2 characters
        minFile = new File(testDir, "min.txt");
        FileUtils.write(minFile, "12");
        minFileInfo = createFileSelectInfo(minFile);

        // 4 characters
        optFile = new File(testDir, "opt.txt");
        FileUtils.write(optFile, "1234");
        optFileInfo = createFileSelectInfo(optFile);

        // 6 characters
        maxFile = new File(testDir, "max.txt");
        FileUtils.write(maxFile, "123456");
        maxFileInfo = createFileSelectInfo(maxFile);

        // Zip the test directory
        zipFile = new File(getTempDir(), SizeFileFilterTest_OE25Dev.class.getName() + ".zip");
        zipDir(testDir, "", zipFile);
        zipFileObj = getZipFileObject(zipFile);

    }

    @Test
    public void testSizeFileFilterLong_1_oe() throws FileSystemException {

        final SizeFileFilter testee = new SizeFileFilter(4);
        Assert.assertFalse(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeFileFilterLong_2_oe() throws FileSystemException {

        final SizeFileFilter testee = new SizeFileFilter(4);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeFileFilterLong_3_oe() throws FileSystemException {

        final SizeFileFilter testee = new SizeFileFilter(4);
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeFileFilterLongBoolean_1_oe() throws FileSystemException {

        SizeFileFilter testee;

        testee = new SizeFileFilter(4, true);
        Assert.assertFalse(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeFileFilterLongBoolean_2_oe() throws FileSystemException {

        SizeFileFilter testee;

        testee = new SizeFileFilter(4, true);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeFileFilterLongBoolean_3_oe() throws FileSystemException {

        SizeFileFilter testee;

        testee = new SizeFileFilter(4, true);
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeFileFilterLongBoolean_4_oe() throws FileSystemException {

        SizeFileFilter testee;

        testee = new SizeFileFilter(4, true);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeFileFilter(4, false);
        Assert.assertTrue(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeFileFilterLongBoolean_5_oe() throws FileSystemException {

        SizeFileFilter testee;

        testee = new SizeFileFilter(4, true);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeFileFilter(4, false);
        // removed other assertion
        Assert.assertFalse(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeFileFilterLongBoolean_6_oe() throws FileSystemException {

        SizeFileFilter testee;

        testee = new SizeFileFilter(4, true);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeFileFilter(4, false);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeFileFilterZipDir_1_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        Assert.assertEquals(2, files.length);
    }

    @Test
    public void testSizeFileFilterZipDir_2_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, false)));
        assertContains(files, minFile.getName());
        Assert.assertEquals(1, files.length);
    }

    @Test
    public void testSizeFileFilterZipDir_3_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, false)));
        assertContains(files, minFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 6)));
        assertContains(files, minFile.getName(), optFile.getName(), maxFile.getName());
        Assert.assertEquals(3, files.length);
    }

    @Test
    public void testSizeFileFilterZipDir_4_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, false)));
        assertContains(files, minFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 6)));
        assertContains(files, minFile.getName(), optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(3, 6)));
        assertContains(files, optFile.getName(), maxFile.getName());
        Assert.assertEquals(2, files.length);
    }

    @Test
    public void testSizeFileFilterZipDir_5_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, false)));
        assertContains(files, minFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 6)));
        assertContains(files, minFile.getName(), optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(3, 6)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 5)));
        assertContains(files, minFile.getName(), optFile.getName());
        Assert.assertEquals(2, files.length);
    }

    @Test
    public void testSizeFileFilterZipDir_6_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, false)));
        assertContains(files, minFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 6)));
        assertContains(files, minFile.getName(), optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(3, 6)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 5)));
        assertContains(files, minFile.getName(), optFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(3, 5)));
        assertContains(files, optFile.getName());
        Assert.assertEquals(1, files.length);
    }

    @Test
    public void testSizeFileFilterZipDir_7_oe() throws FileSystemException {

        // Same test with ZIP file
        FileObject[] files;

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, true)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeFileFilter(4, false)));
        assertContains(files, minFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 6)));
        assertContains(files, minFile.getName(), optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(3, 6)));
        assertContains(files, optFile.getName(), maxFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(2, 5)));
        assertContains(files, minFile.getName(), optFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(3, 5)));
        assertContains(files, optFile.getName());
        // removed other assertion

        files = zipFileObj.findFiles(new FileFilterSelector(new SizeRangeFileFilter(4, 4)));
        assertContains(files, optFile.getName());
        Assert.assertEquals(1, files.length);
    }

    @Test
    public void testSizeRangeFileFilter_1_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        Assert.assertTrue(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_2_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_3_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_4_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        Assert.assertFalse(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_5_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_6_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        Assert.assertTrue(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_7_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        Assert.assertTrue(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_8_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_9_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_10_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 5);
        Assert.assertFalse(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_11_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 5);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_12_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 5);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(maxFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_13_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(4, 4);
        Assert.assertFalse(testee.accept(minFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_14_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(4, 4);
        // removed other assertion
        Assert.assertTrue(testee.accept(optFileInfo));
    }

    @Test
    public void testSizeRangeFileFilter_15_oe() throws FileSystemException {

        SizeRangeFileFilter testee;

        testee = new SizeRangeFileFilter(2, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 6);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(2, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(3, 5);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        testee = new SizeRangeFileFilter(4, 4);
        // removed other assertion
        // removed other assertion
        Assert.assertFalse(testee.accept(maxFileInfo));
    }

}
// CHECKSTYLE:ON
