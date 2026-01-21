package org.apache.commons.jcs3.auxiliary.remote.http.client;

import org.apache.commons.jcs3.engine.control.MockCompositeCacheManager;
import org.apache.commons.jcs3.auxiliary.AuxiliaryCache;
import org.apache.commons.jcs3.auxiliary.remote.http.client.behavior.IRemoteHttpCacheClient;
import org.apache.commons.jcs3.engine.behavior.ICompositeCacheManager;
import org.apache.commons.jcs3.engine.behavior.IElementSerializer;
import org.apache.commons.jcs3.engine.logging.behavior.ICacheEventLogger;

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

/** Unit tests for the manager. */
public class RemoteHttpCacheFactoryUnitTest_OE25Dev
    extends TestCase
{
    /** Verify that we get the default. */

    /** Verify that we get the default. */

    /** Verify that we get a cache no wait. */

    public void testCreateRemoteHttpCacheClient_Bad_1_oe()
    {
        // SETUP
        final String remoteHttpClientClassName = "junk";
        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        cattr.setRemoteHttpClientClassName( remoteHttpClientClassName );

        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();

        // DO WORK
        final IRemoteHttpCacheClient<String, String> result = factory.createRemoteHttpCacheClientForAttributes( cattr );

        // VEIFY
        assertNotNull( "Should have a cache.", result );
    }

    public void testCreateRemoteHttpCacheClient_Bad_2_oe()
    {
        // SETUP
        final String remoteHttpClientClassName = "junk";
        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        cattr.setRemoteHttpClientClassName( remoteHttpClientClassName );

        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();

        // DO WORK
        final IRemoteHttpCacheClient<String, String> result = factory.createRemoteHttpCacheClientForAttributes( cattr );

        // VEIFY
        // removed other assertion
        assertTrue( "Wrong default.", result instanceof RemoteHttpCacheClient );
    }

    public void testCreateRemoteHttpCacheClient_Bad_3_oe()
    {
        // SETUP
        final String remoteHttpClientClassName = "junk";
        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        cattr.setRemoteHttpClientClassName( remoteHttpClientClassName );

        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();

        // DO WORK
        final IRemoteHttpCacheClient<String, String> result = factory.createRemoteHttpCacheClientForAttributes( cattr );

        // VEIFY
        // removed other assertion
        // removed other assertion
        assertTrue( "Should be initialized", ((RemoteHttpCacheClient<String, String>)result).isInitialized() );
    }

    public void testCreateRemoteHttpCacheClient_default_1_oe()
    {
        // SETUP
        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();

        // DO WORK
        final IRemoteHttpCacheClient<String, String> result = factory.createRemoteHttpCacheClientForAttributes( cattr );

        // VEIFY
        assertNotNull( "Should have a cache.", result );
    }

    public void testCreateRemoteHttpCacheClient_default_2_oe()
    {
        // SETUP
        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();

        // DO WORK
        final IRemoteHttpCacheClient<String, String> result = factory.createRemoteHttpCacheClientForAttributes( cattr );

        // VEIFY
        // removed other assertion
        assertTrue( "Wrong default.", result instanceof RemoteHttpCacheClient );
    }

    public void testGetCache_normal_1_oe()
    {
        // SETUP
        final ICompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        assertNotNull( "Should have a manager.", cacheMgr );
    }

    public void testGetCache_normal_2_oe()
    {
        // SETUP
        final ICompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        // removed other assertion
        final ICacheEventLogger cacheEventLogger = null;
        final IElementSerializer elementSerializer = null;

        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        assertNotNull( "Should have attributes.", cattr );
    }

    public void testGetCache_normal_3_oe()
    {
        // SETUP
        final ICompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        // removed other assertion
        final ICacheEventLogger cacheEventLogger = null;
        final IElementSerializer elementSerializer = null;

        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        // removed other assertion
        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();
        assertNotNull( "Should have a factory.", factory );
    }

    public void testGetCache_normal_4_oe()
    {
        // SETUP
        final ICompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        // removed other assertion
        final ICacheEventLogger cacheEventLogger = null;
        final IElementSerializer elementSerializer = null;

        final RemoteHttpCacheAttributes cattr = new RemoteHttpCacheAttributes();
        // removed other assertion
        final RemoteHttpCacheFactory factory = new RemoteHttpCacheFactory();
        // removed other assertion


        // DO WORK
        final AuxiliaryCache<String, String> result = factory.createCache(cattr, cacheMgr, cacheEventLogger, elementSerializer);

        // VERIFY
        assertNotNull( "Should have a cache.", result );
    }

}
