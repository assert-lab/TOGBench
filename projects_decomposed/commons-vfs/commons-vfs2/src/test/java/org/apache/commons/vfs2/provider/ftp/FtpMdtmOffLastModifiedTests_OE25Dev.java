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

package org.apache.commons.vfs2.provider.ftp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;

import org.apache.commons.vfs2.AbstractTestSuite;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.LastModifiedTests;
import org.junit.Test;

public class FtpMdtmOffLastModifiedTests_OE25Dev extends LastModifiedTests {

    /**
     * Tests getting the last modified time of a file.
     */

    @Test
    public void testGetLastModifiedFileInexactMatch_1_oe_1_oe() throws IOException {
        final String fileName = "file1.txt";
        getFileSystem().getFileSystemManager().getFilesCache().clear(getFileSystem());
        final FileObject readFolder = getReadFolder();
        final FileObject fileObject = readFolder.resolveFile(fileName);
        final long lastModifiedTimeMillis = fileObject.getContent().getLastModifiedTime();
        // now try to match
        final long lastModTimeAccuracyMillis = (long) readFolder.getFileSystem().getLastModTimeAccuracy();
        final FileTime lastModifiedTime = Files
            .getLastModifiedTime(Paths.get(getTestDirectory(), AbstractTestSuite.READ_TESTS_FOLDER, fileName));
                final String message0 = "getLastModified on File";
        final long expected0 = lastModifiedTime.toMillis();
        final long actual0 = lastModifiedTimeMillis;
        final long delta0 = Math.max(lastModTimeAccuracyMillis, Duration.ofMinutes(1).toMillis());
        if (expected0 == actual0) {
                    return;
                }
                // getLastModTimeAccuracy() is not accurate
                final long actualDelta0 = Math.abs(expected0 - actual0);
                if (actualDelta0 > Math.max(delta0, 1000)) {
                    Assert.fail(String.format("%s expected0=%,d(%s),actual0=%,d(%s),expected0 delta0=%,d,actual0 delta0=%,d",message0,Long.valueOf(expected0),new Date(expected0).toString(),Long.valueOf(actual0),new Date(actual0).toString(),Long.valueOf(delta0),Long.valueOf(actualDelta0)));
    }
    }

}
