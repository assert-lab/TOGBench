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

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.vfs2.provider.local.LocalFileSystem;
import org.junit.Assert;
import org.junit.Test;

/**
 * Additional file permission tests.
 *
 * Used by Local and SFTP File System.
 *
 * @since 2.1
 */
public class PermissionsTests_OE25Dev extends AbstractProviderTestCase {
    public static final String FILENAME = "permission.txt";

    private FileObject createTestFile() throws Exception {
        // Get the scratch folder
        final FileObject scratchFolder = getWriteFolder();
        assertNotNull(scratchFolder);

        // Make sure the test folder is empty
        scratchFolder.delete(Selectors.EXCLUDE_SELF);
        scratchFolder.createFolder();

        // Create direct child of the test folder
        final FileObject file = scratchFolder.resolveFile(FILENAME);
        assertFalse(file.exists());

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        return file;
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.CREATE, Capability.DELETE, Capability.READ_CONTENT,
                Capability.WRITE_CONTENT, };
    }

    /**
     * Returns true if the file system is a LocalFileSystem on Windows
     */
    private boolean isWindows() {
        return SystemUtils.IS_OS_WINDOWS && this.getFileSystem() instanceof LocalFileSystem;
    }

    /**
     * Clean up the permission-modified file to not affect other tests.
     */
    @Override
    protected void tearDown() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        final FileObject file = scratchFolder.resolveFile(FILENAME);
        file.setWritable(true, true);
        file.delete();

        super.tearDown();
    }

    /**
     * Tests for the execution permission.
     */

    /**
     * Tests for the readable permission
     */

    /**
     * Tests for the writable permission
     */


}
