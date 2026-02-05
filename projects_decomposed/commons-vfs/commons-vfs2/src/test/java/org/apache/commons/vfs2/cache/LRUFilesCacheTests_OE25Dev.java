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
package org.apache.commons.vfs2.cache;

import java.util.Objects;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FilesCache;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link LRUFilesCache} used by {@link LRUFilesCacheTestCase}.
 */
public class LRUFilesCacheTests_OE25Dev extends AbstractFilesCacheTestsBase {

@Test
    public void testClass_1_oe() {
        @SuppressWarnings("resource")
        final DefaultFileSystemManager manager = getManager();
        Assert.assertNotNull("manager", manager);
    }

@Test
    public void testClass_2_oe() {
        @SuppressWarnings("resource")
        final DefaultFileSystemManager manager = getManager();
        // removed other assertion
        final FilesCache filesCache = manager.getFilesCache();
        assertTrue(Objects.toString(filesCache), filesCache instanceof LRUFilesCache);
    }

@Test
    public void testFilesCache_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        Assert.assertNotNull("scratchFolder", scratchFolder);
    }

@Test
    public void testFilesCache_2_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        // removed other assertion

        // releaseable
        final FileObject dir1 = scratchFolder.resolveFile("dir1");

        // avoid cache removal
        final FileObject dir2 = scratchFolder.resolveFile("dir2");
        dir2.getContent();

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir3 = scratchFolder.resolveFile("dir3");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir4 = scratchFolder.resolveFile("dir4");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir5 = scratchFolder.resolveFile("dir5");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir6 = scratchFolder.resolveFile("dir6");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir7 = scratchFolder.resolveFile("dir7");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir8 = scratchFolder.resolveFile("dir8");

        // check if the cache still holds the right instance
        final FileObject dir2_2 = scratchFolder.resolveFile("dir2");
        assertSame(dir2, dir2_2);
    }

@Test
    public void testFilesCache_3_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        // removed other assertion

        // releaseable
        final FileObject dir1 = scratchFolder.resolveFile("dir1");

        // avoid cache removal
        final FileObject dir2 = scratchFolder.resolveFile("dir2");
        dir2.getContent();

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir3 = scratchFolder.resolveFile("dir3");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir4 = scratchFolder.resolveFile("dir4");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir5 = scratchFolder.resolveFile("dir5");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir6 = scratchFolder.resolveFile("dir6");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir7 = scratchFolder.resolveFile("dir7");

        // releaseable
        @SuppressWarnings("unused")
        final FileObject dir8 = scratchFolder.resolveFile("dir8");

        // check if the cache still holds the right instance
        final FileObject dir2_2 = scratchFolder.resolveFile("dir2");
        // removed other assertion

        // check if the cache still holds the right instance
        final FileObject dir1_2 = scratchFolder.resolveFile("dir1");
        assertNotSame(dir1, dir1_2);
    }

}
