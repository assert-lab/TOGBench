package org.apache.commons.jcs3.auxiliary.disk.block;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.IElementAttributes;
import org.apache.commons.jcs3.engine.control.group.GroupAttrName;
import org.apache.commons.jcs3.engine.control.group.GroupId;
import org.apache.commons.jcs3.utils.serialization.StandardSerializer;

import junit.framework.TestCase;

/** Unit tests for the Block Disk Cache */
public abstract class BlockDiskCacheUnitTestAbstract_OE25Dev extends TestCase
{
    public abstract BlockDiskCacheAttributes getCacheAttributes();

    /**
     * Test the basic get matching. With no wait this will all come from purgatory.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify that the block disk cache can handle a big string.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify that the block disk cache can handle a big string.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify that the block disk cache can handle utf encoded strings.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify that the block disk cache can handle utf encoded strings.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify that the block disk cache can handle utf encoded strings.
     * <p>
     *
     * @throws Exception
     */

    public void testLoadFromDisk() throws Exception
    {
        for (int i = 0; i < 20; i++)
        { // usually after 2 time it fails
            oneLoadFromDisk();
        }
    }

    public void oneLoadFromDisk() throws Exception
    {
        // initialize object to be stored
        final X before = new X();
        String string = "IÒtÎrn‚tiÙn‡lizÊti¯n";
        final StringBuilder sb = new StringBuilder();
        sb.append(string);
        for (int i = 0; i < 4; i++)
        {
            sb.append(sb.toString()); // big string
        }
        string = sb.toString();
        before.string = string;
        before.bytes = string.getBytes(StandardCharsets.UTF_8);

        // initialize cache
        final String cacheName = "testLoadFromDisk";
        final BlockDiskCacheAttributes cattr = getCacheAttributes();
        cattr.setCacheName(cacheName);
        cattr.setMaxKeySize(100);
        cattr.setBlockSizeBytes(500);
        cattr.setDiskPath("target/test-sandbox/BlockDiskCacheUnitTest");
        BlockDiskCache<String, X> diskCache = new BlockDiskCache<>(cattr);

        // DO WORK
        for (int i = 0; i < 50; i++)
        {
            diskCache.update(new CacheElement<>(cacheName, "x" + i, before));
        }
        diskCache.dispose();

        // VERIFY
        diskCache = new BlockDiskCache<>(cattr);

        for (int i = 0; i < 50; i++)
        {
            final ICacheElement<String, X> afterElement = diskCache.get("x" + i);
            assertNotNull("Missing element from cache. Cache size: " + diskCache.getSize() + " element: x" + i, afterElement);
            final X after = (afterElement.getVal());

            assertNotNull(after);
            assertEquals("wrong string after retrieval", string, after.string);
            assertEquals("wrong bytes after retrieval", string, new String(after.bytes, StandardCharsets.UTF_8));
        }

        diskCache.dispose();
    }

    /**
     * Add some items to the disk cache and then remove them one by one.
     *
     * @throws IOException
     */

    /**
     * Add some items to the disk cache and then remove them one by one.
     * <p>
     *
     * @throws IOException
     */


    /**
     * Verify that group members are removed if we call remove with a group.
     *
     * @throws IOException
     */

    /**
     * Internal method used for group functionality.
     * <p>
     *
     * @param cacheName
     * @param group
     * @param name
     * @return GroupAttrName
     */
    private GroupAttrName<String> getGroupAttrName(final String cacheName, final String group, final String name)
    {
        final GroupId gid = new GroupId(cacheName, group);
        return new GroupAttrName<>(gid, name);
    }

    /** Holder for a string and byte array. */
    static class X implements Serializable
    {
        /** ignore */
        private static final long serialVersionUID = 1L;

        /** Test string */
        String string;

        /*** test byte array. */
        byte[] bytes;
    }


}
