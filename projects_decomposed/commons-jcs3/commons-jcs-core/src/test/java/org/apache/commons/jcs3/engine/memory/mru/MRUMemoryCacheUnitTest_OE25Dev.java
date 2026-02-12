package org.apache.commons.jcs3.engine.memory.mru;

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

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.access.CacheAccess;
import org.apache.commons.jcs3.access.exception.CacheException;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.control.CompositeCache;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;

/**
 * Tests for the test MRU implementation.
 * <p>
 * @author Aaron Smuts
 */
public class MRUMemoryCacheUnitTest_OE25Dev
    extends TestCase
{
    /** Test setup */
    @Override
    public void setUp()
    {
        JCS.setConfigFilename( "/TestMRUCache.ccf" );
    }

    /**
     * Verify that the mru gets used by a non-defined region when it is set as the default in the
     * default region.
     * <p>
     * @throws CacheException
     */

    /**
     * put twice as many as the max.  verify that the second half is in the cache.
     * <p>
     * @throws CacheException
     */

    /**
     * Put twice as many as the max, twice. verify that the second half is in the cache.
     * <p>
     * @throws CacheException
     */

    /**
     * put the max and remove each. verify that they are all null.
     * <p>
     * @throws CacheException
     */

    /**
     * put the max and clear. verify that no elements remain.
     * <p>
     * @throws CacheException
     */

    /**
     * Get stats.
     * <p>
     * @throws CacheException
     */

    /**
     * Put half the max and clear. get the key array and verify that it has the correct number of
     * items.
     * <p>
     * @throws Exception
     */

    /**
     * Add a few keys with the delimiter. Remove them.
     * <p>
     * @throws CacheException
     */


}
