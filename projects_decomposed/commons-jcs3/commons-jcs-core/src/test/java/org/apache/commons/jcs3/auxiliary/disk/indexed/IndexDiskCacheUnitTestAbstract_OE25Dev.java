package org.apache.commons.jcs3.auxiliary.disk.indexed;

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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.auxiliary.MockCacheEventLogger;
import org.apache.commons.jcs3.auxiliary.disk.DiskTestObject;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.ElementAttributes;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.IElementAttributes;
import org.apache.commons.jcs3.engine.control.group.GroupAttrName;
import org.apache.commons.jcs3.engine.control.group.GroupId;
import org.apache.commons.jcs3.utils.timing.SleepUtil;

import junit.framework.TestCase;

/**
 * Tests for common functionality.
 * <p>
 *
 * @author Aaron Smuts
 */
public abstract class IndexDiskCacheUnitTestAbstract_OE25Dev extends TestCase
{
    public abstract IndexedDiskCacheAttributes getCacheAttributes();

    /**
     * Simply verify that we can put items in the disk cache and retrieve them.
     *
     * @throws IOException
     */

    /**
     * Add some items to the disk cache and then remove them one by one.
     *
     * @throws IOException
     */

    /**
     * Verify that we don't override the largest item.
     * <p>
     *
     * @throws IOException
     */

    /**
     * Verify that the overlap check returns true when there are no overlaps.
     */

    /**
     * Verify that the overlap check returns false when there are overlaps.
     */

    /**
     * Verify that the file size is as expected.
     * <p>
     *
     * @throws IOException
     * @throws InterruptedException
     */

    /**
     * Verify that items are added to the recycle bin on removal.
     * <p>
     *
     * @throws IOException
     * @throws InterruptedException
     */

    /**
     * Verify that items of the same size use recycle bin spots. Setup the recycle bin by removing
     * some items. Add some of the same size. Verify that the recycle count is the number added.
     * <p>
     *
     * @throws IOException
     * @throws InterruptedException
     */

    /**
     * Verify that the data size is as expected after a remove and after a put that should use the
     * spots.
     * <p>
     *
     * @throws IOException
     * @throws InterruptedException
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

    /**
     * Verify event log calls.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Test the basic get matching.
     * <p>
     *
     * @throws Exception
     */

    /**
     * Test the basic get matching. With no wait this will all come from purgatory.
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
     * Verify the item makes it to disk.
     * <p>
     *
     * @throws IOException
     */

    /**
     * Verify the item makes it to disk.
     * <p>
     *
     * @throws IOException
     */

    /**
     * Verify the item makes it to disk.
     * <p>
     *
     * @throws IOException
     */

    /**
     * Verify that the old slot gets in the recycle bin.
     * <p>
     *
     * @throws IOException
     */

    public void testLoadFromDisk() throws Exception
    {
        for (int i = 0; i < 15; i++)
        { // usually after 2 time it fails
            oneLoadFromDisk();
        }
    }

    public void oneLoadFromDisk() throws Exception
    {
        // initialize object to be stored
        String string = "IÒtÎrn‚tiÙn‡lizÊti¯n";
        final StringBuilder sb = new StringBuilder();
        sb.append(string);
        for (int i = 0; i < 4; i++)
        {
            sb.append(sb.toString()); // big string
        }
        string = sb.toString();

        // initialize cache
        final String cacheName = "testLoadFromDisk";
        final IndexedDiskCacheAttributes cattr = getCacheAttributes();
        cattr.setCacheName(cacheName);
        cattr.setMaxKeySize(100);
        cattr.setDiskPath("target/test-sandbox/IndexDiskCacheUnitTest");
        IndexedDiskCache<String, String> diskCache = new IndexedDiskCache<>(cattr);

        // DO WORK
        for (int i = 0; i < 50; i++)
        {
            diskCache.update(new CacheElement<>(cacheName, "x" + i, string));
        }
        // Thread.sleep(1000);
        // VERIFY
        diskCache.dispose();
        // Thread.sleep(1000);

        diskCache = new IndexedDiskCache<>(cattr);

        for (int i = 0; i < 50; i++)
        {
            final ICacheElement<String, String> afterElement = diskCache.get("x" + i);
            assertNotNull("Missing element from cache. Cache size: " + diskCache.getSize() + " element: x" + i, afterElement);
            assertEquals("wrong string after retrieval", string, afterElement.getVal());
        }
    }


}
