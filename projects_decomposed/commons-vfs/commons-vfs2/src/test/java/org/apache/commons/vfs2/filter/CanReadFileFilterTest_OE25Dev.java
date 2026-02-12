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
 * Test for {@link CanReadFileFilter}.
 */
// CHECKSTYLE:OFF Test code
public class CanReadFileFilterTest_OE25Dev extends BaseFilterTest {

    private static final String WRITABLE = "writable.txt";

    private static final String READONLY = "readonly.txt";

    private static File testDir;

    private static File writableFile;

    private static FileSelectInfo writableFileInfo;

    private static File readOnlyFile;

    private static FileSelectInfo readOnlyFileInfo;

    private static File notExistingFile;

    private static FileSelectInfo notExistingFileInfo;

    private static File zipFile;

    private static FileObject zipFileObj;

    @AfterClass
    public static void afterClass() throws IOException {

        writableFileInfo = null;
        writableFile.delete();
        writableFile = null;

        readOnlyFileInfo = null;
        readOnlyFile.delete();
        readOnlyFile = null;

        notExistingFileInfo = null;
        notExistingFile = null;

        zipFileObj.close();
        FileUtils.deleteQuietly(zipFile);
        zipFile = null;

        FileUtils.deleteDirectory(testDir);
        testDir = null;

    }

    @BeforeClass
    public static void beforeClass() throws IOException {

        testDir = getTestDir(CanReadFileFilterTest_OE25Dev.class.getName());

        writableFile = new File(testDir, WRITABLE);
        writableFileInfo = createFileSelectInfo(writableFile);
        FileUtils.touch(writableFile);

        readOnlyFile = new File(testDir, READONLY);
        readOnlyFileInfo = createFileSelectInfo(readOnlyFile);
        FileUtils.touch(readOnlyFile);
        readOnlyFile.setReadable(true);
        readOnlyFile.setWritable(false);

        notExistingFile = new File(testDir, "not-existing-file.txt");
        notExistingFileInfo = createFileSelectInfo(notExistingFile);

        zipFile = new File(getTempDir(), CanReadFileFilterTest_OE25Dev.class.getName() + ".zip");
        zipDir(testDir, "", zipFile);
        zipFileObj = getZipFileObject(zipFile);

    }


}
// CHECKSTYLE:ON
