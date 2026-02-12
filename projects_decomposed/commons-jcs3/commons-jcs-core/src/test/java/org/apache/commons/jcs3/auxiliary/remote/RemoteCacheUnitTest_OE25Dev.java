package org.apache.commons.jcs3.auxiliary.remote;

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

import java.util.HashSet;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.jcs3.auxiliary.MockCacheEventLogger;
import org.apache.commons.jcs3.auxiliary.remote.behavior.IRemoteCacheAttributes;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.ZombieCacheServiceNonLocal;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElementSerialized;
import org.apache.commons.jcs3.utils.serialization.SerializationConversionUtil;

/**
 * Unit Tests for the Remote Cache.
 */
public class RemoteCacheUnitTest_OE25Dev
    extends TestCase
{
    private IRemoteCacheAttributes cattr;
    private MockRemoteCacheService<String, String> service;
    private MockRemoteCacheListener<String, String> listener;
    private RemoteCacheMonitor monitor;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        cattr = new RemoteCacheAttributes();
        service = new MockRemoteCacheService<>();
        listener = new MockRemoteCacheListener<>();
        monitor = new RemoteCacheMonitor();
    }

    /**
     * Verify that the remote service update method is called. The remote cache serializes the object
     * first.
     * <p>
     * @throws Exception
     */

    /**
     * Verify that when we call fix events queued in the zombie are propagated to the new service.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify event log calls.
     * <p>
     * @throws Exception
     */

    /**
     * Verify that there is no problem if there is no listener.
     * <p>
     * @throws Exception
     */


}
