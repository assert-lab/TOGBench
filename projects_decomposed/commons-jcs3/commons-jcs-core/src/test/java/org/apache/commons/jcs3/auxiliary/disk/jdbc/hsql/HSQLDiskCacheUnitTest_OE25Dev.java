package org.apache.commons.jcs3.auxiliary.disk.jdbc.hsql;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.access.CacheAccess;
import org.apache.commons.jcs3.access.exception.CacheException;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;

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

/**
 * Test which exercises the HSQL cache.
 */
public class HSQLDiskCacheUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Test setup
     */
    @Override
    public void setUp()
    {
        JCS.setConfigFilename( "/TestHSQLDiskCache.ccf" );
    }

    /**
     * Adds items to cache, gets them, and removes them. The item count is more than the size of the
     * memory cache, so items should spool to disk.
     * <p>
     * @throws Exception If an error occurs
     */

    /**
     * Verify that remove all work son a region where it is not prohibited.
     * <p>
     * @throws CacheException
     * @throws InterruptedException
     */

    /**
     * Verify that remove all does not work on a region where it is prohibited.
     * <p>
     * @throws CacheException
     * @throws InterruptedException
     */


}
