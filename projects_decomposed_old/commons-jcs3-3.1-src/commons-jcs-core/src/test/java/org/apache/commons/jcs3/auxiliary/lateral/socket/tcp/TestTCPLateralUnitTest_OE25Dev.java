package org.apache.commons.jcs3.auxiliary.lateral.socket.tcp;

import java.util.Map;
import java.util.Set;

import org.apache.commons.jcs3.engine.control.MockCompositeCacheManager;
import org.apache.commons.jcs3.utils.timing.SleepUtil;
import org.apache.commons.jcs3.JCS;
import org.apache.commons.jcs3.auxiliary.lateral.LateralCacheAttributes;
import org.apache.commons.jcs3.auxiliary.lateral.LateralCommand;
import org.apache.commons.jcs3.auxiliary.lateral.LateralElementDescriptor;
import org.apache.commons.jcs3.engine.CacheElement;
import org.apache.commons.jcs3.engine.behavior.ICacheElement;
import org.apache.commons.jcs3.engine.behavior.ICompositeCacheManager;
import org.apache.commons.jcs3.engine.control.CompositeCache;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;
import org.apache.commons.jcs3.engine.control.group.GroupAttrName;
import org.apache.commons.jcs3.engine.control.group.GroupId;

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
 * Basic unit tests for the sending and receiving portions of the lateral cache.
 * <p>
 * @author Aaron Smuts
 */
public class TestTCPLateralUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Test setup
     */
    @Override
    public void setUp()
    {
        JCS.setConfigFilename( "/TestTCPLateralCache.ccf" );
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
        // SETUP
        // force initialization
        JCS.getInstance( "test" );

        TCPLateralCacheAttributes lac = new TCPLateralCacheAttributes();
        lac.setTransmissionType( LateralCacheAttributes.Type.TCP );
        lac.setTcpServer( "localhost" + ":" + 8111 );
        lac.setTcpListenerPort( 8111 );

        ICompositeCacheManager cacheMgr = CompositeCacheManager.getInstance();

        // start the listener
        LateralTCPListener<String, String> listener = LateralTCPListener.getInstance( lac, cacheMgr );

        // send to the listener
        LateralTCPSender lur = new LateralTCPSender( lac );

        // DO WORK
        int numMes = 10;
        for ( int i = 0; i < numMes; i++ )
        {
            String message = "adsfasasfasfasdasf";
            CacheElement<String, String> ce = new CacheElement<>( "test", "test", message );
            LateralElementDescriptor<String, String> led = new LateralElementDescriptor<>( ce );
            led.command = LateralCommand.UPDATE;
            led.requesterId = 1;
            lur.send( led );
        }

        SleepUtil.sleepAtLeast( numMes * 3 );

        // VERIFY
        assertEquals( "Should have received " + numMes + " by now.", numMes, listener.getPutCnt() );
    }

    public void testReceive_1_oe()
        throws Exception
    {
        // VERIFY
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1101 );
        lattr.setTransmissionTypeName( "TCP" );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
