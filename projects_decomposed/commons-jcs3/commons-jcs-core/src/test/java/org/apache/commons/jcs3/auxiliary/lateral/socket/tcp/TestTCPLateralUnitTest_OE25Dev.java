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

    public void testSimpleSend_1_oe()
        throws Exception
    {
        JCS.getInstance( "test" );

        final TCPLateralCacheAttributes lac = new TCPLateralCacheAttributes();
        lac.setTransmissionType(LateralCacheAttributes.Type.TCP);
        lac.setTcpServer( "localhost:" + 8111 );
        lac.setTcpListenerPort( 8111 );

        final ICompositeCacheManager cacheMgr = CompositeCacheManager.getInstance();

        final LateralTCPListener<String, String> listener = LateralTCPListener.getInstance( lac, cacheMgr );

        final LateralTCPSender lur = new LateralTCPSender(lac,  new StandardSerializer());

        final int numMes = 10;
        for ( int i = 0; i < numMes; i++ )
        {
            final String message = "adsfasasfasfasdasf";
            final CacheElement<String, String> ce = new CacheElement<>( "test", "test", message );
            final LateralElementDescriptor<String, String> led =
                    new LateralElementDescriptor<>(ce, LateralCommand.UPDATE, 1);
            lur.send( led );
        }

        SleepUtil.sleepAtLeast( numMes * 3 );

        assertEquals( "Should have received " + numMes + " by now.", numMes, listener.getPutCnt() );
    }

    public void testReceive_1_oe()
        throws Exception
    {
        createCache(1101);

        final LateralTCPService<String, String> service = createService(1102, 1101, 123456);

        final int cnt = 100;
        for ( int i = 0; i < cnt; i++ )
        {
            final ICacheElement<String, String> element = new CacheElement<>( "test", "key" + i, "value1" );
            service.update( element );
        }

        SleepUtil.sleepAtLeast( 1000 );

        assertEquals( "Didn't get the correct number", cnt, cacheMgr.getCache().getUpdateCount() );
    }

    public void testSameKeyDifferentObject_1_oe()
        throws Exception
    {
        final CompositeCache<String, String> cache = createCache(1103);

        final LateralTCPService<String, String> service = createService(1104, 1103, 123456);

        final ICacheElement<String, String> element = new CacheElement<>( "test", "key", "value1" );
        service.update( element );

        SleepUtil.sleepAtLeast( 300 );

        final ICacheElement<String, String> element2 = new CacheElement<>( "test", "key", "value2" );
        service.update( element2 );

        SleepUtil.sleepAtLeast( 1000 );

        final ICacheElement<String, String> cacheElement = cache.get( "key" );
        assertEquals( "Didn't get the correct object "+ cacheElement, element2.getVal(), cacheElement.getVal() );
    }

    public void testSameKeyObjectDifferentValueObject_1_oe()
        throws Exception
    {
        final CompositeCache<String, String> cache = createCache(1105);

        final LateralTCPService<String, String> service = createService(1106, 1105, 123456);

        final String key = "key";
        final ICacheElement<String, String> element = new CacheElement<>( "test", key, "value1" );
        service.update( element );

        SleepUtil.sleepAtLeast( 300 );

        final ICacheElement<String, String> element2 = new CacheElement<>( "test", key, "value2" );
        service.update( element2 );

        SleepUtil.sleepAtLeast( 1000 );

        final ICacheElement<String, String> cacheElement = cache.get( "key" );
        assertEquals( "Didn't get the correct object: " + cacheElement , element2.getVal(), cacheElement.getVal() );
    }

    public void testGet_SendAndReceived_1_oe()
        throws Exception
    {
        final CompositeCache<String, String> cache = createCache(1107);

        final ICacheElement<String, String> element = new CacheElement<>( "test", "key", "value1" );
        cache.update( element );

        final LateralTCPService<String, String> service = createService(1108, 1107, 123456);

        SleepUtil.sleepAtLeast( 300 );

        final ICacheElement<String, String> result = service.get( "test", "key" );

        assertNotNull( "Result should not be null.", result );
    }

    public void testGet_SendAndReceived_2_oe()
        throws Exception
    {
        final CompositeCache<String, String> cache = createCache(1107);

        final ICacheElement<String, String> element = new CacheElement<>( "test", "key", "value1" );
        cache.update( element );

        final LateralTCPService<String, String> service = createService(1108, 1107, 123456);

        SleepUtil.sleepAtLeast( 300 );

        final ICacheElement<String, String> result = service.get( "test", "key" );

        assertEquals( "Didn't get the correct object", element.getVal(), result.getVal() );
    }

    public void testGetGroupKeys_SendAndReceived_1_oe()  throws Exception
    {
        final CompositeCache<GroupAttrName<String>, String> cache = createCache(1150);

        final GroupAttrName<String> groupKey = new GroupAttrName<>(new GroupId("test", "group"), "key");
        final ICacheElement<GroupAttrName<String>, String> element =
            new CacheElement<>( "test", groupKey, "value1" );
        cache.update( element );

        final LateralTCPService<GroupAttrName<String>, String>service = createService(1151, 1150, 123459);

        SleepUtil.sleepAtLeast( 500 );

        final Set<GroupAttrName<String>> result = service.getKeySet("test");


        assertNotNull( "Result should not be null.", result );
    }

    public void testGetGroupKeys_SendAndReceived_2_oe()  throws Exception
    {
        final CompositeCache<GroupAttrName<String>, String> cache = createCache(1150);

        final GroupAttrName<String> groupKey = new GroupAttrName<>(new GroupId("test", "group"), "key");
        final ICacheElement<GroupAttrName<String>, String> element =
            new CacheElement<>( "test", groupKey, "value1" );
        cache.update( element );

        final LateralTCPService<GroupAttrName<String>, String>service = createService(1151, 1150, 123459);

        SleepUtil.sleepAtLeast( 500 );

        final Set<GroupAttrName<String>> result = service.getKeySet("test");


        assertEquals( "Didn't get the correct object", "key", result.iterator().next().attrName );
    }

    public void testGetMatching_WithData_1_oe()
        throws Exception
    {
        final CompositeCache<String, Integer> cache = createCache(1108);

        final String keyprefix1 = "MyPrefix1";
        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            final ICacheElement<String, Integer> element = new CacheElement<>( "test", keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
            cache.update( element );
        }

        final LateralTCPService<String, Integer> service = createService(1108, 1108, 123456);

        SleepUtil.sleepAtLeast( 300 );

        final Map<String, ICacheElement<String, Integer>> result = service.getMatching( "test", keyprefix1 + ".+" );

        assertNotNull( "Result should not be null.", result );
    }

    public void testGetMatching_WithData_2_oe()
        throws Exception
    {
        final CompositeCache<String, Integer> cache = createCache(1108);

        final String keyprefix1 = "MyPrefix1";
        final int numToInsertPrefix1 = 10;
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            final ICacheElement<String, Integer> element = new CacheElement<>( "test", keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
            cache.update( element );
        }

        final LateralTCPService<String, Integer> service = createService(1108, 1108, 123456);

        SleepUtil.sleepAtLeast( 300 );

        final Map<String, ICacheElement<String, Integer>> result = service.getMatching( "test", keyprefix1 + ".+" );

        assertEquals( "Wrong number returned 1:", numToInsertPrefix1, result.size() );
    }

}
