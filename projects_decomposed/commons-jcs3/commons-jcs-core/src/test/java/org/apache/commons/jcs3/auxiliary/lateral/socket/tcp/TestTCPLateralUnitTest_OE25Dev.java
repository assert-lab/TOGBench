package org.apache.commons.jcs3.auxiliary.lateral.socket.tcp;

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
import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.auxiliary.lateral.LateralCacheAttributes;
import org.apache.commons.jcs3.auxiliary.lateral.LateralCommand;
import org.apache.commons.jcs3.auxiliary.lateral.LateralElementDescriptor;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.ICompositeCacheManager;
import org.apache.commons.jcs3.engine.control.CompositeCache;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;
import org.apache.commons.jcs3.engine.control.MockCompositeCacheManager;
import org.apache.commons.jcs3.engine.control.group.GroupAttrName;
import org.apache.commons.jcs3.engine.control.group.GroupId;
import org.apache.commons.jcs3.utils.serialization.StandardSerializer;
import org.apache.commons.jcs3.utils.timing.SleepUtil;

import junit.framework.TestCase;

/**
 * Basic unit tests for the sending and receiving portions of the lateral cache.
 * <p>
 * @author Aaron Smuts
 */
public class TestTCPLateralUnitTest_OE25Dev
    extends TestCase
{
    private final MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();

    /**
     * Test setup
     */
    @Override
    public void setUp()
    {
        JCS.setConfigFilename( "/TestTCPLateralCache.ccf" );
    }

    private <K,V> CompositeCache<K, V> createCache(int port)
    {
        final TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort(port);
        lattr.setTransmissionType(LateralCacheAttributes.Type.TCP);

        final CompositeCache<K, V> cache = cacheMgr.getCache( "test" );

        // get the listener started
        // give it our mock cache manager
        //LateralTCPListener listener = (LateralTCPListener)
        LateralTCPListener.getInstance( lattr, cacheMgr );

        return cache;
    }

    private <K, V> LateralTCPService<K, V> createService(int listenerPort, int serverPort, long listenerId) throws IOException
    {
        final TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort(listenerPort);
        lattr2.setTransmissionType(LateralCacheAttributes.Type.TCP);
        lattr2.setTcpServer("localhost:" + serverPort);

        final LateralTCPService<K, V> service = new LateralTCPService<>(lattr2,  new StandardSerializer());
        service.setListenerId(listenerId);

        return service;
    }

    /**
     * Make sure we can send a bunch to the listener. This would be better if we could plugin a Mock
     * CacheManger. The listener will instantiate it on its own. We have to configure one before
     * that.
     * <p>
     * @throws Exception
     */

    /**
     * @throws Exception
     */

    /**
     * Send objects with the same key but different values.
     * <p>
     * @throws Exception
     */

    /**
     * Send objects with the same key but different values.
     * <p>
     * @throws Exception
     */

    /**
     * Create a listener. Add an element to the listeners cache. Setup a service. Try to get from
     * the service.
     * <p>
     * @throws Exception
     */

    /**
     * Create a listener. Add an element to the listeners cache. Setup a service. Try to get keys from
     * the service.
     * <p>
     * @throws Exception
     */

    /**
     * Create a listener. Add an element to the listeners cache. Setup a service. Try to get from
     * the service.
     * <p>
     * @throws Exception
     */


}