//        System.out.println( "mock cache = " + cacheMgr.getCache( "test" ) );

        LateralTCPListener.getInstance( lattr, cacheMgr );

        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1102 );
        lattr2.setTransmissionTypeName( "TCP" );
        lattr2.setTcpServer( "localhost:1101" );

        LateralTCPService<String, String> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        // DO WORK
        int cnt = 100;
        for ( int i = 0; i < cnt; i++ )
        {
            ICacheElement<String, String> element = new CacheElement<>( "test", "key" + i, "value1" );
            service.update( element );
        }

        SleepUtil.sleepAtLeast( 1000 );

        // VERIFY
        assertEquals( "Didn't get the correct number", cnt, cacheMgr.getCache().getUpdateCount() );
    }

    public void testSameKeyDifferentObject_1_oe()
        throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1103 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<String, String> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        //LateralTCPListener listener = (LateralTCPListener)
        LateralTCPListener.getInstance( lattr, cacheMgr );

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1104 );
        lattr2.setTcpServer( "localhost:1103" );

        LateralTCPService<String, String> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        // DO WORK
        ICacheElement<String, String> element = new CacheElement<>( "test", "key", "value1" );
        service.update( element );

        SleepUtil.sleepAtLeast( 300 );

        ICacheElement<String, String> element2 = new CacheElement<>( "test", "key", "value2" );
        service.update( element2 );

        SleepUtil.sleepAtLeast( 1000 );

        // VERIFY
        ICacheElement<String, String> cacheElement = cache.get( "key" );
        assertEquals( "Didn't get the correct object "+ cacheElement, element2.getVal(), cacheElement.getVal() );
    }

    public void testSameKeyObjectDifferentValueObject_1_oe()
        throws Exception
    {
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1105 );
        lattr.setTransmissionTypeName( "TCP" );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<String, String> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        //LateralTCPListener listener = (LateralTCPListener)
        LateralTCPListener.getInstance( lattr, cacheMgr );

        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1106 );
        lattr2.setTransmissionTypeName( "TCP" );
        lattr2.setTcpServer( "localhost:1105" );

        LateralTCPService<String, String> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        // DO WORK
        String key = "key";
        ICacheElement<String, String> element = new CacheElement<>( "test", key, "value1" );
        service.update( element );

        SleepUtil.sleepAtLeast( 300 );

        ICacheElement<String, String> element2 = new CacheElement<>( "test", key, "value2" );
        service.update( element2 );

        SleepUtil.sleepAtLeast( 1000 );

        // VERIFY
        ICacheElement<String, String> cacheElement = cache.get( "key" );
        assertEquals( "Didn't get the correct object: " + cacheElement , element2.getVal(), cacheElement.getVal() );
    }

    public void testGet_SendAndReceived_1_oe()
        throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1107 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<String, String> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        LateralTCPListener.getInstance( lattr, cacheMgr );

        // add the item to the listeners cache
        ICacheElement<String, String> element = new CacheElement<>( "test", "key", "value1" );
        cache.update( element );

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1108 );
        lattr2.setTcpServer( "localhost:1107" );

        LateralTCPService<String, String> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        SleepUtil.sleepAtLeast( 300 );

        // DO WORK
        ICacheElement<String, String> result = service.get( "test", "key" );

        // VERIFY
        assertNotNull( "Result should not be null.", result );
    }

    public void testGet_SendAndReceived_2_oe()
        throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1107 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<String, String> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        LateralTCPListener.getInstance( lattr, cacheMgr );

        // add the item to the listeners cache
        ICacheElement<String, String> element = new CacheElement<>( "test", "key", "value1" );
        cache.update( element );

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1108 );
        lattr2.setTcpServer( "localhost:1107" );

        LateralTCPService<String, String> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        SleepUtil.sleepAtLeast( 300 );

        // DO WORK
        ICacheElement<String, String> result = service.get( "test", "key" );

        // VERIFY
        // removed other assertion
        assertEquals( "Didn't get the correct object", element.getVal(), result.getVal() );
    }

    public void testGetGroupKeys_SendAndReceived_1_oe()  throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1150 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<GroupAttrName<String>, String> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        LateralTCPListener.getInstance( lattr, cacheMgr );

        // add the item to the listeners cache
        GroupAttrName<String> groupKey = new GroupAttrName<>(new GroupId("test", "group"), "key");
        ICacheElement<GroupAttrName<String>, String> element =
            new CacheElement<>( "test", groupKey, "value1" );
        cache.update( element );

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1151 );
        lattr2.setTcpServer( "localhost:1150" );

        LateralTCPService<GroupAttrName<String>, String> service =
            new LateralTCPService<>( lattr2 );
        service.setListenerId( 123459 );

        SleepUtil.sleepAtLeast( 500 );

        // DO WORK
        Set<GroupAttrName<String>> result = service.getKeySet("test");

       // SleepUtil.sleepAtLeast( 5000000 );

        // VERIFY
        assertNotNull( "Result should not be null.", result );
    }

    public void testGetGroupKeys_SendAndReceived_2_oe()  throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1150 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<GroupAttrName<String>, String> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        LateralTCPListener.getInstance( lattr, cacheMgr );

        // add the item to the listeners cache
        GroupAttrName<String> groupKey = new GroupAttrName<>(new GroupId("test", "group"), "key");
        ICacheElement<GroupAttrName<String>, String> element =
            new CacheElement<>( "test", groupKey, "value1" );
        cache.update( element );

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1151 );
        lattr2.setTcpServer( "localhost:1150" );

        LateralTCPService<GroupAttrName<String>, String> service =
            new LateralTCPService<>( lattr2 );
        service.setListenerId( 123459 );

        SleepUtil.sleepAtLeast( 500 );

        // DO WORK
        Set<GroupAttrName<String>> result = service.getKeySet("test");

       // SleepUtil.sleepAtLeast( 5000000 );

        // VERIFY
        // removed other assertion
        assertEquals( "Didn't get the correct object", "key", result.iterator().next().attrName );
    }

    public void testGetMatching_WithData_1_oe()
        throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1108 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<String, Integer> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        LateralTCPListener.getInstance( lattr, cacheMgr );

        String keyprefix1 = "MyPrefix1";
        int numToInsertPrefix1 = 10;
        // insert with prefix1
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            // add the item to the listeners cache
            ICacheElement<String, Integer> element = new CacheElement<>( "test", keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
            cache.update( element );
        }

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1108 );
        lattr2.setTcpServer( "localhost:1108" );

        LateralTCPService<String, Integer> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        SleepUtil.sleepAtLeast( 300 );

        // DO WORK
        Map<String, ICacheElement<String, Integer>> result = service.getMatching( "test", keyprefix1 + ".+" );

        // VERIFY
        assertNotNull( "Result should not be null.", result );
    }

    public void testGetMatching_WithData_2_oe()
        throws Exception
    {
        // SETUP
        // setup a listener
        TCPLateralCacheAttributes lattr = new TCPLateralCacheAttributes();
        lattr.setTcpListenerPort( 1108 );
        MockCompositeCacheManager cacheMgr = new MockCompositeCacheManager();
        CompositeCache<String, Integer> cache = cacheMgr.getCache( "test" );
//        System.out.println( "mock cache = " + cache );

        // get the listener started
        // give it our mock cache manager
        LateralTCPListener.getInstance( lattr, cacheMgr );

        String keyprefix1 = "MyPrefix1";
        int numToInsertPrefix1 = 10;
        // insert with prefix1
        for ( int i = 0; i < numToInsertPrefix1; i++ )
        {
            // add the item to the listeners cache
            ICacheElement<String, Integer> element = new CacheElement<>( "test", keyprefix1 + String.valueOf( i ), Integer.valueOf( i ) );
            cache.update( element );
        }

        // setup a service to talk to the listener started above.
        TCPLateralCacheAttributes lattr2 = new TCPLateralCacheAttributes();
        lattr2.setTcpListenerPort( 1108 );
        lattr2.setTcpServer( "localhost:1108" );

        LateralTCPService<String, Integer> service = new LateralTCPService<>( lattr2 );
        service.setListenerId( 123456 );

        SleepUtil.sleepAtLeast( 300 );

        // DO WORK
        Map<String, ICacheElement<String, Integer>> result = service.getMatching( "test", keyprefix1 + ".+" );

        // VERIFY
        // removed other assertion
        assertEquals( "Wrong number returned 1:", numToInsertPrefix1, result.size() );
    }

}
