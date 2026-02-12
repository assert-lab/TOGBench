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

import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.impl.VirtualFileSystem;
import org.apache.commons.vfs2.provider.ram.RamFileObject;
import org.apache.commons.vfs2.util.FileObjectUtils;
import org.junit.Test;

/**
 * Test the cache stragey
 */
public class ProviderCacheStrategyTests_OE25Dev extends AbstractProviderTestCase {
    public void assertContains(final FileObject[] fos, final String string) {
        for (final FileObject fo : fos) {
            if (string.equals(fo.getName().getBaseName())) {
                return;
            }
        }

        fail(string + " should be seen");
    }

    public void assertContainsNot(final FileObject[] fos, final String string) {
        for (final FileObject fo : fos) {
            if (string.equals(fo.getName().getBaseName())) {
                fail(string + " should not be seen");
            }
        }
    }

    /**
     * Returns the capabilities required by the tests of this test case.
     */
    @Override
    protected Capability[] getRequiredCapabilities() {
        return new Capability[] { Capability.CREATE, Capability.GET_TYPE, Capability.LIST_CHILDREN, };
    }

    /**
     * Test the manual cache strategy
     */

    /**
     * Test the on_call strategy
     */

    /**
     * Test the on_resolve strategy
     */

    @Test
    public void testManualCache_1_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.MANUAL);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        final FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        fail(string0 + " should not be seen");
    }
    }
    }

    @Test
    public void testManualCache_2_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.MANUAL);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        final FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
        // removed other assertion

        scratchFolder.resolveFile("file1.txt").createFile();

        fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        fail(string0 + " should not be seen");
    }
    }
    }

    @Test
    public void testManualCache_3_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.MANUAL);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        final FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
        // removed other assertion

        scratchFolder.resolveFile("file1.txt").createFile();

        fos = cachedFolder.getChildren();
        // removed other assertion

        cachedFolder.refresh();
        fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        return;
                    }
                }
        
                fail(string0 + " should be seen");
    }

    @Test
    public void testOnCallCache_1_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.ON_CALL);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        final FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        fail(string0 + " should not be seen");
    }
    }
    }

    @Test
    public void testOnCallCache_2_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.ON_CALL);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        final FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
        // removed other assertion

        scratchFolder.resolveFile("file1.txt").createFile();

        fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        return;
                    }
                }
        
                fail(string0 + " should be seen");
    }

    @Test
    public void testOnResolveCache_1_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.ON_RESOLVE);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        fail(string0 + " should not be seen");
    }
    }
    }

    @Test
    public void testOnResolveCache_2_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.ON_RESOLVE);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
        // removed other assertion

        scratchFolder.resolveFile("file1.txt").createFile();

        fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        fail(string0 + " should not be seen");
    }
    }
    }

    @Test
    public void testOnResolveCache_3_oe_1_oe() throws Exception {
        final FileObject scratchFolder = getWriteFolder();
        if (FileObjectUtils.isInstanceOf(getBaseFolder(), RamFileObject.class)
                || scratchFolder.getFileSystem() instanceof VirtualFileSystem) {
            // cant check ram filesystem as every manager holds its own ram filesystem data
            return;
        }

        scratchFolder.delete(Selectors.EXCLUDE_SELF);

        final DefaultFileSystemManager fs = createManager();
        fs.setCacheStrategy(CacheStrategy.ON_RESOLVE);
        fs.init();
        final FileObject foBase2 = getBaseTestFolder(fs);

        FileObject cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());

        FileObject[] fos = cachedFolder.getChildren();
        // removed other assertion

        scratchFolder.resolveFile("file1.txt").createFile();

        fos = cachedFolder.getChildren();
        // removed other assertion

        cachedFolder = foBase2.resolveFile(scratchFolder.getName().getPath());
        fos = cachedFolder.getChildren();
                final FileObject[] fos0 = fos;
        final String string0 = "file1.txt";
        for (final FileObject fo0 : fos0) {
                    if (string0.equals(fo0.getName().getBaseName())) {
                        return;
                    }
                }
        
                fail(string0 + " should be seen");
    }

}
