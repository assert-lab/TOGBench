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
package org.apache.commons.vfs2.provider.webdav.test;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.vfs2.AbstractProviderTestCase;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.provider.URLFileName;
import org.apache.commons.vfs2.provider.webdav.WebdavFileSystemConfigBuilder;
import org.apache.jackrabbit.webdav.version.DeltaVConstants;
import org.apache.jackrabbit.webdav.version.VersionControlledResource;
import org.junit.Test;

/**
 * Test to verify Webdav Versioning support
 */
public class WebdavVersioningTests_OE25Dev extends AbstractProviderTestCase {

    /**
     * Sets up a scratch folder for the test to use.
     */
    protected FileObject createScratchFolder() throws Exception {
        final FileObject scratchFolder = getWriteFolder();

        // Make sure the test folder is empty
        scratchFolder.delete(Selectors.EXCLUDE_SELF);
        scratchFolder.createFolder();

        return scratchFolder;
    }

@Test
    public void testVersioning_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        assertSame(opts, newOpts);
    }

@Test
    public void testVersioning_2_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        assertTrue(builder.isVersioning(newOpts));
    }

@Test
    public void testVersioning_3_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        assertFalse(file.exists());
    }

@Test
    public void testVersioning_4_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        assertTrue(file.exists());
    }

@Test
    public void testVersioning_5_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        assertSame(FileType.FILE, file.getType());
    }

@Test
    public void testVersioning_6_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        assertTrue(file.isFile());
    }

@Test
    public void testVersioning_7_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, file.getContent().getSize());
    }

@Test
    public void testVersioning_8_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.getContent().isEmpty());
    }

@Test
    public void testVersioning_9_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isExecutable());
    }

@Test
    public void testVersioning_10_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isHidden());
    }

@Test
    public void testVersioning_11_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isReadable());
    }

@Test
    public void testVersioning_12_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isWriteable());
    }

@Test
    public void testVersioning_13_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        assertTrue(map.containsKey(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }

@Test
    public void testVersioning_14_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        if (name != null) {
            assertEquals(name, map.get(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }
    }

@Test
    public void testVersioning_15_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        if (name != null) {
            // removed other assertion
        }
        assertTrue(map.containsKey(VersionControlledResource.CHECKED_IN.toString()));
    }

@Test
    public void testVersioning_16_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        if (name != null) {
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        assertSameContent(content, file);
    }

@Test
    public void testVersioning_17_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        if (name != null) {
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        assertTrue(map.containsKey(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }

@Test
    public void testVersioning_18_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        if (name != null) {
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        // removed other assertion
        if (name != null) {
            assertEquals(name, map.get(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }
    }

@Test
    public void testVersioning_19_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        if (name != null) {
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        // removed other assertion
        if (name != null) {
            // removed other assertion
        }
        assertTrue(map.containsKey(VersionControlledResource.CHECKED_IN.toString()));
    }

@Test
    public void testVersioningWithCreator_1_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        assertSame(opts, newOpts);
    }

@Test
    public void testVersioningWithCreator_2_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        assertTrue(builder.isVersioning(newOpts));
    }

@Test
    public void testVersioningWithCreator_3_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        assertFalse(file.exists());
    }

@Test
    public void testVersioningWithCreator_4_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        assertTrue(file.exists());
    }

@Test
    public void testVersioningWithCreator_5_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        assertSame(FileType.FILE, file.getType());
    }

@Test
    public void testVersioningWithCreator_6_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        assertTrue(file.isFile());
    }

@Test
    public void testVersioningWithCreator_7_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, file.getContent().getSize());
    }

@Test
    public void testVersioningWithCreator_8_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.getContent().isEmpty());
    }

@Test
    public void testVersioningWithCreator_9_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isExecutable());
    }

@Test
    public void testVersioningWithCreator_10_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(file.isHidden());
    }

@Test
    public void testVersioningWithCreator_11_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isReadable());
    }

@Test
    public void testVersioningWithCreator_12_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(file.isWriteable());
    }

@Test
    public void testVersioningWithCreator_13_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        assertTrue(map.containsKey(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }

@Test
    public void testVersioningWithCreator_14_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        assertEquals("testUser", map.get(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }

@Test
    public void testVersioningWithCreator_15_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            assertTrue(map.containsKey(DeltaVConstants.COMMENT.toString()));
    }
    }

@Test
    public void testVersioningWithCreator_16_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            assertEquals("Modified by user " + name, map.get(DeltaVConstants.COMMENT.toString()));
    }
    }

@Test
    public void testVersioningWithCreator_17_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        assertTrue(map.containsKey(VersionControlledResource.CHECKED_IN.toString()));
    }

@Test
    public void testVersioningWithCreator_18_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        assertSameContent(content, file);
    }

@Test
    public void testVersioningWithCreator_19_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        assertTrue(map.containsKey(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }

@Test
    public void testVersioningWithCreator_20_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        // removed other assertion
        assertEquals("testUser", map.get(DeltaVConstants.CREATOR_DISPLAYNAME.toString()));
    }

@Test
    public void testVersioningWithCreator_21_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            assertTrue(map.containsKey(DeltaVConstants.COMMENT.toString()));
    }
    }

@Test
    public void testVersioningWithCreator_22_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            assertEquals("Modified by user " + name, map.get(DeltaVConstants.COMMENT.toString()));
    }
    }

@Test
    public void testVersioningWithCreator_23_oe() throws Exception {
        final FileObject scratchFolder = createScratchFolder();
        final FileSystemOptions opts = scratchFolder.getFileSystem().getFileSystemOptions();
        final WebdavFileSystemConfigBuilder builder = (WebdavFileSystemConfigBuilder) getManager()
                .getFileSystemConfigBuilder("webdav");
        builder.setVersioning(opts, true);
        builder.setCreatorName(opts, "testUser");
        final FileObject file = getManager().resolveFile(scratchFolder, "file1.txt", opts);
        final FileSystemOptions newOpts = file.getFileSystem().getFileSystemOptions();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        file.createFile();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        Map<?, ?> map = file.getContent().getAttributes();
        final String name = ((URLFileName) file.getName()).getUserName();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        // Create the source file
        final String content = "Here is some sample content for the file.  Blah Blah Blah.";

        try (OutputStream os = file.getContent().getOutputStream()) {
            os.write(content.getBytes(StandardCharsets.UTF_8));
        }
        // removed other assertion
        map = file.getContent().getAttributes();
        // removed other assertion
        // removed other assertion
        if (name != null) {
            // removed other assertion
            // removed other assertion
        }
        assertTrue(map.containsKey(VersionControlledResource.CHECKED_IN.toString()));
    }

}
