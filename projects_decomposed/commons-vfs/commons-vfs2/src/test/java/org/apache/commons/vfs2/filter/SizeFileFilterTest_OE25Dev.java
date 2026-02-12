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


}
// CHECKSTYLE:ON
