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
    public void testVersioning_16_oe_1_oe() throws Exception {
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
                final FileInfo expected = content;
        final FileObject folder = file;
        for (final FileInfo fileInfo : expected.children.values()) {
                    final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);
        
                    assertTrue(child.getName().toString(), child.exists());
    }
    }

    @Test
    public void testVersioning_16_oe_2_oe() throws Exception {
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
                final FileInfo expected = content;
        final FileObject folder = file;
        for (final FileInfo fileInfo : expected.children.values()) {
                    final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);
        
                    // removed other assertion
                    if (fileInfo.type == FileType.FILE) {
                        assertSameContent(fileInfo.content, child);
    }
    }
    }

    @Test
    public void testVersioning_16_oe_3_oe() throws Exception {
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
                final FileInfo expected = content;
        final FileObject folder = file;
        for (final FileInfo fileInfo : expected.children.values()) {
                    final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);
        
                    // removed other assertion
                    if (fileInfo.type == FileType.FILE) {
                        // removed other assertion
                    } else {
                        assertSameContent(fileInfo, child);
    }
    }
    }

    @Test
    public void testVersioningWithCreator_18_oe_1_oe() throws Exception {
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
                final FileInfo expected = content;
        final FileObject folder = file;
        for (final FileInfo fileInfo : expected.children.values()) {
                    final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);
        
                    assertTrue(child.getName().toString(), child.exists());
    }
    }

    @Test
    public void testVersioningWithCreator_18_oe_2_oe() throws Exception {
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
                final FileInfo expected = content;
        final FileObject folder = file;
        for (final FileInfo fileInfo : expected.children.values()) {
                    final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);
        
                    // removed other assertion
                    if (fileInfo.type == FileType.FILE) {
                        assertSameContent(fileInfo.content, child);
    }
    }
    }

    @Test
    public void testVersioningWithCreator_18_oe_3_oe() throws Exception {
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
                final FileInfo expected = content;
        final FileObject folder = file;
        for (final FileInfo fileInfo : expected.children.values()) {
                    final FileObject child = folder.resolveFile(fileInfo.baseName, NameScope.CHILD);
        
                    // removed other assertion
                    if (fileInfo.type == FileType.FILE) {
                        // removed other assertion
                    } else {
                        assertSameContent(fileInfo, child);
    }
    }
    }

}
