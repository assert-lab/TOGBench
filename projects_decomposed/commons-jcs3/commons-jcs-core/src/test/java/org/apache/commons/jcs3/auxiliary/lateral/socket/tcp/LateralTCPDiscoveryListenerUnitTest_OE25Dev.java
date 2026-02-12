package org.apache.commons.jcs3.auxiliary.lateral.socket.tcp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jcs3.auxiliary.lateral.LateralCache;
import org.apache.commons.jcs3.auxiliary.lateral.LateralCacheNoWait;
import org.apache.commons.jcs3.auxiliary.lateral.LateralCacheNoWaitFacade;
import org.apache.commons.jcs3.auxiliary.lateral.socket.tcp.behavior.ITCPLateralCacheAttributes;
import org.apache.commons.jcs3.engine.ZombieCacheServiceNonLocal;
import org.apache.commons.jcs3.engine.behavior.IElementSerializer;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;
import org.apache.commons.jcs3.engine.logging.MockCacheEventLogger;
import org.apache.commons.jcs3.utils.discovery.DiscoveredService;
import org.apache.commons.jcs3.utils.serialization.StandardSerializer;

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

/** Test for the listener that observers UDP discovery events. */
public class LateralTCPDiscoveryListenerUnitTest_OE25Dev
    extends TestCase
{
    /** the listener */
    private LateralTCPDiscoveryListener listener;

    /** the cache factory */
    private LateralTCPCacheFactory factory;

    /** The cache manager. */
    private CompositeCacheManager cacheMgr;

    /** The event logger. */
    protected MockCacheEventLogger cacheEventLogger;

    /** The serializer. */
    protected IElementSerializer elementSerializer;

    /** Create the listener for testing */
    @Override
    protected void setUp() throws Exception
    {
        factory = new LateralTCPCacheFactory();
        factory.initialize();

        cacheMgr = CompositeCacheManager.getInstance();
        cacheEventLogger = new MockCacheEventLogger();
        elementSerializer = new StandardSerializer();

        listener = new LateralTCPDiscoveryListener( factory.getName(), cacheMgr,
                cacheEventLogger, elementSerializer );
    }

    private LateralCacheNoWaitFacade<String, String> setupFacade(final String cacheName)
    {
        List<LateralCacheNoWait<String, String>> noWaits = new ArrayList<>();
        final ITCPLateralCacheAttributes cattr = new TCPLateralCacheAttributes();
        cattr.setCacheName( cacheName );

        return new LateralCacheNoWaitFacade<>( null, noWaits, cattr );
    }

    private LateralCacheNoWait<String, String> setupNoWait(final String cacheName)
    {
        final ITCPLateralCacheAttributes cattr = new TCPLateralCacheAttributes();
        cattr.setCacheName( cacheName );

        final LateralCache<String, String> cache = new LateralCache<>(cattr, new ZombieCacheServiceNonLocal<>(), null);
        return new LateralCacheNoWait<>( cache );
    }

    /**
     * Add a no wait facade.
     */

    /**
     * Add a no wait to a known facade.
     */

    /**
     * Add a no wait from an unknown facade.
     */

    /**
     * Remove a no wait from an unknown facade.
     */

    /**
     * Remove a no wait from a known facade.
     */

    /**
     * Remove a no wait from a known facade.
     */

    /**
     * Add a no wait to a known facade.
     */

    /**
     * Remove a no wait from a known facade.
     */


}
