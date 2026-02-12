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
package org.apache.commons.vfs2.provider.ram;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.UriParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Custom tests for RamProvider.
 */
public class CustomRamProviderTest_OE25Dev {
    private static final byte[] NON_EMPTY_FILE_CONTENT = new byte[] { 1, 2, 3 };

    /** List of URL special characters encoded for AbstractFileObject#getChild */
    final char[] ENC = { /*'#',*/ '!', '?'};

    private final List<Closeable> closeables = new ArrayList<>();

    FileSystemOptions defaultRamFso = new FileSystemOptions();

    DefaultFileSystemManager manager;

    FileSystemOptions smallSizedFso = new FileSystemOptions();

    FileSystemOptions zeroSizedFso = new FileSystemOptions();

    /**
     * Closes the given {@link Closeable} during the tearDown phase.
     */
    private <C extends Closeable> C closeOnTearDown(final C closeable) {
        this.closeables.add(closeable);
        return closeable;
    }

    private InputStream createEmptyFile() throws FileSystemException, IOException {
        final FileObject root = manager.resolveFile("ram://file");
        root.createFile();
        return this.closeOnTearDown(root.getContent().getInputStream());
    }

    private InputStream createNonEmptyFile() throws FileSystemException, IOException {
        final FileObject root = manager.resolveFile("ram://file");
        root.createFile();

        final FileContent content = root.getContent();
        final OutputStream output = this.closeOnTearDown(content.getOutputStream());
        output.write(1);
        output.write(2);
        output.write(3);
        output.flush();
        output.close();

        return this.closeOnTearDown(content.getInputStream());
    }

    /** Create directory structure for {@link #testSpecialName()} and {@link #testSchemePrefix()} */
    private FileObject prepareSpecialFile(final String dirname, final String testFileName) throws FileSystemException
    {
        // set up a folder containing an filename with special characters:
        final FileObject dir = manager.resolveFile("ram:" + dirname);
        dir.createFolder();
        // construct the absolute name to make sure the relative name is not miss-interpreted
        // ("./" + UriParser.encode(testFileName, ENC) would also work)
        final String filePath = dir.getName().getPath() + "/" + UriParser.encode(testFileName, ENC);

        final FileObject specialFile = dir.resolveFile(filePath);
        specialFile.createFile();

        return dir;
    }


    @Before
    public void setUp() throws Exception {
        manager = new DefaultFileSystemManager();
        manager.addProvider("ram", new RamFileProvider());
        manager.init();

        // File Systems Options
        RamFileSystemConfigBuilder.getInstance().setMaxSize(zeroSizedFso, 0L);
        RamFileSystemConfigBuilder.getInstance().setMaxSize(smallSizedFso, 10L);
    }

    @After
    public void tearDown() {
        for (final Closeable closeable : this.closeables) {
            try {
                closeable.close();
            } catch (final Exception e) {
                // ignore
            }
        }
        manager.close();
    }

    /**
     * Tests VFS-625.
     * @throws FileSystemException
     */

    /**
     *
     * Checks root folder exists
     *
     * @throws FileSystemException
     */

    /**
     * Test if listing files with known scheme prefix works.
     * <p>
     * This test is not RamProvider specific but it uses it as a simple test-bed.
     * Verifies VFS-741.
     */


    /**
     * Test some special file name symbols.
     * <p>
     * Use the RamProvider since it has no character limitations like
     * the (Windows) LocalFileProvider.
     */


}
